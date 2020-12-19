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

	public RFInvoiceExpcetion(IBaseExceptionErrorCodeDefinition baseExceptionErrorCodeDefinition) {
		super(baseExceptionErrorCodeDefinition);
	}

	public RFInvoiceExpcetion(IBaseExceptionErrorCodeDefinition baseExceptionErrorCodeDefinition, String message) {
		super(baseExceptionErrorCodeDefinition, message);
	}

}
