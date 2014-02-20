/**
 * @(#) IpHtmlPaser.java Created on 2012-8-14
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.braver.ip.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * The class <code>IpHtmlPaser</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public final class IPHtmlPaser {

    private Logger logger = Logger.getLogger(getClass());

    public static final String IP_REGEX = "((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))";

    private String shtml;

    private String result;

    public IPHtmlPaser() {
    }

    public IPHtmlPaser(String shtml) {
        super();
        this.shtml = shtml;
    }

    public String paser(String shtml) {
        if (null == shtml)
            throw new IllegalArgumentException("the param shtml is not null!");
        logger.debug("IPHtmlPaser paser:" + shtml);
        final Pattern pattern = Pattern.compile(IP_REGEX);
        final Matcher matcher = pattern.matcher(shtml);
        if (matcher.find()) {
            result = matcher.group();
        }
        return result;
    }

    public String paser() {
        return paser(shtml);
    }

    public String getShtml() {
        return shtml;
    }

    public void setShtml(String shtml) {
        this.shtml = shtml;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
