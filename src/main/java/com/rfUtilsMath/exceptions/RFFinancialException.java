package com.rfUtilsMath.exceptions;

import com.rfUtilsMath.constants.EnumErrorCodes;

/**
 * Exception for financial utils
 * 
 * @author diego
 *
 */
public class RFFinancialException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4728414523437010498L;
	private EnumErrorCodes enumErrorCodes;

	public RFFinancialException(EnumErrorCodes enumErrorCodes) {
		super();
		this.enumErrorCodes = enumErrorCodes;
	}

	public RFFinancialException(EnumErrorCodes enumErrorCodes, String message) {
		super(message);
		this.enumErrorCodes = enumErrorCodes;
	}

	public EnumErrorCodes getEnumErrorCodes() {
		return enumErrorCodes;
	}

	public void setEnumErrorCodes(EnumErrorCodes enumErrorCodes) {
		this.enumErrorCodes = enumErrorCodes;
	}

}
