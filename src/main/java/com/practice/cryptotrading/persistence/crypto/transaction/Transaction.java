package com.practice.cryptotrading.persistence.crypto.transaction;

import java.time.ZonedDateTime;

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
	private long userId;

	@Column(name = "wallet_id")
	private Long walletId;

	@Column(name = "wallet_new_balance")
	private Double walletNewBalance;

	@Column(name = "wallet_crypto_id")
	private Long cryptoWalletId;

	@Column(name = "wallet_crypto_new_quantity_balance")
	private Double cryptoWalletNewQtyBalance;

	@Column(name = "order_type", nullable = false)
	private String orderType;

	@Column(name = "quantity", nullable = false)
	private double quantity;

	@Column(name = "pricing", nullable = false)
	private double pricing;

	@Column(name = "total_amount", nullable = false)
	private double totalAmount;

	@Column(name = "created_at", nullable = false)
	private ZonedDateTime createdAt;

	public Transaction() {

	}

	public Transaction(User user, Wallet wallet, CryptoWallet cryptoWallet, String orderType, double quantity,
			double pricing, double totalAmount) {
		this.userId = user.getId();
		this.walletId = wallet.getId();
		this.walletNewBalance = wallet.getBalance();
		this.cryptoWalletId = cryptoWallet.getId();
		this.cryptoWalletNewQtyBalance = cryptoWallet.getQuantityBalance();
		this.orderType = orderType;
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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Long getWalletId() {
		return walletId;
	}

	public void setWalletId(Long walletId) {
		this.walletId = walletId;
	}

	public Double getWalletNewBalance() {
		return walletNewBalance;
	}

	public void setWalletNewBalance(Double walletNewBalance) {
		this.walletNewBalance = walletNewBalance;
	}

	public Long getCryptoWalletId() {
		return cryptoWalletId;
	}

	public void setCryptoWalletId(Long cryptoWalletId) {
		this.cryptoWalletId = cryptoWalletId;
	}

	public Double getCryptoWalletNewQtyBalance() {
		return cryptoWalletNewQtyBalance;
	}

	public void setCryptoWalletNewQtyBalance(Double cryptoWalletNewQtyBalance) {
		this.cryptoWalletNewQtyBalance = cryptoWalletNewQtyBalance;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
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

	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
