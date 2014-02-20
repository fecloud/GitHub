/**
 * @(#) HttpClient.java Created on 2012-7-20
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspire.common.exception.ExceptionHandler;
import com.aspire.mgt.ats.tm.sync.IHTTPClient;
import com.aspire.mgt.ats.tm.sync.ResponseProcessor;
import com.aspire.mgt.ats.tm.sync.util.http.Client;

/**
 * The class <code>HttpClient</code>
 *
 * @author likai
 * @version 1.0
 */
public class HttpClient implements IHTTPClient {
    /**
     * logger
     */
    protected Logger logger = LoggerFactory.getLogger(HttpClient.class);
    /**
     * 连接远程url 
     */
    public void request(String url, String body, ResponseProcessor parser) {
        ByteArrayOutputStream output = new ByteArrayOutputStream(1024);
        try {
            try {
                if (Client.requestFromHttp(url, null, body, output)) {
                    ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());
                    output.close();
                    output = null;
                    try {
                        parser.process(input);
                    } finally {
                        input.close();
                    }
                }else{
                    logger.error("http connect failed");
                }
            } finally {
                if (output != null) {
                    output.close();
                }
            }
        } catch (Exception e) {
           ExceptionHandler.handle(e,"while httpclient to do something ");
        }
    }

}
