/**
 * @(#) WhileGrabScreen.java Created on 2012-10-23
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.uare.agent.screen;

import android.util.Log;

/**
 * The class <code>WhileGrabScreen</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class WhileGrabScreen implements Runnable {

    private static final String TAG = "WhileGrabScreen";

    private WhileGrabScreenTask task;

    private int sleep;

    private boolean flag;

    private Thread thread;

    public WhileGrabScreen(int rate) {
        super();
        this.sleep = 60000 / rate;
    }

    public WhileGrabScreen(int rate, WhileGrabScreenTask task) {
        super();
        this.sleep = 60000 / rate;
        this.task = task;
    }

    @Override
    public void run() {
        while (flag) {

            try {
                doingTask();
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                Log.d(TAG, "", e);
            }
        }
    }

    public void start() {
        if (null == thread) {
            flag = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    public void stop() {
        flag = false;
        thread = null;
    }

    public void doingTask() {
        Log.d(TAG, "doingTask");
        if (null != task) {
            task.doTask();
        }
    }

    public WhileGrabScreenTask getTask() {
        return task;
    }

    public void setTask(WhileGrabScreenTask task) {
        this.task = task;
    }

}
