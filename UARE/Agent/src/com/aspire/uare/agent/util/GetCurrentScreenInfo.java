/**
 * @(#) GetCurrentScreenInfo.java Created on 2012-10-20
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.uare.agent.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;

/**
 * The class <code>GetCurrentScreenInfo</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class GetCurrentScreenInfo {

    private Context mContext;

    public GetCurrentScreenInfo(Context mContext) {
        super();
        this.mContext = mContext;
    }

    public String currentActivityInfo() {
        final ActivityManager manager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        final ComponentName name = manager.getRunningTasks(1).get(0).topActivity;
        // final String pName = name.getPackageName();
        final String className = name.getClassName();
        // final String shortName = name.getShortClassName();
        // return pName + "_" + className + "_" + shortName + "_" + System.currentTimeMillis();
        return className + "_" + System.currentTimeMillis();
    }
}
