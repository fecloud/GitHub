/**
 * @(#) GrabScreenService.java Created on 2012-12-12
 *
 * Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.android.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import android.util.Log;

/**
 * The class <code>ScreenCaptureAccept</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class ScreenCaptureAccept {

	private static final String TAG = "ScreenCaptureAccept";

	private int port; // accept port

	private ServerSocket serverSocket;

	private OnClientConnect clientConnect;

	private boolean flag;

	private void run() {
		try {
			if (null == serverSocket)
				serverSocket = new ServerSocket(port);
			serverSocket.setReuseAddress(true);
			while (flag) {
				if (null != clientConnect) {
					// accept a client
					clientConnect.clientConnect(serverSocket.accept());
				}

			}
		} catch (IOException e) {
			Log.e(TAG, "start error " + e.getMessage());
		}
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * 
	 * Constructor
	 * 
	 * @param port
	 * @param clientConnect
	 */
	public ScreenCaptureAccept(int port, OnClientConnect clientConnect) {
		super();
		this.port = port;
		this.clientConnect = clientConnect;
	}

	public void setClientConnect(OnClientConnect clientConnect) {
		this.clientConnect = clientConnect;
	}

	public boolean start() {
		flag = true;
		this.run();
		return true;
	}

	public boolean stop() {
		flag = false;
		serverSocket = null;
		return true;
	}

	public interface OnClientConnect {

		public void clientConnect(Socket socket);

	}

}
