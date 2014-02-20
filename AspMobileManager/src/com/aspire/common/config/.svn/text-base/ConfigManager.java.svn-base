/**
 * @(#) ConfigManager.java Created on 2012-7-11
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.common.config;

import java.util.HashMap;

/**
 * The class <code>ConfigManager</code>
 *
 * @author linjunsui
 * @version 1.0
 */
public class ConfigManager {
    
    /**
     * dao map
     */
    protected static HashMap<String, Config> configMap = new HashMap<String, Config>();
    
    /**
     * config 
     */
    protected static Config defaultConfig;
    
    /**
     * Getter of defaultConfig
     * @return the defaultConfig
     */
    public static Config getDefaultConfig() {
        return defaultConfig;
    }

    /**
     * Setter of defaultConfig
     * @param defaultConfig the defaultConfig to set
     */
    public static void setDefaultConfig(Config defaultConfig) {
        ConfigManager.defaultConfig = defaultConfig;
        add(defaultConfig);
    }

    /**
     * get the config for default Config set
     * @param key key of config
     * @return Config
     */
    public static Config getConfig(String key) {
        return configMap.get(key);
    }
    
    /**
     * clear
     */
    public static void clear() {
        configMap.clear();
    }

    /**
     * add a config
     * @param config Config to add
     */
    public static void add(Config config) {
        configMap.put(config.getName(), config);
    }
}
