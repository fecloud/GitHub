/**
 * @(#) LogEvent.java Created on 2007-7-4
 * 
 * Copyright (c) 2007 Aspire. All Rights Reserved
 */
package com.aspire.android.log;

import java.util.Date;

import com.aspire.android.log.attachment.LogAttachment;

/**
 * The class <code>LogEvent</code> is used to store log information which includes the time of the event, the log level
 * and message
 * 
 * @version 1.0
 * @author xuyong
 */
public final class LogEvent {

    public Date timestamp; // The time of the event

    public LogLevel level; // The log level

    public String message; // The log message

    public boolean formative; // indicate this log need format or not

    public Throwable throwable;

    public LogAttachment attachment;

    /**
     * Constructs a new LogEvent with the specified log level and detail log message.
     * 
     * @param level
     *            The log level. The detail message is saved for later retrieval by the {@link #getLevel()} method.
     * @param message
     *            The detail log message. The detail message is saved for later retrieval by the {@link #getMessage()}
     *            method.
     * @param formative
     *            If formative=true, the log will be formated by layout object
     */
    public LogEvent(LogLevel level, String message, boolean formative) {
        this.timestamp = new Date();
        this.level = level;
        this.message = message;
        this.formative = formative;
    }

    /**
     * Constructs a new LogEvent with the specified log level and detail log message.
     * 
     * @param level
     * @param message
     * @param formative
     * @param attachment
     */
    public LogEvent(LogLevel level, String message, boolean formative, Throwable throwable) {
        this.timestamp = new Date();
        this.level = level;
        this.message = message;
        this.formative = formative;
        this.throwable = throwable;
    }

    /**
     * Constructs a new LogEvent with the specified log level and detail log message. Constructor
     * 
     * @param level
     * @param message
     * @param formative
     * @param throwable
     * @param attachment
     */
    public LogEvent(LogLevel level, String message, boolean formative, Throwable throwable, LogAttachment attachment) {
        this(level, message, formative, throwable);
        this.attachment = attachment;
    }

}
