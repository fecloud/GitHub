/**
 * @(#) TMScriptResquest.java Created on 2012-6-11
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.script;

import java.io.File;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspire.common.exception.ExceptionHandler;
import com.aspire.common.util.MobileManagerConstants;
import com.aspire.common.util.XmlUtil;
import com.aspire.mgt.ats.tm.sync.script.ScriptSearchHandler;
import com.aspire.mgt.ats.tm.sync.script.entity.QueryScriptResponse;

/**
 * The class <code>ESScriptSearchHandler</code>脚本更新查询响应处理
 * 
 * @author likai
 * 
 */
public class MScriptSearchHandler extends ScriptSearchHandler {

    protected Logger logger = LoggerFactory.getLogger(MScriptSearchHandler.class);
    /**
     * 处理脚本提纲xml文件的handler
     */
    private MScriptNoteDownloadHandler tmSNDownloadHandler;

    @Override
    public void handle(QueryScriptResponse responseParser) {

        tmSNDownloadHandler = new MScriptNoteDownloadHandler();
        tmSNDownloadHandler.setFtpClient(ftpClient);
        tmSNDownloadHandler.setFtpLocalPath(ftpLocalPath);
        tmSNDownloadHandler.setScriptRequest(scriptRequest);
        tmSNDownloadHandler.setSeparator(separator);
        super.setSNDownloadHandler(tmSNDownloadHandler);
        if (responseParser.getStatus().equals("0000") && responseParser.getUpdateFlag() == 1) {
            File xmlFile = new File(MobileManagerConstants.LOCAL_PARENT_PATH + "\\" + ftpPath + "\\" + scriptRequest.getDevType(),
                    MobileManagerConstants.SCRIPT_QUERY_FILE_NAME);
            if (!xmlFile.getParentFile().exists()) {
                xmlFile.getParentFile().mkdirs();
            }
            FileOutputStream out = null;

            try {
                try {
                    out = new FileOutputStream(xmlFile);
                    XmlUtil.serialize(responseParser, out);
                    out.flush();
                    out.close();
                    out = null;
                    logger.info("Saving caseInquireFile successfully");
                } finally {
                    if (out != null) {
                        out.flush();
                        out.close();
                        out = null;
                    }
                }
                super.handle(responseParser);
            } catch (Exception e) {
                ExceptionHandler.handle(e, "while save task file");
            }
        } else if (responseParser.getStatus().equals("0000") && responseParser.getUpdateFlag() == 0) {
            logger.info("Inquiring casefile successfully,but not need to update");
        } else if (!responseParser.getStatus().equals("0000")) {
            logger.error("Inquiring casefile failed:" + responseParser.getMessage());
        }
    }
}
