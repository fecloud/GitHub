/**
 * @(#) ProxyAccept.java Created on 2013-8-7
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.qq.tcp.proxy;

import java.net.Socket;

import com.braver.qq.tcp.proxy.TCPProxy.OnAcceptClient;
import com.braver.qq.tcp.proxy.forward.InternetForward;
import com.braver.qq.tcp.proxy.forward.LocalForward;
import com.braver.qq.tcp.proxy.forward.TCPProxyForward;

/**
 * The class <code>ProxyAccept</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class ProxyAccept implements OnAcceptClient {

	private String forwardHostname;

	private int forwardPort;

	private ForwardType forwardType;

	/**
	 * @param forwardHostname
	 * @param forwardPort
	 */
	public ProxyAccept(String forwardHostname, int forwardPort, ForwardType forwardType) {
		super();
		this.forwardHostname = forwardHostname;
		this.forwardPort = forwardPort;
		this.forwardType = forwardType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.qq.tcp.proxy.TCPProxy.OnAcceptClient#onAcceptClient(java.net
	 * .Socket)
	 */
	@Override
	public void onAcceptClient(Socket socket) {
		System.out.println("ProxyAccept a client:" + socket.getInetAddress());
		final TCPProxySocket proxySocket = new TCPProxySocket(socket);
		TCPProxyForward proxyForward = null;
		if (forwardType == ForwardType.LOCAL) {
			proxyForward = new LocalForward(proxySocket, forwardHostname, forwardPort);

		} else if (forwardType == ForwardType.INTERNET) {
			proxyForward = new InternetForward(proxySocket, forwardHostname, forwardPort);
		}
		proxySocket.setProxyForward(proxyForward);
		new Thread(proxySocket).start();
	}

}
