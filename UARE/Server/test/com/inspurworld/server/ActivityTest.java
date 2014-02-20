/**
 * @(#) ActivityTest.java Created on 2012-10-26
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server;

import com.inspurworld.server.restore.matcher.activity.ActivityMatch;
import com.inspurworld.server.restore.matcher.activity.ActivityParam;
import com.inspurworld.server.restore.matcher.activity.ActivityResult;

/**
 * The class <code>ActivityTest</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class ActivityTest {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        final ActivityParam param = new ActivityParam("cn.com.fetion.android.ui.activities.LoginActivity");
        final ActivityMatch match = new ActivityMatch(param);
        final ActivityResult result = match.match();
        System.out.println(result.isMatched());
    }

}
