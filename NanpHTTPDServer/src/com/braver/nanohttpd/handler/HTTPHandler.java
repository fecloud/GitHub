/**
 * @(#) HTTPSession.java Created on 2013-9-13
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.nanohttpd.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.braver.nanohttpd.tmp.TempFileManager;

/**
 * The class <code>HTTPSession</code> Handles one session, i.e. parses the HTTP
 * request and returns the response.
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public interface HTTPHandler {

	public void execute(TempFileManager tempFileManager, InputStream inputStream,
			OutputStream outputStream) throws IOException;

}
