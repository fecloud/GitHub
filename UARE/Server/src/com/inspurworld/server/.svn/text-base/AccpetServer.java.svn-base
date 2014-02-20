/**
 * @(#) AccpetServer.java Created on 2012-10-19
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

/**
 * The class <code>AccpetServer</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class AccpetServer extends Thread {

    private Logger logger = Logger.getLogger(getClass());

    private ServerSocket serverSocket;

    private OnClinentConn clinentConn;

    private Socket client;

    private boolean flag;

    private int port;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public AccpetServer(int port) {
        super();
        this.port = port;
    }

    @Override
    public void run() {
        flag = true;
        accpet();
    }

    public boolean startServer() {
        logger.debug("AccpetServer startServer....");
        this.start();
        return flag;
    }

    public boolean stopServer() {
        this.flag = false;
        logger.debug("AccpetServer stopServer....");
        return !flag;
    }

    private void accpet() {
        try {
            serverSocket = new ServerSocket(port);
            logger.warn("AccpetServer accept port " + port);
            while (flag) {
                client = serverSocket.accept();
                logger.debug("connection a client ip:" + client.getInetAddress().getHostAddress() + " port:"
                        + client.getPort());
                notiftyClientConn();
            }
        } catch (IOException e) {
            logger.error("socket accept error", e);
        }
    }

    private void notiftyClientConn() {
        if (null != clinentConn) {
            logger.debug("notiftyClientConn...");
            clinentConn.clientConn(client);
        }

    }

    public OnClinentConn getClinentConn() {
        return clinentConn;
    }

    public void setClinentConn(OnClinentConn clinentConn) {
        this.clinentConn = clinentConn;
    }

}
