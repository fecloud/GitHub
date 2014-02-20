/**
 * @(#) ImageUpLoadService.java Created on 2012-10-22
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.uare.agent.upload;

import java.nio.ByteBuffer;

import android.util.Log;

import com.aspire.uare.agent.net.SocketService;
import com.inspurworld.msg.APSMessage;
import com.inspurworld.msg.common.CommonResponse;
import com.inspurworld.msg.common.ConnectionRequest;
import com.inspurworld.msg.common.ConnectionResponse;
import com.inspurworld.msg.common.ImageUpLoadRespone;
import com.inspurworld.msg.exception.ToolException;

/**
 * The class <code>ImageUpLoadService</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class ImageUpLoadService extends SocketService implements Runnable {

    private static final String TAG = "ImageUpLoadService";

    private TaskContainer container = new TaskContainer();

    private Thread thisThread;

    private boolean flag;

    private Object lock = new Object();

    public ImageUpLoadService(String ip, int port) {
        super(ip, port);
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        Task task = null;
        try {
            while (flag) {
                synchronized (lock) {
                    task = container.getRuningTask();
                    if (null == task) {
                        // 没有任务,把连接关掉
                        sendDisConnectionRequest();
                        disconnect();
                        Log.d(TAG, "upload thread wait");
                        lock.wait();
                        Log.d(TAG, "upload thread runing");
                    } else {
                        // 执行任务
                        if (!sendAndRecevie(task)) {
                            // 如果任务失败,添加到失败组
                            container.addFailTask(task);
                        }
                    }
                }
            }
        } catch (Exception e) {
            Log.d(TAG, "", e);
        }
    }

    /**
     * @param task
     */
    private boolean sendAndRecevie(Task task) {
        try {
            sendMsg(task.doTask());
            return recevieMsg();
        } catch (Exception e) {
            Log.e(TAG, "", e);
        }
        return false;
    }

    private boolean recevieMsg() throws Exception {
        ByteBuffer daemonRsp = receive();
        try {
            APSMessage asp = codec.decode(daemonRsp);
            if (asp instanceof ImageUpLoadRespone) {
                final ImageUpLoadRespone respone = (ImageUpLoadRespone) asp;
                if (respone.getErrcode() == CommonResponse.STATUS_OK) {
                    return true;
                }
            }
        } catch (ToolException e) {
            Log.e(TAG, "decode the response message of daemon catch exception. " + e.getMessage(), e);
        }
        return false;
    }

    public void start() {
        flag = true;
        if (null == thisThread) {
            thisThread = new Thread(this);
            thisThread.start();
        }

    }

    public boolean uploadImage(String path) {
        Log.d(TAG, "uploadImage path:" + path);
        // 当所有任务都执行完成时,通知线程继续
        if (container.getFailTasks().size() < 1 && container.getWaitTasks().size() < 1) {
            synchronized (lock) {
                container.AddTask(new ImageUpLoadTask(path));
                lock.notifyAll();
            }
        } else {
            container.AddTask(new ImageUpLoadTask(path));
        }

        // final ImageUpLoadRequest request = new ImageUpLoadRequest();
        // request.setName("android.os");
        // final ImageMessage message = new ImageMessage();
        // message.setCurrentPackageSequenceNumber(1);
        // message.setTotalPackageCount(1);
        // try {
        // message.setImageData(FileUtil.readFile(path));
        // } catch (IOException e) {
        // Log.e(TAG, "readFile path:" + path + "error", e);
        // }
        // request.setImageMessage(message);
        // try {
        // sendMsg(request);
        // receive();
        // } catch (Exception e) {
        // Log.e(TAG, "", e);
        // return false;
        // }
        // sendDisConnectionRequest();
        return true;
    }

    public void stop() {
        flag = false;
        this.thisThread = null;
    }

    public boolean sendAndRecevie() {
        return false;
    }

    /**
     * 请求连接
     * 
     * @return
     */
    public boolean sendConnectionRequest() {
        Log.d(TAG, "sendConnectionRequest");
        final ConnectionRequest request = new ConnectionRequest();
        request.setConnectionType(ConnectionRequest.CONNECT);
        try {
            sendMsg(request);
            final ConnectionResponse response = (ConnectionResponse) codec.decode(receive());
            if (response.getErrcode() == CommonResponse.STATUS_OK) {
                return true;
            }
        } catch (Exception e) {
            Log.d(TAG, "", e);
        }
        return false;
    }

    /**
     * 请求断开连接
     * 
     * @return
     */
    public boolean sendDisConnectionRequest() {
        Log.d(TAG, "sendDisConnectionRequest");
        final ConnectionRequest request = new ConnectionRequest();
        request.setConnectionType(ConnectionRequest.DISCONNECT);
        try {
            sendMsg(request);
            final ConnectionResponse response = (ConnectionResponse) codec.decode(receive());
            if (response.getErrcode() == CommonResponse.STATUS_OK) {
                return true;
            }
        } catch (Exception e) {
            Log.d(TAG, "", e);
        }
        return false;
    }

}
