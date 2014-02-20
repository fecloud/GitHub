/**
 * @(#) ServerTest.java Created on 2013-10-11
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.udppunch.test;

import com.braver.undpunch.server.RemoteServer;

/**
 * The class <code>ServerTest</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class ServerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RemoteServer remoteServer = new RemoteServer(8080);
		remoteServer.start();
	}

}
