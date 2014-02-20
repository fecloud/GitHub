/**
 * @(#) InputManager.java Created on 2012-11-8
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.mbre.agent.monitor.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.util.Log;

/**
 * The class <code>InputManager</code>
 * 
 * @author ouyangfeng 调用方法
 *         <p>
 *         InputEventReader reader = new InputEventReader(); <br/>
 *         reader.init();<br/>
 *         reader.start();
 *         <p>
 * @version 1.0
 */
public class InputEventReader {

    private static final String TAG = "InputEventReader";

    protected CallBack mCallBack = new CallBack();

    private List<InputEventProcess> process;

    private boolean flag;

    static {
        Log.d(TAG, "loadLibrary monitorevent...");
        System.loadLibrary("monitorevent");
        Log.d(TAG, "loadLibrary monitorevent success");
    }

    public InputEventReader() {
        this.process = Collections.synchronizedList(new ArrayList<InputEventProcess>());
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

    /**
     * 接收到一个事件,交给InputEventProcess类处理,如果处理完成后,想让其他类继续处理,返回false,否则true
     * 
     * @param inputEvent
     * @return
     */
    private boolean process(InputEvent inputEvent) {
        for (InputEventProcess event : process) {
            if (event.process(inputEvent)) {
                return true;
            }
        }
        return false;
    }

    /**
     * add inputEventProcess
     * 
     * @param inputEventProcess
     * @return
     */
    public synchronized boolean addInputEventProcess(InputEventProcess inputEventProcess) {
        this.process.add(inputEventProcess);
        return true;
    }

    /**
     * delete inputEventProcess
     * 
     * @param inputEventProcess
     * @return
     */
    public synchronized boolean deleteInputEventProcess(InputEventProcess inputEventProcess) {
        return this.process.remove(inputEventProcess);
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
            // Log.d(TAG + "$CallBack", String.format(
            // "nativeCallBack deviceId=%d type=%d scanCode=%d keyCode=%d value=%d", deviceId, type, scanCode,
            // keyCode, value));
            inputEvent.setFieldValues(deviceId, type, scanCode, keyCode, value);
            return process(inputEvent);

        }

    }

}
