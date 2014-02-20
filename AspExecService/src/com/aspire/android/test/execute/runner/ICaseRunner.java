/**
 * @(#) ICaseRunner.java Created on 2012-4-28
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute.runner;

import com.aspire.android.common.exception.MException;
import com.aspire.android.test.execute.ContentValues;

/**
 * The class <code>ICaseRunner</code>
 * 
 * @author linjunsui
 * @version 1.0
 */
public interface ICaseRunner {

    /**
     * run the case
     * @param setting
     * @param globalVariables
     * @param params
     * @return
     * @throws MException
     */
    public boolean runCase(ContentValues setting, ContentValues globalVariables, ContentValues params)
            throws MException;
}
