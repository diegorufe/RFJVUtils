package com.rfJVUtils.features.validation.constants;

import com.rfJVUtils.exceptions.IBaseExceptionErrorCodeDefinition;

/**
 * Error codes for operations
 * 
 * @author diego
 *
 */
public enum EnumErrorCodesValidation implements IBaseExceptionErrorCodeDefinition {

	UNDEFINED(-1),

	/**
	 * Bean to validate is null
	 */
	BEAN_TO_VALIDATE_IS_NULL(0xE0000001),

	/**
	 * Field dont accesible value
	 */
	FIELD_DONT_ACCESSIBLE_VALUE(0xE0000002),

	/**
	 * Field null value
	 */
	FIELD_NULL_VALUE(0xE0000003),

	;

	private int code;

	private EnumErrorCodesValidation(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	/**
	 * Method for convert int code to EnumErrorCodesValidation enum
	 * 
	 * @param code for search enum
	 * @return enum if find else return UNDEFINED
	 */
	public static EnumErrorCodesValidation convert(int code) {
		EnumErrorCodesValidation enumErrorCodes = EnumErrorCodesValidation.UNDEFINED;
		for (EnumErrorCodesValidation valueCheck : values()) {
			if (valueCheck.getCode() == code) {
				enumErrorCodes = valueCheck;
				break;
			}
		}
		return enumErrorCodes;
	}

	@Override
	public String getType() {
		return EnumErrorCodesValidation.class.getSimpleName();
	}

}
