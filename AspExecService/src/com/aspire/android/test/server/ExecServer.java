/**
 * @(#) ExecServer.java Created on 2012-7-19
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.server;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.aspire.android.common.exception.ExceptionHandler;
import com.aspire.android.common.exception.MException;
import com.aspire.sqmp.mobilemanager.service.request.RegisterClass;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.google.inject.Inject;

/**
 * The class <code>ExecServer</code>
 * 
 * @author wuyanlong
 * @version 1.0
 */
public class ExecServer {

    static {
        // 不能启用IPV6协议
        // IPv4 和 IPv6 引起的混乱
        java.lang.System.setProperty("java.net.preferIPv4Stack", "true");
        java.lang.System.setProperty("java.net.preferIPv6Addresses", "false");
    }

    /**
     * TAG of ExecSrver
     */
    private static final String TAG = ExecServer.class.getSimpleName();
    /**
     * to observe the port of default
     */
    private static int DEFAULT_PORT = 56000;
    /**
     * Instance
     */
    // private static ExecServer instance;

    /**
     * The server
     */
    private Server server;
    /**
     * List of messagehandle
     */
    private List<IServerMessageHandle> messageHandleList = new ArrayList<IServerMessageHandle>();
    /**
     * handle request
     */
    @Inject
    private RequestHandle requestHandle;
    /**
     * boolean of serverStart
     */
    private boolean serverStart = false;

    /**
     * Gettor of instance
     * 
     * @return
     */

    /**
     * Constructor
     */
    @Inject
    public ExecServer() {
    }

    /**
     * Start of server 失败时可能耗时300毫秒
     * 
     * @param restart
     *            强制重启，否则若已启动则不做任何操作
     * @throws MException
     */
    public void start(boolean restart) throws MException {
        if (!restart && serverStart)
            return;
        if (restart) {
            destoryServer();
        }
        if (!messageHandleList.contains(requestHandle))
            messageHandleList.add(requestHandle);
        int times = 0;
        Exception exception = null;
        serverStart = false;
        // 连接失败尝试连接3次
        while ((!serverStart) && times < 3) {
            try {
                buildServer();
                serverStart = true;
            } catch (MException e) {
                exception = e;
                times++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                }
            }
        }
        if (!serverStart)
            throw new MException("Fail to start ExecServer.", exception != null ? exception : new Exception());
    }

    /**
     * Destory ExecServer
     */
    public void destory() {
        destoryServer();
    }

    /**
     * Build server
     * 
     * @throws MException
     */
    private void buildServer() throws MException {
        destoryServer();
        try {
            server = new Server();
            server.start();
            // Opens a TCP only on client.
            server.bind(DEFAULT_PORT);
        } catch (Exception e) {
            throw new MException(e, "while bind server port:" + DEFAULT_PORT);
        }

        Log.i(TAG, "ExecServer is starting and  binding with the port of " + DEFAULT_PORT);
        server.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                onMessageReceive(connection, object);
            }
        });
        Kryo kryo = server.getKryo();
        for (Class<?> c : RegisterClass.getRegisterClassList()) {
            kryo.register(c);
        }
    }

    /**
     * Destory server
     */
    private void destoryServer() {
        if (server != null) {
            server.stop();
            server.close();
        }
    }

    /**
     * @param object
     */
    protected void onMessageReceive(Connection connection, Object object) {
        try {
            for (IServerMessageHandle h : messageHandleList) {
                if (h.handleMessage(connection, object))
                    break;
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e, "while handle message of receive from ExecServer.");
        }
    }

}
