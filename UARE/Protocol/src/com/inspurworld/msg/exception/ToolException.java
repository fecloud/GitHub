/**
 * @(#) ToolException.java Created on 2012-10-20
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.msg.exception;

/**
 * The class <code>ToolException</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class ToolException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ToolException(String msg) {
        super(msg);
    }

    public ToolException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

}
