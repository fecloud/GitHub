/**
 * @(#) Monitor.java Created on 2013-9-12
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.android.wfupload.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.util.Log;

/**
 * The class <code>Monitor</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public abstract class Monitor extends BroadcastReceiver {

	private String[] actions;

	private int priority;

	/**
	 * @param action
	 */
	public Monitor(String... actions) {
		super();
		this.actions = actions;
	}

	/**
	 * @param action
	 */
	public Monitor(int priority, String... actions) {
		super();
		this.priority = priority;
		this.actions = actions;
	}

	public void start(Context mContext) {
		if (null != actions) {
			final IntentFilter filter = new IntentFilter();
			for (String action : actions) {
				filter.addAction(action);
			}
			if (priority != 0)
				filter.setPriority(priority);
			mContext.registerReceiver(this, filter);
		}
		Log.d(getTag(), "start monitor ...");
	}

	protected abstract String getTag();

	public void stop(Context mContext) {
		mContext.unregisterReceiver(this);
		Log.d(getTag(), "stop monitor ...");
	}

	/**
	 * @return the action
	 */
	public String[] getActions() {
		return actions;
	}

	/**
	 * @param action
	 *            the action to set
	 */
	public void setAction(String... actions) {
		this.actions = actions;
	}

	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

}
