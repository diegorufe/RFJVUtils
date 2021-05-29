package com.rfJVUtils.utils.commons;

import java.math.BigDecimal;

import org.slf4j.Logger;

/**
 * Class utilities BigDecimal
 *
 * <ul>
 * <li>{@link #numberOfDecimalPlaces(BigDecimal)}</li>
 * </ul>
 *
 * @author diego
 */
public final class UtilsBigDecimal {

    private static final Logger LOGGER = UtilsLog.getLogger(UtilsBigDecimal.class.getSimpleName());

    private UtilsBigDecimal() {

    }

    /**
     * Method for get number of places decimal in BigDecimal. If bigDecimal is null
     * return -1. If don't find place of decimal return -1
     *
     * @param bigDecimal is {@link BigDecimal} to get number of places decimal
     * @return number of places decimal
     */
    public static int numberOfDecimalPlaces(BigDecimal bigDecimal) {
        int numberOfplacesDecimal = -1;
        if (bigDecimal != null) {
            String bigDecimalString = bigDecimal.stripTrailingZeros().toPlainString();
            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace("bigDecimal param to string with zeros is " + bigDecimalString);
            }
            int index = bigDecimalString.indexOf(UtilsChar.DOT);
            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace("bigDecimal param index dot is " + index);
            }
            numberOfplacesDecimal = index < 0 ? -1 : bigDecimalString.length() - index - 1;
        } else if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("bigDecimal param is null");
        }
        return numberOfplacesDecimal;
    }
}
