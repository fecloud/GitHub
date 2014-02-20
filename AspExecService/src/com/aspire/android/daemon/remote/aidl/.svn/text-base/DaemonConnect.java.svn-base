/**
 * @(#) DaemonConnect.java Created on 2012-7-20
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.daemon.remote.aidl;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

/**
 * The class <code>DaemonConnect</code>
 * 
 * @author wuyanlong
 * @version 1.0
 */
public class DaemonConnect implements ServiceConnection {
    private IDaemonRemoteService daemonRemoteService;

    /**
     * (non-Javadoc)
     * 
     * @see android.content.ServiceConnection#onServiceConnected(android.content.ComponentName, android.os.IBinder)
     */
    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.d("DaemonConnect", "onServiceConnected");
        daemonRemoteService = IDaemonRemoteService.Stub.asInterface(service);
    }

    /**
     * (non-Javadoc)
     * 
     * @see android.content.ServiceConnection#onServiceDisconnected(android.content.ComponentName)
     */
    public void onServiceDisconnected(ComponentName name) {
        daemonRemoteService = null;
    }

    /**
     * Gettor or DaemonRemoteService;
     * 
     * @return
     */
    public IDaemonRemoteService getIDaemonRemoteService() {
        return daemonRemoteService;
    }
}
