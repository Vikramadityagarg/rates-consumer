package vg.fx.test.Rates.commons;

import java.io.StringReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.toList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.Ostermiller.util.CSVParser;
import vg.fx.test.Rates.vo.RatesVO;

/**
 * This parser is based on ostermiller which is very primitive CSV Library and
 * works efficiently
 * 
 */
public class RatesParser {
	private static final String DATE_FORMAT = "dd-MM-yyyy hh:mm:ss:SSS";

	static Logger logger = LoggerFactory.getLogger(RatesParser.class.getName());

	public static List<RatesVO> parse(String ratesString) throws Exception {
		CSVParser parser = new CSVParser(new StringReader(ratesString), ',');
		try {
			String[][] dataValues = parser.getAllValues();
			return fillDataValues(dataValues);

		} catch (Exception e) {
			logger.error("Exception while extracting Message", e);
			throw e;
		}
	}

	private static List<RatesVO> fillDataValues(String[][] dataValues) throws Exception {
		List<String[]> dataItems = Arrays.asList(dataValues);
		List<RatesVO> ratesVO = dataItems.stream().map((String[] a) -> fillDataValues(a)).collect(toList());
		return ratesVO;
	}

	private static RatesVO fillDataValues(String[] dataValues) {
		RatesVO rates = null;
		try {
			rates = new RatesVO(dataValues[0].trim(), dataValues[1].trim(), new BigDecimal(dataValues[2].trim()),
					new BigDecimal(dataValues[3].trim()), new SimpleDateFormat(DATE_FORMAT).parse(dataValues[4]));
		} catch (NumberFormatException | ParseException e) {
			logger.error("Exception while parsing Message : " + dataValues, e);
		}
		return rates;
	}
}
