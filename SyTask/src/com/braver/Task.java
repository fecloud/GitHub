/**
 * @(#) Task.java Created on 2013-3-18
 *
 * Copyright (c) 2013 com.braver All Rights Reserved
 */
package com.braver;

/**
 * The class <code>Task</code>
 * 
 * @author braver
 * @version 1.0
 */
public abstract class Task {

	public static final int SUCESS = 0;

	public static final int FAIL = -1;

	private int state;

	private CallBack back;

	private TaskExecuteable executeable;

	public Task(TaskExecuteable executeable) {
		this.executeable = executeable;
	}

	public boolean isSucess() {
		if (this.state == SUCESS)
			return true;
		return false;
	}

	/**
	 * @return the back
	 */
	public CallBack getBack() {
		return back;
	}

	/**
	 * @param back
	 *            the back to set
	 */
	public void setBack(CallBack back) {
		this.back = back;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	 * @param executeable
	 *            the executeable to set
	 */
	public void setExecuteable(TaskExecuteable executeable) {
		this.executeable = executeable;
	}

	/**
	 * @return the executeable
	 */
	public TaskExecuteable getExecuteable() {
		return executeable;
	}

	public interface CallBack {

		public void callBack(Task task);

	}

}
