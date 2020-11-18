package com.rfJVUtils.beans.financial.core;

import java.io.Serializable;
import java.math.BigDecimal;

import com.rfJVUtils.constants.financial.EnumDiscountTypes;

/**
 * Discount. If amount is not null apply this for discount percentage
 * 
 * @author diego
 *
 */
public class Discount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2923648648409192663L;

	private BigDecimal percentage;

	private BigDecimal amount;

	private EnumDiscountTypes discountType;

	public Discount() {
		this.discountType = EnumDiscountTypes.TAX_BASE;
	}

	public BigDecimal getPercentage() {
		return percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public EnumDiscountTypes getDiscountType() {
		return discountType;
	}

	public void setDiscountType(EnumDiscountTypes discountType) {
		this.discountType = discountType;
	}

}
