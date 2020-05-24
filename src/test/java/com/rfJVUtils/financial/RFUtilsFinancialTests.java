package com.rfJVUtils.financial;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.rfJVUtils.constants.IRFUtilsFinancialConstants;
import com.rfJVUtils.exceptions.RFFinancialException;
import com.rfJVUtils.financial.utils.RFUtilsFinancial;

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
	 * {@link com.rfJVUtils.financial.utils.RFUtilsFinancial#calculateNPV(BigDecimal, BigDecimal[], BigDecimal)}
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
	 * {@link com.rfJVUtils.financial.utils.RFUtilsFinancial#calcalateIRR(BigDecimal, BigDecimal[])}
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

	/**
	 * Test for calculate compound interest
	 * {@link com.rfJVUtils.financial.utils.RFUtilsFinancial#calculateCompoundInterest(BigDecimal, BigDecimal, BigDecimal)}
	 * 
	 * @throws RFFinancialException
	 */
	@Test
	public void compoundInterestTest() throws RFFinancialException {
		BigDecimal desireResult = new BigDecimal("110.25");
		BigDecimal result = RFUtilsFinancial.calculateCompoundInterest(new BigDecimal("100"), new BigDecimal("0.05"),
				new BigDecimal("2"));
		assertTrue(result.setScale(2, IRFUtilsFinancialConstants.DEFAULT_ROUNDING_MODE).compareTo(desireResult) == 0);
	}

}
