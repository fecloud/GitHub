/**
 * @(#) HttpMethod.java Created on 2013-9-13
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.nanohttpd.request;

/**
 * The class <code>Method</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public enum Method {
	GET, PUT, POST, DELETE, HEAD;

	public static Method lookup(String method) {
		for (Method m : Method.values()) {
			if (m.toString().equalsIgnoreCase(method)) {
				return m;
			}
		}
		return null;
	}
}
