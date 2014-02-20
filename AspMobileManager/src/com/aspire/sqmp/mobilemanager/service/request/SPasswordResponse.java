/**
 * @(#) WCaseRequest.java Created on 2012-7-25
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.service.request;

/**
 * The class <code>WCaseRequest</code>
 * 
 * @author wuyanlong
 * @version 1.0
 */
public class SPasswordResponse extends AspResponse {
    /**
     * 
     * Constructor
     */
    public SPasswordResponse() {
        super(S_PASSWORD_RESP, 0);
    }
    /**
     * Constructor
     * 
     * @param command
     * @param result
     * @param syncFlag
     */
    public SPasswordResponse(long syncFlag) {
        super(S_PASSWORD_RESP, syncFlag);
    }

}
