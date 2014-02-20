/**
 * @(#) TaskTaskItem.java Created on 2012-9-14
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.task.entity;

import java.util.List;

/**
 * The class <code>TaskTaskItem</code>
 * 
 * @author Administrator
 * @version 1.0
 */
public class TaskTaskItem {
    /**
     * 任务
     */
    private Task task;
    /**
     * 任务的所有的Item
     */
    private List<TaskItem> listTaskItem;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<TaskItem> getListTaskItem() {
        return listTaskItem;
    }

    public void setListTaskItem(List<TaskItem> listTaskItem) {
        this.listTaskItem = listTaskItem;
    }

}
