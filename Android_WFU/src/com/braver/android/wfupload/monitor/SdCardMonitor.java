/**
 * @(#) SdCardMonitor.java Created on 2013-9-12
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.android.wfupload.monitor;

import android.content.Context;
import android.content.Intent;

/**
 * The class <code>SdCardMonitor</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class SdCardMonitor extends Monitor {

	private SdCardListener listener;

	/**
	 * @param action
	 */
	public SdCardMonitor(SdCardListener cardListener) {
		super( Intent.ACTION_MEDIA_REMOVED, Intent.ACTION_MEDIA_MOUNTED);
		this.listener = cardListener;
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
		if (action.equals(Intent.ACTION_MEDIA_REMOVED) && null != listener) {
			listener.onUnMount();
		} else if (action.equals(Intent.ACTION_MEDIA_MOUNTED) && null != listener) {
			listener.onMount();
		}

	}

	public static interface SdCardListener {

		void onMount();

		void onUnMount();

	}

	/* (non-Javadoc)
	 * @see com.braver.android.wfupload.monitor.Monitor#getTag()
	 */
	@Override
	protected String getTag() {
		return "SdCardMonitor";
	}

}
