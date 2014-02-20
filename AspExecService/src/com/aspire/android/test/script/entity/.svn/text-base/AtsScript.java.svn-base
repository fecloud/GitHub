/**
 * @(#) AtsScript.java Created on 2012-5-16
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.script.entity;

import java.util.Date;

import com.aspire.android.common.entity.BaseEntity;
import com.aspire.android.test.Constants;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * The class <code>AtsScript</code>
 *
 * @author gouanming
 * @version 1.0
 */
@DatabaseTable(tableName = Constants.ATS_script)
public class AtsScript extends BaseEntity {

    /**
     * 脚本ID
     */
    @DatabaseField(columnName = "ID", generatedId = true)
    private Long id;

    /**
     * 脚本名称
     */
    @DatabaseField(columnName = "SCRIPTNAME")
    private String scriptName;

    /**
     * 拨测方式
     */
    @DatabaseField(columnName = "TESTMODE")
    private String testMode;

    /**
     * 保存名称
     */
    @DatabaseField(columnName = "SAVENAME")
    private String saveName;

    /**
     * 保存路径
     */
    @DatabaseField(columnName = "SAVEPATH")
    private String savePath;

    /**
     * 版本号
     */
    @DatabaseField(columnName = "SCRIPTVERSION")
    private String scriptVersion;

    /**
     * 状态: 1-有效 /0-失效
     */
    @DatabaseField(columnName = "STATUS", columnDefinition = "char")
    private Character status;

    /**
     * 更新时间
     */
    @DatabaseField(columnName = "LASTUPDATEDATE")
    private String lastUpdateDate;
    
    
    /** 业务类型 **/
    @DatabaseField(columnName = "SERVTYPE")
    private String servType;

    /** 指标编码 **/
    @DatabaseField(columnName = "TESTKEYCODE")
    private String taskKeyCode;

    /** 指标编码 **/
    @DatabaseField(columnName = "UPDATE", dataType=DataType.DATE)
    private Date upDate;
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the scriptName
     */
    public String getScriptName() {
        return scriptName;
    }

    /**
     * @param scriptName
     *            the scriptName to set
     */
    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    /**
     * @return the testMode
     */
    public String getTestMode() {
        return testMode;
    }

    /**
     * @param testMode
     *            the testMode to set
     */
    public void setTestMode(String testMode) {
        this.testMode = testMode;
    }


    /**
     * @return the saveName
     */
    public String getSaveName() {
        return saveName;
    }

    /**
     * @param saveName
     *            the saveName to set
     */
    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    /**
     * @return the savePath
     */
    public String getSavePath() {
        return savePath;
    }

    /**
     * @param savePath
     *            the savePath to set
     */
    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    /**
     * @return the scriptVersion
     */
    public String getScriptVersion() {
        return scriptVersion;
    }

    /**
     * @param scriptVersion
     *            the scriptVersion to set
     */
    public void setScriptVersion(String scriptVersion) {
        this.scriptVersion = scriptVersion;
    }

    /**
     * @return the status
     */
    public Character getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(Character status) {
        this.status = status;
    }

    /**
     * @return the lastUpdateDate
     */
    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * @param lastUpdateDate
     *            the lastUpdateDate to set
     */
    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getServType() {
        return servType;
    }

    public void setServType(String servType) {
        this.servType = servType;
    }

    public String getTaskKeyCode() {
        return taskKeyCode;
    }

    public void setTaskKeyCode(String taskKeyCode) {
        this.taskKeyCode = taskKeyCode;
    }

    public Date getUpDate() {
        return upDate;
    }

    public void setUpDate(Date upDate) {
        this.upDate = upDate;
    }
    
    

}

