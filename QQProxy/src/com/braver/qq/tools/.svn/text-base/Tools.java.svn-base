/**
 * @(#) Tools.java Created on 2013-8-6
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.qq.tools;

import java.net.DatagramPacket;

/**
 * The class <code>Tools</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class Tools {
	public static final String printByteArray(byte[] bs, int len) {
		if (null == bs || bs.length < len)
			return null;
		else {
			final StringBuilder builder = new StringBuilder();
			builder.append("[ ");
			for (int i = 0; i < len; i++) {
				if (bs[i] > -1 && bs[i] < 0xF) {
					builder.append("0").append(Integer.toHexString(0xFF & bs[i]));
				} else {
					builder.append(Integer.toHexString(0xFF & bs[i]));
				}
				// if (i == 6) {
				// builder.append(" ");
				// }
				if (i == 16)
					builder.append(" ");

				builder.append(" ");
			}
			builder.append(" ] ");
			return builder.toString().toUpperCase();
		}
	}

	public static final String printDatagramPacket2(DatagramPacket packet) {
		if (null == packet)
			return null;
		else {
			final StringBuilder builder = new StringBuilder();
			builder.append("addrss:" + packet.getSocketAddress() + " length:").append(
					packet.getLength());
			return builder.toString();
		}
	}

	public static final String printDatagramPacket(DatagramPacket packet) {
		if (null == packet)
			return null;
		else {
			final StringBuilder builder = new StringBuilder();
			builder.append("addrss:" + packet.getSocketAddress() + " data:").append(
					printByteArray(packet.getData(), packet.getLength()));
			return builder.toString();
		}
	}
}
