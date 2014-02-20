package com.san.fu.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.san.fu.Const;
import com.san.fu.bean.User;

public class UserTools {

    private static UserTools userTools;

    private String filePath = System.getProperty("user.dir") + Const.USER_NAME_SUCESS;

    private Object object = new Object();

    private FileOutputStream outputStream;

    private boolean fileExists = false;

    private UserTools() {
        try {
            final File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            fileExists = true;
            outputStream = new FileOutputStream(filePath, true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UserTools getInstance() {
        if (null == userTools) {
            userTools = new UserTools();
        }
        return userTools;
    }

    /**
     * 保存有效帐号
     * 
     * @param user
     * @return
     */
    public boolean saveUser(User user) {
        synchronized (object) {
            final byte[] bs = formatUser(user).getBytes();
            try {
                outputStream.write(bs);
                outputStream.flush();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    /**
     * 取得能登录的帐号,一天只能登录一次
     * 
     * @return
     */
    public Set<String> getSaveUserToLogin() {
        if (fileExists) {
            try {
                final Set<String> list = new HashSet<String>();
                final List<String> strings = Tools.readLines(filePath);
                String[] usenAndDate = null;
                for (String str : strings) {
                    usenAndDate = str.split("-");
                    if (null != usenAndDate && usenAndDate.length == 2) {
                        if (Integer.parseInt(usenAndDate[1]) < Integer.parseInt(Tools.formatDate())) {
                            list.add(usenAndDate[0]);
                        }
                    }
                }
                return list;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static String formatUser(User user) {
        final String date = Tools.formatDate();
        final StringBuffer buffer = new StringBuffer();
        buffer.append(user.getUsername() + "-" + date + "\n");
        return buffer.toString();
    }
}
