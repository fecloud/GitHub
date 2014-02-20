package com.aspire.mose.frame.net.protocol.handshake.example;

import java.io.DataInputStream;
import java.io.IOException;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;

/**
 * 处理服务器返回的消息体得临时缓冲存储类
 * 用来给以后的响应或通知消息体来做解码
 * @author liangbo
 *
 * 2011-6-28 下午01:31:26
 *  
 * DeMsgBodyTemp
 *
 */
public class DeHSMsgBodyTemp {

	byte[] a;
    
	/**
	 * 将输入流中的固定长度的数据读取到byte[] a缓冲中，等待具体消息体来解码
	 * @param dataInputStream 输入流
	 * @param size 大小
	 */
	public DeHSMsgBodyTemp(DataInputStream dataInputStream, int size) {
		a = new byte[size];
		try {
			dataInputStream.read(a, 0, size);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
    
	/**
	 * 将临时缓冲解码成具体的消息体
	 * @param msgBody
	 * @throws IOException
	 */
	public MessageLite decode(GeneratedMessageLite msgBody)  {	
		
		
		if (msgBody != null && a!=null) {			
				MessageLite resp;
				try {
					resp = msgBody.getDefaultInstanceForType().toBuilder().mergeFrom(a).buildPartial();
					
					return resp;
				} catch (InvalidProtocolBufferException e) {
					
					e.printStackTrace();
				}
		}
		return null;
	}
}
