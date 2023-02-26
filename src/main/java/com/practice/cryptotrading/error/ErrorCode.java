package com.practice.cryptotrading.error;

/**
 * @author Sin Yee
 *
 */
public enum ErrorCode {

	UNKNOWN("unknown.error.occured","Unknown error occured"),INVALID_FIELD("error.invalid.field","Invalid field"),
	CRYPTO_PRICING_NOT_FOUND("crypto.pricing.not.found", "Crypto pricing not found"),
	USER_NOT_FOUND("user.not.found", "User not found"), WALLET_NOT_FOUND("wallet.not.found", "Wallet not found"),
	BALANCE_INSUFFICIENT("balance.insufficient", "Balance insufficient");

	String errorCode;
	String errorMessage;

	/**
	 * @param errorCode
	 */
	private ErrorCode(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}
}