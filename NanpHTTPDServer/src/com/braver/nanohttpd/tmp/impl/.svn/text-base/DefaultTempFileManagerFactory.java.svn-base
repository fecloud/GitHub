/**
 * @(#) DefaultTempFileManagerFactory.java Created on 2013-9-13
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.nanohttpd.tmp.impl;

import com.braver.nanohttpd.tmp.TempFileManager;
import com.braver.nanohttpd.tmp.TempFileManagerFactory;

/**
 * The class <code>DefaultTempFileManagerFactory</code>
 * 
 * Default strategy for creating and cleaning up temporary files.
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class DefaultTempFileManagerFactory implements TempFileManagerFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.nanohttp.tmp.TempFileManagerFactory#create()
	 */
	@Override
	public TempFileManager create() {
		return new DefaultTempFileManager();
	}

}
