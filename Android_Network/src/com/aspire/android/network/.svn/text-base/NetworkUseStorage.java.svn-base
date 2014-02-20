/**
 * @(#) NetworkUseStorge.java Created on 2012-9-14
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.network;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * The class <code>NetworkUseStorge</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class NetworkUseStorage {

    private static final String RX = "rx";

    public static final String TX = "tx";

    private SharedPreferences preferences;

    public NetworkUseStorage(Context mContext) {
        if (null != mContext) {
            preferences = mContext.getSharedPreferences("NetworkUseStorage", Context.MODE_PRIVATE);
        }
    }

    /**
     * 取获得今天开机之前接收的流量
     * 
     * @return
     */
    public long getDayRxBytes() {
        final String currentTime = formatDate();
        return preferences.getLong(currentTime + RX, 0);
    }

    /**
     * 保存今天开机之后使用的接收流量值
     * 
     * @param bytes
     * @return
     */
    public boolean saveDayRxBytes(long bytes) {
        final String currentTime = formatDate();
        bytes += getDayRxBytes();
        preferences.edit().putLong(currentTime + RX, bytes).commit();
        return true;
    }

    public long getRxBytes(long startDay) {
        return 0;
    }

    /**
     * 获取今天开机前的发送流量
     * 
     * @return
     */
    public long getDayTxBytes() {
        final String currentTime = formatDate();
        return preferences.getLong(currentTime + TX, 0);
    }

    /**
     * 保存今天开机之后使用的发送流量值
     * 
     * @param bytes
     * @return
     */
    public boolean saveDayTxBytes(long bytes) {
        final String currentTime = formatDate();
        bytes += getDayTxBytes();
        preferences.edit().putLong(currentTime + TX, bytes).commit();
        return true;
    }

    /**
     * 获取从指定时间到今天开机前的发送流量
     * 
     * @param startDay
     * @return
     */
    public long getTxBytes(long startDay) {
        return 0;
    }

    /**
     * 获取今天开机之前的总发送量
     * 
     * @return
     */
    public long getDayTotalBytes() {
        return getDayRxBytes() + getDayTxBytes();
    }

    public long getTotalBytes() {
        return 0;
    }

    public long getTotalBytes(long startDay) {
        return 0;
    }

    /**
     * 格式化当前时间yyyy-MM-dd
     * 
     * @return
     */
    public static String formatDate() {
        return formatDate(-1);
    }

    /**
     * 格式化指定时间yyyy-MM-dd
     * 
     * @param date
     * @return
     */
    public static String formatDate(long date) {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = null;
        if (date == -1)
            date2 = new Date();
        else
            date2 = new Date(date);

        return format.format(date2);
    }
}
