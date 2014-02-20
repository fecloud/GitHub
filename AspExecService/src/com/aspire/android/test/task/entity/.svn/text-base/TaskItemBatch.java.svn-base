package com.aspire.android.test.task.entity;

import java.util.Date;
import com.aspire.android.common.entity.BaseEntity;
import com.aspire.android.test.Constants;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * The class <code>TaskItemBatch</code>
 * 
 * @author gouanming
 * @version 1.0
 */
@DatabaseTable(tableName = Constants.ATS_TASK_ITEM_BATCH)
public class TaskItemBatch extends BaseEntity {

    @DatabaseField(columnName = "ID", generatedId = true)
    private long id;

    /** 任务ID **/
    @DatabaseField(columnName = "TASK_ID")
    private Long taskId;

    /** 任务执行批次明细ID **/
    @DatabaseField(columnName = "TASK_BATCH_ID")
    private Long taskBatchId;

    /** 任务明细ID **/
    @DatabaseField(columnName = "TASK_ITEM_ID")
    private Long taskItemId;

    /** 开始时间 **/
    @DatabaseField(columnName = "startTime", dataType = DataType.DATE)
    private Date startTime;

    /** 结束时间 **/
    @DatabaseField(columnName = "endTime", dataType = DataType.DATE)
    private Date endTime;

    /** 执行次数 **/
    @DatabaseField(columnName = "Times")
    private long times;

    /**
     * 状态: 
     */
    @DatabaseField(columnName = "STATUS")
    private String status;
    /**
     * 拨测值
     */
    @DatabaseField(columnName = "TESTVALUE")
    private String testValue;
    /**
     * 状态: -完成上传 
     */
    @DatabaseField(columnName = "UPSTATUS")
    private String upstatus;
    
    @DatabaseField(columnName = "RESULT")
    private String result;
    
    public String getUpstatus() {
        return upstatus;
    }

    public void setUpstatus(String upstatus) {
        this.upstatus = upstatus;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the taskId
     */
    public Long getTaskId() {
        return taskId;
    }

    /**
     * @param taskId
     *            the taskId to set
     */
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    /**
     * @return the taskBatchId
     */
    public Long getTaskBatchId() {
        return taskBatchId;
    }

    /**
     * @param taskBatchId
     *            the taskBatchId to set
     */
    public void setTaskBatchId(Long taskBatchId) {
        this.taskBatchId = taskBatchId;
    }

    /**
     * @return the taskItemId
     */
    public Long getTaskItemId() {
        return taskItemId;
    }

    /**
     * @param taskItemId
     *            the taskItemId to set
     */
    public void setTaskItemId(Long taskItemId) {
        this.taskItemId = taskItemId;
    }

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     *            the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     *            the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    
    /**
     * @return the times
     */
    public long getTimes() {
        return times;
    }

    /**
     * @param times
     *            the times to set
     */
    public void setTimes(long times) {
        this.times = times;
    }

  
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTestValue() {
        return testValue;
    }

    public void setTestValue(String testValue) {
        this.testValue = testValue;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    
}
