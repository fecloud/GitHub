/**
 * @(#) GetIPTask.java Created on 2012-8-15
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.braver.ip.task;

import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.braver.ip.config.Config;
import com.braver.ip.request.internet.GetInternetIP;
import com.braver.ip.request.local.GetLocalIP;
import com.braver.ip.upload.UpLoadIP;
import com.braver.ip.upload.mail.ContentChangUpLoad;

/**
 * The class <code>GetIPTask</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class GetIPTask extends TimerTask {

    protected Logger logger = Logger.getLogger(getClass());

    private Config config;

    private UpLoadIP upLoadIP;

    private GetInternetIP internetIP;

    private GetLocalIP localIP;

    public GetIPTask(Config config) {
        super();
        this.config = config;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.util.TimerTask#run()
     */
    @Override
    public void run() {
        try {
            configGetIP();
        } catch (Exception e) {
            logger.error("configGetIP error", e);
        }
    }

    public void configGetIP() throws Exception {
        logger.debug("configGetIP...");
        if (null == internetIP)
            internetIP = new GetInternetIP(config.getProperty(Config.INTERNET_IP_REQUEST_ADDRESS));
        if (null == localIP)
            localIP = new GetLocalIP();
        final String ipString = "inetrnet ip:" + internetIP.getIP() + "  " + "local ip:" + localIP.getIP();
        if (null == upLoadIP) {
            final String stmp = config.getProperty(Config.MAIL_SENDER_STMP);
            final String username = config.getProperty(Config.MAIL_SENDER_USERNAME);
            final String password = config.getProperty(Config.MAIL_SENDER_PASSWORD);
            final String toAddress = config.getProperty(Config.MAIL_SEND_TO_ADDRESS);
            final String toAddressCc = config.getProperty(Config.MAIL_SEND_TO_ADDRESS_CC);
            final String subject = config.getProperty(Config.MAIL_SENDER_SUBJECT);
            upLoadIP = new ContentChangUpLoad(stmp, username, password, toAddress, toAddressCc, subject);
        }
        upLoadIP.upLoadIP(ipString);
    }
}
