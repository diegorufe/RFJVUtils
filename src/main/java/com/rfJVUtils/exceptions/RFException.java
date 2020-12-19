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
	private String typeCodeErrorException;
	private IBaseExceptionErrorCodeDefinition baseExceptionErrorCodeDefinition;

	public RFException(IBaseExceptionErrorCodeDefinition baseExceptionErrorCodeDefinition) {
		this(baseExceptionErrorCodeDefinition, null);
	}

	public RFException(IBaseExceptionErrorCodeDefinition baseExceptionErrorCodeDefinition, String message) {
		this(baseExceptionErrorCodeDefinition, message, null);
	}

	public RFException(IBaseExceptionErrorCodeDefinition baseExceptionErrorCodeDefinition, String message,
			Exception exception) {
		super(message, exception);
		this.baseExceptionErrorCodeDefinition = baseExceptionErrorCodeDefinition;
		if (baseExceptionErrorCodeDefinition != null) {
			this.errorCode = baseExceptionErrorCodeDefinition.getCode();
			this.typeCodeErrorException = baseExceptionErrorCodeDefinition.getType();
		}
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getTypeCodeErrorException() {
		return typeCodeErrorException;
	}

	public void setTypeCodeErrorException(String typeCodeErrorException) {
		this.typeCodeErrorException = typeCodeErrorException;
	}

	public IBaseExceptionErrorCodeDefinition getBaseExceptionErrorCodeDefinition() {
		return baseExceptionErrorCodeDefinition;
	}

}
