/**
 * @(#) LogTest.java Created on 2012-7-26
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test;

import java.io.File;
import java.util.HashMap;

import android.test.AndroidTestCase;

import com.aspire.android.log.LogLevel;
import com.aspire.android.log.Logger;

/**
 * The class <code>LogTest</code>
 * 
 * @author Administrator
 * @version 1.0
 */
public class LogTest extends AndroidTestCase {

    public void testLog() {
        System.setProperty("MaxLogSize", "1");
        final String logName = "" + System.currentTimeMillis();
        final String location = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator
                + "aspire";
        final HashMap<String, String> environment = new HashMap<String, String>();
        environment.put("KEY_CASE_LOGLOCATION", location);
        environment.put("LOG_LEVEL", LogLevel.ALL.name());
        Logger logger = Logger.getLogger(this.getContext(), logName, environment);
        for(int i = 0;i<Integer.MAX_VALUE;i++)
            logger.verbose("verbose"+ i);
//        logger.debug("debug");
//        logger.info("info");
//        logger.warn("warn");
//        logger.error("error");
//        logger.fatal("fatal");
//        assertTrue(2 >= 2);
    }
}
