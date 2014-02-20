/**
 * @(#) MIMEType.java Created on 2013-9-13
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.nanohttpd;

/**
 * The class <code>MIMEType</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public enum MIMEType {

	/**
	 * Common mime type for dynamic content: plain text
	 */
	PLAINTEXT("text/plain"),
	/**
	 * Common mime type for dynamic content: html
	 */
	HTML("text/html"),

	/**
	 * css
	 */
	CSS("text/css"),

	/**
	 * js
	 */
	JS("application/javascript"),

	/**
	 * png
	 */
	PNG("image/png"),

	/**
	 * swf
	 */
	SWF("application/x-shockwave-flash"),

	/**
	 * Common mime type for dynamic content: binary
	 */
	DEFAULT_BINARY("application/octet-stream"),

	UNKNOW("unknow");

	private final String type;

	/**
	 * @param type
	 * @param desc
	 */
	private MIMEType(String type) {
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return getType();
	}

	public static final MIMEType lookup(String filename) {
		if (null != filename && filename.lastIndexOf(".") != -1
				&& filename.lastIndexOf(".") != filename.length() - 1) {
			final int dot = filename.lastIndexOf(".");
			final String suffix = filename.substring(dot + 1, filename.length());

			for (MIMEType mimeType : MIMEType.values()) {
				if (mimeType.name().equalsIgnoreCase(suffix))
					return mimeType;
			}
		}
		return UNKNOW;
	}
}
