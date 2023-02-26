package com.practice.cryptotrading.controller;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.practice.cryptotrading.constraint.WhiteList;

/**
 * @author Sin Yee
 *
 */
public class TradeRequest {

	@NotBlank
	@WhiteList(value = { "ETHUSDT", "BTCUSDT" }, message = "Value must be ETHUSDT or BTCUSDT only")
	String cryptoType;

	@NotBlank
	@WhiteList(value = { "BUY", "SELL" }, message = "Value must be BUY or SELL only")
	String orderType;

	@Positive(message = "Must more than 0")
	@NotNull
	Double quantity;

	public TradeRequest() {

	}

	public TradeRequest(String cryptoType, String orderType, Double quantity) {
		this.cryptoType = cryptoType;
		this.orderType = orderType;
		this.quantity = quantity;
	}

	public String getCryptoType() {
		return cryptoType;
	}

	public void setCryptoType(String cryptoType) {
		this.cryptoType = cryptoType;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

}
