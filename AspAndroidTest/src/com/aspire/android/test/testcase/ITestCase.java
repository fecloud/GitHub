/**
 * @(#) ITestCase.java Created on 2012-4-25
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.testcase;

import com.aspire.android.test.environment.IEnvironment;
import com.aspire.android.test.execute.ContentValues;

/**
 * The class <code>ITestCase</code>
 *
 * @author linjunsui
 * @version 1.0
 */
public interface ITestCase {
    /**
     * @param testListener
     */
    public void setListener(ITestListener testListener);

    /**
     * 
     * @param environment
     * @param paramMap
     * @return
     */
    public ContentValues execute(IEnvironment environment, ContentValues paramMap);
}
