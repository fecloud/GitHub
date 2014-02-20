/**
 * @(#) QQModityPacket.java Created on 2013-8-7
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.qq.udp.proxy.modify;

import java.net.DatagramPacket;
import java.net.InetSocketAddress;

/**
 * The class <code>QQModityPacket</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public abstract class QQModityPacket implements ModifyDatagramPacket {

	protected InetSocketAddress targetAddress, srcAddress;

	/**
	 * @param remoteQQServer
	 * @param remoteQQServerPort
	 */
	public QQModityPacket(String srcServer, int scrServerPort, String targetServer,
			int targetServerPort) {
		super();
		this.srcAddress = new InetSocketAddress(srcServer, scrServerPort);
		this.targetAddress = new InetSocketAddress(targetServer, targetServerPort);
	}

	/**
	 * 修改远程服务器发来的包,然后送给本地
	 * 
	 * @param packet
	 * @return
	 */
	protected abstract DatagramPacket modifyLocalToRemote(DatagramPacket packet);

	/**
	 * 修改远程服务器发来的包,然后送给本地
	 * 
	 * @param packet
	 * @return
	 */
	protected abstract DatagramPacket modifyRemoteToLocal(DatagramPacket packet);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.qq.udp.proxy.modify.ModifyDatagramPacket#modifyDatagramPacket
	 * (java.net.DatagramPacket)
	 */
	@Override
	public DatagramPacket modifyDatagramPacket(DatagramPacket packet) {
		if (isRemoteServerPacket(packet)) {
			return modifyRemoteToLocal(packet);
		}
		return modifyLocalToRemote(packet);
	}

	/**
	 * 判断是不理远程服务器发送过的包
	 * 
	 * @param packet
	 * @return
	 */
	protected boolean isRemoteServerPacket(DatagramPacket packet) {
		if (packet.getSocketAddress().toString().equals(targetAddress.toString())) {
			return true;
		}
		return false;
	}

}
