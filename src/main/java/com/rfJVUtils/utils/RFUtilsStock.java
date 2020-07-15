package com.rfJVUtils.utils;

import java.math.BigDecimal;
import java.math.MathContext;

import org.slf4j.Logger;

import com.rfJVUtils.constants.EnumErrorCodes;
import com.rfJVUtils.constants.IRFUtilsFinancialConstants;
import com.rfJVUtils.exceptions.RFFinancialException;

/**
 * Class utilities for stock
 * 
 * <p>
 * - Methods stock turnover index
 * <ul>
 * <li>{@link #stockTurnoverIndex(BigDecimal, long, BigDecimal)}</li>
 * <li>{@link #stockTurnoverIndex(MathContext, BigDecimal, long, BigDecimal)}</li>
 * </ul>
 * 
 * @author diego
 *
 */
public final class RFUtilsStock {

	private static final Logger LOGGER = RFUtilsLog.getLogger(RFUtilsStock.class.getSimpleName());

	private RFUtilsStock() {

	}

	/**
	 * Method for calculate stock turnover index. Formula to calculate is: STI =
	 * (costPrice * numberOfSoldItems) / averageStockValue
	 * 
	 * In this method assume math context for operations default
	 * {@link com.rfJVUtils.constants.IRFUtilsFinancialConstants#DEFAULT_MATH_CONTEXT}
	 * 
	 * This method not set scale for result
	 * 
	 * @param costPrice         cost price items
	 * @param numberOfSoldItems number of sold items
	 * @param averageStockValue for items
	 * @return stock turnover index
	 * @throws RFFinancialException
	 *                              <p>
	 *                              if any values is null:
	 *                              {@link com.rfJVUtils.constants.EnumErrorCodes#NULL_VALUES}.
	 *                              <p>
	 *                              If averageStockValue value is zero
	 *                              {@link com.rfJVUtils.constants.EnumErrorCodes#ARITEMICAL_EXCEPTION_DIVISION_BY_ZERO}
	 */
	public static final BigDecimal stockTurnoverIndex(BigDecimal costPrice, long numberOfSoldItems,
			BigDecimal averageStockValue) throws RFFinancialException {
		return RFUtilsStock.stockTurnoverIndex(IRFUtilsFinancialConstants.DEFAULT_MATH_CONTEXT, costPrice,
				numberOfSoldItems, averageStockValue);
	}

	/**
	 * Method for calculate stock turnover index. Formula to calculate is: STI =
	 * (costPrice * numberOfSoldItems) / averageStockValue
	 * 
	 * This method not set scale for result
	 * 
	 * @param mathContext       math context for calculate operation
	 * @param costPrice         cost price items
	 * @param numberOfSoldItems number of sold items
	 * @param averageStockValue for items
	 * @return stock turnover index
	 * @throws RFFinancialException
	 *                              <p>
	 *                              if any values is null:
	 *                              {@link com.rfJVUtils.constants.EnumErrorCodes#NULL_VALUES}.
	 *                              <p>
	 *                              If averageStockValue value is zero
	 *                               {@link com.rfJVUtils.constants.EnumErrorCodes#ARITEMICAL_EXCEPTION_DIVISION_BY_ZERO}
	 * 
	 */
	public static final BigDecimal stockTurnoverIndex(MathContext mathContext, BigDecimal costPrice,
			long numberOfSoldItems, BigDecimal averageStockValue) throws RFFinancialException {

		// Throw error if any value is null
		if (mathContext == null || costPrice == null || averageStockValue == null) {
			throw new RFFinancialException(EnumErrorCodes.NULL_VALUES, "Any of the values is null");
		}

		// Throw error if averageStockValue is zero
		if (averageStockValue.compareTo(BigDecimal.ZERO) == 0) {
			throw new RFFinancialException(EnumErrorCodes.ARITEMICAL_EXCEPTION_DIVISION_BY_ZERO,
					"AverageStockValue for division is zero. Error aritemical division.");
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Cost price: " + costPrice.toPlainString() + ", numberOfSoldItems: " + numberOfSoldItems
					+ ", average stock value: " + averageStockValue);
		}

		BigDecimal result = BigDecimal.ZERO;
		// costprice * numberOfSoldItems
		result = costPrice.multiply(new BigDecimal(String.valueOf(numberOfSoldItems), mathContext), mathContext);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(" costprice * numberOfSoldItems = " + result.toPlainString());
		}

		// result / averageStockValue
		result = result.divide(averageStockValue, mathContext);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(" (costprice * numberOfSoldItems) / averageStockValue = " + result.toPlainString());
		}
		return result;
	}
}
