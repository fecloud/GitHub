package com.aspire.android.test.task.entity;

import java.util.Date;
import com.aspire.android.common.entity.BaseEntity;
import com.aspire.android.test.Constants;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * The class <code>TaskBatch</code>
 * 
 * @author gouanming
 * @version 1.0
 */
@DatabaseTable(tableName = Constants.ATS_TASK_BATCH)
public class TaskBatch extends BaseEntity {

    @DatabaseField(columnName = "ID", generatedId = true)
    private long id;

    /** 任务ID **/
    @DatabaseField(columnName = "TASK_ID")
    private Long taskId;
    
    /** 任务编号 **/
    @DatabaseField(columnName = "TASKCODE")
    private String taskCode;
    
    /** 重复类型**/
    @DatabaseField(columnName = "iterationType")
    private String iterationType;

    /** 开始时间 **/
    @DatabaseField(columnName = "startTime", dataType = DataType.DATE)
    private Date startTime;

    /** 结束时间 **/
    @DatabaseField(columnName = "endTime", dataType = DataType.DATE)
    private Date endTime;
    
    /**
     * 状态: 0-正在运行/1-运行完成 
     * 
     */
    @DatabaseField(columnName = "STATUS")
    private int status;
    
    /**
     * 状态: 2-完成上传 
     */
    @DatabaseField(columnName = "UPSTATUS")
    private String upstatus;

    
    
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

    public String getIterationType() {
        return iterationType;
    }

    public void setIterationType(String iterationType) {
        this.iterationType = iterationType;
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
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }
    
    
    
}
