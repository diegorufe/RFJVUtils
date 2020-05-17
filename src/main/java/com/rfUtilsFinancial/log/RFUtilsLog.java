package com.rfUtilsFinancial.log;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

/**
 * Class for utilities for log
 * 
 * @author diego
 *
 */
public class RFUtilsLog {

	/**
	 * Method for get logger wiht tag
	 * 
	 * @param tag is tag for logger
	 * @return logger for tag
	 */
	public static Logger getLogger(String tag) {
		return LoggerFactory.getLogger(tag);
	}

	public static void sendDebugLogAsync(final Logger logger, final String log) {
		if (logger.isDebugEnabled()) {
			RFUtilsLog.sendLogAsync(Level.DEBUG, logger, log);
		}
	}

	public static void sendInfoLogAsync(final Logger logger, final String log) {
		if (logger.isInfoEnabled()) {
			RFUtilsLog.sendLogAsync(Level.INFO, logger, log);
		}
	}

	public static void sendErrorLogAsync(final Logger logger, final String log) {
		if (logger.isErrorEnabled()) {
			RFUtilsLog.sendLogAsync(Level.ERROR, logger, log);
		}
	}

	public static void sendTraceLogAsync(final Logger logger, final String log) {
		if (logger.isTraceEnabled()) {
			RFUtilsLog.sendLogAsync(Level.TRACE, logger, log);
		}
	}

	private static void sendLogAsync(final Level level, final Logger logger, final String log) {
		if (log != null) {
			CompletableFuture.runAsync(() -> {
				switch (level) {

				case DEBUG:
					if (logger.isDebugEnabled()) {
						logger.debug(log);
					}

					break;

				case ERROR:
					if (logger.isErrorEnabled()) {
						logger.error(log);
					}
					break;

				case INFO:
					if (logger.isInfoEnabled()) {
						logger.info(log);
					}
					break;

				case TRACE:
					if (logger.isTraceEnabled()) {
						logger.trace(log);
					}
					break;

				default:
					break;
				}
			});
		} else {
			RFUtilsLog.sendErrorLogAsync(logger, "Log message is empty or null");
		}
	}
}
