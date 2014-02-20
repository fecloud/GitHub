/**
 * @(#) EngineClient.java Created on 2012-5-8
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute.engine;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.util.Log;

import com.aspire.android.common.exception.ExceptionHandler;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.Constants;
import com.aspire.mcts.agent.msg.APSMessage;
import com.aspire.mcts.agent.msg.APSMessageCodec;
import com.aspire.mcts.agent.msg.TTRequest;
import com.aspire.mobile.codec.MobileMsgCodec;
import com.aspire.mobile.msg.MobileMsgBase;
import com.aspire.util.ByteArray;
import com.aspire.util.ToolException;

/**
 * The class <code>EngineClient</code>
 * 
 * @author linjunsui
 * @version 1.0
 */
public class EngineClient extends Thread {

    /**
     * send message to Agent timeout
     */
    public static final int ENGINECLIENT_TIME_OUT = 100000;

    /**
     * receive message interval
     */
    private static final int RECEIVE_INTERVAL = 100;

    /**
     * sleep time while error
     */
    private static final int ERROR_SLEEP_TIME = 2000;

    /**
     * LOGTAG
     */
    public final static String LOGTAG = Constants.LOGTAG;

    /**
     * socket
     */
    private Socket clientSocket;

    /**
     * Engine Ip
     */
    private String serverIp;

    /**
     * Engine port
     */
    private int serverPort;

    /**
     * APSMessage codec
     */
    private APSMessageCodec apsCodec = new APSMessageCodec();

    /**
     * MTEPMessage codec
     */
    private MobileMsgCodec mtepCodec = new MobileMsgCodec();

    /**
     * APSMessage listener list
     */
    protected List<IAPSMessageListener> apsMessageListenerList = new ArrayList<IAPSMessageListener>();

    /**
     * ttResponseMessageListener
     */
    protected TTResponseMessageListener ttResponseMessageListener;

    /**
     * syncMessageListener
     */
    protected SyncMessageListener syncMessageListener;

    /**
     * grabImageMessageListener
     */
    protected GrabImageMessageListener grabImageMessageListener;
    /**
     * mtepMessageListener
     */
    protected MTEPMessageListener mtepMessageListener;
    
    private AlarmMessageListener alarmMessageListener;

    /**
     * is running
     */
    private boolean running;

    /**
     * Constructor
     * 
     * @param serverIp
     * @param serverPort
     * @throws IOException
     */
    public EngineClient(String serverIp, int serverPort) throws IOException {
        super();
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        ttResponseMessageListener = new TTResponseMessageListener();
        apsMessageListenerList.add(ttResponseMessageListener);
        syncMessageListener = new SyncMessageListener();
        apsMessageListenerList.add(syncMessageListener);
        grabImageMessageListener = new GrabImageMessageListener();
        apsMessageListenerList.add(grabImageMessageListener);
        mtepMessageListener = new MTEPMessageListener();
        ttResponseMessageListener.getMtepMessageListenerList().add(mtepMessageListener);
        
        alarmMessageListener = new AlarmMessageListener();
        apsMessageListenerList.add(alarmMessageListener);
    }

    /**
     * add apsMessage listerner
     * 
     * @param listener
     *            IAPSMessageListener
     */
    public void addAPSMessageListener(IAPSMessageListener listener) {
        apsMessageListenerList.add(listener);
    }

    /**
     * clear APSMessage Listerners
     */
    public void clearAPSMessageListeners() {
        apsMessageListenerList.clear();
    }

    /**
     * add MTEPMessage listerner
     * 
     * @param listener
     *            IAPSMessageListener
     */
    public void addMTEPMessageListener(IMTEPMessageListener listener) {
        ttResponseMessageListener.getMtepMessageListenerList().add(listener);
    }

    /**
     * clear MTEPMessage Listerners
     */
    public void clearMETPMessageListeners() {
        ttResponseMessageListener.getMtepMessageListenerList().clear();
    }

    /**
     * @throws IOException
     */
    private void connect() throws IOException {
        Log.d(LOGTAG, "connect");
        if (clientSocket != null) {            
            Log.d(LOGTAG, "Disconnect from anget");
            clientSocket.close();
        }
        clientSocket = new Socket();
        // clientSocket.setReuseAddress(true);
        // clientSocket.setSoLinger(true, 0);
        clientSocket.connect(new InetSocketAddress(this.serverIp, this.serverPort));
        Log.d(LOGTAG, "Connect from anget");
    }

    /**
     * (non-Javadoc)
     * 
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        Log.d(LOGTAG, "run.......................");
        this.running = true;
        int readed = 0;
        int read = 0;
        int totalLen = 0;
        ByteBuffer buffer = ByteBuffer.allocate(Constants.MAX_MSG_LEN);
        InputStream inputStream = null;

        while (running) {
            // 检查链接
            try {
                checkConnection();
            } catch (MException e2) {
                try {
                    sleep(ERROR_SLEEP_TIME);
                } catch (InterruptedException e) {
                }
                continue;
            }

            // 读取消息
            try {
                // 收取消息头
                readed = 0;
                buffer.clear();
                inputStream = clientSocket.getInputStream();
                while (running && readed < APSMessage.LEN_HEADER) {
                    try {
                        read = inputStream.read(buffer.array(), readed, APSMessage.LEN_HEADER - readed);
                        if (read < 0)
                            return;
                        readed += read;
                    } catch (SocketTimeoutException e) {
                        continue;
                    }
                }

                if (!running) {
                    return;
                }

                buffer.position(readed);

                // 获取消息体长度
                totalLen = readed + buffer.getInt(APSMessage.OFFSET_BODY_LEN);
                Log.d(LOGTAG, "Receiving message with length: " + totalLen);

                // 收取消息体
                read = 0;
                while (running && readed < totalLen) {
                    try {
                        read = inputStream.read(buffer.array(), readed, totalLen - readed);
                        if (read < 0)
                            return;
                        readed += read;
                    } catch (SocketTimeoutException e) {
                        continue;
                    }
                }
                if (!running) {
                    return;
                }

                buffer.position(readed);
                buffer.flip();
                // Log.d(LOGTAG, CodecUtil.toHexString(buffer));

                // 处理收取到的完整消息
                handleMessage((ByteBuffer) buffer);

            } catch (IOException e) {
                ExceptionHandler.handle(e, "Error while receive message from engine");
                try {
                    sleep(3000);
                } catch (InterruptedException e1) {
                }
            } catch (MException e) {
                ExceptionHandler.handle(e, "handleMessage fail");
            }
        }

        try {
            if (clientSocket != null) {
                clientSocket.close();
            }
        } catch (IOException e) {
            ExceptionHandler.handle(e, "Error close socket");
        }
    }

    /**
     * Handle proxy message buffer
     * 
     * @param buffer
     * @throws MException
     * @throws AgentException
     */
    protected void handleMessage(ByteBuffer buffer) throws MException {
//        Log.d(LOGTAG, "handleMessage");
        APSMessage message = null;
        try {
            message = apsCodec.decode(buffer);
        } catch (Exception ex) {
            throw new MException("Decode message error: " + ex.getMessage(), ex);
        }

        for (Iterator<IAPSMessageListener> iterator = apsMessageListenerList.iterator(); iterator.hasNext();) {
            IAPSMessageListener listener = iterator.next();
            if (!listener.onMessage(message)) {
                break;
            }
        }
    }

    /**
     * send MTEP Message
     * 
     * @param message
     * @throws MException
     */
    public void sendMTEPMessage(MobileMsgBase message) throws MException {

        TTRequest apsMessage = new TTRequest();
        ByteArray baMsg = new ByteArray();
        try {
            mtepCodec.encode(message, baMsg);
        } catch (ToolException e) {
            throw new MException(e);
        }
        apsMessage.setBody(baMsg.get());

        sendAPSMessage(apsMessage);
    }

    /**
     * send Aps Message
     * 
     * @param message
     * @throws MException
     */
    public void sendAPSMessage(APSMessage message) throws MException {

        checkConnection();

        try {
            ByteBuffer byteBuffer = apsCodec.encode(message);

            clientSocket.getOutputStream().write(byteBuffer.array(), 0, byteBuffer.limit());
            clientSocket.getOutputStream().flush();
        } catch (IOException e) {
            MException mexception = ExceptionHandler.handle(e, "Error send aps message");
            if (mexception != null) {
                throw mexception;
            }
        } catch (ToolException e) {
            MException mexception = ExceptionHandler.handle(e, "Error encode ApsMessage");
            if (mexception != null) {
                throw mexception;
            }
        }
    }

    /**
     * check the connection and reconnect
     * 
     * @throws MException
     */
    private void checkConnection() throws MException {
        if ((clientSocket == null) || !clientSocket.isConnected() || clientSocket.isInputShutdown()
                || clientSocket.isOutputShutdown()) {
            try {
                connect();
            } catch (IOException e) {
                MException mexception = ExceptionHandler.handle(e, "");
                if (mexception != null) {
                    throw mexception;
                }
            }
        }
    }

    /**
     * 
     * @param message
     * @param timeout
     *            in ms
     * @return
     * @throws MException
     */
    public APSMessage sendAndResponse(APSMessage message, int timeout) throws MException {
        syncMessageListener.reset();
        sendAPSMessage(message);
        int time = 0;
        while (!syncMessageListener.isResponsed()) {
            if (time > timeout) {
                throw new MException("Timeout while wait from response message");
            }
            try {
                sleep(RECEIVE_INTERVAL);
            } catch (Exception e) {
            }

            time += RECEIVE_INTERVAL;
        }
        return syncMessageListener.getResponseMessage();
    }

    public APSMessage sendAndResponse(APSMessage message) throws MException {
        return sendAndResponse(message, ENGINECLIENT_TIME_OUT);
    }

    /**
     * 
     * @param message
     * @param timeout
     * @return
     * @throws MException
     */
    public List<APSMessage> sendAndListResponse(APSMessage message, int timeout) throws MException {
        grabImageMessageListener.reset();
        sendAPSMessage(message);
        int time = 0;
        while (!grabImageMessageListener.isResponsed()) {
            if (time > timeout) {
                throw new MException("Timeout while wait from response message");
            }
            try {
                sleep(RECEIVE_INTERVAL);
            } catch (Exception e) {
            }

            time += RECEIVE_INTERVAL;
        }
        return grabImageMessageListener.getResponseMessages();
    }

    /**
     * @param request
     * @return
     * @throws MException
     */
    public List<APSMessage> sendAndListResponse(APSMessage request) throws MException {
        return sendAndListResponse(request, ENGINECLIENT_TIME_OUT);
    }

    /**
     * 
     * @param message
     * @param timeout
     *            in ms
     * @return
     * @throws MException
     */
    public List<MobileMsgBase> sendAndResponse(MobileMsgBase message, int timeout) throws MException {
        mtepMessageListener.reset();
        sendMTEPMessage(message);
        int time = 0;
        while (!mtepMessageListener.isResponsed()) {
            if (time > timeout) {
                throw new MException("Timeout while wait from response message");
            }
            try {
                sleep(RECEIVE_INTERVAL);
            } catch (Exception e) {
            }

            time += RECEIVE_INTERVAL;
        }
        return mtepMessageListener.getResponseMessageList();
    }

    /**
     * @param message
     * @return
     * @throws MException
     */
    public List<MobileMsgBase> sendAndResponse(MobileMsgBase message) throws MException {
        return sendAndResponse(message, ENGINECLIENT_TIME_OUT);
    }

    /**
     * 
     * @param message
     * @param timeout
     *            in ms
     * @return
     * @throws MException
     */
    public MobileMsgBase sendAndResponseOne(MobileMsgBase message, int timeout) throws MException {
        List<MobileMsgBase> responseMessageList = sendAndResponse(message, timeout);
        if (null != responseMessageList && responseMessageList.size() == 1) {
            return responseMessageList.get(0);
        } else {
            throw new MException("sendAndResponseOne responseMessageList is null");
        }
    }

    /**
     * disconnect from engine
     */
    public void disconnect() {
        running = false;

    }

    /**
     * @param operationReq
     * @return
     * @throws MException
     */
    public MobileMsgBase sendAndResponseOne(MobileMsgBase message) throws MException {
        return sendAndResponseOne(message, ENGINECLIENT_TIME_OUT);
    }

}
