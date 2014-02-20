/**
 * @(#) Main.java Created on 2012-10-19
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server;

import org.apache.log4j.Logger;

import com.inspurworld.server.execute.client.ClientExecuteCenter;
import com.inspurworld.server.restore.ClientRestoreService;

/**
 * The class <code>Main</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class MainServer {

    private Logger logger = Logger.getLogger(getClass());

    private AccpetServer accpetServer;

    private ClientExecuteCenter executeCenter;
    
    private ClientRestoreService clientRestoreService;

    public void start(int port) {
        logger.debug("MainServer start....");
        accpetServer = new AccpetServer(port);
        
        clientRestoreService = new ClientRestoreService();
        
        executeCenter = new ClientExecuteCenter(clientRestoreService);
        accpetServer.setClinentConn(executeCenter);
        accpetServer.startServer();
    }

    public void stop() {
        if (null != accpetServer)
            accpetServer.stopServer();
        logger.debug("MainServer stop....");
    }

}
