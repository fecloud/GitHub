/**
 * @(#) IDeviceDao.java Created on 2012-7-16
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.dao;

import java.util.List;

import com.aspire.common.exception.MException;
import com.aspire.sqmp.mobilemanager.entity.Device;

/**
 * The class <code>IDeviceDao</code>
 *
 * @author linjunsui
 * @version 1.0
 */
public interface IDeviceDao {

    /**
     * load single device 
     * @param path
     * @throws MException
     */
    public Device loadDevice(String path) throws MException;
    
    /**
     * load devices from path
     * @param path path 
     * @throws MException while error
     */
    public List<Device> loadDevices(String path) throws MException;
    
    /**
     * load device from path
     * @param path path to save the devices
     * @param deviceList device list to save 
     * @throws MException while error
     */
    public void saveDevices(String path, List<Device> deviceList) throws MException;
    
    /**
     * load device from path
     * @param path path to save the devices
     * @param device device to save 
     * @throws MException while error
     */
    public void saveDevice(String path, Device device) throws MException;
    
    /**
     * delete device file
     * @param path file path 
     * @param device device to delete
     * @throws MException while error
     */
    public void deleteDevice(String path, Device device) throws MException;
}
