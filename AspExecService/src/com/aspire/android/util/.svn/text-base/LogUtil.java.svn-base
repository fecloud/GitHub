/**
 * @(#) LogUtil.java Created on 2012-7-31
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.util;

/**
 * The class <code>LogUtil</code>
 * 
 * @author gouam
 * @version 1.0
 */
public final class LogUtil {

    public static String parseStrToLogLevel(String level) {
        if ("NONE".equalsIgnoreCase(level)) {
            return "OFF";
        } else if ("error".equalsIgnoreCase(level) || "debug".equalsIgnoreCase(level) || "warn".equalsIgnoreCase(level)|| "info".equalsIgnoreCase(level)) {
            return level.toUpperCase();
        }else {
            throw new IllegalArgumentException("parse level error ,please check level String");
        }
    }

}
