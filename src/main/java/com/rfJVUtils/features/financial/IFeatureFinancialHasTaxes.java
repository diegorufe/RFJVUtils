package com.rfJVUtils.features.financial;

import java.util.ArrayList;
import java.util.List;

import com.rfJVUtils.beans.financial.core.Tax;
import com.rfJVUtils.utils.commons.UtilsCollection;

/**
 * Interface feature indicate has taxes
 * 
 * <ul>
 * <li>{@link #getListTaxes()}</li>
 * <li>{@link #setListTaxes(List)}</li>
 * <li>{@link #addTax(Tax)}</li>
 * </ul>
 * 
 * @author diego
 *
 */
public interface IFeatureFinancialHasTaxes {

	public List<Tax> getListTaxes();

	public void setListTaxes(List<Tax> listTaxes);

	/**
	 * Method for add tax
	 * 
	 * @param tax to add
	 */
	public default void addTax(Tax tax) {
		if (tax != null) {
			if (UtilsCollection.isEmpty(this.getListTaxes())) {
				this.setListTaxes(new ArrayList<Tax>());
			}
			if (this.getListTaxes() != null) {
				this.getListTaxes().add(tax);
			}
		}
	}
}
