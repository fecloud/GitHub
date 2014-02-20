/**
 * @(#) CheckTextUtil.java Created on 2012-8-22
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.widgets.Text;

/**
 * The class <code>CheckTextUtil</code>
 * 
 * @author wuyanlong
 * @version 1.0
 */
public class VerifyTextUtil {
    /**
     * Check Text
     * 
     * @param text
     * @param verifyLevel
     * @return
     */
    public static boolean checkText(Text text, VerifyLevel verifyLevel) {
        switch (verifyLevel) {
        case NOT_NULL:
            return verifyTextNotNull(text);
        case IS_IP:
            return verifyTextIsIp(text);
        default:
            break;
        }
        return false;

    }

    /**
     * Check is Ip.
     * 
     * @param text
     * @return
     */
    private static boolean verifyTextIsIp(Text text) {
        Pattern pattern = Pattern.compile("d+.d+.d+.d+");
        Matcher matcher = pattern.matcher(text.getText().trim());
        if (matcher.find())
            return true;
        return false;
    }

    /**
     * Check null
     * 
     * @param text
     * @return
     */
    private static boolean verifyTextNotNull(Text text) {
        String textString = text.getText().trim();
        if (textString != null && textString.length() != 0)
            return true;
        return false;
    }

    /**
     * The class <code>VerifyLevel</code>
     * 
     * @author wuyanlong
     * @version 1.0
     */
    public enum VerifyLevel {
        NOT_NULL, IS_IP;
    }

}
