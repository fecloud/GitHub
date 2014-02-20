/**
 * @(#) TaskRequest.java Created on 2012-6-12
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.task;

import com.aspire.common.util.MobileManagerConstants;
import com.aspire.mgt.ats.tm.sync.HttpRequestTransmitter;
import com.aspire.mgt.ats.tm.sync.IHTTPClient;
import com.aspire.mgt.ats.tm.sync.XmlResponseProcessor;
import com.aspire.mgt.ats.tm.sync.task.entity.TaskQueryRequest;
import com.aspire.sqmp.mobilemanager.entity.Device;

/**
 * The class <code>TaskRequest</code>
 * 
 * @author likai
 * @version 1.0
 */
public class TaskRequest {

    private HttpRequestTransmitter reqTransmitter;
    
    private XmlResponseProcessor respProcessor;
    
	public void request(String url, IHTTPClient httClient, String companyCode, Device device) {
        this.reqTransmitter = new HttpRequestTransmitter(httClient);
        TaskQueryHandler queryHandler = new TaskQueryHandler();
        queryHandler.setDevice(device);
        this.respProcessor = new XmlResponseProcessor(queryHandler);
		TaskQueryRequest request = new TaskQueryRequest();
        request.setImei(device.getImei());
        request.setCompanyCode(companyCode);
        request.setDevType(device.getModel());
        request.setProvCode(device.getProvinceCode());
        request.setTestMode(MobileManagerConstants.TEST_MODE_INPHONE);
        request.setTestTaskCode("");
        
        reqTransmitter.send(url, request, respProcessor);
	}
}
