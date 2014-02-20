/**
 * @(#) GetInternetIp.java Created on 2012-8-14
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.braver.ip.request.internet;

import com.braver.ip.parser.IPHtmlPaser;
import com.braver.ip.request.AbstractGetIP;
import com.braver.ip.util.HttpRequest;

/**
 * The class <code>GetInternetIp</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class GetInternetIP extends AbstractGetIP {

    private String url;

    private IPHtmlPaser paser;

    public GetInternetIP(String url) {
        super();
        this.url = url;
        this.paser = new IPHtmlPaser();
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.braver.ip.request.GetIP#getIP()
     */
    @Override
    public String getIP() throws Exception {
        final HttpRequest request = new HttpRequest(url);
        final String html = request.request();
        ip = paser.paser(html);
        return ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
