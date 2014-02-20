/**
 * @(#) RegisterRemoteDeviceListener.java Created on 2012-7-24
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.service;

import java.io.IOException;
import java.util.Map;

import com.aspire.common.exception.MException;
import com.aspire.sqmp.mobilemanager.service.RemoteDeviceManager.IRemoteDevicesChangeListener;
import com.aspire.sqmp.mobilemanager.service.adb.DeviceAdbState;

/**
 * The class <code>RegisterRemoteDeviceListener</code>
 * 
 * @author wuyanlong
 * @version 1.0
 */
public class DefaultRemoteDeviceListener implements IRemoteDevicesChangeListener {

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.sqmp.mobilemanager.service.RemoteDeviceManager.IRemoteDevicesChangeListener#onDeviceAdbStateChange(com.aspire.sqmp.mobilemanager.service.RemoteDevice,
     *      com.aspire.sqmp.mobilemanager.service.adb.DeviceAdbState,
     *      com.aspire.sqmp.mobilemanager.service.adb.DeviceAdbState)
     */
    @Override
    public void onDeviceAdbStateChange(RemoteDevice remoteDevice, DeviceAdbState oldState, DeviceAdbState state) {
        if (remoteDevice.isAdbStateOnline()) {
            try {
                if (!remoteDevice.checkConnect())
                    handleErrorOfConnection(remoteDevice);
            } catch (IOException e) {
                // this is error of connect socket
                handleErrorOfConnection(remoteDevice);
            } catch (MException e) {
                // this is error of adb forward ;
                handleErrorOfAdbForward(remoteDevice);
            }
        }
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.sqmp.mobilemanager.service.RemoteDeviceManager.IRemoteDevicesChangeListener#onAddDevice(com.aspire.sqmp.mobilemanager.service.RemoteDevice)
     */
    @Override
    public void onAddDevice(RemoteDevice addRemoteDevice) {
        if (addRemoteDevice.isAdbStateOnline()) {
            try {
                if (!addRemoteDevice.checkConnect())
                    handleErrorOfConnection(addRemoteDevice);
            } catch (IOException e) {
                // this is error of connect socket
                handleErrorOfConnection(addRemoteDevice);
            } catch (MException e) {
                // this is error of adb forward ;
                handleErrorOfAdbForward(addRemoteDevice);
            }
        } else {
            handleDeviceAdbOutLine(addRemoteDevice);
        }
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.sqmp.mobilemanager.service.RemoteDeviceManager.IRemoteDevicesChangeListener#onRemoveDevice(com.aspire.sqmp.mobilemanager.service.RemoteDevice)
     */
    @Override
    public void onRemoveDevice(RemoteDevice addRemoteDevice) {
        // nothing
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.sqmp.mobilemanager.service.RemoteDeviceManager.IRemoteDevicesChangeListener#onDeviceMapChange(java.util.Map)
     */
    @Override
    public void onDeviceMapChange(Map<String, RemoteDevice> deviceMap) {
        // nothing
    }

    /**
     * handle can not connect AspExeService
     */
    private void handleErrorOfConnection(RemoteDevice remoteDevice) {
        // DOTO
        // 造成连接不上原因:
        // 软件未安装
        // 软件服务未启动
        System.out.println("-----------------设备无法连接:" + remoteDevice.getAdbDeviceName());
    }
    
    /**
     * handle can not exe adb forward
     * 
     * @param remoteDevice
     */
    private void handleErrorOfAdbForward(RemoteDevice remoteDevice) {
        // DOTO
        System.out.println("--------------------------设备adb forward 失败:" + remoteDevice.getAdbDeviceName());
    }

    /**
     * handle the device of adb OutLine
     * 
     * @param remoteDevice
     */
    private void handleDeviceAdbOutLine(RemoteDevice remoteDevice) {
        // DOTO
        System.out.println("-------------------------adb outline:" + remoteDevice.getAdbDeviceName());
    }
}
