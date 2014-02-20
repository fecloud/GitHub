/**
 * @(#) AsyncRunner.java Created on 2013-9-13
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.nanohttpd;

/**
 * The class <code>AsyncRunner</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public interface AsyncRunner {
	
	void exec(Runnable code);
	
}
