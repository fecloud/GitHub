/**
 * @(#) ClientCount.java Created on 2013-10-11
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.undpunch.server;

/**
 * The class <code>ClientCount</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class ClientCount {

	private volatile int clientCount;

	public synchronized int add() {
		clientCount++;
		return clientCount;
	}

	public synchronized int delete() {
		clientCount--;
		return clientCount;
	}

	public synchronized int get() {
		return clientCount;
	}
}
