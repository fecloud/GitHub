/**
 * @(#) PropertyConfigDao.java Created on 2012-7-12
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.common.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Properties;

import com.aspire.common.exception.ExceptionHandler;

/**
 * The class <code>PropertyConfigDao</code>
 *
 * @author linjunsui
 * @version 1.0
 */
public class PropertyConfigDao implements IConfigDao {
    
    /**
     * encode
     */
    private static final String UTF_8 = "utf-8";

    /**
     * path of the dao
     */
    protected String propertyFilePath;
    
    /**
     * new Properties
     */
    protected Properties properties = new Properties();

    /**
     * Constructor
     * @param propertyFilePath Property file path
     * @throws IOException IOException
     */
    public PropertyConfigDao(String propertyFilePath) throws IOException {
        super();
        this.propertyFilePath = propertyFilePath;
        properties.clear();
        File propertyFile = new File(propertyFilePath);
        if (!propertyFile.exists()) {
            propertyFile.getParentFile().mkdirs();
            propertyFile.createNewFile();
        }
        properties.load(new InputStreamReader(new FileInputStream(propertyFile), UTF_8));
    }

    /**
     * {@inheritDoc}
     * @see net.iz.common.config.IConfigDao#getConfig(java.lang.String)
     */
    public String getConfig(String key) {
        return properties.getProperty(key);
    }
    
    /**
     * {@inheritDoc}
     * @see net.iz.common.config.IConfigDao#getConfig(java.lang.String, java.lang.String)
     */
    public String getConfig(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * {@inheritDoc}
     * @see net.iz.common.config.IConfigDao#setConfig(java.lang.String, java.lang.String)
     */
    public void setConfig(String key, String value) {
        properties.setProperty(key, value);
        save();
    }
    
    /**
     * {@inheritDoc}
     * @see net.iz.common.config.IConfigDao#contain(java.lang.String)
     */
    public boolean contain(String key) {
        return properties.containsKey(key);
    }

    /**
     * {@inheritDoc}
     * @see net.iz.common.config.IConfigDao#remove(java.lang.String)
     */
    public void remove(String key) {
        properties.remove(key);
        save();
    }

    /**
     * {@inheritDoc}
     * @see net.iz.common.config.IConfigDao#save()
     */
    public void save() {
        try {
            properties.store(new OutputStreamWriter(
                    new FileOutputStream(new File(this.propertyFilePath)), UTF_8), "");
        } catch (FileNotFoundException e) {
            ExceptionHandler.handle(e, "error save config");
        } catch (IOException e) {
            ExceptionHandler.handle(e, "error save config");
        }
    }
}
