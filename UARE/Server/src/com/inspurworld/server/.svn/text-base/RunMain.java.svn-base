/**
 * @(#) RunMain.java Created on 2012-10-19
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server;

/**
 * The class <code>RunMain</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class RunMain {

    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0)
            port = Integer.parseInt(args[0]);
        final MainServer server = new MainServer();
        server.start(port);
    }
}
