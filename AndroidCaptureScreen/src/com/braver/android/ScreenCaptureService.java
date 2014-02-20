/**
 * @(#) ScreenCaptureService.java Created on 2012-12-18
 *
 * Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.android;

import java.net.Socket;

import com.braver.android.net.ExecuteClient;
import com.braver.android.net.ScreenCaptureAccept;
import com.braver.android.net.ScreenCaptureAccept.OnClientConnect;
import com.braver.android.screen.capture.IScreenCapture;
import com.braver.android.screen.capture.ScreenCaptureImpl;

/**
 * The class <code>ScreenCaptureService</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class ScreenCaptureService implements OnClientConnect {

	private ScreenCaptureAccept accept;

	private ClientCount count = new ClientCount();

	private IScreenCapture capture = new ScreenCaptureImpl();

	public void start() {
		if (null == accept) {
			accept = new ScreenCaptureAccept(Config.port, this);
		}
		accept.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.android.net.ScreenCaptureAccept.OnClientConnect#clientConnect
	 * (java.net.Socket)
	 */
	@Override
	public void clientConnect(Socket socket) {
		new ExecuteClient(socket, count, capture).start();
	}

}
