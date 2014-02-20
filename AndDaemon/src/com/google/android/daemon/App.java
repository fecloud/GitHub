package com.google.android.daemon;
import android.app.Application;
import android.content.Context;

/**
 * @(#) App.java Created on 2013-1-19
 *
 * Copyright (c) 2013 com.braver. All Rights Reserved
 */

/**
 * The class <code>App</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class App extends Application {

    private static Context mContext;

    public App() {
        mContext = this;
    }

    /**
     * get cotext
     * 
     * @return
     */
    public static final synchronized Context getContext() {
        return mContext;
    }

}
