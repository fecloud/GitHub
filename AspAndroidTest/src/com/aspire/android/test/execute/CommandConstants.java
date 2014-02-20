/**
 * @(#) CommandConstants.java Created on 2012-4-28
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute;

/**
 * The class <code>CommandConstants</code>
 * 
 * @author linjunsui
 * @version 1.0
 */
public interface CommandConstants {

    /**
     * Type constants
     */
    public static int TYPE_GETPARAMS = 0;
    public static int TYPE_GETSETTING = 1;
    public static int TYPE_GETGLOBALVARIABLES = 2;
    public static int TYPE_SAVERESULT = 3;
    public static int TYPE_SAVETRANSACTION = 4;
    public static int TYPE_LOG = 5;
    public static int TYPE_GET_SAVERESULT = 6;
    public static int TYPE_LOGSCREEN = 7;
    public static int TYPE_OCRSCREEN = 8;
    public static int TYPE_OCRRECT = 9;
    public static int TYPE_SCREENDOWN = 10;
    public static int TYPE_SCREENUP = 11;
    public static int TYPE_SCREENMOVE = 12;
    public static int TYPE_SCREENCLICK = 13;
    public static int TYPE_KEYDOWN = 14;
    public static int TYPE_KEYUP = 15;
    public static int TYPE_VERIFYSCREEN = 16;
    public static int TYPE_FINDINSCREEN = 17;
    public static int TYPE_GETMESSAGE = 18;
    public static int TYPE_SENDMESSAGE = 19;
    public static int TYPE_SETMESSAGE = 20;
    public static int TYPE_RUNACIVITY = 21;
    public static int TYPE_INSTALL = 22;
    public static int TYPE_UNINSTALL = 23;
    public static int TYPE_GETURL = 24;
    public static int TYPE_SETPARAMS = 25;
    public static int TYPE_SETSETTING = 26;
    public static int TYPE_SETGLOBALVARIABLES = 27;
    public static int TYPE_RUNTEST = 28;
    public static int TYPE_INPUT = 29;
    public static int TYPE_RUNAPP = 30;
    public static int TYPE_SAVEOCRSCREENCERTIFICATE = 31;
    public static int TYPE_SAVEOCRRECTCERTIFICATE = 32;
    public static int TYPE_SAVEVERIFYCERTIFICATE = 33;
    public static int TYPE_SAVEFINDINCERTIFICATE = 34;
    public static int TYPE_WAPOPEN = 35;
    public static int TYPE_SCREENTOUCH = 36;
    public static int TYPE_GETMODEL = 37;
    public static int TYPE_SETDEBUGPARAMS = 38;
    public static int TYPE_GETDEBUGRESULT = 39;
    public static int TYPE_KEYCLICK = 40;
    public static int TYPE_KEYLONGCLICK = 41;
    public static int TYPE_SETALLMESSAGEREADED = 42;
    public static int TYPE_DELETEALLMESSAGE = 43;
    public static int TYPE_STARTGRABTCPPACKAGE = 44;
    public static int TYPE_STOPGRABTCPPACKAGE = 45;
    public static int TYPE_FINDWIDGET = 46;
    public static int TYPE_COUNTWIDGET = 47;
    public static int TYPE_CLICKWIDGET = 48;
    public static int TYPE_GETWIDGETTEXT = 49;
    public static int TYPE_FINDWEBWIDGET = 50;
    public static int TYPE_COUNTWEBWIDGET = 51;
    public static int TYPE_CLICKWEBWIDGET = 52;
    public static int TYPE_GETWEBWIDGETTEXT = 53;
    public static int TYPE_WAPCLOSE = 54;
    public static int TYPE_GETRESOURCE = 55;
    public static int TYPE_OCRWITHRESOURCE = 56;
    public static int TYPE_ALARM = 57;
    public static int TYPE_OCRSCREENMATCH = 58;
    public static int TYPE_OCRRECTMATCH = 59;
    public static int TYPE_SAVEOCRSCREENMATCHCERTIFICATE = 60;
    public static int TYPE_SAVEOCRRECTMATCHCERTIFICATE = 61;

    public static int TYPE_GETFILESIZE = 62;
    public static int TYPE_DELETEFILE = 63;
    public static int TYPE_SCREENMUTILDOWN = 64;
    public static int TYPE_SCREENMUTILMOVE = 65;
    public static int TYPE_SCREENMUTILUP = 66;

    public static int TYPE_SCREENZOOM = 67;
    public static int TYPE_SCREENSHRINK = 68;

    public static int TYPE_STOPAPP = 69;

    public static int TYPE_FINDINSCREENCOM = 70;

    public static int TYPE_SAVESCREEN = 71;

    public static int TYPE_FINDPICTURE = 72;

    public static int TYPE_SAVEIMAGETOCERTIFICATE = 73;

    public static int TYPE_DELETEINPUT = 74;

    /**
     * debug mode
     */
    public static String MODE_DEBUG = "MODE_DEBUG";
    public static String MODE_RUN = "MODE_RUN";

    /**
     * Key Constants
     */
    public static String KEY_CASE_NAME = "KEY_CASE_NAME";
    public static String KEY_CASE_CLASSNAME = "KEY_CASE_CLASSNAME";
    public static String KEY_CASE_TESTRESULT = "KEY_CASE_TESTRESULT";
    public static String KEY_CASE_ERRORMSG = "KEY_CASE_ERRORMSG";
    public static String KEY_CASE_INDEX = "KEY_CASE_INDEX";
    public static String KEY_CASE_GRABTCP = "KEY_CASE_GRABTCP";
    /**
     * the log save location
     */
    public static String KEY_CASE_LOGLOCATION = "KEY_CASE_LOGLOCATION";

    /**
     * the tcp dump file save location
     */
    public static String KEY_CASE_TCPDUM_LOCATION = "KEY_CASE_TCPDUM_LOCATION";
    /**
     * the certificate save location
     */
    public static String KEY_CASE_CERTIFICATE_LOCATION = "KEY_CASE_CERTIFICATE_LOCATION";

    /**
     * the case set loglevl
     */
    public static String KEY_CASE_LOG_LEVEL = "LOG_LEVEL";

    public static String KEY_CASE_ATTACHMENT_LOCTION = "KEY_CASE_ATTACHMENT_LOCTION";

    public static String KEY_SCREEN_BYTE = "KEY_SCREEN_BYTE";

    public static String KEY_CERTIFICATE = "KEY_CERTIFICATE";

    public static String KEY_TEMP_LOCATION = "KEY_TEMP_LOCATION";

    /**
     * Value Constants
     */
    public static int VALUE_FAIL = 0;
    public static int VALUE_SUCCESS = 1;
    public static int VALUE_ERROR = -1;

    /**
     * key and value
     */
    public static String KEY_KEY = "KEY_KEY";
    public static String KEY_VALUE = "KEY_VALUE";

    /**
     * key error code and key error message Constants
     */
    public static final String KEY_ERROR_CODE = "KEY_ERROR_CODE";
    public static final int KEY_ERROR_CODE_SUCESS = 0;
    public static final int KEY_ERROR_CODE_FAIL = 1;
    public static final String KEY_ERROR_MESSAGE = "KEY_ERROR_MESSAGE";

    /**
     * key point Constants
     */
    public static final String KEY_POINT_X = "KEY_POINT_X";
    public static final String KEY_POINT_Y = "KEY_POINT_Y";
    public static final String KEY_POINT_X1 = "KEY_POINT_X1";
    public static final String KEY_POINT_Y1 = "KEY_POINT_Y1";
    public static final String KEY_POINT_X2 = "KEY_POINT_X2";
    public static final String KEY_POINT_Y2 = "KEY_POINT_Y2";

    /**
     * key method result Constants
     */

    public static final String KEY_METHOD_RESULT = "KEY_METHOD_RESULT";

    /**
     * Key Constants for saveTransaction method params
     */
    public static final String KEY_SAVETRANSACTION_BEGINTIME = "KEY_SAVETRANSACTION_BEGINTIME";
    public static final String KEY_SAVETRANSACTION_ENDTIME = "KEY_SAVETRANSACTION_ENDTIME";
    public static final String KEY_SAVETRANSACTION_NAME = "KEY_SAVETRANSACTION_NAME";
    public static final String KEY_SAVETRANSACTION_VALUE = "KEY_SAVETRANSACTION_VALUE";
    public static final String KEY_SAVETRANSACTION_RESULT = "KEY_SAVETRANSACTION_RESULT";
    public static final String KEY_SAVETRANSACTION_SAVECERTIFICATE = "KEY_SAVETRANSACTION_SAVECERTIFICATE";

    /**
     * Key Constants for log method params
     */
    public static final String KEY_LOG_LOGLEVEL = "KEY_LOG_LOGLEVEL";
    public static final String KEY_LOG_MESSAGE = "KEY_LOG_MESSAGE";

    /**
     * Key Constants for ocrScreen method params
     */
    public static final String KEY_OCR_LANG = "KEY_OCR_LANG";
    public static final String KEY_OCR_EXPECT = "KEY_OCR_EXPECT";

    /**
     * Key Constants for ocrRect method params
     */
    public static final String KEY_OCRRECT_STARTX = "KEY_OCRRECT_STARTX";
    public static final String KEY_OCRRECT_STARTY = "KEY_OCRRECT_STARTY";
    public static final String KEY_OCRRECT_WIDTH = "KEY_OCRRECT_WIDTH";
    public static final String KEY_OCRRECT_HEIGHT = "KEY_OCRRECT_HEIGHT";

    /**
     * Key Constants for keys method params
     */
    public static final String KEY_KEYCODE = "KEY_KEYCODE";

    /**
     * Key Constants for verifyScreen method params
     */
    public static final String KEY_INPUTIMAGEPATH = "KEY_INPUTIMAGEPATH";
    public static final String KEY_INPUTSTARTX = "KEY_INPUTSTARTX";
    public static final String KEY_INPUTSTARTY = "KEY_INPUTSTARTY";
    public static final String KEY_TEMPLATEIMAGEPATH = "KEY_TEMPLATEIMAGEPATH";
    public static final String KEY_TEMPLATESTARTX = "KEY_TEMPLATESTARTX";
    public static final String KEY_TEMPLATESTARTY = "KEY_TEMPLATESTARTY";
    public static final String KEY_W = "KEY_W";
    public static final String KEY_H = "KEY_H";
    public static final String KEY_COLORTOLERANCEASPERCENT = "KEY_COLORTOLERANCEASPERCENT";
    public static final String KEY_PIXELTOLERANCE = "KEY_PIXELTOLERANCE";
    public static final String KEY_INPUTWIDTH = "KEY_INPUTWIDTH";
    public static final String KEY_INPUTHEIGHT = "KEY_INPUTHEIGHT";
    public static final String KEY_METHOD_RESULT_X = "KEY_METHOD_RESULT_X";
    public static final String KEY_METHOD_RESULT_Y = "KEY_METHOD_RESULT_Y";

    public static final String KEY_FINDINSCREENCOM_XSTEP = "KEY_FINDINSCREENCOM_XSTEP";
    public static final String KEY_FINDINSCREENCOM_YSTEP = "KEY_FINDINSCREENCOM_YSTEP";

    /**
     * Key Constants for install
     */
    public static final String KEY_APKPATH = "KEY_APKPATH";

    /**
     * Key Constants for uninstall method params
     */
    public static final String KEY_PACKAGENAME = "KEY_PACKAGENAME";

    /**
     * Key Constants for input method params
     */
    public static final String KEY_INPUT_TEXT = "KEY_INPUT_TEXT";

    /**
     * Key Constants for sendMessage method params
     */

    public static final String KEY_SENDMESSAGE_RECEIVERS = "KEY_SENDMESSAGE_RECEIVERS";
    public static final String KEY_SENDMESSAGE_CONTENT = "KEY_SENDMESSAGE_CONTENT";

    /**
     * Key Constants for getMessage method params
     */
    public static final String KEY_GETMESSAGE_ID = "KEY_GETMESSAGE_ID";
    public static final String KEY_GETMESSAGE_TYPE = "KEY_GETMESSAGE_TYPE";
    public static final String KEY_GETMESSAGE_ISREAD = "KEY_GETMESSAGE_ISREAD";
    public static final String KEY_GETMESSAGE_PRIORITY = "KEY_GETMESSAGE_PRIORITY";
    public static final String KEY_GETMESSAGE_DESTADDRESSES = "KEY_GETMESSAGE_DESTADDRESSES";
    public static final String KEY_GETMESSAGE_SUBJECT = "KEY_GETMESSAGE_SUBJECT";
    public static final String KEY_GETMESSAGE_STARTTIME = "KEY_GETMESSAGE_STARTTIME";
    public static final String KEY_GETMESSAGE_ENDTIME = "KEY_GETMESSAGE_ENDTIME";
    public static final String KEY_GETMESSAGE_FOLDERNAME = "KEY_GETMESSAGE_FOLDERNAME";
    public static final String KEY_GETMESSAGE_STARTINDEX = "KEY_GETMESSAGE_STARTINDEX";
    public static final String KEY_GETMESSAGE_COUNT = "KEY_GETMESSAGE_COUNT";
    public static final String KEY_GETMESSAGE_TIME = "KEY_GETMESSAGE_TIME";
    public static final String KEY_GETMESSAGE_VALIDITYPERIOD = "KEY_GETMESSAGE_VALIDITYPERIOD";
    public static final String KEY_GETMESSAGE_SOURCEADDRESS = "KEY_GETMESSAGE_SOURCEADDRESS";
    public static final String KEY_GETMESSAGE_CALLBACKNUMBER = "KEY_GETMESSAGE_CALLBACKNUMBER";
    public static final String KEY_GETMESSAGE_BODY = "KEY_GETMESSAGE_BODY";
    public static final String KEY_GETMESSAGE_HASATTACHMENT = "KEY_GETMESSAGE_HASATTACHMENT";

    /**
     * Key Constants for saveOcrCertificate method params
     */
    public static final String KEY_SAVEOCRCERTIFICATE_EXPECT = "KEY_SAVEOCRCERTIFICATE_EXPECT";
    public static final String KEY_SAVEOCRCERTIFICATE_REALITY = "KEY_SAVEOCRCERTIFICATE_REALITY";

    /**
     * Key Constants for wapope method params
     */
    public static final String KEY_WAPOPE_URL = "KEY_WAPOPE_URL";
    public static final String KEY_WAPOPE_WIDGETTESTENABLE = "KEY_WAPOPE_WIDGETTESTENABLE";

    /**
     * log level const
     */
    public static final int LOG_LEVEL_VERBOSE = 1;
    public static final int LOG_LEVEL_DEBUG = 2;
    public static final int LOG_LEVEL_INFO = 3;
    public static final int LOG_LEVEL_WARN = 4;
    public static final int LOG_LEVEL_ERROR = 5;
    public static final int LOG_LEVEL_FATAL = 6;
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

    /**
     * the methods of alarm
     */
    public final static String KEY_ALARM_CODE = "KEY_ALARM_CODE";
    public final static String KEY_ALARM_MESSAGE = "KEY_ALARM_MESSAGE";

    /**
     * the method of getFileSize
     */
    public final static String KEY_PATH = "KEY_PATH";
    public final static String KEY_FILENAME = "KEY_FILENAME";

    /**
     * 移动网络注册失败
     */
    public static final int ALARM_MN_REG_FAIL = 102;

    /**
     * 数据网络连接断开
     */
    public static final int ALARM_N_DATA_DISCONN = 103;

    /**
     * 屏幕被关闭
     */
    public static final int ALARM_SCREEN_CLOSED = 104;

    /**
     * 屏幕被锁屏
     */
    public static final int ALARM_SCREEN_LOCKED = 105;

    /**
     * SD卡被移除
     */
    public static final int ALARM_SDCARD_ABSENT = 106;

    /**
     * CPU占用超限值告警
     */
    public static final int ALARM_CPU_OVERRUN = 107;
    /**
     * 内存使用超限值告警
     */
    public static final int ALARM_MEM_OVERRUN = 108;
    /**
     * 存储空间超限值告警
     */
    public static final int ALARM_STORAGE_OVERRUN = 109;

    /**
     * 网络流量超限值告警
     */
    public static final int ALARM_NW_OVERRUN = 114;

    /**
     * 低电量告警
     */
    public static final int ALARM_LOWER_POWER = 110;

    /**
     * 弱信号告警
     */
    public static final int ALARM_LOWER_SIGNAL = 111;

    /**
     * 电池过热
     */
    public static final int ALARM_HIGH_TEMP = 101;

    /**
     * 业务指标连续失败告警
     */
    public static final int ALARM_BS_FAIL = 119;

    public static final String KEY_LEFT = "KEY_LEFT";

    public static final String KEY_RIGHT = "KEY_RIGHT";
}
