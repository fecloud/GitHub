/**
 * @(#) Util.java Created on 2012-6-11
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.attachment;

import java.io.File;

/**
 * The class <code>Util</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public final class Utils {
    /**
     * get current dir
     * 
     * @return
     */
    public static String getCurrentDir() {
        final String dir = getCurrentPath();
        if (null != dir && dir.contains(File.separator)) {
            return dir.substring(dir.lastIndexOf(File.separator) + 1);
        }
        return null;
    }

    /**
     * get current path
     * 
     * @return
     */
    public static String getCurrentPath() {
        return System.getProperty("user.dir");
    }
}
