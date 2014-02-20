/**
 * @(#) ResultResponseHandler.java Created on 2012-6-29
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.result.sync;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.net.io.Util;

import android.os.Environment;

import com.aspire.android.common.exception.ExceptionHandler;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.log.RunLogger;
import com.aspire.android.test.task.entity.Upload;
import com.aspire.android.test.task.service.ITaskService;
import com.aspire.mgt.ats.tm.sync.AbstractFileHandler;
import com.aspire.mgt.ats.tm.sync.util.zip.Unzip;

/**
 * The class <code>ResultResponseHandler</code>
 * 
 * @author likai
 * @version 1.0
 */
public class ResultResponseHandler extends AbstractFileHandler {

    private RunLogger runLogger = RunLogger.getInstance();

    private File responseFile;

    private ITaskService taskService;

    private Upload upload;

    public ResultResponseHandler(ITaskService taskService) {
        this.taskService = taskService;
    }

    public void setResponseFile(File responseFile) {
        this.responseFile = responseFile;
    }

    public void setUpload(Upload upload) {
        this.upload = upload;
    }

    @Override
    public void handle(InputStream input) {
        try {
            FileOutputStream out = new FileOutputStream(responseFile);
            try {
                Util.copyStream(input, out);
                runLogger.debug(getClass(),
                        "save result response file successfully, and file is" + responseFile.getAbsolutePath());
                out.flush();
                out.close();
                out = null;
            } finally {
                if (out != null) {
                    out.flush();
                    out.close();
                    out = null;
                }
            }

        } catch (Exception e) {
            runLogger.error(getClass(),
                    "failed while save result response file, the file is" + responseFile.getAbsolutePath()
                            + ", and errmessage is " + e.getMessage());
            return;
        }
        try {
            int status = disposeResponse();
            if (status == 0) {
                upload.setStatus(0);
            } else {
                upload.setStatus(status == 1 ? 1 : 2);
            }
            upload.setUpDate(new Date());
            upload.setResponseFile(responseFile.getAbsolutePath());
            taskService.updateUpload(upload);
            runLogger.debug(getClass(),
                    "update info in db for the result response file, " + responseFile.getAbsolutePath());
        } catch (MException e) {
            runLogger.error(getClass(),
                    "failed while parse and update info in db for result response, the file is" + responseFile.getAbsolutePath()
                            + ", and errmessage is " + e.getMessage());
        }
    }

    /**
     * 
     * 得到返回结果的内容，去判断结果是否正确
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    private int disposeResponse() throws MException {
        String pathName = Environment.getExternalStorageDirectory() + File.separator
                + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        unZipResultResp(responseFile, pathName);

        File file = new File(pathName);
        File[] files = file.listFiles();
        String atsPath = null;
        if (files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].getAbsolutePath().endsWith(".txt")) {
                    atsPath = files[i].getAbsolutePath();
                }
            }
        }
        int line = 0;
        if (atsPath != null) {
            BufferedReader reader = null;
            try {

                try {
                    reader = new BufferedReader(new FileReader(atsPath));
                    String tempString = null;
                    // 一次读入一行，直到读入null为文件结束
                    while ((tempString = reader.readLine()) != null) {
                        line++;
                        // 显示行号
                        runLogger.debug(getClass(), " the respfile 's line : " + line + "; content is " + tempString);
                    }
                    reader.close();
                    reader = null;
                } finally {
                    if (reader != null) {
                        reader.close();
                        reader = null;
                    }
                }
            } catch (Exception e) {
                runLogger.error(getClass(), "failed while parse respfile, the file is " + responseFile.getAbsolutePath()
                        + "and the errmessage is " + e.getMessage());
                MException mException = ExceptionHandler.handle(e, "failed while parse respfile, the file is " + responseFile.getAbsolutePath());
                if(mException != null){
                    throw mException;
                }
            }
        }
        // 删除的文件目录
        deleteDir(file);

        return line;
    }

    private void unZipResultResp(File f, String path)  throws MException {
        runLogger.debug(getClass(), "unzip respfile start, the file is " + f.getAbsolutePath());
        File parent = new File(path);
        if (!parent.exists()) {
            parent.mkdirs();
        }
        try {
            Unzip unzip = new Unzip(f, "UTF-8");
            try {
                unzip.unZipByPath(path);
                runLogger.debug(getClass(), "unzip respfile finish, the file is " + f.getAbsolutePath());
            } finally {
                unzip.close();
            }
        } catch (Exception e) {
            runLogger.error(getClass(), "failed while unzip respfile, the file is " + f.getAbsolutePath()
                    + "and the errmessage is " + e.getMessage());
            MException mException = ExceptionHandler.handle(e, "failed while unzip respfile, the file is " + f.getAbsolutePath());
            if(mException != null){
                throw mException;
            }
            
        }
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * 
     * @param dir
     *            将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful. If a deletion fails, the method stops attempting
     *         to delete and returns "false".
     */
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            // 递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
}
