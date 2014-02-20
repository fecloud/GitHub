/**
 * @(#) Main.java Created on 2012-8-14
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.braver.ip.test;

import org.apache.commons.mail.EmailException;

import com.braver.ip.GetIPCenter;

/**
 * The class <code>Main</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class Main {

    /**
     * @param args
     * @throws EmailException
     */
    public static void main(String[] args) throws Exception {
        new GetIPCenter().start();
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            
//            @Override
//            public void run() {
//               System.out.println(",,,,,");
//                
//            }
//        }, 0, 2000);
    }

}
