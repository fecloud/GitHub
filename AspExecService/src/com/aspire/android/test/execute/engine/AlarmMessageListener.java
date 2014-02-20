/**
 * @(#) AlarmMessageListener.java Created on 2012-9-20
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute.engine;

import android.util.Log;

import com.aspire.android.common.exception.MException;
import com.aspire.android.test.device.sync.DeviceSync;
import com.aspire.android.util.AlarmConverter;
import com.aspire.mcts.agent.msg.APSMessage;
import com.aspire.mcts.agent.msg.ErrorReportRequest;

/**
 * The class <code>AlarmMessageListener</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class AlarmMessageListener implements IAPSMessageListener {

    private static final String TAG = "AlarmMessageListener";

    private ErrorReportRequest request;

    private DeviceSync deviceSync;

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.execute.engine.IAPSMessageListener#onMessage(com.aspire.mcts.agent.msg.APSMessage)
     */
    public boolean onMessage(APSMessage message) {
        switch (message.getType()) {
        case APSMessage.ER_REQ:
            this.request = (ErrorReportRequest) message;
            executeMessage();
            return false;
        }
        return true;
    }

    /**
     * 处理发来的错误消息
     * 
     * @return
     */
    protected boolean executeMessage() {
        if (null != request) {
            if (null == deviceSync)
                deviceSync = new DeviceSync();
            Log.d("AlarmMessageListener", "execute");
            int code = request.getErrorCode();
            final String message = request.getMessage();
            try {
                Log.d(TAG, "code:" + code);// 转换之前的
                code = AlarmConverter.converter(code);// 转换
                Log.d(TAG, "code:" + code + "message:" + message);
                deviceSync.deviceAlarm("" + code, message);
            } catch (MException e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
        return true;
    }

}
