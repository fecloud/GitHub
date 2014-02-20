/**
 * @(#) Utils.java Created on 2012-10-23
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.uare.agent.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * The class <code>Utils</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public final class Utils {

    public static String getUpLoadServerIp(Context mContext) {
        final SharedPreferences preferences = mContext.getSharedPreferences(mContext.getPackageName() + "_preferences",
                Context.MODE_PRIVATE);
        return preferences.getString("server_ip", "10.0.0.10");
    }

    public static int getUpLoadServerPort(Context mContext) {
        final SharedPreferences preferences = mContext.getSharedPreferences(mContext.getPackageName() + "_preferences",
                Context.MODE_PRIVATE);
        return Integer.parseInt(preferences.getString("server_port", "8080"));
    }

    public static int getRate(Context mContext) {
        final SharedPreferences preferences = mContext.getSharedPreferences(mContext.getPackageName() + "_preferences",
                Context.MODE_PRIVATE);
        return Integer.parseInt(preferences.getString("rate", "1"));
    }

}
