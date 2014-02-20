/**
 * @(#) WFUploadService.java Created on 2013-9-10
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.android.wfupload.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.braver.android.wfupload.http.server.WFUploadServer;
import com.braver.android.wfupload.process.impl.WFUAbleImpl;

/**
 * The class <code>WFUploadService</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class WFUploadService extends Service {

	final static String TAG = "WFUploadService";

	public static final String ACTION = "com.braver.android.wfupload.service.WFUploadService";
	
	private WFUAbleImpl wfuAbleImpl;
	
	private WFUploadServer uploadServer;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent intent) {
		if(null == wfuAbleImpl)
			wfuAbleImpl = new WFUAbleImpl(uploadServer);
		return wfuAbleImpl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Service#onCreate()
	 */
	@Override
	public void onCreate() {
		Log.d(TAG, "onCreate");
		super.onCreate();
		uploadServer = new WFUploadServer(this);
		uploadServer.start();
	}
	
	/**
	 * (non-Javadoc)
	 * @see android.app.Service#onDestroy()
	 */
	@Override
	public void onDestroy() {
	    uploadServer.stop();
	    super.onDestroy();
	    System.exit(1);
	}

	public static final boolean startService(Context mContext) {
		final Intent intent = new Intent(ACTION);
		mContext.startService(intent);
		return true;
	}

	public static final boolean stopService(Context mContext) {
		final Intent intent = new Intent(ACTION);
		mContext.stopService(intent);
		return true;
	}

}
