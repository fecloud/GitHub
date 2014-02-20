/**
 * @(#) LocalFtpClient.java Created on 2012-7-10
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.sync.pub;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.commons.net.io.Util;

import com.aspire.android.common.exception.ExceptionHandler;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.log.RunLogger;
import com.aspire.mgt.ats.tm.sync.IFTPClient;
import com.aspire.mgt.ats.tm.sync.ResponseProcessor;
import com.aspire.mgt.ats.tm.sync.util.zip.Unzip;
import com.aspire.mgt.ats.tm.sync.util.zip.ZFile;

/**
 * The class <code>LocalFtpClient</code>
 *
 * @author likai
 * @version 1.0
 */
public class LocalFtpClient implements IFTPClient {
	
    private RunLogger runLogger = RunLogger.getInstance();
    
	/**
	 * 本地接口主目录
	 */
	private String localParentPath;
	
	public LocalFtpClient(String localParentPath){
		this.localParentPath = localParentPath;
	}
	/** 
	 * 读取本地文件来实现模拟ftp下载
	 */
	public boolean downloadFile(String path, String fileName, FILE_TYPE type,
			ResponseProcessor respProcessor) {
		File scriptLocalPath = new File(localParentPath,path);
		File file = new File(scriptLocalPath,fileName);
		if(!file.exists())return false;
		FileInputStream inputStream = null;
		try {
			try{
				inputStream = new FileInputStream(file);
				respProcessor.process(inputStream);
				inputStream.close();
				inputStream = null;
				file.delete();
				return true;
			}finally{
				if(inputStream != null){
					inputStream.close();
				}
			}
		} catch (IOException e) {
		    runLogger.error(LocalFtpClient.class, "LocalFtpClient to read file, and errmessge is " + e.getMessage());
		}
		return false;
	}

	/**
	 * 用于解析指定文件
	 * @param path 文件的绝对路径
	 * @param processor 处理文件inputstream的处理器
	 * @return
	 */
	public String downloadParse(String path, ResponseProcessor processor){
		File serviceKeyLocalPath = new File(localParentPath,path);
		if(!serviceKeyLocalPath.exists()){
			serviceKeyLocalPath.mkdirs();
			return null;
		}
		File[] files = serviceKeyLocalPath.listFiles();
		if(files.length == 0)return null;
		try {
			if(parseFiles(processor, files[0])){
				files[0].delete();
			}
			return files[0].getName();
		} catch (Exception e) {
            runLogger.error(LocalFtpClient.class, "LocalFtpClient to downloadParse, and errmessge is " + e.getMessage());
		}
		return null;
	}
	/**
	 * 解析下载的文件
	 * 
	 * @param parser
	 *            Parser
	 * @param files
	 *            本地文件列表
	 * @param path
	 *            FTP路径
	 * @throws Exception
	 *             异常
	 */
	private boolean parseFiles(ResponseProcessor parser, File file) throws Exception {
		if (file == null) {
			return false;
		}
		try {
			if (file.getName().toLowerCase().endsWith(".zip")) {
				Unzip unzip = new Unzip(file);
				try {
					List<ZFile> zFiles = unzip.listFiles();
					if (zFiles == null) {
						return false;
					}
					ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
					try {
						for (ZFile zfile : zFiles) {
							out.reset();
							if (parser != null) {
								unzip.readFile(zfile, out);
								ByteArrayInputStream input = new ByteArrayInputStream(
										out.toByteArray());
								out.close();
								out = null;
								try {
									parser.process(input);
									return true;
								} finally {
									input.close();
								}
							}
						}
					} finally {
						if (out != null) {
							out.close();
						}
					}

				} finally {
					unzip.close();
				}
			} else {
				FileInputStream input = new FileInputStream(file);
				try {
					if (parser != null) {
						parser.process(input);
						return true;
					}
				} finally {
					input.close();
				}
			}
		} catch (Exception e) {
            runLogger.error(LocalFtpClient.class, "LocalFtpClient to parse zip, and errmessge is " + e.getMessage());
		}
		return false;

	}
	
	/**
	 * 上传文件
	 * 
	 * @param path
	 *            上传到本地的目录
	 * @param fileName
	 *            文件名
	 * @param local
	 *            本地文件
	 * @return 上传到本地指定目录的文件名字,null表示失败
	 */
	public String uploadFile(String path, String fileName, InputStream local) throws MException{
		File parent = new File(localParentPath,path);
		if(!parent.exists()){
			parent.mkdirs();
		}
		int index = 1;
        int dot = fileName.lastIndexOf(".");
        if (dot <= 0) {
            dot = fileName.length();
        }
        DecimalFormat dFormat = new DecimalFormat("000");
        String offset = fileName.substring(0, dot);
        String tail = "";
        if (dot < fileName.length()) {
            tail = fileName.substring(dot);
        }
        String airName = offset + "_" + dFormat.format(index) + tail;
        while ((new File(airName)).exists()) {
            index++;
            airName = offset + "_" + dFormat.format(index) + tail;
        }
        File resultFile = new File(parent,airName);
        try {
        	FileOutputStream out = null;
        	try{
        		out = new FileOutputStream(resultFile);
        		Util.copyStream(local, out);
        		return airName;
        	}finally{
        		if(out != null){
        			out.flush();
        			out.close();
        		}
        	}
		} catch (IOException e) {
			MException mException = ExceptionHandler.handle(e,
                    "Error while copy resultfile to path " + path);
			if(mException != null)
			    throw mException;
		}
        return null;
	}
}
