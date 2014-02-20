/**
 * @(#) RemteDevice.java Created on 2012-7-16
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.service;

import java.io.IOException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspire.common.exception.MException;
import com.aspire.sqmp.mobilemanager.entity.Device;
import com.aspire.sqmp.mobilemanager.entity.MobileSharedFile;
import com.aspire.sqmp.mobilemanager.service.adb.DeviceAdbState;
import com.aspire.sqmp.mobilemanager.service.client.RemoteClient;
import com.aspire.sqmp.mobilemanager.service.request.AspResponse;
import com.aspire.sqmp.mobilemanager.service.request.GCaseRequest;
import com.aspire.sqmp.mobilemanager.service.request.GCaseResponse;
import com.aspire.sqmp.mobilemanager.service.request.GResultRequest;
import com.aspire.sqmp.mobilemanager.service.request.GResultResponse;
import com.aspire.sqmp.mobilemanager.service.request.GSettingRequest;
import com.aspire.sqmp.mobilemanager.service.request.GSettingResponse;
import com.aspire.sqmp.mobilemanager.service.request.GStoreInfoReqeust;
import com.aspire.sqmp.mobilemanager.service.request.GStoreInfoResponse;
import com.aspire.sqmp.mobilemanager.service.request.SCaseRequest;
import com.aspire.sqmp.mobilemanager.service.request.SPasswordRequest;
import com.aspire.sqmp.mobilemanager.service.request.SSettingRequest;
import com.aspire.sqmp.mobilemanager.service.request.STaskRequest;
import com.aspire.sqmp.mobilemanager.service.request.SServicekeyRequest;

/**
 * The class <code>RemteDevice</code>
 * 
 * @author linjunsui
 * @version 1.0
 */
public class RemoteDevice {
    /**
     * logger
     */
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(RemoteDevice.class);
    /**
     * adbDeviceName
     */
    private String adbDeviceName;
    /**
     * The state of device connecttion with adb
     */
    private DeviceAdbState deviceAdbState;
    /**
     * RemoteClient
     */
    private RemoteClient remoteClient;

    /**
     * Constructor
     * 
     * @param adbDeviceName
     *            the serial of device
     */
    public RemoteDevice(String adbDeviceName, DeviceAdbState deviceAdbState) {
        super();
        this.adbDeviceName = adbDeviceName;
        this.deviceAdbState = deviceAdbState;
        remoteClient = new RemoteClient(adbDeviceName);
    }

    /**
     * Destory of RemoteDevice
     */
    public void destory() {
        remoteClient.disConnect();
    }

    /**
     * RemoteDevice is connected
     */
    public boolean isConnected() {
        return remoteClient.isConnected();
    }

    /**
     * CheckConncet
     * 
     * @throws MException
     * @throws IOException
     */
    public boolean checkConnect() throws IOException, MException {
        return remoteClient.checkConnect();
    }

    /**
     * get config of device
     * 
     * @param device
     * @throws MException
     *             while error
     * @return
     */
    public HashMap<String, String> getConfig(Device device) throws MException {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * set config of device
     * 
     * @param device
     * @param config
     * @throws MException
     *             while error
     */
    public void setConfig(Device device) {
    }

    /**
     * Getter of adbDeviceState
     * 
     * @return the adbDeviceState
     */
    public DeviceAdbState getDeviceAdbState() {
        return deviceAdbState;
    }

    /**
     * Setter of adbDeviceState
     * 
     * @param adbDeviceState
     *            the adbDeviceState to set
     */
    public void setDeviceAdbState(DeviceAdbState deviceAdbState) {
        this.deviceAdbState = deviceAdbState;
    }

    /**
     * Is adbDeviceState online.
     * 
     * @return
     */
    public boolean isAdbStateOnline() {
        return this.deviceAdbState == DeviceAdbState.ONLINE;
    }

    /**
     * Getter of adbDeviceName
     * 
     * @return the adbDeviceName
     */
    public String getAdbDeviceName() {
        return adbDeviceName;
    }

    /**
     * Setter of adbDeviceName
     * 
     * @param adbDeviceName
     *            the adbDeviceName to set
     */
    public void setAdbDeviceName(String adbDeviceName) {
        this.adbDeviceName = adbDeviceName;
    }

    /** execute menthon */
    /**
     * 向设备同步配置
     * 
     * @throws MException
     * @throws IOException
     */
    public void syncSetSetting(MobileSharedFile mobileSharedFile) throws IOException, MException {
        if (mobileSharedFile == null)
            return;
        SSettingRequest request = new SSettingRequest();
        request.setMobileSharedFile(mobileSharedFile);
        final AspResponse response = remoteClient.sendForResponse(request);
        verifyResponse(response);
    }

    /**
     * 获取设备配置
     * 
     * @throws MException
     * @throws IOException
     */
    public MobileSharedFile syncGetSetting() throws IOException, MException {
        final AspResponse response = remoteClient.sendForResponse(new GSettingRequest());
        GSettingResponse settingResponse = (GSettingResponse)response;
        verifyResponse(settingResponse);
        return settingResponse.getMobileSharedFile();
    }

    /**
     * 向设备同步task
     * 
     * @throws MException
     * @throws IOException
     */
    public void syncSetTask() throws IOException, MException {
        final AspResponse response = remoteClient.sendForResponse(new STaskRequest());
        verifyResponse(response);
    }

    /**
     * 向设备同步Case
     * 
     * @throws MException
     * @throws IOException
     */
    public void syncSetCase() throws IOException, MException {
        final AspResponse response = remoteClient.sendForResponse(new SCaseRequest());
        verifyResponse(response);
    }

    /**
     * 获取设备脚本更新时间戳
     * 
     * @throws MException
     * @throws IOException
     */
    public String syncGetCase(int type) throws IOException, MException {
        GCaseRequest request = new GCaseRequest();
        request.setType(type);
        final AspResponse response = remoteClient.sendForResponse(request);
        GCaseResponse gCaseResponse = (GCaseResponse)response;
        verifyResponse(response);
        return gCaseResponse.getMobileScriptLastUpdateTime();
    }

    /**
     * 向设备同步业务指标
     * 
     * @throws MException
     * @throws IOException
     */
    public void syncSetSevicekey() throws IOException, MException {
        final AspResponse response = remoteClient.sendForResponse(new SServicekeyRequest());
        verifyResponse(response);
    }

    /**
     * 向设备同步密钥
     * 
     * @throws MException
     * @throws IOException
     */
    public void syncSetPassword() throws IOException, MException {
        final AspResponse response = remoteClient.sendForResponse(new SPasswordRequest());
        verifyResponse(response);
    }

    /**
     * 向设备获取结果
     * 
     * @throws MException
     * @throws IOException
     */
    public GResultResponse syncGetResult(String requestType) throws IOException, MException {
        GResultRequest request = new GResultRequest();
        request.setRequestType(requestType);
        final AspResponse response = remoteClient.sendForResponse(request);
        GResultResponse resultResponse = (GResultResponse)response;
        verifyResponse(response);
        return resultResponse;
    }

    /**
     * 获取sdcard根目录和空间大小
     * 
     * @throws MException
     * @throws IOException
     */
    public GStoreInfoResponse syncGetSdcardPath() throws IOException, MException {
        final AspResponse response = remoteClient.sendForResponse(new GStoreInfoReqeust());
        GStoreInfoResponse resultResponse = (GStoreInfoResponse)response;
        verifyResponse(response);
        return resultResponse;
    }

   
    /**
     * verify response
     * 
     * @param respone
     * @throws MException
     */
    private void verifyResponse(AspResponse response) throws MException {
        if (null == response)
            throw (new NullPointerException("Response message is null"));
        if (!response.isSuccess())
            throw new MException("Success to recive response  but there is an error, an error message:"
                    + response.getMsg());
    }

    /**
     * gettor of RemoteClient
     * 
     * @return
     */
    public RemoteClient getClient() {
        return remoteClient;
    }
}
