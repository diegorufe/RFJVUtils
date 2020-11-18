package com.rfJVUtils.beans.financial.core;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Tax. If share not null use this for apply tax else calculate share with
 * percentage
 * 
 * @author diego
 *
 */
public class Tax implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2923648648409192663L;

	private BigDecimal percentage;

	private BigDecimal share;

	public Tax() {

	}

	public BigDecimal getPercentage() {
		return percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

	public BigDecimal getShare() {
		return share;
	}

	public void setShare(BigDecimal share) {
		this.share = share;
	}

}
