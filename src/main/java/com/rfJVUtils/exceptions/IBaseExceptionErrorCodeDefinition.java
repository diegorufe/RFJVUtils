package com.rfJVUtils.exceptions;

/**
 * Base class for create enums for exceptions codes definitions
 *
 * @author diego
 */
public interface IBaseExceptionErrorCodeDefinition {

    /**
     * Method for get code for exception
     *
     * @return code for exceptión
     */
    int getCode();

    /**
     * Method for get type code error exception
     *
     * @return type for code error exception
     */
    String getType();
}
