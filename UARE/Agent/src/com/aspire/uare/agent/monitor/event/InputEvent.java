/**
 * @(#) InputEvent.java Created on 2012-11-8
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.uare.agent.monitor.event;

/**
 * The class <code>InputEvent</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class InputEvent {

    private int deviceId;

    private int type;

    private int scanCode;

    private int keyCode;

    private int value;

    public InputEvent() {
    }

    public InputEvent(int deviceId, int type, int scanCode, int keyCode, int value) {
        super();
        this.deviceId = deviceId;
        this.type = type;
        this.scanCode = scanCode;
        this.keyCode = keyCode;
        this.value = value;
    }

    public void setFieldValues(int deviceId, int type, int scanCode, int keyCode, int value) {
        this.deviceId = deviceId;
        this.type = type;
        this.scanCode = scanCode;
        this.keyCode = keyCode;
        this.value = value;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getScanCode() {
        return scanCode;
    }

    public void setScanCode(int scanCode) {
        this.scanCode = scanCode;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     * type 表示的设备
     * The class <code>InputEventType</code>
     *
     * @author ouyangfeng
     * @version 1.0
     */
    public static final class InputEventType {
        
        /**
         * 键盘
         */
        public static final int EV_KEY = 0x01;
        
        /**
         * 鼠标
         */
        public static final int EV_ABS = 0x3;
        
    }

}
