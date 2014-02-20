/**
 * @(#) GStorePathResponse.java Created on 2012-7-30
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.service.request;

/**
 * The class <code>GStorePathResponse</code>
 * 
 * @author wuyanlong
 * @version 1.0
 */
public class GStoreInfoResponse extends AspResponse {
    /**
     * Sd卡根路径
     */
    private String sdcardPath;
    /**
     * Sd卡剩余空间，单位:MB
     */
    private long sdcardAvailaleSize;

    /**
     * 
     * Constructor
     */
    public GStoreInfoResponse() {
        super(G_STORE_PATH_RESP, 0);
    }

    /**
     * Constructor
     * 
     * @param command
     * @param result
     * @param syncFlag
     */
    public GStoreInfoResponse(long syncFlag) {
        super(G_STORE_PATH_RESP, syncFlag);
    }

    /**
     * Getter of sdcardPath
     * 
     * @return the sdcardPath
     */
    public String getSdcardPath() {
        return sdcardPath;
    }

    /**
     * Setter of sdcardPath
     * 
     * @param sdcardPath
     *            the sdcardPath to set
     */
    public void setSdcardPath(String sdcardPath) {
        this.sdcardPath = sdcardPath;
    }

    /**
     * Getter of sdcardAvailaleSize
     * 
     * @return the sdcardAvailaleSize
     */
    public long getSdcardAvailaleSize() {
        return sdcardAvailaleSize;
    }

    /**
     * Setter of sdcardAvailaleSize
     * 
     * @param sdcardAvailaleSize
     *            the sdcardAvailaleSize to set
     */
    public void setSdcardAvailaleSize(long sdcardAvailaleSize) {
        this.sdcardAvailaleSize = sdcardAvailaleSize;
    }

}
