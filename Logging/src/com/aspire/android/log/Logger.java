/**
 * @(#) Logger.java Created on 2007-7-5
 * 
 * Copyright (c) 2007 Aspire. All Rights Reserved
 */
package com.aspire.android.log;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Vector;

import android.content.Context;

import com.aspire.android.log.appender.LogAppender;
import com.aspire.android.log.attachment.LogAttachment;

/**
 * The class <code>Logger</code> is the interface to write log
 * 
 * @version 1.0
 * @author ouyangfeng
 */
public class Logger {

    private String name;
    /*
     * The log appenders Note: Here use Vector but not ArrayList cause Vector is thread-safe
     */
    protected Vector<LogAppender> appenders;

    protected HashMap<String, String> environment;

    /**
     * 最低日志打印级别
     */
    private LogLevel lowestLogLevel = LogLevel.ALL;

    /**
     * Constructor a default Logger
     */
    public Logger() {
        appenders = new Vector<LogAppender>();
    }

    /**
     * Adds an appender to the log appender list
     * 
     * @param appender
     *            The appender will be added
     * @return Returns true if the log is added, or will return false
     */
    public synchronized boolean addAppender(LogAppender appender) {

        if (appender == null || appenders.contains(appender))
            return false;
        appenders.add(appender);

        if (appender.getLevelFilter().mask(lowestLogLevel)) {
            lowestLogLevel = appender.getLevelFilter();
        }

        return true;
    }

    /**
     * Removes an appender from the log appender list
     * 
     * @param appender
     *            The appender to be removed
     * @throws Exception
     */
    public synchronized void removeAppender(LogAppender appender) throws Exception {
        if (appender != null)
            appender.close();

        appenders.remove(appender);

        lowestLogLevel = LogLevel.ALL;

        for (int i = 0; i < appenders.size(); i++) {
            LogAppender app = appenders.get(i);
            if (app.getLevelFilter().mask(lowestLogLevel)) {
                lowestLogLevel = app.getLevelFilter();
            }
        }
    }

    /**
     * Removes all appenders from the log appender list
     * 
     * @throws Exception
     */
    public synchronized void removeAll() throws Exception {
        for (int i = 0; i < appenders.size(); i++) {
            LogAppender app = appenders.get(i);
            app.close();
        }

        appenders.removeAllElements();

        lowestLogLevel = LogLevel.ALL;
    }

    /**
     * Sets Log level
     * 
     * @param level
     *            The log level
     */
    public synchronized void setLogLevel(LogLevel level) {
        for (int i = 0; i < appenders.size(); i++) {
            LogAppender app = appenders.get(i);
            app.setLevelFilter(level);
        }

        if (level.mask(lowestLogLevel)) {
            lowestLogLevel = level;
        }
    }

    /**
     * Sets Log level
     * 
     * @param levelName
     *            The log level name
     */
    public void setLogLevel(String levelName) {
        setLogLevel(LogLevel.forName(levelName));
    }

    /**
     * Gets lowest log level
     * 
     * @return Returns the lowest log level
     */
    public LogLevel getLowestLogLevel() {

        return lowestLogLevel;
        // int level = LogLevel.FATAL;
        //
        // synchronized(appenders) {
        // for (int i = 0; i < appenders.size(); i++) {
        // LogAppender app = appenders.get(i);
        // if (level > app.getLevelFilter()) {
        // level = app.getLevelFilter();
        // }
        // }
        // }
        //
        // return level;
    }

    public void log(int logLevel, String message) {
        log(logLevel, message, null, null);
    }

    public void log(int logLevel, String message, Throwable throwable) {
        log(logLevel, message, throwable, null);
    }

    public void log(int logLevel, String message, LogAttachment attachment) {
        log(logLevel, message, null, attachment);
    }

    public void log(int logLevel, String message, Throwable t, LogAttachment attachment) {
        LogLevel level = LogLevel.forLevel(logLevel);
        switch (level) {
        case VERBOSE:
            verbose(message, t, attachment);
            break;
        case DEBUG:
            debug(message, t, attachment);
            break;
        case INFO:
            info(message, t, attachment);
            break;
        case WARN:
            warn(message, t, attachment);
            break;
        case ERROR:
            error(message, t, attachment);
            break;
        case FATAL:
            fatal(message, t, attachment);
            break;
        default:
            break;
        }
    }

    /**
     * Writes debug log messages
     * 
     * @param msg
     *            The debug log messages
     */
    public void debug(String msg) {
        debug(msg, null);
    }

    public void debug(String msg, Throwable t) {
        log(LogLevel.DEBUG, msg, t, null);
    }

    /**
     * Writes debug log messages with exception information to logs
     * 
     * @param msg
     *            The error log messages
     * @param e
     *            The exception
     */
    public void debug(String msg, Throwable t, LogAttachment attachment) {
        log(LogLevel.DEBUG, msg, t, attachment);
    }

    /**
     * Writes verbose log messages
     * 
     * @param msg
     *            The protocol log messages
     */
    public void verbose(String msg) {
        verbose(msg, null, null);
    }

    public void verbose(String msg, Throwable throwable) {
        verbose(msg, throwable, null);
    }

    public void verbose(String msg, Throwable throwable, LogAttachment attachment) {
        log(LogLevel.VERBOSE, msg, throwable, attachment);
    }

    /**
     * Writes information log messages
     * 
     * @param msg
     *            The information log messages
     */
    public void info(String msg) {
        info(msg, null);
    }

    public void info(String msg, Throwable t) {
        log(LogLevel.INFO, msg, t, null);
    }

    public void info(String msg, Throwable t, LogAttachment attachment) {
        log(LogLevel.INFO, msg, t, attachment);
    }

    /**
     * Writes warning log messages
     * 
     * @param msg
     *            The warning log messages
     */
    public void warn(String msg) {
        warn(msg, null);
    }

    public void warn(String msg, Throwable t) {
        warn(msg, t, null);
    }

    /**
     * Writes warning log messages with exception information to logs
     * 
     * @param msg
     *            The warning log messages
     * @param e
     *            The exception
     */
    public void warn(String msg, Throwable t, LogAttachment attachment) {
        log(LogLevel.WARN, msg, t, attachment);
    }

    /**
     * Writes error log messages
     * 
     * @param msg
     *            The error log messages
     */
    public void error(String msg) {
        error(msg, null);
    }

    /**
     * Writes error log messages with exception information to logs
     * 
     * @param msg
     *            The error log messages
     * @param e
     *            The exception
     */
    public void error(String msg, Throwable t) {
        error(msg, t, null);
    }

    public void error(String msg, Throwable t, LogAttachment attachment) {
        log(LogLevel.ERROR, msg, t, null);
    }

    /**
     * Writes fatal log messages
     * 
     * @param msg
     *            The fatal log messages
     */
    public void fatal(String msg) {
        fatal(msg, null);
    }

    public void fatal(String msg, Throwable t) {
        fatal(msg, t, null);
    }

    public void fatal(String msg, Throwable t, LogAttachment attachment) {
        log(LogLevel.FATAL, msg, t, attachment);
    }

    /**
     * Writes log messages to the appender logs
     * 
     * @param level
     *            The level of the log
     * @param msg
     *            The log message
     */
    protected void log(LogLevel level, String msg) {
        log(level, msg, true);
    }

    /**
     * Writes log messages to the appender logs
     * 
     * @param level
     *            The level of the log
     * @param msg
     *            The log message
     * @param formative
     *            If isNeedFormat=true, the log will be formated
     */
    protected void log(LogLevel level, String msg, boolean formative) {
        log(new LogEvent(level, msg, formative));
    }

    /**
     * Log message with attachment
     * 
     * @param level
     * @param msg
     * @param attachment
     */
    protected void log(LogLevel level, String msg, Throwable throwable, LogAttachment attachment) {
        log(new LogEvent(level, msg, true, throwable, attachment));
    }

    /**
     * Log message with attachment
     * 
     * @param level
     * @param msg
     * @param formative
     * @param attachment
     */
    protected void log(LogLevel level, String msg, boolean formative, Throwable throwable, LogAttachment attachment) {
        log(new LogEvent(level, msg, formative, throwable, attachment));
    }

    /**
     * Write a log event
     * 
     * @param evt
     */
    protected void log(LogEvent evt) {
        synchronized (appenders) {
            for (LogAppender appender : appenders) {
                appender.append(evt);
            }
        }
    }

    /**
     * Dispose this logger object
     * 
     * @throws Exception
     */
    public void dispose() {
        for (LogAppender appender : appenders) {
            try {
                appender.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        appenders.clear();
        LogManager.getInstance().removeLogger(this.name);
    }

    /**
     * get logger for logger name
     * 
     * @param name
     * @return
     */
    public static Logger getLogger(Context mContext, String name, HashMap<String, String> environment) {
        final LogManager logManager = LogManager.getInstance();
        Logger logger = logManager.getLogger(mContext, name, environment);
        logger.name = name;
        return logger;
    }
    
    public static Logger getLogger(Context mContext , String name,InputStream config,HashMap<String, String> environment){
        final LogManager logManager = LogManager.getInstance();
        Logger logger = logManager.getLogger(name, config, environment);
        logger.name = name;
        return logger;
    }

    /**
     * Getter of environment
     * 
     * @return the environment
     */
    public HashMap<String, String> getEnvironment() {
        return environment;
    }

    /**
     * Setter of environment
     * 
     * @param environment
     *            the environment to set
     */
    public void setEnvironment(HashMap<String, String> environment) {
        this.environment = environment;
    }

}
