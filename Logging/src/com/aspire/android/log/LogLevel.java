/**
 * @(#) LogLevel.java Created on 2011-6-3
 *
 * Copyright (c) 2011 Aspire. All Rights Reserved
 */
package com.aspire.android.log;

/**
 * The class <code>LogLevel</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public enum LogLevel {

    ALL(0), VERBOSE(1), DEBUG(2), INFO(3), WARN(4), ERROR(5), FATAL(6), OFF(7);

    private int level;

    private LogLevel(int level) {
        this.level = level;
    }

    public int level() {
        return level;
    }

    /**
     * paser LogLevel for name
     * @param level
     * @return
     */
    public static LogLevel forName(String level) {
        if (level != null) {
            level = level.toUpperCase();
            if (level.equals(ALL.name())) {
                return ALL;
            }
            if (level.equals(VERBOSE.name())) {
                return VERBOSE;
            }
            if (level.equals(DEBUG.name())) {
                return DEBUG;
            }
            if (level.equals(INFO.name())) {
                return INFO;
            }
            if (level.equals(WARN.name())) {
                return WARN;
            }
            if (level.equals(ERROR.name())) {
                return ERROR;
            }
            if (level.equals(FATAL.name())) {
                return FATAL;
            }
            if (level.equals(OFF.name())) {
                return OFF;
            }
        }
        throw new RuntimeException("Unknown log level: " + level);
    }

    /**
     * paser LogLevel for level
     * @param level
     * @return
     */
    public static LogLevel forLevel(int level) {
        if (ALL.level() == level) {
            return ALL;
        }
        if (VERBOSE.level == level) {
            return VERBOSE;
        }
        if (DEBUG.level() == level) {
            return DEBUG;
        }
        if (INFO.level() == level) {
            return INFO;
        }
        if (WARN.level() == level) {
            return WARN;
        }
        if (ERROR.level() == level) {
            return ERROR;
        }
        if (FATAL.level() == level) {
            return FATAL;
        }
        if (OFF.level() == level) {
            return OFF;
        }
        throw new RuntimeException("Unknown log level: " + level);
    }

    public boolean mask(LogLevel level) {
        return this.level >= level.level;
    }
}
