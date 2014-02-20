/**
 * @(#) ServerWorker.java Created on 2013-10-12
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.undpunch.server.worker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.braver.undpunch.common.net.PunchSocket;
import com.braver.undpunch.server.ClientContainer;
import com.braver.undpunch.server.ClientContainer.ContainerListener;

/**
 * The class <code>ServerWorker</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class ServerWorker implements Runnable, ContainerListener {

	private Thread serverWorkerThread;

	private ClientContainer container;

	private ExecutorService executors = Executors.newSingleThreadExecutor();

	private boolean flag;

	/**
	 * @param container
	 */
	public ServerWorker(ClientContainer container) {
		super();
		this.container = container;
	}

	public void start() {
		if (serverWorkerThread == null) {
			serverWorkerThread = new Thread(this);
			serverWorkerThread.start();
		}
		flag = true;
	}

	public void stop() {
		serverWorkerThread = null;
		executors.shutdownNow();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.undpunch.server.ClientContainer.ContainerListener#onAdd(com
	 * .braver.undpunch.common.net.PunchSocket)
	 */
	@Override
	public void onAdd(PunchSocket punchSocket) {
		executors.submit(new WorkerThread());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.undpunch.server.ClientContainer.ContainerListener#onRemove
	 * (com.braver.undpunch.common.net.PunchSocket)
	 */
	@Override
	public void onRemove(PunchSocket punchSocket) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.undpunch.server.ClientContainer.ContainerListener#onClear()
	 */
	@Override
	public void onClear() {
		// TODO Auto-generated method stub

	}

}
