/**
 * @(#) HttpService.java Created on 2013-3-14
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.gohome.task.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.braver.gohome.task.Task;
import com.braver.gohome.task.container.TaskContainer;

/**
 * The class <code>TaskService</code> <br>
 * 任务执行中心
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class TaskService implements Runnable {

	Logger logger = Logger.getLogger(TaskService.class);

	private static final TaskService SERVICE = new TaskService();

	protected TaskContainer container; // 任务容器

	protected ExecutorService pool;

	protected boolean flag = true; // 执行标志位

	private TaskService() {
		container = new TaskContainer();
		pool = Executors.newScheduledThreadPool(5); // 构造最多5个线程
	}

	protected TaskService(ExecutorService pool, TaskContainer container) {
		this.pool = pool;
		this.container = container;
	}

	public static synchronized TaskService getInstance() {
		return SERVICE;
	}

	/**
	 * @return the container
	 */
	public TaskContainer getContainer() {
		return container;
	}

	/**
	 * @param container
	 *            the container to set
	 */
	public void setContainer(TaskContainer container) {
		this.container = container;
	}

	/**
	 * @return the pool
	 */
	public ExecutorService getPool() {
		return pool;
	}

	/**
	 * @param pool
	 *            the pool to set
	 */
	public void setPool(ExecutorService pool) {
		this.pool = pool;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// 线程从任务容器中获得任务
		Task task = null;
		while (flag) {
			task = container.getRuningTask();
			if (null == task) {
				break;
			} else {
				// 执行任务
				try {
					task.doTask();
				} catch (Exception e) {
					logger.error("出现重大错误,线程结束", e);
					pool.shutdownNow();
				}
			}
		}
	}

	/**
	 * 添加任务,任务按队列执行
	 * 
	 * @param task
	 */
	public void addTask(Task task) {
		this.container.addTask(task);
		logger.debug("addTask taskId:" + task.getTaskId());
		pool.submit(this);
	}

	/**
	 * 添加任务,任务会在上一个任务执行完成后,优先执行这个任务
	 * 
	 * @param task
	 */
	public void addToFirstTask(Task task) {
		this.container.addTask(task);
		logger.debug("addTask taskId:" + task.getTaskId());
		pool.submit(this);
	}

	public void terminat() {
		if (null != pool) {
			pool.shutdownNow();
		}
	}

}
