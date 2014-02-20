/**
 * @(#) UserTpContrl.java Created on 2012-7-12
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.san.fu.contrl;

import java.util.ArrayList;
import java.util.List;

import com.san.fu.action.UserTp;

/**
 * The class <code>UserTpContrl</code>
 * 
 * @author Administrator
 * @version 1.0
 */
public class UserTpContrl extends AbstactUserContrl {

    private List<UserTp> userTps = new ArrayList<UserTp>();

    /**
     * (non-Javadoc)
     * 
     * @see com.san.fu.contrl.AbstactUserContrl#start(int)
     */
    @Override
    public void start(int num) {
        System.err.println("用户投票控制器启动" + num + "个线程");
        UserTp userTp = null;
        for (int i = 0; i < num; i++) {
            userTp = new UserTp(manger, this);
            userTps.add(userTp);
            userTp.start();
        }
        System.err.println("用户投票控制器启动完成");
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.san.fu.contrl.AbstactUserContrl#stop()
     */
    @Override
    public void stop() {
        for (UserTp tp : userTps) {
            tp.stopUserTpThread();
        }

    }
}
