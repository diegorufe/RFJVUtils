package com.rfJVUtils.exceptions;

/**
 * Exception for financial utils
 * 
 * @author diego
 *
 */
public class RFInvoiceExpcetion extends RFException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4728414523437010498L;

	public RFInvoiceExpcetion(int errorCode) {
		super(errorCode);
	}

	public RFInvoiceExpcetion(int errorCode, String message) {
		super(errorCode, message);
	}

}
