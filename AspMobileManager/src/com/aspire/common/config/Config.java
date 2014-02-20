/**
 * @(#) Config.java Created on 2012-7-12
 *
 * Copyright (c) 2012 IZ. All Rights Reserved
 */
package com.aspire.common.config;

import com.aspire.common.util.CastUtil;

/**
 * The class <code>Config</code>
 *
 * @author linjunsui
 * @version 1.0
 */
public class Config {
    
    /**
     * Config name
     */
    protected String name;
    
    /**
     * config dao
     */
    protected IConfigDao configDao;

    /**
     * Constructor
     * @param name Name of the config
     * @param configDao Dao of the config
     */
    public Config(String name, IConfigDao configDao) {
        super();
        this.name = name;
        this.configDao = configDao;
    }

    /**
     * Getter of name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of name
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter of configDao
     * @return the configDao
     */
    public IConfigDao getConfigDao() {
        return configDao;
    }

    /**
     * Setter of configDao
     * @param configDao the configDao to set
     */
    public void setConfigDao(IConfigDao configDao) {
        this.configDao = configDao;
    }

    /**
     * get the String
     * @param key Key of config
     * @return Value of the key
     */
    public String get(String key) {
        return configDao.getConfig(key);
    }
    
    /**
     * get the String
     * @param key Key of config
     * @param defaultValue Default value of config , return while cant find corresponding key
     * @return Value of the key
     */
    public String get(String key, String defaultValue) {
        return configDao.getConfig(key, defaultValue);
    }
    
    /**
     * get the String
     * @param key Key of config
     * @param value Value of the config
     */
    public void set(String key, String value) {
        configDao.setConfig(key, value);
    }
    
    /**
     * get as Integer
     * @param key Key of config
     * @return Value of the key
     */
    public Integer getAsInteger(String key) {
        return CastUtil.toInteger(configDao.getConfig(key));
    }
    
    /**
     * get as Integer
     * @param key Key of config
     * @param defaultValue Default value of config , return while cant find corresponding key
     * @return Value of the key
     */
    public Integer getAsInteger(String key, Integer defaultValue) {
        return CastUtil.toInteger(configDao.getConfig(key, null), defaultValue);
    }
    
    /**
     * set the config
     * @param key Key of config
     * @param value Value of the config
     */
    public void set(String key, Integer value) {
        configDao.setConfig(key, CastUtil.toString(value));
    }
    
    /**
     * get as Long
     * @param key Key of config
     * @return Value of the key
     */
    public Long getAsLong(String key) {
        return CastUtil.toLong(configDao.getConfig(key));
    }
    
    /**
     * get as Long
     * @param key Key of config
     * @param defaultValue Default value of config , return while cant find corresponding key
     * @return Value of the key
     */
    public Long getAsLong(String key, Long defaultValue) {
        return CastUtil.toLong(configDao.getConfig(key, null), defaultValue);
    }
    
    /**
     * set the config
     * @param key Key of config
     * @param value Value of the config
     */
    public void set(String key, Long value) {
        configDao.setConfig(key, CastUtil.toString(value));
    }
    
    /**
     * get as Short
     * @param key Key of config
     * @return Value of the key
     */
    public Short getAsShort(String key) {
        return CastUtil.toShort(configDao.getConfig(key));
    }
    
    /**
     * get as Short
     * @param key Key of config
     * @param defaultValue Default value of config , return while cant find corresponding key
     * @return Value of the key
     */
    public Short getAsShort(String key, Short defaultValue) {
        return CastUtil.toShort(configDao.getConfig(key, null), defaultValue);
    }

    /**
     * set the config
     * @param key Key of config
     * @param value Value of the config
     */
    public void set(String key, Short value) {
        configDao.setConfig(key, CastUtil.toString(value));
    }    
    
    /**
     * get as Byte
     * @param key Key of config
     * @return Value of the key
     */
    public Byte getAsByte(String key) {
        return CastUtil.toByte(configDao.getConfig(key));
    }
    
    /**
     * get as Byte
     * @param key Key of config
     * @param defaultValue Default value of config , return while cant find corresponding key
     * @return Value of the key
     */
    public Byte getAsByte(String key, Byte defaultValue) {
        return CastUtil.toByte(configDao.getConfig(key, null), defaultValue);
    }
    
    /**
     * set the config
     * @param key Key of config
     * @param value Value of the config
     */
    public void set(String key, Byte value) {
        configDao.setConfig(key, CastUtil.toString(value));
    }  
    
    /**
     * get as Double
     * @param key Key of config
     * @return Value of the key
     */
    public Double getAsDouble(String key) {
        return CastUtil.toDouble(configDao.getConfig(key));
    }
    
    /**
     * get as Double
     * @param key Key of config
     * @param defaultValue Default value of config , return while cant find corresponding key
     * @return Value of the key
     */
    public Double getAsDouble(String key, Double defaultValue) {
        return CastUtil.toDouble(configDao.getConfig(key, null), defaultValue);
    }
    
    /**
     * set the config
     * @param key Key of config
     * @param value Value of the config
     */
    public void set(String key, Double value) {
        configDao.setConfig(key, CastUtil.toString(value));
    } 
    
    /**
     * get as Float
     * @param key Key of config
     * @return Value of the key
     */
    public Float getAsFloat(String key) {
        return CastUtil.toFloat(configDao.getConfig(key));
    }
    
    /**
     * get as Float
     * @param key Key of config
     * @param defaultValue Default value of config , return while cant find corresponding key
     * @return Value of the key
     */
    public Float getAsFloat(String key, Float defaultValue) {
        return CastUtil.toFloat(configDao.getConfig(key, null), defaultValue);
    }
    
    /**
     * set the config
     * @param key Key of config
     * @param value Value of the config
     */
    public void set(String key, Float value) {
        configDao.setConfig(key, CastUtil.toString(value));
    } 
    
    /**
     * get as Boolean
     * @param key Key of config
     * @return Value of the key
     */
    public Boolean getAsBoolean(String key) {
        return CastUtil.toBoolean(configDao.getConfig(key));
    }
    
    /**
     * get as Boolean
     * @param key Key of config
     * @param defaultValue Default value of config , return while cant find corresponding key
     * @return Value of the key
     */
    public Boolean getAsBoolean(String key, Boolean defaultValue) {
        return CastUtil.toBoolean(configDao.getConfig(key, null), defaultValue);
    }
    
    /**
     * set the config
     * @param key Key of config
     * @param value Value of the config
     */
    public void set(String key, Boolean value) {
        configDao.setConfig(key, CastUtil.toString(value));
    } 
}
