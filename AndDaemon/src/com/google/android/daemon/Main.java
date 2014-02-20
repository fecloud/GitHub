/**
 * @(#) Main.java Created on 2013-1-19
 *
 * Copyright (c) 2013 com.braver. All Rights Reserved
 */
package com.google.android.daemon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * The class <code>Main</code>
 *
 * @author ouyangfeng
 * @version 1.0
 */
public class Main extends Activity {

    /**
     * {@inheritDoc}
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startService(new Intent("com.google.android.daemon.AndroidDaemon"));
        super.onCreate(savedInstanceState);
    }
}
