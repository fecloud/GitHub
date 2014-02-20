/**
 * @(#) DaemonRemoteServiceManager.java Created on 2012-7-20
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.daemon.remote.aidl;

import com.aspire.android.daemon.remote.aidl.entity.DaemonPreference;
import com.aspire.android.test.exception.MException;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.util.Log;

/**
 * The class <code>DaemonRemoteServiceManager</code>
 * 
 * @author wuyanlong
 * @version 1.0
 */
public class DaemonRemoteServiceManager extends IDaemonRemoteService.Stub {

    private static final String TAG = "DaemonRemoteServiceManager";
    /**
     * Action of DaemonRemoteService
     */
    public static String DAEMON_SERVICE_ACTION = "aspire.android.daemon.REMOTE_SERVICE";
    /**
     * The instace
     */
    private static DaemonRemoteServiceManager daemonRemoteServiceManager;
    /**
     * Connection of service
     */
    private DaemonConnect conn = new DaemonConnect();
    /**
     * Context
     */
    private Context context;

    /**
     * Constructor
     * 
     * @param context
     */
    public DaemonRemoteServiceManager(Context context) {
        if (context == null)
            throw new NullPointerException("while DaemonRemoteServiceManager construct!");
        this.context = context;
        bingRemoteService();
        daemonRemoteServiceManager = this;
    }

    /**
     * Gettor of instance
     * 
     * @return
     */
    public static DaemonRemoteServiceManager instance() {
        return daemonRemoteServiceManager;
    }

    /**
     * Bind remote service
     */
    public void bingRemoteService() {
        Intent intent = new Intent(DAEMON_SERVICE_ACTION);
        context.bindService(intent, conn, Context.BIND_AUTO_CREATE);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * (non-Javadoc)
     * 
     * @throws MException
     * 
     * @see com.aspire.android.daemon.remote.aidl.IDaemonRemoteService#getDaemonProperty()
     */
    public DaemonPreference getDaemonPreference() throws RemoteException {
        bingRemoteService();
        final IDaemonRemoteService daemonRemoteService = conn.getIDaemonRemoteService();
        if (daemonRemoteService == null) {
            Log.e(TAG, "Can not cannect Daemon RemoteService !");
            return null;
        }
        return daemonRemoteService.getDaemonPreference();

    }

    /**
     * Destory
     */
    public void destory() {
        try {
            context.unbindService(conn);
        } catch (Exception e) {
        }
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.daemon.remote.aidl.IDaemonRemoteService#serDaemonProperty(java.lang.String)
     */
    public boolean setDaemonPreference(DaemonPreference daemonPreference) throws RemoteException {
        bingRemoteService();
        final IDaemonRemoteService daemonRemoteService = conn.getIDaemonRemoteService();
        if (daemonRemoteService == null) {
            Log.e(TAG, "Can not cannect Daemon RemoteService !");
            return false;
        }
        Log.d(TAG, "Set:" + daemonPreference.toString());
        return daemonRemoteService.setDaemonPreference(daemonPreference);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.daemon.remote.aidl.IDaemonRemoteService#getSavePath()
     */
    public String getSavePath() throws RemoteException {
        bingRemoteService();
        final IDaemonRemoteService daemonRemoteService = conn.getIDaemonRemoteService();
        if (daemonRemoteService == null) {
            Log.e(TAG, "Can not cannect Daemon RemoteService !");
            return null;
        }
        return daemonRemoteService.getSavePath();
    }

}
