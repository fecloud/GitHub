/**
 * @(#) VerifyParamLoader.java Created on 2012-10-28
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server.restore.matcher.verify;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.inspurworld.server.config.Config;

/**
 * The class <code>VerifyParamLoader</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public final class VerifyParamLoader {

    private Logger logger = Logger.getLogger(getClass());

    private static VerifyParamLoader loader;

    private static String filePath = Config.MASTER_DIR + "verify.properties";

    private Properties properties = new Properties();

    private VerifyParamLoader() {
        logger.debug("new instance...");
        loadProperties();
    }

    public static VerifyParamLoader getInstance() {

        if (null == loader) {
            loader = new VerifyParamLoader();
        }
        return loader;
    }

    private void loadProperties() {
        try {
            properties.load(new FileInputStream(filePath));
        } catch (Exception e) {
            logger.error("loadProperties error", e);

        }
    }

    public String find(String key) {
        final String str = properties.getProperty(key, null);
        // if (null != str) {
        // final String[] strings = str.split("_");
        // return strings;
        //
        // }
        return str;
    }
}
