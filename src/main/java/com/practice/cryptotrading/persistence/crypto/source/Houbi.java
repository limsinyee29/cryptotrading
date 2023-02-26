package com.practice.cryptotrading.persistence.crypto.source;

/**
 * @author Sin Yee
 *
 */
public class Houbi {

	private String symbol;

	private Double bid;

	private Double ask;

	public Houbi() {
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getBid() {
		return bid;
	}

	public void setBid(Double bid) {
		this.bid = bid;
	}

	public Double getAsk() {
		return ask;
	}

	public void setAsk(Double ask) {
		this.ask = ask;
	}

}
