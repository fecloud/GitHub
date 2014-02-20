package com.san.fu.manger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.san.fu.Log;
import com.san.fu.bean.User;

public class UserManger {

    private static final UserManger USER_MANGER = new UserManger();

    private List<User> reg = Collections.synchronizedList(new ArrayList<User>());

    private List<User> login = Collections.synchronizedList(new ArrayList<User>());

    private List<User> tp = Collections.synchronizedList(new ArrayList<User>());

    private Object regObject = new Object();

    private Object logObject = new Object();

    private Object tpObject = new Object();

    private UserManger() {
    }

    public static UserManger getInstance() {
        return USER_MANGER;
    }

    /**
     * 取得帐号,准备注册
     * 
     * @return
     */
    public User getPreRegUser() {
        synchronized (regObject) {
            if (reg.size() > 0) {
                final User user = reg.get(0);
                reg.remove(0);
                return user;
            } else {
                try {
                    regObject.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
    }

    /**
     * 取得帐号,准备登录
     * 
     * @return
     */
    public User getPreLoginUser() {
        synchronized (logObject) {
            if (login.size() > 0) {
                final User user = login.get(0);
                login.remove(0);
                return user;
            } else {
                try {
                    logObject.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
    }

    public User getPreTpUser() {
        synchronized (tpObject) {
            if (tp.size() > 0) {
                final User user = tp.get(0);
                tp.remove(0);
                return user;
            } else {
                try {
                    tpObject.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
    }

    /**
     * 添加到准备注册队伍
     * 
     * @param user
     */
    public void addPreRegUser(User user) {
        synchronized (regObject) {
            Log.log(Log.LOG_LEVEL_DEBUG, "+++++++++++++++++++++++++++++++++++++++++++++++++++添加一个帐号到准备注册队伍 username:"
                    + user.getUsername());
            reg.add(user);
            regObject.notifyAll();
        }
    }

    /**
     * 添加到准备登录队伍
     * 
     * @param user
     */
    public void addPreLoginUser(User user) {
        synchronized (logObject) {
            Log.log(Log.LOG_LEVEL_DEBUG, "+++++++++++++++++++++++++++++++++++++++++++++++++++添加一个帐号到准备登录队伍 username:"
                    + user.getUsername());
            login.add(user);
            logObject.notifyAll();
        }
    }

    /**
     * 添加到投票投票队伍
     * 
     * @param user
     */
    public void addPreTpUser(User user) {
        synchronized (tpObject) {
            Log.log(Log.LOG_LEVEL_DEBUG, "+++++++++++++++++++++++++++++++++++++++++++++++++++添加一个帐号到准备投票队伍 username:"
                    + user.getUsername());
            tp.add(user);
            tpObject.notifyAll();
        }
    }

    public void removeRegUser(User user) {
        synchronized (regObject) {
            reg.remove(user);
        }
    }

    public void removeLoginUser(User user) {
        synchronized (logObject) {
            login.remove(user);
        }
    }

    public void removeTpUser(User user) {
        synchronized (tpObject) {
            tp.remove(user);
        }
    }

}
