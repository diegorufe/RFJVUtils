package com.rfJVUtils.constants;

/**
 * Error codes for operations
 * 
 * @author diego
 *
 */
public enum EnumErrorCodes {

	UNDEFINED(-1),

	/**
	 * General code error
	 */
	GENERAL(0xE0000001),

	/**
	 * Code for null values
	 */
	NULL_VALUES(0xE0000002),

	/**
	 * Code for division by zero
	 */
	ARITEMICAL_EXCEPTION_DIVISION_BY_ZERO(0xE0000003),

	;

	private int code;

	private EnumErrorCodes(int code) {
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
	public static EnumErrorCodes convert(int code) {
		EnumErrorCodes enumErrorCodes = EnumErrorCodes.UNDEFINED;
		for (EnumErrorCodes valueCheck : values()) {
			if (valueCheck.getCode() == code) {
				enumErrorCodes = valueCheck;
				break;
			}
		}
		return enumErrorCodes;
	}

}
