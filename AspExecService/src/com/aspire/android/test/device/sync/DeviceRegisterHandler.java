/**
 * @(#) DeviceInfoHandler.java Created on 2012-6-14
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.device.sync;

import java.util.HashMap;

import com.aspire.android.test.log.RunLogger;
import com.aspire.mgt.ats.tm.sync.AbstractMessageHandler;
import com.aspire.mgt.ats.tm.sync.device.entity.DeviceInfoResponse;


/**
 * The class <code>DeviceInfoHandler</code>设备注册响应处理
 *
 * @author likai
 * @version 1.0
 */
public class DeviceRegisterHandler extends AbstractMessageHandler<DeviceInfoResponse>{
	
	private RunLogger runLogger = RunLogger.getInstance();
	
	@Override
	public void handle(DeviceInfoResponse infoResponse) {
        runLogger.debug(getClass(), "response of registered device is : status = " + infoResponse.getStatus() + ", message = "+infoResponse.getMessage());
	}

    @Override
    public void handle(DeviceInfoResponse arg0, HashMap<String, Object> arg1) {
    }

}
