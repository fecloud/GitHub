package com.san.fu.action;

import org.apache.commons.httpclient.NameValuePair;

import com.san.fu.Const;
import com.san.fu.Log;
import com.san.fu.bean.User;
import com.san.fu.manger.UserManger;
import com.san.fu.tools.Tools;

public class UserReg extends Thread {

    private boolean flag = true;

    private User user;

    private UserManger manger;

    private UserRegListener listener;

    public UserReg(UserManger manger, UserRegListener listener) {
        this.manger = manger;
        this.listener = listener;
    }

    /**
     * 注册
     * 
     * @return
     */
    public boolean register() {
        Log.log(Log.LOG_LEVEL_DEBUG, "开始注册帐号:" + user.getUsername());
        NameValuePair params[] = { new NameValuePair("useradmin", user.getUsername()),
                new NameValuePair("userpassword", user.getUsername()),
                new NameValuePair("userpassword2", user.getUsername()),
                new NameValuePair("email", user.getUsername() + Tools.createmil()), new NameValuePair("sh", "1") };
        try {
            final String str = Tools.openPostUrl(Const.REG_ADDRESS, params, user.getUserAgent());
            if (null != str && str.contains(Const.REGIST_OK)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public void doRegister() {
        final boolean con = register();
        if (con) {
            Log.log(Log.LOG_LEVEL_DEBUG, "----------------------注册成功帐号:" + user.getUsername());
            user.setReg(true);// 设置为成功
            if (null != listener) {
                listener.onUserRegOK(this);
            }
        } else {
            Log.log(Log.LOG_LEVEL_ERROR, "----------------------注册失败帐号:" + user.getUsername());
            if (null != listener) {
                listener.onUserRegFail(this);
            }
        }
    }

    @Override
    public void run() {
        User tmpUser = null;
        while (flag) {
            tmpUser = manger.getPreRegUser();
            if (null != tmpUser) {
                this.user = tmpUser;
                doRegister();
            }
        }
    }

    public void stopUserRegThread() {
        flag = false;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public interface UserRegListener {

        public boolean register(UserReg user);

        public void onUserRegOK(UserReg user);

        public void onUserRegFail(UserReg user);
    }

}
