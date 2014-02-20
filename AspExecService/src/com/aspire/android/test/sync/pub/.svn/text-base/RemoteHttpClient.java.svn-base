/**

 * @(#) RemoteHTTPClient.java Created on 2012-5-16

 *

 * Copyright (c) 2012 Aspire. All Rights Reserved

 */
package com.aspire.android.test.sync.pub;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.aspire.android.test.log.RunLogger;
import com.aspire.mgt.ats.tm.sync.IHTTPClient;
import com.aspire.mgt.ats.tm.sync.ResponseProcessor;
import com.aspire.mgt.ats.tm.sync.util.http.Client;

/**
 * 
 * The class <code>RemoteHTTPClient</code>
 * 
 * 
 * 
 * @author gouanming
 * 
 * @version 1.0
 */
public class RemoteHttpClient implements IHTTPClient {

    private RunLogger runLogger = RunLogger.getInstance();
	/**
	 * 连接远程url 
	 */
    public void request(String url, String body, ResponseProcessor parser) {
        ByteArrayOutputStream output = new ByteArrayOutputStream(1024);
        ByteArrayInputStream input = null;
        runLogger.debug(getClass(), "post http body is : " + body);
        boolean connStatus = false;
        try {
            connStatus = Client.requestFromHttp(url, null, body, output);
        } catch (Exception e) {
            runLogger.error(getClass(), "connect http server failed, errmessage is :" + e.getMessage());
        }
        try {
            try {
                if (connStatus) {
                    input = new ByteArrayInputStream(output.toByteArray());
                    output.close();
                    output = null;
                    parser.process(input);
                }else{
                    runLogger.error(getClass(), "connect http server failed");
                }
            } finally {
                if (output != null) {
                    output.flush();
                    output.close();
                    output = null;
                }
                if(input != null){
                    input.close();
                    input= null;
                }
            }
        } catch (Exception e) {
            runLogger.error(getClass(), "parse http response failed, errmessage is :" + e.getMessage());
        }
    }
}
