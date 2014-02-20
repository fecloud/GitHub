/**
 * @(#) ConfigMonitor.java Created on 2012-8-14
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.braver.ip.config.monitor;

import java.util.Vector;

import org.apache.log4j.Logger;

/**
 * The class <code>ConfigMonitor</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public abstract class Monitor {
    
    protected Logger logger = Logger.getLogger(getClass());

    public Vector<MonitorListener> listeners = new Vector<MonitorListener>();

    public synchronized void addListener(MonitorListener listener) {
        listeners.add(listener);
    }

    public synchronized void remove(MonitorListener listener) {
        listeners.remove(listener);
    }

    public synchronized void onChange() {
        logger.debug("Monitor onChange...");
        for (MonitorListener listener : listeners) {
            listener.change();
        }
    }

    public abstract void startMonitor();

    public abstract void stopMonitor();

}
