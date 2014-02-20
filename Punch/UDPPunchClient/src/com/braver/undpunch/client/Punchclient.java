/**
 * @(#) Punchclient.java Created on 2013-10-12
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.undpunch.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.logging.Logger;

/**
 * The class <code>Punchclient</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class Punchclient {

	private String host;

	private int port;

	private SocketChannel socketChannel;

	private SelectorThread selectorThread;

	private Selector selector;

	/**
	 * @param host
	 * @param port
	 */
	public Punchclient(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}

	public void connect() throws IOException {
		if (socketChannel == null) {
			socketChannel = SelectorProvider.provider().openSocketChannel();
			socketChannel.socket().connect(new InetSocketAddress(host, port));
			socketChannel.configureBlocking(false);
			selector = Selector.open();
			socketChannel.register(selector, socketChannel.validOps());

			if (null == selectorThread)
				selectorThread = new SelectorThread();
			selectorThread.start();
		}
	}

	private class SelectorThread extends Thread {

		private boolean flag;

		private ByteBuffer buffer = ByteBuffer.allocate(1024);

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			flag = true;
			try {
				Iterator<SelectionKey> iterator = null;
				SelectionKey key = null;
				while (flag) {

					if (selector.select() == 0)
						continue;
					iterator = selector.keys().iterator();
					while (iterator.hasNext()) {
						key = iterator.next();
						if (key.isValid()) {

							if (key.isReadable()) {
								final ByteBuffer b = read((ByteChannel) key.channel());
								if (b.hasRemaining())
									onReceive(buffer);
							}

						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private ByteBuffer read(ByteChannel channel) throws IOException {
			final ByteBuffer buffer = takeBuffer();
			if (channel.isOpen()) {
				channel.read(buffer);
			}
			buffer.flip();
			return buffer;
		}

		public ByteBuffer takeBuffer() {
			buffer.clear();
			return buffer;
		}
	}

	public void write(ByteBuffer buffer) throws IOException {
		if (null != socketChannel) {
			socketChannel.write(buffer);
		}
	}

	protected void onReceive(ByteBuffer buffer) {

	}

	/**
	 * 
	 */
	public boolean start() {
		try {
			this.connect();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
