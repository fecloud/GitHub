/**
 * @(#) Case1.java Created on 2012-6-25
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.demo;

import com.aspire.android.test.testcase.AbstractTestCase;

/**
 * The class <code>Case1</code>
 *
 * @author linjunsui
 * @version 1.0
 */
public class Case1 extends AbstractTestCase {

    /**
     * {@inheritDoc}
     * @see com.aspire.android.test.testcase.AbstractTestCase#doExecute()
     */
    @Override
    protected void doExecute() throws Exception {
        
        deviceEntity.screenClick(320, 720);
        
        deviceEntity.verifyScreen(30, 30, "ASPIRE_20120625_0.jpg", 30, 30, 50, 50, 3, 1);
        
        deviceEntity.beginTransaction("700001");
        deviceEntity.saveVerifyCertificate();
        deviceEntity.endTransaction("700001", "1", false);
        deviceEntity.beginTransaction("700005");
        deviceEntity.endTransaction("700005", "1", true);
    }

}
