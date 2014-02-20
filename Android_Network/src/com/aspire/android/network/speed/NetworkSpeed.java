/**
 * @(#) NetworkSpeed.java Created on 2012-9-14
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.network.speed;

import android.util.Log;

import com.aspire.android.network.NetworkUse;

/**
 * The class <code>NetworkSpeed</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class NetworkSpeed {

    private static final String TAG = "NetworkSpeed";

    private NetworkUse networkUse;

    public NetworkSpeed(NetworkUse networkUse) {
        this.networkUse = networkUse;
    }

    /**
     * 取得当前一秒接收的字节数
     * 
     * @return
     */
    public long getRxSpeed() {
        final long oneTime = networkUse.getRxBytes();
        Log.d(TAG, "getRxSpeed oneTime:" + oneTime);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Log.e(TAG, "getRxSpeed error", e);
        }
        final long twoTime = networkUse.getRxBytes();
        Log.d(TAG, "getRxSpeed twoTime:" + twoTime);
        return twoTime - oneTime;
    }

    /**
     * 取得当前一秒接收的总数<b>单位KB</b>
     * 
     * @return
     */
    public double getRxSpeedKB() {
        final double d = getRxSpeed();
        return d / 1024;
    }

    /**
     * 取得当前一秒发送的字节数
     * 
     * @return
     */
    public long getTxSpeed() {
        final long oneTime = networkUse.getTxBytes();
        Log.d(TAG, "getTxSpeed oneTime:" + oneTime);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Log.e(TAG, "getTxSpeed error", e);
        }
        final long twoTime = networkUse.getTxBytes();
        Log.d(TAG, "getTxSpeed twoTime:" + twoTime);
        return twoTime - oneTime;
    }

    /**
     * 取得当前一秒发送的总数 <b>单位KB</b>
     * 
     * @return
     */
    public double getTxSpeedKB() {
        final double d = getTxSpeed();
        return d / 1024;
    }

    /**
     * 取得当前一秒发送和接收的总字节数
     * 
     * @return
     */
    public long getSpeed() {
        final long oneTime = networkUse.getTxBytes() + networkUse.getRxBytes();
        Log.d(TAG, "getSpeed oneTime:" + oneTime);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Log.e(TAG, "getSpeed error", e);
        }
        final long twoTime = networkUse.getTxBytes() + networkUse.getRxBytes();
        return twoTime - oneTime;
    }

    /**
     * 取得当前一秒发送和接收的总数 <b>单位KB</b>
     * 
     * @return
     */
    public double getSpeedKB() {
        final double d = getSpeed();
        return d / 1024;
    }
}
