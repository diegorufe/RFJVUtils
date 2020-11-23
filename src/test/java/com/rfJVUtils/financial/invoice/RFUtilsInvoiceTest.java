package com.rfJVUtils.financial.invoice;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.rfJVUtils.beans.financial.core.Discount;
import com.rfJVUtils.beans.financial.core.Tax;
import com.rfJVUtils.beans.financial.invoice.Invoice;
import com.rfJVUtils.beans.financial.invoice.InvoiceLine;
import com.rfJVUtils.constants.financial.IRFUtilsFinancialConstants;
import com.rfJVUtils.exceptions.RFInvoiceExpcetion;
import com.rfJVUtils.utils.financial.UtilsInvoice;

/**
 * 
 * @author diego
 *
 */
public class RFUtilsInvoiceTest {

	@Test
	public void calculateInvoice() throws RFInvoiceExpcetion {

		BigDecimal desireResult = new BigDecimal("3097.90");

		Invoice invoice = new Invoice();

		InvoiceLine invoiceLine = new InvoiceLine();
		Discount discount = new Discount();
		discount.setPercentage(new BigDecimal("0.05"));

		invoiceLine.setQuantity(new BigDecimal("2.5"));
		invoiceLine.setTaxBase(new BigDecimal("1100.00"));
		invoiceLine.addDiscount(discount);

		discount = new Discount();
		discount.setPercentage(new BigDecimal("0.02"));
		invoiceLine.addDiscount(discount);

		Tax tax = new Tax();
		tax.setPercentage(new BigDecimal("0.21"));

		invoiceLine.addTax(tax);

		invoice.addInvoiceLine(invoiceLine);

		UtilsInvoice.calculateInvoice(invoice, true);

		System.out.println("Invoice amountInvoiceWithoutTaxesAndDiscountHeader "
				+ invoice.getAmountInvoiceWithoutTaxesAndDiscountHeader().toPlainString());

		assertTrue(invoice.getAmountInvoiceWithoutTaxesAndDiscountHeader()
				.setScale(2, IRFUtilsFinancialConstants.DEFAULT_ROUNDING_MODE).compareTo(desireResult) == 0);

	}
}
