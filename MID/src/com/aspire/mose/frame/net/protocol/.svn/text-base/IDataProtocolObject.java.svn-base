package com.aspire.mose.frame.net.protocol;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;



/**
 * 对象转换协议对象 负责数据流和对象之间的 编码 解码
 * @author liangbo
 *
 * 2011-6-28 上午11:00:38
 *  
 * IDataProtocolObject
 *
 */
public interface IDataProtocolObject {
	/**
	 * 编码 将对象转换为数据流
	 * @param msg
	 * @throws IOException 
	 */
	public void encode(DataOutputStream dataOutputStream) throws IOException;
	
	/**
	 * 解码 将数据流解析到对象
	 * @param data
	 * @throws IOException 
	 */
	public void decode(DataInputStream dataInputStream) throws IOException;


}
