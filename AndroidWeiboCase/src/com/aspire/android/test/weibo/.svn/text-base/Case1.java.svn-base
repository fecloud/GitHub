/**
 * @(#) Case1.java Created on 2012-6-25
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.weibo;

import com.aspire.android.test.exception.MException;
import com.aspire.android.test.testcase.AbstractTestCase;

/**
 * The class <code>Case1</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class Case1 extends AbstractTestCase {

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.testcase.AbstractTestCase#doExecute()
     */
    @Override
    protected void doExecute() throws Exception {
        runWeibo();
        inputUserAndPass();
        login();
    }

    protected void runWeibo() throws MException {
        deviceEntity.log(LOG_LEVEL_DEBUG, "准备打开腾讯微博应用");
        deviceEntity.runApp("com.tencent.WBlog");
        deviceEntity.log(LOG_LEVEL_DEBUG, "打开腾讯微博应用成功");
        deviceEntity.waitMillis(5000);
        deviceEntity.logScreen(LOG_LEVEL_DEBUG, "记录当前屏幕");
    }
    
    protected void inputUserAndPass() throws MException {
        deviceEntity.log(LOG_LEVEL_DEBUG, "准备输入帐号");
        deviceEntity.screenClick(125, 138);
        deviceEntity.waitMillis(1000);
        deviceEntity.input("413379493");
        deviceEntity.log(LOG_LEVEL_DEBUG, "输入帐号413379493");
        deviceEntity.waitMillis(1000);
        deviceEntity.log(LOG_LEVEL_DEBUG, "准备输入密码");
        deviceEntity.screenClick(125, 190);
        deviceEntity.input("ouyangfeng7758");
        deviceEntity.log(LOG_LEVEL_DEBUG, "输入密码ouyangfeng7758");
        deviceEntity.waitMillis(1000);
        deviceEntity.log(LOG_LEVEL_DEBUG, "输入完成");
    }
    
    protected void login() throws MException{
        deviceEntity.screenClick(240, 240);
        deviceEntity.log(LOG_LEVEL_DEBUG, "点击登录按钮");
    }

}
