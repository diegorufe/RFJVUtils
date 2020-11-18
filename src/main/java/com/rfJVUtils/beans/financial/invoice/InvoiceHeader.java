package com.rfJVUtils.beans.financial.invoice;

import java.io.Serializable;
import java.util.List;

import com.rfJVUtils.beans.financial.core.Discount;
import com.rfJVUtils.beans.financial.core.Tax;
import com.rfJVUtils.features.financial.IFeatureFinancialHasDiscounts;
import com.rfJVUtils.features.financial.IFeatureFinancialHasTaxes;

/**
 * Header for invoice
 * 
 * @author diego
 *
 */
public class InvoiceHeader implements Serializable, IFeatureFinancialHasTaxes, IFeatureFinancialHasDiscounts {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1320910189072017434L;

	private List<Tax> listTaxes;
	private List<Discount> listDiscounts;

	public InvoiceHeader() {

	}

	@Override
	public List<Tax> getListTaxes() {
		return this.listTaxes;
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
