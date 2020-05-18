package com.rfUtilsFinancial.constants;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Default constants for library
 * 
 * @author diego
 *
 */
public interface IRFUtilsFinancialConstants {

	/**
	 * Default precisión for math context for calculate data
	 */
	public static final int DEFAULT_PRECISION_MATH_CONTEXT = 34;

	/**
	 * Default Rounding mode for calculate data
	 */
	public static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;

	/**
	 * Default scale for percents
	 */
	public static final int DEFAULT_SCALE_PERCENT = 2;

	/**
	 * Default math contexto for calculate data.
	 * {@link #DEFAULT_PRECISION_MATH_CONTEXT} {@link #DEFAULT_ROUNDING_MODE}
	 */
	public static final MathContext DEFAULT_MATH_CONTEXT = new MathContext(DEFAULT_PRECISION_MATH_CONTEXT,
			DEFAULT_ROUNDING_MODE);

	/**
	 * BigDecimal Hundred value
	 */
	public static final BigDecimal HUNDRED = new BigDecimal(String.valueOf("100"));

	/**
	 * Default scale IRR
	 */
	public static final int DEFAULT_SCALE_IRR = 2;

}
