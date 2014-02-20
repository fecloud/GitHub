/**
 * @(#) Daemon.java Created on 2013-1-19
 *
 * Copyright (c) 2013 com.braver. All Rights Reserved
 */
package com.google.android.daemon;

import com.google.android.daemon.collect.CollectService;

/**
 * The class <code>Daemon</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public final class Daemon {

    private CollectService collectService = new CollectService();

    public void start() {
        collectService.start();
    }

    public void stop() {
        collectService.stop();
    }
}
