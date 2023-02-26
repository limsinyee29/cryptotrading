package com.practice.cryptotrading.persistence.user;

public class UserResponse {

	private long id;

	private String name;

	private Double walletBalance;

	public UserResponse() {

	}

	public UserResponse(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.walletBalance = user.getWallet() != null ? user.getWallet().getBalance() : 0;
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

	public Double getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(Double walletBalance) {
		this.walletBalance = walletBalance;
	}

}
