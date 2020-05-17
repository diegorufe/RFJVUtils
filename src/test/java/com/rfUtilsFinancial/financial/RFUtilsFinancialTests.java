package com.rfUtilsFinancial.financial;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.Test;

import com.rfUtilsFinancial.constants.IRFUtilsFinancialConstants;
import com.rfUtilsFinancial.exceptions.RFFinancialException;
import com.rfUtilsFinancial.financial.utils.RFUtilsFinancial;

/**
 * Tests cases for utilities for financial
 * 
 * 
 * @author diego
 *
 */
public final class RFUtilsFinancialTests {

	/**
	 * Test for calculateNPV
	 * {@link com.rfUtilsFinancial.financial.utils.RFUtilsFinancial#calculateNPV(BigDecimal, BigDecimal[], BigDecimal, int, RoundingMode)}
	 * 
	 * @throws RFFinancialException
	 */
	@Test
	public void calculateNPVTest() throws RFFinancialException {
		BigDecimal desireResult = new BigDecimal("-1.29");
		BigDecimal result = RFUtilsFinancial.calculateNPV(new BigDecimal("70"),
				new BigDecimal[] { new BigDecimal("15"), new BigDecimal("60") }, new BigDecimal("0.05"));
		assertTrue(result.setScale(2, IRFUtilsFinancialConstants.DEFAULT_ROUNDING_MODE).compareTo(desireResult) == 0);
	}

}
