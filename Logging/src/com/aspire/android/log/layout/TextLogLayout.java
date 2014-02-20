/**
 * @(#) TextLogLayout.java Created on 2007-7-5
 * 
 * Copyright (c) 2007 Aspire. All Rights Reserved
 */
package com.aspire.android.log.layout;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import com.aspire.android.log.LogEvent;
import com.aspire.android.log.LogLevel;
import com.aspire.android.util.DateTimeUtil;

/**
 * The class <code>TextLogLayout</code>
 * 
 * @version 1.0
 * @author xuyong
 */
public class TextLogLayout implements LogLayout {

	public final static String CRLF = "\n";

	/*
	 * The instance of the TextLogLayout
	 */

	/**
	 * Constructs a new TextLogLayout
	 */
	public TextLogLayout() {
	}

	/**
	 * Formats the LogEvent data to a string that appenders can log. Implement
	 * this method to create your own layout format.
	 * 
	 * @param evt
	 *            The log event which contains log information
	 * @return Returns the formated log information
	 */
	public String format(LogEvent evt) {
		final StringBuffer buffer = new StringBuffer();
		if (evt.message != null) {
			switch (evt.level) {
			case VERBOSE:
			case DEBUG:
			case INFO:
			case WARN:
			case ERROR:
			case FATAL:
				buffer.append(formatMsg(evt)).append(formatThrowable(evt)).append(formatAttachment(evt));
				break;
			}
		}
		return buffer.toString();
	}

	/**
	 * format LogEvent message
	 * 
	 * @param evt
	 * @return
	 */
	private static String formatMsg(LogEvent evt) {
		final String[] lines = evt.message.split(CRLF);
		final StringBuffer buffer = new StringBuffer();
		for (String string : lines) {
			buffer.append(formatLineHead(evt)).append(string).append(CRLF);
		}
		return buffer.toString();
	}

	/**
	 * format LogEvent Throwable
	 * 
	 * @param evt
	 * @return
	 */
	private static String formatThrowable(LogEvent evt) {
		if (null == evt || null == evt.throwable)
			return "";
		return getStackTraceString(evt);
	}

	/**
	 * format LogEvent Attachment
	 * 
	 * @param evt
	 * @return
	 */
	private static String formatAttachment(LogEvent evt) {
		if (null == evt || null == evt.attachment)
			return "";
		final StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(formatLineHead(evt));
			final String attachmentMsg = evt.attachment.saveAttachment();
			buffer.append("[Attachment: ").append(attachmentMsg).append("]").append(CRLF);
		} catch (Exception e) {
			buffer.append("save attachment errot").append(CRLF).append(getStackTraceString(new Date(), e));
		}
		return buffer.toString();
	}

	/**
	 * Handy function to get a loggable stack trace from a Throwable
	 * 
	 * @param tr
	 *            An exception to log
	 */
	public static String getStackTraceString(LogEvent evt) {
		if (evt.throwable == null) {
			return "";
		}
		return getStackTraceString(evt.timestamp, evt.throwable);
	}

	/**
	 * Handy function to get a loggable stack trace from a Throwable
	 * 
	 * @param tr
	 *            An exception to log
	 */
	public static String getStackTraceString(Date date, Throwable throwable) {
		if (throwable == null) {
			return "";
		}
		final StringWriter sw = new StringWriter();
		throwable.printStackTrace(new PrintWriter(sw));
		final StringBuffer buffer = new StringBuffer();
		for (String line : sw.toString().split(CRLF)) {
			buffer.append(DateTimeUtil.toText(date, DateTimeUtil.FORMAT_MS)).append(" ").append(LogLevel.ERROR.name())
					.append(": ").append(line).append(CRLF);
		}
		return buffer.toString();
	}

	/**
	 * format Line Head
	 * 
	 * @param evt
	 * @return
	 */
	private static String formatLineHead(LogEvent evt) {
		final StringBuffer buffer = new StringBuffer();
		buffer.append(DateTimeUtil.toText(evt.timestamp, DateTimeUtil.FORMAT_MS)).append(" ").append(evt.level.name())
				.append(": ");
		return buffer.toString();

	}
}
