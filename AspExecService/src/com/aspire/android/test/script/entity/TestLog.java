package com.aspire.android.test.script.entity;

import java.util.Date;
import com.aspire.android.common.entity.BaseEntity;
import com.aspire.android.test.Constants;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * The class <code>TestLog</code>
 * 
 * @author gouanming
 * @version 1.0
 */
@DatabaseTable(tableName = Constants.ATS_TEST_LOG)
public class TestLog extends BaseEntity {

    @DatabaseField(columnName = "ID", generatedId = true)
    private long id;

    /** 脚本ID **/
    @DatabaseField(columnName = "SCRIPT_ID")
    private Long scriptId;
    
    @DatabaseField(columnName = "CASE_ID")
    private Long caseId;
    
    /** taskItemBatchID **/
    @DatabaseField(columnName = "TESKITENBATCH_ID")
    private Long taskItemBatchId;

    /** 脚本中设备的名字 **/
    @DatabaseField(columnName = "DEVICE_PARAM")
    private String deviceParam;

    /** 设备ID **/
    @DatabaseField(columnName = "DEVICE_ID")
    private Integer deviceId;

    /** 开始时间 **/
    @DatabaseField(columnName = "startTime", dataType = DataType.DATE)
    private Date startTime;

    /** 结束时间 **/
    @DatabaseField(columnName = "endTime", dataType = DataType.DATE)
    private Date endTime;

    /** 日志文件保存路径 **/
    @DatabaseField(columnName = "LogPath")
    private String logPath;

    /** 生成测试结果文件的时间 **/
    @DatabaseField(columnName = "ResultFileName")
    private String resultFileName;

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
     * @return the scriptId
     */
    public Long getScriptId() {
        return scriptId;
    }

    /**
     * @param scriptId
     *            the scriptId to set
     */
    public void setScriptId(Long scriptId) {
        this.scriptId = scriptId;
    }

    /**
     * @return the deviceParam
     */
    public String getDeviceParam() {
        return deviceParam;
    }

    /**
     * @param deviceParam
     *            the deviceParam to set
     */
    public void setDeviceParam(String deviceParam) {
        this.deviceParam = deviceParam;
    }

    /**
     * @return the deviceId
     */
    public Integer getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId
     *            the deviceId to set
     */
    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
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
     * @return the logPath
     */
    public String getLogPath() {
        return logPath;
    }

    /**
     * @param logPath
     *            the logPath to set
     */
    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    /**
     * @return the resultFileName
     */
    public String getResultFileName() {
        return resultFileName;
    }

    /**
     * @param resultFileName
     *            the resultFileName to set
     */
    public void setResultFileName(String resultFileName) {
        this.resultFileName = resultFileName;
    }

    public Long getTaskItemBatchId() {
        return taskItemBatchId;
    }

    public void setTaskItemBatchId(Long taskItemBatchId) {
        this.taskItemBatchId = taskItemBatchId;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }
    
    
}
