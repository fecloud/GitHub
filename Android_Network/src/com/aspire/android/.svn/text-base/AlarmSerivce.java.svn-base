/**
 * @(#) AlarmSerivce.java Created on 2012-9-14
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android;

import com.aspire.android.network.NetworkAlarmListener;
import com.aspire.android.network.NetworkUse;
import com.aspire.android.network.NetworkUseAlarm;
import com.aspire.android.network.NetworkUseStorage;
import com.aspire.android.network.mobile.MobileNetworkUse;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * The class <code>AlarmSerivce</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class AlarmSerivce extends Service implements NetworkAlarmListener {

    private static final String TAG = "AlarmSerivce";

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
     * @see android.app.Service#onStart(android.content.Intent, int)
     */
    @Override
    public void onStart(Intent intent, int startId) {
        NetworkUse networkUse = new MobileNetworkUse();
        NetworkUseAlarm alarm = new NetworkUseAlarm(300, networkUse, this, new NetworkUseStorage(
                getApplicationContext()));
        alarm.start();
        super.onStart(intent, startId);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.network.NetworkAlarmListener#onTxAlarm(long)
     */
    public void onTxAlarm(long txBytes) {
        Log.d(TAG, "onTxAlarm:" + txBytes);

    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.network.NetworkAlarmListener#onRxAlarm(long)
     */
    public void onRxAlarm(long rxBytes) {
        Log.d(TAG, "onRxAlarm:" + rxBytes);

    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.network.NetworkAlarmListener#onAllAlarm(long)
     */
    public void onAllAlarm(long allBytes) {
        Log.d(TAG, "onAllAlarm:" + allBytes);
    }
}
