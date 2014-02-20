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
public class SSettingResponse extends AspResponse {
    /**
     * 
     * Constructor
     */
    public SSettingResponse() {
        super(S_SETTING_RESP, 0);
    }

    /**
     * Constructor
     * 
     * @param command
     * @param result
     * @param syncFlag
     */
    public SSettingResponse(long syncFlag) {
        super(S_SETTING_RESP, syncFlag);
    }

}
