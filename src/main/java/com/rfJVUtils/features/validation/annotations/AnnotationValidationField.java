package com.rfJVUtils.features.validation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.rfJVUtils.features.validation.constants.EnumTypeValidationField;
import com.rfJVUtils.utils.commons.UtilsString;

/**
 * Annotation for validate field bean
 * 
 * @author diego
 *
 */
//Make the annotation available at runtime:
@Retention(RetentionPolicy.RUNTIME)
//Allow to use only on types:
@Target(ElementType.TYPE)
public @interface AnnotationValidationField {

	/**
	 * Method for get key translate field
	 * 
	 * @return key for translate field
	 */
	String keyI18nField() default UtilsString.EMPTY;

	/**
	 * Method for get type validation field
	 * 
	 * @return type validation field
	 */
	EnumTypeValidationField[] typesValidations() default EnumTypeValidationField.UNDEFINED;

	/**
	 * If have key apply validator find by key after apply types validations
	 * 
	 * @return
	 */
	String keyValidator() default UtilsString.EMPTY;
}
