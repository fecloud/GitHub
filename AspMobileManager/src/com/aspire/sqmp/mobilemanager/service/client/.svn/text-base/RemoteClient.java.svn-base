/**
 * @(#) RemoteClient.java Created on 2012-7-25
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.service.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspire.common.exception.ExceptionHandler;
import com.aspire.common.exception.MException;
import com.aspire.sqmp.mobilemanager.service.DevicesPortManager;
import com.aspire.sqmp.mobilemanager.service.RemoteDevice;
import com.aspire.sqmp.mobilemanager.service.adb.AdbHelper;
import com.aspire.sqmp.mobilemanager.service.request.AspRequest;
import com.aspire.sqmp.mobilemanager.service.request.AspResponse;
import com.aspire.sqmp.mobilemanager.service.request.IMessageHandle;
import com.aspire.sqmp.mobilemanager.service.request.RegisterClass;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/**
 * The class <code>RemoteClient</code>
 * 
 * @author wuyanlong
 * @version 1.0
 */
public class RemoteClient {
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(RemoteDevice.class);
    /**
     * default of host
     */
    private static final String DEFAULT_HOST = "127.0.0.1";
    /**
     * default of port
     */
    private static final int DEFAULT_PORT = 56000;
    /**
     * timeout of wait for response
     */
    private static int WAIT_RESPONSE_TIME_OUT = 1000 * 10;
    /**
     * receive response interval
     */
    private static int RECEIVE_INTERVAL = 100;
    /**
     * timeout of connection
     */
    private static int DEFAULT_TIMEOUT = 5000;
    /**
     * timeout of adb forward
     */
    private static int ADBFORWARD_TIMEOUT = 10;
    /**
     * client
     */
    private Client client;

    /**
     * to handle response {@link #sendForResponse(AspRequest)} {@link #sendForResponse(AspRequest, int)}
     */
    private WaitForResponseHandle responseHandle;
    /**
     * list of mesaageHandle
     */
    private List<IMessageHandle> messageHandleList = new ArrayList<IMessageHandle>();
    /**
     * devicename of adb
     */
    private String adbDeviceName;

    /**
     * Constructor
     */
    public RemoteClient(String adbDeviceName) {
        initMessageHandle();
        this.adbDeviceName = adbDeviceName;
    }

    /**
     * 
     */
    private void initMessageHandle() {
        responseHandle = new WaitForResponseHandle();
        messageHandleList.add(responseHandle);
    }

    /**
     * Connect
     * 
     * @throws IOException
     * @throws MException
     */
    private void connect() throws IOException, MException {
        disConnect();
        client = new Client();
        client.start();
        int usablePort = DevicesPortManager.instance().findUsablePort(DEFAULT_PORT);
        usablePort = adbForward(adbDeviceName, usablePort, DEFAULT_PORT, ADBFORWARD_TIMEOUT);
        if (usablePort == -1)
            throw new MException("Abd forward cannot bind socket! Serial:" + adbDeviceName + ",Port:" + usablePort);
        client.connect(DEFAULT_TIMEOUT, DEFAULT_HOST, usablePort);
        DevicesPortManager.instance().registerDevices(adbDeviceName, usablePort);
        client.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                onMessageReceive(object);
            }
        });
        Kryo kryo = client.getKryo();
        for (Class<?> c : RegisterClass.getRegisterClassList()) {
            kryo.register(c);
        }
    }

    /**
     * CheckConncet
     * 
     * @throws MException
     * @throws IOException
     */
    public boolean checkConnect() throws IOException, MException {
        int connectionAttempt = 0;
        while (!isConnected()) {
            connect();
            connectionAttempt++;
            if (!isConnected() && connectionAttempt <= 3) {
                waitABit();
            } else if (connectionAttempt > 3) {
                return false;
            } else if (isConnected()) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * RemoteDevice is connected
     */
    public boolean isConnected() {
        if ((client == null) || !client.isConnected()) {
            return false;
        }
        return true;
    }

    /**
     * Adb forward
     * 
     * @param serial
     * @param port
     * @param remotePort
     * @param timesOut
     * @return the actual port of forward
     */
    private int adbForward(String serial, int port, int remotePort, int timeOut) {
        try {
            boolean result = false;
            for (int runTimes = 0; !result && runTimes <= timeOut; runTimes++) {
                result = AdbHelper.AdbForward(serial, port, remotePort);
                if (!result) {
                    port++;
                }
            }
            return result ? port : -1;
        } catch (Exception e) {
            ExceptionHandler.handle(e, "while adb forward serial:" + serial + ",loaclport:" + port + ",remotePort:"
                    + remotePort);
        }
        return -1;
    }

    /**
     * Sleeps for a little bit.
     */
    private void waitABit() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e1) {
        }
    }

    /**
     * DisConnect
     */
    public void disConnect() {
        DevicesPortManager.instance().removeDevices(adbDeviceName);
        if (client != null) {
            client.stop();
            client.close();
            client = null;
        }
    }

    /**
     * Send Message
     * 
     * @param object
     * @throws MException
     * @throws IOException
     */
    public void sendMessage(Object object) throws IOException, MException {
        checkConnect();
        client.sendTCP(object);
    }

    /**
     * on message receive
     * 
     * @param object
     * @param object2
     */
    protected void onMessageReceive(Object object) {
        for (IMessageHandle handle : messageHandleList) {
            if (handle.handleMessage(object))
                break;
        }
    }

    /**
     * Send AspRequest
     * 
     * @param request
     * @throws MException
     * @throws IOException
     */
    public void sendAspRequest(AspRequest request) throws IOException, MException {
        logger.debug("To send request. command:" + request.getCommand() + ",syncFlag:" + request.getSyncFlag());
        sendMessage(request);
    }

    /**
     * Send the request and wait for response
     * 
     * @param request
     * @param timeout
     * @throws IOException
     * @throws MException
     */
    public AspResponse sendForResponse(AspRequest request, int timeout) throws IOException, MException {
        responseHandle.registerWaitResponse(request);
        try {
            sendAspRequest(request);
            int time = 0;
            AspResponse response = null;
            while ((response = responseHandle.getResponse(request)) == null) {
                if (time > timeout) {
                    throw new MException("Timeout while wait from response message");
                }
                try {
                    Thread.sleep(RECEIVE_INTERVAL);
                } catch (Exception e) {
                }
                time += RECEIVE_INTERVAL;
            }
            return response;
        } catch (IOException e) {
            throw e;
        } catch (MException e) {
            throw e;
        } finally {
            responseHandle.unRegisterWaitResponse(request);
        }
    }

    /**
     * Send the request and wait for response
     * 
     * @param request
     * @throws IOException
     * @throws MException
     */
    public AspResponse sendForResponse(AspRequest request) throws IOException, MException {
        return sendForResponse(request, WAIT_RESPONSE_TIME_OUT);
    }

}
