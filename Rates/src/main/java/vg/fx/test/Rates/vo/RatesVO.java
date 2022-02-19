package vg.fx.test.Rates.vo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class RatesVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3767079090353156461L;

	private String uid;
	/**
	 * 
	 * Assignment states Instrument Name. Rather it should be currency p[air
	 * */

	private String currencyPair;
	/**
	 * Calculations are always accurate with BigDecimal
	 * */

	private BigDecimal bidPrice;
	/**
	 * Calculations are always accurate with BigDecimal
	 * */

	private BigDecimal askPrice;

	private Date creationTime;
	
	public RatesVO(String string, String string2, BigDecimal parseDouble, BigDecimal parseDouble2, Date parse) {
		this.uid = string;
		this.currencyPair = string2;
		this.bidPrice = parseDouble;
		this.askPrice = parseDouble2;
		this.creationTime = parse;
	}

	public String getSource() {
		return currencyPair.substring(0, currencyPair.indexOf('/'));
	}
	
	public String getTarget() {
		return currencyPair.substring(currencyPair.indexOf('/')+1);
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getCurrencyPair() {
		return currencyPair;
	}

	public void setCurrencyPair(String currencyPair) {
		this.currencyPair = currencyPair;
	}

	public BigDecimal getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(BigDecimal bidPrice) {
		this.bidPrice = bidPrice;
	}

	public BigDecimal getAskPrice() {
		return askPrice;
	}

	public void setAskPrice(BigDecimal askPrice) {
		this.askPrice = askPrice;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

}
