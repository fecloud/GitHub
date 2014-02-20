/**
 * @(#) TT.java Created on 2013-8-7
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.qq;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * The class <code>TT</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class TT {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("121.199.16.114", 8000);
		final InputStream in = socket.getInputStream();
		final OutputStream out = socket.getOutputStream();
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					int i = -1;
					while ((i = in.read()) != -1) {
						System.out.println(i);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
						out.write(new byte[] { 2 });
						out.flush();
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		}).start();
	}
}
