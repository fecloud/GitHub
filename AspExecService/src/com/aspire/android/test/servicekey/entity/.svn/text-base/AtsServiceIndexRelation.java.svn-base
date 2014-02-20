/**
 * @(#) AtsServiceIndexRelation.java Created on 2012-6-12
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.servicekey.entity;

import java.util.Date;

import com.aspire.android.common.entity.BaseEntity;
import com.aspire.android.test.Constants;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * The class <code>AtsServiceIndexRelation</code>
 *
 * @author gouanming
 * @version 1.0
 */
@DatabaseTable(tableName = Constants.ATS_SERVICE_INDEX)
public class AtsServiceIndexRelation  extends BaseEntity {
    
    
    @DatabaseField(columnName = "ID", generatedId = true)
    private long id;
    
    /**
     * 指标编码
     */
    @DatabaseField(columnName = "TESTKEYCODE")
    private String testKeyCode;
    
    /**
     * 业务编码
     */
    @DatabaseField(columnName = "SERVICECODE")
    private String serviceCode;
    
    /**
     * 指标名称
     */
    @DatabaseField(columnName = "TESTKEYNAME")
    private String testKeyName;
    /**
     * 业务名称
     */
    @DatabaseField(columnName = "SERVICNAME")
    private String serviceName;
    
    /**
     * 状态: 1-有效 /0-失效
     */
    @DatabaseField(columnName = "STATUS")
    private String status;

    /**
     * 最后更新时间
     */
    @DatabaseField(columnName = "LASTUPDATEDATE", dataType = DataType.DATE)
    private Date lastUpdateDate;
    
    
    
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTestKeyCode() {
        return testKeyCode;
    }

    public void setTestKeyCode(String testKeyCode) {
        this.testKeyCode = testKeyCode;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getTestKeyName() {
        return testKeyName;
    }

    public void setTestKeyName(String testKeyName) {
        this.testKeyName = testKeyName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
    
    

}
