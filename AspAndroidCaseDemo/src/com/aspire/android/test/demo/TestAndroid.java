/**
 * @(#) TestAndroid.java Created on 2012-9-5
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.demo;

import com.aspire.android.test.testcase.AbstractTestCase;

/**
 * The class <code>TestAndroid</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class TestAndroid extends AbstractTestCase {

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.testcase.AbstractTestCase#doExecute()
     */
    @Override
    protected void doExecute() throws Exception {
//        deviceEntity.runApp("评估程序1");
        // final int[] finds = findInScreenCom(0, 250, 460, 600, "11.jpg", 0, 0, 1, 1, 10, 60, 60, 60000);
        // if (null != finds) {
        // final String path = deviceEntity.saveScreen(null, "name");
        int p [] = deviceEntity.findPicture("/mnt/sdcard/aspire/temp/name", 0, 0, 0, 0,
                "SamSungI9108_999024_43_59_number5.bmp", 0, 0, 0, 0, 20);
        assertTrue(null != p);
        // }

    }
}
