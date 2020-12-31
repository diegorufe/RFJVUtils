package com.rfJVUtils.utils.commons;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Utilities for reflection
 * 
 * <p>
 * Fields
 * <ul>
 * <li>{@link #getValueField(Object, Class, String)}</li>
 * <li>{@link #setValueField(Object, Class, String, Object)}</li>
 * <li>{@link #instaceValueField(Object, Class, String)}</li>
 * <li>{@link #getListDeclaredFields(Class)}</li>
 * </ul>
 * 
 * @author diego
 *
 */
public final class UtilsReflection {

	private UtilsReflection() {

	}

	/**
	 * Method for get list declared fields
	 * 
	 * @param classData to get declared fields
	 * @return list of declared fields from class. Never returns null
	 */
	public final static List<Field> getListDeclaredFields(@SuppressWarnings("rawtypes") Class classData) {
		List<Field> listDeclaredFields = new ArrayList<Field>();
		if (classData != null) {
			Field[] arrFields = classData.getDeclaredFields();
			if (UtilsCollection.isArrayNotEmpty(arrFields)) {
				for (Field field : arrFields) {
					listDeclaredFields.add(field);
				}
			}
			// Get declared fields for superclass
			if (classData.getSuperclass() != Object.class) {
				listDeclaredFields.addAll(UtilsReflection.getListDeclaredFields(classData.getSuperclass()));
			}
		}
		return listDeclaredFields;
	}

	/**
	 * Method for instance value field
	 * 
	 * @param data
	 * @param fieldName
	 * @return
	 */
	public final static Object instaceValueField(Object data, @SuppressWarnings("rawtypes") Class classData,
			String fieldName) {
		Object value = null;
		if (data != null && UtilsString.isNotEmpty(fieldName)) {
			try {
				Field field = data.getClass().getDeclaredField(fieldName);
				// This is deprecated
				//field.getType().getClass().newInstance();
				value = field.getType().getClass().getDeclaredConstructor().newInstance();
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException
					| InstantiationException | InvocationTargetException | NoSuchMethodException ignored) {
				if (classData.getSuperclass() != Object.class) {
					value = instaceValueField(data, data.getClass().getSuperclass(), fieldName);
				}
			}
		}
		return value;
	}

	/**
	 * Method for get value field
	 * 
	 * @param data      to get value
	 * @param fieldName to get data for value
	 * @param classData to get data for value
	 * @return value for field exits
	 */
	public final static Object getValueField(Object data, @SuppressWarnings("rawtypes") Class classData,
			String fieldName) {
		Object value = null;
		if (data != null && UtilsString.isNotEmpty(fieldName)) {
			try {
				Field field = classData.getDeclaredField(fieldName);
				field.setAccessible(true);
				value = field.get(data);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException
					| IllegalAccessException ignored) {
				if (classData.getSuperclass() != Object.class) {
					value = getValueField(data, data.getClass().getSuperclass(), fieldName);
				}
			}
		}
		return value;
	}

	/**
	 * Method for set value field
	 * 
	 * @param data      to set value
	 * @param classData to get data for value
	 * @param fieldName to set value
	 * @param value     to set in data
	 */
	public final static void setValueField(Object data, @SuppressWarnings("rawtypes") Class classData, String fieldName,
			Object value) {
		if (data != null && UtilsString.isNotEmpty(fieldName)) {
			try {
				Field field = classData.getDeclaredField(fieldName);
				field.setAccessible(true);
				field.set(data, value);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException
					| IllegalAccessException ignored) {
				if (classData.getSuperclass() != Object.class) {
					setValueField(data, data.getClass().getSuperclass(), fieldName, value);
				}
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public final static <T> Class<?>[] getGenericInterfaceParameter(Class clazz, Class<T> interfaceType) {
		Class<?>[] result = new Class<?>[0];
		// make sure interfaceType is a generic interface.
		if (!interfaceType.isInterface() || interfaceType.getTypeParameters().length < 1) {
			return result;
		}
		// get all interfaces implemented by concrete class.
		Type[] interfaceTypes = clazz.getGenericInterfaces();

		// for each interface the concrete class implements
		// we check if that interface is actually equal to interfaceType
		// and is a parametrized type,
		// i.e has a type parameter.
		// Once a match is found, we return the type parameters.
		for (Type it : interfaceTypes) {
			if (it instanceof ParameterizedType) {
				ParameterizedType parameterizedType = (ParameterizedType) it;
				if (!parameterizedType.getRawType().equals(interfaceType)) {
					continue;
				}
				Type[] typeParameters = parameterizedType.getActualTypeArguments();
				result = new Class[typeParameters.length];
				for (int j = 0; j < typeParameters.length; j++) {
					result[j] = (Class) typeParameters[j];
				}
			}
		}

		return result;
	}
}
