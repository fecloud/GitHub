/**
 * @(#) RemoteDeviceManager.java Created on 2012-7-16
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspire.common.exception.ExceptionHandler;
import com.aspire.sqmp.mobilemanager.service.adb.DeviceAdbState;
import com.aspire.sqmp.mobilemanager.service.adb.AdbHelper;

/**
 * The class <code>RemoteDeviceManager</code>
 * 
 * @author linjunsui
 * @version 1.0
 */
public class RemoteDeviceManager {
    /**
     * logger
     */
    protected Logger logger = LoggerFactory.getLogger(RemoteDeviceManager.class);
    /**
     * singleton instance
     */
    protected static RemoteDeviceManager instance;

    /**
     * deviceList
     */
    protected Hashtable<String, RemoteDevice> deviceMap = new Hashtable<String, RemoteDevice>();

    /**
     * deviceDetectThread
     */
    protected Thread deviceDetectThread;
    /**
     * the list of RemoteDevicesListener
     */
    protected List<IRemoteDevicesChangeListener> devicesChangeListener = new ArrayList<RemoteDeviceManager.IRemoteDevicesChangeListener>();
    /**
     * Lock of listener
     */
    protected Object listenerLock = devicesChangeListener;

    /**
     * Constructor
     */
    private RemoteDeviceManager() {
        super();
        deviceDetectThread = new DeviceDetectThread();
        addDeviceChangeListener(new DefaultRemoteDeviceListener());
        deviceDetectThread.start();
    }

    /**
     * Getter of instance
     * 
     * @return the instance
     */
    public static RemoteDeviceManager getInstance() {
        if (instance == null) {
            instance = new RemoteDeviceManager();
        }
        return instance;
    }

    /**
     * check the device is connected
     * 
     * @param adbDeviceName
     * @return
     */
    public boolean isConnected(String adbDeviceName) {
        // check remote device socket connect
        RemoteDevice remoteDevice = deviceMap.get(adbDeviceName);
        if (remoteDevice == null)
            return false;
        if (!remoteDevice.isAdbStateOnline())
            return false;
        return remoteDevice.isConnected();
    }

    /**
     * get Remote Device
     * 
     * @param adbDeviceName
     * @return
     */
    public RemoteDevice getRemoteDevice(String adbDeviceName) {
        if (adbDeviceName == null)
            return null;
        return deviceMap.get(adbDeviceName);
    }

    /**
     * Getter of deviceMap
     * 
     * @return the deviceMap is copy
     */
    public Hashtable<String, RemoteDevice> getDeviceMap() {
        return new Hashtable<String, RemoteDevice>(deviceMap);
    }

    /**
     * Setter of deviceMap
     * 
     * @param deviceMap
     *            the deviceMap to set
     */
    public void setDeviceMap(Hashtable<String, RemoteDevice> deviceMap) {
        this.deviceMap = deviceMap;
    }

    /**
     * Add removeDeviceChangeListener
     * 
     * @param l
     */
    public void addDeviceChangeListener(IRemoteDevicesChangeListener l) {
        synchronized (listenerLock) {
            if (!devicesChangeListener.contains(l))
                devicesChangeListener.add(l);
        }
    }

    /**
     * Remove removeDeviceChangeListener
     * 
     * @param l
     */
    public void removeDeviceChangeListener(IRemoteDevicesChangeListener l) {
        synchronized (listenerLock) {
            devicesChangeListener.remove(l);
        }
    }

    /**
     * Clear removeDeviceChangeListener
     */
    public void clearDeviceChangeListener() {
        synchronized (listenerLock) {
            devicesChangeListener.clear();
        }
    }

    /**
     * Destory
     */
    public void destory() {
        ((DeviceDetectThread) deviceDetectThread).toStop();
        for (String serial : deviceMap.keySet()) {
            deviceMap.get(serial).destory();
        }
        deviceMap.clear();
    }

    /**
     * InitDeviceDetectThread
     */
    public class DeviceDetectThread extends Thread {
        /**
         * isRunning of thread
         */
        private boolean isRunning = true;
        private boolean isChange = false;

        /**
         * {@inheritDoc}
         * 
         * @see java.lang.Thread#run()
         */
        @Override
        public void run() {
            // add or remove devices
            while (isRunning) {
                try {
                    List<RemoteDevice> newDevicesList = AdbHelper.AdbGetDevices();
                    for (RemoteDevice r : newDevicesList) {
                    }
                    isChange = false;
                    // copy listener
                    IRemoteDevicesChangeListener[] changeListener;
                    synchronized (listenerLock) {
                        changeListener = devicesChangeListener
                                .toArray(new IRemoteDevicesChangeListener[devicesChangeListener.size()]);
                    }

                    // remove devices
                    Iterator<String> it = deviceMap.keySet().iterator();
                    while (it.hasNext()) {
                        String serial = it.next();
                        boolean foundMatch = false;
                        for (RemoteDevice newDevice : newDevicesList) {
                            if (serial.equals(newDevice.getAdbDeviceName())) {
                                foundMatch = true;
                                // change adbdevicestate
                                final RemoteDevice remoteDevice = deviceMap.get(serial);
                                if (remoteDevice.getDeviceAdbState() != newDevice.getDeviceAdbState()) {
                                    isChange = true;
                                    DeviceAdbState oldState = remoteDevice.getDeviceAdbState();
                                    remoteDevice.setDeviceAdbState(newDevice.getDeviceAdbState());
                                    // listener of state change
                                    for (IRemoteDevicesChangeListener l : changeListener) {
                                        try {
                                            l.onDeviceAdbStateChange(remoteDevice, oldState,
                                                    remoteDevice.getDeviceAdbState());
                                        } catch (Exception e) {
                                        }
                                    }
                                }
                                break;
                            }
                        }
                        if (foundMatch)
                            continue;
                        isChange = true;
                        final RemoteDevice remoteDevice = deviceMap.get(serial);
                        remoteDevice.destory();
                        it.remove();
                        // lsitener of remove device
                        for (IRemoteDevicesChangeListener l : changeListener) {
                            try {
                                l.onRemoveDevice(remoteDevice);
                            } catch (Exception e) {
                            }
                        }
                    }

                    // add devices
                    for (RemoteDevice newDevice : newDevicesList) {
                        if (deviceMap.get(newDevice.getAdbDeviceName()) == null) {
                            isChange = true;
                            deviceMap.put(newDevice.getAdbDeviceName(), newDevice);
                            // listener of add device
                            for (IRemoteDevicesChangeListener l : changeListener) {
                                try {
                                    l.onAddDevice(newDevice);
                                } catch (Exception e) {
                                }
                            }
                        }
                    }

                    // listener of change map
                    if (isChange) {
                        for (IRemoteDevicesChangeListener l : changeListener) {
                            try {
                                l.onDeviceMapChange(getDeviceMap());
                            } catch (Exception e) {
                            }
                        }
                    }
                    waitABit();

                } catch (IOException e) {
                    ExceptionHandler.handle(e, "Error in thread of devices monitor !");
                }
            }
        }

        /**
         * Stop thread
         */
        public void toStop() {
            isRunning = false;
        }
    }

    /**
     * Sleeps for a little bit.
     */
    private void waitABit() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e1) {
        }
    }

    /**
     * 
     * The class <code>RemoteDevicesListener</code>
     * 
     * @author wuyanlong
     * @version 1.0
     */
    public interface IRemoteDevicesChangeListener {

        /**
         * 移除一个设备
         * 
         * @param addRemoteDevice
         */
        public void onAddDevice(RemoteDevice addRemoteDevice);

        /**
         * 添加一个设备
         * 
         * @param addRemoteDevice
         */
        public void onRemoveDevice(RemoteDevice removeRemoteDevice);

        /**
         * 设备映射列表中有设备DeviceAdbState改变
         * 
         * @param remoteDevice
         * @param oldState
         * @param state
         */
        public void onDeviceAdbStateChange(RemoteDevice remoteDevice, DeviceAdbState oldState, DeviceAdbState state);

        /**
         * 
         * 在一次扫描中，有设备添加删除，或状态改变
         * <p>
         * 该线程在非ui线程运行
         * 
         * @param deviceMap
         *            当前设备映射列表
         * @param addRemoteDevice
         *            添加远程设备
         * @param remoteDevice
         *            删除的远程设备
         */
        public void onDeviceMapChange(final Map<String, RemoteDevice> deviceMap);
    }
}
