/**
 * @(#) ScreenCapService.java Created on 2012-12-13
 *
 * Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.android.screen.capture;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * The class <code>ScreenCapService</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class ScreenCapService extends Service {

	private static final String TAG = "ScreenCapService";

	public static final String ACTION = "com.braver.android.ScreenCapService";

	public static final String STATE_ACTION = ACTION + ".State";

	public static final int START_SUCCESS = 1;

	public static final int START_FAIL = -1;

	/**
	 * {@inheritDoc}
	 * 
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see android.app.Service#onCreate()
	 */
	@Override
	public void onCreate() {
		int state = START_SUCCESS;
		try {
			ScreenCapManager.startAgentDaemon(this);// init daemo
		} catch (Exception e) {
			state = START_FAIL;
			Log.e(TAG, "", e);
		}
		super.onCreate();
		final Intent intent = new Intent(STATE_ACTION);
		intent.putExtra("state", state);
		sendBroadcast(intent);
	}

}
