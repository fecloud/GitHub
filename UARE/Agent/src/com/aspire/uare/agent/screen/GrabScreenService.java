/**
 * @(#) GrabScreen.java Created on 2012-10-19
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.uare.agent.screen;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.aspire.uare.agent.net.SocketService;
import com.inspurworld.msg.APSMessage;
import com.inspurworld.msg.common.GrabImageRequest;
import com.inspurworld.msg.common.GrabImageResponse;
import com.inspurworld.msg.data.ImageMessage;
import com.inspurworld.msg.exception.ToolException;

/**
 * The class <code>GrabScreen</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class GrabScreenService extends SocketService implements GrabImageResponseListener {

    private static final String TAG = "GrabScreenService";

    private List<GrabImageResponse> grabImageResponses = new ArrayList<GrabImageResponse>();

    private Object object = new Object();

    private byte[] imagedata;

    /**
     * Constructor
     * 
     * @param ip
     * @param port
     */
    public GrabScreenService(String ip, int port) {
        super(ip, port);
    }

    private void recevieMsg() throws Exception {
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

    public byte[] sendAndRecevie() {
        imagedata = null;
        try {
            grabImageResponses.clear();
            final GrabImageRequest request = new GrabImageRequest();
            sendMsg(request);
        } catch (Exception e) {
            Log.e(TAG, "sendMsg error", e);
            return null;
        }
        for (int i = 0; i < 1000; i++) {
            try {
                recevieMsg();
                if (null != imagedata)
                    break;
                Thread.sleep(200);
            } catch (Exception e) {
                Log.d(TAG, "", e);
            }

        }
        return imagedata;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.inspurworld.agent.screen.GrabImageResponseListener#onGrabImageResponse(com.inspurworld.msg.common.GrabImageResponse)
     */
    @Override
    public void onGrabImageResponse(GrabImageResponse response) {
        grabImageResponses.add(response);
        final ImageMessage imageMessage = response.getImageMessage();
        final int current = imageMessage.getCurrentPackageSequenceNumber();
        final int total = imageMessage.getTotalPackageCount();
        if (current == total) {
            // if (null != grabScreenFinish)
            try {
                imagedata = grabScreenFinish();
            } catch (IOException e) {
                Log.e(TAG, "grabScreenFinish write error", e);
            }
        }
    }

    private byte[] grabScreenFinish() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (GrabImageResponse response : grabImageResponses) {
            out.write(response.getImageMessage().getImageData());

        }
        out.flush();
        synchronized (object) {
            object.notifyAll();
        }
        return out.toByteArray();
    }

    // class ReceiveThread extends Thread {
    //
    // public boolean flag;
    //
    // /**
    // * {@inheritDoc}
    // *
    // * @see java.lang.Thread#run()
    // */
    // @Override
    // public void run() {
    // while (flag) {
    // try {
    // if (connectIsAlive()) {
    // recevieMsg();
    // } else {
    // connect();
    // }
    //
    // } catch (Exception e) {
    // Log.e(TAG + "_ReceiveThread", "run error", e);
    // }
    // }
    // }
    //
    // public boolean isFlag() {
    // return flag;
    // }
    //
    // public void setFlag(boolean flag) {
    // this.flag = flag;
    // }
    //
    // }

    /**
     * 
     */
    public void start() {
        try {
            connect();
        } catch (Exception e) {
            Log.e(TAG, "start error", e);
        }
    }

    public void stop() {
        disconnect();
    }

}
