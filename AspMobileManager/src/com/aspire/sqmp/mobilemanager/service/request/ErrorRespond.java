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
public class ErrorRespond extends AspResponse {
    /**
     * 
     * Constructor
     */
    public ErrorRespond() {
        super(0, 0);
    }


    /**
     * Constructor
     * 
     * @param command
     * @param d
     */
    public ErrorRespond(int commond, long syncFlag) {
        super(commond, RESULT_FAIL, syncFlag);
    }

    /**
     * Constructor
     * 
     * @param command
     * @param d
     */
    public ErrorRespond(int commond, long syncFlag, String errorMsg) {
        super(commond, RESULT_FAIL, syncFlag);
        setMsg(errorMsg);
    }


}
