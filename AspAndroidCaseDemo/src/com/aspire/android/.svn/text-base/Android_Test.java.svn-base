/**
 * @(#) Android_Test.java Created on 2012-10-12
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.test.AndroidTestCase;
import android.util.Log;

/**
 * The class <code>Android_Test</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class Android_Test extends AndroidTestCase {

    private static final String TAG = "Android_Test";

    private static final String CMD = "ps";

    public void testProcess() {
       
        final ActivityManager activityManager = (ActivityManager) getContext().getSystemService(
                Context.ACTIVITY_SERVICE);
        final List<RunningAppProcessInfo> list = activityManager.getRunningAppProcesses();
        for (RunningAppProcessInfo info : list) {
            Log.d(TAG, info.processName);
        }
    }

    public void testGetProcessInfoByPs() {
        try {
            final Process process = Runtime.getRuntime().exec(CMD);
            process.waitFor();
            final InputStream in = process.getInputStream();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            String[] arrary = null;
            int flag = 0;
            while ((line = reader.readLine()) != null) {
                if (flag == 0) {
                    flag = 1;
                    continue;
                }
                arrary = line.split(" ");
                if (null != arrary)
                    Log.d(TAG, arrary[arrary.length - 1]);

            }
            reader.close();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

}
