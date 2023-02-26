package com.practice.cryptotrading.persistence.crypto.source;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author Sin Yee
 *
 */
@JsonDeserialize(using = BinanceDeserializer.class)
public class Binance {

	private String symbol;

	private Double bidPrice;

	private Double askPrice;

	public Binance() {

	}

	public Binance(String symbol, Double bidPrice, Double askPrice) {
		this.symbol = symbol;
		this.bidPrice = bidPrice;
		this.askPrice = askPrice;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(Double bidPrice) {
		this.bidPrice = bidPrice;
	}

	public Double getAskPrice() {
		return askPrice;
	}

	public void setAskPrice(Double askPrice) {
		this.askPrice = askPrice;
	}

}
