/**
 * @(#) LogManager.java Created on 2007-7-16
 * 
 * Copyright (c) 2007 Aspire. All Rights Reserved
 */
package com.aspire.android.log;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Hashtable;

import android.content.Context;
import android.util.Log;

import com.aspire.android.log.config.LogConfigure;

/**
 * The class <code>LogManager</code> is used to manage and create Logger
 * 
 * @version 1.0
 * @author xuyong
 */
public final class LogManager {

    /*
     * InheritableThreadLocal variable, using to transfer a certain case's logger object to it's child thread.
     * 
     * private static final InheritableThreadLocal<Logger> threadLocalLogger = new InheritableThreadLocal<Logger>();
     */
    /*
     * The loggers used to store logger Note: Here use Hashtable but not HashMap cause Hashtable is thread-safe
     */
    protected Hashtable<String, Logger> loggers;

    /*
     * The LogManager instance
     */
    protected final static LogManager instance = new LogManager();

    /**
     * The main logger name
     */
    public final static String MAIN_LOGGER = "main";

    /**
     * Constructs a new LogManager
     */
    private LogManager() {
        loggers = new Hashtable<String, Logger>();
    }

    /**
     * Gets the instance of the LogManager
     * 
     * @return Returns the instance of the LogManager
     */
    public static LogManager getInstance() {
        return instance;
    }

    /**
     * Gets a Logger by using current thread name as the logger name
     * 
     * @return Returns the name of the logger
     * 
     *         public Logger getLogger() { Logger logger = threadLocalLogger.get(); if (logger == null) { return
     *         getLogger(Long.toString(Thread.currentThread().getId())); } return logger; }
     */

    /**
     * Gets a Logger by logger name ,</p> if not found , create for asset/logconfig.properties file
     * 
     * @param name
     *            The name of the Logger
     * @return Returns the name of the logger
     */
    public Logger getLogger(Context mContext, String name, HashMap<String, String> environment) {

        try {
            return getLogger(name, mContext.getAssets().open("logconfig.properties"), environment);
        } catch (IOException e) {
            throw new RuntimeException("not found assert/logconfig.properties !");
        }

    }

    /**
     * 
     * @param name
     * @param filepath
     * @return
     */
    public Logger getLogger(String name, InputStream in, HashMap<String, String> environment) {
        Logger logger = loggers.get(name);
        if (logger == null) {
            LogConfigure configure = new LogConfigure(in);
            configure.addAllEnvironment(environment);
            try {
                logger = configure.getConfigure();
            } catch (Exception e) {
                Log.e("LogManager", "", e);
                throw new RuntimeException("the log config fail , please cheching");
            }
            loggers.put(name, logger);
        }
        return logger;
    }

    /**
     * Removes a Logger from the Log system
     * 
     * @param name
     *            The name of the Logger
     */
    public Logger removeLogger(String name) {
        return loggers.remove(name);
    }

    /**
     * Removes a Logger by using current thread name as the logger name
     */
    public Logger removeLogger() {
        return removeLogger(Long.toString(Thread.currentThread().getId()));
    }

    /**
     * config logger params
     * 
     * @param logger
     * @param configure
     * 
     *            private void configLogger(Logger logger, LogConfigure configure) { if (null != logger && null !=
     *            configure) { for (LogAppender appender : configure.getAppenders()) { logger.addAppender(appender); } }
     *            }
     */
}
