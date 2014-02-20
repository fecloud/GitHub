/**
 * @(#) TaskContainer.java Created on 2013-3-14
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.gohome.task.container;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;

import com.braver.gohome.task.Task;

/**
 * The class <code>TaskContainer</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class TaskContainer {

	Logger logger = Logger.getLogger(TaskContainer.class);

	protected static final String TAG = "TaskContainer";

	private volatile AtomicLong atomicLong = new AtomicLong();

	protected volatile LinkedList<Task> waitTasks = new LinkedList<Task>();

	protected volatile Task runingTask;

	public synchronized Task getRuningTask() {
		if (waitTasks.size() > 0) {
			runingTask = waitTasks.get(0);
			waitTasks.remove(0);
		} else {
			runingTask = null;
		}
		return runingTask;
	}

	public synchronized List<Task> getTasks() {
		logger.debug("getAllTaskId waitTasks:" + waitTasks.size());
		return waitTasks;
	}

	public synchronized List<Long> getAllTaskId() {
		logger.debug("getAllTaskId");
		final List<Long> tasks = new ArrayList<Long>();
		for (Task t : getTasks()) {
			tasks.add(t.getTaskId());
		}
		return tasks;
	}

	/*
	 * public synchronized List<T> getFailTasks() { return failTasks; }
	 */

	public synchronized void addTask(Task task) {
		logger.debug("AddTask");
		if (null != task)
			task.setTaskId(atomicLong.addAndGet(1l));
		this.waitTasks.add(task);
	}

	public synchronized void addToFirstTask(Task task) {
		if (null != task)
			task.setTaskId(atomicLong.addAndGet(1l));
		this.waitTasks.addFirst(task);
	}

	public synchronized void removeTask(long taskId) {
		if (null != waitTasks && waitTasks.size() > 0) {
			Task temp = null;
			for (Task t : waitTasks) {
				if (t.getTaskId() == taskId) {
					temp = t;
					break;
				}
			}

			if (temp != null)
				waitTasks.remove(temp);

		}
	}

	// public synchronized void addFailTask(T task) {
	// Log.d(TAG, "addFailTask");
	// this.failTasks.add(task);
	// }

	public synchronized Task getTask(long taskId) {
		if (null != waitTasks && waitTasks.size() > 0) {
			for (Task t : waitTasks) {
				if (t.getTaskId() == taskId)
					return t;
			}
		}
		return null;
	}

	public synchronized void clear() {
		waitTasks.clear();
	}

	/**
	 * 取当前正在运行的任务
	 * 
	 * @return
	 */
	public synchronized Task getCurrentRuningTask() {
		return runingTask;
	}
}
