/**
 * 
 */
package com.san.fu.action;

import com.san.fu.Const;
import com.san.fu.Log;
import com.san.fu.bean.User;
import com.san.fu.manger.UserManger;
import com.san.fu.tools.Tools;

/**
 * @author oyf_feng
 * 
 */
public class UserTp extends Thread {

    private boolean flag = true;

    private User user;

    private UserManger manger;

    private UserTpListener listener;

    public UserTp(UserManger manger, UserTpListener listener) {
        this.manger = manger;
        this.listener = listener;
    }

    /**
     * 投票
     * 
     * @return
     */
    public boolean userTp() {
        final String url = Const.USER_TP_ADDRESS + "?id=" + Const.USER_ID + "&n=" + Tools.random() + " ";
        try {
            final String str = Tools.openGetUrl(url, user.getCookies());
            if (null != str) {
                String[] strings = str.split(",");
                if (strings[0].equalsIgnoreCase("Dig")) {
                    Log.log(Log.LOG_LEVEL_DEBUG, "----------------------帐号：" + user.getUsername() + "已经投过票了");
                } else if (strings[0].equalsIgnoreCase("over")) {
                    Log.log(Log.LOG_LEVEL_DEBUG, "----------------------帐号：" + user.getUsername() + "今天已经投完了");
                } else if (strings[0].equalsIgnoreCase("NoData")) {
                    Log.log(Log.LOG_LEVEL_DEBUG, "----------------------参数错误:" + user.getUsername());
                } else {
                    Log.log(Log.LOG_LEVEL_DEBUG, "----------------------帐号：" + user.getUsername() + "投票成功");
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * 执行投票
     */
    private void doTp() {
        Log.log(Log.LOG_LEVEL_DEBUG, "开始投票帐号：" + user.getUsername());
        final boolean con = userTp();
        Log.log(Log.LOG_LEVEL_DEBUG, "线束 投票帐号：" + user.getUsername());
        if (con) {
            Log.log(Log.LOG_LEVEL_DEBUG, "----------------------投票成功:" + user.getUsername());
            user.setTp(true);// 设置投票成功
            if (null != listener)
                listener.onUserTpOK(this);
        } else {
            Log.log(Log.LOG_LEVEL_ERROR, "----------------------投票失败:" + user.getUsername());
            if (null != listener)
                listener.onUserTpFail(this);
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
            tmpUser = manger.getPreTpUser();
            if (null != tmpUser) {
                this.user = tmpUser;
                doTp();
            }
        }
    }

    public User getUser() {
        return user;
    }

    public void stopUserTpThread() {
        flag = false;
    }

    public interface UserTpListener {

        public boolean userTp(UserTp user);

        public void onUserTpOK(UserTp user);

        public void onUserTpFail(UserTp user);

    }

}
