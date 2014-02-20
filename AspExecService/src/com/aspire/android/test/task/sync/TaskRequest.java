/**
 * @(#) TaskRequest.java Created on 2012-6-12
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.task.sync;

import com.aspire.android.test.task.service.ITaskService;
import com.aspire.mgt.ats.tm.sync.HttpRequestTransmitter;
import com.aspire.mgt.ats.tm.sync.IHTTPClient;
import com.aspire.mgt.ats.tm.sync.task.entity.TaskQueryRequest;

/**
 * The class <code>TaskRequest</code>
 * 
 * @author likai
 * @version 1.0
 */
public class TaskRequest {

    private HttpRequestTransmitter reqTransmitter;
    
    private TaskXmlProcessor respProcessor;
    
    private ITaskService service;

    public TaskRequest(ITaskService service){
    	this.service = service;
    }
	public void request(String ftpLocalPath, String httpServer, IHTTPClient httClient, String testMode, String companyCode,
			String provinceCode, String deviceType, String testTaskCode, String imei) {
        this.reqTransmitter = new HttpRequestTransmitter(httClient);
        TaskQueryHandler queryHandler = new TaskQueryHandler(service);
        queryHandler.setDeviceType(deviceType);
        this.respProcessor = new TaskXmlProcessor(queryHandler);
        respProcessor.setFtpLocalPath(ftpLocalPath);
		if (testTaskCode == null) {
            testTaskCode = "";
        }
		TaskQueryRequest request = new TaskQueryRequest();
        request.setImei(imei);
        request.setCompanyCode(companyCode);
        request.setDevType(deviceType);
        request.setProvCode(provinceCode);
        request.setTestMode(testMode);
        request.setTestTaskCode(testTaskCode);
        
        reqTransmitter.send(httpServer, request, respProcessor);
	}
}
