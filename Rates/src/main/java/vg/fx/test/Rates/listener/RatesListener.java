package vg.fx.test.Rates.listener;

import java.util.List;

import javax.jms.JMSException;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vg.fx.test.Rates.alert.AlertService;
import vg.fx.test.Rates.commons.MarginApplier;
import vg.fx.test.Rates.commons.RatesConstant;
import vg.fx.test.Rates.commons.RatesParser;
import vg.fx.test.Rates.commons.RatesSubmitter;
import vg.fx.test.Rates.vo.RatesVO;
import org.springframework.core.*;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * I am making an assumption that I am connecting to an ActiveMQ Queue and my
 * listener will connect to this queue and will process the messages from
 * there...
 * 
 */
@Service
public class RatesListener implements MessageListener {

	Logger logger = LoggerFactory.getLogger(RatesListener.class.getName());
	
	
	@Autowired
	private AlertService alertService;
	
	@Autowired
	private RatesSubmitter ratesSubmitter;
	
	

	@Override
	public void onMessage(Message message) {
		String dataString = "";
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
			try {
				dataString = textMessage.getText();
				/**
				 * Lets parse the message and extract it into a POJO
				 */
				List<RatesVO> extractedRates = RatesParser.parse(dataString);
				if (extractedRates.size() > 0) {
					logger.info("Total Number of extracted rates", extractedRates.size());
					/**
					 * Lets Apply commission/margin/brokerage
					 * */
					MarginApplier.applyMargin(extractedRates);
					ratesSubmitter.postRates(extractedRates);

				} else {
					logger.error("FATAL: DataString {} cannot be parsed", dataString);
					/**
					 * Raise a alert notification about a nessage not parsable into ErrorSystem
					 */
					alertService.raiseErrorAlert("Some Error Processing input data", dataString);
				}

			} catch (Exception e) {

				logger.error("Exception while extracting Message", e);
				/**
				 * Raise a alert notification about a nessage not parsable
				 */
				alertService.raiseErrorAlert("Some Error Parsing input data", dataString);

			}
		} else {
			/*
			 * We made assumption that message is always text message
			 */
			logger.error("Exception while extracting Message");
			alertService.raiseErrorAlert("A Format other than Text received", "Data Format Incompatible");
		}

	}

}
