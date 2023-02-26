package com.practice.cryptotrading.persistence.user;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	private Timestamp createdAt;

	@Column(name = "updated_at")
	private Timestamp updatedAt;

	@JsonIgnore
	@JsonManagedReference
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Wallet> wallets;

	@JsonManagedReference
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
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