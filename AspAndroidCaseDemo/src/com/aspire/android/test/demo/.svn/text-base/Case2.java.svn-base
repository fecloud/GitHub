/**
 * @(#) Case2.java Created on 2012-6-25
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.demo;

import com.aspire.android.test.testcase.AbstractTestCase;

/**
 * The class <code>Case2</code>
 *
 * @author linjunsui
 * @version 1.0
 */
public class Case2 extends AbstractTestCase {

    /**
     * {@inheritDoc}
     * @see com.aspire.android.test.testcase.AbstractTestCase#doExecute()
     */
    @Override
    protected void doExecute() throws Exception {
       deviceEntity.wapOpen("www.baidu.com");
       
       deviceEntity.beginTransaction("700006");
       deviceEntity.endTransaction("700006", "1", true);

    }

}
