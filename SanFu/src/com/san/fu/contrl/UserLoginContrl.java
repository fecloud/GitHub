/**
 * @(#) UserLoginContrl.java Created on 2012-7-12
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.san.fu.contrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.san.fu.Log;
import com.san.fu.action.UserLogin;
import com.san.fu.bean.User;
import com.san.fu.tools.Tools;

/**
 * The class <code>UserLoginContrl</code>
 * 
 * @author Administrator
 * @version 1.0
 */
public class UserLoginContrl extends AbstactUserContrl {

    private List<UserLogin> userLogins = new ArrayList<UserLogin>();

    @Override
    public void init() {
        Log.log(Log.LOG_LEVEL_DEBUG, "读取是否有效果的帐号");
        final Set<String> list = userTools.getSaveUserToLogin();
        if (null != list && list.size() > 0) {
            Log.log(Log.LOG_LEVEL_DEBUG, "有效果的帐号" + list.size() + "个");
            User user = null;
            for (String str : list) {
                user = new User();
                user.setUsername(str);
                user.setUserAgent(Tools.createUserAgent());
                manger.addPreLoginUser(user);
            }
        } else {
            Log.log(Log.LOG_LEVEL_DEBUG, "没有效果的帐号");
        }
        super.init();
    }

    /**
     * 启动
     * 
     * @param num
     *            几个线程
     */
    public void start(int num) {
        //init();
        System.err.println("用户登录控制器启动" + num + "个线程");
        UserLogin userLogin = null;
        for (int i = 0; i < num; i++) {
            userLogin = new UserLogin(manger, this);
            userLogins.add(userLogin);
            userLogin.start();
        }
        System.err.println("用户登录控制器启动完成");
    }

    /**
     * 停止
     */
    public void stop() {
        for (UserLogin login : userLogins) {
            login.stopUserLoginThread();
        }
    }

}
