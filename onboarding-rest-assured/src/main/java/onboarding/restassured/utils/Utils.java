package onboarding.restassured.utils;

import java.math.BigDecimal;

/**
 * Utils for core-api functions
 */
public class Utils {

    private Utils(){

    }

    /**
     * Get integer
     * @param number Object
     * @return Integer
     */
    public static Integer getInt(Object number) {
        if (number instanceof BigDecimal)
            return ((BigDecimal) number).intValue();
        else if (number instanceof Integer) {
            return (Integer) number;
        } else {
            return new BigDecimal((String) number).intValue();
        }
    }

    /**
     * Get double
     * @param number Object
     * @return Double
     */
    public static Double getDouble(Object number) {
        if (number instanceof BigDecimal)
            return ((BigDecimal) number).doubleValue();
        else if (number instanceof Double) {
            return (Double) number;
        } else {
            return new BigDecimal((String) number).doubleValue();
        }
    }

    /**
     * Get Long
     * @param number Object
     * @return Long
     */
    public static Long getLong(Object number) {
        if (number instanceof BigDecimal)
            return ((BigDecimal) number).longValue();
        else if (number instanceof Double) {
            return (Long) number;
        } else {
            return new BigDecimal((String) number).longValue();
        }
    }
}
