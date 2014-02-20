/**
 * @(#) UserNameCreate.java Created on 2012-7-12
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.san.fu.action;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.san.fu.Const;
import com.san.fu.Log;
import com.san.fu.tools.Tools;

/**
 * The class <code>UserNameCreate</code> 生成帐号
 * 
 * @author oyf_feng
 * @version 1.0
 */
public class UserNameCreate extends Thread {

    // private boolean b

    private UserNameCreateListener listener;

    private UserNameCreateRandom random = new UserNameCreateRandom();

    /**
     * 模板
     */
    private Set<String> template = new HashSet<String>();

    /**
     * 生成的帐号
     */
    private Set<String> creates = new HashSet<String>();

    public UserNameCreate(UserNameCreateListener listener) {
        super();
        this.listener = listener;
    }

    @Override
    public void run() {

        readTemplate();
        Log.log(Log.LOG_LEVEL_DEBUG, "开始生成帐号");
        create();
        Log.log(Log.LOG_LEVEL_DEBUG, "帐号生成完成");
        if (null != listener) {
            listener.createFinish(this);
        }
    }

    public void readTemplate() {
        try {
            Log.log(Log.LOG_LEVEL_DEBUG, "开始读取帐号生成模板错");
            final List<String> template = Tools.readLines(System.getProperty("user.dir") + Const.USER_NAME_TEMPLATE);
            this.template.addAll(template);
        } catch (IOException e) {
            e.printStackTrace();
            Log.log(Log.LOG_LEVEL_ERROR, "读取帐号生成模板错误!");
        }
    }

    public void create() {
        int strTmpLen = 0;
        int appedLen = 0;
        int appedRand = 0;
        for (String str : template) {
            strTmpLen = str.length();
            appedLen = Const.USER_NAME_LENGTH_MAX - strTmpLen;
            // for (int i = 0; i < 1; i++) {
            for (int i = 0; i < Tools.random(Const.RANDOM_USER); i++) {
                appedRand = Tools.random(appedLen);
                creates.add(str + random.random(appedRand));
            }

        }
    }

    public void display() {
        for (String str : creates) {
            System.out.println(str);
        }
    }

    public Set<String> getCreates() {
        return creates;
    }

    private final class UserNameCreateRandom {

        private char[] chars = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
                'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

        /*
         * public String random() { return random(2); }
         */
        /**
         * 生成帐号后面随机数
         * 
         * @return
         */
        public String random(int num) {
            final StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < num; i++) {
                Random random = new Random();
                int rand = random.nextInt(chars.length);
                buffer.append(chars[rand]);
            }
            return buffer.toString();
        }
    }

    public interface UserNameCreateListener {

        public void createFinish(UserNameCreate create);

    }
}
