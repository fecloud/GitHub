/**
 * @(#) DemoTestCase.java Created on 2012-5-7
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.demo;

import android.util.Log;

import com.aspire.android.test.exception.MException;
import com.aspire.android.test.execute.CommandConstants;
import com.aspire.android.test.testcase.AbstractTestCase;

/**
 * The class <code>DemoTestCase</code>
 * 
 * @author linjunsui
 * @version 1.0
 */
public class DemoTestCase extends AbstractTestCase {

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.testcase.AbstractTestCase#doExecute()
     */
    @Override
    protected void doExecute() throws Exception {
        // deviceEntity.beginTransaction("123");
        // deviceEntity.beginTransaction("abc");
        // install();
        // runApp();
        // keyDown();
        // deviceEntity.waitMillis(20000);
        // screenClick();
        // // sendMessage();
    //OCR屏幕区域 
    //deviceEntity.ocrScreen(OCR_SIMPLIPIED_CHINESE,0,0,0,0);
    
        // log();
        // deviceEntity.endTransaction("123", "456", true);
        // deviceEntity.endTransaction("abc" , "789" ,false);
        // log();
        final boolean con = verifyScreen();
        Log.e("DemoTestCase", "con=" + con);
    }

    protected void testLog() throws MException {
        deviceEntity.log(LOG_LEVEL_VERBOSE, "LOG_LEVEL_VERBOSE");
        deviceEntity.log(CommandConstants.LOG_LEVEL_DEBUG, "LOG_LEVEL_DEBUG");
        deviceEntity.log(CommandConstants.LOG_LEVEL_INFO, "LOG_LEVEL_INFO");
        deviceEntity.log(CommandConstants.LOG_LEVEL_WARN, "LOG_LEVEL_WARN");
        deviceEntity.log(CommandConstants.LOG_LEVEL_ERROR, "LOG_LEVEL_ERROR");
        deviceEntity.log(CommandConstants.LOG_LEVEL_FATAL, "LOG_LEVEL_FATAL");
        deviceEntity.logScreen(CommandConstants.LOG_LEVEL_VERBOSE, "LOG_LEVEL_VERBOSE");
    }

    protected void sendMessage() throws MException {
        final String receivers = "";
        final String content = "";
        deviceEntity.sendMessage(receivers, content);
    }

    protected void screenClick() throws MException {
        deviceEntity.screenClick(170, 320);
    }

    protected void keyDown() throws MException {
        deviceEntity.keyDown(24);
    }

    protected void install() throws MException {
        deviceEntity.install("/sdcard/CNApp/test.apk");
    }

    protected void runApp() throws MException {
        String packageName = "cn.com.ui";
        deviceEntity.runApp(packageName);
    }

    protected void log() throws MException {
        deviceEntity.log(CommandConstants.LOG_LEVEL_DEBUG, "123");
    }

    protected boolean verifyScreen() throws MException {
        final String tempPath = "device-2012-07-02-110609.png";
        return deviceEntity.verifyScreen(0, 0, tempPath, 0, 0, 480, 320, 0, 0);
    }
}
