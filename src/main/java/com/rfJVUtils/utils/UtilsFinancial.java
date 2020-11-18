package com.rfJVUtils.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.slf4j.Logger;

import com.rfJVUtils.constants.core.EnumErrorCodes;
import com.rfJVUtils.constants.financial.IRFUtilsFinancialConstants;
import com.rfJVUtils.exceptions.RFFinancialException;

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
 * <p>
 * - Methods Rate of return on investment (IRR)
 * <ul>
 * <li>{@link #calcalateIRR(BigDecimal, BigDecimal[])}</li>
 * <li>{@link #calcalateIRR(MathContext, BigDecimal, BigDecimal[])}</li>
 * </ul>
 * 
 * <p>
 * - Compound interest
 * <ul>
 * <li>{@link #calculateCompoundInterest(BigDecimal, BigDecimal, BigDecimal)}</li>
 * <li>{@link #calculateCompoundInterest(MathContext, BigDecimal, BigDecimal, BigDecimal)}</li>
 * </ul>
 * 
 * @author diego
 *
 */
public final class UtilsFinancial {

	private static final Logger LOGGER = UtilsLog.getLogger(UtilsFinancial.class.getSimpleName());

	private UtilsFinancial() {

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
	 *                              {@link com.rfJVUtils.constants.core.EnumErrorCodes#NULL_VALUES}.
	 */
	public static final BigDecimal calculateNPV(BigDecimal initialOutlay, BigDecimal[] netFlows, BigDecimal interests)
			throws RFFinancialException {
		return UtilsFinancial.calculateNPV(IRFUtilsFinancialConstants.DEFAULT_MATH_CONTEXT, initialOutlay, netFlows,
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
	 *                              {@link com.rfJVUtils.constants.core.EnumErrorCodes#NULL_VALUES}.
	 */
	public static final BigDecimal calculateNPV(MathContext mathContext, BigDecimal initialOutlay,
			BigDecimal[] netFlows, BigDecimal interests) throws RFFinancialException {
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

		BigDecimal result = initialOutlay.negate().add(sumNetFlows);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("initialOutlay: " + initialOutlay.toPlainString());
			LOGGER.debug("sumNetFlows: " + sumNetFlows.toPlainString());
			LOGGER.debug("result: " + result.toPlainString());
		}

		// initialOutlay always is negate in operation
		return result;
	}

	/**
	 * Method for calculate rate of return on investment (IRR)
	 * 
	 * Scale default IRR in find low and higth values
	 * {@link com.rfJVUtils.constants.financial.IRFUtilsFinancialConstants#DEFAULT_SCALE_IRR}
	 * Rounding mode default IRR scale in find low and higth values
	 * {@link com.rfJVUtils.constants.financial.IRFUtilsFinancialConstants#DEFAULT_ROUNDING_MODE}
	 * 
	 * This method not set scale for result.
	 * 
	 * @param initialOutlay for inversion
	 * @param netFlows      for inversion
	 * @return (IRR) rate of return on investment
	 * @throws RFFinancialException
	 *                              <p>
	 *                              if any values is null:
	 *                              {@link com.rfJVUtils.constants.core.EnumErrorCodes#NULL_VALUES}.
	 *                              <p>
	 *                              If averageStockValue value is zero
	 *                              {@link com.rfJVUtils.constants.core.EnumErrorCodes#ARITEMICAL_EXCEPTION_DIVISION_BY_ZERO}
	 */
	public static final BigDecimal calcalateIRR(BigDecimal initialOutlay, BigDecimal[] netFlows)
			throws RFFinancialException {
		return UtilsFinancial.calcalateIRR(IRFUtilsFinancialConstants.DEFAULT_MATH_CONTEXT, initialOutlay, netFlows);
	}

	/**
	 * Method for calculate rate of return on investment (IRR)
	 * 
	 * This method not set scale for result.
	 * 
	 * @param mathContext   math context for calculate operation
	 * @param initialOutlay for inversion
	 * @param netFlows      for inversion
	 * @return (IRR) rate of return on investment
	 * @throws RFFinancialException
	 *                              <p>
	 *                              if any values is null:
	 *                              {@link com.rfJVUtils.constants.core.EnumErrorCodes#NULL_VALUES}.
	 *                              <p>
	 *                              If averageStockValue value is zero
	 *                              {@link com.rfJVUtils.constants.core.EnumErrorCodes#ARITEMICAL_EXCEPTION_DIVISION_BY_ZERO}
	 */
	public static final BigDecimal calcalateIRR(MathContext mathContext, BigDecimal initialOutlay,
			BigDecimal[] netFlows) throws RFFinancialException {
		BigDecimal result = BigDecimal.ZERO;

		// Throw error if any value is null
		if (mathContext == null || initialOutlay == null || netFlows == null) {
			throw new RFFinancialException(EnumErrorCodes.NULL_VALUES, "Any of the values is null");
		}

		// Throw error if initialOutlay is cero
		if (initialOutlay.compareTo(BigDecimal.ZERO) == 0) {
			throw new RFFinancialException(EnumErrorCodes.ARITEMICAL_EXCEPTION_DIVISION_BY_ZERO,
					"InitailOutlay musbe greater than 0");
		}

		// Calcualte low and higth value
		BigDecimal valueSumNetFlow = BigDecimal.ZERO;
		BigDecimal valueDivideNetFlowLowValue = BigDecimal.ZERO;
		BigDecimal valueSumNetFlowHightValue = BigDecimal.ZERO;

		for (int i = 0; i < netFlows.length; i++) {
			// Throw error if netflow is zero
			if (netFlows[i].compareTo(BigDecimal.ZERO) == 0) {
				throw new RFFinancialException(EnumErrorCodes.ARITEMICAL_EXCEPTION_DIVISION_BY_ZERO,
						"NetFlow for division is zero. Error aritemical division.");
			}
			valueSumNetFlow = valueSumNetFlow.add(netFlows[i]);
			valueDivideNetFlowLowValue = valueDivideNetFlowLowValue
					.add(netFlows[i].multiply(new BigDecimal(String.valueOf(i + 1)), mathContext));
			valueSumNetFlowHightValue = valueSumNetFlowHightValue
					.add(netFlows[i].divide(new BigDecimal(String.valueOf(i + 1)), mathContext));
		}

		BigDecimal valueSumDivideInitialOulay = valueSumNetFlow.divide(initialOutlay, mathContext);
		BigDecimal valuePowNetFlowLowValue = valueSumNetFlow.divide(valueDivideNetFlowLowValue, mathContext);

		BigDecimal lowValue = new BigDecimal(String
				.valueOf(Math.pow(valueSumDivideInitialOulay.doubleValue(), valuePowNetFlowLowValue.doubleValue())))
						.subtract(BigDecimal.ONE, mathContext);

		BigDecimal valuePowNetFlowHightValue = valueSumNetFlowHightValue.divide(valueSumNetFlow, mathContext);
		BigDecimal higthValue = new BigDecimal(String
				.valueOf(Math.pow(valueSumDivideInitialOulay.doubleValue(), valuePowNetFlowHightValue.doubleValue())))
						.subtract(BigDecimal.ONE, mathContext);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("lowValue: " + lowValue.toPlainString());
			LOGGER.debug("higthValue: " + higthValue.toPlainString());
		}

		lowValue = lowValue.setScale(IRFUtilsFinancialConstants.DEFAULT_SCALE_IRR, RoundingMode.DOWN);
		higthValue = higthValue.setScale(IRFUtilsFinancialConstants.DEFAULT_SCALE_IRR,
				IRFUtilsFinancialConstants.DEFAULT_ROUNDING_MODE);

		// Sarch value van between low and higth closest to zero
		// int numberOfPlacesDecimal = UtilsBigDecimal.numberOfDecimalPlaces(lowValue);
//		BigDecimal valueIncrement = new BigDecimal(
//				"0." + UtilsString.padRight("0", numberOfPlacesDecimal).replace(' ', '0') + "1");
		BigDecimal valueIncrement = BigDecimal.ONE;
		BigDecimal valueToCheck = lowValue.multiply(IRFUtilsFinancialConstants.HUNDRED);
		BigDecimal valueClotestToZeroPositiveNPV = BigDecimal.ZERO;
		BigDecimal valueClotestToZeroNegativeNPV = BigDecimal.ZERO;
		BigDecimal valueClotestToZeroPositiveIRR = BigDecimal.ZERO;
		BigDecimal valueNPV = BigDecimal.ZERO;
		BigDecimal higthValuePercent = higthValue.multiply(IRFUtilsFinancialConstants.HUNDRED);

		// Check closest to zero positive and negative NPV
		while (valueToCheck.compareTo(higthValuePercent) <= 0) {
			valueNPV = UtilsFinancial.calculateNPV(mathContext, initialOutlay, netFlows,
					valueToCheck.divide(IRFUtilsFinancialConstants.HUNDRED, mathContext));

			valueNPV = valueNPV.setScale(IRFUtilsFinancialConstants.DEFAULT_SCALE_IRR,
					IRFUtilsFinancialConstants.DEFAULT_ROUNDING_MODE);

			if (valueNPV.compareTo(BigDecimal.ZERO) >= 0) {
				valueClotestToZeroPositiveNPV = valueNPV;
				valueClotestToZeroPositiveIRR = valueToCheck;
			} else {
				valueClotestToZeroNegativeNPV = valueNPV;
				break;
			}

			if (valueToCheck.compareTo(higthValuePercent) < 0
					&& valueToCheck.add(valueIncrement).compareTo(higthValuePercent) > 0) {
				valueToCheck = higthValuePercent;
			}

			valueToCheck = valueToCheck.add(valueIncrement);
		}

		// AxisCorssing closest to zero NPV
		BigDecimal axisCrossing = valueClotestToZeroPositiveNPV
				.divide(valueClotestToZeroPositiveNPV.add(valueClotestToZeroNegativeNPV.abs()), mathContext);

		result = valueClotestToZeroPositiveIRR.add(axisCrossing);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("valueClotestToZeroPositiveIRR: " + valueClotestToZeroPositiveIRR.toPlainString());
			LOGGER.debug("axisCrossing: " + axisCrossing.toPlainString());
			LOGGER.debug("Result: " + result.toPlainString());
		}

		return result;
	}

	/**
	 * Method for calculate compound interest
	 * 
	 * compound interest=initialOutlay+((1 + interest).pow(unitTime))
	 * 
	 * This method not set scale for result.
	 * 
	 * @param initialOutlay for operation
	 * @param interests     for operation
	 * @param unitTime      for operation
	 * @return compound interest
	 * @throws RFFinancialException
	 *                              <p>
	 *                              if any values is null:
	 *                              {@link com.rfJVUtils.constants.core.EnumErrorCodes#NULL_VALUES}.
	 */
	public static final BigDecimal calculateCompoundInterest(BigDecimal initialOutlay, BigDecimal interests, BigDecimal unitTime)
			throws RFFinancialException {
		return UtilsFinancial.calculateCompoundInterest(IRFUtilsFinancialConstants.DEFAULT_MATH_CONTEXT, initialOutlay,
				interests, unitTime);
	}

	/**
	 * Method for calculate compound interest
	 * 
	 * compound interest=initialOutlay+((1 + interest).pow(unitTime))
	 * 
	 * This method not set scale for result.
	 * 
	 * @param mathContext   math context for calculate operation
	 * @param initialOutlay for operation
	 * @param interests     for operation
	 * @param unitTime      for operation
	 * @return compound interest
	 * @throws RFFinancialException
	 *                              <p>
	 *                              if any values is null:
	 *                              {@link com.rfJVUtils.constants.core.EnumErrorCodes#NULL_VALUES}.
	 */
	public static final BigDecimal calculateCompoundInterest(MathContext mathContext, BigDecimal initialOutlay,
			BigDecimal interests, BigDecimal unitTime) throws RFFinancialException {
		BigDecimal result = BigDecimal.ZERO;

		// Throw error if any value is null
		if (mathContext == null || initialOutlay == null || unitTime == null || interests == null) {
			throw new RFFinancialException(EnumErrorCodes.NULL_VALUES, "Any of the values is null");
		}

		BigDecimal interestOperation = BigDecimal.ONE.add(interests);
		interestOperation = new BigDecimal(
				String.valueOf(Math.pow(interestOperation.doubleValue(), unitTime.doubleValue())));

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Interest operation: " + interestOperation.toPlainString());
		}

		result = initialOutlay.multiply(interestOperation, mathContext);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Result: " + result.toPlainString());
		}

		return result;
	}
}
