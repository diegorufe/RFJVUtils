package com.rfJVUtils.beans.financial.invoice;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.rfJVUtils.beans.financial.core.Discount;
import com.rfJVUtils.beans.financial.core.Tax;
import com.rfJVUtils.features.financial.IFeatureFinancialHasDiscounts;
import com.rfJVUtils.features.financial.IFeatureFinancialHasTaxes;

/**
 * Line for invoice
 * 
 * @author diego
 *
 */
public class InvoiceLine implements Serializable, IFeatureFinancialHasTaxes, IFeatureFinancialHasDiscounts {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4871080196370799637L;

	private BigDecimal quantity;
	private BigDecimal taxBase;
	private List<Tax> listTaxes;
	private List<Discount> listDiscounts;

	public InvoiceLine() {
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTaxBase() {
		return taxBase;
	}

	public void setTaxBase(BigDecimal taxBase) {
		this.taxBase = taxBase;
	}

	@Override
	public List<Tax> getListTaxes() {
		return listTaxes;
	}

	@Override
	public void setListTaxes(List<Tax> listTaxes) {
		this.listTaxes = listTaxes;
	}

	@Override
	public List<Discount> getListDiscounts() {
		return this.listDiscounts;
	}

	@Override
	public void setListDiscounts(List<Discount> listDiscounts) {
		this.listDiscounts = listDiscounts;
	}

}
