/**
 * @(#) DefaultTempFile.java Created on 2013-9-13
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.nanohttpd.tmp.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.braver.nanohttpd.tmp.TempFile;

/**
 * The class <code>DefaultTempFile</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class DefaultTempFile implements TempFile {

	 private File file;
	 
     private OutputStream fstream;
	
     public DefaultTempFile(String tempdir) throws IOException {
         file = File.createTempFile("NanoHTTPD-", "", new File(tempdir));
         fstream = new FileOutputStream(file);
     }
     
	/* (non-Javadoc)
	 * @see com.braver.nanohttp.tmp.TempFile#open()
	 */
	@Override
	public OutputStream open() throws Exception {
		return fstream;
	}

	/* (non-Javadoc)
	 * @see com.braver.nanohttp.tmp.TempFile#delete()
	 */
	@Override
	public void delete() throws Exception {
		 if(null != fstream)
			 fstream.close();
         file.delete();
	}

	/* (non-Javadoc)
	 * @see com.braver.nanohttp.tmp.TempFile#getName()
	 */
	@Override
	public String getName() {
		 return file.getAbsolutePath();
	}

}
