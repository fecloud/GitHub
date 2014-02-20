/**
 * @(#) LineReader.java Created on 2012-9-20
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.braver.code.parse;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * The class <code>LineReader</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class LineReader {

    private LineReaderListeners listeners;

    private InputStream in;

    public LineReader(String path) {
        super();
        try {
            this.in = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public LineReader(InputStream in) {
        super();
        this.in = in;
    }

    public void read() {
        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while (null != (line = reader.readLine())) {
                notifyListeners(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void notifyListeners(String line) {
        if (null != listeners)
            listeners.readingLine(line);
    }

    public LineReaderListeners getListeners() {
        return listeners;
    }

    public void setListeners(LineReaderListeners listeners) {
        this.listeners = listeners;
    }

}
