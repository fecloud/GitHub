/**
 * @(#) UncaughtExceptionHandler.java Created on 2012-7-30
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.common.exception;

import java.lang.Thread.UncaughtExceptionHandler;

import android.util.Log;

import com.aspire.android.test.server.ExecServer;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryonet.KryoNetException;
import com.google.inject.Inject;

/**
 * The class <code>UncaughtExceptionHandler</code>
 * 
 * @author wuyanlong
 * @version 1.0
 */
public class MUncaughtExceptionHandler implements UncaughtExceptionHandler {

    private static final String TAG = "MUncaughtExceptionHandler";
    
    @Inject
    private ExecServer execServer;

    /**
     * (non-Javadoc)
     * 
     * @see java.lang.Thread.UncaughtExceptionHandler#uncaughtException(java.lang.Thread, java.lang.Throwable)
     */
    public void uncaughtException(Thread thread, Throwable ex) {
        if (ex instanceof KryoException) {
            Log.e(TAG, "Caught KryoException exception.", ex);
            execServer.destory();
            Log.e(TAG, "Restart ExecServer.");
            try {
                execServer.start(true);
            } catch (MException e) {
                e.printStackTrace();
            }
            return;
        } else if (ex instanceof KryoNetException) {
            Log.e(TAG, "Caught KryoException exception.", ex);
            execServer.destory();
            Log.e(TAG, "Restart ExecServer.");
            try {
                execServer.start(true);
            } catch (MException e) {
                e.printStackTrace();
            }
            return;
        }
        Log.e(TAG, "Unkown exception.", ex);
        // 结束旧进程，重启（前提是有Server存活）
        // ActivityManager 一直监听者进程状态。
        // 如果发现进程被kill，会立即重启进行，并重启之前状态对应的Activity、Service、ContentProvider等。
        // 这就是为什么我们调用Process.killProcess后，发现程序是重启了，而不是被kill了。
        //
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
