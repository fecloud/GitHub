/**
 * @(#) GetIpCenter.java Created on 2012-8-14
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.braver.ip;

import java.io.File;
import java.util.Timer;

import org.apache.log4j.Logger;

import com.braver.ip.config.Config;
import com.braver.ip.config.monitor.FileConfigMonitor;
import com.braver.ip.config.monitor.MonitorListener;
import com.braver.ip.task.GetIPTask;
import com.braver.ip.util.Utils;

/**
 * The class <code>GetIpCenter</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class GetIPCenter implements MonitorListener {

    protected Logger logger = Logger.getLogger(getClass());

    private FileConfigMonitor monitor;

    private String configFile;

    private Config config;

    private Timer timer;

    public void start() {
        configFile = Utils.getUserDir() + File.separator + "config" + File.separator + "cfg.properties";
        monitor = new FileConfigMonitor(configFile);
        monitor.addListener(this);
        monitor.startMonitor();
    }

    public void stop() {
        if (null != monitor)
            monitor.stopMonitor();
    }

    public void startGetIPTask() {
        timer = new Timer(true);
        final int period = config.getPropertyInt(Config.UPLOAD_IP_INTERVAL, 3600);
        timer.schedule(new GetIPTask(config), 0, period);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.braver.ip.config.monitor.MonitorListener#change()
     */
    @Override
    public void change() {
        config = new Config(configFile);
        if (null != timer)
            timer.cancel();
        startGetIPTask();
    }

}
