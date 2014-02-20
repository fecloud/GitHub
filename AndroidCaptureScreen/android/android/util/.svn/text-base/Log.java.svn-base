/**
 * @(#) Log.java Created on 2012-12-19
 *
 * Copyright (c) 2012 Braver. All Rights Reserved
 */
package android.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The class <code>Log</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class Log {

    public static String getDate(){
           SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ms");
           return format.format(new Date());
    }
    
    /**
     * @param tag
     * @param string
     */
    public static void e(String tag, String string) {
        System.out.println(getDate() + " " + tag + " " + string);
    }

    /**
     * @param tag
     * @param string
     */
    public static void d(String tag, String string) {
        System.out.println(getDate() + " " + tag + " " + string);
    }

    /**
     * @param tag
     * @param string
     * @param e
     */
    public static void e(String tag, String string, InterruptedException e) {
        e(tag, string);
        e.printStackTrace();
    }

    /**
     * @param tag
     * @param string
     * @param e
     */
    public static void e(String tag, String string, IOException e) {
        e(tag, string);
        e.printStackTrace();

    }

}
