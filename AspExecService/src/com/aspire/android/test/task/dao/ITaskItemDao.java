/**
 * @(#) ITaskDao.java Created on 2012-5-9
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.task.dao;

import java.util.List;

import com.aspire.android.common.db.IBaseDao;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.task.entity.TaskItem;

/**
 * The class <code>ITaskItemDao</code>
 * 
 * @author gouanming
 * @version 1.0
 */
public interface ITaskItemDao extends IBaseDao<TaskItem, Long> {
    /**
     * 
     * @param taskId
     * @return
     */
    public List<TaskItem> getTaskItem(long taskId) throws MException;

    public List<TaskItem> getAllTaskItem(String taskKeyCode, String servType) throws MException;

    public List<TaskItem> getAllTaskItem(Iterable<Long> taskIds, Iterable<String> serviceCodes, Iterable<String> testKeyCodes) throws MException;
}
