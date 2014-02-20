/**
 * @(#) Status.java Created on 2013-9-13
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.nanohttpd.response;

/**
 * The class <code>Status</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public enum Status {
	OK(200, "OK"), CREATED(201, "Created"), ACCEPTED(202, "Accepted"), NO_CONTENT(204, "No Content"), PARTIAL_CONTENT(
			206, "Partial Content"), REDIRECT(301, "Moved Permanently"), NOT_MODIFIED(304,
			"Not Modified"), BAD_REQUEST(400, "Bad Request"), UNAUTHORIZED(401, "Unauthorized"), FORBIDDEN(
			403, "Forbidden"), NOT_FOUND(404, "Not Found"), RANGE_NOT_SATISFIABLE(416,
			"Requested Range Not Satisfiable"), INTERNAL_ERROR(500, "Internal Server Error");

	private final int code;

	private final String desc;

	/**
	 * @param code
	 * @param desc
	 */
	private Status(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

}
