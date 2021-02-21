package com.rfJVUtils.utils.commons;

import java.text.Normalizer;
import java.util.Random;
import java.util.UUID;

/**
 * Class utilities for String
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
 * <li>{@link #isEmpty(String, boolean)}</li>
 * <li>{@link #isNotEmpty(String)}</li>
 * <li>{@link #isNotEmpty(String, boolean)}</li>
 * </ul>
 * 
 * <p>
 * Random
 * <ul>
 * <li>{@link #ramdomString(int)}</li>
 * <li>{@link #uniqueId()}</li>
 * </ul>
 * 
 * <p>
 * Replace
 * <ul>
 * <li>{@link #replaceAllChars(String, char, char)}</li>
 * <li>{@link #replaceAllString(String, String, String)}</li>
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
 * <li>{@link #BREAK_LINE}</li>
 * </ul>
 * 
 * <p>
 * Hex
 * <ul>
 * <li>{@link #byteArrayToHexString(byte[])}</li>
 * <li>{@link #hexStringToByteArray(String)}</li>
 * </ul>
 * 
 * <p>
 * Normalize
 * <ul>
 * <li>{@link #normalize(String)}</li>
 * </ul>
 * 
 * @author diego
 *
 */
public final class UtilsString {

	/**
	 * Format regex hex string
	 */
	private static final String FORMAT_HEX_STRING = "%02x";

	/**
	 * String to use replace regex normalize. This expression represents the UTF-8
	 * characters between U+0300 and U+036F
	 */
	private static final String REGEX_REPLACE_NORMALIZE = "[\\p{InCombiningDiacriticalMarks}]";

	/**
	 * Empty -> " "
	 */
	public static final String EMPTY = "";

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
	
	/**
	 * Break line
	 */
	public static final String BREAK_LINE = "\n";

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
	 * Method to know value is empty. Check value is not null and not empty with
	 * trim java generic method
	 * 
	 * @param value to check
	 * @return true if null or empty false if not
	 */
	public static final boolean isEmpty(String value) {
		return UtilsString.isEmpty(value, true);
	}

	/**
	 * Method to know value is empty. Check value is not null if pass true checkNull
	 * and not empty with trim java generic method
	 * 
	 * @param value     to check
	 * @param checkNull if is true check string is null in this case is empty if is
	 *                  null. If value is null and dont check null maybe throw null
	 *                  pointer exception
	 * @return true if null or empty false if not
	 */
	public static final boolean isEmpty(String value, boolean checkNull) {
		return (checkNull && value == null) || value.trim().isEmpty();
	}

	/**
	 * Method to know value is not empty. In this method check value is null
	 * 
	 * @param value to check
	 * @return true if value is not null and not empty, false if not
	 */
	public static final boolean isNotEmpty(String value) {
		return !UtilsString.isEmpty(value, true);
	}

	/**
	 * Method to know value is not empty
	 * 
	 * @param value     to check
	 * @param checkNull if is true check string is null in this case is empty if is
	 *                  null. If value is null and dont check null maybe throw null
	 *                  pointer exception
	 * @return true if value is not null and not empty, false if not
	 */
	public static final boolean isNotEmpty(String value, boolean checkNull) {
		return !UtilsString.isEmpty(value, checkNull);
	}

	/**
	 * Method for generate random string
	 * 
	 * @param targetStringLength to generate random string
	 * @return random string generated
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
	 * Method for replace all string in text
	 * 
	 * @param text      to replace
	 * @param replace   to replace
	 * @param newString to add
	 * @return text with String replace if this is not empty
	 */
	public static final String replaceAllString(String text, String replace, String newString) {
		if (UtilsString.isNotEmpty(text) && replace != null && newString != null) {
			text = text.replace(replace, newString);
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

	/**
	 * Method for generate unique id
	 * 
	 * @return unique id
	 */
	public static final String uniqueId() {
		return UUID.randomUUID().toString().concat(UUID.randomUUID().toString());
	}

	/**
	 * Method for convert byte array to hex string
	 * 
	 * @param byteArray to covert
	 * @return hex string from byte array
	 */
	public static String byteArrayToHexString(byte[] byteArray) {
		if (byteArray != null && byteArray.length > 0) {
			StringBuilder result = new StringBuilder();
			for (byte byteToConvert : byteArray) {
				result.append(String.format(FORMAT_HEX_STRING, byteToConvert));
			}
			return result.toString();
		}
		return null;
	}

	/**
	 * Method to conver hex string to byte array
	 * 
	 * @param hexString to convert
	 * @return byte array from hex string
	 */
	public static byte[] hexStringToByteArray(String hexString) {
		byte[] byteArrayForHexString = null;
		if (UtilsString.isNotEmpty(hexString)) {
			int len = hexString.length();
			byteArrayForHexString = new byte[len / 2];
			for (int i = 0; i < len; i += 2) {
				byteArrayForHexString[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
						+ Character.digit(hexString.charAt(i + 1), 16));
			}
		}
		return byteArrayForHexString;
	}

	/**
	 * Method for normalize string. Only normalize if text is not null else return
	 * null
	 * 
	 * @param text to normalize
	 * @return text normalized or null if text param is null
	 */
	public static final String normalize(String text) {
		String textNormalized = null;
		if (text != null) {
			textNormalized = Normalizer.normalize(text, Normalizer.Form.NFD);
			textNormalized = textNormalized.replaceAll(REGEX_REPLACE_NORMALIZE, EMPTY);
		}
		return textNormalized;
	}
}
