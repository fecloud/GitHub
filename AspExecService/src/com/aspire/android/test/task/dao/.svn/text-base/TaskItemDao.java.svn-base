/**
 * @(#) TaskDao.java Created on 2012-5-9
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.task.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.aspire.android.common.db.BaseDao;
import com.aspire.android.common.db.DatabaseHelper;
import com.aspire.android.common.db.DatabaseManager;
import com.aspire.android.common.exception.ExceptionHandler;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.Constants;
import com.aspire.android.test.task.entity.Task;
import com.aspire.android.test.task.entity.TaskItem;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

/**
 * The class <code>TaskItemDao</code>
 * 
 * @author gouanming
 * @version 1.0
 */
@Singleton
public class TaskItemDao extends BaseDao<TaskItem, Long> implements ITaskItemDao {
    /**
     * Constructor
     * 
     * @param databaseHelper
     * @throws MException
     */
    public TaskItemDao() throws MException {
        super(DatabaseManager.getDBHelper(), Constants.ATS_TASK_ITEM, TaskItem.class);
    }

    /**
     * 
     * Constructor
     * 
     * @param databaseHelper
     * @throws MException
     */
    @Inject
    public TaskItemDao(DatabaseHelper databaseHelper) throws MException {
        super(databaseHelper, Constants.ATS_TASK_ITEM, TaskItem.class);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.dao.ITaskItemDao#getTaskItem(long)
     */
    public List<TaskItem> getTaskItem(long taskId) throws MException {
        HashMap<String, Object> simpledataMap = new HashMap<String, Object>();
        simpledataMap.put("TASK_ID", taskId);
        List<TaskItem> list = null;
        try {
            list = this.dao.queryForFieldValues(simpledataMap);
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error getTaskItem(long taskId)");
            if (mexception != null) {
                throw mexception;
            }
        }
        return list;
    }

    public List<TaskItem> getAllTaskItem(String taskKeyCode, String servType) throws MException {
        HashMap<String, Object> simpledataMap = new HashMap<String, Object>();
        simpledataMap.put("TESTKEYCODE", taskKeyCode);
        simpledataMap.put("SERVTYPE", servType);
        List<TaskItem> list = null;
        try {
            list = this.dao.queryForFieldValues(simpledataMap);
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e,
                    "Error getAllTaskItem(String taskKeyCode,String servType)");
            if (mexception != null) {
                throw mexception;
            }
        }
        return list;
    }

    public List<TaskItem> getAllTaskItem(Iterable<Long> taskIds, Iterable<String> serviceCodes,
            Iterable<String> testKeyCodes) throws MException {
        try {
            final QueryBuilder<TaskItem, Long> builder = this.dao.queryBuilder();
            final Where<TaskItem, Long> where = builder.where();
            where.in("TASK_ID", taskIds).and().in("SERVTYPE", serviceCodes).and().in("TESTKEYCODE", testKeyCodes);
            return builder.query();
        } catch (SQLException e) {
            MException mexception = ExceptionHandler
                    .handle(e,
                            "Error findTaskItem(Iterable<Long> taskIds, Iterable<String> serviceCodes, Iterable<String> testKeyCodes)");
            if (mexception != null) {
                throw mexception;
            }
        }
        return null;
    }
}
