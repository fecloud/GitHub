/**
 * @(#) ScreenCaptureImpl.java Created on 2012-12-18
 *
 * Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.android.screen.capture;

import android.util.Log;

import com.braver.android.Config;
import com.braver.android.screen.capture.CaptureTask.CaptureTaskCallBack;

/**
 * The class <code>ScreenCaptureImpl</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class ScreenCaptureImpl implements IScreenCapture, CaptureTaskCallBack {

	private static final String TAG = "ScreenCaptureImpl";

	private Object lock = new Object();

	private CaptureTask task;

	private byte[] arr = new byte[Config.buffer];

	private ScreenCaptureService captureService = ScreenCaptureService
			.getInstance();

	public ScreenCaptureImpl() {
		task = new CaptureTask(arr, this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.android.screen.capture.IScreenCapture#capture()
	 */
	@Override
	public byte[] capture() {
		synchronized (lock) {
			captureService.addTask(task);
			try {
				lock.wait();
			} catch (InterruptedException e) {
				Log.e(TAG, "", e);
			}
		}
		byte[] copy = new byte[task.len];
		System.arraycopy(task.array, 0, copy, 0,
				Math.min(task.array.length, task.len));
		return copy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.android.screen.capture.CaptureTask.CaptureTaskCallBack#callBack
	 * (com.braver.android.screen.capture.CaptureTask)
	 */
	@Override
	public void callBack(CaptureTask captureTask) {
		synchronized (lock) {
			lock.notifyAll();
		}
	}

}
