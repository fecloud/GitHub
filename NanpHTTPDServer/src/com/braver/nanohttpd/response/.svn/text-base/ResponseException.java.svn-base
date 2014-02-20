/**
 * @(#) ResponseException.java Created on 2013-9-13
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.nanohttpd.response;

/**
 * The class <code>ResponseException</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class ResponseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Status status;

	public ResponseException(Status status, String message) {
		super(message);
		this.status = status;
	}

	public ResponseException(Status status, String message, Exception e) {
		super(message, e);
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

}
