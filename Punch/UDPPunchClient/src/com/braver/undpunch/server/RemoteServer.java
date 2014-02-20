/**
 * @(#) RemoteServer.java Created on 2013-10-11
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.undpunch.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

import com.braver.undpunch.common.net.PunchSocket;
import com.braver.undpunch.common.net.ServerAccept;
import com.braver.undpunch.server.worker.ServerWorker;

/**
 * The class <code>RemoteServer</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class RemoteServer extends ServerAccept {

	private ClientCount clientCount = new ClientCount();

	private ByteBuffer buffer = ByteBuffer.allocate(1024);

	private ServerWorker serverWorker;

	private ClientContainer container = new ClientContainer();

	/**
	 * @param port
	 * @param clientConnect
	 */
	public RemoteServer(int port) {
		super(port);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.undpunch.common.net.ServerAccept#start()
	 */
	@Override
	public boolean start() {
		if (null == serverWorker) {
			serverWorker = new ServerWorker(container);
			serverWorker.start();
		}
		return super.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.undpunch.common.net.ServerAccept#onClientReadable(java.nio
	 * .channels.SelectionKey)
	 */
	@Override
	protected void onClientReadable(SelectionKey key) {
		if (null == key.attachment())
			return;

		final PunchSocket punchSocket = (PunchSocket) key.attachment();
		ByteBuffer buffer;
		try {
			buffer = read(punchSocket.getChannel());
			if (buffer.remaining() == 0)
				return;
			// 说明读到数据了
			punchSocket.put(buffer);
		} catch (IOException e) {
			e.printStackTrace();
			container.removeTask(punchSocket);
			key.cancel();

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.undpunch.common.net.ServerAccept#onClientConnected(java.nio
	 * .channels.SocketChannel)
	 */
	@Override
	public Object onClientConnected(SocketChannel socketChannel) {
		clientCount.add();
		final PunchSocket punchSocket = new PunchSocket(socketChannel);
		container.addTask(punchSocket);
		return punchSocket;
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
