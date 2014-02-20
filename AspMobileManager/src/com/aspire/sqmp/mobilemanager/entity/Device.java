/**
 * @(#) Device.java Created on 2012-7-13
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * The class <code>Device</code>
 *
 * @author linjunsui
 * @version 1.0
 */
@XStreamAlias("device")
public class Device {
    
    /**
     * 保存存储设备相关文件的文件夹名称
     */
    @XStreamAlias("folderName")
    protected String folderName;

    /**
     * 手机的sdcard根目录
     */
    @XStreamAlias("sdcardPath")
    protected String sdcardPath;

    /**
     * devicename of the device
     */
    @XStreamAlias("deviceName")
    protected String deviceName;

    /**
     * mobileNum of the device
     */
    @XStreamAlias("mobileNum")
    protected String mobileNum;
    
    /**
     * adbname of the device
     */
    @XStreamAlias("adbName")
    protected String adbDeviceName;
    
    /**
     * model of the device
     */
    @XStreamAlias("model")
    protected String model;
    
    /**
     * imei of the device
     */
    @XStreamAlias("imei")
    protected String imei;
    
    /**
     * ip of the device
     */
    @XStreamAlias("ip")
    protected String ip;
     
    /**
     * 性能数据采集频率
     */
    @XStreamAlias("dataCollFreq")
    protected String dataCollFreq;
    
    /**
     * 短信控制电话号码
     */
    @XStreamAlias("smsControlNum")
    protected String smsControlNum;
    
    /**
     * 测试数据目录
     */
    @XStreamAlias("dataParentPath")
    protected String dataParentPath;
    
    /**
     * 省份编码
     */
    @XStreamAlias("provinceCode")
    protected String provinceCode;
    
    /**
     * 城市编码
     */
    @XStreamAlias("cityCode")
    protected String cityCode;
    
    /**
     * 拨测结果上传方式
     */
    @XStreamAlias("resultUploadType")
    protected String resultUploadType;
    
    /**
     * 连接开关
     */
    @XStreamAlias("connSwitch")
    protected boolean connSwitch;
    
    /**
     * PC从服务器最后更新任务的时间
     */
    @XStreamAlias("taskLastUpdateTime")
    protected String taskLastUpdateTime;

    /**
     * PC从同步任务到手机的最后时间
     */
    @XStreamAlias("taskLastSyncTime")
    protected String taskLastSyncTime;

    /**
     * PC端是否有任务更新
     */
    @XStreamAlias("taskHasUpdate")
    protected boolean taskHasUpdate;

    /**
     * PC从服务器最后更新脚本的时间
     */
    @XStreamAlias("scriptLastUpdateTime")
    protected String scriptLastUpdateTime;

    /**
     * PC同步脚本到手机端的最后时间
     */
    @XStreamAlias("scriptLastSyncTime")
    protected String scriptLastSyncTime;

    /**
     * PC是否有脚本更新
     */
    @XStreamAlias("scriptHasUpdate")
    protected boolean scriptHasUpdate;

    /**
     * PC端最后上传拨测结果的时间
     */
    @XStreamAlias("resultLastUploadTime")
    protected String resultLastUploadTime;

    /**
     * PC从手机端同步来拨测结果的最后时间
     */
    @XStreamAlias("resultLastSyncTime")
    protected String resultLastSyncTime;

    /**
     * PC端是否由结果需要上传
     */
    @XStreamAlias("resultHasUpload")
    protected boolean resultHasUpload;

    /**
     * PC端最后更新脚本的时间戳
     */
    @XStreamAlias("pCScriptLastUpdateTime")
    protected String pCScriptLastUpdateTime; 

    /**
     * 手机端最后更新脚本的时间戳
     */
    @XStreamAlias("mobileScriptLastUpdateTime")
    protected String mobileScriptLastUpdateTime; 
    /**
     * Constructor
     */
    public Device() {
        super();
    }

    /**
     * Getter of fileName
     * @return the fileName
     */
    public String getFolderName() {
        return folderName;
    }

    /**
     * Setter of fileName
     * @param fileName the fileName to set
     */
    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    /**
     * Getter of sdcardPath
     * @return the sdcardPath
     */
    public String getSdcardPath() {
        return sdcardPath;
    }

    /**
     * Setter of sdcardPath
     * @param sdcardPath the sdcardPath to set
     */
    public void setSdcardPath(String sdcardPath) {
        this.sdcardPath = sdcardPath;
    }

    /**
     * Getter of deviceName
     * @return the deviceName
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * Setter of deviceName
     * @param deviceName the deviceName to set
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    /**
     * Getter of mobileNum
     * @return the mobileNum
     */
    public String getMobileNum() {
        return mobileNum;
    }

    /**
     * Setter of mobileNum
     * @param mobileNum the mobileNum to set
     */
    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    /**
     * Getter of adbDeviceName
     * @return the adbDeviceName
     */
    public String getAdbDeviceName() {
        return adbDeviceName;
    }

    /**
     * Setter of adbDeviceName
     * @param adbDeviceName the adbDeviceName to set
     */
    public void setAdbDeviceName(String adbDeviceName) {
        this.adbDeviceName = adbDeviceName;
    }

    /**
     * Getter of model
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * Setter of model
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Getter of imei
     * @return the imei
     */
    public String getImei() {
        return imei;
    }

    /**
     * Setter of imei
     * @param imei the imei to set
     */
    public void setImei(String imei) {
        this.imei = imei;
    }

    /**
     * Getter of ip
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * Setter of ip
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * Getter of dataCollFreq
     * @return the dataCollFreq
     */
    public String getDataCollFreq() {
        return dataCollFreq;
    }

    /**
     * Setter of dataCollFreq
     * @param dataCollFreq the dataCollFreq to set
     */
    public void setDataCollFreq(String dataCollFreq) {
        this.dataCollFreq = dataCollFreq;
    }

    /**
     * Getter of smsControlNum
     * @return the smsControlNum
     */
    public String getSmsControlNum() {
        return smsControlNum;
    }

    /**
     * Setter of smsControlNum
     * @param smsControlNum the smsControlNum to set
     */
    public void setSmsControlNum(String smsControlNum) {
        this.smsControlNum = smsControlNum;
    }

    /**
     * Getter of dataParentPath
     * @return the dataParentPath
     */
    public String getDataParentPath() {
        return dataParentPath;
    }

    /**
     * Setter of dataParentPath
     * @param dataParentPath the dataParentPath to set
     */
    public void setDataParentPath(String dataParentPath) {
        this.dataParentPath = dataParentPath;
    }

    /**
     * Getter of provinceCode
     * @return the provinceCode
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * Getter of cityCode
     * @return the cityCode
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * Setter of provinceCode
     * @param provinceCode the provinceCode to set
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    /**
     * Setter of cityCode
     * @param cityCode the cityCode to set
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * Getter of resultUploadType
     * @return the resultUploadType
     */
    public String getResultUploadType() {
        return resultUploadType;
    }

    /**
     * Setter of resultUploadType
     * @param resultUploadType the resultUploadType to set
     */
    public void setResultUploadType(String resultUploadType) {
        this.resultUploadType = resultUploadType;
    }

    /**
     * Getter of connSwitch
     * @return the connSwitch
     */
    public boolean isConnSwitch() {
        return connSwitch;
    }

    /**
     * Setter of connSwitch
     * @param connSwitch the connSwitch to set
     */
    public void setConnSwitch(boolean connSwitch) {
        this.connSwitch = connSwitch;
    }

    /**
     * Getter of taskLastUpdateTime
     * @return the taskLastUpdateTime
     */
    public String getTaskLastUpdateTime() {
        return taskLastUpdateTime;
    }

    /**
     * Getter of taskLastSyncTime
     * @return the taskLastSyncTime
     */
    public String getTaskLastSyncTime() {
        return taskLastSyncTime;
    }

    /**
     * Getter of taskHasUpdate
     * @return the taskHasUpdate
     */
    public boolean isTaskHasUpdate() {
        return taskHasUpdate;
    }

    /**
     * Getter of scriptLastUpdateTime
     * @return the scriptLastUpdateTime
     */
    public String getScriptLastUpdateTime() {
        return scriptLastUpdateTime;
    }

    /**
     * Getter of scriptLastSyncTime
     * @return the scriptLastSyncTime
     */
    public String getScriptLastSyncTime() {
        return scriptLastSyncTime;
    }

    /**
     * Getter of scriptHasUpdate
     * @return the scriptHasUpdate
     */
    public boolean isScriptHasUpdate() {
        return scriptHasUpdate;
    }

    /**
     * Getter of resultLastUploadTime
     * @return the resultLastUploadTime
     */
    public String getResultLastUploadTime() {
        return resultLastUploadTime;
    }

    /**
     * Getter of resultLastSyncTime
     * @return the resultLastSyncTime
     */
    public String getResultLastSyncTime() {
        return resultLastSyncTime;
    }

    /**
     * Getter of resultHasUpload
     * @return the resultHasUpload
     */
    public boolean isResultHasUpload() {
        return resultHasUpload;
    }

    /**
     * Setter of taskLastUpdateTime
     * @param taskLastUpdateTime the taskLastUpdateTime to set
     */
    public void setTaskLastUpdateTime(String taskLastUpdateTime) {
        this.taskLastUpdateTime = taskLastUpdateTime;
    }

    /**
     * Setter of taskLastSyncTime
     * @param taskLastSyncTime the taskLastSyncTime to set
     */
    public void setTaskLastSyncTime(String taskLastSyncTime) {
        this.taskLastSyncTime = taskLastSyncTime;
    }

    /**
     * Setter of taskHasUpdate
     * @param taskHasUpdate the taskHasUpdate to set
     */
    public void setTaskHasUpdate(boolean taskHasUpdate) {
        this.taskHasUpdate = taskHasUpdate;
    }

    /**
     * Setter of scriptLastUpdateTime
     * @param scriptLastUpdateTime the scriptLastUpdateTime to set
     */
    public void setScriptLastUpdateTime(String scriptLastUpdateTime) {
        this.scriptLastUpdateTime = scriptLastUpdateTime;
    }

    /**
     * Setter of scriptLastSyncTime
     * @param scriptLastSyncTime the scriptLastSyncTime to set
     */
    public void setScriptLastSyncTime(String scriptLastSyncTime) {
        this.scriptLastSyncTime = scriptLastSyncTime;
    }

    /**
     * Setter of scriptHasUpdate
     * @param scriptHasUpdate the scriptHasUpdate to set
     */
    public void setScriptHasUpdate(boolean scriptHasUpdate) {
        this.scriptHasUpdate = scriptHasUpdate;
    }

    /**
     * Setter of resultLastUploadTime
     * @param resultLastUploadTime the resultLastUploadTime to set
     */
    public void setResultLastUploadTime(String resultLastUploadTime) {
        this.resultLastUploadTime = resultLastUploadTime;
    }

    /**
     * Setter of resultLastSyncTime
     * @param resultLastSyncTime the resultLastSyncTime to set
     */
    public void setResultLastSyncTime(String resultLastSyncTime) {
        this.resultLastSyncTime = resultLastSyncTime;
    }

    /**
     * Setter of resultHasUpload
     * @param resultHasUpload the resultHasUpload to set
     */
    public void setResultHasUpload(boolean resultHasUpload) {
        this.resultHasUpload = resultHasUpload;
    }

    /**
     * Getter of pCScriptLastUpdateTime
     * @return the pCScriptLastUpdateTime
     */
    public String getpCScriptLastUpdateTime() {
        return pCScriptLastUpdateTime;
    }

    /**
     * Getter of mobileScriptLastUpdateTime
     * @return the mobileScriptLastUpdateTime
     */
    public String getMobileScriptLastUpdateTime() {
        return mobileScriptLastUpdateTime;
    }

    /**
     * Setter of pCScriptLastUpdateTime
     * @param pCScriptLastUpdateTime the pCScriptLastUpdateTime to set
     */
    public void setpCScriptLastUpdateTime(String pCScriptLastUpdateTime) {
        this.pCScriptLastUpdateTime = pCScriptLastUpdateTime;
    }

    /**
     * Setter of mobileScriptLastUpdateTime
     * @param mobileScriptLastUpdateTime the mobileScriptLastUpdateTime to set
     */
    public void setMobileScriptLastUpdateTime(String mobileScriptLastUpdateTime) {
        this.mobileScriptLastUpdateTime = mobileScriptLastUpdateTime;
    }
}
