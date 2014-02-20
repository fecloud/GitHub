/**
 * @(#) NetworkAlarmListener.java Created on 2012-9-14
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.network;

/**
 * The class <code>NetworkAlarmListener</code>
 * <p>
 * 流量告警回调接口
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public interface NetworkAlarmListener {

    /**
     * 当发送流量超限
     * 
     * @param txBytes
     */
    public void onTxAlarm(long txBytes);

    /**
     * 当接收流量超限
     * 
     * @param rxBytes
     */
    public void onRxAlarm(long rxBytes);

    /**
     * 当发送和接收流量超限
     * 
     * @param allBytes
     */
    public void onAllAlarm(long allBytes);

}
