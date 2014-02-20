/**
 * @(#) Main.java Created on 2012-9-20
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.braver.code;

/**
 * The class <code>Main</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("用法：java -jar codestatistics.jar 文件名");
        System.out.println();
        if (null == args || args.length <= 0) {
            System.out.println("没有输出文件!");
        } else {
            final CodeStatistic statistic = new CodeStatistic(args[0]);
            statistic.work();
            System.out.println(statistic.getStatistics());
        }

    }

}
