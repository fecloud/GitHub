/**
 * @(#) TCPProxy.java Created on 2013-8-7
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.qq.tcp.proxy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The class <code>TCPProxy</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class TCPProxy {

	// 监听哪个端口
	private int accpet;

	private boolean flag;

	private ServerSocket serverSocket;

	private OnAcceptClient acceptClient;

	/**
	 * @param accpet
	 */
	public TCPProxy(int accpet, String forwardHostname, int forwardPort) {
		super();
		this.accpet = accpet;
	}

	/**
	 * @param accpet
	 * @param acceptClient
	 */
	public TCPProxy(int accpet, OnAcceptClient acceptClient) {
		super();
		this.accpet = accpet;
		this.acceptClient = acceptClient;
	}

	public void start() {
		flag = true;

		if (null == serverSocket) {
			try {
				serverSocket = new ServerSocket(accpet);
				System.out.println("TCPProxy start accpt:" + accpet);
				Socket socket = null;
				while (flag && (null != (socket = serverSocket.accept()))) {
					if (null != acceptClient)
						acceptClient.onAcceptClient(socket);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public interface OnAcceptClient {

		public void onAcceptClient(Socket socket);

	}

}
