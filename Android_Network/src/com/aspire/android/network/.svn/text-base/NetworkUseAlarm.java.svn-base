/**
 * @(#) NetworkUseAlarm.java Created on 2012-9-14
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.network;

import android.util.Log;

/**
 * The class <code>NetworkUseAlarm</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class NetworkUseAlarm implements Runnable {

    private static final String TAG = "NetworkUseAlarm";

    private NetworkAlarmListener listener;

    private boolean isflag;

    private int loopTime = 500;

    /**
     * 接收超限值
     */
    private long maxRx;

    /**
     * 发送超限值
     */
    private long maxTx;

    /**
     * 发送和接收总超限值
     */
    private long maxTota;

    /**
     * 已接收的字节数
     */
    private long rxUse;

    /**
     * 已发送的字节数
     */
    private long txUse;

    /**
     * 接收和发送的总字节数
     */
    private long allUse;

    private NetworkUse networkUse;

    private NetworkUseStorage networkUseStorage;

    public NetworkUseAlarm(NetworkUse networkUse, NetworkAlarmListener listener, NetworkUseStorage networkUseStorage) {
        this.networkUse = networkUse;
        this.listener = listener;
        this.networkUseStorage = networkUseStorage;
    }

    public NetworkUseAlarm(long maxTota, NetworkUse networkUse, NetworkAlarmListener listener,
            NetworkUseStorage networkUseStorage) {
        this(networkUse, listener, networkUseStorage);
        this.maxTota = maxTota;
    }

    public NetworkUseAlarm(long maxRx, long maxTx, long maxTota, NetworkUse networkUse, NetworkAlarmListener listener,
            NetworkUseStorage networkUseStorage) {
        this(maxTota, networkUse, listener, networkUseStorage);
        this.maxRx = maxRx;
        this.maxTx = maxTx;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Runnable#run()
     */
    public void run() {
        Log.d(TAG, "run...");
        isflag = true;
        preTask();
        while (isflag) {
            getNetworkUse();
            checkAlarm();
            try {
                Thread.sleep(loopTime);
            } catch (InterruptedException e) {
                Log.e(TAG, "run ... erro", e);
            }
        }
    }

    /**
     * 启动告警服务
     */
    public synchronized void start() {
        Log.d(TAG, "start...");
        if (!isflag) {
            Thread thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * 停止告警服务
     */
    public synchronized void stop() {
        Log.d(TAG, "stop...");
        if (isflag) {
            isflag = false;
        }
    }

    /**
     * 取得上一次的流量
     */
    private void preTask() {
        if (null != networkUseStorage) {
            this.rxUse = networkUseStorage.getDayRxBytes();
            this.txUse = networkUseStorage.getDayTxBytes();
            this.allUse = networkUseStorage.getDayTotalBytes();
        }
    }

    /**
     * 取得本机从到当前时间的网络使用量
     */
    private void getNetworkUse() {
        if (null != networkUse) {
            this.rxUse += networkUse.getRxBytes();
            this.txUse += networkUse.getTxBytes();
            this.allUse += networkUse.getTotalBytes();
        }
    }

    /**
     * 检查是否需要告警
     */
    private void checkAlarm() {
        if (null != listener) {
            if (maxRx != 0 && maxRx <= rxUse) {
                listener.onRxAlarm(rxUse);
            }
            if (maxTx != 0 && maxTx <= txUse) {
                listener.onTxAlarm(txUse);
            }
            if (maxTota != 0 && maxTota <= allUse) {
                listener.onAllAlarm(allUse);
            }
        }
    }

    public long getMaxRx() {
        return maxRx;
    }

    public void setMaxRx(long maxRx) {
        this.maxRx = maxRx;
    }

    public long getMaxTx() {
        return maxTx;
    }

    public void setMaxTx(long maxTx) {
        this.maxTx = maxTx;
    }

    public long getMaxTota() {
        return maxTota;
    }

    public void setMaxTota(long maxTota) {
        this.maxTota = maxTota;
    }

    public long getRxUse() {
        return rxUse;
    }

    public long getTxUse() {
        return txUse;
    }

    public long getAllUse() {
        return allUse;
    }

    public void setListener(NetworkAlarmListener listener) {
        this.listener = listener;
    }

    /**
     * 设置告警服务查询流量的轮循时间,单位ms
     * 
     * @param loopTime
     */
    public void setLoopTime(int loopTime) {
        this.loopTime = loopTime;
    }

}
