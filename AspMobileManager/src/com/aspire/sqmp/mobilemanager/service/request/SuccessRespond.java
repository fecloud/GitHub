/**
 * @(#) ErrorRespone.java Created on 2012-7-25
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.service.request;

/**
 * The class <code>ErrorRespone</code>
 * 
 * @author wuyanlong
 * @version 1.0
 */
public class SuccessRespond extends AspResponse {
    /**
     * 
     * Constructor
     */
    public SuccessRespond() {
        super(0, 0);
    }


    /**
     * Constructor
     * 
     * @param command
     * @param d
     */
    public SuccessRespond(int commond, long syncFlag) {
        super(commond, RESULT_SUCCESS, syncFlag);
    }

    /**
     * Constructor
     * 
     * @param command
     * @param d
     */
    public SuccessRespond(int commond, long syncFlag, String errorMsg) {
        super(commond, RESULT_SUCCESS, syncFlag);
        setMsg(errorMsg);
    }


}
