/**
 * @(#) TEst.java Created on 2013-10-11
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.udppunch.test;

import java.io.File;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;

import com.braver.undpunch.protoc.PunchMessages.PunchMessage;

/**
 * The class <code>TEst</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class TEst implements Runnable {

	static ByteBuffer b;

	public static void main(String[] args) throws Exception {
		PunchMessage message = PunchMessage.newBuilder().setId(1).setType("value").build();
		
		
		
		final byte[] data = message.toByteArray();

		File file = new File("d:\\QQ(v2.2.0.3800).ipa");
//		FileChannel channel = FileChann
		FileInputStream inputStream = new FileInputStream(file);
		
		ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
		inputStream.getChannel().read(byteBuffer);
		byteBuffer.flip();
		
		// PunchMessage message1 =
		// PunchMessage.newBuilder().setId(2).setType("2")
		// .setData(ByteString.copyFrom(data)).build();
		//
		// PunchMessage m = PunchMessage.parseFrom(message1.toByteArray());
		// System.out.println(m.getId());
		// System.out.println(m.getType());
		//
		// m = PunchMessage.parseFrom(m.getData().toByteArray());
		//
		// System.out.println(m.getId());
		// System.out.println(m.getType());
		ByteBuffer buffer = ByteBuffer.allocate(byteBuffer.limit() + 4);
		buffer.putInt(byteBuffer.limit());
		buffer.put(byteBuffer);
		buffer.flip();

		TEst est = new TEst();
		est.b = buffer;

		for (int i = 0; i < 1; i++) {
			new Thread(new TEst()).start();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			final SocketChannel channel = SelectorProvider.provider().openSocketChannel();
			channel.socket().connect(new InetSocketAddress("127.0.0.1", 8080));
			channel.configureBlocking(false);
			// Socket socket = new Socket("127.0.0.1", 8080);

			while (true) {
				channel.write(ByteBuffer.wrap(b.array(), b.arrayOffset(),
						b.remaining()));
				System.out.println("write 1");
				Thread.sleep(100);
				// socket.getOutputStream().write(buffer.array(),0,buffer.limit());

			}
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}

	}
}
