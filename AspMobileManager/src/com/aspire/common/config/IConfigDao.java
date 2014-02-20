/**
 * @(#) IConfigDao.java Created on 2012-7-11
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.common.config;

/**
 * The class <code>IConfigDao</code>
 *
 * @author linjunsui
 * @version 1.0
 */
public interface IConfigDao {
    
    /**
     * whether contain the config of key 
     * @param key Key of Config
     * @return true if contain the key
     */
    public boolean contain(String key);

    /**
     * get Config
     * @param key Key of the config
     * @return value of config
     */
    public String getConfig(String key);
    
    /**
     * get Config
     * @param key Key of the config
     * @param defaultValue default value of the config, return defaultValue while no config of the key
     * @return value of config
     */
    public String getConfig(String key, String defaultValue);
    
    /**
     * set Config
     * @param key Key of the config
     * @param value Value of config
     */
    public void setConfig(String key, String value);
    
    /**
     * remove the config 
     * @param key Key of config
     */
    public void remove(String key);
    
    /**
     * save the config
     */
    public void save();
}
