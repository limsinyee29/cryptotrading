package com.practice.cryptotrading.persistence.crypto.pricing;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.practice.cryptotrading.util.Utils;

/**
 * @author Sin Yee
 *
 */
@Entity
@Table(name = "tbl_crypto_pricing")
public class CryptoPricing {

	public static final String ORDER_TYPE_SELL = "SELL";
	public static final String ORDER_TYPE_BUY = "BUY";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@Column(name = "crypto_type", nullable = false)
	private String cryptoType;

	@Column(name = "order_type", nullable = false)
	private String orderType;

	@Column(name = "price", nullable = false)
	private double price;

	@Column(name = "created_at", nullable = false)
	private Timestamp createdAt;

	@Column(name = "updated_at")
	private Timestamp updatedAt;

	public CryptoPricing() {

	}

	public CryptoPricing(String cryptoType, String orderType, double price) {
		this.cryptoType = cryptoType;
		this.orderType = orderType;
		this.price = price;
		this.createdAt = Utils.getCurrentTimeStamp();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

}
