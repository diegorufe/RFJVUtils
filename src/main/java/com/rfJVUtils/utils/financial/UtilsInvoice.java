package com.rfJVUtils.utils.financial;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;

import com.rfJVUtils.beans.financial.core.Discount;
import com.rfJVUtils.beans.financial.core.Tax;
import com.rfJVUtils.beans.financial.invoice.Invoice;
import com.rfJVUtils.beans.financial.invoice.InvoiceLine;
import com.rfJVUtils.constants.financial.EnumErrorCodesInvoice;
import com.rfJVUtils.constants.financial.IRFUtilsFinancialConstants;
import com.rfJVUtils.exceptions.RFInvoiceExpcetion;
import com.rfJVUtils.utils.commons.UtilsCollection;
import com.rfJVUtils.utils.commons.UtilsLog;

/**
 * Class utilities for invoice
 * 
 * <ul>
 * <li>{@link #calculateInvoice(Invoice)}</li>
 * <li>{@link #isValidDiscount(Discount)}</li>
 * <li>{@link #isValidTax(Tax)}</li>
 * <li>{@link #isValidInvoiceLine(InvoiceLine)}</li>
 * <li>{@link #applyDiscountAmount(BigDecimal, Discount)}</li>
 * <li>{@link #applyTaxAmount(BigDecimal, Tax)}</li>
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
	 * Method to know invoice line is valid
	 * 
	 * @param invoiceLine to check is valid
	 * @return true if not null and has quantity and has tax base
	 */
	public static final boolean isValidInvoiceLine(InvoiceLine invoiceLine) {
		return invoiceLine != null && invoiceLine.getQuantity() != null && invoiceLine.getTaxBase() != null;
	}

	/**
	 * Method to know discount is valid
	 * 
	 * @param discount to check is valid
	 * @return true if not null and has amount or percentage
	 */
	public static final boolean isValidDiscount(Discount discount) {
		return discount != null && (discount.getAmount() != null || discount.getPercentage() != null);
	}

	/**
	 * Method to know vtax is valid
	 * 
	 * @param discount to check is valid
	 * @return true if not null and has share or percentage
	 */
	public static final boolean isValidTax(Tax tax) {
		return tax != null && (tax.getPercentage() != null || tax.getShare() != null);
	}

	/**
	 * Mhetod for apply discount amount
	 * 
	 * @param amount   to apply discount
	 * @param discount to apply
	 * @return amount with apply discount
	 */
	public static final BigDecimal applyDiscountAmount(BigDecimal amount, Discount discount) {
		amount = amount == null ? BigDecimal.ZERO : amount;

		if (isValidDiscount(discount)) {

			if (discount.getAmount() != null) {
				amount = amount.subtract(discount.getAmount());
			} else {
				amount = amount.subtract(
						amount.multiply(discount.getPercentage(), IRFUtilsFinancialConstants.DEFAULT_MATH_CONTEXT));
			}

		}

		return amount;
	}

	/**
	 * Mhetod for apply tax amount
	 * 
	 * @param amount to apply tax
	 * @param tax    to apply
	 * @return amount with apply tax
	 */
	public static final BigDecimal applyTaxAmount(BigDecimal amount, Tax tax) {
		amount = amount == null ? BigDecimal.ZERO : amount;

		if (isValidTax(tax)) {

			if (tax.getShare() != null) {
				amount = amount.add(tax.getShare());
			} else {
				amount = amount
						.add(amount.multiply(tax.getPercentage(), IRFUtilsFinancialConstants.DEFAULT_MATH_CONTEXT));
			}

		}

		return amount;
	}

	/**
	 * Method for calculate invoice line
	 * 
	 * @param invoiceLine to calculate
	 * @return alwais return value not null. If line is not valid return ZERO
	 */
	private static final BigDecimal calculateInvoiceLine(InvoiceLine invoiceLine) {
		BigDecimal amount = BigDecimal.ZERO;

		if (isValidInvoiceLine(invoiceLine)) {
			BigDecimal taxBase = invoiceLine.getTaxBase();

			// amount = quanity x taxBase (unitPrice)
			BigDecimal amountWithoutTaxes = invoiceLine.getQuantity()
					.multiply(taxBase, IRFUtilsFinancialConstants.DEFAULT_MATH_CONTEXT)
					.setScale(IRFUtilsFinancialConstants.DEFAULT_SCALE_TOTAL_AMOUNT,
							IRFUtilsFinancialConstants.DEFAULT_ROUNDING_MODE);

			// Discounts apply amount
			if (UtilsCollection.isNotEmpty(invoiceLine.getListDiscounts())) {
				for (Discount discount : invoiceLine.getListDiscounts()) {
					amountWithoutTaxes = applyDiscountAmount(amountWithoutTaxes, discount);
				}
			}

			BigDecimal amountWithTaxes = amountWithoutTaxes;

			// Taxes apply amount
			if (UtilsCollection.isNotEmpty(invoiceLine.getListTaxes())) {
				for (Tax tax : invoiceLine.getListTaxes()) {
					amountWithTaxes = applyTaxAmount(amountWithTaxes, tax);
				}
			}

			amount = amountWithTaxes;

		}

		return amount;
	}

	/**
	 * Method for calculate invoice
	 * 
	 * @param invoice                            to calculate if not null
	 * @param throwErrorIfLineOrHeaderIsNotValid throw error if line or header is
	 *                                           not valid
	 * @throws RFInvoiceExpcetion
	 */
	public static void calculateInvoice(Invoice invoice, boolean throwErrorIfLineOrHeaderIsNotValid)
			throws RFInvoiceExpcetion {
		if (invoice != null) {

			long time = System.currentTimeMillis();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Start calculate invoice");
			}

			invoice.setLastDateExecuteCalculateInvoice(new Date());

			BigDecimal amountInvoiceWithoutTaxesAndDiscount = BigDecimal.ZERO;

			// Get invoice lines
			List<InvoiceLine> listInvoiceLines = invoice.getListInvoiceLines();

			if (UtilsCollection.isNotEmpty(listInvoiceLines)) {
				long lineNo = 0;

				// Calculate invoice line
				for (InvoiceLine invoiceLine : listInvoiceLines) {
					if (isValidInvoiceLine(invoiceLine)) {
						amountInvoiceWithoutTaxesAndDiscount = amountInvoiceWithoutTaxesAndDiscount
								.add(calculateInvoiceLine(invoiceLine));

					} else if (throwErrorIfLineOrHeaderIsNotValid) {
						throw new RFInvoiceExpcetion(EnumErrorCodesInvoice.LINE_INVOICE_IS_NOT_VALID.getCode(),
								"Line " + lineNo + " is not valid");
					}

					lineNo++;
				}
			}

			invoice.setAmountInvoiceWithoutTaxesAndDiscountHeader(amountInvoiceWithoutTaxesAndDiscount);

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(
						"End calculate invoice. Time for calculate invoce: " + (System.currentTimeMillis() - time));
			}
		}
	}
}
