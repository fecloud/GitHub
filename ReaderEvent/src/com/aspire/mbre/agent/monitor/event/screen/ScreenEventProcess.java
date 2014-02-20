/**
 * @(#) ScreenEventProcess.java Created on 2012-12-14
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.mbre.agent.monitor.event.screen;

import com.aspire.mbre.agent.monitor.event.InputEvent;
import com.aspire.mbre.agent.monitor.event.InputEvent.InputEventType;
import com.aspire.mbre.agent.monitor.event.InputEventProcess;

/**
 * The class <code>ScreenEventProcess</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class ScreenEventProcess extends InputEventProcess {

    protected static final String TAG = "InputEventProcess";

    private static final int INIT_TAG = 0x39;

    private static final int X_TAG = 0x35;

    private static final int Y_TAG = 0x36;

    protected ScreenEventListener eventListener;

    // private static final int END_TAG = 0x36;

    // private int currenScanCode;

    private int x, y;

    /**
     * Constructor
     * 
     * @param eventType
     */
    public ScreenEventProcess() {
        super(InputEventType.EV_ABS);
    }

    /**
     * Constructor
     * 
     * @param eventType
     */
    public ScreenEventProcess(ScreenEventListener eventListener) {
        super(InputEventType.EV_ABS);
        this.eventListener = eventListener;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.mbre.agent.monitor.event.InputEventProcess#selfProcess(com.aspire.mbre.agent.monitor.event.InputEvent)
     */
    @Override
    public boolean selfProcess(InputEvent inputEvent) {
        switch (inputEvent.getScanCode()) {
        case INIT_TAG:
            x = y = 0;
            break;
        case X_TAG:
            x = inputEvent.getValue();
            break;
        case Y_TAG:
            y = inputEvent.getValue();
            notifyTouch(x, y);
            break;
        default:
            break;
        }
        return true;
    }

    /**
     * 通知
     * 
     * @param x
     * @param y
     * @return
     */
    private boolean notifyTouch(int x, int y) {
        // Log.d(TAG, "x=" + x + " y=" + y);
        if (null != this.eventListener)
            this.eventListener.onTouch(x, y);
        return true;
    }

    public ScreenEventListener getEventListener() {
        return eventListener;
    }

    public void setEventListener(ScreenEventListener eventListener) {
        this.eventListener = eventListener;
    }

    /**
     * 屏幕点击监听
     * <p>
     * The class <code>ScreenEventListener</code>
     * 
     * @author ouyangfeng
     * @version 1.0
     */
    public interface ScreenEventListener {

        /**
         * 点击屏幕
         * 
         * @param x
         *            点击的X坐标
         * 
         * @param y
         *            点击的Y坐标
         */
        public void onTouch(int x, int y);

    }

}
