/**
 * @(#) FtpClient.java Created on 2012-5-23
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspire.common.exception.ExceptionHandler;
import com.aspire.mgt.ats.tm.sync.IFTPClient;
import com.aspire.mgt.ats.tm.sync.ResponseProcessor;
import com.aspire.mgt.ats.tm.sync.util.ftp.Client;
import com.aspire.mgt.ats.tm.sync.util.ftp.ClientConfig;
import com.aspire.mgt.ats.tm.sync.util.ftp.FFile;

/**
 * 
 * The class <code>RemoteFtpClient</code>
 * 
 * 
 * 
 * @author likai
 * 
 * @version 1.0
 */
public class FtpClient implements IFTPClient {

    protected Logger logger = LoggerFactory.getLogger(FtpClient.class);
	
	private Client client;

	private String separator = "/";

	public FtpClient() throws Exception {
		try {
			ClientConfig clientConfig = new ClientConfig("10.1.5.154", 21, "szzw",
					"szzw+888");
			client = new Client(clientConfig);
		} catch (Exception e) {
			ExceptionHandler.handle(e,"Error while encryptTxt");
		}
	}

	public FtpClient(ClientConfig clientConfig) throws Exception {
		client = new Client(clientConfig);
	}

	/**
	 * 下载ftp服务器上的特定的文件
	 */
	public boolean downloadFile(String path, String fileName, FILE_TYPE type,
			ResponseProcessor respProcessor) {
	    logger.info("-------------download file :" + fileName + "--------------");
	    path = replaceSeparator(path);
		try {
			if (client != null) {
				return client.downloadFile(
						path,
						fileName,
						type == null ? null : Client.FILE_TYPE.valueOf(type
								.name()), respProcessor);
			}
		} catch (Exception e) {
		    ExceptionHandler.handle(e,"while downloadFile, filename: " + fileName);
		}
		return false;
	}

	private String replaceSeparator(String str) {
		if (str == null || separator == null) {
			return str;
		}
		if (!"\\".equals(separator)) {
			str = str.replace("\\", separator);
		}
		if (!"/".equals(separator)) {
			str = str.replace("/", separator);
		}
		return str;
	}
	
	/**
	 * 下载业务指标
	 * 
	 * @param local
	 *            本地存储路径
	 * @param ftpPath
	 *            FTP文件目录
	 * @param lastName 
	 *            上次下载的文件名称
	 */
	public String download(String local, String ftpPath, String lastName) {
		try {
			File file = new File(local,ftpPath);
            if (!file.exists()) {
            	file.mkdirs();
            }
            ftpPath = replaceSeparator(ftpPath);
		    return downloadNewestFile(ftpPath, file.getAbsolutePath(), lastName);
		} catch (Exception e) {
		    ExceptionHandler.handle(e,"ftppath=" + ftpPath
							+ ",local=" + local);
		}
        return null; 
	}
	/**
	 * 下载给定工作目录的最新文件 
	 * 
	 * @param ftpPath
	 *            给定工作目录
	 * @param localPath
	 *            文件本地存储路径
	 * @param lastName 上次下载的文件名称
	 * @return 所下载的文件的名称
	 * @throws Exception
	 */
	public String downloadNewestFile(String ftpPath, String localPath, String lastName)
			throws Exception {
		Date lastUpdateTime = null;
		FFile newestFileInfo = client.getNewestFileInfo(ftpPath, lastUpdateTime);
		if (null == newestFileInfo || newestFileInfo.getFileName().equals(lastName)) {
			return null;
		}

		File fileDir = new File(localPath);
		// 如果该目录不存在则创建之
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}

		File newestFile = new File(localPath, newestFileInfo.getFileName());
		newestFile.createNewFile();
		FileOutputStream out = new FileOutputStream(newestFile, false);
		try {
			client.download(newestFileInfo, out,
					Client.FILE_TYPE.BINARY_FILE_TYPE);
		} finally {
			out.close();
		}

		return newestFile.getName();
	}

	/**
	 * 上传文件
	 * 
	 * @param path
	 *            工作目录
	 * @param fileName
	 *            文件名
	 * @param type
	 *            文件的类型
	 * @param local
	 *            本地文件
	 * @return FTP的名字,null表示失败
	 */
	public String uploadFile(String path, String fileName, FILE_TYPE type,
			InputStream local) {
		try {
		    path = replaceSeparator(path);
			if (client != null) {
				return client.uploadFile(
						path,
						fileName,
						type == null ? null : Client.FILE_TYPE.valueOf(type
								.name()), local);
			}
		} catch (Exception e) {
		    ExceptionHandler.handle(e,"fail to upload the file:" + path + "/" + fileName);
		}
		return null;

	}

	/**
	 * 关闭FTP连接
	 */
	public void close() {
		if (client != null) {
			try {
				client.close();
			} catch (Exception e) {
			    ExceptionHandler.handle(e,"fail to close the FTP");
			}
		}
	}
}
