/**
 * @(#) DefaultAsyncRunner.java Created on 2013-9-13
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.nanohttpd;

/**
 * The class <code>DefaultAsyncRunner</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class DefaultAsyncRunner implements AsyncRunner {

	private long requestCount;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.nanohttp.AsyncRunner#exec(java.lang.Runnable)
	 */
	@Override
	public void exec(Runnable code) {
		++requestCount;
		Thread t = new Thread(code);
		t.setDaemon(true);
		t.setName("NanoHttpd Request Processor (#" + requestCount + ")");
		t.start();

	}
}
