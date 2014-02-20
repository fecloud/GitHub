/**
 * @(#) TempFileManager.java Created on 2013-9-13
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.nanohttpd.tmp;

/**
 * The class <code>TempFileManager</code>
 * 
 * @author Feng OuYang
 * @version 1.0 Temp file manager.
 *          <p/>
 *          <p>
 *          Temp file managers are created 1-to-1 with incoming requests, to
 *          create and cleanup temporary files created as a result of handling
 *          the request.
 *          </p>
 */
public interface TempFileManager {

	TempFile createTempFile() throws Exception;

	void clear();
}
