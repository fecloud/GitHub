/**
 * @(#) CaptureThread.java Created on 2012-12-18
 *
 * Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.android.screen.capture;

import android.util.Log;

/**
 * The class <code>CaptureThread</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class CaptureThread extends Thread {

    private static final String TAG = "CaptureThread";

    private ScreenCaptureService service;

    private CaptureTask task;

    public CaptureThread(ScreenCaptureService service) {
        this.service = service;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        try {
            while (true) {
                execute();
            }
        } catch (InterruptedException e) {
            Log.e(TAG, "", e);
        }
    }

    /**
     * @throws InterruptedException
     * 
     */
    private void execute() throws InterruptedException {
        task = service.getTask();
        if (null == task) {
            return;
        }
        final int len = grabScreen(task.getArray());
        task.len = len;
        task.back.callBack(task);
    }

    public static int grabScreen(byte[] arr) {
        //Log.d(TAG, "grabScreen arr length =" + arr.length);
        return NativeScreenCapture.screenCapture(arr, 0, arr.length);
    }

}
