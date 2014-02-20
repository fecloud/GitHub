/**
 * @(#) RemoteModifyDatagramPacket.java Created on 2013-8-8
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.qq.udp.proxy.modify;

import java.net.DatagramPacket;
import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;

/**
 * The class <code>RemoteModifyDatagramPacket</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class RemoteModifyDatagramPacket extends QQModityPacket {

	/**
	 * @param srcServer
	 * @param scrServerPort
	 * @param targetServer
	 * @param targetServerPort
	 */
	public RemoteModifyDatagramPacket(String srcServer, int scrServerPort, String targetServer,
			int targetServerPort) {
		super(srcServer, scrServerPort, targetServer, targetServerPort);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.qq.udp.proxy.modify.QQModityPacket#modifyLocalToRemote(java
	 * .net.DatagramPacket)
	 */
	@Override
	protected DatagramPacket modifyLocalToRemote(DatagramPacket packet) {
		final byte[] buffer = Base64.decodeBase64(Arrays.copyOf(packet.getData(),
				packet.getLength()));
		packet.setData(buffer);
		packet.setSocketAddress(targetAddress);
		return packet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.qq.udp.proxy.modify.QQModityPacket#modifyRemoteToLocal(java
	 * .net.DatagramPacket)
	 */
	@Override
	protected DatagramPacket modifyRemoteToLocal(DatagramPacket packet) {
		final byte[] buffer = Base64.encodeBase64(Arrays.copyOf(packet.getData(),
				packet.getLength()));
		packet.setData(buffer);
		packet.setSocketAddress(srcAddress);
		return packet;
	}

}
