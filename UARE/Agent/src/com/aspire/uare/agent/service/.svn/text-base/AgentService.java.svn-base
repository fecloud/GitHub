/**
 * @(#) ScreenService.java Created on 2012-10-19
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.uare.agent.service;

import java.io.File;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;

import com.aspire.uare.agent.AgentActivity;
import com.aspire.uare.agent.R;
import com.aspire.uare.agent.screen.GrabScreenService;
import com.aspire.uare.agent.screen.WhileGrabScreen;
import com.aspire.uare.agent.screen.WhileGrabScreenTask;
import com.aspire.uare.agent.upload.ImageUpLoadService;
import com.aspire.uare.agent.util.FileUtil;
import com.aspire.uare.agent.util.GetCurrentScreenInfo;
import com.aspire.uare.agent.util.Utils;

/**
 * The class <code>AgentService</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class AgentService extends Service implements WhileGrabScreenTask {

    private NotificationManager nm;

    private WakeLock wakeLock;

    private static final String TAG = "AgentService";

    private GrabScreenService grabScreenService;

    private ImageUpLoadService imageUpLoadService;

    private WhileGrabScreen whileGrabScreen;

    private GetCurrentScreenInfo screenInfo;

    /**
     * {@inheritDoc}
     * 
     * @see android.app.Service#onCreate()
     */
    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate...");
        try {
            acquireWakeLock();
            nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            setForeground(true);
            DaemonManager.startAgentDaemon(this);
            screenInfo = new GetCurrentScreenInfo(this);
            nofityState(1);
        } catch (Exception e) {
            Log.e(TAG, "DaemonManager startAgentDaemon error", e);
            nofityState(-1);
        }
        super.onCreate();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "All clients have unbound with unbindService()");
        return super.onUnbind(intent);
    }

    /**
     * {@inheritDoc}
     * 
     * @see android.app.Service#onDestroy()
     */
    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy...");
        super.onDestroy();
        if (null != grabScreenService) {
            grabScreenService.stop();
        }
        if (null != imageUpLoadService) {
            imageUpLoadService.start();
        }
        if (null == screenInfo)
            screenInfo = null;

        releaseWakeLock();
        
        nm.cancel(Process.myUid());
    }

    /**
     * {@inheritDoc}
     * 
     * @see android.app.Service#onBind(android.content.Intent)
     */
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "a client bind");
        return mBinder;
    }

    public synchronized byte[] localGrabScreen() {
        startGrabScreenService();
        return grabScreenService.sendAndRecevie();
    }

    public synchronized boolean localUploadImage(String path) {
        Log.d(TAG, "localUploadImage path:" + path);
        startImageUpLoadService();
        if (null != path && !"".equals(path.trim())) {
            imageUpLoadService.uploadImage(path);
        }
        return false;
    }

    public boolean startImageUpLoadService() {
        if (null == imageUpLoadService) {
            final String uploadIp = Utils.getUpLoadServerIp(this);
            final int uploadPort = Utils.getUpLoadServerPort(this);
            imageUpLoadService = new ImageUpLoadService(uploadIp, uploadPort);
            imageUpLoadService.start();
        }
        return true;
    }

    public boolean startGrabScreenService() {
        if (null == grabScreenService) {
            grabScreenService = new GrabScreenService("127.0.0.1", 54001);
            grabScreenService.start();
        }
        return true;
    }

    private final RemoteAgentService.Stub mBinder = new RemoteAgentService.Stub() {

        @Override
        public byte[] grabScreen() throws RemoteException {
            Log.d(TAG, "grabScreen for aidl");
            return localGrabScreen();
        }

        @Override
        public boolean uploadImage(String path) throws RemoteException {
            return localUploadImage(path);
        }

        @Override
        public int startGrabScreen() throws RemoteException {
            if (null == whileGrabScreen) {
                whileGrabScreen = new WhileGrabScreen(Utils.getRate(AgentService.this), AgentService.this);
                whileGrabScreen.start();
            }
            return 1;
        }

        @Override
        public int stopGrabScreen() throws RemoteException {
            if (null != whileGrabScreen) {
                whileGrabScreen.stop();
                whileGrabScreen = null;
            }
            return 0;
        }
    };

    /**
     * 任务栏上图标
     * 
     * @param state
     */
    @SuppressLint("NewApi")
    public void nofityState(int state) {

        Notification n = null;
        if (state > 0) {
            n = new Notification(R.drawable.ic_launcher, getString(R.string.startsucess),
                    System.currentTimeMillis());
        } else {
            n = new Notification(R.drawable.ic_launcher, getString(R.string.startfail),
                    System.currentTimeMillis());
        }

        n.flags = Notification.FLAG_ONGOING_EVENT;
        Intent i = new Intent(this, AgentActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        // PendingIntent
        final PendingIntent contentIntent = PendingIntent.getActivity(this, R.string.app_name, i,
                PendingIntent.FLAG_UPDATE_CURRENT);

        n.setLatestEventInfo(this, getString(R.string.app_name), n.tickerText, contentIntent);
        nm.notify(Process.myUid(), n);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.inspurworld.agent.screen.WhileGrabScreenTask#doTask()
     */
    @Override
    public void doTask() {
        Log.d(TAG, "AgentService doTask");
        // 截图并上传
        final byte[] bs = localGrabScreen();
        final String nameString = screenInfo.currentActivityInfo() + ".jpg";
        final String filename = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "inspur"
                + File.separator + "agent" + File.separator + nameString;
        try {
            FileUtil.writeFile(filename, bs);
            localUploadImage(filename);
        } catch (Exception e) {
            Log.e(TAG, "write file " + filename + "error", e);
        }

    }

    /**
     * Get wake lock
     */
    private void acquireWakeLock() {
        Log.d(TAG, "Acquiring wake lock");
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, this.getClass()
                .getCanonicalName());
        wakeLock.acquire();
    }

    /**
     * Release wake lock
     */
    private void releaseWakeLock() {
        if (wakeLock != null && wakeLock.isHeld()) {
            wakeLock.release();
            wakeLock = null;
        }
    }
}
