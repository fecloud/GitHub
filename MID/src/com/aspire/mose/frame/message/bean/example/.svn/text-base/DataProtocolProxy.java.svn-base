package com.aspire.mose.frame.message.bean.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.aspire.mose.frame.net.protocol.IDataProtocolObject;
import com.google.protobuf.GeneratedMessageLite;

/**
 * 对象协议代理器  用来适配将各种不同的协议 转换成IDataProtocolObject协议
 * @author liangbo
 *
 * 2011-7-26 下午02:23:23
 *  
 * DataProtocolProxy
 *
 */
public class DataProtocolProxy implements IDataProtocolObject{

	public void setMessageLite(GeneratedMessageLite messageLite) {
		this.messageLite = messageLite;
	}

	//gooble buffer的 消息对象
	GeneratedMessageLite messageLite;
	
	public GeneratedMessageLite getMessageLite() {
		return messageLite;
	}

	public DataProtocolProxy(GeneratedMessageLite messageLite)
	{
		this.messageLite = messageLite;
	}
	
	@Override
	public void decode(DataInputStream dataInputStream) throws IOException {
		
		GeneratedMessageLite resp = (GeneratedMessageLite)messageLite.getDefaultInstanceForType().toBuilder().mergeFrom(dataInputStream).buildPartial();
		if(resp!=null)
		{
			messageLite = resp;
		}
	}

	@Override
	public void encode(DataOutputStream dataOutputStream) throws IOException {
		messageLite.writeTo(dataOutputStream);
	}

}
