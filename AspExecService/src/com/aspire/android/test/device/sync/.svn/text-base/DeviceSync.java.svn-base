/**
 * @(#) DeviceSync.java Created on 2012-6-14
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.device.sync;

import java.util.List;

import android.content.SharedPreferences;

import com.aspire.android.common.exception.MException;
import com.aspire.android.test.PreferencesManager;
import com.aspire.android.test.execute.ui.PrefsActivity;
import com.aspire.android.test.sync.pub.RemoteHttpClient;
import com.aspire.mgt.ats.tm.sync.HttpRequestTransmitter;
import com.aspire.mgt.ats.tm.sync.XmlResponseProcessor;
import com.aspire.mgt.ats.tm.sync.device.entity.DeviceAlarmRequest;
import com.aspire.mgt.ats.tm.sync.device.entity.DeviceAlarmResponse;
import com.aspire.mgt.ats.tm.sync.device.entity.DeviceInfo;
import com.aspire.mgt.ats.tm.sync.device.entity.DeviceInfoRequest;
import com.aspire.mgt.ats.tm.sync.device.entity.DeviceStatus;
import com.aspire.mgt.ats.tm.sync.device.entity.DeviceStatusRequest;

/**
 * The class <code>DeviceSync</code>包含设备注册和设备状态更新
 * 
 * @author likai
 * @version 1.0
 */
public class DeviceSync {

    protected static final String TAG = null;
    private HttpRequestTransmitter reqTransmitter;
    private XmlResponseProcessor processor;
    private SharedPreferences preferences;
    private PreferencesManager preferencesManager = PreferencesManager.getInstance();

    public DeviceSync() {
        this.reqTransmitter = new HttpRequestTransmitter(new RemoteHttpClient());
        this.preferences = preferencesManager.getPreferences();
    }

    /**
     * 注册设备信息
     * 
     * @param url
     * @param devInfoList
     * @param httpClient
     * @param devInfoRespParser
     */
    public void registerDeviceInfo(List<DeviceInfo> devInfoList, DeviceRegisterHandler devInfoHandler) {
        String url = preferences.getString(PrefsActivity.EQUIPMENT, null);
        DeviceInfoRequest request = new DeviceInfoRequest();
        request.setDevices(devInfoList);
        processor = new XmlResponseProcessor(devInfoHandler);
        reqTransmitter.send(url, request, processor);
    }

    /**
     * 设备状态更新
     * 
     * @param url
     * @param devStatusList
     * @param httpClient
     * @param devStatusRespParser
     */
    public void updateDeviceStatus(List<DeviceStatus> devStatusList, DeviceStatusHandler devStatusHandler) {
        String url = preferences.getString(PrefsActivity.HEARTBEAT, null);
        DeviceStatusRequest request = new DeviceStatusRequest();
        request.setDevices(devStatusList);
        processor = new XmlResponseProcessor(devStatusHandler);
        reqTransmitter.send(url, request, processor);
    }

    /**
     * 告警
     * 
     * @param code
     *            告警代码
     * @return message 告警消息
     * @throws MException
     */

    public DeviceAlarmResponse deviceAlarm(String code, String message) throws MException {
        String imei = preferences.getString(PrefsActivity.IMEI, null);
        String url = preferences.getString(PrefsActivity.ALARM, null);
        DeviceAlarmHandler alarmHandler = new DeviceAlarmHandler();
        processor = new XmlResponseProcessor(alarmHandler);
        DeviceAlarmRequest request = new DeviceAlarmRequest();
        request.setDevId(imei);
        request.setEventCode(code);
        request.setMessage(message);
        reqTransmitter.send(url, request, processor);
        return alarmHandler.getDeviceAlarmResponse();
    }
}
