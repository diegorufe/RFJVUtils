package com.rfJVUtils.constants.financial;

import com.rfJVUtils.exceptions.IBaseExceptionErrorCodeDefinition;

/**
 * Error codes for invoice operations
 * 
 * @author diego
 *
 */
public enum EnumErrorCodesInvoice implements IBaseExceptionErrorCodeDefinition {

	UNDEFINED(-1),

	/**
	 * General code error
	 */
	GENERAL(0xFE0001),

	/**
	 * Line invoice is not valid
	 */
	LINE_INVOICE_IS_NOT_VALID(0xFE0002),

	;

	private int code;

	private EnumErrorCodesInvoice(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	/**
	 * Method for convert int code to EnumErrorCodes enum
	 * 
	 * @param code for search enum
	 * @return enum if find else return UNDEFINED
	 */
	public static EnumErrorCodesInvoice convert(int code) {
		EnumErrorCodesInvoice enumErrorCodes = EnumErrorCodesInvoice.UNDEFINED;
		for (EnumErrorCodesInvoice valueCheck : values()) {
			if (valueCheck.getCode() == code) {
				enumErrorCodes = valueCheck;
				break;
			}
		}
		return enumErrorCodes;
	}

	@Override
	public String getType() {
		return EnumErrorCodesInvoice.class.getSimpleName();
	}

}
