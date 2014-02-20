/**
 * @(#) ShareConfigHelp.java Created on 2012-6-11
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.common.share;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
/**
 * 
 * The class <code>WorkingHelp</code>
 *
 * @author wuyanlong
 * @version 1.0
 */
public class AspShareUtil {
    
    /**
     * Constants
     */
    public static final Uri CONTENT_URI = Uri.parse("content://com.aspire.android.daemon/sharedconfig");
    public static final String AGENT_PORT_KEY = "aspire_agent_port";
    public static final String KEY_BASE_DIR = "aspire_base_dir";
    public static final String KEY_CONNECT_MODE = "aspire_connect_mode";

    /**
     * 设置程序是否正在运行任务
     * 
     * @param context
     * @param isWorking
     */
    public static void setWorking(Context context, boolean isWorking) {
        if (context == null)
            return;
        ContentValues values = new ContentValues();
        values.put("key", context.getPackageName());
        values.put("value", isWorking ? "1" : "0");
        try {
            context.getContentResolver().insert(CONTENT_URI, values);
        } catch (IllegalArgumentException e1) {
        } catch (Exception e) {
            Log.e("AspShareUtil", "Error while setWorking", e);
        }
    }
    
    /**
     * set connect mode
     * @param context
     * @param value
     */
    public static void setConnectMode(Context context, String value) {
        set(context, KEY_CONNECT_MODE, value);
    } 
    
    /**
     * set connect mode
     * @param context
     * @param value
     */
    public static void setBaseDir(Context context, String value) {
        set(context, KEY_BASE_DIR, value);
    } 
    
    /**
     * set the value to share config
     * @param context context 
     * @param key key of the config
     * @param value value of the config
     */
    public static void set(Context context, String key, String value) {
        ContentValues values = new ContentValues();
        values.put("key", key);
        values.put("value", value);
        try {
            context.getContentResolver().insert(CONTENT_URI, values);
        } catch (IllegalArgumentException e1) {
        } catch (Exception e) {
            Log.e("AspShareUtil", "Error while set", e);
        }
    }

    /**
     * 获取agent socket连接端口
     * @param context
     * @return
     */
    public static String getAgentPort(Context context) {
        return get(context, AGENT_PORT_KEY);
    }

    /**
     * 查询一条记录
     * 
     * @param key
     * @return value
     */
    public static String get(Context context, String key) {
        String temp = null;
        Cursor cur = null;
        try {
            cur = context.getContentResolver().query(CONTENT_URI, null, "key='" + key + "'", null, null);
            if (null != cur &&cur.moveToFirst())
                temp = cur.getString(1);
            
        } catch (Exception e) {
            Log.e("AspShareUtil", "Error while get", e);
        } finally {
            if (cur != null)
                cur.close();
        }
        return temp;
    }
}
