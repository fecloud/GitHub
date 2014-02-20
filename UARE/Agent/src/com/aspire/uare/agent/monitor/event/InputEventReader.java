/**
 * @(#) InputManager.java Created on 2012-11-8
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.uare.agent.monitor.event;

import android.util.Log;

/**
 * The class <code>InputManager</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class InputEventReader {

    private static final String TAG = "InputEventReader";

    protected CallBack mCallBack = new CallBack();

    private boolean flag;

    static {
        Log.d(TAG, "loadLibrary monitorevent...");
        System.loadLibrary("monitorevent");
    }

    public native boolean nativeInit(CallBack mBack);

    public native boolean nativeStart();

    public native boolean nativeStop();

    /**
     * 初始化
     * 
     * @return
     */
    public boolean init() {
        return nativeInit(mCallBack);
    }

    /**
     * 启动读取
     * 
     * @return
     */
    public boolean start() {
        if (nativeStart()) {
            flag = true;
        }
        return flag;
    }

    /**
     * 停止读取
     * 
     * @return
     */
    public boolean stop() {
        if (flag) {
            if (nativeStop())
                flag = false;
        }
        return flag;
    }

    private boolean process(InputEvent inputEvent) {
        return true;
    }

    /**
     * native callBack The class <code>CallBack</code>
     * 
     * @author ouyangfeng
     * @version 1.0
     */
    public final class CallBack {

        private InputEvent inputEvent = new InputEvent();

        public boolean nativeCallBack(int deviceId, int type, int scanCode, int keyCode, int value) {
            Log.d(TAG + "$CallBack", String.format(
                    "nativeCallBack deviceId=%d type=%d scanCode=%d keyCode=%d value=%d", deviceId, type, scanCode,
                    keyCode, value));
            inputEvent.setFieldValues(deviceId, type, scanCode, keyCode, value);
            return process(inputEvent);

        }

    }

}
