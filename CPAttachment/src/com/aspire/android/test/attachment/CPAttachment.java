package com.aspire.android.test.attachment;

import java.io.File;
import java.io.IOException;

/**
 * @(#) CPAttachment.java Created on 2012-6-11
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */

/**
 * The class <code>CPAttachment</code>
 * 
 * @author Administrator
 * @version 1.0
 */
public final class CPAttachment {

    /**
     * copy attachment to android mobile sdcard
     * 
     * @throws IOException
     */
    public static void copyAttachmentToSdcard() throws Exception {
        final String sdcard_path = SysProperties.getProperty("android.sdcard.path");
        final String soure_path = SysProperties.getProperty("android.copy.directory");
        if (null != sdcard_path && null != soure_path) {
            final String cmd = "adb push " + Utils.getCurrentPath() + File.separator + soure_path + " " + sdcard_path
                    + Utils.getCurrentDir() + "/" + soure_path;
            new ProcessIO(cmd);
        }
    }

}
