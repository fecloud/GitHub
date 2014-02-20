/**
 * @(#) KeyEventProcess.java Created on 2012-12-14
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.mbre.agent.monitor.event.key;

import android.util.Log;

import com.aspire.mbre.agent.monitor.event.InputEvent;
import com.aspire.mbre.agent.monitor.event.InputEvent.InputEventType;
import com.aspire.mbre.agent.monitor.event.InputEventProcess;

/**
 * The class <code>KeyEventProcess</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class KeyEventProcess extends InputEventProcess {

    protected static final String TAG = "KeyEventProcess";

    private KeyEventListener keyEventListener;

    /**
     * Constructor
     * 
     * @param eventType
     */
    public KeyEventProcess() {
        super(InputEventType.EV_KEY);
    }

    /**
     * Constructor
     * 
     * @param eventType
     */
    public KeyEventProcess(KeyEventListener keyEventListener) {
        super(InputEventType.EV_KEY);
        this.keyEventListener = keyEventListener;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.mbre.agent.monitor.event.InputEventProcess#selfProcess(com.aspire.mbre.agent.monitor.event.InputEvent)
     */
    @Override
    public boolean selfProcess(InputEvent inputEvent) {
        if (inputEvent.getValue() == Action.KEY_DOWN) {
            notifyKeyDown(inputEvent);
        } else if (inputEvent.getValue() == Action.KEY_UP) {
            notifyKeyUp(inputEvent);
        } else {
            Log.d(TAG, "" + inputEvent);
        }
        return true;
    }

    /**
     * 键盘按下 ,inputEvent里keyCode为android key 键值
     * 
     * @param inputEvent
     */
    private void notifyKeyDown(InputEvent inputEvent) {
        // Log.d(TAG, "notifyKeyDown");
        if (null != keyEventListener)
            keyEventListener.onKeyDown(inputEvent.getKeyCode());
    }

    /**
     * 键盘松开,inputEvent里keyCode为android key 键值
     * 
     * @param inputEvent
     */
    private void notifyKeyUp(InputEvent inputEvent) {
        // Log.d(TAG, "notifyKeyUp");
        if (null != keyEventListener)
            keyEventListener.onKeyUp(inputEvent.getKeyCode());
    }

    /**
     * 设置键盘处理监听
     * 
     * @param keyEventListener
     */
    public void setKeyEventListener(KeyEventListener keyEventListener) {
        this.keyEventListener = keyEventListener;
    }

    /**
     * 
     * The class <code>KeyEventListener</code> 设置键盘处理监听
     * 
     * @author ouyangfeng
     * @version 1.0
     */
    public interface KeyEventListener {

        /**
         * 键盘按下事件
         * 
         * @param keyCode
         */
        public void onKeyDown(int keyCode);

        /**
         * 键盘松开事件
         * 
         * @param keyCode
         */
        public void onKeyUp(int keyCode);
    }

    /**
     * The class <code>Action</code>
     * <p>
     * 键盘事件类型,1为按下,0为松开
     * 
     * @author ouyangfeng
     * @version 1.0
     */
    public static final class Action {

        /**
         * 键盘按下事件
         */
        public static final int KEY_DOWN = 0x1;

        /**
         * 键盘松开事件
         */
        public static final int KEY_UP = 0x0;

    }

}
