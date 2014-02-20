/**
 * @(#) Task.java Created on 2012-10-23
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server.task;


/**
 * The class <code>Task</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public interface Task {

    public static final short TASK_STATE_NOEXE = 0;

    public static final short TASK_STATE_FAIL = -1;

    public static final short TASK_STATE_SUCCESS = 1;

    public boolean doTask();

    /**
     * return task doing state
     * 
     * @return
     */
    public int taskState();

}
