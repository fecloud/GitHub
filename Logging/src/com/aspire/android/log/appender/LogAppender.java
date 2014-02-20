/*
 * @(#) LogAppender.java Created on 2007-07-04
 * 
 * Copyright (c) 2007 Aspire. All Rights Reserved
 */

package com.aspire.android.log.appender;

import com.aspire.android.log.LogEvent;
import com.aspire.android.log.LogLevel;
import com.aspire.android.log.layout.LogLayout;
import com.aspire.android.log.layout.TextLogLayout;

/**
 * The LogAppender is the base class of all Appenders. It's a abstract class. Each appenders should inherit from it. You
 * should create a anonymous layout use new operator and set to the appender. The passed appender will be distoryed in
 * destructor.
 * 
 * @version 1.0
 * @author xuyong
 */
public abstract class LogAppender {

    protected LogLayout layout; // The log layout used to format the log message

    protected LogLevel levelFilter; // The log level filter, only the log whoes level

    // is greater or equal to levelFilter will be
    // printed or displayed

    /**
     * Constructs a new LogAppender with basic layout as its LogLayout, and LogLevel.INFO as its log level filter
     */
    public LogAppender() {
        this(null);
    }

    /**
     * Constructs a new LogAppender with the specified log layout as its LogLayout and LogLevel.INFO as its log level
     * filter
     * 
     * @param layout
     *            The special log layout. The log layout is saved for later retrieval by the {@link #getLayout()}
     *            method.
     */
    public LogAppender(LogLayout layout) {
        this(layout, LogLevel.ALL);
    }

    /**
     * Constructs a new LogAppender with the specified log layout as its LogLayout and the specified level filter as its
     * log level filter
     * 
     * @param layout
     *            The special log layout. The log layout is saved for later retrieval by the {@link #getLayout()}
     *            method.
     * @param levelFilter
     *            The special log filter. The log level filter is saved for later retrieval by the
     *            {@link #getLevelFilter()} method.
     */
    public LogAppender(LogLayout layout, LogLevel levelFilter) {
        this.layout = layout != null ? layout : new TextLogLayout();
        this.levelFilter = levelFilter;
    }

    /**
     * Sets the log layout with the specified log layout
     * 
     * @param layout
     *            The special log layout
     */
    public synchronized void setLogLayout(LogLayout layout) {
        this.layout = layout;
    }

    /**
     * Gets the log layout
     * 
     * @return Returns the log layout
     */
    public LogLayout getLogLayout() {
        return layout;
    }

    /**
     * Sets the log level filter with the specified log level filter
     * 
     * @param levelFilter
     *            The special log level filter
     */
    public synchronized void setLevelFilter(LogLevel levelFilter) {
        this.levelFilter = levelFilter;
    }

    /**
     * Gets the log level filter
     * 
     * @return Returns the log level filter
     */
    public LogLevel getLevelFilter() {
        return levelFilter;
    }

    /**
     * Append/write log event
     * 
     * @param evt
     *            The log event, which contains log information
     */
    public synchronized void append(LogEvent evt) {
        if(layout != null && evt != null && evt.level.mask(levelFilter)) {  
            // Don't throw any kind of exception when writing
            // Log, otherwise will make the application coredump
            try {
                write(evt);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Close the file/socket, etc has being opened
     */
    public abstract void close() throws Exception;

    /**
     * Write the log information
     * 
     * @param evt
     *            The log event, which contains log information
     * @throws ToolException
     *             If error occurs, will throw ToolException
     */
    protected abstract void write(LogEvent evt) throws Exception;
}
