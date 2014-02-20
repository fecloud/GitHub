package com.aspire.mose.frame.message.bean;

import java.io.DataInputStream;
import java.io.IOException;

import com.aspire.mose.frame.net.protocol.IDataProtocolObject;

/**
 * 消息体缓冲，对于部分具体消息体，框架不负责解析，框架只解析消息头和消息，消息体就被缓冲在这里
 * @author liangbo
 *
 * 2011-7-26 下午03:42:47
 *  
 * IBodyCatch
 *
 */
public interface IBodyCatch {
	
	/**
	 * 写入缓冲，从流中将具体的消息体内容读入缓冲
	 * @param dataInputStream
	 * @param size
	 */
	public void doCatch(DataInputStream dataInputStream, int size);
	
	/**
	 * 根据传入的对象协议，将缓冲的消息体解码成传入的对象协议对象
	 * @param msgBody
	 * @throws IOException
	 */
	public void decode(IDataProtocolObject msgBody) throws IOException ;
}
