/**
 * @(#) UserNameCreateContrl.java Created on 2012-7-12
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.san.fu.contrl;

import com.san.fu.action.UserNameCreate;

/**
 * The class <code>UserNameCreateContrl</code>
 * 
 * @author Administrator
 * @version 1.0
 */
public class UserNameCreateContrl extends AbstactUserContrl {

    private UserNameCreate create;

    /**
     * (non-Javadoc)
     * 
     * @see com.san.fu.contrl.AbstactUserContrl#start(int)
     */
    @Override
    public void start(int num) {
        System.err.println("用户帐号生成控制器启动" + num + "个线程");
        create = new UserNameCreate(this);
        create.start();
        System.err.println("用户帐号生成控制器完成");
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.san.fu.contrl.AbstactUserContrl#stop()
     */
    @Override
    public void stop() {

    }

}
