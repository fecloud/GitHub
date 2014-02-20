/**
 * @(#) VerifyTest.java Created on 2012-10-27
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server;

import com.inspurworld.server.config.Config;
import com.inspurworld.server.restore.matcher.verify.VerifyMatch;
import com.inspurworld.server.restore.matcher.verify.VerifyParam;
import com.inspurworld.server.restore.matcher.verify.VerifyResult;

/**
 * The class <code>VerifyTest</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class VerifyTest {

    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        final String inputPath = Config.MASTER_DIR + "cn.com.fetion.android.ui.activities.LoginActivity_1.jpg";
        final String templatePath = inputPath;
        final VerifyParam param = new VerifyParam(inputPath, 0, 0, 0, 0, templatePath, 0, 0, 0, 0, 20);
        final VerifyMatch match = new VerifyMatch(param);
        final VerifyResult result = match.match();
        System.out.println(result.isMatched());
    }

}
