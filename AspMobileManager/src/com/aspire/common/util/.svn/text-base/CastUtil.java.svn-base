/**
 * @(#) CastUtil.java Created on 2012-7-12
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <code>CastUtil</code>
 *
 * @author linjunsui
 * @version 1.0
 */
public class CastUtil {
    
    /**
     * Logger
     */
    protected static Logger logger = LoggerFactory.getLogger(CastUtil.class);
    
    /**
     * to String
     * @param value Value to change
     * @return String value
     */
    public static String toString(Object value) {
        return toString(value, null);
    }
    
    /**
     * to String
     * @param value Value to change
     * @param defaultValue Default value
     * @return String value
     */
    public static String toString(Object value, String defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        return value.toString();
    }

    /**
     * to Integer
     * @param value Value to change
     * @return Integer value
     */
    public static Integer toInteger(Object value) {
        return toInteger(value, null);
    }
    
    /**
     * to Integer
     * @param value Value to change
     * @param defaultValue Default value
     * @return Integer value
     */
    public static Integer toInteger(Object value, Integer defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        
        if (value instanceof Integer) {
            return (Integer) value;
        }
        
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        
        if (value instanceof CharSequence) {
            try {
                return Integer.valueOf(value.toString());
            } catch (NumberFormatException e2) {
                logger.error("Cannot parse Integer value for " + value);
                return defaultValue;
            }
        } else {
            logger.error("Cannot cast value to a Integer: " + value);
            return defaultValue;
        }
    }
    
    /**
     * to Short
     * @param value Value to change
     * @return Short value
     */
    public static Short toShort(Object value) {
         return toShort(value, null);
    }
    
    /**
     * to Short
     * @param value Value to change
     * @param defaultValue Default value
     * @return Short value
     */
    public static Short toShort(Object value, Short defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        
        if (value instanceof Short) {
            return (Short) value;
        }
        
        if (value instanceof Number) {
            return ((Number) value).shortValue();
        }
        
        if (value instanceof CharSequence) {
            try {
                return Short.valueOf(value.toString());
            } catch (NumberFormatException e) {
                logger.error("Cannot parse Short value for " + value);
                return defaultValue;
            }
        } else {
            logger.error("Cannot cast value to a Short: " + value);
            return defaultValue;
        }
    }
    
    /**
     * to Long
     * @param value Value to change
     * @return Long value
     */
    public static Long toLong(Object value) {
         return toLong(value, null);
    }
    
    /**
     * to Long
     * @param value Value to change
     * @param defaultValue Default value
     * @return Long value
     */
    public static Long toLong(Object value, Long defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        
        if (value instanceof Long) {
            return (Long) value;
        }
        
        if (value instanceof Number) {
            return ((Number) value).longValue();
        }
        
        if (value instanceof CharSequence) {
            try {
                return Long.valueOf(value.toString());
            } catch (NumberFormatException e) {
                logger.error("Cannot parse Long value for " + value);
                return defaultValue;
            }
        } else {
            logger.error("Cannot cast value to a Long: " + value);
            return defaultValue;
        }
    }
    
    /**
     * to Byte
     * @param value Value to change
     * @return Byte value
     */
    public static Byte toByte(Object value) {
        return toByte(value, null);
    }
    
    /**
     * to Byte
     * @param value Value to change
     * @param defaultValue Default value
     * @return Byte value
     */
    public static Byte toByte(Object value, Byte defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        
        if (value instanceof Byte) {
            return (Byte) value;
        }
        
        if (value instanceof Number) {
            return ((Number) value).byteValue();
        }
        
        if (value instanceof CharSequence) {
            try {
                return Byte.valueOf(value.toString());
            } catch (NumberFormatException e) {
                logger.error("Cannot parse Byte value for " + value);
                return defaultValue;
            }
        } else {
            logger.error("Cannot cast value to a Byte: " + value);
            return defaultValue;
        }
    }
    
    /**
     * to Double
     * @param value Value to change
     * @return Double value
     */
    public static Double toDouble(Object value) {
        return toDouble(value, null);
    }
    
    /**
     * to Double
     * @param value Value to change
     * @param defaultValue Default value
     * @return Double value
     */
    public static Double toDouble(Object value, Double defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        
        if (value instanceof Double) {
            return (Double) value;
        }
        
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        
        if (value instanceof CharSequence) {
            try {
                return Double.valueOf(value.toString());
            } catch (NumberFormatException e) {
                logger.error("Cannot parse Double value for " + value);
                return defaultValue;
            }
        } else {
            logger.error("Cannot cast value to a Double: " + value);
            return defaultValue;
        }
    }
    
    /**
     * to Float
     * @param value Value to change
     * @return Float value
     */
    public static Float toFloat(Object value) {
        return toFloat(value, null);
    }
    
    /**
     * to Float
     * @param value Value to change
     * @param defaultValue Default value
     * @return Float value
     */
    public static Float toFloat(Object value, Float defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        
        if (value instanceof Float) {
            return (Float) value;
        }
        
        if (value instanceof Number) {
            return ((Number) value).floatValue();
        }
        
        if (value instanceof CharSequence) {
            try {
                return Float.valueOf(value.toString());
            } catch (NumberFormatException e) {
                logger.error("Cannot parse Float value for " + value);
                return defaultValue;
            }
        } else {
            logger.error("Cannot cast value to a Float: " + value);
            return defaultValue;
        }
    }
    
    /**
     * to Boolean
     * @param value Value to change
     * @return Boolean value
     */
    public static Boolean toBoolean(Object value) {
        return toBoolean(value, null);
    }
    
    /**
     * to Boolean
     * @param value Value to change
     * @param defaultValue Default value
     * @return Boolean value
     */
    public static Boolean toBoolean(Object value, Boolean defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        
        if (value instanceof CharSequence) {
            try {
                return Boolean.valueOf(value.toString());
            } catch (NumberFormatException e) {
                logger.error("Cannot parse Boolean value for " + value);
                return defaultValue;
            }
        } else {
            logger.error("Cannot cast value to a Boolean: " + value);
            return defaultValue;
        }
    }
}
