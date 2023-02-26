package com.practice.cryptotrading.exception;

import java.util.Arrays;
import java.util.List;

import com.practice.cryptotrading.error.ErrorCode;

/**
 * @author Sin Yee
 *
 */
public class ErrorResponse {

	private List<Error> errors;

	public ErrorResponse(List<Error> errors) {
		this.errors = errors;
	}

	public ErrorResponse(Error error) {
		this.errors = Arrays.asList(error);
	}

	public ErrorResponse(ErrorCode error) {
		this(new Error(error.getErrorCode(), error.getErrorMessage()));
	}

	public ErrorResponse(String errorMessage) {
		this(new Error(errorMessage));
	}

	public ErrorResponse(String errorCode, String errorMessage) {
		this(new Error(errorCode, errorMessage));
	}

	public ErrorResponse(String value, String errorCode, String errorMessage) {
		this(new Error(value, errorCode, errorMessage));
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	public static class Error {

		private String value;

		private String errorCode;

		private String errorMessage;

		public Error(String errorMessage) {
			super();
			this.errorMessage = errorMessage;
		}

		public Error(String errorCode, String errorMessage) {
			super();
			this.errorCode = errorCode;
			this.errorMessage = errorMessage;
		}

		public Error(String value, String errorCode, String errorMessage) {
			super();
			this.value = value;
			this.errorCode = errorCode;
			this.errorMessage = errorMessage;
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

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

}
