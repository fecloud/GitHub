/**
 * @(#) SocketService.java Created on 2012-10-22
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.uare.agent.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

import android.util.Log;

import com.inspurworld.msg.APSMessage;
import com.inspurworld.msg.codec.APSMessageCodec;
import com.inspurworld.msg.exception.ToolException;

/**
 * The class <code>SocketService</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class SocketService {

    private static final String TAG = "SocketService";

    protected static final int DAEMON_IO_TIMEOUT = 5000;

    protected Socket socket;

    protected APSMessageCodec codec = new APSMessageCodec();

    protected boolean connected;

    private String ip;

    private int port;

    public SocketService(String ip, int port) {
        super();
        this.ip = ip;
        this.port = port;
    }

    /**
     * Connect to agent daemon process.
     * 
     * @throws IOException
     */
    public void connect() throws IOException {
        if (socket == null || !connected || socket.isClosed()) {
            socket = new Socket(ip, port);
            socket.setSoTimeout(DAEMON_IO_TIMEOUT);
            socket.setKeepAlive(true);
            connected = true;
            Log.i(TAG, "Establish daemon connection on socket: " + socket);
        }
    }

    protected void disconnect() {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException ex) {
                Log.w(TAG, "Fail to close daemon socket: " + ex.getMessage(), ex);
            }
        }
        connected = false;
        socket = null;
    }

    protected void sendMsg(APSMessage message) throws IOException, ToolException {
        Log.d(TAG, "sendMsg");

        if (!connectIsAlive()) {
            connect();
        }
        final ByteBuffer buffer = codec.encode(message);

        OutputStream out = socket.getOutputStream();
        out.write(buffer.array(), 0, buffer.limit());
        out.flush();
    }

    protected ByteBuffer receive() throws Exception {
        int read = 0;
        int readed = 0;
        int totalLen = 0;
        ByteBuffer recvBuf = ByteBuffer.allocate(500 * 1024);
        try {
            InputStream ins = socket.getInputStream();

            readed = 0;
            recvBuf.clear();
            while (readed < APSMessage.LEN_HEADER) {
                read = ins.read(recvBuf.array(), readed, APSMessage.LEN_HEADER - readed);
                if (read < 0) {
                    throw new Exception("Lose connection to daemon.");
                }
                readed += read;
            }

            recvBuf.position(readed);

            totalLen = readed + recvBuf.getInt(APSMessage.OFFSET_BODY_LEN);

            // Log.e(TAG, "CODE BUF: " + CodecUtil.toHexString(buffer));

            read = 0;
            // logger.d(TAG, "read response totalLen: " + totalLen);
            while (readed < totalLen) {
                read = ins.read(recvBuf.array(), readed, totalLen - readed);
                if (read < 0) {
                    throw new Exception("Lose connection to daemon.");
                }
                readed += read;
            }

            recvBuf.position(readed);
        } catch (Exception ex) {
            disconnect();
            throw ex;
        }

        return (ByteBuffer) recvBuf.flip();
    }

    /**
     * Check daemon connection status.
     * 
     * @return
     */
    public boolean connectIsAlive() {
        return connected && socket != null && socket.isConnected();
    }

}
