/**
 * @(#) ITaskItemBatchDao.java Created on 2012-6-12
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.task.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.aspire.android.common.db.IBaseDao;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.task.entity.TaskItemBatch;

/**
 * The class <code>ITaskItemBatchDao</code>
 * 
 * @author gouanming
 * @version 1.0
 */
public interface ITaskItemBatchDao extends IBaseDao<TaskItemBatch, Long> {

    public List<TaskItemBatch> findTaskItemBatchs(Long TaskId) throws MException;

    public String findMAXTaskItenBatchID() throws MException;

    public long findMAXTaskItenBatchID(long TaskId) throws MException;
    
    /**
     * 通过ID降序排序，搜索最前limit条
     * @param task
     * @param taskItemid
     * @param limit
     * @return
     * @throws MException
     */
    public List<TaskItemBatch> findOrderById(long task,long taskItemid,long limit)throws MException ;
    public long findAllMAXTaskTaskItem(long task,long taskItemid)throws MException;
    public int countTaskTaskItem(long task,long taskItemid,long id)throws MException;
    public int countTaskTaskItem(long task,long taskItemid)throws MException;
    
    // @Deprecated
    // public long findMAXTaskTaskItem(long task,long taskItemid)throws
    // MException;
    // @Deprecated
    // public List<TaskItemBatch> findAllTaskItemBatch(long task,long
    // taskItemid,long id) throws MException;
    // @Deprecated
    // public List<TaskItemBatch> findAllTaskItemBatch(long task,long
    // taskItemid) throws MException;
    
    public List<TaskItemBatch> findAllTaskItemBatch(Long taskBatchID,String status) throws MException;
    public List<TaskItemBatch> findTaskItemBatch(Long taskBatchID) throws MException;
    public List<TaskItemBatch> findListTaskItemBatch(long taskItemid, long taskBatchId) throws MException;
    public List<TaskItemBatch> findListTaskItemBatch(long taskItemid,String upstatus, String status) throws MException;
    /**
     * 搜索满足条件的运行结果集合
     * @param taskItemids 任务详情集合
     * @param status 运行状态
     * @param upstatus 上传结果状态
     * @param errCode 错误原因代码，在status为0是有效，如不需要则传null
     * @param startTime 开始时间范围，为null表示不加时间条件
     * @param endTime 结束时间范围，为null表示不加时间条件
     * @param offset 搜索的偏移量,-1：不偏移
     * @param num 搜索的具体条数-1：全部搜索
     * @return
     * @throws MException
     */
    public List<TaskItemBatch> findListTaskItemBatch(Iterable<Long> taskItemids, String status,String upstatus, String errCode, Date startTime, Date endTime, long offset, long num) throws MException;
    /**
     * 搜索满足条件的条数
     * @param taskItemids 任务详情集合
     * @param status 运行状态
     * @param upstatus 上传结果状态
     * @param errCode 错误原因代码，在status为0是有效，如不需要则传null
     * @param startTime 开始时间范围
     * @param endTime 结束时间范围
     * @return
     * @throws MException
     */
    public int findListTaskItemBatchCount(Iterable<Long> taskItemids, String status, String upstatus, String errCode,
            Date startTime, Date endTime) throws MException;
    public List<TaskItemBatch> findListTaskItemBatch(long taskItemid, String supStatus) throws MException;

    public List<TaskItemBatch> allOrderbyTaskItemBatch(long TaskId, long TaskBatchId) throws MException;
    
    /**
     * 删除id在集合中的记录
     * @param taskItemBatchIds
     */
    public int deteleByIds(Iterable<Long> taskItemBatchIds) throws MException;

    public List<TaskItemBatch> findTestkItemBatchUpStatus(long taskItemId, String upStatus) throws MException;
    public Long findTestkItemBatchAll(long taskId,long taskItemId) throws MException;
    // taskItem Id 当天执行次数
    public long getExecutionNumber(long taskItemId) throws MException;
    // taskItem Id 共执行次数
    public long getAllExecutionNumber(long taskItemId) throws MException;
    // taskItem Id 成功次数
    public long getSucceedNumber(long taskItemId) throws MException;
    // taskItem Id 失败次数
    public long getLoseNumber(long taskItemId) throws MException;
    /**
     * 通过id更新数据库字段的值
     * @param id
     * @param column
     * @param value
     * @return
     * @throws MException
     */
    public void updateTaskItemBatchById(long id, String[] columns, String[] value) throws MException;

}
