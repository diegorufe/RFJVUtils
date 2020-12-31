package com.rfJVUtils.utils.commons;

import java.util.Map;

/**
 * Class utilties for map
 * 
 * <p>
 * Method to know size map
 * 
 * <ul>
 * 
 * <li>{@link #isEmpty(Map)}</li>
 * <li>{@link #isNotEmpty(Map)}</li>
 * </ul>
 * 
 * @author diego
 *
 */
public class UtilsMap {

	/**
	 * Method to know map is empty. Map for know is empty is collection is null or
	 * empty
	 * 
	 * @param collection to check
	 * @return true if is null or empty false if not
	 */
	@SuppressWarnings("rawtypes")
	public static final boolean isEmpty(Map map) {
		return map == null || map.isEmpty();
	}

	/**
	 * Method to know map is not empty. Map for know is not null and not empty
	 * 
	 * @param collection to check
	 * @return true if is not null and not empty false if not
	 */
	@SuppressWarnings("rawtypes")
	public static final boolean isNotEmpty(Map map) {
		return !UtilsMap.isEmpty(map);
	}
}
