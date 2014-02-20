/**
 * @(#) TempFile.java Created on 2013-9-13
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.nanohttpd.tmp;

import java.io.OutputStream;

/**
 * The class <code>TempFile</code>
 * 
 * @author Feng OuYang
 * @version 1.0 A temp file.
 *          <p/>
 *          <p>
 *          Temp files are responsible for managing the actual temporary storage
 *          and cleaning themselves up when no longer needed.
 *          </p>
 */
public interface TempFile {
	
	OutputStream open() throws Exception;

	void delete() throws Exception;

	String getName();
}
