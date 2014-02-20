/**
 * @(#) CaseScript.java Created on 2012-7-11
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.script.entity;


/**
 * The class <code>CaseScript</code>
 *
 * @author Administrator
 * @version 1.0
 */
public class CaseScript {
    private long caseId;
    private String caseName;
    //指标编码
    private String testKeyCode;
    //业务编码
    private String serviceCode;
    
    
    
    public long getCaseId() {
        return caseId;
    }
    public void setCaseId(long caseId) {
        this.caseId = caseId;
    }
    public String getCaseName() {
        return caseName;
    }
    public void setCaseName(String caseName) {
        this.caseName = caseName;
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


}
