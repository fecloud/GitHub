package com.aspire.mose.frame.net.protocol.transport.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.aspire.mose.frame.net.protocol.IDataProtocolObject;
import com.aspire.mose.frame.net.protocol.transport.ITransProtocolObject;

/**
 * 实现的目前使用的传输协议报文消息
 * @author liangbo
 *
 * 2011-7-18 上午11:37:24
 *  
 * TranMsg
 *
 */
public class TranMsg implements ITransProtocolObject<TranMsgHead,byte[],byte[]>{
	

	
	
	//传输报文头  
	TranMsgHead tranMsgHead=null;
	
	//正文内容 
	//正文长度不能超过 224Bytes
	byte[] tranMsgbody;
	
	//正文内容的数字摘要（可选）
	//注意：基于HMAC的SHA-1的长度为20 bytes，基于HMAC的MD5的长度为16 bytes
	byte[] summary;
	
	@Override
	public void decode(DataInputStream dataInputStream) throws IOException {
		//这个函数可能没地方使用  因为是这样 
		//网络传输过来的byte不保证是一个完整的传输数据包
		//所以我们在 ExConnectInputListener中进行处理
		
	}

	@Override
	public void encode(DataOutputStream dataOutputStream) throws IOException {
		tranMsgHead.encode(dataOutputStream);
		dataOutputStream.write(tranMsgbody);
		if(summary!=null)
		{
			dataOutputStream.write(summary);
		}
		
	}


	@Override
	public byte[] getBody() {
		return tranMsgbody;
	}


	@Override
	public TranMsgHead getHead() {
		return tranMsgHead;
	}


	@Override
	public byte[] getSummary() {
		return summary;
	}


	@Override
	public void setUpLayerDataProtocol(IDataProtocolObject dataProtocol) {
		
	}

	@Override
	public void setBody(byte[] body) {
		this.tranMsgbody = body;
		
	}

	@Override
	public void setHead(TranMsgHead head) {
		this.tranMsgHead = head;
		
	}

	@Override
	public void setSummary(byte[] summary) {
		this.summary = summary;
		
	}

}
