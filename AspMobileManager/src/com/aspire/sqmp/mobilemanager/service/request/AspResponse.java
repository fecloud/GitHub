/**
 * @(#) AspRespone.java Created on 2012-7-25
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.service.request;

/**
 * The class <code>AspRespone</code>
 * 
 * @author wuyanlong
 * @version 1.0
 */
public class AspResponse extends AspRequest {
    public static int RESULT_SUCCESS = 1;
    public static int RESULT_FAIL = 0;
    protected int result;

    /**
     * The error msg of respone
     */
    protected String msg;

    /**
     * 
     * Constructor
     */
    public AspResponse() {
        this(0, RESULT_SUCCESS, 0);
    }

    /**
     * 
     * Constructor
     * 
     * @param command
     * @param syncFlag
     */
    public AspResponse(int command, long syncFlag) {
        this(command, RESULT_SUCCESS, syncFlag);
    }

    /**
     * 
     * Constructor
     * 
     * @param command
     * @param result
     * @param syncFlag
     */
    public AspResponse(int command, int result, long syncFlag) {
        super(command);
        this.syncFlag = syncFlag;
        this.result = result;
    }

    /**
     * Getter of result
     * 
     * @return the result
     */
    public int getResult() {
        return result;
    }

    /**
     * Setter of result
     * 
     * @param result
     *            the result to set
     */
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * Getter of msg
     * 
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Setter of msg
     * 
     * @param msg
     *            the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * is success
     * 
     * @return
     */
    public boolean isSuccess() {
        return result == RESULT_SUCCESS;
    }

    /**
     * sync response and request
     * 
     * @param response
     * @return
     */
    public boolean checkResponse(AspResponse response) {
        if (syncFlag == response.getSyncFlag())
            return true;
        return false;
    }
}
