/**
 * @(#) Environment.java Created on 2012-4-23
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.environment;

import java.io.File;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.aspire.android.test.exception.MException;
import com.aspire.android.test.execute.CommandConstants;
import com.aspire.android.test.execute.ContentValues;
import com.aspire.android.test.execute.ITestService;

/**
 * The class <code>Environment</code>
 * 
 * @author linjunsui
 * @version 1.0
 */
public class Environment implements IEnvironment {

    private static final String TAG = "Environment";

    /**
     * singleton instance
     */
    protected static Environment INSTANCE;

    /**
     * environment setting
     */
    protected ContentValues setting;

    /**
     * environment setting
     */
    protected ContentValues globalVariables;

    /**
     * paramsMap
     */
    protected ContentValues params;

    /**
     * device Entity
     */
    protected IDeviceEntity deviceEntity;

    /**
     * aidl test service
     */
    protected ITestService testService;

    protected static boolean isConnected = false;

    /**
     * test model
     */
    private String testMode = CommandConstants.MODE_RUN;

    /**
     * service connection
     */
    protected ServiceConnection serviceConnection = new ServiceConnection() {
        /**
         * (non-Javadoc)
         * 
         * @see android.content.ServiceConnection#onServiceConnected(android.content.ComponentName, android.os.IBinder)
         */
        public void onServiceConnected(ComponentName className, IBinder service) {

            // lock
            ITestService testService = ITestService.Stub.asInterface(service);
            deviceEntity = new DeviceEntity(testService);
            // // lock
            // synchronized (INSTANCE) {
            // try {
            // setting = deviceEntity.getSetting();
            // globalVariables = deviceEntity.getGlobalVariables();
            // testMode = deviceEntity.getModel();// get test model
            // Log.d(TAG, "run mode:" + testMode);
            // isConnected = true;
            // } catch (MException e) {
            // testService = null;
            // Log.e("Enviroment", "onServiceConnected error", e);
            // }

            try {
                setting = deviceEntity.getSetting();
                globalVariables = deviceEntity.getGlobalVariables();
                testMode = deviceEntity.getModel();// get test model
                Log.d(TAG, "run mode:" + testMode);
                isConnected = true;
            } catch (MException e) {
                testService = null;
                Log.e(TAG, "onServiceConnected error", e);
            }
        }

        /**
         * (non-Javadoc)
         * 
         * @see android.content.ServiceConnection#onServiceDisconnected(android.content.ComponentName)
         */
        public void onServiceDisconnected(ComponentName className) {
            deviceEntity = null;
        }
    };

    /**
     * context
     */
    private Context context;

    /**
     * hide constructor
     */
    private Environment(Context context) {
        super();
        this.context = context;
        Intent intent = new Intent("com.aspire.android.test.execute.TestService");
        this.context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        Log.d(TAG, "send bindService success");
    }

    /**
     * hide constructor
     */
    public static Environment getInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = new Environment(context);

            // lock
            // synchronized (INSTANCE) {
            // Log.d(TAG, "waiting 10000ms");

            // try {
            // INSTANCE.wait(10000);
            // } catch (InterruptedException e) {
            // }
            // Log.d(TAG, "waiting time to");
            // if (INSTANCE.getDeviceEntity() == null) {
            // INSTANCE = null;
            // }
        }

        for (int i = 0; i < 100; i++) {
            if (!isConnected) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                }
                continue;
            }
            break;
        }

        return INSTANCE;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IEnvironment#saveResult(android.content.ContentValues)
     */
    public void saveResult(ContentValues resultMap) {
        try {
            deviceEntity.saveResult(resultMap);
        } catch (MException e) {
            Log.e(TAG, "Error saveResult", e);
        }

    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IEnvironment#getSetting()
     */
    public ContentValues getSetting() {
        return setting;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IEnvironment#getGlobalVariables()
     */
    public ContentValues getGlobalVariables() {
        return globalVariables;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IEnvironment#getParams()
     */
    public ContentValues getParams() {
        if (null == params) {
            try {
                params = deviceEntity.getParams();
            } catch (MException e) {
                Log.e(TAG, "getParams error", e);
            }
        }
        return params;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IEnvironment#getDeviceEntity()
     */
    public IDeviceEntity getDeviceEntity() {
        return deviceEntity;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IEnvironment#getMode()
     */
    public String getMode() {
        return testMode;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IEnvironment#getDebugParams()
     */
    public ContentValues getDebugParams() {
        final ContentValues params = new ContentValues();
        final String root = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator
                + "aspire" + File.separator;
        final String tempLoaction = root + "temp";
        final String logLocation = root + "logs";
        final String certificateLocation = root + "certificates";
        final String attachmentLoction = root + "attachments";
        final String tcpdumpLocation = root + "tcpdumps";
        params.put(CommandConstants.KEY_CASE_LOGLOCATION, logLocation);
        params.put(CommandConstants.KEY_CASE_LOG_LEVEL, "ALL");
        params.put(CommandConstants.KEY_CASE_CERTIFICATE_LOCATION, certificateLocation);
        params.put(CommandConstants.KEY_CASE_ATTACHMENT_LOCTION, attachmentLoction);
        params.put(CommandConstants.KEY_CASE_TCPDUM_LOCATION, tcpdumpLocation);
        params.put(CommandConstants.KEY_TEMP_LOCATION, tempLoaction);
        return params;
    }
}
