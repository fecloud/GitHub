/**
 * @(#) InputEventProcess.java Created on 2012-12-13
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.mbre.agent.monitor.event;

/**
 * The class <code>InputEventProcess</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public abstract class InputEventProcess {

    // private static final String TAG = "InputEventProcess";

    protected int eventType;

    public InputEventProcess(int eventType) {
        super();
        this.eventType = eventType;
    }

    /**
     * 
     * @param inputEvent
     * @return
     */
    public boolean process(InputEvent inputEvent) {
        if (inputEvent.getType() == this.eventType) {
            return selfProcess(inputEvent);
        }
        return false;
    }

    public abstract boolean selfProcess(InputEvent inputEvent);

    // private boolean notifyKey(InputEvent inputEvent) {
    // Log.d(TAG, "notifyKey " + inputEvent);
    // return false;
    // }
    //
    // private boolean notifyABS(InputEvent inputEvent) {
    // Log.d(TAG, "notifyABS " + inputEvent);
    // return false;
    // }

}
