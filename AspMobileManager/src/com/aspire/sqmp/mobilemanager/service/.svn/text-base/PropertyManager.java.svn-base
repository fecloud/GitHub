/**
 * @(#) PreferenceManager.java Created on 2012-8-1
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.service;

import com.aspire.common.config.Config;
import com.aspire.common.config.ConfigManager;
import com.aspire.common.util.MobileManagerConstants;
import com.aspire.sqmp.mobilemanager.entity.Device;
import com.aspire.sqmp.mobilemanager.entity.MobileSharedFile;

/**
 * The class <code>PreferenceManager</code>
 *
 * @author likai
 * @version 1.0
 */
public class PropertyManager {

    /**
     * 用来发送给手机端
     */
    private MobileSharedFile mobileSharedFile = null;
    /**
     * 用于获取公共配置
     */
    private Config config;
    
    /**
     * 用于加载设备
     */
    private DeviceManager deviceManager;
    /**
     * 包含adbName的设备
     */
    private Device device;
    
    /**
     * Getter of mobileSharedFile
     * @return the mobileSharedFile
     */
    public MobileSharedFile getMobileSharedFile() {
        return mobileSharedFile;
    }

    public PropertyManager(String adbName){
        if(adbName != null){
            mobileSharedFile = new MobileSharedFile();
            config = ConfigManager.getConfig("global");
            deviceManager = DeviceManager.getInstance();
            for(Device dev : deviceManager.getDeviceList()){
                if(dev.getAdbDeviceName() != null && dev.getAdbDeviceName().equals(adbName)){
                    device = dev;
                    mobileSharedFile = new MobileSharedFile();
                    initialSharedPreference();
                }
            }
        }
    }
    /**
     * 将配置存放到传送的对象中
     */
    private void initialSharedPreference(){
        //private data
        mobileSharedFile.setDeviceName(device.getDeviceName());
        mobileSharedFile.setMobileNum(device.getMobileNum());
        mobileSharedFile.setModel(device.getModel());
        mobileSharedFile.setImei(device.getImei());
        mobileSharedFile.setIp(device.getIp());
        mobileSharedFile.setDataCollFreq(String.valueOf(device.getDataCollFreq()));
        mobileSharedFile.setSmsControlNum(device.getSmsControlNum());
        mobileSharedFile.setDataParentPath(device.getDataParentPath());
        mobileSharedFile.setProvinceCode(device.getProvinceCode());
        mobileSharedFile.setCityCode(device.getCityCode());
        mobileSharedFile.setResultUploadType(device.getResultUploadType());
        mobileSharedFile.setConnSwitch(device.isConnSwitch());
        
        //common data
        mobileSharedFile.setCompanyCode(config.get(MobileManagerConstants.COMPANY_CODE));
        mobileSharedFile.setTester(config.get(MobileManagerConstants.TESTER));
        //设备类型ftp接口
        mobileSharedFile.setDeviceTypeIp(config.get(MobileManagerConstants.DEVICE_TYPE_FTP_IP));
        mobileSharedFile.setDeviceTypePort(config.get(MobileManagerConstants.DEVICE_TYPE_FTP_PORT));
        mobileSharedFile.setDeviceTypePath(config.get(MobileManagerConstants.DEVICE_TYPE_FTP_PATH));
        mobileSharedFile.setDeviceTypeUser(config.get(MobileManagerConstants.DEVICE_TYPE_FTP_USER));
        mobileSharedFile.setDeviceTypePwd(config.get(MobileManagerConstants.DEVICE_TYPE_FTP_PWD));
        mobileSharedFile.setDeviceTypeInterval(config.get(MobileManagerConstants.DEVICE_TYPE_INTERVAL));
        //业务指标ftp接口
        mobileSharedFile.setServiceKeyIp(config.get(MobileManagerConstants.SERVICE_KEY_FTP_IP));
        mobileSharedFile.setServiceKeyPort(config.get(MobileManagerConstants.SERVICE_KEY_FTP_PORT));
        mobileSharedFile.setServiceKeyPath(config.get(MobileManagerConstants.SERVICE_KEY_FTP_PATH));
        mobileSharedFile.setServiceKeyUser(config.get(MobileManagerConstants.SERVICE_KEY_FTP_USER));
        mobileSharedFile.setServiceKeyPwd(config.get(MobileManagerConstants.SERVICE_KEY_FTP_PWD));
        mobileSharedFile.setServiceKeyInterval(config.get(MobileManagerConstants.SERVICE_KEY_INTERVAL));
        //拨测脚本ftp接口  
        mobileSharedFile.setTestScriptIp(config.get(MobileManagerConstants.TEST_SCRIPT_FTP_IP));
        mobileSharedFile.setTestScriptPort(config.get(MobileManagerConstants.TEST_SCRIPT_FTP_PORT));
        mobileSharedFile.setTestScriptPath(config.get(MobileManagerConstants.TEST_SCRIPT_FTP_PATH));
        mobileSharedFile.setTestScriptUser(config.get(MobileManagerConstants.TEST_SCRIPT_FTP_USER));
        mobileSharedFile.setTestScriptPwd(config.get(MobileManagerConstants.TEST_SCRIPT_FTP_PWD));
        mobileSharedFile.setTestScriptInterval(config.get(MobileManagerConstants.TEST_SCRIPT_INTERVAL));
        //拨测结果ftp接口
        mobileSharedFile.setTestResultIp(config.get(MobileManagerConstants.TEST_RESULT_FTP_IP));
        mobileSharedFile.setTestResultPort(config.get(MobileManagerConstants.TEST_RESULT_FTP_PORT));
        mobileSharedFile.setTestResultPath(config.get(MobileManagerConstants.TEST_RESULT_FTP_PATH));
        mobileSharedFile.setTestResultUser(config.get(MobileManagerConstants.TEST_RESULT_FTP_USER));
        mobileSharedFile.setTestResultPwd(config.get(MobileManagerConstants.TEST_RESULT_FTP_PWD));
        mobileSharedFile.setTestResultUploadInterval(config.get(MobileManagerConstants.TEST_RESULT_UPLOAD_INTERVAL));
        mobileSharedFile.setTestResultRespInterval(config.get(MobileManagerConstants.TEST_RESULT_RESP_INTERVAL));
        //版本升级ftp接口
        mobileSharedFile.setVersionIp(config.get(MobileManagerConstants.VERSION_FTP_IP));
        mobileSharedFile.setVersionPort(config.get(MobileManagerConstants.VERSION_FTP_PORT));
        mobileSharedFile.setVersionPath(config.get(MobileManagerConstants.VERSION_FTP_PATH));
        mobileSharedFile.setVersionUser(config.get(MobileManagerConstants.VERSION_FTP_USER));
        mobileSharedFile.setVersionPwd(config.get(MobileManagerConstants.VERSION_FTP_PWD));
        //拨测http相关接口
        mobileSharedFile.setTaskDownloadUrl(config.get(MobileManagerConstants.TASK_DOWNLOAD_URL));
        mobileSharedFile.setTaskDownloadUserName(config.get(MobileManagerConstants.TASK_DOWNLOAD_USER));
        mobileSharedFile.setTaskDownloadPwd(config.get(MobileManagerConstants.TASK_DOWNLOAD_PWD));
        mobileSharedFile.setScriptDownloadUrl(config.get(MobileManagerConstants.TEST_SCRIPT_QUERY_URL));
        mobileSharedFile.setScriptDownloadUserName(config.get(MobileManagerConstants.TEST_SCRIPT_QUERY_USER));
        mobileSharedFile.setScriptDownloadPwd(config.get(MobileManagerConstants.TEST_SCRIPT_QUERY_PWD));
        mobileSharedFile.setDeviceStatusUrl(config.get(MobileManagerConstants.DEVICE_STATUS_URL));
        mobileSharedFile.setDeviceStatusUserName(config.get(MobileManagerConstants.DEVICE_STATUS_USER));
        mobileSharedFile.setDeviceStatusPwd(config.get(MobileManagerConstants.DEVICE_STATUS_PWD));
        mobileSharedFile.setDeviceRegisterUrl(config.get(MobileManagerConstants.DEVICE_REGISTER_URL));
        mobileSharedFile.setDeviceRegisterUserName(config.get(MobileManagerConstants.DEVICE_REGISTER_USER));
        mobileSharedFile.setDeviceRegisterPwd(config.get(MobileManagerConstants.DEVICE_REGISTER_PWD));
        mobileSharedFile.setDeviceAlarmUrl(config.get(MobileManagerConstants.DEVICE_ALARM_URL));
        mobileSharedFile.setDeviceAlarmUserName(config.get(MobileManagerConstants.DEVICE_ALARM_USER));
        mobileSharedFile.setDeviceAlarmPwd(config.get(MobileManagerConstants.DEVICE_ALARM_PWD));
        mobileSharedFile.setPasswordUpdateUrl(config.get(MobileManagerConstants.PASSWORD_UPDATE_URL));
        mobileSharedFile.setPasswordUpdateUserName(config.get(MobileManagerConstants.PASSWORD_UPDATE_USER));
        mobileSharedFile.setPasswordUpdatePwd(config.get(MobileManagerConstants.PASSWORD_UPDATE_PWD));
        //各种间隔
        mobileSharedFile.setTaskDownloadInterval(config.get(MobileManagerConstants.TASK_DOWNLOAD_INTERVAL));
        mobileSharedFile.setDeviceStatusInterval(config.get(MobileManagerConstants.DEVICE_STATUS_INTERVAL));
        mobileSharedFile.setInquireUpdateEngineInterval(config.get(MobileManagerConstants.INQUIRE_UPDATE_ENGINE_INTERVAL));
        mobileSharedFile.setMtExecserviceInterval(config.get(MobileManagerConstants.MT_EXECSERVICE_INTERVAL));
    }
    /**
     * 将手机中配置的信息同步到pc端
     * @param mobileSharedFile
     */
    public Device initialDevice(MobileSharedFile mobileSharedFile){
        Device device = new Device();
        device.setDeviceName(mobileSharedFile.getDeviceName());
        device.setMobileNum(mobileSharedFile.getMobileNum());
        device.setModel(mobileSharedFile.getModel());
        device.setImei(mobileSharedFile.getImei());
        device.setIp(mobileSharedFile.getIp());
        device.setDataCollFreq(mobileSharedFile.getDataCollFreq());
        device.setSmsControlNum(mobileSharedFile.getSmsControlNum());
        device.setDataParentPath(mobileSharedFile.getDataParentPath());
        device.setProvinceCode(mobileSharedFile.getProvinceCode());
        device.setCityCode(mobileSharedFile.getCityCode());
        device.setResultUploadType(mobileSharedFile.getResultUploadType());
        device.setConnSwitch(mobileSharedFile.isConnSwitch());
        return device;
    }
}
