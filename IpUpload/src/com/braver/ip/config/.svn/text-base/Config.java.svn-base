/**
 * @(#) Config.java Created on 2012-8-14
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.braver.ip.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * The class <code>Config</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public final class Config {

    private Logger logger = Logger.getLogger(Config.class);

    public static final String INTERNET_IP_REQUEST_ADDRESS = "internet.ip.request.address";

    public static final String MAIL_SENDER_STMP = "mail.sender.stmp";

    public static final String MAIL_SENDER_USERNAME = "mail.sender.username";

    public static final String MAIL_SENDER_PASSWORD = "mail.sender.password";

    public static final String MAIL_SEND_TO_ADDRESS = "mail.send.to.address";

    public static final String MAIL_SENDER_SUBJECT = "mail.sender.subject";

    public static final String UPLOAD_IP_INTERVAL = "upload.ip.interval";
    
    public static final String MAIL_SEND_TO_ADDRESS_CC = "mail.send.to.address.cc";

    private String file;

    private Properties properties;

    public Config(String file) {
        this.file = file;
        properties = new Properties();
        try {
            load();
        } catch (IOException e) {
            logger.error("load file error", e);
        }
    }

    private void load() throws FileNotFoundException, IOException {
        logger.debug("load file " + file);
        properties.load(new FileInputStream(file));
    }

    public String getProperty(String key, String defaultValue) {
        final String value = properties.getProperty(key);
        return value != null ? value : defaultValue;
    }

    public String getProperty(String key) {
        return getProperty(key, null);
    }

    public int getPropertyInt(String key, int defaultValue) {
        final String value = getProperty(key);
        return value != null ? Integer.parseInt(value) : defaultValue;
    }

    public int getPropertyInt(String key) {
        return getPropertyInt(key, -1);
    }
}
