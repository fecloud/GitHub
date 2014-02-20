/**
 * @(#) ShellAdb.java Created on 2012-8-10
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * The class <code>ShellAdb</code>
 * 
 * @author Administrator
 * @version 1.0
 */
public class ShellAdb {
    public static void execCommand(String command) throws IOException {

        // start the ls command running
        // String[] args = new String[]{"sh", "-c", command};
        Runtime runtime = Runtime.getRuntime();
        Process proc = runtime.exec(command); // 这句话就是shell与高级语言间的调用
                                              // 如果有参数的话可以用另外一个被重载的exec方法

        // 实际上这样执行时启动了一个子进程,它没有父进程的控制台
        // 也就看不到输出,所以我们需要用输出流来得到shell执行后的输出
        InputStream inputstream = proc.getInputStream();
        InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
        BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

        // read the ls output

        String line = "";
        StringBuilder sb = new StringBuilder(line);
        while ((line = bufferedreader.readLine()) != null) {
            // System.out.println(line);
            sb.append(line);
            sb.append('\n');
        }

        // 使用exec执行不会等执行成功以后才返回,它会立即返回
        // 所以在某些情况下是很要命的(比如复制文件的时候)
        // 使用wairFor()可以等待命令执行完成以后才返回
        try {
            if (proc.waitFor() != 0) {
                System.err.println("exit value = " + proc.exitValue());
            }
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }

    /*
     * args[0] : shell 命令 如"ls" 或"ls -1"; args[1] : 命令执行路径 如"/" ;
     */
    public static String execute(String[] cmmand, String directory) throws IOException {
        String result = "";
        try {
            ProcessBuilder builder = new ProcessBuilder(cmmand);

            if (directory != null)
                builder.directory(new File(directory));
            builder.redirectErrorStream(true);
            Process process = builder.start();

            // 得到命令执行后的结果
            InputStream is = process.getInputStream();
            byte[] buffer = new byte[1024];
            while (is.read(buffer) != -1) {
                result = result + new String(buffer);
            }
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
