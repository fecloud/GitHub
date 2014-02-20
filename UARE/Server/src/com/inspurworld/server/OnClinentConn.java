/**
 * @(#) OnClinentConn.java Created on 2012-10-19
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server;

import java.net.Socket;

/**
 * The class <code>OnClinentConn</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public interface OnClinentConn {

    public void clientConn(Socket socket);

}
