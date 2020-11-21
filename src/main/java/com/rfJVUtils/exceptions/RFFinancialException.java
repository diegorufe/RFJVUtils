package com.rfJVUtils.exceptions;

/**
 * Exception for financial utils
 * 
 * @author diego
 *
 */
public class RFFinancialException extends RFException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4728414523437010498L;

	public RFFinancialException(int errorCode) {
		super(errorCode);
	}

	public RFFinancialException(int errorCode, String message) {
		super(errorCode, message);
	}

}
