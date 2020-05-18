package com.rfUtilsFinancial.utils;

/**
 * Class utilites for String
 * 
 * <p>
 * Method for pad
 * <ul>
 * <li>{@link #padRight(String, int)}</li>
 * <li>{@link #padLeft(String, int)}</li>
 * </ul>
 * 
 * @author diego
 *
 */
public final class UtilsString {

	private UtilsString() {

	}

	/**
	 * Method for pad right string
	 * 
	 * @param text                   for pad
	 * @param numeberOfCharactersPad for string
	 * @return text pad right
	 */
	public static final String padRight(String text, int numeberOfCharactersPad) {
		return String.format("%-" + numeberOfCharactersPad + "s", text);
	}

	/**
	 * Method for pad left string
	 * 
	 * @param text                   for pad
	 * @param numeberOfCharactersPad for string
	 * @return text pad left
	 */
	public static final String padLeft(String text, int numeberOfCharactersPad) {
		return String.format("%" + numeberOfCharactersPad + "s", text);
	}
}
