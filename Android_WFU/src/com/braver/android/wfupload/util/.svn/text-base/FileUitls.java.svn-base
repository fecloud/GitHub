/**
 * @(#) FileUitls.java Created on 2013-9-10
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.android.wfupload.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The class <code>FileUitls</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class FileUitls {

	public static final void copyFile(String src, String target) throws FileNotFoundException,
			IOException {
		copyFile(new File(src), new File(target));
	}

	public static final void copyFile(File src, File target) throws FileNotFoundException,
			IOException {
		copyFile(new FileInputStream(src), new FileOutputStream(target));
	}

	public static final void copyFile(InputStream in, OutputStream out) throws IOException {

		if (null != in && null != out) {

			final byte[] bs = new byte[1024];
			int len = -1;

			while (-1 != (len = in.read(bs))) {
				out.write(bs, 0, len);
			}
			out.flush();
			out.close();
			in.close();

		}

	}
	
	public static final void reNameFile(String src,String target){
	    final File file = new File(src);
	    if(file.exists()){
	        file.renameTo(new File(target));
	    }
	}

	public static final void deleteFile(String file) {
		new File(file).delete();
	}
}
