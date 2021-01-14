package com.rfJVUtils.utils.commons;

import java.util.LinkedHashMap;
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
 * <p>
 * Other methods
 * 
 * <li>{@link #mergeMapsIntoLinkedHashMap(Map...)}</li>
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

	/**
	 * Method for merge maps into LinkedHashMap
	 * 
	 * @param <K>           key type for map
	 * @param <V>           value type for map
	 * @param arrayMapMerge array maps to merge
	 * @return LinkedHashMap with map merged
	 */
	@SuppressWarnings("unchecked")
	public static final <K, V> Map<K, V> mergeMapsIntoLinkedHashMap(Map<K, V>... arrayMapMerge) {
		Map<K, V> mapResultMerged = new LinkedHashMap<K, V>();
		if (UtilsCollection.isArrayNotEmpty(arrayMapMerge)) {
			for (Map<K, V> mapToMerge : arrayMapMerge) {
				if (mapToMerge != null) {
					for (K keyMap : mapToMerge.keySet()) {
						mapResultMerged.put(keyMap, mapToMerge.get(keyMap));
					}
				}
			}
		}
		return mapResultMerged;
	}
}
