/**
 * @(#) EatBreadExecuteable.java Created on 2013-3-18
 *
 * Copyright (c) 2013 com.braver All Rights Reserved
 */
package com.braver;

/**
 * The class <code>EatBreadExecuteable</code>
 * 
 * @author braver
 * @version 1.0
 */
public class EatBreadExecuteable implements TaskExecuteable {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.TaskExecuteable#execute(com.braver.Task)
	 */
	@Override
	public void execute(Task task) {
		System.out.println("开始吃面包");
		System.out.println("结束吃面包");
		task.setState(Task.SUCESS);
		task.getBack().callBack(task);
		System.out.println(Thread.currentThread().getName());
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}

}
