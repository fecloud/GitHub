/**
 * @(#) TaskService.java Created on 2013-3-18
 *
 * Copyright (c) 2013 com.braver All Rights Reserved
 */
package com.braver;

/**
 * The class <code>TaskService</code>
 * 
 * @author braver
 * @version 1.0
 */
public class TaskService implements Runnable {

	private TaskContainer container = new TaskContainer();

	private Object object = new Object();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			final Task task = container.getTask();
			if (null == task) {
				try {
					synchronized (object) {
						object.wait();
					}
					
					continue;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			task.getExecuteable().execute(task);
		}

	}

	public void start() {
		new Thread(this).start();
	}

	public void addTask(Task task) {
		container.addTask(task);
		synchronized (object) {
			object.notifyAll();
		}
	}

}
