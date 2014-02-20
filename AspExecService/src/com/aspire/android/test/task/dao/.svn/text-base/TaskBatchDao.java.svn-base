/**
 * @(#) TaskBatchDaoImpl.java Created on 2012-5-22
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.task.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import android.util.Log;

import com.aspire.android.common.db.BaseDao;
import com.aspire.android.common.db.DatabaseHelper;
import com.aspire.android.common.db.DatabaseManager;
import com.aspire.android.common.exception.ExceptionHandler;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.Constants;
import com.aspire.android.test.task.entity.TaskBatch;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

/**
 * The class <code>TaskBatchDaoImpl</code>
 * 
 * @author gouanming
 * @version 1.0
 */
@Singleton
public class TaskBatchDao extends BaseDao<TaskBatch, Long> implements ITaskBatchDao {
    /**
     * Constructor
     * 
     * @param databaseHelper
     * @throws MException
     */
    public TaskBatchDao() throws MException {
        super(DatabaseManager.getDBHelper(), Constants.ATS_TASK_BATCH, TaskBatch.class);
    }
    
    /**
     * Constructor
     * 
     * @param databaseHelper
     * @throws MException
     */
    @Inject
    public TaskBatchDao(DatabaseHelper databaseHelper) throws MException {
        super(databaseHelper, Constants.ATS_TASK_BATCH, TaskBatch.class);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.dao.ITaskBatchDao#getNotOverTaskbatchs()
     */
    public List<TaskBatch> getNotOverTaskbatchs() throws MException {
        // StringBuilder sb = new StringBuilder();
        // sb.append(" from ").append(TaskBatch.class.getSimpleName())
        // .append(" a ").append(" where ").append(" a.endTime is null");
        // Query query = entityMgr.createQuery(sb.toString());

        HashMap<String, Object> simpledataMap = new HashMap<String, Object>();
        simpledataMap.put("endTime", " is null");
        try {
            return this.dao.queryForFieldValues(simpledataMap);
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error getNotOverTaskbatchs");
            if (mexception != null) {
                throw mexception;
            }
        }
        return null;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.dao.ITaskBatchDao#findTaskBatch(long)
     */
    public TaskBatch findTaskBatch(long taskId) throws MException {

        HashMap<String, Object> simpledataMap = new HashMap<String, Object>();
        simpledataMap.put("TASK_ID", taskId);
        List<TaskBatch> listTaskBatch = null;
        try {
            listTaskBatch = this.dao.queryForFieldValues(simpledataMap);
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error findTaskBatch");
            if (mexception != null) {
                throw mexception;
            }
        }
        for (TaskBatch taskBatch : listTaskBatch) {
            return taskBatch;
        }
        return null;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.dao.ITaskBatchDao#findTaskBatchs(long)
     */
    public List<TaskBatch> findTaskBatchs(long taskId) throws MException {
        HashMap<String, Object> simpledataMap = new HashMap<String, Object>();
        simpledataMap.put("TASK_ID", taskId);
        List<TaskBatch> listTaskBatch = null;
        try {
            listTaskBatch = this.dao.queryForFieldValues(simpledataMap);
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error findTaskBatchs(long taskId)");
            if (mexception != null) {
                throw mexception;
            }
        }
        return listTaskBatch;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.dao.ITaskBatchDao#findAllTaskBatch(java.lang.String, java.lang.String[])
     */
    public List<TaskBatch> findAllTaskBatch(String sql, String... arguments) throws MException {
        try {
            GenericRawResults<String[]> generic = this.dao.queryRaw(sql, arguments);
            List<String[]> allList = generic.getResults();
            for (String[] strings : allList) {
                for (int i = 0; i < strings.length; i++) {
                    Log.i(getClass().getSimpleName(), "-:-_" + strings[i]);
                }
            }
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e,
                    "Error findAllTaskBatch(String sql, String... arguments)");
            if (mexception != null) {
                throw mexception;
            }
        }
        return null;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.dao.ITaskBatchDao#findMAXID()
     */
    public Long findMAXID() throws MException {
        try {
            String sql = "select max(id) from ats_task_batch";
            GenericRawResults<String[]> generic = this.dao.queryRaw(sql, new String[] {});
            List<String[]> allList = generic.getResults();
            for (String[] strings : allList) {
                for (int i = 0; i < strings.length;) {
                    return Long.parseLong(strings[i]);
                }
            }
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error findMAXID() ");
            if (mexception != null) {
                throw mexception;
            }
        }
        return null;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.dao.ITaskBatchDao#findMAXTaskBatchID(long)
     */
    public Long findMAXTaskBatchID(long taskId, String iterationType) throws MException {
        try {
            String sql = "select max(ID) from ats_task_batch where TASK_ID=? and iterationType = ?";
            GenericRawResults<String[]> generic = this.dao.queryRaw(sql, new String[] { Long.toString(taskId),
                    iterationType });
            List<String[]> allList = generic.getResults();
            for (String[] strings : allList) {
                for (int i = 0; i < strings.length;) {
                    String str = null;
                    if (strings[i] == null) {
                        str = "0";
                    } else {
                        str = strings[i];
                    }
                    return Long.parseLong(str);
                }
            }
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error findMAXTaskBatchID(long taskId) ");
            if (mexception != null) {
                throw mexception;
            }
        }
        return null;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.dao.ITaskBatchDao#findMAXTaskBatchIterationTypeID(long, java.lang.String)
     */
    public Long findMAXTaskBatchIterationTypeID(long taskId, String iterationType) throws MException {
        try {
            String sql = "select count(*) from ats_task_batch where TASK_ID = ? and iterationType = ?";
            GenericRawResults<String[]> generic = this.dao.queryRaw(sql, new String[] { Long.toString(taskId),
                    iterationType });
            List<String[]> allList = generic.getResults();
            for (String[] strings : allList) {
                for (int i = 0; i < strings.length;) {
                    String str = null;
                    if (strings[i] == null) {
                        str = "0";
                    } else {
                        str = strings[i];
                    }
                    return Long.parseLong(str);
                }
            }
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error findMAXTaskBatchID(long taskId) ");
            if (mexception != null) {
                throw mexception;
            }
        }
        return null;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.dao.ITaskBatchDao#getOrderbyTaskbatchs()
     */
    public List<TaskBatch> getOrderbyTaskbatchs() throws MException {
        try {
            return this.dao.queryBuilder().orderBy("ID", false).query();
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error getOrderbyTaskbatchs ");
            if (mexception != null) {
                throw mexception;
            }
        }

        return null;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.dao.ITaskBatchDao#findAllTaskBatch(int)
     */
    public List<TaskBatch> findAllTaskBatch(int status) throws MException {
        HashMap<String, Object> simpledataMap = new HashMap<String, Object>();
        simpledataMap.put("STATUS", status);
        List<TaskBatch> listTaskBatch = null;
        try {
            listTaskBatch = this.dao.queryForFieldValues(simpledataMap);
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error findTaskBatchs(long taskId)");
            if (mexception != null) {
                throw mexception;
            }
        }
        return listTaskBatch;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.dao.ITaskBatchDao#findListTaskBatch(java.lang.String)
     */
    public List<TaskBatch> findListTaskBatch(String upstatus) throws MException {
        HashMap<String, Object> simpledataMap = new HashMap<String, Object>();
        simpledataMap.put("UPSTATUS", upstatus);
        List<TaskBatch> listTaskBatch = null;
        try {
            listTaskBatch = this.dao.queryForFieldValues(simpledataMap);
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error findTaskBatchs(long taskId)");
            if (mexception != null) {
                throw mexception;
            }
        }
        return listTaskBatch;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.dao.ITaskBatchDao#allOrderbyTaskBatch(long)
     */
    public List<TaskBatch> allOrderbyTaskBatch(long TaskId) throws MException {
        try {
            final QueryBuilder<TaskBatch, Long> builder = this.dao.queryBuilder();
            final Where<TaskBatch, Long> where = builder.where();
            where.eq("TASK_ID", TaskId);
            return builder.orderBy("ID", false).query();
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error allOrderbyTaskBatch ");
            if (mexception != null) {
                throw mexception;
            }
        }
        return null;
    }
    public  List<TaskBatch>  findTaskBatchEndTimeIsNull()throws MException{
        try {
            final QueryBuilder<TaskBatch, Long> builder = this.dao.queryBuilder();
            final Where<TaskBatch, Long> where = builder.where();
            where.isNull("endTime");
            return builder.query();
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error allOrderbyTaskBatch ");
            if (mexception != null) {
                throw mexception;
            }
        }
        return null;
    }
}
