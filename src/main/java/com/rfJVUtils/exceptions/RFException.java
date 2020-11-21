package com.rfJVUtils.exceptions;

/**
 * Exception for financial utils
 * 
 * @author diego
 *
 */
public class RFException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4728414523437010498L;
	private int errorCode;

	public RFException(int errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public RFException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
