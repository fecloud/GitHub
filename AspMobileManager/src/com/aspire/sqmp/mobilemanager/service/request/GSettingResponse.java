/**
 * @(#) SSettingRequest.java Created on 2012-7-25
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.service.request;

import com.aspire.sqmp.mobilemanager.entity.MobileSharedFile;

/**
 * The class <code>SSettingRequest</code>
 * 
 * @author wuyanlong
 * @version 1.0
 */
public class GSettingResponse extends AspResponse {

    private MobileSharedFile mobileSharedFile;
    /**
     * Getter of mobileSharedFile
     * @return the mobileSharedFile
     */
    public MobileSharedFile getMobileSharedFile() {
        return mobileSharedFile;
    }

    /**
     * Setter of mobileSharedFile
     * @param mobileSharedFile the mobileSharedFile to set
     */
    public void setMobileSharedFile(MobileSharedFile mobileSharedFile) {
        this.mobileSharedFile = mobileSharedFile;
    }

    /**
     * 
     * Constructor
     */
    public GSettingResponse() {
        super(G_SETTING_RESP, 0);
    }

    /**
     * Constructor
     * 
     * @param command
     * @param result
     * @param syncFlag
     */
    public GSettingResponse(long syncFlag) {
        super(G_SETTING_RESP, syncFlag);
    }

}
