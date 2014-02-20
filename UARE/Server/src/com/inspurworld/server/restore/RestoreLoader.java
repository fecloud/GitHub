/**
 * @(#) RestoreLoader.java Created on 2012-10-28
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server.restore;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.inspurworld.server.config.Config;

/**
 * The class <code>RestoreLoader</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public final class RestoreLoader {

    private Logger logger = Logger.getLogger(getClass());

    private static RestoreLoader restoreLoader;

    private static String filePath = Config.MASTER_DIR + "master.properties";

    private Properties properties = new Properties();

    private RestoreLoader() {
        logger.debug("new instance...");
        loadProperties();
    }

    public static RestoreLoader getInstance() {

        if (null == restoreLoader) {
            restoreLoader = new RestoreLoader();
        }
        return restoreLoader;
    }

    private void loadProperties() {
        try {
            properties.load(new FileInputStream(filePath));
        } catch (Exception e) {
            logger.error("loadProperties error", e);

        }
    }

    public String find(String key) {
        return properties.getProperty(key, null);
    }
}
