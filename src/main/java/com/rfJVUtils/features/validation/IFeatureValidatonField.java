package com.rfJVUtils.features.validation;

import java.lang.reflect.Field;

import com.rfJVUtils.exceptions.RFException;

/**
 * Feature for validate field for object
 * 
 * @author diego
 *
 */
public interface IFeatureValidatonField {

	/**
	 * Method for validate fields bean and field
	 * 
	 * @param bean  to validate
	 * @param field to validate
	 * @throws RFException when not valid field
	 */
	public void validate(Object bean, Field field) throws RFException;
	
	/**
	 * Method for get key validation for store in map validations 
	 * @return
	 */
	public String getKeyValidation();
}
