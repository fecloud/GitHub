/**
 * @(#) IPFind.java Created on 2012-8-31
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.braver.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class <code>IPFind</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class IPFind {

    private static final String IPREG = "(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})";

    private List<String> finds;

    private StringBuffer text;

    private InputStream in;

    public IPFind(String path) throws FileNotFoundException {
        in = new FileInputStream(path);
    }

    public IPFind(InputStream in) {
        this.in = in;
    }

    public List<String> find() {
        readText();

        final Matcher matcher = Pattern.compile(IPREG).matcher(text);
        final List<String> strings = new ArrayList<String>();
        while (matcher.find()) {
            strings.add(matcher.group());
        }
        return strings;

    }

    public void find(OutputStream out) throws IOException {
        if (null == out)
            throw new NullPointerException("the param out is not null!");
        this.finds = find();
        for (String s : finds) {
            out.write(s.getBytes());
            out.write("\r\n".getBytes());
        }
        out.flush();
    }

    private void readText() {
        text = new FileReader(in).read();
    }

}
