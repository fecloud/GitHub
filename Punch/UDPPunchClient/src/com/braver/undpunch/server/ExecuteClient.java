/**
 * @(#) ExecuteClient.java Created on 2013-10-11
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.undpunch.server;

import org.apache.log4j.Logger;

import com.braver.undpunch.common.net.PunchSocket;

/**
 * The class <code>ExecuteClient</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class ExecuteClient {

	Logger logger = Logger.getLogger(getClass());

	private PunchSocket punchSocket;

	/**
	 * @param logger
	 * @param punchSocket
	 */
	public ExecuteClient(PunchSocket punchSocket) {
		super();
		this.punchSocket = punchSocket;
	}

}
