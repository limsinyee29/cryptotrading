package com.practice.cryptotrading.persistence.user;

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.practice.cryptotrading.persistence.wallet.CryptoWallet;
import com.practice.cryptotrading.persistence.wallet.Wallet;

/**
 * @author Sin Yee
 *
 */
@Entity
@Table(name = "tbl_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	private String name;

	@Column(name = "created_at", nullable = false)
	private ZonedDateTime createdAt;

	@Column(name = "updated_at")
	private ZonedDateTime updatedAt;

	@JsonIgnore
	@JsonManagedReference
	@OneToMany(mappedBy = "user")
	private List<Wallet> wallets;

	@JsonManagedReference
	@OneToMany(mappedBy = "user")
	private List<CryptoWallet> cryptoWallets;

	public User() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<Wallet> getWallets() {
		return wallets;
	}

	public void setWallets(List<Wallet> wallets) {
		this.wallets = wallets;
	}

	public List<CryptoWallet> getCryptoWallets() {
		return cryptoWallets;
	}

	public void setCryptoWallets(List<CryptoWallet> cryptoWallets) {
		this.cryptoWallets = cryptoWallets;
	}

	public Wallet getWallet() {
		if (!wallets.isEmpty()) {
			return wallets.get(0);
		}
		return null;
	}

	public void setWallet(Wallet wallet) {
		this.wallets.clear();
		this.wallets.add(wallet);
	}

}