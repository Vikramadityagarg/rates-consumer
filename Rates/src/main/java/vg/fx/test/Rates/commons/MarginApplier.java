package vg.fx.test.Rates.commons;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import vg.fx.test.Rates.vo.RatesVO;

/**
 * This class will apply Margin to the Rates
 * 
 */
public class MarginApplier {
	public static void applyMargin(List<RatesVO> rates) {
		rates.forEach(rate -> {
			rate.setBidPrice(rate.getBidPrice().add(
					rate.getBidPrice().multiply(
							RatesConstant.BID_PIP_MARGIN,
							MathContext.DECIMAL64),
					MathContext.DECIMAL64));
			rate.setAskPrice(rate.getAskPrice().add(
					rate.getAskPrice().multiply(
							RatesConstant.ASK_PIP_MARGIN,
							MathContext.DECIMAL64),
					MathContext.DECIMAL64));
		});
	}
	/**
	 * Developer Test Method
	 * */
	public static void main(String[] args) {
		try {
			RatesVO rates = new RatesVO("12345", "GBP/INR", new BigDecimal("100.1000"), new BigDecimal("110.1100"),
					new SimpleDateFormat("dd-MM-yyyy hh:mm:ss:SSS").parse("01-06-2020 12:01:01:001"));
			List<RatesVO> ratesVOs = new ArrayList<RatesVO> ();
			ratesVOs.add(rates);
			applyMargin(ratesVOs);
			System.out.println(ratesVOs.get(0).getBidPrice());
			System.out.println(ratesVOs.get(0).getAskPrice());
			
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}
