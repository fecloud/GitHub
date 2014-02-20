/**
 * @(#) LocalForward.java Created on 2013-8-7
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.qq.tcp.proxy.forward;

import org.apache.commons.codec.binary.Base64;

import com.braver.qq.tcp.proxy.OnReceiveTCP;

/**
 * The class <code>LocalForward</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class LocalForward extends TCPProxyForward {

	/**
	 * @param onReceiveTCP
	 * @param hostname
	 * @param port
	 */
	public LocalForward(OnReceiveTCP onReceiveTCP, String hostname, int port) {
		super(onReceiveTCP, hostname, port);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.qq.tcp.proxy.forward.TCPProxyForward#maskSendBefore(byte[])
	 */
	@Override
	protected byte[] maskSendBefore(byte[] buffer) {
		return buffer;//Base64.encodeBase64(buffer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.qq.tcp.proxy.forward.TCPProxyForward#maskReceiveAfter(byte[])
	 */
	@Override
	protected byte[] maskReceiveAfter(byte[] buffer) {
		return buffer;//Base64.decodeBase64(buffer);
	}

}
