/**
 * @(#) SystemProperty.java Created on 2012-9-6
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * The class <code>SystemProperty</code>
 *
 * @author likai
 * @version 1.0
 */
public class SystemProperties {

    @XStreamAlias("connectSwitch")
    private boolean connectSwitch;

    @XStreamAlias("uploadResultType")
    private String uploadResultType;

    @XStreamAlias("dataCollectionFreq")
    private String dataCollectionFreq;

    @XStreamAlias("smsControlNum")
    private String smsControlNum;

    @XStreamAlias("dataParentPath")
    private String dataParentPath;

    /**
     * Getter of connectSwitch
     * @return the connectSwitch
     */
    public boolean isConnectSwitch() {
        return connectSwitch;
    }

    /**
     * Setter of connectSwitch
     * @param connectSwitch the connectSwitch to set
     */
    public void setConnectSwitch(boolean connectSwitch) {
        this.connectSwitch = connectSwitch;
    }

    /**
     * Getter of uploadResultType
     * @return the uploadResultType
     */
    public String getUploadResultType() {
        return uploadResultType;
    }

    /**
     * Setter of uploadResultType
     * @param uploadResultType the uploadResultType to set
     */
    public void setUploadResultType(String uploadResultType) {
        this.uploadResultType = uploadResultType;
    }

    /**
     * Getter of dataCollectionFreq
     * @return the dataCollectionFreq
     */
    public String getDataCollectionFreq() {
        return dataCollectionFreq;
    }

    /**
     * Setter of dataCollectionFreq
     * @param dataCollectionFreq the dataCollectionFreq to set
     */
    public void setDataCollectionFreq(String dataCollectionFreq) {
        this.dataCollectionFreq = dataCollectionFreq;
    }

    /**
     * Getter of smsControlNum
     * @return the smsControlNum
     */
    public String getSmsControlNum() {
        return smsControlNum;
    }

    /**
     * Setter of smsControlNum
     * @param smsControlNum the smsControlNum to set
     */
    public void setSmsControlNum(String smsControlNum) {
        this.smsControlNum = smsControlNum;
    }

    /**
     * Getter of dataParentPath
     * @return the dataParentPath
     */
    public String getDataParentPath() {
        return dataParentPath;
    }

    /**
     * Setter of dataParentPath
     * @param dataParentPath the dataParentPath to set
     */
    public void setDataParentPath(String dataParentPath) {
        this.dataParentPath = dataParentPath;
    }
}
