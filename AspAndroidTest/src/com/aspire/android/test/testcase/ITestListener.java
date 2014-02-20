/**
 * @(#) ITestListener.java Created on 2012-4-23
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.testcase;

import com.aspire.android.test.execute.ContentValues;

/**
 * The class <code>ITestListener</code>
 *
 * @author linjunsui
 * @version 1.0
 */
public interface ITestListener {

    /**
     * on start
     */
    public void onStart();
    
    /**
     * on finish
     */
    public void onFinish(ContentValues resultMap);
    
    /**
     * on error
     */
    public void onError(Throwable throwable, ContentValues resultMap);
}
