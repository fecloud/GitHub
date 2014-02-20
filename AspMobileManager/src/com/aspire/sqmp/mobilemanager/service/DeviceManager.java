/**
 * @(#) DeviceManager.java Created on 2012-7-16
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.service;

import java.util.ArrayList;
import java.util.List;

import com.aspire.common.exception.MException;
import com.aspire.sqmp.mobilemanager.dao.DeviceDao;
import com.aspire.sqmp.mobilemanager.dao.IDeviceDao;
import com.aspire.sqmp.mobilemanager.entity.Device;

/**
 * The class <code>DeviceManager</code>
 *
 * @author linjunsui
 * @version 1.0
 */
public class DeviceManager {
    
    /**
     * singleton instance
     */
    protected static DeviceManager instance;
    
    /**
     * device list
     */
    protected List<Device> deviceList;
    
    /**
     * base path of the device
     */
    protected String basePath;
    
    /**
     * device Dao
     */
    protected IDeviceDao deviceDao;
    
    /**
     * Constructor
     */
    private DeviceManager() {
        super();
        deviceList = new ArrayList<Device>();
        deviceDao = new DeviceDao();
    }
    
    /**
     * Getter of deviceList
     * @return the deviceList
     */
    public List<Device> getDeviceList() {
        return deviceList;
    }

    /**
     * Setter of deviceList
     * @param deviceList the deviceList to set
     */
    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }
    
    /**
     * Getter of instance
     * @return the instance
     */
    public static DeviceManager getInstance() {
        if (instance == null) {
            instance = new DeviceManager();
        }
        return instance;
    }
    
    /**
     * load device from path
     * @throws MException while error
     */
    public void loadDevice(String path) throws MException {
        this.basePath = path;
        deviceList = deviceDao.loadDevices(path);
    }
    
    /**
     * save devices
     * @throws MException while error
     */
    public void saveDevices() throws MException {
        deviceDao.saveDevices(basePath, deviceList);
    }
    
    
    /**
     * save single device
     * @throws MException while error
     */
    public void saveDevice(Device device) throws MException {
        deviceList.add(device);
        deviceDao.saveDevice(this.basePath, device);
    }
    
    /**
     * update single device
     * @throws MException while error
     */
    public void updateDevice(int position, Device device) throws MException {
        deviceList.set(position, device);
        deviceDao.saveDevice(this.basePath, device);
    }
    /**
     * delete device file
     * @param name file name
     * @throws MException while error
     */
    public void deleteDevice(int position, Device device) throws MException {
        deviceDao.deleteDevice(basePath, device);
        deviceList.remove(position);
        
    }
}
