package vg.fx.test.Rates.commons;

import java.math.BigDecimal;

public interface RatesConstant {
	/***
	 * Assuming that this would be loaded from a Database/Property File
	 * I am still going to hardcode this value. We will apply signage here only to keep 
	 * it simple
	 */
	public static final BigDecimal BID_PIP_MARGIN = new BigDecimal(-0.001);
	public static final BigDecimal ASK_PIP_MARGIN = new BigDecimal(0.001);
	public static final String ServerURL = "http://localhost:9000/rates/add";
}
