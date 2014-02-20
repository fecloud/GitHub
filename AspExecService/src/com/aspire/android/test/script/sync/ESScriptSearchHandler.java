/**
 * @(#) TMScriptResquest.java Created on 2012-6-11
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.script.sync;

import com.aspire.android.test.log.RunLogger;
import com.aspire.android.test.script.service.IScriptService;
import com.aspire.mgt.ats.tm.sync.script.ScriptSearchHandler;
import com.aspire.mgt.ats.tm.sync.script.entity.QueryScriptResponse;

/**
 * The class <code>ESScriptSearchHandler</code>脚本更新查询响应处理
 * 
 * @author likai
 * 
 */
public class ESScriptSearchHandler extends ScriptSearchHandler {

    private RunLogger runLogger = RunLogger.getInstance();

    /**
     * 处理脚本提纲xml文件的handler
     */
    private ESScriptNoteDownloadHandler tmSNDownloadHandler;

    private IScriptService service;

    public void setService(IScriptService service) {
        this.service = service;
    }

    @Override
    public void handle(QueryScriptResponse responseParser) {
        if (!responseParser.getStatus().equals("0000")) {
            runLogger.debug(getClass(), "the response of query script is : status = " + responseParser.getStatus()
                    + "; message = " + responseParser.getMessage());
        } else if (responseParser.getUpdateFlag() == 0) {
            runLogger.debug(getClass(), "UpdateFlag is 0, so script need not to update");
        } else {
            runLogger.debug(getClass(), "the server's newest time for script is " + responseParser.getLastUpdateTime());
        }
        tmSNDownloadHandler = new ESScriptNoteDownloadHandler();
        tmSNDownloadHandler.setFtpClient(ftpClient);
        tmSNDownloadHandler.setFtpLocalPath(ftpLocalPath);
        tmSNDownloadHandler.setScriptRequest(scriptRequest);
        tmSNDownloadHandler.setSeparator(separator);
        tmSNDownloadHandler.setService(service);
        super.setSNDownloadHandler(tmSNDownloadHandler);
        super.handle(responseParser);
    }
}
