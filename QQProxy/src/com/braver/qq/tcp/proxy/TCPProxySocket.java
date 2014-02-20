/**
 * @(#) TCPProxy.java Created on 2013-8-7
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.qq.tcp.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

import com.braver.qq.tcp.proxy.forward.TCPProxyForward;

/**
 * The class <code>TCPProxySocket</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class TCPProxySocket implements OnReceiveTCP, Runnable {

	private TCPProxyForward proxyForward;

	private Socket socket;

	/**
	 * @param socket
	 */
	public TCPProxySocket(Socket socket) {
		super();
		this.socket = socket;
	}

	/**
	 * @param proxyForward
	 *            the proxyForward to set
	 */
	public void setProxyForward(TCPProxyForward proxyForward) {
		this.proxyForward = proxyForward;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		System.out.println("TCPProxySocket run...");
		try {
			readData();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readData() throws IOException {
		final InputStream in = socket.getInputStream();
		while (socket.isConnected()) {
			int len = 0;
			final byte[] buffer = new byte[256];
			len = in.read(buffer);
			if (len != -1) {
				receive(Arrays.copyOf(buffer, len));
			} else {
				break;
			}
		}
		socket.close();
	}

	private void receive(byte[] buffer) {
		if (null != proxyForward)
			proxyForward.onReceiveTCP(buffer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.qq.tcp.proxy.OnReceiveTCP#onReceiveTCP(byte[])
	 */
	@Override
	public void onReceiveTCP(byte[] buffer) {
		try {
			final OutputStream out = socket.getOutputStream();
			out.write(buffer);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
