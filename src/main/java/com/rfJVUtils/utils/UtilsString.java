package com.rfJVUtils.utils;

import java.util.Random;

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
 * <p>
 * Method for check size
 * <ul>
 * <li>{@link #isEmpty(String)}</li>
 * <li>{@link #isNotEmpty(String)}</li>
 * </ul>
 * 
 * <p>
 * Random
 * <ul>
 * <li>{@link #ramdomString(int)}</li>
 * </ul>
 * 
 * <p>
 * Replace
 * <ul>
 * <li>{@link #replaceAllChars(String, char, char)}</li>
 * </ul>
 * 
 * <p>
 * Contains
 * <ul>
 * <li>{@link #contaisChar(String, char)}</li>
 * </ul>
 * 
 * <p>
 * Constants
 * <ul>
 * <li>{@link #SPACE}</li>
 * <li>{@link #TWO_POINTS}</li>
 * <li>{@link #PERCENT}</li>
 * <li>{@link #QUOTE}</li>
 * <li>{@link #DOT}</li>
 * <li>{@link #COMA}</li>
 * <li>{@link #SPLIT_DOT}</li>
 * </ul>
 * 
 * @author diego
 *
 */
public final class UtilsString {

	/**
	 * Space value -> " "
	 */
	public static final String SPACE = " ";
	/**
	 * Two points -> ":"
	 */
	public static final String TWO_POINTS = ":";

	/**
	 * Percent -> "%"
	 */
	public static final String PERCENT = "%";

	/**
	 * Quote -> "'"
	 */
	public static final String QUOTE = "'";

	/**
	 * Dot -> "."
	 */
	public static final String DOT = ".";

	/**
	 * Coma -> ","
	 */
	public static final String COMA = ",";

	/**
	 * Split dot -> "\\."
	 */
	public static final String SPLIT_DOT = "\\" + DOT;

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

	/**
	 * Method to know value is emtpy
	 * 
	 * @param value to check
	 * @return true if null or empty false if not
	 */
	public static final boolean isEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}

	/**
	 * Method to know value is not emtpy
	 * 
	 * @param value to check
	 * @return true if value is not null and not empty, false if not
	 */
	public static final boolean isNotEmpty(String value) {
		return !UtilsString.isEmpty(value);
	}

	/**
	 * Method for generate ramdom string
	 * 
	 * @param targetStringLength to generate ramdon string
	 * @return ramdom string generated
	 */
	public static final String ramdomString(int targetStringLength) {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'

		Random random = new Random();
		StringBuilder builder = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			builder.append((char) randomLimitedInt);
		}
		return builder.toString();
	}

	/**
	 * Method for replace all chars in text
	 * 
	 * @param text        to replace
	 * @param charReplace char to replace
	 * @param newChar     to add
	 * @return text with char replace if this is not empty
	 */
	public static final String replaceAllChars(String text, char charReplace, char newChar) {
		if (UtilsString.isNotEmpty(text)) {
			text = text.replace(charReplace, newChar);
		}
		return text;
	}

	/**
	 * Method to check contains char
	 * 
	 * @param text        to check
	 * @param charToCheck to check
	 * @return true if char to check exists. If text is empty return false
	 */
	public static final boolean contaisChar(String text, char charToCheck) {
		boolean contain = false;
		if (UtilsString.isNotEmpty(text)) {
			contain = text.indexOf(charToCheck) != -1;
		}
		return contain;
	}
}
