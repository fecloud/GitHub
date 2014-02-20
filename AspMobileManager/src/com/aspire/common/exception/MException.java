/**
 * @(#) MException.java Created on 2012-5-4
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.common.exception;

import java.io.Serializable;

/**
 * The class <code>MException</code>
 *
 * @author linjunsui
 * @version 1.0
 */
public class MException extends Exception implements Serializable{

    /**
     * SID
     */
    private static final long serialVersionUID = 2187040212031235350L;
    
    /**
     * uiMessage
     */
    protected String uiMessage;
    
    /**
     * Constructor
     */
    public MException() {
        super();
    }

    /**
     * Constructor
     * @param detailMessage
     * @param throwable
     */
    public MException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
    
    /**
     * Constructor
     * @param detailMessage
     * @param throwable
     * @param uiMessage
     */
    public MException(String detailMessage, Throwable throwable, String uiMessage) {
        super(detailMessage, throwable);
        this.uiMessage = uiMessage;
    }

    /**
     * Constructor
     * @param detailMessage
     */
    public MException(String detailMessage) {
        super(detailMessage);
    }
    
    /**
     * Constructor
     * @param detailMessage
     * @param uiMessage
     */
    public MException(String detailMessage, String uiMessage) {
        super(detailMessage);
        this.uiMessage = uiMessage;
    }

    /**
     * Constructor
     * @param throwable
     */
    public MException(Throwable throwable) {
        super(throwable);
    }
    
    /**
     * Constructor
     * @param throwable
     * @param uiMessage
     */
    public MException(Throwable throwable, String uiMessage) {
        super(throwable);
        this.uiMessage = uiMessage;
    }

    /**
     * Getter of uiMessage
     * @return the uiMessage
     */
    public String getUiMessage() {
        return uiMessage;
    }

    /**
     * Setter of uiMessage
     * @param uiMessage the uiMessage to set
     */
    public void setUiMessage(String uiMessage) {
        this.uiMessage = uiMessage;
    }
}
