/**
 * @(#) ClientTest.java Created on 2013-10-12
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.udppunch.test;

import java.io.IOException;
import java.nio.ByteBuffer;

import com.braver.undpunch.client.Punchclient;

/**
 * The class <code>ClientTest</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class ClientTest extends Punchclient {

	/**
	 * @param host
	 * @param port
	 */
	public ClientTest(String host, int port) {
		super(host, port);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		final ClientTest clientTest = new ClientTest("127.0.0.1", 8080);
		clientTest.connect();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.undpunch.client.Punchclient#onReceive(java.nio.ByteBuffer)
	 */
	@Override
	protected void onReceive(ByteBuffer buffer) {
		System.out.println("onReceive");
	}

}
