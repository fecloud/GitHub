/**
 * @(#) Test.java Created on 2012-9-14
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.network;

import com.aspire.android.network.mobile.MobileFieldConfig;
import com.aspire.android.network.mobile.MobileNetworkUse;
import com.aspire.android.network.speed.NetworkSpeed;

import android.test.AndroidTestCase;
import android.util.Log;

/**
 * The class <code>Test</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class Test extends AndroidTestCase implements NetworkAlarmListener {

    private static final String TAG = "Test";

    public void test() {
        final String read = new MobileFieldConfig().getReadField();
        Log.d(TAG, "read:" + read);
    }

    public void getRxBytes() {
        NetworkUse use = new MobileNetworkUse();
        Log.d(TAG, "rxbytes:" + use.getRxBytes());
        Log.d(TAG, "txbytes:" + use.getTxBytes());
        Log.d(TAG, "totalbytes:" + use.getTotalBytes());
    }

    public void testNetworkAlarm() {
        // NetworkUse networkUse = new MobileNetworkUse();
        // NetworkUseAlarm alarm = new NetworkUseAlarm(300, networkUse, this);
        // alarm.start();
    }

    public void testSpeed() {
        NetworkSpeed speed = new NetworkSpeed(new MobileNetworkUse());
        int i = 100;
        while (i > 0) {
//            Log.d(TAG, "rx:" + speed.getRxSpeedKB());
            Log.d(TAG, "alx:" + speed.getSpeedKB());
            i--;
        }
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
