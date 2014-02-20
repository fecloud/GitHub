/**
 * @(#) AbstractTask.java Created on 2013-3-14
 *
 * Copyright (c) 2013 com.braver All Rights Reserved
 */
package com.braver.gohome.task;

import com.braver.gohome.task.executeable.TaskExecuteable;

/**
 * The class <code>BaseTask</code>
 * 
 * @author braver
 * @version 1.0
 */
public class BaseTask implements Task {

	protected TaskExecuteable executeable;

	protected TaskCallBack callBack;

	protected volatile long taskId;

	protected volatile long status = TaskStatus.SUBMIT;

	/**
	 * @param callBack
	 * @param url
	 * @param method
	 * @param header
	 * @param content
	 */
	public BaseTask(TaskCallBack callBack) {
		super();
		this.callBack = callBack;
	}

	/**
	 * @param callBack
	 * @param url
	 * @param method
	 * @param header
	 * @param content
	 */
	public BaseTask(TaskExecuteable executeable) {
		this.executeable = executeable;
	}

	public BaseTask(TaskExecuteable executeable, TaskCallBack callBack) {
		super();
		this.executeable = executeable;
		this.callBack = callBack;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oatos.task.Task#callBack()
	 */
	@Override
	public void callBack() {
		if (null != callBack)
			callBack.callBack(this);
	}

	/**
	 * @return the callBack
	 */
	public TaskCallBack getCallBack() {
		return callBack;
	}

	/**
	 * @param callBack
	 *            the callBack to set
	 */
	public void setCallBack(TaskCallBack callBack) {
		this.callBack = callBack;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oatos.task.Task#doTask()
	 */
	@Override
	public void doTask() {
		if (null != executeable)
			executeable.executeTask(this);
	}

	/**
	 * @param executeable
	 *            the executeable to set
	 */
	public void setExecuteable(TaskExecuteable executeable) {
		this.executeable = executeable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qycloud.task.Task#getTaskId()
	 */
	@Override
	public long getTaskId() {
		return taskId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qycloud.task.Task#setTaskId(long)
	 */
	@Override
	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.task.Task#setTaskStatus(long)
	 */
	@Override
	public void setTaskStatus(long status) {
		this.status = status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.task.Task#getTaskStatus()
	 */
	@Override
	public long getTaskStatus() {
		return this.status;
	}

}
