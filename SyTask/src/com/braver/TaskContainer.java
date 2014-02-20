/**
 * @(#) TaskContainer.java Created on 2013-3-18
 *
 * Copyright (c) 2013 com.braver All Rights Reserved
 */
package com.braver;

import java.util.ArrayList;
import java.util.List;

/**
 * The class <code>TaskContainer</code>
 * 
 * @author braver
 * @version 1.0
 */
public class TaskContainer {

	private List<Task> tasks = new ArrayList<Task>();

	/**
	 * 拿一个任务
	 * 
	 * @return
	 */
	public synchronized Task getTask() {
		if (!tasks.isEmpty()) {
			final Task task = tasks.get(0);
			tasks.remove(task);
			return task;
		}
		return null;

	}

	/**
	 * 添加一个任务
	 * 
	 * @param task
	 */
	public void addTask(Task task) {
		synchronized (tasks) {
			tasks.add(task);
		}

	}

}
