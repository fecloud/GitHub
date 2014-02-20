/**
 * @(#) AppHelper.java Created on 2012-9-1
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.braver.tools.help;

import java.io.IOException;
import java.io.OutputStream;

import com.braver.tools.FileReader;

/**
 * The class <code>AppHelper</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class AppHelper {

    /**
     * 打印程序帮助信息
     * 
     * @param out
     */
    public static void printHelp(OutputStream out) {

        try {
            final StringBuffer buffer = new FileReader(AppHelper.class.getResourceAsStream("help")).read();
            out.write(buffer.toString().getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
