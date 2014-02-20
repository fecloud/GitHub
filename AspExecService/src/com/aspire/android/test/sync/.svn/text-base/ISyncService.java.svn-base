/**
 * @(#) ISyncService.java Created on 2012-5-9
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.sync;

import com.aspire.android.common.exception.MException;

/**
 * The class <code>ISyncService</code>
 *
 * @author linjunsui
 * @version 1.0
 */
public interface ISyncService {
	/**
	 * 设备状态：  空闲
	 */
	public static final String STATUES_IDLE = "00";
	/**
	 * 设备状态：  占用
	 */
	public static final String STATUES_OCCUPY = "01";
	/**
	 * 设备状态：  异常
	 */
	public static final String STATUES_ABNORMAL = "02";
	/**
	 * 设备状态：  离线
	 */
	public static final String STATUES_OFFLINE = "03";
	
	/**
	 * 初始化某些需要配置的信息
	 * @throws MException
	 */
	public void initialSync() throws MException;
	
    /**
     * download TestCase
     * @throws MException
     */
    public void downloadTestCase() throws MException;
    
    /**
     * download service_keys
     */
    public void downloadServiceKeys() throws MException;
    
    /**
     * download task
     * @throws MException
     */
    public void downloadTask() throws MException;
    
    /**
     * upload test Certificate
     * @throws MException
     * 
     */
    public void updatePassword() throws MException;
    
    /**
     * get upload result responser
     * @throws MException
     */
    public void uploadResultResponser() throws MException;
    
    /**
     * upload test result
     * @throws MException
     */
    public void uploadResult() throws MException;
    
    /**
     * requestDeviceInfo
     * @throws MException
     */
    public void deviceRegister() throws MException;
    
    /**
     * deviceStatusUpdate
     * @throws MException
     */
    public void deviceStatusUpdate(String status) throws MException;
    
    /**
     * @throws MException
     */
    public void deleteSyncFile() throws MException;
    
}
