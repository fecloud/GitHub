/**
 * @(#) LocalProxy.java Created on 2013-8-7
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.qq.tcp.proxy.main;

import com.braver.qq.tcp.proxy.ForwardType;
import com.braver.qq.tcp.proxy.ProxyAccept;
import com.braver.qq.tcp.proxy.TCPProxy;

/**
 * The class <code>LocalProxy</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class LocalProxy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		final ProxyAccept accept = new ProxyAccept("tcpconn2.tencent.com", 8000, ForwardType.LOCAL);
		// final ProxyAccept accept = new ProxyAccept("127.0.0.1", 9000,
		// ForwardType.LOCAL);

		TCPProxy proxy = new TCPProxy(4430, accept);
		proxy.start();
	}

}
