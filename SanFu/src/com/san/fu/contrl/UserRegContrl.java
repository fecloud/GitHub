/**
 * @(#) UserRegContrl.java Created on 2012-7-12
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.san.fu.contrl;

import java.util.ArrayList;
import java.util.List;

import com.san.fu.action.UserReg;

/**
 * The class <code>UserRegContrl</code>
 * 
 * @author oyf_feng
 * @version 1.0
 */
public class UserRegContrl extends AbstactUserContrl {

    private List<UserReg> userRegs = new ArrayList<UserReg>();

    /**
     * 启动
     * 
     * @param num
     *            几个线程
     */
    public void start(int num) {
        System.err.println("用户注册控制器启动" + num + "个线程");
        UserReg userReg = null;
        for (int i = 0; i < num; i++) {
            userReg = new UserReg(manger, this);
            userRegs.add(userReg);
            userReg.start();
        }
        System.err.println("用户注册控制器启动完成");
    }

    /**
     * 停止
     */
    public void stop() {
        for (UserReg reg : userRegs) {
            reg.stopUserRegThread();
        }
    }

}
