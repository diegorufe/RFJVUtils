package com.rfJVUtils.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Utilities for reflection
 * 
 * <p>
 * Fields
 * <ul>
 * <li>{@link #getValueField(Object, Class, String)}</li>
 * <li>{@link #setValueField(Object, Class, String, Object)}</li>
 * <li>{@link #instaceValueField(Object, Class, String)}</li>
 * </ul>
 * 
 * @author diego
 *
 */
public final class UtilsReflection {

	private UtilsReflection() {

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
				value = field.getType().getClass().newInstance();
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException
					| InstantiationException ignored) {
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
				Field field = data.getClass().getDeclaredField(fieldName);
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
