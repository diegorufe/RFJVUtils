package com.rfJVUtils.financial;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.rfJVUtils.constants.financial.IRFUtilsFinancialConstants;
import com.rfJVUtils.exceptions.RFFinancialException;
import com.rfJVUtils.utils.financial.UtilsFinancial;

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
	 * {@link com.rfJVUtils.utils.financial.UtilsFinancial#calculateNPV(BigDecimal, BigDecimal[], BigDecimal)}
	 * 
	 * @throws RFFinancialException
	 */
	@Test
	public void calculateNPVTest() throws RFFinancialException {
		BigDecimal desireResult = new BigDecimal("-1.29");
		BigDecimal result = UtilsFinancial.calculateNPV(new BigDecimal("70"),
				new BigDecimal[] { new BigDecimal("15"), new BigDecimal("60") }, new BigDecimal("0.05"));
		assertTrue(result.setScale(2, IRFUtilsFinancialConstants.DEFAULT_ROUNDING_MODE).compareTo(desireResult) == 0);
	}

	/**
	 * Test for calcauteIRR
	 * {@link com.rfJVUtils.utils.financial.UtilsFinancial#calcalateIRR(BigDecimal, BigDecimal[])}
	 * 
	 * @throws RFFinancialException
	 */
	@Test
	public void calcauteIRR() throws RFFinancialException {
		BigDecimal desireResult = new BigDecimal("3.92");
		BigDecimal result = UtilsFinancial.calcalateIRR(new BigDecimal("70"),
				new BigDecimal[] { new BigDecimal("15"), new BigDecimal("60") });
		assertTrue(result.setScale(2, IRFUtilsFinancialConstants.DEFAULT_ROUNDING_MODE).compareTo(desireResult) == 0);

		desireResult = new BigDecimal("14.88");
		result = UtilsFinancial.calcalateIRR(new BigDecimal("1550.22"),
				new BigDecimal[] { new BigDecimal("1000.23"), new BigDecimal("200.21"), new BigDecimal("800.32") });
		assertTrue(result.setScale(2, IRFUtilsFinancialConstants.DEFAULT_ROUNDING_MODE).compareTo(desireResult) == 0);
	}

	/**
	 * Test for calculate compound interest
	 * {@link com.rfJVUtils.utils.financial.UtilsFinancial#calculateCompoundInterest(BigDecimal, BigDecimal, BigDecimal)}
	 * 
	 * @throws RFFinancialException
	 */
	@Test
	public void compoundInterestTest() throws RFFinancialException {
		BigDecimal desireResult = new BigDecimal("110.25");
		BigDecimal result = UtilsFinancial.calculateCompoundInterest(new BigDecimal("100"), new BigDecimal("0.05"),
				new BigDecimal("2"));
		assertTrue(result.setScale(2, IRFUtilsFinancialConstants.DEFAULT_ROUNDING_MODE).compareTo(desireResult) == 0);
	}

}
