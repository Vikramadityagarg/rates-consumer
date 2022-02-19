package vg.fx.test.Rates.commons;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import vg.fx.test.Rates.vo.RatesVO;
public class MarginApplierTest {
	@Test
	public void applyMarginTest() {
		RatesVO rates = null;
		try {
			rates = new RatesVO("12345", "GBP/INR", new BigDecimal("100.1000"), new BigDecimal("110.1100"),
					new SimpleDateFormat("dd-MM-yyyy hh:mm:ss:SSS").parse("01-06-2020 12:01:01:001"));
		} catch (ParseException e) {
			
		}
		List<RatesVO> ratesVOs = new ArrayList<RatesVO> ();
		ratesVOs.add(rates);
		MarginApplier.applyMargin(ratesVOs);
		assertTrue(ratesVOs.get(0).getBidPrice().doubleValue()==99.9999d);
		assertTrue(ratesVOs.get(0).getAskPrice().doubleValue()==110.22011d);
		
		
	}
}
