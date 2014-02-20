/**
 * @(#) Test.java Created on 2013-3-18
 *
 * Copyright (c) 2013 com.braver All Rights Reserved
 */
package com.braver.test;

import com.braver.Task;
import com.braver.Task.CallBack;
import com.braver.TaskService;

/**
 * The class <code>Test</code>
 * 
 * @author braver
 * @version 1.0
 */
public class Test implements CallBack {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		new TH(false,10).start();
		final TH th = new TH(true,100000);
		th.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.Task.CallBack#callBack(com.braver.Task)
	 */
	@Override
	public void callBack(Task task) {
		System.out.println("任务完成!");

	}

}

class TH extends Thread {

	private int max = 0;
	
	public TH(boolean cn,int i) {
		setDaemon(cn);
		this.max = i;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		for (int i = 0; i < max; i++)
			System.out.println("--" + i);
	}

}
