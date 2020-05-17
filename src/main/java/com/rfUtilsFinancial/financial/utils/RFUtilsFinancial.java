package com.rfUtilsFinancial.financial.utils;

import java.math.BigDecimal;
import java.math.MathContext;

import org.slf4j.Logger;

import com.rfUtilsFinancial.constants.EnumErrorCodes;
import com.rfUtilsFinancial.constants.IRFUtilsFinancialConstants;
import com.rfUtilsFinancial.exceptions.RFFinancialException;
import com.rfUtilsFinancial.log.RFUtilsLog;

/**
 * Class utilities for financial operations
 * 
 * <p>
 * - Methods Net Present Value (NPV)
 * <ul>
 * <li>{@link #calculateNPV(MathContext, BigDecimal, BigDecimal[], BigDecimal)}</li>
 * <li>{@link #calculateNPV(BigDecimal, BigDecimal[], BigDecimal)}</li>
 * </ul>
 * 
 * @author diego
 *
 */
public final class RFUtilsFinancial {

	private static final Logger LOGGER = RFUtilsLog.getLogger(RFUtilsFinancial.class.getSimpleName());

	private RFUtilsFinancial() {

	}

	/**
	 * Method to calculate value Net Present Value (NPV) If the return value is less
	 * than or equal to zero, the investment is inadvisable.
	 * 
	 * NPV = - initialOutlay + (netFlow1 / (1 + interests).pow(1)) + (nnetFlown / (1
	 * + interests).pow(n)) + .... <br>
	 * <br>
	 * This method not set scale for result
	 * 
	 * @param initialOutlay for inversion
	 * @param netFlows      for inversion
	 * @param interests     for inversion
	 * @return Net Present Value (NPV)
	 * @throws RFFinancialException
	 *                              <p>
	 *                              if any values is null:
	 *                              {@link com.rfUtilsFinancial.constants.EnumErrorCodes#NULL_VALUES}.
	 */
	public static BigDecimal calculateNPV(BigDecimal initialOutlay, BigDecimal[] netFlows, BigDecimal interests)
			throws RFFinancialException {
		return RFUtilsFinancial.calculateNPV(IRFUtilsFinancialConstants.DEFAULT_MATH_CONTEXT, initialOutlay, netFlows,
				interests);
	}

	/**
	 * Method to calculate value Net Present Value (NPV) If the return value is less
	 * than or equal to zero, the investment is inadvisable.
	 * 
	 * NPV = - initialOutlay + (netFlow1 / (1 + interests).pow(1)) + (nnetFlown / (1
	 * + interests).pow(n)) + .... <br>
	 * <br>
	 * This method not set scale for result
	 * 
	 * @param mathContext   math context for calculate operation
	 * @param initialOutlay for inversion
	 * @param netFlows      for inversion
	 * @param interests     for inversion
	 * @return Net Present Value (NPV)
	 * @throws RFFinancialException
	 *                              <p>
	 *                              if any values is null:
	 *                              {@link com.rfUtilsFinancial.constants.EnumErrorCodes#NULL_VALUES}.
	 */
	public static BigDecimal calculateNPV(MathContext mathContext, BigDecimal initialOutlay, BigDecimal[] netFlows,
			BigDecimal interests) throws RFFinancialException {
		BigDecimal sumNetFlows = BigDecimal.ZERO;

		// Throw error if any value is null
		if (mathContext == null || initialOutlay == null || netFlows == null) {
			throw new RFFinancialException(EnumErrorCodes.NULL_VALUES, "Any of the values is null");
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(
					"initialOutlay: " + initialOutlay.toPlainString() + ", interests: " + interests.toPlainString());
		}

		for (int i = 0; i < netFlows.length; i++) {

			// throw error if any netflow is null
			if (netFlows[i] == null) {
				throw new RFFinancialException(EnumErrorCodes.NULL_VALUES, "Any of the values is null");
			}

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("netflow " + i + ": " + netFlows[i].toPlainString());
			}

			sumNetFlows = sumNetFlows.add(netFlows[i].divide(BigDecimal.ONE.add(interests).pow(i + 1), mathContext));
		}

		// initialOutlay always is negate in operation
		return initialOutlay.negate().add(sumNetFlows);
	}
}
