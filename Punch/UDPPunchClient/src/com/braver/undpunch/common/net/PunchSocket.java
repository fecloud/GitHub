/**
 * @(#) PunchSocket.java Created on 2013-10-12
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.undpunch.common.net;

import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.util.concurrent.LinkedBlockingDeque;

import org.apache.log4j.Logger;

/**
 * The class <code>PunchSocket</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class PunchSocket {

	Logger logger = Logger.getLogger(getClass());

	private ByteChannel channel;

	private LinkedBlockingDeque<ByteBuffer> datas;

	/**
	 * @param channel
	 */
	public PunchSocket(ByteChannel channel) {
		super();
		this.channel = channel;
		datas = new LinkedBlockingDeque<ByteBuffer>();
	}

	public void put(ByteBuffer buffer) {
		final ByteBuffer byteBuffer = ByteBuffer.allocate(buffer.remaining());
		byteBuffer.put(buffer);
		this.datas.add(byteBuffer);
		buffer.flip();
		logger.debug("datas size:" + datas.size());
	}

	/**
	 * @return the channel
	 */
	public ByteChannel getChannel() {
		return channel;
	}

	/**
	 * @param channel
	 *            the channel to set
	 */
	public void setChannel(ByteChannel channel) {
		this.channel = channel;
	}

}
