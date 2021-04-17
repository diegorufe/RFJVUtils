package com.rfJVUtils.features.validation.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rfJVUtils.exceptions.RFFinancialException;
import com.rfJVUtils.features.validation.IFeatureValidatonField;
import com.rfJVUtils.features.validation.annotations.AnnotationFeatureValidationField;
import com.rfJVUtils.features.validation.annotations.AnnotationValidationBean;
import com.rfJVUtils.features.validation.annotations.AnnotationValidationField;
import com.rfJVUtils.features.validation.constants.EnumErrorCodesValidation;
import com.rfJVUtils.features.validation.constants.EnumTypeValidationField;
import com.rfJVUtils.utils.commons.UtilsCollection;
import com.rfJVUtils.utils.commons.UtilsReflection;
import com.rfJVUtils.utils.commons.UtilsString;

/**
 * Utils validation fields
 * 
 * @author diego
 *
 */
public final class UtilsValidation {

	private static final Logger LOGGER = LoggerFactory.getLogger(UtilsValidation.class);

	private static final String PACKAGE_FIND_ANNOTATIONS_VALIDATIONS = "com.config.features.beans.validators";

	private static Map<String, IFeatureValidatonField> mapValidationFields = null;

	static {
		// Find validations fields
		if (mapValidationFields == null) {
			mapValidationFields = new HashMap<String, IFeatureValidatonField>();

			Set<Class<?>> collectionClasses = UtilsReflection.findAllClassesByAnnotation(
					PACKAGE_FIND_ANNOTATIONS_VALIDATIONS, AnnotationFeatureValidationField.class);

			if (UtilsCollection.isNotEmpty(collectionClasses)) {
				for (Class<?> classAnnotation : collectionClasses) {
					try {
						IFeatureValidatonField validation = (IFeatureValidatonField) classAnnotation.newInstance();
						addValidation(validation);
					} catch (InstantiationException | IllegalAccessException exception) {
						if (LOGGER.isErrorEnabled()) {
							LOGGER.error("Unable to load mapValidationFields");
							LOGGER.error(exception.getLocalizedMessage(), exception);
						}
					}
					break;
				}
			}
		}
	}

	private UtilsValidation() {

	}

	/**
	 * Method for force load utilitie validation for load static validation. For
	 * example call this mehtod on startup server for prevent load validation in
	 * others call method static context
	 */
	public static void forceLoadUtilitieValidation() {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("UtilsValidation is loadeed");
		}
	}

	/**
	 * Method for add validation
	 * 
	 * @param featureValidatonField to add
	 */
	public static void addValidation(IFeatureValidatonField featureValidatonField) {
		if (featureValidatonField != null && UtilsString.isNotBlank(featureValidatonField.getKeyValidation())) {

			if (mapValidationFields == null) {
				mapValidationFields = new HashMap<String, IFeatureValidatonField>();
			}

			mapValidationFields.put(featureValidatonField.getKeyValidation(), featureValidatonField);
		}
	}

	/**
	 * Mhetod for validate null value in field
	 * 
	 * @param locale                    for internationalization
	 * @param valueField                to validate
	 * @param annotationValidationField to get data for example i18n key
	 * @throws RFFinancialException
	 */
	private static void validateNullValue(Locale locale, Object valueField,
			AnnotationValidationField annotationValidationField) throws RFFinancialException {

		if (valueField == null) {
			throwErrorValidation(locale, EnumErrorCodesValidation.FIELD_NULL_VALUE,
					annotationValidationField.keyI18nField());
		}

	}

	/**
	 * Method for throw error validation
	 * 
	 * @param locale               for internationalization
	 * @param errorCodesValidation to throw
	 * @param keyI18nField         to traslate field
	 * @throws RFFinancialException
	 */
	private static void throwErrorValidation(Locale locale, EnumErrorCodesValidation errorCodesValidation,
			String keyI18nField) throws RFFinancialException {
		throw new RFFinancialException(errorCodesValidation);
	}

	/**
	 * Method for validate bean
	 * 
	 * @param locale                     for internationalization
	 * @param bean                       to validate
	 * @param throwExceptionIfBeanIsNull if is true throw exceptión when bean is
	 *                                   null
	 * @throws RFFinancialException
	 */
	public static void validate(Locale locale, Object bean, boolean throwExceptionIfBeanIsNull)
			throws RFFinancialException {

		if (bean != null) {

			// If validation present
			if (bean.getClass().isAnnotationPresent(AnnotationValidationBean.class)) {
				List<Field> listFields = UtilsReflection.getListDeclaredFields(bean.getClass());

				if (UtilsCollection.isNotEmpty(listFields)) {

					Object valueField = null;

					for (Field field : listFields) {
						if (field.isAnnotationPresent(AnnotationValidationField.class)) {
							boolean fieldSetAccessible = true;

							try {
								field.setAccessible(true);
							} catch (SecurityException ignored) {
								fieldSetAccessible = false;
							}

							// If field dont accesible dont validate
							if (!fieldSetAccessible) {
								continue;
							}

							AnnotationValidationField annotationValidationField = field
									.getAnnotation(AnnotationValidationField.class);
							EnumTypeValidationField[] arrayTypeValidationFIeld = annotationValidationField
									.typesValidations();

							if (UtilsCollection.isArrayNotEmpty(arrayTypeValidationFIeld)) {

								try {
									valueField = field.get(bean);
								} catch (IllegalArgumentException | IllegalAccessException exception) {
									throwErrorValidation(locale, EnumErrorCodesValidation.FIELD_DONT_ACCESSIBLE_VALUE,
											annotationValidationField.keyI18nField());

								}

								for (EnumTypeValidationField typeValidationField : arrayTypeValidationFIeld) {
									switch (typeValidationField) {
									case NOT_NULL:
										validateNullValue(locale, valueField, annotationValidationField);
										break;

									default:
										break;
									}
								}
							}

							// TODO apply validator
						}
					}

				}
			}

		} else {
			// TODO throwExceptionIfBeanIsNull
			if (throwExceptionIfBeanIsNull) {
				throw new RFFinancialException(EnumErrorCodesValidation.BEAN_TO_VALIDATE_IS_NULL);
			}
		}

	}
}
