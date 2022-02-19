package vg.fx.test.Rates.commons;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import vg.fx.test.Rates.vo.RatesVO;

public class RatesParserTest {
	@Test
	public void parseTest() {
		String rates = "106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001\n107, EUR/JPY, 119.60,119.90,01-06-2020 12:01:02:002\n";

		List<RatesVO> data = null;
		try {
			data = RatesParser.parse(rates);
		} catch (Exception e) {

		}
		Assert.assertTrue(data.size() == 2);
		Assert.assertTrue(data.get(0).getCurrencyPair().equals("EUR/USD"));
		Assert.assertTrue(data.get(0).getSource().equals("EUR"));
		Assert.assertTrue(data.get(0).getTarget().equals("USD"));

	}

	@Test
	public void parseTestException() throws Exception {
		String rates = "106, EUR/USD, 1A.1000,1.2000,01-06-2020 12:01:01:001\n107, EUR/JPY, 119.60,119.90,01-06-2020 12:01:02:002\n";

		List<RatesVO> data = null;
		try {
			data = RatesParser.parse(rates);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
