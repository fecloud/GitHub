/**
 * @(#) SystemProperties.java Created on 2012-6-11
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.attachment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * The class <code>SysProperties</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class SysProperties {

    private static SysProperties SYS_PROPERTIES;

    private Properties properties;

    private SysProperties() throws Exception {
        loadProperties();
        SYS_PROPERTIES = this;
    }

    private void loadProperties() throws Exception {
        final String sysproperPath = Utils.getCurrentPath() + File.separator + "system.properties";
        final File file = new File(sysproperPath);
        properties = new Properties();
        try {
            properties.load(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            System.err.println("current Directory not find system.properties file , please checking");
            throw e;
        } catch (IOException e) {
            System.err.println("IO IOException");
            throw e;
        }
    }

    /**
     * get SysProperties instance
     * 
     * @return
     * @throws Exception
     */
    private static SysProperties getInstance() throws Exception {
        if (null != SYS_PROPERTIES)
            return SYS_PROPERTIES;
        return new SysProperties();
    }

    /**
     * get system properties , if you are config ,write on system.properties file
     * 
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        try {
            return getInstance().properties.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
