/**
 * @(#) DeviceStatusHandler.java Created on 2012-6-14
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.device.sync;

import java.util.HashMap;

import com.aspire.android.test.log.RunLogger;
import com.aspire.mgt.ats.tm.sync.AbstractMessageHandler;
import com.aspire.mgt.ats.tm.sync.device.entity.DeviceStatusResponse;

/**
 * The class <code>DeviceStatusHandler</code>设备状态更新响应处理
 * 
 * @author likai
 * @version 1.0
 */
public class DeviceStatusHandler extends AbstractMessageHandler<DeviceStatusResponse> {

    private RunLogger runLogger = RunLogger.getInstance();

    @Override
    public void handle(DeviceStatusResponse statusResponse) {
        runLogger.debug(getClass(), "response of changed device status is : status =" + statusResponse.getStatus()
                + ", message =" + statusResponse.getMessage());
    }

    @Override
    public void handle(DeviceStatusResponse arg0, HashMap<String, Object> arg1) {
    }

}
