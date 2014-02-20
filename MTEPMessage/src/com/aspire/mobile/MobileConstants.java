/**
 * @(#) MobileConstants.java Created on 2008-12-30
 *
 * Copyright (c) 2008 Aspire. All Rights Reserved
 */
package com.aspire.mobile;

/**
 * The class <code>MobileConstants</code>
 * 
 * @author xuyong
 * @version 1.0
 */
public interface MobileConstants {

    /**
     * Semicolon
     */
    public final static char SEPARATOR_SEMICOLON = ';';

    /**
     * Separator of emulator command data for sms's data field
     */
    public final static char SEPARATOR_EMULATOR_CMD_SMS_DATA = '?';

    /**
     * Max time-field length
     */
    public final static int MAX_ID_LEN = 32;
    public final static int MAX_TIME_LEN = 24;
    public final static int MAX_PHONE_NUM_LEN = 32;
    public final static int MAX_IMEI_LEN = 24;

    /**
     * Message types
     */
    // Engine control
    public final static int CONFIG_MODIFY_REQ = 0x03; // ConfigModifyReq
    public final static int CONFIG_MODIFY_RESP = 0x04; // ConfigModifyResp
    public final static int MONITOR_CONTROL_REQ = 0x05; // MonitorControlReq
    public final static int MONITOR_CONTROL_RESP = 0x06; // MonitorControlResp
    public final static int MOBILE_CONNECT_REQ = 0x21; // MobileConnectReq
    public final static int MOBILE_CONNECT_RESP = 0x22; // MobileConnectResp
    public final static int PERF_MONITOR_CONTROL_REQ = 0x23; // PerfMonitorControlReq
    public final static int PERF_MONITOR_CONTROL_RESP = 0x24; // PerfMonitorControlResp

    // Monitor data
    public final static int PERF_DATA_REPORT_REQ = 0x07; // PerformanceDataReportReq
    public final static int PERF_DATA_REPORT_RESP = 0x08; // PerformanceDataReportResp
    public final static int EVENT_REPORT_REQ = 0x09; // EventReportReq
    public final static int EVENT_REPORT_RESP = 0x0a; // EventReportResp

    // Data Query
    public final static int PROCESS_QUERY_REQ = 0x01; // ProcessQueryReq
    public final static int PROCESS_QUERY_RESP = 0x02; // ProcessQueryResp
    public final static int FILE_INFO_QUERY_REQ = 0x31; // FileInfoQueryReq
    public final static int FILE_INFO_QUERY_RESP = 0x32; // FileInfoQueryResp
    public final static int DIR_INFO_QUERY_REQ = 0x33; // DirInfoQueryReq
    public final static int DIR_INFO_QUERY_RESP = 0x34; // DirInfoQueryResp
    public final static int CONTACTS_QUERY_REQ = 0x35; // ContactsQueryReq
    public final static int CONTACTS_QUERY_RESP = 0x36; // ContactsQueryResp
    public final static int CONTACT_GROUP_QUERY_REQ = 0x37; // ContactGroupQueryReq
    public final static int CONTACT_GROUP_QUERY_RESP = 0x38; // ContactGroupQueryResp
    public final static int CALENDAR_QUERY_REQ = 0x39; // CalendarQueryReq
    public final static int CALENDAR_QUERY_RESP = 0x3A; // CalendarQueryResp
    public final static int CALL_RECORD_QUERY_REQ = 0x3B; // CallRecordQueryReq
    public final static int CALL_RECORD_QUERY_RESP = 0x3C; // CallRecordQueryResp
    public final static int MESSAGE_BOX_QUERY_REQ = 0x3D; // MessageBoxQueryReq
    public final static int MESSAGE_BOX_QUERY_RESP = 0x3E; // MessageBoxQueryResp
    public final static int MESSAGE_STATISTIC_QUERY_REQ = 0x3F; // MessageStatisticQueryReq
    public final static int MESSAGE_STATISTIC_QUERY_RESP = 0x40;// MessageStatisticQueryResp

    // Data Update
    public final static int FILE_OPERATE_REQ = 0x61; // FileOperateReq
    public final static int FILE_OPERATE_RESP = 0x62; // FileOperateResp
    public final static int DELETE_CONTACTS_REQ = 0x63; // DeleteContactsReq
    public final static int DELETE_CONTACTS_RESP = 0x64; // DeleteContactsResp
    public final static int DELETE_CALENDAR_ITEM_REQ = 0x65; // DeleteCalendarItemReq
    public final static int DELETE_CALENDAR_ITEM_RESP = 0x66; // DeleteCalendarItemResp
    public final static int DELETE_CALL_RECORD_REQ = 0x67; // DeleteCallRecordReq
    public final static int DELETE_CALL_RECORD_RESP = 0x68; // DeleteCallRecordResp
    public final static int DELETE_MESSAGE_REQ = 0x69; // DeleteMessageReq
    public final static int DELETE_MESSAGE_RESP = 0x70; // DeleteMessageResp
    public final static int ADD_MESSAGE_REQ = 0x71; // AddMessageReq
    public final static int ADD_MESSAGE_RESP = 0x72; // AddMessageResp
    public final static int ADD_CALL_RECORD_REQ = 0x73; // AddCallRecordReq
    public final static int ADD_CALL_RECORD_RESP = 0x74; // AddCallRecordResp
    public final static int ADD_CALENDAR_ITEM_REQ = 0x75; // AddCalendarItemReq
    public final static int ADD_CALENDAR_ITEM_RESP = 0x76; // AddCalendarItemResp
    public final static int ADD_CONTACTS_REQ = 0x77; // AddContactsReq
    public final static int ADD_CONTACTS_RESP = 0x78; // AddContactsResp

    // Mobile operation message
    public final static int CALL_OPERATION_REQ = 0x101; // CallOperationReq
    public final static int CALL_OPERATION_RESP = 0x102; // CallOperationResp
    public final static int WAP_OPERATION_REQ = 0x103; // WapOperationReq
    public final static int WAP_OPERATION_RESP = 0x104; // WapOperationResp
    public final static int APP_OPERATION_REQ = 0x105; // ApplicationOperationReq
    public final static int APP_OPERATION_RESP = 0x106; // ApplicationOperationResp
    public final static int SMS_OPERATION_REQ = 0x107; // SMSOperationReq
    public final static int SMS_OPERATION_RESP = 0x108; // SMSOperationResp
    public final static int INPUT_OPERATION_REQ = 0x109; // InputOperationReq
    public final static int INPUT_OPERATION_RESP = 0x110; // InputOperationResp
    public final static int VIEWER_OPERATION_REQ = 0x111; // ViewerOperationReq
    public final static int VIEWER_OPERATION_RESP = 0x112; // ViewerOperationResp
    public final static int GRAB_NETWORKPACKAGE_REQ = 0x113; // GrabNetworkPackageReq
    public final static int GRAB_NETWORKPACKAGE_RESP = 0x114;// GrabNetworkPackageResp

    // Emulator command
    public final static int EMULATOR_TELNET_CMD_REQ = 0x1001; // EmulatorTelnetCmdReq
    public final static int EMULATOR_TELNET_CMD_RESP = 0x1002; // EmulatorTelnetCmdResp
    public final static int EMULATOR_RAW_CMD_REQ = 0x1003; // EmulatorRawCmdReq
    public final static int EMULATOR_RAW_CMD_RESP = 0x1004; // EmulatorRawCmdResp

    /**
     * Event type for EventReportReq
     */
    public final static byte EVENT_TYPE_PROCESS = 0x01; // 程序事件(启动、停止、安装)
    public final static byte EVENT_TYPE_SMS = 0x02; // 短信事件
    public final static byte EVENT_TYPE_MMS = 0x03; // 彩信事件
    public final static byte EVENT_TYPE_PHONE = 0x04; // 电话事件
    public final static byte EVENT_TYPE_NETWORK = 0x05; // 网络访问事件
    public final static byte EVENT_TYPE_DISK = 0x06; // 磁盘读写删事件
    public final static byte EVENT_TYPE_INVOKE = 0x07; // 本地程序调用事件

    /**
     * Configuration type for ConfigModifyReq
     */
    public final static byte CONFIG_TYPE_CPU_MEM = 0x01; // CPU内存监控子字段
    public final static byte CONFIG_TYPE_NET_RES = 0x02; // 网络带宽监控子字段
    public final static byte CONFIG_TYPE_BATTERY = 0x03; // 电量监控子字段
    public final static byte CONFIG_TYPE_EVENT = 0x04; // 事件监控子字段
    public final static byte CONFIG_TYPE_FREQUENCY = 0x05; // 监控频率子字段
    public final static byte CONFIG_TYPE_TIME = 0x06; // 监控周期子字段

    /**
     * Response status type
     */
    public final static byte STATUS_OK = 0;
    public final static byte STATUS_FAIL = 1;

    /**
     * File existent/inexistent flag
     */
    public final static byte FLAG_INEXISTENT = 0x00; // File inexistent
    public final static byte FLAG_EXISTENT = 0x01; // File existent

    /**
     * File type
     */
    public final static byte FILE_TYPE_ARCHIVE = 0x00; // Archive
    public final static byte FILE_TYPE_DIRECTORY = 0x01; // Directory

    /**
     * Alarm switch
     */
    public final static byte ALARM_SWITCH_OFF = 0x00; // Off
    public final static byte ALARM_SWITCH_ON = 0x01; // On

    /**
     * Event recurrent interval
     */
    public final static byte CAL_EVENT_RECURRENT_NO_REPEAT = 0x00; // No Repeat
    public final static byte CAL_EVENT_RECURRENT_DAILY = 0x01; // Daily
    public final static byte CAL_EVENT_RECURRENT_EVERY_WEEKDAY = 0x02; // Every Weekday
    public final static byte CAL_EVENT_RECURRENT_WEEKLY_ON_DAY = 0x03; // Weekly On Day
    public final static byte CAL_EVENT_RECURRENT_MONTHLY_ON_DAY = 0x04; // Monthly On Day
    public final static byte CAL_EVENT_RECURRENT_MONTHLY_ON_DAY_COUNT = 0x05; // Monthly On Day Count
    public final static byte CAL_EVENT_RECURRENT_YEARLY = 0x06; // Yearly

    /**
     * Call type
     */
    public final static byte CALL_TYPE_INCOMING = 0x01; // Incoming call
    public final static byte CALL_TYPE_OUTGOING = 0x02; // Outgoing call
    public final static byte CALL_TYPE_MISSED = 0x03; // Missed call

    /**
     * Message type
     */
    public final static byte MESSAGE_TYPE_SMS = 0x01; // SMS
    public final static byte MESSAGE_TYPE_MMS = 0x02; // MMS
    public final static byte MESSAGE_TYPE_EMAIL = 0x03; // Email

    /**
     * Priority of MMS
     */
    public final static byte MMS_PRI_LOW = 0x00; // Low Priority
    public final static byte MMS_PRI_NORMAL = 0x01; // Normal Priority
    public final static byte MMS_PRI_HIGH = 0x02; // High Priority

    /**
     * Message direction type
     */
    public final static byte MESSAGE_DIRECTION_RECV = 0x01; // Receive
    public final static byte MESSAGE_DIRECTION_SEND = 0x02; // Send

    /**
     * Message un/readed flag
     */
    public final static byte FLAG_UNREADED = 0x00; // Unreaded
    public final static byte FLAG_READED = 0x01; // Readed

    /**
     * Message attachment flag
     */
    public final static byte FLAG_WITHOUT = 0x00; // Without
    public final static byte FLAG_WITH = 0x01; // With

    /**
     * File operation type
     */
    public final static byte FILE_OPERATION_TYPE_CREATE = 0x01; // Create
    public final static byte FILE_OPERATION_TYPE_DELETE = 0x02; // Delete

    /**
     * Monitor control type
     */
    public final static byte MONITOR_CONTROL_TYPE_START = 0x01; // Start
    public final static byte MONITOR_CONTROL_TYPE_STOP = 0x02; // Stop
    public final static byte MONITOR_CONTROL_TYPE_RESTART = 0x03; // Restart

    /**
     * Monitor point switch
     */
    public final static int MONITOR_OFF = 0x0000; // All point off
    public final static int MONITOR_CPU_ON = 0x0001; // Cpu monitor on
    public final static int MONITOR_MEM_ON = 0x0002; // Memory monitor on
    public final static int MONITOR_PROC_ON = 0x0004; // Process monitor on
    public final static int MONITOR_NET_ON = 0x0008; // Network monitor on
    public final static int MONITOR_BAT_ON = 0x0010; // Battery monitor on

    /**
     * Call operation action type
     */
    public final static byte CALL_OPERATION_ACTION_HANG_UP = 0x01; // Hang up call
    public final static byte CALL_OPERATION_ACTION_PICK_UP = 0x02; // Pick up call
    public final static byte CALL_OPERATION_ACTION_INITIATE = 0x03; // Initiate call

    /**
     * Wap operation action type
     */
    public final static byte WAP_OPERATION_ACTION_START = 0x01; // Start Wap
    public final static byte WAP_OPERATION_ACTION_WAP_DRIVER = 0x02; // wap driver
    public final static byte WAP_OPERATION_ACTION_FIND = 0x03; // find by wap page
    public final static byte WAP_OPERATION_ACTION_CLICK = 0x04; // click the text
    public final static byte WAP_OPERATION_ACTION_CLOSE_DRIVER = 0x05; // colsed wap

    /**
     * Viewer operation action type
     */
    public final static byte VIEWER_OPERATION_ACTION_GET = 0x01; // get viewer tree
    public final static byte VIEWER_OPERATION_ACTION_SAVE = 0x02; // save viewer tree to a file

    /**
     * grab network package action type
     */
    public final static byte GRAB_NETWORKPACKAGE_ACTION_START = 0x01;// start the grab network package
    public final static byte GRAB_NETWORKPACKAGE_ACTION_STOP = 0x02;// stop the grab network package

    /**
     * Application operation action type
     */
    public final static byte APP_OPERATION_ACTION_INSTALL = 0x01; // install app
    public final static byte APP_OPERATION_ACTION_UNINSTALL = 0x02; // uninstall app
    public final static byte APP_OPERATION_ACTION_START = 0x03; // start app
    public final static byte APP_OPERATION_ACTION_STARTCLASS = 0x04; // start app with className
    public final static byte APP_OPERATION_ACTION_STOP = 0x05; // stop app
    public final static byte APP_OPERATION_ACTION_STOPCLASS = 0x06; // stop app with className

    /**
     * Emulator command action type
     */
    public final static byte EMULATOR_CMD_ACTION_CALL_IN = 0x01; // Call emulator
    public final static byte EMULATOR_CMD_ACTION_PICK_UP = 0x02; // Pick up call
    public final static byte EMULATOR_CMD_ACTION_HOLD = 0x03; // Hold call
    public final static byte EMULATOR_CMD_ACTION_RESUME = 0x04; // Hold Call resume
    public final static byte EMULATOR_CMD_ACTION_HANG_UP = 0x05; // Hang up call
    public final static byte EMULATOR_CMD_ACTION_SEND_SMS = 0x31; // Send SMS to Emulator

    /**
     * 分包flag
     */
    public final static byte TE_FIRST_MSG = 0x02; // 如果消息分为多个，非最后一个消息
    public final static byte TE_LAST_MSG = 0x01; // 如果消息分为多个，最后一个消息
    public final static byte TE_SINGLE_MSG = 0x03; // 单独消息

    /**
     * the action of SMS_OPERATION
     */
    public final static byte SMS_OPERATION_ACTION_UPDATE = 0x01; // 更新短信
    public final static byte SMS_OPERATION_ACTION_DELETE = 0x02; // 删除短信

    /**
     * the action of FILE_OPERATION
     */
    public final static byte FILE_OPERATION_ACTION_DELETE = 0x1;// 删除文件
    public final static byte FILE_OPERATION_ACTION_GETSIZE = 0x2; // 获取文件size
    /**
     * the value of foldname
     */

    public final static String FOLDNAME_INBOX = "inbox"; // 收件箱
    public final static String FOLDNAME_SENTBOX = "sentbox"; // 发件箱
    public final static String FOLDNAME_ALL = "all"; // 所有

    /**
     * the value of input method
     */
    public final static byte INPUT_TYPE_ADD = 0X01; // 增加文本
    public final static byte INPUT_TYPE_DEL = 0X02; // 删除文本

    /**
     * the methods of web key
     */
    public final static String WAP_ID = "id";
    public final static String WAP_NAME = "name";
    public final static String WAP_CLASSNAME = "className";
    public final static String WAP_TAGNAME = "tagName";
    public final static String WAP_LINKTEXT = "linkText";
    public final static String WAP_PARTIALLINKTEXT = "partialLinkText";
    public final static String WAP_CSS = "cssSelector";

}
