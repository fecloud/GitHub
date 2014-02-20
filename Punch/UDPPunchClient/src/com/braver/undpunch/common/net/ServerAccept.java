/**
 * @(#) ServerAccept.java Created on 2013-10-11
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.undpunch.common.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import org.apache.log4j.Logger;

/**
 * The class <code>ServerAccept</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public abstract class ServerAccept implements Runnable {

	Logger logger = Logger.getLogger(getClass());

	private String hostname;

	private int port; // accept port

	private ServerSocketChannel serverSocketChannel;

	private Selector selector;

	private boolean flag;

	private Thread acceptThread;

	/**
	 * @param hostname
	 * @param port
	 */
	protected ServerAccept(int port) {
		this(null, port);
	}

	/**
	 * @param hostname
	 * @param port
	 */
	protected ServerAccept(String hostname, int port) {
		super();
		this.hostname = hostname;
		this.port = port;
	}

	public void run() {
		try {
			accept();
		} catch (IOException e) {
			logger.error("", e);
		}
	}

	private void accept() throws IOException {

		serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);

		if (null == hostname) {
			serverSocketChannel.socket().bind(new InetSocketAddress(port));
			logger.info("server start sucess ! port:" + port);
		} else {
			serverSocketChannel.socket().bind(new InetSocketAddress(hostname, port));
			logger.info("server start sucess ! accept :" + hostname + ":" + port);
		}

		selector = Selector.open();
		serverSocketChannel.register(selector, serverSocketChannel.validOps());

		SelectionKey selectionKey = null;
		Iterator<SelectionKey> iterator = null;
		while (flag) {
			if (selector.select() == 0)
				continue;
			iterator = selector.selectedKeys().iterator();

			while (iterator.hasNext()) {
				selectionKey = iterator.next();

				if (!selectionKey.isValid())
					continue;

				if (selectionKey.isAcceptable()) {
					acceptable();
				} else if (selectionKey.isReadable()) {
					readable(selectionKey);
				}
				iterator.remove();
			}
		}

	}

	protected void readable(SelectionKey selectionKey) {
		onClientReadable(selectionKey);
	}

	protected abstract void onClientReadable(SelectionKey key);

	/**
	 * 客端连接事件
	 * 
	 * @throws IOException
	 */
	protected void acceptable() throws IOException {
		final SocketChannel socketChannel = serverSocketChannel.accept();
		socketChannel.configureBlocking(false);
		socketChannel.register(selector, SelectionKey.OP_READ, onClientConnected(socketChannel));
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public abstract Object onClientConnected(SocketChannel socketChannel);

	public boolean start() {
		flag = true;
		if (null == acceptThread) {
			acceptThread = new Thread(this);
			acceptThread.start();
		}
		return true;
	}

	public boolean stop() {
		flag = false;
		try {
			serverSocketChannel.close();
		} catch (IOException e) {
			logger.error("", e);
		}
		serverSocketChannel = null;
		return true;
	}

}
