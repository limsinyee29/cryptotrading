package com.practice.cryptotrading.restservice;

/**
 * @author Sin Yee
 *
 */
public class RestServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3877591608800810977L;

	private int status;

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @param cause
	 */
	public RestServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
