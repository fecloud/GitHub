/**
 * @(#) AdbDeviceState.java Created on 2012-7-24
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.service.adb;


/**
 * The class <code>AdbDeviceState</code> The state of a device.
 * 
 * @author wuyanlong
 * @version 1.0
 */
public enum DeviceAdbState {
    BOOTLOADER("bootloader"), 
    OFFLINE("offline"), 
    ONLINE("device"); 

    private String mState;
    /**
     * Constructor
     * @param state
     */
    DeviceAdbState(String state) {
        mState = state;
    }

    /**
     * Returns a {@link DeviceAdbState} from the string returned by <code>adb devices</code>.
     * 
     * @param state
     *            the device state.
     * @return a {@link DeviceAdbState} object or <code>null</code> if the state is unknown.
     */
    public static DeviceAdbState getState(String state) {
        for (DeviceAdbState deviceState : values()) {
            if (deviceState.mState.equals(state)) {
                return deviceState;
            }
        }
        return null;
    }
    /**
     * Gettor Name of state
     * @return
     */
    public String getStateName(){
        return mState;
    }
}
