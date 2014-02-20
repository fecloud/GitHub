/**
 * @(#) Case2.java Created on 2012-6-25
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.weibo;

import com.aspire.android.test.exception.MException;
import com.aspire.android.test.testcase.AbstractTestCase;

/**
 * The class <code>Case2</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class Case2 extends AbstractTestCase {

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.testcase.AbstractTestCase#doExecute()
     */
    @Override
    protected void doExecute() throws Exception {
        //sendWBlog();
        exit();
    }

    protected void sendWBlog() throws Exception {
        deviceEntity.log(LOG_LEVEL_DEBUG, "点击写blog按键");
        deviceEntity.screenClick(300, 50);
        deviceEntity.waitMillis(2000);
        
        deviceEntity.screenClick(70, 100);
        deviceEntity.waitMillis(1000);
        deviceEntity.input("微博测试");
        deviceEntity.log(LOG_LEVEL_DEBUG, "编辑内容");
        deviceEntity.waitMillis(1000);
        
        deviceEntity.screenClick(226, 455);
        deviceEntity.waitMillis(1000);
        deviceEntity.log(LOG_LEVEL_DEBUG, "加入表情");
        deviceEntity.screenClick(100, 53);
        deviceEntity.waitMillis(1000);
        
        deviceEntity.screenClick(295, 50);
        deviceEntity.waitMillis(1000);
        deviceEntity.log(LOG_LEVEL_DEBUG, "发送微博");
    }
    
    protected void exit() throws MException {
        deviceEntity.keyDown(82);
    }
    
    
}
