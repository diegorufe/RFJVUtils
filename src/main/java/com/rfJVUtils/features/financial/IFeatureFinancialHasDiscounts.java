package com.rfJVUtils.features.financial;

import java.util.ArrayList;
import java.util.List;

import com.rfJVUtils.beans.financial.core.Discount;
import com.rfJVUtils.utils.UtilsCollection;

/**
 * Interface feature indicate has discounts
 * 
 * <ul>
 * <li>{@link #getListDiscounts())}</li>
 * <li>{@link #setListDiscounts(List)}</li>
 * <li>{@link #addDiscount(Discount)}</li>
 * </ul>
 * 
 * @author diego
 *
 */
public interface IFeatureFinancialHasDiscounts {

	public List<Discount> getListDiscounts();

	public void setListDiscounts(List<Discount> listDiscounts);

	/**
	 * Method for add discount
	 * 
	 * @param discount to add
	 */
	public default void addDiscount(Discount discount) {
		if (discount != null) {
			if (UtilsCollection.isEmpty(this.getListDiscounts())) {
				this.setListDiscounts(new ArrayList<Discount>());
			}
			if (this.getListDiscounts() != null) {
				this.getListDiscounts().add(discount);
			}
		}
	}
}
