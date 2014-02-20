/**
 * @(#) MobileConstants.java Created on 2009-7-14
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.codec;

/**
 * The class <code>MobileConstants</code>
 *
 * @author xuyong
 * @version 1.0
 */
public interface MobileConstants {
    /**
     * Max time-field length
     */
    public final static int MAX_TIME_LEN      = 24;
    public final static int MAX_PHONE_NUM_LEN = 32;
    public final static int MAX_IMEI_LEN = 24;

    /**
     * Response status type
     */
    public final static byte STATUS_OK   = 0;
    public final static byte STATUS_FAIL = 1;

    /**
     * Monitor control type
     */
    public final static byte MONITOR_CONTROL_TYPE_START   = 0x01;   // Start
    public final static byte MONITOR_CONTROL_TYPE_STOP    = 0x02;   // Stop
    public final static byte MONITOR_CONTROL_TYPE_RESTART = 0x03;   // Restart

    /**
     * Configuration type for ConfigModifyReq
     */
    public final static byte CONFIG_TYPE_CPU_MEM   = 0x01;  // CPU内存监控子字段
    public final static byte CONFIG_TYPE_NET_RES   = 0x02;  // 网络带宽监控子字段
    public final static byte CONFIG_TYPE_BATTERY   = 0x03;  // 电量监控子字段
    public final static byte CONFIG_TYPE_EVENT     = 0x04;  // 事件监控子字段
    public final static byte CONFIG_TYPE_FREQUENCY = 0x05;  // 监控频率子字段
    public final static byte CONFIG_TYPE_TIME      = 0x06;  // 监控周期子字段
    public final static byte CONFIG_TYPE_DISK      = 0x07;  // 配置磁盘监控点

    /**
     * Event type for EventReportReq
     */
    public final static byte EVENT_TYPE_PROCESS = 0x01; // 程序事件(启动、停止、安装)
    public final static byte EVENT_TYPE_SMS     = 0x02; // 短信事件
    public final static byte EVENT_TYPE_MMS     = 0x03; // 彩信事件
    public final static byte EVENT_TYPE_PHONE   = 0x04; // 电话事件
    public final static byte EVENT_TYPE_NETWORK = 0x05; // 网络访问事件
    public final static byte EVENT_TYPE_DISK    = 0x06; // 磁盘读写删事件
    public final static byte EVENT_TYPE_INVOKE  = 0x07; // 本地程序调用事件

    public final static byte TE_PHONE_CALL_IN    = 0x01; // 电话拨入
    public final static byte TE_PHONE_CALL_OUT   = 0x02; // 电话拨出
    public final static byte TE_PHONE_CALL_IN_N  = 0x03; // 电话拨入未接
    public final static byte TE_PHONE_CALL_OUT_N = 0x04; // 电话拨出未接
    
    public final static byte TE_MMS_SEND    = 0x01; // 彩信发送
    public final static byte TE_MMS_RECEIVE = 0x02; // 彩信接收
    
    public final static byte TE_SMS_SEND    = 0x01; // 短信发送
    public final static byte TE_SMS_RECEIVE = 0x02; // 短信接收
    
    public final static byte TE_PROCESS_START = 0x01;       // 程序启动
    public final static byte TE_PROCESS_STOP      = 0x02;   // 程序停止
    public final static byte TE_PROCESS_INSTALL   = 0x03;   // 程序安装
    
    public final static byte TE_DISK_READ  = 0x01;  // 读文件
    public final static byte TE_DISK_WRITE = 0x02;  // 写文件
    
    /**
     * 长消息结束标志
     */
    public final static byte TE_FIRST_MSG = 0x02;   // 如果消息分为多个，非最后一个消息
    public final static byte TE_LAST_MSG = 0x0;     // 如果消息分为多个，最后一个消息
    public final static byte TE_SINGLE_MSG = 0x03;  // 单独消息

    /**
     * PushAnyKeyReq消息的控制类型
     */
    public final static byte TE_PUSH_ANYKEY_START = 0x01; // 启动按键模拟
    public final static byte TE_PUSH_ANYKEY_STOP = 0x00;  // 停止按键模拟
    
    /**
     * Drive type
     */
    public final static byte TE_DRIVE_NANDFlash = 0x09;
    public final static byte TE_DRIVE_FLASH = 0x06;
    public final static byte TE_DRIVE_RAM  = 0X05;
    public final static byte TE_DRIVE_HARDISK = 0x03;

    /**
     * Event monitor point
     */
    public final static int TE_PROCESS_START_STOP_POINT = 0x00000001; // 第1位：程序启动/停止事件
    public final static int TE_SMS_SEND_RECV_POINT      = 0x00000002; // 第2位：短信收发事件
    public final static int TE_MMS_SEND_RECV_POINT      = 0x00000004; // 第3位：彩信收发事件
    public final static int TE_CALL_POINT               = 0x00000008; // 第4位：电话拨入/拨出事件 
    public final static int TE_NET_ACCESS_POINT         = 0x00000010; // 第5位：网络访问事件
    public final static int TE_DISK_POINT               = 0x00000020; // 第6位：磁盘读写事件
    public final static int TE_PROCESS_INSTALL_POINT    = 0x00000040; // 第7位：程序安装事件
    public final static int TE_PROCESS_INVOKE_POINT     = 0x00000080; // 第8位：本地程序调用事件

    /**
     * Message types
     */
    // Data Query
    public final static int PROCESS_QUERY_REQ = 0x01;           // ProcessQueryReq
    public final static int PROCESS_QUERY_RESP = 0x02;          // ProcessQueryResp 
    // Engine control
    public final static int CONFIG_MODIFY_REQ = 0x03;           // ConfigModifyReq
    public final static int CONFIG_MODIFY_RESP = 0x04;          // ConfigModifyResp
    public final static int MONITOR_CONTROL_REQ = 0x05;         // MonitorControlReq
    public final static int MONITOR_CONTROL_RESP = 0x06;        // MonitorControlResp
    public final static int MOBILE_CONNECT_REQ = 0x21;          // MobileConnectReq
    public final static int MOBILE_CONNECT_RESP = 0x22;         // MobileConnectResp
    // Monitor data
    public final static int PERF_DATA_REPORT_REQ = 0x07;        // PerformanceDataReportReq
    public final static int PERF_DATA_REPORT_RESP = 0x08;       // PerformanceDataReportResp
    public final static int EVENT_REPORT_REQ = 0x09;            // EventReportReq
    public final static int EVENT_REPORT_RESP = 0x0a;           // EventReportResp
    // Query message
    public final static int DRIVE_LIST_QUERY_REQ = 0x0b;        // DriveListQueryReq
    public final static int DRIVE_LIST_QUERY_RESP = 0x0c;       // DriveListQueryResp
    public final static int DRIVE_SNAPSHOT_REQ = 0x0d;          // DriveSnapshotQueryReq
    public final static int DRIVE_SNAPSHOT_RESP = 0x0e;         // DriveSnapshotQueryResp
    // Simulation message
    public final static int PUSH_ANY_KEY_REQ = 0x2001;          // PushAnyKeyReq
    public final static int PUSH_ANY_KEY_RESP = 0x2002;         // PushAnyKeyResp
    
    public final static String PROC_PATH = "/proc";
}
