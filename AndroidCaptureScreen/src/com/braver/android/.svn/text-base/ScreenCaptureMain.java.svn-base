/**
 * @(#) GrabScreen.java Created on 2012-12-12
 *
 * Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.android;

import android.os.Build;
import android.util.Log;

/**
 * The class <code>GrabScreen</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class ScreenCaptureMain {

    private static final String TAG = "ScreenCaptureMain";

    public static void main(String[] args) {
        if (Build.VERSION.SDK_INT < 10) {
            System.out.print("android sdk version must 2.3.3 or hight!");
            Log.e(TAG, "android sdk version must 2.3.3 or hight!");
        } else if (null == args || args.length < 2) {
            System.out.print("useage : <port> <buffer>");
            Log.e(TAG, "useage : <port> <buffer>");
        } else {
            final int port = Integer.parseInt(args[0]);
            final int buffer = Integer.parseInt(args[1]);
            Log.d(TAG, "accept port:" + port + " buffer:" + buffer);
            Config.port = port;
            Config.buffer = 1024 * buffer;
            final ScreenCaptureService service = new ScreenCaptureService();
            service.start();
        }

    }
}
