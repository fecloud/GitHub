/**
 * @(#) AndroidReceiver.java Created on 2013-1-19
 *
 * Copyright (c) 2013 com.braver. All Rights Reserved
 */
package com.google.android.daemon.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * The class <code>AndroidReceiver</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public final class AndroidReceiver extends BroadcastReceiver {

    /**
     * service action
     */
    private static final String ACTION = "com.google.android.daemon.AndroidDaemon";

    /**
     * {@inheritDoc}
     * 
     * @see android.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        //accept boot broadcastreceiver
        //start service
        context.startService(new Intent(ACTION));
    }

}
