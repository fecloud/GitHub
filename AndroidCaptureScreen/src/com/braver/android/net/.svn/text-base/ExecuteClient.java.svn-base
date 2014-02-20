/**
 * @(#) ExecuteClientThread.java Created on 2012-12-12
 *
 * Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.android.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import android.util.Log;

import com.braver.android.ClientCount;
import com.braver.android.ValueOf;
import com.braver.android.screen.capture.IScreenCapture;

/**
 * The class <code>ExecuteClientThread</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class ExecuteClient extends Thread {

	private static final String TAG = "ExecuteClient";

	private Socket socket;

	private ClientCount clientCount;

	private IScreenCapture capture;

	public ExecuteClient(Socket socket, ClientCount clientCount,
			IScreenCapture capture) {
		this.socket = socket;
		this.clientCount = clientCount;
		this.capture = capture;
	}

	public void run() {
		clientCount.add();
		Log.d(TAG,
				"accept a client ip:"
						+ socket.getInetAddress().getHostAddress() + " port:"
						+ socket.getPort() + " client num " + clientCount.get());
		// Log.d(TAG, "running start");
		try {
			execute();
		} catch (IOException e) {
			Log.e(TAG, "running error 1", e);
			try {
				socket.close();
			} catch (IOException e1) {
				Log.e(TAG, "running error 2", e1);
			}
		}
		clientCount.delete();
		// Log.d(TAG, "running end");
		Log.d(TAG, "disconnect a client , client num " + clientCount.get());
		socket = null;
	}

	/**
	 * execute client socket connection
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void execute() throws IOException {
		final InputStream in = socket.getInputStream();

		final byte[] bs = new byte[2];
		int len = 0;
		while (true) {
			len = in.read(bs);
			if (len == 2 && validateMsg(bs)) {
				onScreenCapture();
			} else {
				break;
			}
		}
	}

	/**
	 * screen capture
	 * 
	 * @throws IOException
	 */
	protected void onScreenCapture() throws IOException {
		final OutputStream out = socket.getOutputStream();
		final byte[] arrary = capture.capture();
		out.write(ValueOf.intToByte(arrary.length));// write body length
		// out.write(1);
		out.write(arrary);
		out.flush();
		// Log.d(TAG, "write to client");
	}

	/**
	 * validate two bytes ,[83, 67] string is SC ,mean screencapture
	 * 
	 * @param bs
	 * @return
	 */
	public static boolean validateMsg(byte[] bs) {
		if (bs[0] == 83 && bs[1] == 67)
			return true;
		return false;
	}
}
