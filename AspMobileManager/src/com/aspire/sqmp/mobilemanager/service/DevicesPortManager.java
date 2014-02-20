/**
 * @(#) LocalPortManager.java Created on 2012-7-19
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.service;

import java.util.Hashtable;
import java.util.Map;

/**
 * The class <code>LocalPortManager</code>
 * 
 * @author wuyanlong
 * @version 1.0
 */
public class DevicesPortManager {
    /**
     * Defatult of port
     */
    public static final int DEFAULT_PORT = 56000;
    /**
     * Port map
     */
    private Map<String, Integer> portMap = new Hashtable<String, Integer>();
    /**
     * Instance
     */
    private static DevicesPortManager instance;

    /**
     * Constructor
     */
    private DevicesPortManager() {
    }

    /**
     * Getter of instance
     * 
     * @return
     */
    public synchronized static DevicesPortManager instance() {
        if (instance == null)
            instance = new DevicesPortManager();
        return instance;
    }

    /**
     * Is busy to used
     * 
     * @return
     */
    public boolean checkPort(int port) {
        for (Integer p : portMap.values()) {
            if (p == port)
                return true;
        }
        return false;
    }

    /**
     * Find the usable port
     * 
     * @param port
     * @return
     */
    public int findUsablePort(int port) {
        while (checkPort(port)) {
            port = port + 1;
        }
        return port;
    }

    /**
     * Register port
     * 
     * @param port
     * @param info
     */
    public void registerDevices(String serial, int port) {
        portMap.put(serial, port);
    }

    /**
     * Remove port
     * 
     * @param port
     */
    public void removeDevices(String serial) {
        portMap.remove(serial);
    }
}
