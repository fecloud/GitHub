/**
 * @(#) ServerMain.java Created on 2013-10-12
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.undpunch.main;

import com.braver.undpunch.server.RemoteServer;

/**
 * The class <code>ServerMain</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class ServerMain {

	public static void main(String[] args) {
		if (args == null || args.length < 1) {
			System.err.println("Uage: port");
			return;
		}

		final RemoteServer remoteServer = new RemoteServer(Integer.parseInt(args[1]));
		remoteServer.start();
	}

}
