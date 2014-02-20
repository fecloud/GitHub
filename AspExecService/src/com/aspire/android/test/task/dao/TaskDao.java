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
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

/**
 * The class <code>TaskDao</code>
 * 
 * @author linjunsui
 * @version 1.0
 */
@Singleton
public class TaskDao extends BaseDao<Task, Long> implements ITaskDao {

    /**
     * Constructor
     * 
     * @param databaseHelper
     * @throws MException
     */
    public TaskDao() throws MException {
        super(DatabaseManager.getDBHelper(), Constants.TABLE_TASK, Task.class);
    }
    
    /**
     * Constructor
     * @param databaseHelper databaseHelper
     * @throws MException 
     */
    @Inject
    public TaskDao(DatabaseHelper databaseHelper) throws MException {
        super(databaseHelper, Constants.TABLE_TASK, Task.class);
    }

    /**
     * 获取包含该机型任务明细的任务
     * 
     * @param devType
     *            设备型号
     * @return 任务
     */
    public List<Task> getTaskByDeviceType(String devType, String imei) throws MException {
        return null;

    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.dao.ITaskDao#getTaskByCode(java.lang.String)
     */
    public Task getTaskByCode(String testTaskCode) throws MException {
        HashMap<String, Object> simpledataMap = new HashMap<String, Object>();
        simpledataMap.put("TASKCODE", testTaskCode);
        List<Task> list = null;
        try {
            list = this.dao.queryForFieldValues(simpledataMap);
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error getTaskByCode(String testTaskCode)");
            if (mexception != null) {
                throw mexception;
            }
        }
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.dao.ITaskDao#allOrderbyTask()
     */
    public List<Task> allOrderbyTask() throws MException {
        try {

//            final QueryBuilder<Task, Long> builder = this.dao.queryBuilder();
            // return builder.orderBy("ID", false).query() ;

            return this.dao.queryBuilder().orderBy("ID", false).query();
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error getOrderbyTaskbatchs ");
            if (mexception != null) {
                throw mexception;
            }
        }
        return null;
    }

    public List<Task> getNotTask(Object[] mObject) throws MException {
        try {
            final QueryBuilder<Task, Long> builder = this.dao.queryBuilder();
            final Where<Task, Long> where = builder.where();
            where.notIn("ID", mObject);
            return builder.query();
            // return this.dao.queryBuilder().orderBy("ID", false).query() ;
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error getOrderbyTaskbatchs ");
            if (mexception != null) {
                throw mexception;
            }
        }
        return null;
    }

    public List<Task> getTaskStatus(int mun) throws MException {

        HashMap<String, Object> simpledataMap = new HashMap<String, Object>();
        simpledataMap.put("STATUS", mun);
        List<Task> list = null;
        try {
            list = this.dao.queryForFieldValues(simpledataMap);
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error getTaskStatus(int mun)");
            if (mexception != null) {
                throw mexception;
            }
        }
        return list;
    }
    
    
    public List<Task> findTask(String taskNam) throws MException{
//        HashMap<String, Object> simpledataMap = new HashMap<String, Object>();
//        simpledataMap.put("TASKNAME", taskNam);
//        List<Task> list = null;
//        try {
//            list = this.dao.queryForFieldValues(simpledataMap);
//        } catch (SQLException e) {
//            MException mexception = ExceptionHandler.handle(e, "Error getTaskStatus(int mun)");
//            if (mexception != null) {
//                throw mexception;
//            }
//        }
//        return list;
        
        try {
            final QueryBuilder<Task, Long> builder = this.dao.queryBuilder();
            final Where<Task, Long> where = builder.where();
            where.like("TASKNAME", "%"+taskNam.trim()+"%");
            return builder.query();
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error findTask(String taskNam)");
            if (mexception != null) {
                throw mexception;
            }
        }
        
        return null;
    }

}
