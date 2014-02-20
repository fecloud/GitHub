/**
 * @(#) Test.java Created on 2012-7-20
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.san.fu;

import com.san.fu.action.UserLogin;
import com.san.fu.bean.User;
import com.san.fu.tools.Tools;
import com.san.fu.tools.UserTools;

/**
 * The class <code>Test</code>
 *
 * @author Administrator
 * @version 1.0
 */
public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {
        UserLogin login = new UserLogin(null, null);
        User user = new User();;
        user.setUsername("macbookair");
        user.setUserAgent(Tools.createUserAgent());
        login.user = user;
        login.doLogin();
    }

}
