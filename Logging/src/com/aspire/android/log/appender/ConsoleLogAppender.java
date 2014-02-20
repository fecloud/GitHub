/**
// * @(#) ConsoleLogAppender.java Created on 2007-7-5
 * 
 * Copyright (c) 2007 Aspire. All Rights Reserved
 */
package com.aspire.android.log.appender;

import com.aspire.android.log.LogEvent;
import com.aspire.android.log.LogLevel;
import com.aspire.android.log.layout.LogLayout;

/**
 * The class <code>ConsoleLogAppender</code> is a special log appender which will output the log information to the
 * screen.
 * 
 * @version 1.0
 * @author xuyong
 */
public class ConsoleLogAppender extends LogAppender {

    /**
     * Constructs a new ConsoleLogAppender with <code>null</code> as its LogLayout, and LogLevel.INFO as its log level
     * filter
     */
    public ConsoleLogAppender() {
        super();
    }

    /**
     * Constructs a new ConsoleLogAppender with the specified log layout as its LogLayout and LogLevel.INFO as its log
     * level filter
     * 
     * @param layout
     *            The special log layout. The log layout is saved for later retrieval by the {@link #getLayout()}
     *            method.
     */
    public ConsoleLogAppender(LogLayout layout) {
        super(layout);
    }

    /**
     * Constructs a new ConsoleLogAppender with the specified log layout as its LogLayout and the specified level filter
     * as its log level filter
     * 
     * @param layout
     *            The special log layout. The log layout is saved for later retrieval by the {@link #getLayout()}
     *            method.
     * @param levelFilter
     *            The special log filter. The log level filter is saved for later retrieval by the
     *            {@link #getLevelFilter()} method.
     */
    public ConsoleLogAppender(LogLayout layout, LogLevel levelFilter) {
        super(layout, levelFilter);
    }

    /**
     * Write the log information
     * 
     * @param evt
     *            The log event, which contains log information
     * @throws ToolException
     *             If error occurs, will throw ToolException
     */
    protected void write(LogEvent evt) throws Exception {
        if (layout != null && evt.formative) {
            System.out.print(layout.format(evt));
        } else {
            System.out.print(evt.message);
        }
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.log.appender.LogAppender#close()
     */
    @Override
    public void close() {
        // TODO Auto-generated method stub

    }
}
