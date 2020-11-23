package com.rfJVUtils.utils.commons;

import java.math.BigDecimal;

/**
 * Class utilities BigDecimal
 * 
 * <ul>
 * <li>{@link #numberOfDecimalPlaces(BigDecimal)}</li>
 * </ul>
 * 
 * @author diego
 *
 */
public final class UtilsBigDecimal {

	private UtilsBigDecimal() {

	}

	/**
	 * Method for get number of places decimal in BigDecimal
	 * 
	 * @param bigDecimal is bigdecimal to get number of places decimal
	 * @return number of places decimal
	 */
	public static final int numberOfDecimalPlaces(BigDecimal bigDecimal) {
		String string = bigDecimal.stripTrailingZeros().toPlainString();
		int index = string.indexOf(".");
		return index < 0 ? 0 : string.length() - index - 1;
	}
}
