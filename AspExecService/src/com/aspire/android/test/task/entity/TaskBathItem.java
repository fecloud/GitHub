/**
 * @(#) TaskBathItem.java Created on 2012-7-10
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.task.entity;

/**
 * The class <code>TaskBathItem</code>
 *
 * @author Administrator
 * @version 1.0
 */
public class TaskBathItem {
    
    private long taskItemBatchId;
    private String testKeyName;
    private String testKeyCode;
    private String logLevel;
    private String result;
    
    
    public long getTaskItemBatchId() {
        return taskItemBatchId;
    }
    public void setTaskItemBatchId(long taskItemBatchId) {
        this.taskItemBatchId = taskItemBatchId;
    }
    public String getTestKeyName() {
        return testKeyName;
    }
    public void setTestKeyName(String testKeyName) {
        this.testKeyName = testKeyName;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public String getTestKeyCode() {
        return testKeyCode;
    }
    public void setTestKeyCode(String testKeyCode) {
        this.testKeyCode = testKeyCode;
    }
    public String getLogLevel() {
        return logLevel;
    }
    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }
    
    

}
