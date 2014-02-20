/**

 * @(#) TestResult.java Created on 2012-6-1

 *

 * Copyright (c) 2012 Aspire. All Rights Reserved

 */
package com.aspire.android.test.result.sync;

/**
 * 
 * The class <code>TestResult</code>
 * 
 * 
 * 
 * @author gouanming
 * 
 * @version 1.0
 */
public class TestResult {
    //业务编码
    public String serviceCode;
    //指标编码
    public String testKeyCode;
    //任务编号
    public String testTaskCode;
    //拨测方式
    public String testMode;
    //脚本名称
    public String scriptName;
    //脚本版本号
    public String scriptVersion;
    //拨测开始时间
    public String testBeginTime;
    //拨测结束时间
    public String testEndTime;
    //执行序号
    public long testNum;
    //拨测结果
    public String testResult;
    //拨测值
    public String testValue;
    //网络
    public String NetworkFlag;
    //附件   0：无附件 1：实体附件 2：URL地址
    public String attachFileType;
    //附件文件名
    public String attachFileName;

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getTestKeyCode() {
        return testKeyCode;
    }

    public void setTestKeyCode(String testKeyCode) {
        this.testKeyCode = testKeyCode;
    }

    public String getTestTaskCode() {
        return testTaskCode;
    }

    public void setTestTaskCode(String testTaskCode) {
        this.testTaskCode = testTaskCode;
    }

    public String getTestMode() {
        return testMode;
    }

    public void setTestMode(String testMode) {
        this.testMode = testMode;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public String getScriptVersion() {
        return scriptVersion;
    }

    public void setScriptVersion(String scriptVersion) {
        this.scriptVersion = scriptVersion;
    }

    public String getTestBeginTime() {
        return testBeginTime;
    }

    public void setTestBeginTime(String testBeginTime) {
        this.testBeginTime = testBeginTime;
    }

    public String getTestEndTime() {
        return testEndTime;
    }

    public void setTestEndTime(String testEndTime) {
        this.testEndTime = testEndTime;
    }

    public long getTestNum() {
        return testNum;
    }

    public void setTestNum(long testNum) {
        this.testNum = testNum;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public String getTestValue() {
        return testValue;
    }

    public void setTestValue(String testValue) {
        this.testValue = testValue;
    }

    public String getAttachFileType() {
        return attachFileType;
    }

    public void setAttachFileType(String attachFileType) {
        this.attachFileType = attachFileType;
    }

    public String getAttachFileName() {
        return attachFileName;
    }

    public void setAttachFileName(String attachFileName) {
        this.attachFileName = attachFileName;
    }

    public String getNetworkFlag() {
        return NetworkFlag;
    }

    public void setNetworkFlag(String networkFlag) {
        NetworkFlag = networkFlag;
    }

}
