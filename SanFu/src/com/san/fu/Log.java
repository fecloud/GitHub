/**
 * @(#) Log.java Created on 2012-7-12
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.san.fu;

/**
 * The class <code>Log</code>
 * 
 * @author Administrator
 * @version 1.0
 */
public class Log {

    public static final int LOG_LEVEL_VERBOSE = 1;

    public static final int LOG_LEVEL_DEBUG = 2;

    public static final int LOG_LEVEL_INFO = 3;

    public static final int LOG_LEVEL_WARN = 4;

    public static final int LOG_LEVEL_ERROR = 5;

    public static final int LOG_LEVEL_FATAL = 6;

    private static int level = LOG_LEVEL_ERROR;

    public static void log(int levl, String msg) {
        if (levl >= level) {
            if (levl >= LOG_LEVEL_ERROR) {
                System.err.println(msg);
            } else {
                System.out.println(msg);
            }
        }
    }
}
