/**
 * @(#) TaskXmlProcessor.java Created on 2012-8-30
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.task.sync;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.aspire.android.test.execute.NameConstants;
import com.aspire.android.test.log.RunLogger;
import com.aspire.mgt.ats.tm.sync.AbstractMessageHandler;
import com.aspire.mgt.ats.tm.sync.XmlResponseProcessor;

/**
 * The class <code>TaskXmlProcessor</code>
 * 
 * @author likai
 * @version 1.0
 */
public class TaskXmlProcessor extends XmlResponseProcessor {

    private RunLogger runLogger = RunLogger.getInstance();
    /**
     * 保存taskxml文件的主目录
     */
    private String ftpLocalPath;

    public void setFtpLocalPath(String ftpLocalPath) {
        this.ftpLocalPath = ftpLocalPath;
    }

    public TaskXmlProcessor(AbstractMessageHandler<?> messageHandler) {
        super(messageHandler);
    }

    @Override
    public void process(InputStream inputStream) {
        int i = -1;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String content = null;
        try {
            try {
                while ((i = inputStream.read()) != -1) {
                    baos.write(i);
                }
                inputStream.reset();
                content = baos.toString();
            } finally {
                baos.flush();
                baos.close();
                baos = null;
            }
        } catch (IOException e1) {
            runLogger.debug(getClass(), "failed while change task inputstream to string, errmessage" + e1.getMessage());
        }
        runLogger.debug(getClass(), "change task inputstream to string, is" + content);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(NameConstants.TASK_UPDATE_STATUS, false);
        messageHandler.handleMessage(deserialize(inputStream), map);
        boolean taskUpdateStatus = (Boolean) map.get(NameConstants.TASK_UPDATE_STATUS);
        if (taskUpdateStatus) {
            String filePath = ftpLocalPath + NameConstants.BACKUP_ADDRESS + "/" + NameConstants.TASK_FILE_PATH;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String fileName = "taskquery" + dateFormat.format(new Date()) + ".xml";
            runLogger.debug(getClass(), "save task file, fileName is " + fileName);
            File taskXmlFile = new File(filePath, fileName);
            if (!taskXmlFile.getParentFile().exists())
                taskXmlFile.getParentFile().mkdirs();
            FileOutputStream out = null;
            try {
                try {
                    out = new FileOutputStream(taskXmlFile);
                    inputStream.reset();
                    int bytesRead = 0;
                    byte[] buffer = new byte[1024];
                    while ((bytesRead = inputStream.read(buffer, 0, 1024)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }
                } finally {
                    if (out != null) {
                        out.flush();
                        out.close();
                        out = null;
                    }
                }
            } catch (Exception e) {
                runLogger.error(getClass(), "save taskxmlfile error;" + e.getMessage());
            }
        } else {
            runLogger.debug(getClass(), "needn't to save task file");
        }
    }

}
