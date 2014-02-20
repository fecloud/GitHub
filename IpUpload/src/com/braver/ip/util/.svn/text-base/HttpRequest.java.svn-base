/**
 * @(#) HttpRequest.java Created on 2012-8-14
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.braver.ip.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

/**
 * The class <code>HttpRequest</code>
 * 
 * @author braver
 * @version 1.0
 */
public class HttpRequest {

    protected Logger logger = Logger.getLogger(getClass());

    private String url;

    private String reponse;

    public HttpRequest(String url) {
        super();
        this.url = url;
    }

    public String request(String url, String encoding) {
        if (null == url)
            throw new IllegalArgumentException("url not null!");
        try {
            this.url = url;
            logger.debug("request url " + url);
            final HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(false);
            conn.connect();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));
            final StringBuffer buffer = new StringBuffer();
            String line = null;
            while (null != (line = reader.readLine())) {
                buffer.append(line);
            }
            reader.close();
            conn.disconnect();
            if (buffer.length() > 0) {
                reponse = buffer.toString();
            } else
                reponse = null;

        } catch (IOException e) {
            logger.error("request error", e);
        }
        logger.debug("return internet ip " + reponse);
        return reponse;
    }

    public String request(String encodeing) {
        this.reponse = request(url, encodeing);
        return reponse;
    }

    public String request() {
        return request(url, "GB2312");
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

}
