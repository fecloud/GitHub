/**
 * @(#) IndexListing.java Created on 2012-7-25
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.task.entity;

/**
 * The class <code>IndexListing</code> 指标列表
 *  指标列表
 * @author Gouanming
 * @version 1.0
 */
public class IndexListing {

    // 业务类型名称
    private String serviceName;
    // 拨测指标名称
    private String testKeyName;
    // 当天执行次数
    private long executionNumber;
    // 共执行次数
    private long allExecutionNumber;
    // 成功次数
    private long succeedNumber;
    // 失败次数
    private long loseNumber;
    
    public String getServiceName() {
        return serviceName;
    }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public String getTestKeyName() {
        return testKeyName;
    }
    public void setTestKeyName(String testKeyName) {
        this.testKeyName = testKeyName;
    }
    public long getExecutionNumber() {
        return executionNumber;
    }
    public void setExecutionNumber(long executionNumber) {
        this.executionNumber = executionNumber;
    }
    public long getAllExecutionNumber() {
        return allExecutionNumber;
    }
    public void setAllExecutionNumber(long allExecutionNumber) {
        this.allExecutionNumber = allExecutionNumber;
    }
    public long getSucceedNumber() {
        return succeedNumber;
    }
    public void setSucceedNumber(long succeedNumber) {
        this.succeedNumber = succeedNumber;
    }
    public long getLoseNumber() {
        return loseNumber;
    }
    public void setLoseNumber(long loseNumber) {
        this.loseNumber = loseNumber;
    }
    
    

}
