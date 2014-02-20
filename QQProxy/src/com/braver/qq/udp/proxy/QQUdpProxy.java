/**
 * @(#) QQUdpServer.java Created on 2013-8-7
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.qq.udp.proxy;

import java.net.DatagramPacket;

import com.braver.qq.tools.Tools;
import com.braver.qq.udp.proxy.UDPProxy.OnReceivePacket;
import com.braver.qq.udp.proxy.modify.ModifyDatagramPacket;

/**
 * The class <code>QQUdpServer</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class QQUdpProxy extends UDPProxy implements Runnable, OnReceivePacket {

	private ModifyDatagramPacket modityPacket;

	/**
	 * @param accpet
	 * @param modityPacket
	 */
	public QQUdpProxy(int accpet, ModifyDatagramPacket modityPacket) {
		super(accpet);
		this.modityPacket = modityPacket;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.qq.udp.proxy.UDPProxy.OnReceivePacket#onReceivePacket(java
	 * .net.DatagramPacket)
	 */
	@Override
	public void onReceivePacket(DatagramPacket packet) {
		System.out.println("QQUdpProxy onReceivePacket " + Tools.printDatagramPacket2(packet));
		DatagramPacket datagramPacket = null;
		if (null != modityPacket) {
			datagramPacket = modityPacket.modifyDatagramPacket(packet);
			sendPacket(datagramPacket);
		} else {
			throw new RuntimeException("modityPacket is null!");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		System.out.println("QQUdpServer start port:" + getAccpet());
		setOnReceivePacket(this);
		start();
	}

}
