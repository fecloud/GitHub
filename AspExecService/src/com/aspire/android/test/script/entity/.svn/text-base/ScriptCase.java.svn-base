/**
 * @(#) ScriptServiceIndex.java Created on 2012-5-16
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
 * 
 * The class <code>ScriptServiceIndex</code>
 * 
 * @author gouanming
 * @version 1.0
 */
@DatabaseTable(tableName = Constants.ATS_script_CASE)
public class ScriptCase extends BaseEntity {

    @DatabaseField(columnName = "ID", generatedId = true)
    private Long id;
    @DatabaseField(columnName = "CASEPATH")
    private String casePath;
    @DatabaseField(columnName = "ATSPATH")
    private String atsPath;
    @DatabaseField(columnName = "APKPATH")
    private String apkPath;
    @DatabaseField(columnName = "PACKAGENAME")
    private String packageName;
    @DatabaseField(columnName = "UPDATE", dataType=DataType.DATE)
    private Date upDate;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCasePath() {
        return casePath;
    }

    public void setCasePath(String casePath) {
        this.casePath = casePath;
    }

    public String getAtsPath() {
        return atsPath;
    }

    public void setAtsPath(String atsPath) {
        this.atsPath = atsPath;
    }

    public String getApkPath() {
        return apkPath;
    }

    public void setApkPath(String apkPath) {
        this.apkPath = apkPath;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Date getUpDate() {
        return upDate;
    }

    public void setUpDate(Date upDate) {
        this.upDate = upDate;
    }
    
    
    
}
