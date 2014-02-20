/**
 * @(#) LocalHttpClient.java Created on 2012-7-10
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.sync.pub;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.aspire.android.test.log.RunLogger;
import com.aspire.mgt.ats.tm.sync.IHTTPClient;
import com.aspire.mgt.ats.tm.sync.ResponseProcessor;

/**
 * The class <code>LocalHttpClient</code>
 *
 * @author likai
 * @version 1.0
 */
public class LocalHttpClient implements IHTTPClient {

    private RunLogger runLogger = RunLogger.getInstance();
    
	/**
	 * 用本地路径地址来模拟http连接
	 */
	public void request(String url, String msg, ResponseProcessor processor) {
	    runLogger.debug(getClass(), "url is " + url + " in local mode");
		File file = new File(url);
		if (!file.exists())return;
		FileInputStream inputStream = null;
		try {
			try{
				inputStream = new FileInputStream(file);
				processor.process(inputStream);
				inputStream.close();
				inputStream = null;
				file.delete();
			}finally{
				if(inputStream != null){
					inputStream.close();
				}
			}
		} catch (IOException e) {
	        runLogger.error(getClass(), "while read sdcard file in local mode");
		}
	}

}
