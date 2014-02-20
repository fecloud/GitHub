/**
 * @(#) SingleThreadTaskService.java Created on 2013-6-25
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.gohome.task.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;

import com.braver.gohome.task.Task;
import com.braver.gohome.task.container.TaskContainer;

/**
 * The class <code>SingleThreadTaskService</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class SingleThreadTaskService extends TaskService implements Runnable {

	Logger logger = Logger.getLogger(SingleThreadTaskService.class);

	private Future<?> future;

	private volatile boolean shutdownNow;

//	private static final int CORE_POOL_SIZE = 1;
//	private static final int MAXIMUM_POOL_SIZE = 10;
//	private static final int KEEP_ALIVE = 2;
//
//	private static final BlockingQueue<Runnable> sWorkQueue = new LinkedBlockingQueue<Runnable>(10);
//
//	private static final ThreadFactory sThreadFactory = new ThreadFactory() {
//		private final AtomicInteger mCount = new AtomicInteger(1);
//
//		public Thread newThread(Runnable r) {
//			return new Thread(r, "SingleThreadTaskService #" + mCount.getAndIncrement());
//		}
//	};
//
//	private static final ThreadPoolExecutor sExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE,
//			MAXIMUM_POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS, sWorkQueue, sThreadFactory);

	/**
	 * @param pool
	 * @param container
	 */
	protected SingleThreadTaskService(ExecutorService pool, TaskContainer container) {
		super(pool, container);
	}

	/**
	 * @param pool
	 * @param container
	 */
	public SingleThreadTaskService() {
		this(Executors.newSingleThreadExecutor(), new TaskContainer());
	}

	public synchronized void addTask(Task task) {
		if (!shutdownNow) {
			logger.debug("addTask " + task);
			this.container.addTask(task);
			autoStart();
		}
	}

	public synchronized void addFirstTask(Task task) {
		if (!shutdownNow) {
			logger.debug("addFirstTask " + task);
			this.container.addToFirstTask(task);
			autoStart();
		}
	}

	/**
	 * 单线程上传,没有任务后,自动wait
	 */
	protected void autoStart() {
		if (taskFinish())
			future = pool.submit(this);
	}

	/**
	 * 查看任务是否完成
	 * 
	 * @return
	 */
	public boolean taskFinish() {
		if (null == future || future.isDone())
			return true;
		return false;
	}

	public synchronized void shutdownNow() {
		shutdownNow = true;
	}

}
