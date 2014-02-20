/**
 * @(#) AbstactUserContrl.java Created on 2012-7-12
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.san.fu.contrl;

import java.util.Set;

import com.san.fu.Const;
import com.san.fu.Log;
import com.san.fu.action.UserLogin;
import com.san.fu.action.UserLogin.UserLoginListener;
import com.san.fu.action.UserNameCreate;
import com.san.fu.action.UserNameCreate.UserNameCreateListener;
import com.san.fu.action.UserReg;
import com.san.fu.action.UserReg.UserRegListener;
import com.san.fu.action.UserTp;
import com.san.fu.action.UserTp.UserTpListener;
import com.san.fu.bean.User;
import com.san.fu.manger.UserManger;
import com.san.fu.tools.Tools;
import com.san.fu.tools.UserTools;

/**
 * The class <code>AbstactUserContrl</code>
 * 
 * @author Administrator
 * @version 1.0
 */
public abstract class AbstactUserContrl implements UserLoginListener, UserNameCreateListener, UserRegListener,
        UserTpListener {

    protected UserManger manger = UserManger.getInstance();

    protected UserTools userTools = UserTools.getInstance();

    /**
     * 初始化
     */
    public void init() {
    }

    public abstract void start(int num);

    public abstract void stop();

    /**
     * (non-Javadoc)
     * 
     * @see com.san.fu.action.UserTp.UserTpListener#userTp(com.san.fu.action.UserTp)
     */
    @Override
    public boolean userTp(UserTp user) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.san.fu.action.UserTp.UserTpListener#onUserTpOK(com.san.fu.action.UserTp)
     */
    @Override
    public void onUserTpOK(UserTp user) {
        for (;;) {
            if (userTools.saveUser(user.getUser())) {
                Log.log(Log.LOG_LEVEL_ERROR, "-----------------------------保存帐号：" + user.getUser().getUsername()
                        + "成功！");
                break;
            }
        }
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.san.fu.action.UserTp.UserTpListener#onUserTpFail(com.san.fu.action.UserTp)
     */
    @Override
    public void onUserTpFail(UserTp user) {
        Tools.actionWait();
        errorExecute(user.getUser());
        manger.addPreTpUser(user.getUser());
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.san.fu.action.UserReg.UserRegListener#register(com.san.fu.action.UserReg)
     */
    @Override
    public boolean register(UserReg user) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.san.fu.action.UserReg.UserRegListener#onUserRegOK(com.san.fu.action.UserReg)
     */
    @Override
    public void onUserRegOK(UserReg user) {
        Tools.actionWait();
        manger.addPreLoginUser(user.getUser());

    }

    /**
     * (non-Javadoc)
     * 
     * @see com.san.fu.action.UserReg.UserRegListener#onUserRegFail(com.san.fu.action.UserReg)
     */
    @Override
    public void onUserRegFail(UserReg user) {
    	Tools.actionWait();
    	errorExecute(user.getUser());
        manger.addPreRegUser(user.getUser());
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.san.fu.action.UserNameCreate.UserNameCreateListener#createFinish(com.san.fu.action.UserNameCreate)
     */
    @Override
    public void createFinish(UserNameCreate create) {
        final Set<String> users = create.getCreates();
        User user = null;
        for (String str : users) {
            user = new User();
            user.setUsername(str);
            user.setUserAgent(Tools.createUserAgent());
            manger.addPreRegUser(user);
        }

    }

    /**
     * (non-Javadoc)
     * 
     * @see com.san.fu.action.UserLogin.UserLoginListener#login(com.san.fu.action.UserLogin)
     */
    @Override
    public boolean login(UserLogin user) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.san.fu.action.UserLogin.UserLoginListener#onLoginOK(com.san.fu.action.UserLogin)
     */
    @Override
    public void onLoginOK(UserLogin user) {
        Tools.actionWait();
        manger.addPreTpUser(user.getUser());
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.san.fu.action.UserLogin.UserLoginListener#onLoginFail(com.san.fu.action.UserLogin)
     */
    @Override
    public void onLoginFail(UserLogin user) {
    	Tools.actionWait();
    	errorExecute(user.getUser());
        manger.addPreLoginUser(user.getUser());
    }
    
    public void errorExecute(User user){
    	if(Const.ERROR_MAX >= user.errorcount){
    		Log.log(Log.LOG_LEVEL_ERROR, "帐号：" + user.getUsername() + "错误次数：" + user.errorcount + "清除帐号");
    		return ;
    	}else {
    		user.errorcount ++;
    	}
    }

}
