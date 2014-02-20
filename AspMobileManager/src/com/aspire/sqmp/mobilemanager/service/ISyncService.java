/**
 * @(#) ISyncService.java Created on 2012-7-16
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.service;

import com.aspire.common.exception.MException;

/**
 * The class <code>ISyncService</code>
 *
 * @author linjunsui
 * @version 1.0
 */
public interface ISyncService {

    /**
     * 获取密钥
     * @throws MException
     */
//    public void downloadPassword() throws MException;
    
    /**
     * 获取拨测结果的响应文件
     * @throws MException
     */
//    public void downloadResultResponse() throws MException;
    
    /**
     * 上传拨测结果
     * @throws MException
     */
    public void uploadResult(int position) throws MException;
    
    /**
     * 下载业务指标
     * @throws MException
     */
//    public void downloadServicekey() throws MException;
    
    /**
     * 下载脚本
     * @throws MException
     */
    public void downloadCase(int position) throws MException;
    
    /**
     * 下载任务
     * @throws MException
     */
    public void downloadTask(int position) throws MException;
    
    /**
     * 同步任务文件到手机端
     * @throws MException
     */
    public void syncTask(int position) throws MException;
    
    /**
     * 同步脚本到手机端
     * @throws MException
     */
    public void syncScript(int position) throws MException;
    
    /**
     * 从手机端获取拨测结果文件
     * @throws MException
     */
    public boolean syncResult(int position) throws MException;
}
