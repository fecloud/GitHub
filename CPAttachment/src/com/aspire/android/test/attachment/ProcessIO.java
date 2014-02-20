/**
 * @(#) ProcessIO.java Created on 2012-6-11
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.attachment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * The class <code>ProcessIO</code>
 * 
 * @author Administrator
 * @version 1.0
 */
public class ProcessIO {

    private Process process;

    public ProcessIO(String cmd) throws Exception {
        process = Runtime.getRuntime().exec(cmd);
        process.waitFor();
        System.out.println(printOut());
        // process.waitFor();
    }

    /**
     * write command
     * 
     * @param cmd
     */
    public void write(String cmd) {
        if (null != process) {
            try {
                final OutputStream out = process.getOutputStream();
                out.write((cmd + "\r\n").getBytes());
                out.flush();
                process.waitFor();
                System.out.println(printOut());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String printOut() throws IOException {
        // final InputStream in = process.getInputStream();
        // final BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        // String line = null;
        // while (null != (line = reader.readLine())) {
        // System.out.println(line);
        // }
        BufferedReader bufferdReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder(1024);
        char[] buffer = new char[1024];
        int readLength;
        while ((readLength = bufferdReader.read(buffer)) > 0) {
            stringBuilder.append(buffer, 0, readLength);
        }
        return stringBuilder.toString();
    }

}
