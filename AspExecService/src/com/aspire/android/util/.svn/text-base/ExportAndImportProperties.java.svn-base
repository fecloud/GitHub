/**
 * @(#) ExportAndImportProperties.java Created on 2012-9-6
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.widget.Toast;

import com.aspire.android.common.exception.ExceptionHandler;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.application.ExecApplication;
import com.aspire.android.test.execute.R;
import com.aspire.android.test.execute.entity.FtpInterfaceProperties;
import com.aspire.android.test.execute.entity.FtpInterfacePropertiesList;
import com.aspire.android.test.execute.entity.MobileProperties;
import com.aspire.android.test.execute.entity.Settings;
import com.aspire.android.test.execute.entity.SystemProperties;
import com.aspire.android.test.execute.entity.HttpInterfaceProperties;
import com.aspire.android.test.execute.ui.PrefsActivity;
import com.aspire.android.test.log.RunLogger;
import com.aspire.common.util.XmlUtil;

/**
 * The class <code>ExportAndImportProperties</code>
 *
 * @author likai
 * @version 1.0
 */
public class ExportAndImportProperties {

    private RunLogger runLogger = RunLogger.getInstance();
    /**
     * 导入导出配置文件的路径
     */
    private String filePath = null;
    
    private SharedPreferences preferences;
    
    public ExportAndImportProperties(SharedPreferences preferences){
        filePath = Environment.getExternalStorageDirectory() + "/com.aspire.android.test.execute.xml";
        this.preferences = preferences;
    }
    
    /**
     * 到处配置信息到sdcard中，文件名问com.aspire.android.test.execute.xml
     * @throws MException
     */
    public void exportProperties() throws MException{
        Settings settings = new Settings();
        MobileProperties mobileProperties = new MobileProperties();
        SystemProperties systemProperties = new SystemProperties();
        HttpInterfaceProperties httpInterfaces = new HttpInterfaceProperties();
        FtpInterfacePropertiesList ftpInterfaces = new FtpInterfacePropertiesList();
        FtpInterfaceProperties serviceKeyFtp = new FtpInterfaceProperties();
        FtpInterfaceProperties testScriptFtp = new FtpInterfaceProperties();
        FtpInterfaceProperties testResultFtp = new FtpInterfaceProperties();
        FtpInterfaceProperties versiontFtp = new FtpInterfaceProperties();
        String ftpDataTimeOut = null;
        //设置手机本身属性
        mobileProperties.setDeviceName(preferences.getString(PrefsActivity.DEVIC_NAME, ""));
        mobileProperties.setDeviceType(preferences.getString(PrefsActivity.TERMINAL,""));
        mobileProperties.setMobileNum(preferences.getString(PrefsActivity.SET_PHONE_NUM, ""));
        mobileProperties.setImei(preferences.getString(PrefsActivity.IMEI, ""));
        mobileProperties.setIp(preferences.getString(PrefsActivity.IP, ""));
        mobileProperties.setProvinceCode(preferences.getString(PrefsActivity.PROVINCE, ""));
        mobileProperties.setCityCode(preferences.getString(PrefsActivity.CITY, ""));
        mobileProperties.setCompanyCode(preferences.getString(PrefsActivity.COMPANY, ""));
        mobileProperties.setTester(preferences.getString(PrefsActivity.TEST_PEOPLE, ""));
        //设置系统参数
        systemProperties.setConnectSwitch(preferences.getBoolean(PrefsActivity.DATA_GET_SWITCH, true));
        systemProperties.setUploadResultType(preferences.getString(PrefsActivity.UPLOADS_TYPE, ""));
        systemProperties.setDataCollectionFreq(preferences.getString(PrefsActivity.COLLECTION_FREQUENCY, ""));
        systemProperties.setDataParentPath(preferences.getString(PrefsActivity.SET_SAVE_ADDRESS, ""));
        systemProperties.setSmsControlNum(preferences.getString(PrefsActivity.SMS_CONTROAL_NUMBER, ""));
        //设置拨测接口
        httpInterfaces.setTestScriptQueryUrl(preferences.getString(PrefsActivity.SCRIPT_DOWNLOAD_URL, ""));
        httpInterfaces.setDeviceRegisterUrl(preferences.getString(PrefsActivity.EQUIPMENT, ""));
        httpInterfaces.setDeviceStatusUrl(preferences.getString(PrefsActivity.HEARTBEAT, ""));
        httpInterfaces.setDeviceStatusInterval(preferences.getString(PrefsActivity.DEVICE_TIME_INTERVAL, ""));
        httpInterfaces.setDeviceAlarmUrl(preferences.getString(PrefsActivity.ALARM, ""));
        httpInterfaces.setTaskDownloadUrl(preferences.getString(PrefsActivity.TASK_DOWNLOAD_URL, ""));
        httpInterfaces.setTaskDownloadInterval(preferences.getString(PrefsActivity.TASK_DOWNLOAD_TIME, ""));
        httpInterfaces.setPwdDownloadUrl(preferences.getString(PrefsActivity.PWD_UPDATE_URL, ""));
        httpInterfaces.setPwdDownloadUserName(preferences.getString(PrefsActivity.PWD_UPDATE_USERNAME, ""));
        httpInterfaces.setPwdDownloadPwd(preferences.getString(PrefsActivity.PWD_UPDATE_PWD, ""));
        //设置业务指标的ftp属性
        serviceKeyFtp.setFtpIp(preferences.getString(PrefsActivity.SERVICE_IP_TWO, ""));
        serviceKeyFtp.setFtpPort(preferences.getString(PrefsActivity.SERVICE_PORT_TWO, ""));
        serviceKeyFtp.setFtpPath(preferences.getString(PrefsActivity.SERVICE_PATH_TWO, ""));
        serviceKeyFtp.setFtpUserName(preferences.getString(PrefsActivity.SERVICE_USERNAME_TWO, ""));
        serviceKeyFtp.setFtpPwd(preferences.getString(PrefsActivity.SERVICE_PASSWORD_TWO, ""));
        //设置拨测脚本的ftp属性
        testScriptFtp.setFtpIp(preferences.getString(PrefsActivity.SERVICE_IP_THREE, ""));
        testScriptFtp.setFtpPort(preferences.getString(PrefsActivity.SERVICE_PORT_THREE, ""));
        testScriptFtp.setFtpPath(preferences.getString(PrefsActivity.SERVICE_PATH_THREE, ""));
        testScriptFtp.setFtpUserName(preferences.getString(PrefsActivity.SERVICE_USERNAME_THREE, ""));
        testScriptFtp.setFtpPwd(preferences.getString(PrefsActivity.SERVICE_PASSWORD_THREE, ""));
        testScriptFtp.setFtpDownloadInterval(preferences.getString(PrefsActivity.SCRIPT_DOWNLOAD_TIME, ""));
        //设置拨测结果的ftp属性
        testResultFtp.setFtpIp(preferences.getString(PrefsActivity.SERVICE_IP_FOUR, ""));
        testResultFtp.setFtpPort(preferences.getString(PrefsActivity.SERVICE_PORT_FOUR, ""));
        testResultFtp.setFtpPath(preferences.getString(PrefsActivity.SERVICE_PATH_FOUR, ""));
        testResultFtp.setFtpUserName(preferences.getString(PrefsActivity.SERVICE_USERNAME_FOUR, ""));
        testResultFtp.setFtpPwd(preferences.getString(PrefsActivity.SERVICE_PASSWORD_FOUR, ""));
        testResultFtp.setFtpDownloadInterval(preferences.getString(PrefsActivity.TEST_RESULT_RESP_INTERVAL, ""));
        testResultFtp.setFtpUploadInterval(preferences.getString(PrefsActivity.TEST_RESULT_UPLOAD_INTERVAL, ""));
        
        ftpDataTimeOut = preferences.getString(PrefsActivity.FTP_DATA_TIMEOUT, "");
        //设置设备更新的ftp属性
//        versiontFtp.setFtpIp(preferences.getString(PrefsActivity.SERVICE_IP_ONE, ""));
//        versiontFtp.setFtpPort(preferences.getString(PrefsActivity.SERVICE_PORT_ONE, ""));
//        versiontFtp.setFtpPath(preferences.getString(PrefsActivity.SERVICE_PATH_ONE, ""));
//        versiontFtp.setFtpUserName(preferences.getString(PrefsActivity.SERVICE_USERNAME_ONE, ""));
//        versiontFtp.setFtpPwd(preferences.getString(PrefsActivity.SERVICE_PASSWORD_ONE, ""));
        
        ftpInterfaces.setServiceKeysFtpProperties(serviceKeyFtp);
        ftpInterfaces.setTestResultFtpProperties(testResultFtp);
        ftpInterfaces.setTestScriptFtpProperties(testScriptFtp);
        ftpInterfaces.setVersionFtpProperties(versiontFtp);
        ftpInterfaces.setFtpDataTimeOut(ftpDataTimeOut);
        settings.setMobileProperties(mobileProperties);
        settings.setSystemProperties(systemProperties);
        settings.setHttpInterfaces(httpInterfaces);
        settings.setFtpInterfaces(ftpInterfaces);
        File xmlFile = new File(filePath);
        FileOutputStream out = null;
        try {
            try{
                out = new FileOutputStream(xmlFile);
                XmlUtil.serialize(settings, out);
                out.flush();
                out.close();
                out = null;
            }finally{
                if(out != null){
                    out.flush();
                    out.close();
                    out = null;
                }
            }
        } catch (Exception e) {
            MException mexception = ExceptionHandler.handle(e, "export property xmlFile");
            if (mexception != null) {
                throw mexception;
            }
        }
    }
    
    public void importProperties() throws MException{
        String saveParentPath = preferences.getString(PrefsActivity.SET_SAVE_ADDRESS, "/aspire/es/");
        Settings settings = new Settings();
        MobileProperties mobileProperties = new MobileProperties();
        SystemProperties systemProperties = new SystemProperties();
        HttpInterfaceProperties httpInterfaces = new HttpInterfaceProperties();
        FtpInterfacePropertiesList ftpInterfaces = new FtpInterfacePropertiesList();
        FtpInterfaceProperties serviceKeyFtp = new FtpInterfaceProperties();
        FtpInterfaceProperties testScriptFtp = new FtpInterfaceProperties();
        FtpInterfaceProperties testResultFtp = new FtpInterfaceProperties();
        String ftpDataTimeOut = null;
        File xmlFile = new File(filePath);
        if(!xmlFile.exists()){
            Toast.makeText(ExecApplication.instance().getBaseContext(), R.string.import_nofile_error, Toast.LENGTH_LONG).show();
            throw new MException("propertyfile is not found"); 
        }
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(xmlFile);
            settings = (Settings)XmlUtil.deSerialize(Settings.class, inputStream);
        } catch (FileNotFoundException e) {
            MException mexception = ExceptionHandler.handle(e, "import property xmlFile");
            if (mexception != null) {
                throw mexception;
            }
        }
        mobileProperties = settings.getMobileProperties();
        systemProperties = settings.getSystemProperties();
        httpInterfaces = settings.getHttpInterfaces();
        ftpInterfaces = settings.getFtpInterfaces();
        serviceKeyFtp = ftpInterfaces.getServiceKeysFtpProperties();
        testScriptFtp = ftpInterfaces.getTestScriptFtpProperties();
        testResultFtp = ftpInterfaces.getTestResultFtpProperties();
        ftpDataTimeOut = ftpInterfaces.getFtpDataTimeOut();
        Editor editor = preferences.edit();
        //设置本级信息
        editor.putString(PrefsActivity.DEVIC_NAME, mobileProperties.getDeviceName());
        editor.putString(PrefsActivity.SET_PHONE_NUM, mobileProperties.getMobileNum());
        editor.putString(PrefsActivity.TERMINAL, mobileProperties.getDeviceType());
        editor.putString(PrefsActivity.IMEI, mobileProperties.getImei());
        editor.putString(PrefsActivity.IP, mobileProperties.getIp());
        editor.putString(PrefsActivity.PROVINCE, mobileProperties.getProvinceCode());
        editor.putString(PrefsActivity.CITY, mobileProperties.getCityCode());
        editor.putString(PrefsActivity.DEVIC_NAME, mobileProperties.getDeviceName());
        editor.putString(PrefsActivity.COMPANY, mobileProperties.getCompanyCode());
        editor.putString(PrefsActivity.TEST_PEOPLE, mobileProperties.getTester());
        //设置系统参数
        editor.putBoolean(PrefsActivity.DATA_GET_SWITCH, systemProperties.isConnectSwitch());
        editor.putString(PrefsActivity.UPLOADS_TYPE, systemProperties.getUploadResultType());
        editor.putString(PrefsActivity.COLLECTION_FREQUENCY, systemProperties.getDataCollectionFreq());
        editor.putString(PrefsActivity.SMS_CONTROAL_NUMBER, systemProperties.getSmsControlNum());
        editor.putString(PrefsActivity.SET_SAVE_ADDRESS, systemProperties.getDataParentPath());
        //设置拨测接口
        editor.putString(PrefsActivity.SCRIPT_DOWNLOAD_URL, httpInterfaces.getTestScriptQueryUrl());
        editor.putString(PrefsActivity.EQUIPMENT, httpInterfaces.getDeviceRegisterUrl());
        editor.putString(PrefsActivity.HEARTBEAT, httpInterfaces.getDeviceStatusUrl());
        editor.putString(PrefsActivity.DEVICE_TIME_INTERVAL, httpInterfaces.getDeviceStatusInterval());
        editor.putString(PrefsActivity.ALARM, httpInterfaces.getDeviceAlarmUrl());
        editor.putString(PrefsActivity.TASK_DOWNLOAD_URL, httpInterfaces.getTaskDownloadUrl());
        editor.putString(PrefsActivity.TASK_DOWNLOAD_TIME, httpInterfaces.getTaskDownloadInterval());
        editor.putString(PrefsActivity.PWD_UPDATE_URL, httpInterfaces.getPwdDownloadUrl());
        editor.putString(PrefsActivity.PWD_UPDATE_USERNAME, httpInterfaces.getPwdDownloadUserName());
        editor.putString(PrefsActivity.PWD_UPDATE_PWD, httpInterfaces.getPwdDownloadPwd());
        //设置业务指标的ftp参数
        editor.putString(PrefsActivity.SERVICE_IP_TWO, serviceKeyFtp.getFtpIp());
        editor.putString(PrefsActivity.SERVICE_PORT_TWO, serviceKeyFtp.getFtpPort());
        editor.putString(PrefsActivity.SERVICE_PATH_TWO, serviceKeyFtp.getFtpPath());
        editor.putString(PrefsActivity.SERVICE_USERNAME_TWO, serviceKeyFtp.getFtpUserName());
        editor.putString(PrefsActivity.SERVICE_PASSWORD_TWO, serviceKeyFtp.getFtpPwd());
        //设置拨测脚本的ftp参数
        editor.putString(PrefsActivity.SERVICE_IP_THREE, testScriptFtp.getFtpIp());
        editor.putString(PrefsActivity.SERVICE_PORT_THREE, testScriptFtp.getFtpPort());
        editor.putString(PrefsActivity.SERVICE_PATH_THREE, testScriptFtp.getFtpPath());
        editor.putString(PrefsActivity.SERVICE_USERNAME_THREE, testScriptFtp.getFtpUserName());
        editor.putString(PrefsActivity.SERVICE_PASSWORD_THREE, testScriptFtp.getFtpPwd());
        editor.putString(PrefsActivity.SCRIPT_DOWNLOAD_TIME, testScriptFtp.getFtpDownloadInterval());
        //设置业务指标的ftp参数
        editor.putString(PrefsActivity.SERVICE_IP_FOUR, testResultFtp.getFtpIp());
        editor.putString(PrefsActivity.SERVICE_PORT_FOUR, testResultFtp.getFtpPort());
        editor.putString(PrefsActivity.SERVICE_PATH_FOUR, testResultFtp.getFtpPath());
        editor.putString(PrefsActivity.SERVICE_USERNAME_FOUR, testResultFtp.getFtpUserName());
        editor.putString(PrefsActivity.SERVICE_PASSWORD_FOUR, testResultFtp.getFtpPwd());
        editor.putString(PrefsActivity.TEST_RESULT_RESP_INTERVAL, testResultFtp.getFtpDownloadInterval());
        editor.putString(PrefsActivity.TEST_RESULT_UPLOAD_INTERVAL, testResultFtp.getFtpUploadInterval());
        editor.putString(PrefsActivity.FTP_DATA_TIMEOUT, ftpDataTimeOut);
        //设置业务指标的ftp参数
//        editor.putString(PrefsActivity.SERVICE_IP_ONE, versiontFtp.getFtpIp());
//        editor.putString(PrefsActivity.SERVICE_PORT_ONE, versiontFtp.getFtpPort());
//        editor.putString(PrefsActivity.SERVICE_PATH_ONE, versiontFtp.getFtpPath());
//        editor.putString(PrefsActivity.SERVICE_USERNAME_ONE, versiontFtp.getFtpUserName());
//        editor.putString(PrefsActivity.SERVICE_PASSWORD_ONE, versiontFtp.getFtpPwd());
        editor.commit();
        if(!saveParentPath.equals(systemProperties.getDataParentPath())){
            runLogger.onSaveParentPathChange();
        }
    }
}
