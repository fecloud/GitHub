/**
 * @(#) FtpClient.java Created on 2012-5-23
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.sync.pub;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import android.util.Log;

import com.aspire.android.common.exception.ExceptionHandler;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.execute.NameConstants;
import com.aspire.android.test.log.RunLogger;
import com.aspire.mgt.ats.tm.sync.IFTPClient;
import com.aspire.mgt.ats.tm.sync.ResponseProcessor;
import com.aspire.mgt.ats.tm.sync.util.ftp.Client;
import com.aspire.mgt.ats.tm.sync.util.ftp.ClientConfig;
import com.aspire.mgt.ats.tm.sync.util.ftp.FFile;
import com.aspire.mgt.ats.tm.sync.util.zip.Unzip;
import com.aspire.mgt.ats.tm.sync.util.zip.ZFile;

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
public class RemoteFtpClient implements IFTPClient {

    private RunLogger runLogger = RunLogger.getInstance();

    private Client client;

    private String separator = "/";

    public RemoteFtpClient(ClientConfig clientConfig) throws Exception {
        client = new Client(clientConfig);
    }

    /**
     * 下载ftp服务器上的特定的文件
     */
    public boolean downloadFile(String path, String fileName, FILE_TYPE type, ResponseProcessor respProcessor)
            throws Exception {
        runLogger.debug(RemoteFtpClient.class, "download ftp file start, and file name is " + fileName);
        fileName = replaceSeparator(fileName);
        if (client != null) {
            if (client.downloadFile(path, fileName, type == null ? null : Client.FILE_TYPE.valueOf(type.name()),
                    respProcessor)) {
                runLogger.debug(RemoteFtpClient.class, "download ftp file successfully, and file name is " + fileName);
                return true;
            } else {
                runLogger.error(RemoteFtpClient.class, "download " + fileName + ", but it not exist");
            }
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
     * 下载并解析文件
     * 
     * @param local
     *            本地存储路径
     * @param ftpPath
     *            FTP文件目录
     * @param parser
     *            解析文件的接口
     */
    public String download(String ftpLocalPath, String ftpPath, String lastFileName, ResponseProcessor parser)
            throws MException {
        File file = new File(ftpLocalPath, ftpPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return downloadByPath(ftpLocalPath, ftpPath, file.getAbsolutePath(), lastFileName, parser);
    }

    /**
     * 下载并解析指定目录下的文件
     * 
     * @param path
     *            FTP路径
     * @param local
     *            文件本地存储路径
     * @param parser
     *            Parser对象
     */
    private String downloadByPath(String ftpLocalPath, String ftpPath, String local, String lastFileName,
            ResponseProcessor parser) throws MException {
        ftpPath = replaceSeparator(ftpPath);
        File newestFile = downloadNewestFile(ftpPath, local, lastFileName);
        if (parser != null && newestFile != null) {
            runLogger.debug(getClass(),
                    "download the newest servicekey successfully, and file is " + newestFile.getName());
            parseFiles(ftpLocalPath, parser, newestFile);
            return newestFile.getName();
        }
        return null;
    }

    /**
     * 下载给定工作目录的最新文件
     * 
     * @param path
     *            给定工作目录
     * @param localPath
     *            文件本地存储路径
     * @return 所下载的文件
     * @throws Exception
     */
    public File downloadNewestFile(String ftpPath, String localPath, String lastFileName) throws MException {
        Date lastUpdateTime = null;
        FFile newestFileInfo = null;
        File newestFile = null;
        try {
            newestFileInfo = client.getNewestFileInfo(ftpPath, lastUpdateTime);
            if (null == newestFileInfo) {
                runLogger.debug(RemoteFtpClient.class, "the ftpserver don't has servicekey zipfile");
                return null;
            } else if (newestFileInfo.getFileName().equals(lastFileName)) {
                runLogger.debug(RemoteFtpClient.class,
                        "Don't need to update servicekeys, because the local's is same to ftpserver's");
                return null;
            }

            File fileDir = new File(localPath);
            // 如果该目录不存在则创建之
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }

            newestFile = new File(localPath, newestFileInfo.getFileName());
            newestFile.createNewFile();
            FileOutputStream out = new FileOutputStream(newestFile, false);
            try {
                client.download(newestFileInfo, out, Client.FILE_TYPE.BINARY_FILE_TYPE);
            } finally {
                out.close();
            }

        } catch (Exception e) {
            MException mException = ExceptionHandler.handle(e,
                    "Failed while download the newest servicekey file in ftpserver");
            if (mException != null)
                throw mException;
        }
        return newestFile;
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
    private void parseFiles(String ftpLocalPath, ResponseProcessor parser, File file) throws MException {
        if (file == null) {
            return;
        }
        try {
            if (file.getName().toLowerCase().endsWith(".zip")) {
                Unzip unzip = new Unzip(file);
                try {
                    List<ZFile> zFiles = unzip.listFiles();
                    if (zFiles == null) {
                        return;
                    }
                    ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
                    try {
                        for (ZFile zfile : zFiles) {
                            out.reset();
                            if (parser != null) {
                                unzip.readFile(zfile, out);
                                ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
                                out.close();
                                out = null;
                                try {
                                    parser.process(input);
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
                // 备份
                String backPath = ftpLocalPath + NameConstants.BACKUP_ADDRESS + "/"
                        + NameConstants.SERVICE_KEY_FILE_PATH;
                File backFile = new File(backPath, file.getName());
                if (backFile.exists())
                    backFile.delete();
                if (!backFile.getParentFile().exists())
                    backFile.getParentFile().mkdirs();
                file.renameTo(backFile);
                if (file.exists())
                    runLogger.debug(RemoteFtpClient.class, "Failed while rename service");
            } else {
                FileInputStream input = new FileInputStream(file);
                try {
                    if (parser != null) {
                        parser.process(input);
                    }
                } finally {
                    input.close();
                }
            }
        } catch (Exception e) {
            MException mException = ExceptionHandler.handle(e, "Failed while parse the file:" + file.getAbsolutePath());
            if (mException != null)
                throw mException;

        }

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
    public String uploadFile(String path, String fileName, FILE_TYPE type, InputStream local) throws MException {
        try {
            path = replaceSeparator(path);
            if (client != null) {
                return client.uploadForATS(path, fileName, type == null ? null : Client.FILE_TYPE.valueOf(type.name()),
                        local);
            }
        } catch (Exception e) {
            MException mException = ExceptionHandler.handle(e, "fail to upload the file:" + path + "/" + fileName + ";"
                    + e.getMessage());
            if (mException != null)
                throw mException;
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
                Log.e(RemoteFtpClient.class.getSimpleName(), "fail to close the FTP:" + e.getMessage());
            }
        }
    }
}
