/**
 * @(#) FileReader.java Created on 2012-8-31
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.braver.tools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * The class <code>FileReader</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class FileReader {

    private StringBuffer stringBuffer;

    private InputStream in;

    public FileReader(InputStream in) {
        super();
        this.in = in;
        stringBuffer = new StringBuffer();
    }

    public StringBuffer read() {
        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line = null;
            while (null != (line = reader.readLine())) {
                stringBuffer.append(line).append("\r\n");
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stringBuffer;
    }

}
