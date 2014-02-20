/**
 * @(#) Task.java Created on 2012-4-26
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.task.entity;

import java.util.Date;

import com.aspire.android.common.entity.BaseEntity;
import com.aspire.android.test.Constants;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * The class <code>Task</code>
 *
 * @author linjunsui
 * @version 1.0
 */
@DatabaseTable(tableName = Constants.TABLE_TASK)
public class Task extends BaseEntity {
    
    /**
     * Tabelname
     */
    public static final String TABLENAME = Constants.TABLE_TASK;
    
    /**
     * Constants
     */
    public static final int PRIORITY_LOW = 2;

    public static final int PRIORITY_MEDIUM = 5;

    public static final int PRIORITY_HIGH = 8;

    public static final Character EXETYPE_BY_TIMES = '2';

    public static final Character EXETYPE_BY_TIME = '1';

    public static final Character ITERATION_TYPE_NONE = '0';

    public static final Character ITERATION_TYPE_BY_DAY = '1';

    public static final Character ITERATION_TYPE_BY_WEEK = '2';

    public static final Character ITERATION_TYPE_BY_MONTH = '3';

    /** 拨测任务ID **/
    @DatabaseField(columnName = "ID", generatedId = true)
    private long id;

    /** 任务编号 **/
    @DatabaseField(columnName = "TASKCODE")
    private String taskCode;

    /** 任务名称 **/
    @DatabaseField(columnName = "TASKNAME")
    private String taskName;

    /** 任务描述 **/
    @DatabaseField(columnName = "TASKDESC")
    private String taskDesc;

    /** 日志级别 **/
    @DatabaseField(columnName = "LogLevel")
    private String logLevel;

    /** 抓包标识 **/
    @DatabaseField(columnName = "NetCapture")
    private String netCapture;

    /** 任务类型 **/
    @DatabaseField(columnName = "TASKTYPE")
    private String taskType;

    /** 优先级 **/
    @DatabaseField(columnName = "PRIORITY")
    private Integer priority;

    /** 计划开始时间 **/
    @DatabaseField(columnName = "STARTDATE", dataType=DataType.DATE)
    private Date startDate;

    /** 计划结束时间 **/
    @DatabaseField(columnName = "ENDDATE", dataType=DataType.DATE)
    private Date endDate;

    /** 执行类型：1：按时执行2：按次执行 **/
    @DatabaseField(columnName = "ExeType")
    private String exeType;

    /** 任务开始执行时间,格式：HH24MISS **/
    @DatabaseField(columnName = "ExeBeginTime")
    private String exeBeginTime;

    /** 任务结束执行时间,格式：HH24MISS **/
    @DatabaseField(columnName = "ExeEndTime")
    private String exeEndTime;

    /** 重复类型：0：不限,1：每天,2：每周,3：每月,执行类型为2时，默认为0 **/
    @DatabaseField(columnName = "IterationType")
    private String iterationType;

    /** 循环次数,执行类型为1时，表示具体每次重复类型必须循环次数,执行类型为2时，表示总次数 **/
    @DatabaseField(columnName = "IterationNum")
    private Integer iterationNum;

    /** 间隔时间，单位分钟，0：没间隔，非0表示具体的间隔分钟数 **/
    @DatabaseField(columnName = "IntervalVal")
    private Integer interval;

    /** 更新时间 **/
    @DatabaseField(columnName = "LASTUPDATEDATE", dataType=DataType.DATE)
    private Date lastUpdateDate;
    /**
     * 状态: 1-有效 /0-无效
     */
    @DatabaseField(columnName = "STATUS")
    private int  status;
    /**
     * 判断结果是否完成: 1-完成 /0-未完成
     */
    @DatabaseField(columnName = "JUDGERESULT")
    private int  judgeResult;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the taskCode
     */
    public String getTaskCode() {
        return taskCode;
    }

    /**
     * @param taskCode the taskCode to set
     */
    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    /**
     * @return the taskName
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * @param taskName the taskName to set
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * @return the taskDesc
     */
    public String getTaskDesc() {
        return taskDesc;
    }

    /**
     * @param taskDesc the taskDesc to set
     */
    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    /**
     * @return the logLevel
     */
    public String getLogLevel() {
        return logLevel;
    }

    /**
     * @param logLevel the logLevel to set
     */
    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    /**
     * @return the netCapture
     */
    public String getNetCapture() {
        return netCapture;
    }

    /**
     * @param netCapture the netCapture to set
     */
    public void setNetCapture(String netCapture) {
        this.netCapture = netCapture;
    }

    /**
     * @return the taskType
     */
    public String getTaskType() {
        return taskType;
    }

    /**
     * @param taskType the taskType to set
     */
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    /**
     * @return the priority
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the exeType
     */
    public String getExeType() {
        return exeType;
    }

    /**
     * @param exeType the exeType to set
     */
    public void setExeType(String exeType) {
        this.exeType = exeType;
    }

    /**
     * @return the exeBeginTime
     */
    public String getExeBeginTime() {
        return exeBeginTime;
    }

    /**
     * @param exeBeginTime the exeBeginTime to set
     */
    public void setExeBeginTime(String exeBeginTime) {
        this.exeBeginTime = exeBeginTime;
    }

    /**
     * @return the exeEndTime
     */
    public String getExeEndTime() {
        return exeEndTime;
    }

    /**
     * @param exeEndTime the exeEndTime to set
     */
    public void setExeEndTime(String exeEndTime) {
        this.exeEndTime = exeEndTime;
    }

    /**
     * @return the iterationType
     */
    public String getIterationType() {
        return iterationType;
    }

    /**
     * @param iterationType the iterationType to set
     */
    public void setIterationType(String iterationType) {
        this.iterationType = iterationType;
    }

    /**
     * @return the iterationNum
     */
    public Integer getIterationNum() {
        return iterationNum;
    }

    /**
     * @param iterationNum the iterationNum to set
     */
    public void setIterationNum(Integer iterationNum) {
        this.iterationNum = iterationNum;
    }

    /**
     * @return the interval
     */
    public Integer getInterval() {
        return interval;
    }

    /**
     * @param interval the interval to set
     */
    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    /**
     * @return the lastUpdateDate
     */
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * @param lastUpdateDate the lastUpdateDate to set
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getJudgeResult() {
        return judgeResult;
    }

    public void setJudgeResult(int judgeResult) {
        this.judgeResult = judgeResult;
    }
    
    
    
}
