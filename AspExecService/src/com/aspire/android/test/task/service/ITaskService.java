/**

 * @(#) ATaskService.java Created on 2012-5-21

 *

 * Copyright (c) 2012 Aspire. All Rights Reserved

 */
package com.aspire.android.test.task.service;

import java.util.HashMap;
import java.util.List;

import com.aspire.android.common.exception.MException;
import com.aspire.android.test.task.entity.IndexListing;
import com.aspire.android.test.task.entity.QueryResults;
import com.aspire.android.test.task.entity.Task;
import com.aspire.android.test.task.entity.TaskBatch;
import com.aspire.android.test.task.entity.TaskBathItem;
import com.aspire.android.test.task.entity.TaskItem;
import com.aspire.android.test.task.entity.TaskItemBatch;
import com.aspire.android.test.task.entity.TaskTaskItem;
import com.aspire.android.test.task.entity.Upload;
import com.aspire.mgt.ats.tm.sync.task.entity.TaskInfo;

/**
 * 
 * The class <code>PublicService</code>
 * 
 * 
 * 
 * @author gouanming
 * 
 * @version 1.0
 */
public interface ITaskService {

    /**
     * 更新任务，同步任务数据时调用
     * 
     * @param taskInfo
     */
    public Task mergeTaskOnSync(String deviceType, TaskInfo taskInfo, HashMap<String, Object> map) throws MException;

    /**
     * 得到所有的任务 Task
     * @return
     * @throws MException
     */
    public List<Task> getAllTask() throws MException;

    /**
     * 得到所有的任务 Task,mObject id 不在这之内的所有任务
     * @param mObject id 
     * @return
     * @throws MException
     */
    public List<Task> getNotTask(Object[] id) throws MException;

    /**
     *  根据id找到相对应的任务 Task
     * @param id
     * @return
     * @throws MException
     */
    public Task getTask(Long id) throws MException;

    /**
     * 更新任务 Task
     * @param mTask
     * @return
     * @throws MException
     */
    public Task updataTask(Task mTask) throws MException;

    /**
     * 根据任务名 Task
     * @param taskName 任务名
     * @return
     * @throws MException
     */
    public List<Task> findTask(String taskName) throws MException;

    /**
     * 根据任务ID找到相对应的TaskItem
     * @param taskId 任务id
     * @return
     * @throws MException
     */
    public List<TaskItem> getAllTaskItem(long taskId) throws MException;

    /**
     *  根据id找到相对应的TaskItem
     * @param id
     * @return
     * @throws MException
     */
    public TaskItem getTaskItem(long id) throws MException;

    /**
     *查找所有的 TaskItem
     * @return
     * @throws MException
     */
    public List<TaskItem> getAllTaskItem() throws MException;

    /**
     * 根据指标编码与 业务类型查相对应的 TaskItem
     * @param taskKeyCode  指标编码
     * @param servType  业务类型
     * @return
     * @throws MException
     */
    public List<TaskItem> getAllTaskItem(String taskKeyCode, String servType) throws MException;

    /**
     * 根据任务ID 查找TaskBatch
     * @param taskId 任务ID
     * @return
     * @throws MException
     */
    public TaskBatch getTaskBatch(long taskId) throws MException;

    /**
     * 得到所有的TaskBatch
     * @return
     * @throws MException
     */
    public List<TaskBatch> getAllTaskBatch() throws MException;

    /**
     * 更新taskBatch
     * @param taskBatch
     * @throws MException
     */
    public void updateTaskBatch(TaskBatch taskBatch) throws MException;

    /**
     * 执行任务
     */
    public void motionTask() throws MException;

    /**
     * 根据ID与重复类型找到最大的id号
     * @param taskId
     * @param iterationType 重复类型
     * @return
     * @throws MException
     */
    public Long findMAXTaskBatchID(long taskId, String iterationType) throws MException;

    /**
     * 根据ID与重复类型得到TaskBatch相应的总记录数
     * @param taskId
     * @param iterationType 重复类型
     * @return
     * @throws MException
     */
    public Long findMAXTaskBatchIterationTypeID(long taskId, String iterationType) throws MException;

    /**
     * 根据ID找到TaskBatch
     * @param id
     * @return
     * @throws MException
     */
    public TaskBatch findTaskBatch(long id) throws MException;

    /**
     * 根据ID找到TaskItemBatch
     * @param id
     * @return
     * @throws MException
     */
    public TaskItemBatch findTaskItemBatch(long id) throws MException;

    /**
     *添加 TaskItemBatch
     * @param taskItemBatch
     * @throws MException
     */
    public void addTaskItemBatch(TaskItemBatch taskItemBatch) throws MException;

    /**
     * 根据status查找TaskBatch
     * @param status 
     * @return
     * @throws MException
     */
    public List<TaskBatch> findAllTaskBatch(int status) throws MException;
    
    /**
     * 根据status查找TaskBatch
     * @param status 
     * @return
     * @throws MException
     */
    public List<TaskBatch> findAllTaskBatch(String status) throws MException;

    /**
     * 根据status查找Task
     * @param status 
     * @return
     * @throws MException
     */
    public List<Task> getTaskForStatus(int status) throws MException;

    /**
     * 根据taskItemid,taskBatchId查找TaskItemBatch
     * @param taskItemid
     * @param taskBatchId
     * @return
     * @throws MException
     */
    public List<TaskItemBatch> findListTaskItemBatch(long taskItemid, long taskBatchId) throws MException;

    /**
     * 根据taskItemid,upStatus查找TaskItemBatch
     * @param taskItemid
     * @param upStatus 上传状态
     * @return
     * @throws MException
     */
    public List<TaskItemBatch> findListTaskItemBatch(long taskItemid, String upStatus) throws MException;

    /**
     * 更新TaskItemBatch
     * @param mTaskItemBatch
     * @return
     * @throws MException
     */
    public TaskItemBatch updateTaskItemBatch(TaskItemBatch mTaskItemBatch) throws MException;

    /**
     * 通过id更新TaskItemBatch
     * @param id batch的id
     * @param columns 需要更新的字段
     * @param values 需要更新的字段对应的值
     * @throws MException
     */
    public void updateTaskItemBatchById(long id, String[] columns, String[] values) throws MException;

    /**
     * 根据taskItemid,upStatus查找TaskItemBatch
     * @param taskItemId
     * @param upStatus 上传状态
     * @return
     * @throws MException
     */
    public List<TaskItemBatch> findTestkItemBatchUpStatus(long taskItemId, String upStatus) throws MException;

    /**
     * 添加Upload
     * @param mUpload 
     * @return
     * @throws MException
     */
    public Upload addUpload(Upload mUpload) throws MException;

    /**
     * 根据upstatus查找Upload
     * @param upstatus 上传状态
     * @return
     * @throws MException
     */
    public List<Upload> listUploads(String upstatus) throws MException;

    /**
     * 根据upstatus, resp查找Upload
     * @param upstatus 上传状态
     * @param resp
     * @return
     * @throws MException
     */
    public List<Upload> listUploads(String upstatus, String resp) throws MException;

    /**
     * 更新Upload
     * @param mUpload
     * @return
     * @throws MException
     */
    public Upload updateUpload(Upload mUpload) throws MException;

  
    public List<Task> allOrderbyTask() throws MException;

    public List<TaskBatch> allOrderbyTaskBatch(long TaskId) throws MException;

    public List<TaskItemBatch> allOrderbyTaskItemBatch(long TaskId, long TaskBatchId) throws MException;

    public List<TaskBathItem> findTaskBathItem(long TaskId, long TaskBatchId) throws MException;

    /**
     * 任务重跑
     * 
     * @param id
     * @throws MException
     */
    public void taskReiteration(long taskItemBatchId) throws MException;

    /**
     * 案例重跑
     * @throws MException
     */
    public void caseReiteration() throws MException;

    /**
     * 删除taskItemBatch
     * 
     * @param taskItemBatchId 要删除的ID
     * @throws MException
     */
    public void deleteTaskCase(long taskItemBatchId) throws MException;

    /**
     * 删除taskItemBatch
     * 
     * @param taskItemBatchIds  要删除信息的ID集合
     * @throws MException
     */
    public int deleteTaskCase(List<Long> taskItemBatchIds) throws MException;

    public IndexListing getIndexListing(long taskItemId) throws MException;

//    /**
//     * 结果查询
//     * 
//     * @param taskName 任务名称
//     * @param testKeyName 指标名称
//     * @param status 拨测结果
//     *               0：失败 /1:成功/2：全部
//     * @param upstatus 处理状态
//     *           0:待上报/1：已上报/2：全部
//     * @return
//     * @throws MException
//     */
//    public List<QueryResults> getQueryResults(String taskName, String testKeyName, String status, String upstatus)
//            throws MException;

    /**
     * 结果查询
     * 
     * @param taskName 任务名称
     * @param testKeyName 指标名称
     * @param status 拨测结果
     *               0：失败 /1:成功/2：全部
     * @param upstatus 处理状态
     *           0:待上报/1：已上报/2：全部
     * @param errCode 错误代码，此状态只在status为0的时候有用，如不需要，则为null
     * @param startTime 开始时间范围，为null表示不加时间条件
     * @param endTime 结束时间范围，为null表示不加时间条件
     * @param offset 搜索的偏移量,-1：不偏移
     * @param num 搜索的具体条数-1：全部搜索
     * @param queryResults 需要返回的具体列表
     * @return 返回满足条件的总条数
     * @throws MException
     */
    public int getQueryResults(String taskName, String testKeyName, String status, String upstatus, String errCode,String startTime, String endTime, long offset, long num, List<QueryResults> queryResults)
            throws MException;
    /**
     * 确认结果查询    
     * @param status  11-全部、0-待反馈、1-成功、2失败
     * @return
     * @throws MException
     */
    public List<Upload> getUpload(int status)throws MException;
    
    /**
    * 判断测试结果是否上传
    * 
    * @throws MException
    */
   public boolean  judgeUploadResult() throws MException ;
   /**
    * 得到任务与任务的所有指标
    * @return
    * @throws MException
    */
   public TaskTaskItem getTaskTaskItem(long taskId) throws MException ;

}
