package com.practice.cryptotrading.persistence.wallet;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.practice.cryptotrading.persistence.user.User;
import com.practice.cryptotrading.util.Utils;

/**
 * @author Sin Yee
 *
 */
@Entity
@Table(name = "tbl_wallet_crypto")
public class CryptoWallet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private User user;

	@Column(name = "user_id", nullable = false)
	private long userId;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "quantity_balance", nullable = false)
	private double quantityBalance;

	@Column(name = "created_at", nullable = false)
	private ZonedDateTime createdAt;

	@Column(name = "updated_at")
	private ZonedDateTime updatedAt;

	public CryptoWallet() {

	}

	public CryptoWallet(long userId, String type, double quantityBalance) {
		this.userId = userId;
		this.type = type;
		this.quantityBalance = quantityBalance;
		this.createdAt = Utils.getCurrentTimeStamp();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getQuantityBalance() {
		return quantityBalance;
	}

	public void setQuantityBalance(double quantityBalance) {
		this.quantityBalance = quantityBalance;
	}

	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public ZonedDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(ZonedDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
