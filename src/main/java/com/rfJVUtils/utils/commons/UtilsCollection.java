package com.rfJVUtils.utils.commons;

import java.util.Collection;

/**
 * Class for utifiles for collections
 * 
 * <p>
 * Check empty
 * <ul>
 * <li>{@link #isEmpty(Collection)}</li>
 * <li>{@link #isNotEmpty(Collection)}</li>
 * <li>{@link #isArrayEmpty(Object[])}</li>
 * <li>{@link #isArrayNotEmpty(Object[])}</li>
 * </ul>
 * <p>
 * Check contains values
 * <ul>
 * <li>{@link #contains(Object[], Object)}</li>
 * </ul>
 * 
 * @author diego
 *
 */
public final class UtilsCollection {

	private UtilsCollection() {

	}

	/**
	 * Method to know collecion is empty. Condictions for know is empty is
	 * collection is null or empty
	 * 
	 * @param collection to check
	 * @return true if is null or empty false if not
	 */
	@SuppressWarnings("rawtypes")
	public static final boolean isEmpty(Collection collection) {
		return collection == null || collection.isEmpty();
	}

	/**
	 * Method to know collecion is not empty. Condictions for know is not null and
	 * not empty
	 * 
	 * @param collection to check
	 * @return true if is not null and not empty false if not
	 */
	@SuppressWarnings("rawtypes")
	public static final boolean isNotEmpty(Collection collection) {
		return !UtilsCollection.isEmpty(collection);
	}

	/**
	 * Method to check array is empty. Condictions for know is null or empty
	 * 
	 * @param <T>
	 * @param array to check
	 * @return true if array is null or is empty
	 */
	public static final <T> boolean isArrayEmpty(T[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * Method to check array is not empty. Condictions for know is not null and not
	 * empty
	 * 
	 * @param <T>
	 * @param array to check
	 * @return true if array is not null and not empty
	 */
	public static final <T> boolean isArrayNotEmpty(T[] array) {
		return !UtilsCollection.isArrayEmpty(array);
	}

	/**
	 * Method to check array contains object
	 * 
	 * @param array  to check contains object
	 * @param object to check is inside array
	 * @return true if object inside array
	 */
	public static boolean contains(Object[] array, Object object) {
		if (array == null || array.length == 0) {
			return false;
		}

		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(object)) {
				return true;
			}
		}

		return false;
	}
}
