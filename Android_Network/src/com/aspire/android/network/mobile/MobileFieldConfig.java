/**
 * @(#) MobileFileConfig.java Created on 2012-9-14
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.network.mobile;

import java.util.Hashtable;

import android.os.Build;
import android.util.Log;

/**
 * The class <code>MobileFileConfig</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public final class MobileFieldConfig {

    private static final String TAG = "MobileFieldConfig";

    private static final Hashtable<String, String> field_conf = new Hashtable<String, String>();

    static {
        field_conf.put("SEMC", "rmnet0");
    }

    /**
     * 根据机器类型得到读取哪个/proc/net/dev流量字段 如果没有配置则返回net
     * 
     * @return
     */
    public String getReadField() {
        final String machine = Build.BRAND;
        Log.d(TAG, "machine brand is [" + machine + "]");
        String field = field_conf.get(machine);
        if (null == field)
            field = "net";
        Log.d(TAG, "machine read field is [" + field + "]");
        return field;
    }

}
