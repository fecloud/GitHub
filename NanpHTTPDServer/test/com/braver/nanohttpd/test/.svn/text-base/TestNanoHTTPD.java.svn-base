/**
 * @(#) TestNanoHTTPD.java Created on 2013-9-13
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.nanohttpd.test;

import java.io.IOException;

import com.braver.nanohttpd.NanoHTTPD;

/**
 * The class <code>TestNanoHTTPD</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class TestNanoHTTPD extends NanoHTTPD {

	/**
	 * @param port
	 */
	public TestNanoHTTPD(int port) {
		super(port);
	}

	public static void main(String[] args) throws IOException {
		final TestNanoHTTPD httpd = new TestNanoHTTPD(8080) {
			protected com.braver.nanohttpd.handler.HTTPHandler conHandler() {
				return new TestHttpSession();
			};
		};
		httpd.start();
	}

}
