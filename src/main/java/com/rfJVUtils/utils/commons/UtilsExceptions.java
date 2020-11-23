package com.rfJVUtils.utils.commons;

/**
 * Class utilities for exceptions
 * 
 * @author diego
 *
 */
public final class UtilsExceptions {

	private UtilsExceptions() {

	}

	/**
	 * Method for unwrap exception for get cause for this
	 * 
	 * @param <T>
	 * @param exception to unwrap
	 * @param type      to unwrap exception
	 * @return exception unwrap if found type else return exception passed in
	 *         prameter
	 */
	public static <T extends Throwable> Throwable unwrap(Throwable exception, Class<T> type) {
		while (type.isInstance(exception) && exception.getCause() != null) {
			exception = exception.getCause();
		}

		return exception;
	}

	/**
	 * Method to check exception is instance of other
	 * 
	 * @param <T>
	 * @param exception to check
	 * @param type      class to other exception to check
	 * @return True if exception is instance of other
	 */
	public static <T extends Throwable> boolean is(Throwable exception, Class<T> type) {
		for (; exception != null; exception = exception.getCause()) {
			if (type.isInstance(exception)) {
				return true;
			}
		}

		return false;
	}
}
