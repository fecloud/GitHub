/**
 * @(#) WIFIListener.java Created on 2013-9-11
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.android.wfupload.monitor;

import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

/**
 * The class <code>WIFIListener</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class WIFIMonitor extends Monitor {

	static final String TAG = "WIFIMonitor";

	private WIFIListener listener;

	/**
	 * @param listener
	 */
	public WIFIMonitor(WIFIListener listener) {
		super(WifiManager.WIFI_STATE_CHANGED_ACTION, WifiManager.NETWORK_STATE_CHANGED_ACTION);
		this.listener = listener;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context,
	 * android.content.Intent)
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		final String action = intent.getAction();
		Log.d(TAG, action);

		if (action.equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) { //wifi网络的状态
			final NetworkInfo info = (NetworkInfo) intent
					.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
			if (info.getState().equals(NetworkInfo.State.CONNECTED) && null != listener) {
				// wifi连接
				listener.onConnection();
			} else if (info.getState().equals(NetworkInfo.State.DISCONNECTED) && null != listener) {
				//listener.onDisConnection();
			}
		} else if (action.equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {//wifi的状态
			int wifistate = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
					WifiManager.WIFI_STATE_DISABLED);
			if (wifistate == WifiManager.WIFI_STATE_DISABLED) {// 如果关闭
				listener.onDisConnection();
			}
		}
	}

	public static interface WIFIListener {

		void onConnection();

		void onDisConnection();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.android.wfupload.monitor.Monitor#getTag()
	 */
	@Override
	protected String getTag() {
		return TAG;
	}

}
