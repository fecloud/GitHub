/**
 * @(#) MainTest.java Created on 2012-10-19
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.msg.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import com.inspurworld.msg.APSMessage;
import com.inspurworld.msg.codec.APSMessageCodec;
import com.inspurworld.msg.common.GrabImageRequest;
import com.inspurworld.msg.common.GrabImageResponse;
import com.inspurworld.msg.data.ImageMessage;
import com.inspurworld.msg.exception.ToolException;

/**
 * The class <code>MainTest</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class MainTest implements GrabImageResponseListener {

    private List<GrabImageResponse> grabImageResponses = new ArrayList<GrabImageResponse>();

    private Socket socket;

    public MainTest(Socket socket) {
        super();
        this.socket = socket;
    }

    /**
     * @param args
     * @throws ToolException
     * @throws IOException
     * @throws UnknownHostException
     */
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 6100);
        MainTest mainTest = new MainTest(socket);
       for(int i = 0 ;i < 10;)
           mainTest.sendAndRecevie();
       Thread.sleep(2000);
    }

    public void sendAndRecevie() throws Exception {
        grabImageResponses.clear();
        final GrabImageRequest request = new GrabImageRequest();
        APSMessageCodec codec = new APSMessageCodec();
        final ByteBuffer buffer = codec.encode(request);

        OutputStream out = socket.getOutputStream();
        out.write(buffer.array(), 0, buffer.limit());
        out.flush();

        // Do receive
        ByteBuffer daemonRsp = receive();

        try {
            APSMessage asp = codec.decode(daemonRsp);
            this.onGrabImageResponse((GrabImageResponse) asp);
            if (asp instanceof GrabImageResponse) {
                int count = ((GrabImageResponse) asp).getImageMessage().getTotalPackageCount() - 1;
                while (count > 0) {
                    asp = codec.decode(receive());
                    this.onGrabImageResponse((GrabImageResponse) asp);
                    count--;
                }
            }
        } catch (ToolException e) {
            throw new Exception("decode the response message of daemon catch exception. " + e.getMessage(), e);
        }
    }

    private ByteBuffer receive() throws Exception {
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

    private void disconnect() {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                // Log.w(TAG, "Fail to close daemon socket: " + ex.getMessage(), ex);
            }
        }
        // connected = false;
        socket = null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.inspurworld.msg.test.GrabImageResponseListener#onGrabImageResponse(com.inspurworld.msg.common.GrabImageResponse)
     */
    @Override
    public void onGrabImageResponse(GrabImageResponse response) {
        grabImageResponses.add(response);
        final ImageMessage imageMessage = response.getImageMessage();
        final int current = imageMessage.getCurrentPackageSequenceNumber();
        final int total = imageMessage.getTotalPackageCount();
        if (current == total) {
            try {
                writeFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeFile() throws IOException {
        File file = new File(System.getProperty("user.dir") + File.separator + System.currentTimeMillis() + ".jpg");
        FileOutputStream out = new FileOutputStream(file);
        for (GrabImageResponse response : grabImageResponses) {
            out.write(response.getImageMessage().getImageData());
        }
        out.flush();
        out.close();
    }

}
