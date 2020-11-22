package com.rfJVUtils.beans.financial.invoice;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rfJVUtils.utils.UtilsCollection;

/**
 * Class for store data invoce for calculate this
 * 
 * <ul>
 * <li>{@link #addInvoiceLine(InvoiceLine)}</li>
 * <li>{@link #clearInvoice()}</li>
 * </ul>
 * 
 * @author diego
 *
 */
public class Invoice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2736258219852334166L;

	private InvoiceHeader invoiceHeader;
	private List<InvoiceLine> listInvoiceLines;
	private BigDecimal amountInvoiceWithoutTaxesAndDiscountHeader;
	private Date lastDateExecuteCalculateInvoice;

	public Invoice() {

	}

	/**
	 * Method for add invoice line
	 * 
	 * @param invoiceLine
	 */
	public void addInvoiceLine(InvoiceLine invoiceLine) {
		if (invoiceLine != null) {
			if (UtilsCollection.isEmpty(this.listInvoiceLines)) {
				this.listInvoiceLines = new ArrayList<InvoiceLine>();
			}
			this.listInvoiceLines.add(invoiceLine);
		}
	}

	public InvoiceHeader getInvoiceHeader() {
		return invoiceHeader;
	}

	public void setInvoiceHeader(InvoiceHeader invoiceHeader) {
		this.invoiceHeader = invoiceHeader;
	}

	public List<InvoiceLine> getListInvoiceLines() {
		return listInvoiceLines;
	}

	public void setListInvoiceLines(List<InvoiceLine> listInvoiceLines) {
		this.listInvoiceLines = listInvoiceLines;
	}

	/**
	 * Method for clear invoice
	 */
	public void clearInvoice() {
		if (UtilsCollection.isNotEmpty(this.listInvoiceLines)) {
			this.listInvoiceLines.clear();
		}
		this.invoiceHeader = null;
		this.lastDateExecuteCalculateInvoice = null;
		this.amountInvoiceWithoutTaxesAndDiscountHeader = null;
	}

	public Date getLastDateExecuteCalculateInvoice() {
		return lastDateExecuteCalculateInvoice;
	}

	public void setLastDateExecuteCalculateInvoice(Date lastDateExecuteCalculateInvoice) {
		this.lastDateExecuteCalculateInvoice = lastDateExecuteCalculateInvoice;
	}

	public BigDecimal getAmountInvoiceWithoutTaxesAndDiscountHeader() {
		return amountInvoiceWithoutTaxesAndDiscountHeader;
	}

	public void setAmountInvoiceWithoutTaxesAndDiscountHeader(BigDecimal amountInvoiceWithoutTaxesAndDiscountHeader) {
		this.amountInvoiceWithoutTaxesAndDiscountHeader = amountInvoiceWithoutTaxesAndDiscountHeader;
	}

}
