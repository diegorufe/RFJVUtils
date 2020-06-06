package com.rfJVUtils.utils;

import java.util.Collection;

/**
 * Class for utifiles for collections
 * 
 * <p>
 * Check empty
 * <ul>
 * <li>{@link #isEmpty(Collection)}</li>
 * <li>{@link #isNotEmpty(Collection)}</li>
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
}
