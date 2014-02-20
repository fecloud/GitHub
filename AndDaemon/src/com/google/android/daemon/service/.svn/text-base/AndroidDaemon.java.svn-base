/**
 * @(#) AndroidDaemon.java Created on 2013-1-19
 *
 * Copyright (c) 2013 com.braver. All Rights Reserved
 */
package com.google.android.daemon.service;

import com.google.android.daemon.Daemon;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * The class <code>AndroidDaemon</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public final class AndroidDaemon extends Service {

    private Daemon daemon;

    /**
     * {@inheritDoc}
     * 
     * @see android.app.Service#onBind(android.content.Intent)
     */
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see android.app.Service#onCreate()
     */
    @Override
    public void onCreate() {
        super.onCreate();
        if (null == daemon)
            daemon = new Daemon();
        daemon.start();
    }

    /**
     * {@inheritDoc}
     * 
     * @see android.app.Service#onDestroy()
     */
    @Override
    public void onDestroy() {
        if (null != daemon)
            daemon.stop();
        super.onDestroy();
    }

}
