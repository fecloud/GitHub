/**
 * @(#) WebGoHomeMessageInbound.java Created on 2014-1-4
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.gohome.webapp;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;
import org.apache.log4j.Logger;

/**
 * The class <code>WebGoHomeMessageInbound</code>
 * 
 * @author braver
 * @version 1.0
 */
public class WebGoHomeMessageInbound extends MessageInbound {

	Logger logger = Logger.getLogger(WebGoHomeMessageInbound.class);

	private InboundListener inboundListener;

	/**
	 * @param messageListener
	 */
	public WebGoHomeMessageInbound(InboundListener inboundListener, int byteBufferMaxSize,
			int charBufferMaxSize) {
		super();
		this.inboundListener = inboundListener;
		setByteBufferMaxSize(byteBufferMaxSize);
		setCharBufferMaxSize(charBufferMaxSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.catalina.websocket.MessageInbound#onBinaryMessage(java.nio
	 * .ByteBuffer)
	 */
	@Override
	protected void onBinaryMessage(ByteBuffer arg0) throws IOException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.catalina.websocket.MessageInbound#onTextMessage(java.nio.
	 * CharBuffer)
	 */
	@Override
	protected void onTextMessage(CharBuffer arg0) throws IOException {
		if (null != arg0 && null != inboundListener) {
			inboundListener.onTextMessage(arg0.toString());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.catalina.websocket.StreamInbound#onOpen(org.apache.catalina
	 * .websocket.WsOutbound)
	 */
	@Override
	protected void onOpen(WsOutbound outbound) {
		if (null != inboundListener) {
			inboundListener.onOpen();
		}
		super.onOpen(outbound);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.catalina.websocket.StreamInbound#onClose(int)
	 */
	@Override
	protected void onClose(int status) {
		if (null != inboundListener) {
			inboundListener.onClose();
		}
		super.onClose(status);
	}

	/**
	 * 消息监听 The class <code>MessageListener</code>
	 * 
	 * @author braver
	 * @version 1.0
	 */
	public interface InboundListener {

		void onTextMessage(String str);

		void onOpen();

		void onClose();

	}

	/**
	 * 发送
	 * 
	 * @param str
	 */
	public void sendTextMessage(String str) {
		CharBuffer buffer = CharBuffer.wrap(str);
		try {
			getWsOutbound().writeTextMessage(buffer);
		} catch (IOException e) {
			logger.error("sendTextMessage", e);
		}
	}

}
