/**
 * @(#) DefaultTempFileManager.java Created on 2013-9-13
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.nanohttpd.tmp.impl;

import java.util.ArrayList;
import java.util.List;

import com.braver.nanohttpd.tmp.TempFile;
import com.braver.nanohttpd.tmp.TempFileManager;


/**
 * The class <code>DefaultTempFileManager</code>
 * 
 * @author Feng OuYang
 * @version 1.0 Default strategy for creating and cleaning up temporary files.
 *          <p/>
 *          <p>
 *          </p>
 *          This class stores its files in the standard location (that is,
 *          wherever <code>java.io.tmpdir</code> points to). Files are added to
 *          an internal list, and deleted when no longer needed (that is, when
 *          <code>clear()</code> is invoked at the end of processing a
 *          request).</p>
 */
public class DefaultTempFileManager implements TempFileManager {

	private final String tmpdir;

	private final List<TempFile> tempFiles;

	public DefaultTempFileManager() {
		tmpdir = System.getProperty("java.io.tmpdir");
		tempFiles = new ArrayList<TempFile>();
	}
	
	public DefaultTempFileManager(String tmpdir) {
		this.tmpdir = tmpdir;
		tempFiles = new ArrayList<TempFile>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.nanohttp.tmp.TempFileManager#createTempFile()
	 */
	@Override
	public TempFile createTempFile() throws Exception {
		DefaultTempFile tempFile = new DefaultTempFile(tmpdir);
		tempFiles.add(tempFile);
		return tempFile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.nanohttp.tmp.TempFileManager#clear()
	 */
	@Override
	public void clear() {
		for (TempFile file : tempFiles) {
			try {
				file.delete();
			} catch (Exception ignored) {
			}
		}
		tempFiles.clear();

	}

}
