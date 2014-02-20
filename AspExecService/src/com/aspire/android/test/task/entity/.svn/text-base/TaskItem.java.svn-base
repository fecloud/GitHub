package com.aspire.android.test.task.entity;

import com.aspire.android.common.entity.BaseEntity;
import com.aspire.android.test.Constants;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * The class <code>TaskItem</code>
 * 
 * @author gouanming
 * @version 1.0
 */
@DatabaseTable(tableName = Constants.ATS_TASK_ITEM)
public class TaskItem extends BaseEntity {

    /** 拨测明细ID **/
    @DatabaseField(columnName = "ID", generatedId = true)
    private long id;

    /** 任务ID **/
    @DatabaseField(columnName = "TASK_ID")
    private long taskId;

    /** 设备型号 **/
    @DatabaseField(columnName = "DEVTYPE")
    private String devType;

    /** 业务类型 **/
    @DatabaseField(columnName = "SERVTYPE")
    private String servType;

    /** 指标编码 **/
    @DatabaseField(columnName = "TESTKEYCODE")
    private String taskKeyCode;

    /** 设备编码,手机终端用IMEI号 **/
    @DatabaseField(columnName = "DEVICE_CODE")
    private String deviceCode;

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
    public long getTaskId() {
        return taskId;
    }

    /**
     * @param taskId
     *            the taskId to set
     */
    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    /**
     * @return the devType
     */
    public String getDevType() {
        return devType;
    }

    /**
     * @param devType
     *            the devType to set
     */
    public void setDevType(String devType) {
        this.devType = devType;
    }

    /**
     * @return the servType
     */
    public String getServType() {
        return servType;
    }

    /**
     * @param servType
     *            the servType to set
     */
    public void setServType(String servType) {
        this.servType = servType;
    }

    /**
     * @return the taskKeyCode
     */
    public String getTaskKeyCode() {
        return taskKeyCode;
    }

    /**
     * @param taskKeyCode
     *            the taskKeyCode to set
     */
    public void setTaskKeyCode(String taskKeyCode) {
        this.taskKeyCode = taskKeyCode;
    }

    /**
     * @return the deviceCode
     */
    public String getDeviceCode() {
        return deviceCode;
    }

    /**
     * @param deviceCode
     *            the deviceCode to set
     */
    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }
}
