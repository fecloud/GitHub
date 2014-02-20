/**
 * @(#) GetMessageTest.java Created on 2012-7-16
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.demo;

import java.util.List;

import com.aspire.android.test.execute.ContentValues;
import com.aspire.android.test.testcase.AbstractTestCase;

/**
 * The class <code>GetMessageTest</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class GetMessageTest extends AbstractTestCase {

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.testcase.AbstractTestCase#doExecute()
     */
    @Override
    protected void doExecute() throws Exception {
        List<ContentValues> list = deviceEntity.getMessage(10);
        System.out.println(list);
    }

}
