package com.rfUtilsFinancial.stock;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.rfUtilsFinancial.constants.IRFUtilsFinancialConstants;
import com.rfUtilsFinancial.exceptions.RFFinancialException;
import com.rfUtilsFinancial.stock.utils.RFUtilsStock;

/**
 * Tests cases for utiliest for stock
 * 
 * @author diego
 *
 */
public final class RFUtilsStockTests {

	/**
	 * Test for stockTurnoverIndex
	 * {@link com.rfUtilsFinancial.stock.utils.RFUtilsStock#stockTurnoverIndex(java.math.MathContext, java.math.BigDecimal, long, java.math.BigDecimal)}
	 * @throws RFFinancialException 
	 */
	@Test
	public void stockTurnoverIndexTest() throws RFFinancialException {
		BigDecimal desireResult = new BigDecimal("1190.32", IRFUtilsFinancialConstants.DEFAULT_MATH_CONTEXT);
		BigDecimal costPrice = new BigDecimal("1.23", IRFUtilsFinancialConstants.DEFAULT_MATH_CONTEXT);
		long numberOfSoldItems = 1500;
		BigDecimal averageStockValue = new BigDecimal("1.55", IRFUtilsFinancialConstants.DEFAULT_MATH_CONTEXT);
		assertTrue(
				desireResult.compareTo(RFUtilsStock.stockTurnoverIndex(costPrice, numberOfSoldItems, averageStockValue)
						.setScale(2, IRFUtilsFinancialConstants.DEFAULT_ROUNDING_MODE)) == 0);
	}

}
