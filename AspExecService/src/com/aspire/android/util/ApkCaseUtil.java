/**

 * @(#) ApkCaseManager.java Created on 2012-6-6

 *

 * Copyright (c) 2012 Aspire. All Rights Reserved

 */
package com.aspire.android.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.aspire.android.test.environment.DeviceEntity;
import com.aspire.android.test.exception.MException;
import com.aspire.android.test.execute.CommandConstants;
import com.aspire.android.test.execute.ContentValues;
import com.aspire.android.test.execute.ITestService;

/**
 * 
 * The class <code>ApkCaseManager</code>
 * 
 * 
 * 
 * @author ouyangfeng
 * 
 * @version 1.0
 */
public class ApkCaseUtil implements ServiceConnection {

    private DeviceEntity deviceEntity;

    private static ApkCaseUtil apkCaseManager;

    private static final Intent INTENT = new Intent(
            "com.aspire.android.test.execute.TestService");

    private boolean isConnection;

    private Context mContext;

    private static final int BINDSERVER_TIMEOUT = 20000;

    /**
     * Constructor
     */
    private ApkCaseUtil() {
    }

    /**
     * get a instance for ApkCaseManager
     * 
     * @param mContext
     * @return
     */
    public static ApkCaseUtil getInstance(Context mContext) {
        if (null != apkCaseManager) {
            return apkCaseManager;
        }
        apkCaseManager = new ApkCaseUtil();
        apkCaseManager.mContext = mContext;
        apkCaseManager.bindServer();
        return apkCaseManager;
    }

    /**
     * run android test case
     * 
     * @param params
     * @return
     */
    public ContentValues runCase(ContentValues params) throws MException {
        final ContentValues values = deviceEntity.executeCommand(
                CommandConstants.TYPE_RUNTEST, params);
        return values;
    }

    /**
     * install apk
     * 
     * @param apk_path
     * @return
     * @throws MException
     */
    public boolean install(String apk_path) throws MException {
        if (null != deviceEntity && null != apk_path) {
            return deviceEntity.install(apk_path);
        }
        throw new MException("the apk_path is null");
    }

    /**
     * uninstall the application
     * 
     * @param packageName
     * @return
     * @throws MException
     */
    public boolean uninstall(String packageName) throws MException {
        if (null != deviceEntity && null != packageName) {
            return deviceEntity.uninstall(packageName);
        }
        throw new MException("the packageName is null");
    }

    /**
     * bind to the itestservice
     */
    private void bindServer() {
        if (null != mContext) {
            mContext.bindService(INTENT, this, Context.BIND_AUTO_CREATE);
            synchronized (this) {
                try {
                    wait(BINDSERVER_TIMEOUT);
                } catch (InterruptedException e) {
                }
            }
            if (!isConnection) {
                throw new RuntimeException("service bind time out !");
            }
        }
    }

    /**
     * shutdown the itestservice
     */
    public void shutDown() {
        isConnection = false;
        mContext.unbindService(this);
        deviceEntity = null;
        apkCaseManager = null;
    }

    /**
     * (non-Javadoc)
     * 
     * @see android.content.ServiceConnection#onServiceConnected(android.content.ComponentName,
     *      android.os.IBinder)
     */
    public void onServiceConnected(ComponentName name, IBinder service) {
        synchronized (this) {
            isConnection = true;
            final ITestService iTestService = ITestService.Stub
                    .asInterface(service);
            deviceEntity = new DeviceEntity(iTestService);
            this.notifyAll();
        }

    }

    /**
     * (non-Javadoc)
     * 
     * @see android.content.ServiceConnection#onServiceDisconnected(android.content.ComponentName)
     */
    public void onServiceDisconnected(ComponentName name) {
        isConnection = false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#finalize()
     */
    @Override
    protected void finalize() throws Throwable {
        mContext.unbindService(this);
        super.finalize();
    }

}
