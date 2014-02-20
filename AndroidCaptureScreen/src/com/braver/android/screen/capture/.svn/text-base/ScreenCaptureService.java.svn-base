/**
 * @(#) ScreenCaptureService.java Created on 2012-12-18
 *
 * Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.android.screen.capture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.util.Log;

/**
 * The class <code>ScreenCaptureService</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public final class ScreenCaptureService {

	public static final String TAG = "ScreenCaptureService";

	private static ScreenCaptureService service;

	private List<CaptureTask> tasks = Collections
			.synchronizedList(new ArrayList<CaptureTask>());

	private static CaptureThread captureThread;

	private ScreenCaptureService() {

	}

	public static synchronized ScreenCaptureService getInstance() {
		if (null == service)
			service = new ScreenCaptureService();
		return service;
	}

	/**
	 * add task
	 * 
	 * @param task
	 */
	public void addTask(CaptureTask task) {
		// Log.d(TAG, "addTask");
		synchronized (tasks) {
			this.tasks.add(task);
			if (null == captureThread) {
				captureThread = new CaptureThread(this);
				captureThread.start();
			}
			tasks.notifyAll();
		}
	}

	/**
	 * get a task
	 * 
	 * @return
	 */
	public synchronized CaptureTask getTask() {
		// Log.d(TAG, "getTask");
		synchronized (tasks) {
			CaptureTask task = null;
			if (tasks.size() > 0) {
				task = tasks.get(0);
				tasks.remove(0);
			} else {
				try {
					// Log.d(TAG, " tasks.wait");
					tasks.wait();
				} catch (InterruptedException e) {
					Log.e(TAG, "", e);

				}
			}
			return task;
		}

	}

}
