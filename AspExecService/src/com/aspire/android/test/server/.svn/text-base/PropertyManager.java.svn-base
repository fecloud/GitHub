/**
 * @(#) PreferencesManager.java Created on 2012-8-1
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.server;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.RemoteException;
import android.util.Log;

import com.aspire.android.daemon.remote.aidl.DaemonRemoteServiceManager;
import com.aspire.android.daemon.remote.aidl.entity.DaemonPreference;
import com.aspire.android.test.execute.ui.PrefsActivity;
import com.aspire.android.test.log.RunLogger;
import com.aspire.sqmp.mobilemanager.entity.MobileSharedFile;

/**
 * The class <code>PreferencesManager</code>
 * 
 * @author likai
 * @version 1.0
 */
public class PropertyManager {

    private RunLogger runLogger = RunLogger.getInstance();
    
    private static final String TAG = "PreferencesManager";
    /**
     * pc端传过来的配置信息
     */
    private MobileSharedFile mobileSharedFile;
    /**
     * 存储配置信息
     */
    private Editor editor;
    private SharedPreferences preferences;

    public PropertyManager(MobileSharedFile mobileSharedFile, SharedPreferences preferences) {
        this.mobileSharedFile = mobileSharedFile;
        this.preferences = preferences;
        editor = preferences.edit();
    }

    public void setPreference() {
        String saveParentPath = preferences.getString(PrefsActivity.SET_SAVE_ADDRESS, "/aspire/es/");
        // 设置本机信息
        editor.putString(PrefsActivity.DEVIC_NAME, mobileSharedFile.getDeviceName());
        editor.putString(PrefsActivity.SET_PHONE_NUM, mobileSharedFile.getMobileNum());
        editor.putString(PrefsActivity.TERMINAL, mobileSharedFile.getModel());
        editor.putString(PrefsActivity.IMEI, mobileSharedFile.getImei());
        editor.putString(PrefsActivity.PROVINCE, mobileSharedFile.getProvinceCode());
        editor.putString(PrefsActivity.CITY, mobileSharedFile.getCityCode());
        editor.putString(PrefsActivity.COMPANY, mobileSharedFile.getCompanyCode());
        editor.putString(PrefsActivity.TEST_PEOPLE, mobileSharedFile.getTester());
        // 设置系统参数
        editor.putBoolean(PrefsActivity.DATA_GET_SWITCH, mobileSharedFile.isConnSwitch());
        editor.putString(PrefsActivity.UPLOADS_TYPE, mobileSharedFile.getResultUploadType());
        editor.putString(PrefsActivity.COLLECTION_FREQUENCY, mobileSharedFile.getDataCollFreq());
        editor.putString(PrefsActivity.SMS_CONTROAL_NUMBER, mobileSharedFile.getSmsControlNum());
        editor.putString(PrefsActivity.SET_SAVE_ADDRESS, mobileSharedFile.getDataParentPath());
        // 拨测接口
        editor.putString(PrefsActivity.SCRIPT_DOWNLOAD_URL, mobileSharedFile.getScriptDownloadUrl());
        editor.putString(PrefsActivity.EQUIPMENT, mobileSharedFile.getDeviceRegisterUrl());
        editor.putString(PrefsActivity.HEARTBEAT, mobileSharedFile.getDeviceStatusUrl());
        editor.putString(PrefsActivity.ALARM, mobileSharedFile.getDeviceAlarmUrl());
        editor.putString(PrefsActivity.TASK_DOWNLOAD_URL, mobileSharedFile.getTaskDownloadUrl());
        editor.putString(PrefsActivity.PWD_UPDATE_URL, mobileSharedFile.getPasswordUpdateUrl());
        editor.putString(PrefsActivity.PWD_UPDATE_USERNAME, mobileSharedFile.getPasswordUpdateUserName());
        editor.putString(PrefsActivity.PWD_UPDATE_PWD, mobileSharedFile.getPasswordUpdatePwd());
        // 部分间隔
        editor.putString(PrefsActivity.TASK_DOWNLOAD_TIME, mobileSharedFile.getTaskDownloadInterval());
        editor.putString(PrefsActivity.DEVICE_TIME_INTERVAL, mobileSharedFile.getDeviceStatusInterval());
        editor.putString(PrefsActivity.FTP_DATA_TIMEOUT, mobileSharedFile.getFtpDataTimeOut());
        //  版本升级ftp设置
//        editor.putString(PrefsActivity.SERVICE_IP_ONE, mobileSharedFile.getVersionIp());
//        editor.putString(PrefsActivity.SERVICE_PORT_ONE, mobileSharedFile.getVersionPort());
//        editor.putString(PrefsActivity.SERVICE_PATH_ONE, mobileSharedFile.getVersionPath());
//        editor.putString(PrefsActivity.SERVICE_USERNAME_ONE, mobileSharedFile.getVersionUser());
//        editor.putString(PrefsActivity.SERVICE_PASSWORD_ONE, mobileSharedFile.getVersionPwd());
        // 业务指标ftp设置
        editor.putString(PrefsActivity.SERVICE_IP_TWO, mobileSharedFile.getServiceKeyIp());
        editor.putString(PrefsActivity.SERVICE_PORT_TWO, mobileSharedFile.getServiceKeyPort());
        editor.putString(PrefsActivity.SERVICE_PATH_TWO, mobileSharedFile.getServiceKeyPath());
        editor.putString(PrefsActivity.SERVICE_USERNAME_TWO, mobileSharedFile.getServiceKeyUser());
        editor.putString(PrefsActivity.SERVICE_PASSWORD_TWO, mobileSharedFile.getServiceKeyPwd());
        // 拨测脚本ftp设置
        editor.putString(PrefsActivity.SERVICE_IP_THREE, mobileSharedFile.getTestScriptIp());
        editor.putString(PrefsActivity.SERVICE_PORT_THREE, mobileSharedFile.getTestScriptPort());
        editor.putString(PrefsActivity.SERVICE_PATH_THREE, mobileSharedFile.getTestScriptPath());
        editor.putString(PrefsActivity.SERVICE_USERNAME_THREE, mobileSharedFile.getTestScriptUser());
        editor.putString(PrefsActivity.SERVICE_PASSWORD_THREE, mobileSharedFile.getTestScriptPwd());
        editor.putString(PrefsActivity.SCRIPT_DOWNLOAD_TIME, mobileSharedFile.getTestScriptInterval());
        // 拨测结果ftp设置
        editor.putString(PrefsActivity.SERVICE_IP_FOUR, mobileSharedFile.getTestResultIp());
        editor.putString(PrefsActivity.SERVICE_PORT_FOUR, mobileSharedFile.getTestResultPort());
        editor.putString(PrefsActivity.SERVICE_PATH_FOUR, mobileSharedFile.getTestResultPath());
        editor.putString(PrefsActivity.SERVICE_USERNAME_FOUR, mobileSharedFile.getTestResultUser());
        editor.putString(PrefsActivity.SERVICE_PASSWORD_FOUR, mobileSharedFile.getTestResultPwd());
        editor.putString(PrefsActivity.TEST_RESULT_UPLOAD_INTERVAL, mobileSharedFile.getTestResultUploadInterval());
        editor.putString(PrefsActivity.TEST_RESULT_RESP_INTERVAL, mobileSharedFile.getTestResultRespInterval());
        editor.commit();
        if(!saveParentPath.equals(mobileSharedFile.getDataParentPath())){
            runLogger.onSaveParentPathChange();
        }
        // 版本升级ftp设置
        setDaemonServicePreference();
    }

    public MobileSharedFile getMobileSharedFile() {
        mobileSharedFile = new MobileSharedFile();
        mobileSharedFile.setDeviceName(preferences.getString(PrefsActivity.DEVIC_NAME, null));
        mobileSharedFile.setMobileNum(preferences.getString(PrefsActivity.SET_PHONE_NUM, null));
        mobileSharedFile.setModel(preferences.getString(PrefsActivity.TERMINAL, null));
        mobileSharedFile.setImei(preferences.getString(PrefsActivity.IMEI, null));
        mobileSharedFile.setIp(preferences.getString(PrefsActivity.IP, null));
        mobileSharedFile.setDataCollFreq(preferences.getString(PrefsActivity.COLLECTION_FREQUENCY, null));
        mobileSharedFile.setSmsControlNum(preferences.getString(PrefsActivity.SMS_CONTROAL_NUMBER, null));
        mobileSharedFile.setDataParentPath(preferences.getString(PrefsActivity.SET_SAVE_ADDRESS, null));
        mobileSharedFile.setProvinceCode(preferences.getString(PrefsActivity.PROVINCE, null));
        mobileSharedFile.setCityCode(preferences.getString(PrefsActivity.CITY, null));
        mobileSharedFile.setResultUploadType(preferences.getString(PrefsActivity.UPLOADS_TYPE, null));
        mobileSharedFile.setConnSwitch(preferences.getBoolean(PrefsActivity.DATA_GET_SWITCH, false));
        addDaemonServicePreference();
        return mobileSharedFile;
    }

    public void setDaemonServicePreference() {
        try {
            boolean result = false;
            DaemonPreference daemonPreferenc = new DaemonPreference();
            String value = null;
            value = mobileSharedFile.getInquireUpdateEngineInterval();
            daemonPreferenc.setCheckUpdateInterval((value != null && value.length() != 0) ? Long.parseLong(value) : -1);
            value = mobileSharedFile.getMtExecserviceInterval();
            daemonPreferenc
                    .setCheckProcessInterval((value != null && value.length() != 0) ? Long.parseLong(value) : -1);
            value = mobileSharedFile.getVersionIp();
            daemonPreferenc.setHostname(value != null ? value : null);
            value = mobileSharedFile.getVersionPort();
            daemonPreferenc.setPort(value != null ? value : null);
            value = mobileSharedFile.getVersionPath();
            daemonPreferenc.setPath(value != null ? value : null);
            value = mobileSharedFile.getVersionUser();
            daemonPreferenc.setUserName(value != null ? value : null);
            value = mobileSharedFile.getVersionPwd();
            daemonPreferenc.setPassword(value != null ? value : null);
            result = DaemonRemoteServiceManager.instance().setDaemonPreference(daemonPreferenc);
            if (!result) {// daemon远程连接未连接成功
                Log.e(TAG, "Error to set  preference of daemon!");
                return;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void addDaemonServicePreference() {
        try {
            if (mobileSharedFile == null)
                return;
            DaemonPreference daemonPreference = DaemonRemoteServiceManager.instance().getDaemonPreference();
            if (daemonPreference == null) {// daemon远程连接未连接成功
                Log.e(TAG, "Error to get  preference of daemon!");
                return;
            }
            mobileSharedFile.setInquireUpdateEngineInterval(Long.toString(daemonPreference.getCheckUpdateInterval()));
            mobileSharedFile.setMtExecserviceInterval(Long.toString(daemonPreference.getCheckProcessInterval()));
            mobileSharedFile.setVersionIp(daemonPreference.getHostname());
            mobileSharedFile.setVersionPort(daemonPreference.getPort());
            mobileSharedFile.setVersionPath(daemonPreference.getPath());
            mobileSharedFile.setVersionUser(daemonPreference.getUserName());
            mobileSharedFile.setVersionPwd(daemonPreference.getPassword());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
