/**
 * @(#) UDPProxy.java Created on 2013-8-7
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.qq.udp.proxy;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import com.braver.qq.tools.Tools;

/**
 * The class <code>UDPProxy</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class UDPProxy {

	// 监听哪个端口
	private int accpet;

	private OnReceivePacket onReceivePacket;

	private boolean flag;

	private DatagramSocket socket;

	private byte[] buff = new byte[0xFFFF];

	/**
	 * @param accpet
	 */
	public UDPProxy(int accpet) {
		super();
		this.accpet = accpet;
	}

	public void start() {
		flag = true;
		if (null == socket)
			try {
				socket = new DatagramSocket(accpet);
				DatagramPacket packet = new DatagramPacket(buff, buff.length);
				while (flag) {
					socket.receive(packet);
					if (null != onReceivePacket)
						onReceivePacket.onReceivePacket(packet);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	/**
	 * 发送udp包
	 * 
	 * @param packet
	 * @return
	 */
	public boolean sendPacket(DatagramPacket packet) {
		if (null != socket) {
			System.out.println("UDPProxy send to " + Tools.printDatagramPacket2(packet));
			try {
				socket.send(packet);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return false;
	}

	/**
	 * @return the accpet
	 */
	public int getAccpet() {
		return accpet;
	}

	/**
	 * @param onReceivePacket
	 *            the onReceivePacket to set
	 */
	public void setOnReceivePacket(OnReceivePacket onReceivePacket) {
		this.onReceivePacket = onReceivePacket;
	}

	public interface OnReceivePacket {

		public void onReceivePacket(DatagramPacket packet);

	}
}
