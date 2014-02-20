/**

 * @(#) RestResultResponsevo.java Created on 2012-6-1

 *

 * Copyright (c) 2012 Aspire. All Rights Reserved

 */
package com.aspire.android.test.result.sync;

import java.util.List;

/**
 * 
 * The class <code>FileHead</code>
 * 
 * 
 * 
 * @author gouanming
 * 
 * @version 1.0
 */
public class FileHead {
    
    //厂商编码
    private String venderCode;
    //拨测设备型号
    private String devType;
    //设备编码
    private String devId;
    //IP地址
    private String ip;
    //手机号码
    private String mobileNum;
    //结果文件总记录数
    private Integer totalNum;
    private List<Code> mCode;
    List<TestResult> mTestResult; 
    
    
    
    public String getVenderCode() {
        return venderCode;
    }
    public void setVenderCode(String venderCode) {
        this.venderCode = venderCode;
    }
    public String getDevType() {
        return devType;
    }
    public void setDevType(String devType) {
        this.devType = devType;
    }
    public String getDevId() {
        return devId;
    }
    public void setDevId(String devId) {
        this.devId = devId;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getMobileNum() {
        return mobileNum;
    }
    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }
    public Integer getTotalNum() {
        return totalNum;
    }
    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }
    public List<Code> getmCode() {
        return mCode;
    }
    public void setmCode(List<Code> mCode) {
        this.mCode = mCode;
    }
    public List<TestResult> getmTestResult() {
        return mTestResult;
    }
    public void setmTestResult(List<TestResult> mTestResult) {
        this.mTestResult = mTestResult;
    }
    

}
