/**
 * @(#) SSettingRequest.java Created on 2012-7-25
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.service.request;

/**
 * The class <code>SSettingRequest</code>
 * 
 * @author wuyanlong
 * @version 1.0
 */
public class GResultResponse extends AspResponse {
    /**
     * 判断文件是否产出成功
     */
    private boolean deleteResult;
    /**
     * 查询是否包含需要同步的文件
     */
    private boolean haveFile;
    /**
     * Getter of deleteResult
     * @return the deleteResult
     */
    public boolean isDeleteResult() {
        return deleteResult;
    }
    /**
     * Setter of deleteResult
     * @param deleteResult the deleteResult to set
     */
    public void setDeleteResult(boolean deleteResult) {
        this.deleteResult = deleteResult;
    }
    /**
     * Getter of haveFile
     * @return the haveFile
     */
    public boolean isHaveFile() {
        return haveFile;
    }
    /**
     * Setter of haveFile
     * @param haveFile the haveFile to set
     */
    public void setHaveFile(boolean haveFile) {
        this.haveFile = haveFile;
    }
    /**
     * 
     * Constructor
     */
    public GResultResponse() {
        super(G_RESULT_RESP, 0);
    }
    /**
     * Constructor
     * 
     * @param command
     * @param result
     * @param syncFlag
     */
    public GResultResponse(long syncFlag) {
        super(G_RESULT_RESP, syncFlag);
    }

}
