/**
 * @(#) RequestCommand.java Created on 2012-7-25
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.service.request;

/**
 * The class <code>RequestCommand</code>
 * 
 * @author wuyanlong
 * @version 1.0
 */
public interface AspCommand {
    /***
     * 向设备取东西以G_开头
     * <p>
     * 向设配设置东西以S_开头
     */

    /** 请求 获取task */
    public int S_TASK_REQ = 100;
    /** 响应 获取task */
    public int S_TASK_RESP = 101;

    /** 请求 传送Case */
    public int S_CASE_REQ = 110;
    /** 响应 传送Case */
    public int S_CASE_RESP = 111;
    /** 请求 获取Case */
    public int G_CASE_REQ = 112;
    /** 响应 获取Case */
    public int G_CASE_RESP = 113;

    /** 请求 获取指标 */
    public int S_SERVICEKEY_REQ = 120;
    /** 响应 获取指标 */
    public int S_SERVICEKEY_RESP = 121;

    /** 请求 获取密钥 */
    public int S_PASSWORD_REQ = 130;
    /** 响应 获取密钥 */
    public int S_PASSWORD_RESP = 131;

    /** 请求 获取配置信息 */
    public int S_SETTING_REQ = 140;
    /** 响应 获取配置信息 */
    public int S_SETTING_RESP = 141;
    /** 请求 设置配置信息 */
    public int G_SETTING_REQ = 142;
    /** 响应 设置配置信息 */
    public int G_SETTING_RESP = 143;

    /** 请求 获取执行任务结果 请求 */
    public int G_RESULT_REQ = 150;
    /** 响应 获取任务结果 */
    public int G_RESULT_RESP = 151;

    /** 请求 存储空间地址 */
    public int G_STORE_PATH_REQ = 160;
    /** 响应 存储空间地址 */
    public int G_STORE_PATH_RESP = 161;

}
