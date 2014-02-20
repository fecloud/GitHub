/**
 * @(#) RemoteQQServer.java Created on 2013-8-8
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.qq.udp.proxy.main;

import com.braver.qq.udp.proxy.QQUdpProxy;
import com.braver.qq.udp.proxy.modify.RemoteModifyDatagramPacket;

/**
 * The class <code>RemoteQQServer</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class RemoteQQServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final RemoteModifyDatagramPacket modityPacket = new RemoteModifyDatagramPacket(
				"113.106.200.122", 8000, "sz2.tencent.com", 8000);
		QQUdpProxy qqUdpServer = new QQUdpProxy(8000, modityPacket);
		new Thread(qqUdpServer).start();

	}

}
