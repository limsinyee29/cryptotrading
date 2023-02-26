package com.practice.cryptotrading.exception;

import com.practice.cryptotrading.error.ErrorCode;

/**
 * @author Sin Yee
 *
 */
public class ServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 455849498648346439L;

	private String errorCode;

	private String errorMessage;

	/**
	 * 
	 */
	public ServiceException() {
		super();
	}

	/**
	 * @param errorCode
	 * @param errorMessage
	 */
	public ServiceException(String errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	/**
	 * @param errorCode
	 */
	public ServiceException(ErrorCode errorCode) {
		super(errorCode.getErrorMessage());
		this.errorCode = errorCode.getErrorCode();
	}

	/**
	 * @param errorCode
	 * @param errorMessage
	 */
	public ServiceException(String message) {
		super(message);
		this.errorMessage = message;
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		this.errorMessage = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
