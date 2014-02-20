/**
 * @(#) ExceptionHandler.java Created on 2012-5-4
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <code>ExceptionHandler</code>
 *
 * @author linjunsui
 * @version 1.0
 */
public class ExceptionHandler {
    
    /**
     * logger
     */
    protected static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);
    
    /**
     * logTag
     */
    protected static String LOGTAG = "com.aspire.android";
    
    /**
     * Getter of lOGTAG
     * @return the lOGTAG
     */
    public static String getLOGTAG() {
        return LOGTAG;
    }

    /**
     * Setter of lOGTAG
     * @param lOGTAG the lOGTAG to set
     */
    public static void setLOGTAG(String lOGTAG) {
        LOGTAG = lOGTAG;
    }

    /**
     * handle the exception
     * @param throwable origin throwable
     * @param uiMessage uiMessage
     * @return exception while need re-throw
     */
    public static MException handle(Throwable throwable, String uiMessage) {
        MException exception = null;
        if (throwable instanceof MException) {
            exception = (MException) throwable;
            exception.setUiMessage(uiMessage);
        } else {
            exception = new MException(throwable.getMessage(), throwable, uiMessage);
        }
        logger.error(uiMessage, throwable);
        return exception;
    }

    /**
     * handle the exception
     * @param throwable origin throwable
     * @return exception while need re-throw
     */
    public static MException handle(Throwable throwable) throws MException {
        
        MException exception = null;
        if (throwable instanceof MException) {
            exception = (MException) throwable;
        } else {
            exception = new MException(throwable.getMessage(), throwable);
        }
        logger.error(throwable.getMessage(), throwable);
        return exception;
    }
    
}
