/**
 * @(#) Task.java Created on 2012-10-22
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * The class <code>Task</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class TaskContainer {
    
    private Logger logger = Logger.getLogger(getClass());

    private List<Task> waitTasks = new ArrayList<Task>();

    private List<Task> failTasks = new ArrayList<Task>();

    private Task runingTask;

    public synchronized Task getRuningTask() {
        logger.debug("getRuningTask");
        if (waitTasks.size() > 0) {
            runingTask = waitTasks.get(0);
            waitTasks.remove(0);
        } else if (failTasks.size() > 0) {
            runingTask = failTasks.get(0);
            failTasks.remove(0);
        } else {
            runingTask = null;
        }
        return runingTask;
    }

    public synchronized List<Task> getWaitTasks() {
        return waitTasks;
    }

    public synchronized List<Task> getFailTasks() {
        return failTasks;
    }

    public synchronized void AddTask(Task task) {
        logger.debug("AddTask");
        this.waitTasks.add(task);
    }

    public synchronized void addFailTask(Task task) {
        logger.debug("addFailTask");
        this.failTasks.add(task);
    }

}
