/**
 * @(#) ClientMain.java Created on 2013-10-12
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.undpunch.main;

import com.braver.undpunch.client.Punchclient;

/**
 * The class <code>ClientMain</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class ClientMain {

	private Punchclient punchclient;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args == null || args.length < 2) {
			System.err.println("Uage: host port");
			return;
		}

		new ClientMain().start(args[0], args[1]);
	}

	public void start(String host, String port) {
		punchclient = new Punchclient(host, Integer.parseInt(port));
		if (punchclient.start()) {
			System.out.println("连接服务器成功!");
		}
	}

}
