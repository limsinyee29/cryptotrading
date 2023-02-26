package com.practice.cryptotrading.persistence.crypto.transaction;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.practice.cryptotrading.persistence.user.User;
import com.practice.cryptotrading.persistence.wallet.CryptoWallet;
import com.practice.cryptotrading.persistence.wallet.Wallet;
import com.practice.cryptotrading.util.Utils;

/**
 * @author Sin Yee
 *
 */
@Entity
@Table(name = "tbl_transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@Column(name = "user_id", nullable = false)
	private long user;

	@Column(name = "wallet_id")
	private Long wallet;

	@Column(name = "wallet_crypto_id")
	private Long cryptoWallet;

	@Column(name = "transaction_type", nullable = false)
	private String transactionType;

	@Column(name = "quantity", nullable = false)
	private double quantity;

	@Column(name = "pricing", nullable = false)
	private double pricing;

	@Column(name = "total_amount", nullable = false)
	private double totalAmount;

	@Column(name = "created_at", nullable = false)
	private Timestamp createdAt;

	public Transaction() {

	}

	public Transaction(User user, Wallet wallet, CryptoWallet cryptoWallet, String transactionType, double quantity,
			double pricing, double totalAmount) {
		this.user = user.getId();
		this.wallet = wallet.getId();
		this.cryptoWallet = cryptoWallet.getId();
		this.transactionType = transactionType;
		this.quantity = quantity;
		this.pricing = pricing;
		this.totalAmount = totalAmount;
		this.createdAt = Utils.getCurrentTimeStamp();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUser() {
		return user;
	}

	public void setUser(long user) {
		this.user = user;
	}

	public Long getWallet() {
		return wallet;
	}

	public void setWallet(Long wallet) {
		this.wallet = wallet;
	}

	public Long getCryptoWallet() {
		return cryptoWallet;
	}

	public void setCryptoWallet(Long cryptoWallet) {
		this.cryptoWallet = cryptoWallet;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getPricing() {
		return pricing;
	}

	public void setPricing(double pricing) {
		this.pricing = pricing;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

}
