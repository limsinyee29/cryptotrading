package com.practice.cryptotrading.error;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * @author Sin Yee
 *
 */
public enum ErrorCode {

	CRYPTO_PRICING_NOT_FOUND("crypto.pricing.not.found"), USER_NOT_FOUND("user.not.found"),
	WALLET_NOT_FOUND("wallet.not.found"), BALANCE_INSUFFICIENT("balance.insufficient");

	String errorCode;
	String errorMessage;

	/**
	 * @param errorCode
	 */
	private ErrorCode(String errorCode) {
		this.errorCode = errorCode;
		// this.errorMessage = ErrorMessage.getMessage(errorCode);
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}
}

class ErrorMessage {

	private static Properties errorMessages;

	private static void loadErrorMessage() throws IOException {
		synchronized (ErrorMessage.class) {
			if (errorMessages == null) {
				Resource resource = new ClassPathResource("/errors.message");
				errorMessages = PropertiesLoaderUtils.loadProperties(resource);
			}
		}
	}

	public static String getMessage(String errorCode) {
		if (errorMessages == null) {
//			try {
//				loadErrorMessage();
//			} catch (IOException e) {
//				throw new RuntimeException("Unable to load error messsage");
//			}
		}
		return errorMessages.getProperty(errorCode, "Error message is not defined");
	}

}
