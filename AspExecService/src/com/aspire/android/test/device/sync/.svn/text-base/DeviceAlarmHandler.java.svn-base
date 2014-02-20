/**
 * @(#) DeviceAlarmHandler.java Created on 2012-7-5
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.device.sync;

import java.util.HashMap;

import com.aspire.android.test.log.RunLogger;
import com.aspire.mgt.ats.tm.sync.AbstractMessageHandler;
import com.aspire.mgt.ats.tm.sync.device.entity.DeviceAlarmResponse;


/**
 * The class <code>DeviceAlarmHandler</code>设备告警
 *
 * @author likai
 * @version 1.0
 */
public class DeviceAlarmHandler extends AbstractMessageHandler<DeviceAlarmResponse> {
	
    private RunLogger runLogger = RunLogger.getInstance();
    
    private DeviceAlarmResponse deviceAlarmResponse;
    
	/**
     * Getter of deviceAlarmResponse
     * @return the deviceAlarmResponse
     */
    public DeviceAlarmResponse getDeviceAlarmResponse() {
        return deviceAlarmResponse;
    }

    @Override
	public void handle(DeviceAlarmResponse deviceAlarmResponse) {
        this.deviceAlarmResponse = deviceAlarmResponse;
	    runLogger.debug(getClass(), "the device alarm's response is : status = " + deviceAlarmResponse.getStatus()
                + "message = " + deviceAlarmResponse.getMessage());
	}

    @Override
    public void handle(DeviceAlarmResponse arg0, HashMap<String, Object> arg1) {
    }

}
