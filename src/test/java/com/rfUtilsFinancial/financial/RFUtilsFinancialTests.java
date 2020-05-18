package com.rfUtilsFinancial.financial;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

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
	 * {@link com.rfUtilsFinancial.financial.utils.RFUtilsFinancial#calculateNPV(BigDecimal, BigDecimal[], BigDecimal)}
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

	/**
	 * Test for calcauteIRR
	 * {@link com.rfUtilsFinancial.financial.utils.RFUtilsFinancial#calcalateIRR(BigDecimal, BigDecimal[])}
	 * 
	 * @throws RFFinancialException
	 */
	@Test
	public void calcauteIRR() throws RFFinancialException {
		BigDecimal desireResult = new BigDecimal("3.92");
		BigDecimal result = RFUtilsFinancial.calcalateIRR(new BigDecimal("70"),
				new BigDecimal[] { new BigDecimal("15"), new BigDecimal("60") });
		assertTrue(result.setScale(2, IRFUtilsFinancialConstants.DEFAULT_ROUNDING_MODE).compareTo(desireResult) == 0);

		desireResult = new BigDecimal("14.88");
		result = RFUtilsFinancial.calcalateIRR(new BigDecimal("1550.22"),
				new BigDecimal[] { new BigDecimal("1000.23"), new BigDecimal("200.21"), new BigDecimal("800.32") });
		assertTrue(result.setScale(2, IRFUtilsFinancialConstants.DEFAULT_ROUNDING_MODE).compareTo(desireResult) == 0);
	}

}
