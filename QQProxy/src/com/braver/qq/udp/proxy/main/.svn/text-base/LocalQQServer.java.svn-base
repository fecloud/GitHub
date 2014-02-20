/**
 * @(#) LocalQQServer.java Created on 2013-8-8
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.qq.udp.proxy.main;

import com.braver.qq.udp.proxy.QQUdpProxy;
import com.braver.qq.udp.proxy.modify.LocalModifyDatagramPacket;

/**
 * The class <code>LocalQQServer</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class LocalQQServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final LocalModifyDatagramPacket modityPacket = new LocalModifyDatagramPacket("192.168.2.9",
				4000, "sz2.tencent.com", 8000);
		QQUdpProxy qqUdpServer = new QQUdpProxy(7000, modityPacket);
		new Thread(qqUdpServer).start();

	}

}
