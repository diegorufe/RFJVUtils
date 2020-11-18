package com.rfJVUtils.utils;

import org.slf4j.Logger;

import com.rfJVUtils.beans.financial.invoice.Invoice;

/**
 * Class utilities for invoice
 * 
 * <ul>
 * <li>{@link #calculateInvoice(Invoice)}</li>
 * <ul>
 * 
 * @author diego
 *
 */
public final class UtilsInvoice {

	private static final Logger LOGGER = UtilsLog.getLogger(UtilsInvoice.class.getSimpleName());

	private UtilsInvoice() {

	}

	/**
	 * Method for calculate invoice
	 * 
	 * @param invoice to calculate if not null
	 */
	public static void calculateInvoice(Invoice invoice) {
		if (invoice != null) {
			long time = System.currentTimeMillis();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Start calculate invoice");
			}

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(
						"End calculate invoice. Time for calculate invoce: " + (System.currentTimeMillis() - time));
			}
		}
	}
}
