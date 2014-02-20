/**
 * 
 */
package com.san.fu.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.NameValuePair;

import com.san.fu.Const;
import com.san.fu.Log;
import com.san.fu.bean.User;
import com.san.fu.manger.UserManger;
import com.san.fu.tools.Tools;

/**
 * @author oyf_feng
 * 
 */
public class UserLogin extends Thread {

    private boolean flag = true;

    public User user;

    private Cookie[] cookies;

    private UserManger manger;

    private UserLoginListener listener;

    public UserLogin(UserManger manger, UserLoginListener listener) {
        this.manger = manger;
        this.listener = listener;
    }

    private boolean login() {
        NameValuePair[] params = { new NameValuePair("useradmin", user.getUsername()),
                new NameValuePair("password", user.getUsername()) };
        try {
            List<Cookie[]> listCookies = new ArrayList<Cookie[]>();
            final String str = Tools.openPostUrl(Const.LOGIN_ADDRESS, params, listCookies, user.getUserAgent());
            if (null != str && str.contains(Const.LOGIN_OK)) {
                if (listCookies.size() > 0) {
                    this.cookies = listCookies.get(0);
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public void doLogin() {
        Log.log(Log.LOG_LEVEL_DEBUG, "开始登录帐号：" + user.getUsername());
        final boolean con = login();
        Log.log(Log.LOG_LEVEL_DEBUG, "结束登录帐号：" + user.getUsername());
        if (con) {
            Log.log(Log.LOG_LEVEL_DEBUG, "----------------------登陆成功帐号：" + user.getUsername());
            user.setLogin(true);// 设置登录成功
            user.setCookies(cookies);
            if (null != listener) {
                listener.onLoginOK(this);
            }
        } else {
            Log.log(Log.LOG_LEVEL_ERROR, "----------------------登陆失败帐号：" + user.getUsername());
            if (null != listener) {
                listener.onLoginFail(this);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        flag = true;
        User tmpUser = null;
        while (flag) {
            tmpUser = manger.getPreLoginUser();
            if (null != tmpUser) {
                this.user = tmpUser;
                doLogin();
            }
        }
    }

    public void stopUserLoginThread() {
        flag = false;
    }

    public User getUser() {
        return user;
    }

    public interface UserLoginListener {

        public boolean login(UserLogin user);

        public void onLoginOK(UserLogin user);

        public void onLoginFail(UserLogin user);

    }
}
