/**
 * @(#) TCPProxyForward.java Created on 2013-8-7
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.qq.tcp.proxy.forward;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;

import com.braver.qq.tcp.proxy.OnReceiveTCP;
import com.braver.qq.tools.Tools;

/**
 * The class <code>TCPProxyForward</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public abstract class TCPProxyForward implements OnReceiveTCP {

	private String hostname;

	private int port;

	private Socket socket;

	private byte[] buffer;

	private OnReceiveTCP onReceiveTCP;

	private TCPProxyForwardInput forwardInput = new TCPProxyForwardInput();

	/**
	 * @param hostname
	 * @param port
	 * @param socket
	 */
	public TCPProxyForward(OnReceiveTCP onReceiveTCP, String hostname, int port) {
		super();
		this.onReceiveTCP = onReceiveTCP;
		this.hostname = hostname;
		this.port = port;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.qq.tcp.proxy.TCPProxySocket.OnReceiveTCP#onReceiveTCP(byte[])
	 */
	@Override
	public void onReceiveTCP(byte[] buffer) {
		System.out
				.println("TCPProxyForward Receive:" + Tools.printByteArray(buffer, buffer.length));
		this.buffer = buffer;
		try {
			if (null == socket) {
				socket = new Socket();
				socket.connect(new InetSocketAddress(hostname, port));
				new Thread(forwardInput).start();
			}
			send();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void send() throws IOException {
		final OutputStream out = socket.getOutputStream();
		final byte[] bs = maskSendBefore(buffer);
		out.write(bs);
		out.flush();
	}

	/**
	 * 发送到远程服器之前
	 * 
	 * @param buffer
	 */
	protected abstract byte[] maskSendBefore(byte[] buffer);

	/**
	 * 远程服务接收后
	 * 
	 * @param buffer
	 */
	protected abstract byte[] maskReceiveAfter(byte[] buffer);

	private void receive(byte[] buffer) {
		System.out.println("TCPProxyForward remoteReceive:"
				+ Tools.printByteArray(buffer, buffer.length));
		if (null != onReceiveTCP) {
			final byte[] bs = maskReceiveAfter(buffer);
			onReceiveTCP.onReceiveTCP(bs);
		}

	}

	private class TCPProxyForwardInput implements Runnable {

		private boolean flag;

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			flag = true;
			System.out.println("TCPProxyForwardInput run...");
			try {
				readData();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private void readData() throws IOException {
			System.out.println("TCPProxyForwardInput readData...");
			final InputStream in = socket.getInputStream();
			while (flag && socket.isConnected()) {
				int len = 0;
				final byte[] bs = new byte[256];
				len = in.read(bs);
				if (len != -1) {
					receive(Arrays.copyOf(buffer, len));
				} else {
					break;
				}
			}
			socket.close();
		}

	}

}
