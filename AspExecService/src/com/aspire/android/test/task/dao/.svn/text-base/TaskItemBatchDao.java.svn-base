/**
 * @(#) TaskItemBatchDao.java Created on 2012-6-12
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.task.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.util.Log;

import com.aspire.android.common.db.BaseDao;
import com.aspire.android.common.db.DatabaseHelper;
import com.aspire.android.common.db.DatabaseManager;
import com.aspire.android.common.exception.ExceptionHandler;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.Constants;
import com.aspire.android.test.task.entity.TaskItemBatch;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;

/**
 * The class <code>TaskItemBatchDao</code>
 * 
 * @author gouanming
 * @version 1.0
 */
@Singleton
public class TaskItemBatchDao extends BaseDao<TaskItemBatch, Long> implements ITaskItemBatchDao {
    /**
     * Constructor
     * 
     * @param databaseHelper
     * @throws MException
     */
    public TaskItemBatchDao() throws MException {
        super(DatabaseManager.getDBHelper(), Constants.ATS_TASK_ITEM_BATCH, TaskItemBatch.class);
    }

    /**
     * 
     * Constructor
     * 
     * @param databaseHelper
     * @throws MException
     */
    @Inject
    public TaskItemBatchDao(DatabaseHelper databaseHelper) throws MException {
        super(databaseHelper, Constants.ATS_TASK_ITEM_BATCH, TaskItemBatch.class);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.dao.ITaskItemBatchDao#findTaskItemBatchs(java.lang.Long)
     */
    public List<TaskItemBatch> findTaskItemBatchs(Long TaskId) throws MException {
        HashMap<String, Object> simpledataMap = new HashMap<String, Object>();
        simpledataMap.put("TASK_ID", TaskId);
        List<TaskItemBatch> list = null;
        try {
            list = this.dao.queryForFieldValues(simpledataMap);
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error findTaskItemBatchs(Long TaskId)");
            if (mexception != null) {
                throw mexception;
            }
        }
        return list;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.dao.ITaskItemBatchDao#findMAXTaskItenBatchID()
     */
    public String findMAXTaskItenBatchID() throws MException {
        try {
            String sql = "select max(id) from ats_task_item_batch";
            GenericRawResults<String[]> generic = this.dao.queryRaw(sql, new String[] {});
            List<String[]> allList = generic.getResults();
            for (String[] strings : allList) {
                for (int i = 0; i < strings.length;) {
                    return strings[i];
                }
            }
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error findMAXTaskItenBatchID() ");
            if (mexception != null) {
                throw mexception;
            }
        }
        return null;
    }

    public long findMAXTaskItenBatchID(long TaskId) throws MException {

        try {
            String sql = "select max(id) from ats_task_item_batch where TASK_ID = ?";
            GenericRawResults<String[]> generic = this.dao.queryRaw(sql, new String[] { String.valueOf(TaskId) });
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
            MException mexception = ExceptionHandler.handle(e, "Error findMAXTaskItenBatchID(long TaskId)");
            if (mexception != null) {
                throw mexception;
            }
        }
        return 0;
    }

    /**
     * (non-Javadoc)
     * 
     * @throws SQLException
     * 
     * @see com.aspire.android.test.task.dao.ITaskItemBatchDao#findOrderById(long, long, int)
     */
    public List<TaskItemBatch> findOrderById(long task, long taskItemid, long limit) throws MException {
        try {
            final QueryBuilder<TaskItemBatch, Long> builder = this.dao.queryBuilder();
            builder.orderBy("ID", false   );
            builder.limit(limit);
            final Where<TaskItemBatch, Long> where = builder.where();
            where.eq("TASK_ID", task).and().eq("TASK_ITEM_ID", taskItemid);
            return builder.query();
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "findOrderById(long task, long taskItemid, long limit)");
            if (mexception != null) {
                throw mexception;
            }
        }
        return null;
    }

    public long findAllMAXTaskTaskItem(long task, long taskItemid) throws MException {
        try {
            String sql = "select max(id) from ats_task_item_batch where TASK_ID = ? and  TASK_ITEM_ID =?";
            GenericRawResults<String[]> generic = this.dao.queryRaw(sql,
                    new String[] { String.valueOf(task), String.valueOf(taskItemid) });
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
            MException mexception = ExceptionHandler.handle(e, "findMAXTaskTaskItem(long task,long taskItemid)");
            if (mexception != null) {
                throw mexception;
            }
        }
        return 0;
    }

    public int countTaskTaskItem(long task, long taskItemid, long id) throws MException {
        try {
            String sql = "select count(*) from ats_task_item_batch where TASK_ID = ? and  TASK_ITEM_ID =? and  ID >  ?";
            GenericRawResults<String[]> generic = this.dao.queryRaw(sql,
                    new String[] { Long.toString(task), String.valueOf(taskItemid), String.valueOf(id) });
            List<String[]> allList = generic.getResults();
            for (String[] strings : allList) {
                for (int i = 0; i < strings.length;) {
                    String str = null;
                    if (strings[i] == null) {
                        str = "0";
                    } else {
                        str = strings[i];
                    }
                    return Integer.valueOf(str);
                }
            }
        } catch (SQLException e) {
            MException mexception = ExceptionHandler
                    .handle(e, " countTaskTaskItem(long task,long taskItemid,long id) ");
            if (mexception != null) {
                throw mexception;
            }
        }
        return 0;
    }

    public int countTaskTaskItem(long task, long taskItemid) throws MException {
        try {
            String sql = "select count(*) from ats_task_item_batch where TASK_ID = ? and  TASK_ITEM_ID =? and   STATUS = 0";
            GenericRawResults<String[]> generic = this.dao.queryRaw(sql,
                    new String[] { Long.toString(task), String.valueOf(taskItemid) });
            List<String[]> allList = generic.getResults();
            for (String[] strings : allList) {
                for (int i = 0; i < strings.length;) {
                    String str = null;
                    if (strings[i] == null) {
                        str = "0";
                    } else {
                        str = strings[i];
                    }
                    return Integer.valueOf(str);
                }
            }
        } catch (SQLException e) {
            MException mexception = ExceptionHandler
                    .handle(e, " countTaskTaskItem(long task,long taskItemid,long id) ");
            if (mexception != null) {
                throw mexception;
            }
        }
        return 0;
    }


    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.dao.ITaskItemBatchDao#findTaskItemBatch(java.lang.Long)
     */
    public List<TaskItemBatch> findAllTaskItemBatch(Long taskBatchID, String status) throws MException {
        HashMap<String, Object> simpledataMap = new HashMap<String, Object>();
        simpledataMap.put("TASK_BATCH_ID", taskBatchID);
        if (status != null) {
            simpledataMap.put("STATUS", status);
        }
        List<TaskItemBatch> list = null;
        try {
            list = this.dao.queryForFieldValues(simpledataMap);
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error findTaskItemBatchs(Long TaskId)");
            if (mexception != null) {
                throw mexception;
            }
        }
        return list;
    }

    /**
     * 
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.dao.ITaskItemBatchDao#findTaskItemBatch(java.lang.Long)
     */
    public List<TaskItemBatch> findTaskItemBatch(Long taskBatchID) throws MException {
        HashMap<String, Object> simpledataMap = new HashMap<String, Object>();
        simpledataMap.put("TASK_BATCH_ID", taskBatchID);
        List<TaskItemBatch> list = null;
        try {
            list = this.dao.queryForFieldValues(simpledataMap);
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error findTaskItemBatchs(Long TaskId)");
            if (mexception != null) {
                throw mexception;
            }
        }
        return list;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.dao.ITaskItemBatchDao#findListTaskItemBatch(long)
     */
    public List<TaskItemBatch> findListTaskItemBatch(long taskItemid, long taskBatchId) throws MException {
        HashMap<String, Object> simpledataMap = new HashMap<String, Object>();
        simpledataMap.put("TASK_ITEM_ID", taskItemid);
        simpledataMap.put("TASK_BATCH_ID", taskBatchId);
        List<TaskItemBatch> list = null;
        try {
            list = this.dao.queryForFieldValues(simpledataMap);
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error findListTaskItemBatch(Long taskItemid)");
            if (mexception != null) {
                throw mexception;
            }
        }
        return list;
    }

    public List<TaskItemBatch> findListTaskItemBatch(long taskItemid, String status, String upstatus) throws MException {
        try {
            final QueryBuilder<TaskItemBatch, Long> builder = this.dao.queryBuilder();
            final Where<TaskItemBatch, Long> where = builder.where();
            if (upstatus.equals("2") && status.equals("2")) {
                where.eq("TASK_ITEM_ID", taskItemid);
                return builder.query();
            } else if (status.equals("2")) {
                where.eq("TASK_ITEM_ID", taskItemid).and().eq("UPSTATUS", upstatus);
                return builder.query();
            } else if (upstatus.equals("2")) {
                if (status.equals("0") || status.equals("1")) {
                    where.eq("TASK_ITEM_ID", taskItemid).and().eq("STATUS", status);
                    return builder.query();
                } else {
                    where.eq("TASK_ITEM_ID", taskItemid).and().eq("RESULT", status);
                    return builder.query();
                }

            } else {
                if (status.equals("0") || status.equals("1")) {
                    where.eq("TASK_ITEM_ID", taskItemid).and().eq("UPSTATUS", upstatus).and().eq("STATUS", status);
                    return builder.query();
                } else {
                    where.eq("TASK_ITEM_ID", taskItemid).and().eq("UPSTATUS", upstatus).and().eq("RESULT", status);
                    return builder.query();
                }
            }
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error findListTaskItemBatch ");
            if (mexception != null) {
                throw mexception;
            }
        }
        return null;
    }

    public List<TaskItemBatch> findListTaskItemBatch(Iterable<Long> taskItemids, String status, String upstatus,
            String errCode, Date startTime, Date endTime, long offset, long num) throws MException {
        try {
            final QueryBuilder<TaskItemBatch, Long> builder = this.dao.queryBuilder();
            builder.orderBy("ID", false);
            if (num != -1) {
                builder.limit(num);
            }
            if (offset != -1) {
                builder.offset(offset);
            }
            final Where<TaskItemBatch, Long> where = builder.where();
            if (startTime != null && endTime != null) {
                where.between("startTime", startTime, endTime).and();
            }
            if (upstatus.equals("2") && status.equals("2")) {
                where.in("TASK_ITEM_ID", taskItemids);
            } else if (status.equals("2")) {
                where.in("TASK_ITEM_ID", taskItemids).and().eq("UPSTATUS", upstatus);
            } else if (upstatus.equals("2")) {
                if (status.equals("0")) {
                    where.in("TASK_ITEM_ID", taskItemids).and().eq("STATUS", status);
                    if (errCode != null && !errCode.trim().equals("") && !errCode.trim().equals("00")) {
                        where.and().eq("RESULT", errCode);
                    }
                } else if (status.equals("1")) {
                    where.in("TASK_ITEM_ID", taskItemids).and().eq("STATUS", status);
                }

            } else {
                if (status.equals("0")) {
                    where.in("TASK_ITEM_ID", taskItemids).and().eq("UPSTATUS", upstatus).and().eq("STATUS", status);
                    if (errCode != null && !errCode.trim().equals("") && !errCode.trim().equals("00")) {
                        where.and().eq("RESULT", errCode);
                    }
                } else if (status.equals("1")) {
                    where.in("TASK_ITEM_ID", taskItemids).and().eq("UPSTATUS", upstatus).and().eq("STATUS", status);
                }
            }
            return builder.query();
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error findListTaskItemBatch ");
            if (mexception != null) {
                throw mexception;
            }
        }
        return null;
    }

    public int findListTaskItemBatchCount(Iterable<Long> taskItemids, String status, String upstatus, String errCode,
            Date startTime, Date endTime) throws MException {
        try {
            final QueryBuilder<TaskItemBatch, Long> builder = this.dao.queryBuilder();
            builder.orderBy("ID", false);
            builder.selectColumns("ID");
            final Where<TaskItemBatch, Long> where = builder.where();
            if (startTime != null && endTime != null) {
                where.between("startTime", startTime, endTime).and();
            }
            if (upstatus.equals("2") && status.equals("2")) {
                where.in("TASK_ITEM_ID", taskItemids);
            } else if (status.equals("2")) {
                where.in("TASK_ITEM_ID", taskItemids).and().eq("UPSTATUS", upstatus);
            } else if (upstatus.equals("2")) {
                if (status.equals("0")) {
                    where.in("TASK_ITEM_ID", taskItemids).and().eq("STATUS", status);
                    if (errCode != null && !errCode.trim().equals("") && !errCode.trim().equals("00")) {
                        where.and().eq("RESULT", errCode);
                    }
                } else if (status.equals("1")) {
                    where.in("TASK_ITEM_ID", taskItemids).and().eq("STATUS", status);
                }

            } else {
                if (status.equals("0")) {
                    where.in("TASK_ITEM_ID", taskItemids).and().eq("UPSTATUS", upstatus).and().eq("STATUS", status);
                    if (errCode != null && !errCode.trim().equals("") && !errCode.trim().equals("00")) {
                        where.and().eq("RESULT", errCode);
                    }
                } else if (status.equals("1")) {
                    where.in("TASK_ITEM_ID", taskItemids).and().eq("UPSTATUS", upstatus).and().eq("STATUS", status);
                }
            }
            return builder.query().size();
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error findListTaskItemBatch ");
            if (mexception != null) {
                throw mexception;
            }
        }
        return 0;
    }

    public int deteleByIds(Iterable<Long> taskItemBatchIds) throws MException {
        DeleteBuilder<TaskItemBatch, Long> deleteBuilder = dao.deleteBuilder();
        Where<TaskItemBatch, Long> where = deleteBuilder.where();
        try {
            where.in("ID", taskItemBatchIds);
            return deleteBuilder.delete();
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error findListTaskItemBatch ");
            if (mexception != null) {
                throw mexception;
            }
        }
        return 0;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.dao.ITaskItemBatchDao#findListTaskItemBatch(long)
     */
    public List<TaskItemBatch> findListTaskItemBatch(long taskItemid, String upStatus) throws MException {
        HashMap<String, Object> simpledataMap = new HashMap<String, Object>();
        simpledataMap.put("TASK_ITEM_ID", taskItemid);
        simpledataMap.put("UPSTATUS", upStatus);
        List<TaskItemBatch> list = null;
        try {
            list = this.dao.queryForFieldValues(simpledataMap);
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error findListTaskItemBatch(Long taskItemid)");
            if (mexception != null) {
                throw mexception;
            }
        }
        return list;
    }

    public List<TaskItemBatch> allOrderbyTaskItemBatch(long TaskId, long TaskBatchId) throws MException {
        try {
            final QueryBuilder<TaskItemBatch, Long> builder = this.dao.queryBuilder();
            final Where<TaskItemBatch, Long> where = builder.where();
            where.eq("TASK_ID", TaskId).and().eq("TASK_BATCH_ID", TaskBatchId);
            return builder.orderBy("ID", false).query();
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error allOrderbyTaskBatch ");
            if (mexception != null) {
                throw mexception;
            }
        }
        return null;
    }

    public List<TaskItemBatch> findTestkItemBatchUpStatus(long taskItemId, String upStatus) throws MException {
        HashMap<String, Object> simpledataMap = new HashMap<String, Object>();
        simpledataMap.put("TASK_ITEM_ID", taskItemId);
        simpledataMap.put("UPSTATUS", upStatus);
        List<TaskItemBatch> list = null;
        try {
            list = this.dao.queryForFieldValues(simpledataMap);
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e,
                    "Error findTestkItemBatchUpStatus(long taskItemId,String upStatus)");
            if (mexception != null) {
                throw mexception;
            }
        }
        return list;
    }

    public Long findTestkItemBatchAll(long taskId, long taskItemId) throws MException {
        try {
            String sql = "select count(*) from ats_task_item_batch where TASK_ID = ? and TASK_ITEM_ID = ?";
            GenericRawResults<String[]> generic = this.dao.queryRaw(sql,
                    new String[] { Long.toString(taskId), Long.toString(taskItemId) });
            List<String[]> allList = generic.getResults();
            for (String[] strings : allList) {
                for (int i = 0; i < strings.length;) {
                    String str = null;
                    if (strings[i] == null) {
                        str = "0";
                    } else {
                        str = strings[i];
                    }
                    Log.d(getClass().getSimpleName(), "-:-_" + strings[i]);
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

    public long getExecutionNumber(long taskItemId) throws MException {
        try {

            // SELECT * FROM SALARY_HISTORY WHERE date(CHANGE_DATE,'Localtime')=Date('now','Localtime')
            String sql = "select count(*) from ats_task_item_batch where TASK_ITEM_ID = ? and date(startTime) = date('now')";
            GenericRawResults<String[]> generic = this.dao.queryRaw(sql, new String[] { Long.toString(taskItemId) });
            List<String[]> allList = generic.getResults();
            for (String[] strings : allList) {
                for (int i = 0; i < strings.length;) {

                    String str = strings[i];
                    return Long.parseLong(str);
                }
            }
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error getExecutionNumber(long taskItemId)");
            if (mexception != null) {
                throw mexception;
            }
        }
        return 0;

    }

    public long getAllExecutionNumber(long taskItemId) throws MException {

        try {

            // SELECT * FROM SALARY_HISTORY WHERE date(CHANGE_DATE,'Localtime')=Date('now','Localtime')
            String sql = "select count(*) from ats_task_item_batch where TASK_ITEM_ID = ?";
            GenericRawResults<String[]> generic = this.dao.queryRaw(sql, new String[] { Long.toString(taskItemId) });
            List<String[]> allList = generic.getResults();
            for (String[] strings : allList) {
                for (int i = 0; i < strings.length;) {

                    String str = strings[i];
                    return Long.parseLong(str);
                }
            }
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error getAllExecutionNumber(long taskItemId)");
            if (mexception != null) {
                throw mexception;
            }
        }
        return 0;
    }

    public long getSucceedNumber(long taskItemId) throws MException {
        try {

            // SELECT * FROM SALARY_HISTORY WHERE date(CHANGE_DATE,'Localtime')=Date('now','Localtime')
            String sql = "select count(*) from ats_task_item_batch where TASK_ITEM_ID = ? and STATUS='1'";
            GenericRawResults<String[]> generic = this.dao.queryRaw(sql, new String[] { Long.toString(taskItemId) });
            List<String[]> allList = generic.getResults();
            for (String[] strings : allList) {
                for (int i = 0; i < strings.length;) {

                    String str = strings[i];
                    return Long.parseLong(str);
                }
            }
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error getAllExecutionNumber(long taskItemId)");
            if (mexception != null) {
                throw mexception;
            }
        }
        return 0;
    }

    public long getLoseNumber(long taskItemId) throws MException {
        try {

            // SELECT * FROM SALARY_HISTORY WHERE date(CHANGE_DATE,'Localtime')=Date('now','Localtime')
            String sql = "select count(*) from ats_task_item_batch where TASK_ITEM_ID = ? and STATUS='0'";
            GenericRawResults<String[]> generic = this.dao.queryRaw(sql, new String[] { Long.toString(taskItemId) });
            List<String[]> allList = generic.getResults();
            for (String[] strings : allList) {
                for (int i = 0; i < strings.length;) {

                    String str = strings[i];
                    return Long.parseLong(str);
                }
            }
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error getAllExecutionNumber(long taskItemId)");
            if (mexception != null) {
                throw mexception;
            }
        }
        return 0;
    }

    public void updateTaskItemBatchById(long id, String[] columns, String[] values) throws MException {
        final UpdateBuilder<TaskItemBatch, Long> updateBuilder = dao.updateBuilder();
        Where<TaskItemBatch, Long> where = updateBuilder.where();
        try {
            where.eq("ID", id);
            if (columns.length == values.length && columns.length > 0) {
                for (int i = 0; i < columns.length; i++) {
                    updateBuilder.updateColumnValue(columns[i], values[i]);
                }
                updateBuilder.update();
            }
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e,
                    "updateTaskItemBatchById(long id, String[] columns, String[] value)");
            if (mexception != null) {
                throw mexception;
            }
        }
    }

}
