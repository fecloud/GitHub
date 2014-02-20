/**
 * @(#) ClientExecuteThread.java Created on 2012-10-19
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server.execute.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.inspurworld.msg.APSMessage;
import com.inspurworld.msg.codec.APSMessageCodec;
import com.inspurworld.msg.common.ConnectionRequest;
import com.inspurworld.msg.common.ConnectionResponse;
import com.inspurworld.msg.exception.ToolException;
import com.inspurworld.server.message.ConnectionMessageListener;
import com.inspurworld.server.message.listener.IMessageListener;

/**
 * The class <code>ClientExecuteThread</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class ClientExecuteThread extends Thread {

    private Logger logger = Logger.getLogger(getClass());
    /**
     * APSMessage codec
     */
    private APSMessageCodec apsCodec = new APSMessageCodec();

    private Socket client;

    protected boolean connected;

    private List<IMessageListener> listeners = new ArrayList<IMessageListener>();

    /**
     * is running
     */
    private boolean running;

    public ClientExecuteThread(Socket client) {
        super();
        this.client = client;
        this.listeners.add(new ConnectionMessageListener());
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        this.connected = true;
        this.running = true;
        int readed = 0;
        int read = 0;
        int totalLen = 0;
        ByteBuffer buffer = ByteBuffer.allocate(512 * 1024);
        InputStream inputStream = null;

        while (running) {

            // 读取消息
            try {
                // 收取消息头
                readed = 0;
                buffer.clear();
                inputStream = client.getInputStream();
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
                logger.debug("Receiving message with length: " + totalLen);

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
                logger.error("Error while receive message from engine", e);
                try {
                    sleep(3000);
                } catch (InterruptedException e1) {
                }
            } catch (Exception e) {
                logger.error("handleMessage fail", e);
            }
        }

        try {
            if (client != null) {
                client.close();
                logger.debug("close socket");
            }
        } catch (IOException e) {
            logger.error("Error close socket");
        }

    }

    /**
     * Handle proxy message buffer
     * 
     * @param buffer
     * @throws MException
     * @throws AgentException
     */
    protected void handleMessage(ByteBuffer buffer) throws Exception {
        logger.debug("handleMessage...");
        APSMessage message = null;
        try {
            message = apsCodec.decode(buffer);
        } catch (Exception ex) {
            throw new Exception("Decode message error: " + ex.getMessage(), ex);
        }

        for (Iterator<IMessageListener> iterator = listeners.iterator(); iterator.hasNext();) {
            IMessageListener listener = iterator.next();
            if (!listener.onMessage(message)) {
                receiveMessage(message, listener.receiveMessage());
                break;
            }
        }
    }

    protected void receiveMessage(APSMessage request, APSMessage response) throws Exception {
        logger.debug("receiveMessage...");
        if (null != response) {
            logger.debug("send message to client response");
            sendMsg(response);
            if (response instanceof ConnectionResponse && request instanceof ConnectionRequest) {
                final ConnectionRequest connectionRequest = (ConnectionRequest) request;
                if (connectionRequest.getConnectionType() == ConnectionRequest.DISCONNECT) {
                    disconnect();
                }
            }
        }
    }

    protected void sendMsg(APSMessage message) throws IOException, ToolException {
        logger.debug("sendMsg");

        if (!connectIsAlive()) {
            throw new IOException("client socket is reset connection!");
        }
        final ByteBuffer buffer = apsCodec.encode(message);

        OutputStream out = client.getOutputStream();
        out.write(buffer.array(), 0, buffer.limit());
        out.flush();
    }

    /**
     * Check daemon connection status.
     * 
     * @return
     */
    public boolean connectIsAlive() {
        return connected && client != null && client.isConnected();
    }

    public void addMessageListener(IMessageListener listener) {
        synchronized (this) {
            listeners.add(listener);
        }
    }

    protected void disconnect() {
        logger.debug("disconnect...");
        running = false;
        if (client != null) {
            try {
                client.close();
            } catch (IOException ex) {
                logger.error("Fail to close daemon socket: " + ex.getMessage(), ex);
            }
        }
        connected = false;
        client = null;
    }
}
