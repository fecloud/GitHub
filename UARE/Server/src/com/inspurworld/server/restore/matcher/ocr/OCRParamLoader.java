/**
 * @(#) OCRParamLoader.java Created on 2012-10-30
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server.restore.matcher.ocr;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.inspurworld.server.config.Config;

/**
 * The class <code>OCRParamLoader</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public final class OCRParamLoader {

    private Logger logger = Logger.getLogger(getClass());

    private static OCRParamLoader loader;

    private static String filePath = Config.MASTER_DIR + "ocr.properties";
    
    private Properties properties = new Properties();
    
    private OCRParamLoader() {
        logger.debug("new instance...");
        loadProperties();
    }
    
    public static OCRParamLoader getInstance() {

        if (null == loader) {
            loader = new OCRParamLoader();
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
