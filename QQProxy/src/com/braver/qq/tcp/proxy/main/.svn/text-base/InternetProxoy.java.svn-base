/**
 * @(#) InternetProxoy.java Created on 2013-8-7
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.qq.tcp.proxy.main;

import com.braver.qq.tcp.proxy.ForwardType;
import com.braver.qq.tcp.proxy.ProxyAccept;
import com.braver.qq.tcp.proxy.TCPProxy;

/**
 * The class <code>InternetProxoy</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class InternetProxoy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		final ProxyAccept accept = new ProxyAccept("tcpconn2.tencent.com", 443,
				ForwardType.INTERNET);

		TCPProxy proxy = new TCPProxy(8000, accept);
		proxy.start();
	}

}
