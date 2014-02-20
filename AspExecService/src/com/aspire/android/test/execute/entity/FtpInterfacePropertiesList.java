/**
 * @(#) FtpProperties.java Created on 2012-9-6
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * The class <code>FtpInterfacePropertiesList</code>
 * 
 * @author likai
 * @version 1.0
 */
public class FtpInterfacePropertiesList {
    
    @XStreamAlias("serviceKeysFtpProperties")
    protected FtpInterfaceProperties serviceKeysFtpProperties;

    @XStreamAlias("testScriptFtpProperties")
    protected FtpInterfaceProperties testScriptFtpProperties;

    @XStreamAlias("testResultFtpProperties")
    protected FtpInterfaceProperties testResultFtpProperties;

    @XStreamAlias("versionFtpProperties")
    protected FtpInterfaceProperties versionFtpProperties;
    
    @XStreamAlias("ftpDataTimeOut")
    protected String ftpDataTimeOut;

    /**
     * Getter of serviceKeysFtpProperties
     * 
     * @return the serviceKeysFtpProperties
     */
    public FtpInterfaceProperties getServiceKeysFtpProperties() {
        return serviceKeysFtpProperties;
    }

    /**
     * Setter of serviceKeysFtpProperties
     * 
     * @param serviceKeysFtpProperties
     *            the serviceKeysFtpProperties to set
     */
    public void setServiceKeysFtpProperties(FtpInterfaceProperties serviceKeysFtpProperties) {
        this.serviceKeysFtpProperties = serviceKeysFtpProperties;
    }

    /**
     * Getter of testScriptFtpProperties
     * 
     * @return the testScriptFtpProperties
     */
    public FtpInterfaceProperties getTestScriptFtpProperties() {
        return testScriptFtpProperties;
    }

    /**
     * Setter of testScriptFtpProperties
     * 
     * @param testScriptFtpProperties
     *            the testScriptFtpProperties to set
     */
    public void setTestScriptFtpProperties(FtpInterfaceProperties testScriptFtpProperties) {
        this.testScriptFtpProperties = testScriptFtpProperties;
    }

    /**
     * Getter of testResultFtpProperties
     * 
     * @return the testResultFtpProperties
     */
    public FtpInterfaceProperties getTestResultFtpProperties() {
        return testResultFtpProperties;
    }

    /**
     * Setter of testResultFtpProperties
     * 
     * @param testResultFtpProperties
     *            the testResultFtpProperties to set
     */
    public void setTestResultFtpProperties(FtpInterfaceProperties testResultFtpProperties) {
        this.testResultFtpProperties = testResultFtpProperties;
    }

    /**
     * Getter of versionFtpProperties
     * @return the versionFtpProperties
     */
    public FtpInterfaceProperties getVersionFtpProperties() {
        return versionFtpProperties;
    }

    /**
     * Setter of versionFtpProperties
     * @param versionFtpProperties the versionFtpProperties to set
     */
    public void setVersionFtpProperties(FtpInterfaceProperties versionFtpProperties) {
        this.versionFtpProperties = versionFtpProperties;
    }

    /**
     * Getter of ftpDataTimeOut
     * @return the ftpDataTimeOut
     */
    public String getFtpDataTimeOut() {
        return ftpDataTimeOut;
    }

    /**
     * Setter of ftpDataTimeOut
     * @param ftpDataTimeOut the ftpDataTimeOut to set
     */
    public void setFtpDataTimeOut(String ftpDataTimeOut) {
        this.ftpDataTimeOut = ftpDataTimeOut;
    }
}
