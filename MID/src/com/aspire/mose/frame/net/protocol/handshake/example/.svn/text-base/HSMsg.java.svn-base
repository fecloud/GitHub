package com.aspire.mose.frame.net.protocol.handshake.example;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.aspire.mose.frame.net.protocol.IDataProtocolObject;
import com.google.protobuf.GeneratedMessageLite;

public class HSMsg implements IDataProtocolObject{

	//传输报文头  
	HSMsgHead head=null;
	
	//正文内容 
	//正文长度不能超过 224Bytes
	//byte[] body;
	
	public GeneratedMessageLite body;

	private DeHSMsgBodyTemp deMsgBodyTemp;
	
	



	public HSMsgHead getHead() {
		return head;
	}

	public void setHead(HSMsgHead head) {
		this.head = head;
	}
	
	



	@Override
	public void decode(DataInputStream dataInputStream) throws IOException {
		//解析消息头
		head = new HSMsgHead();
		head.decode(dataInputStream);		
		
		
		//body.writeTo(GeneratedMessageLite)
		
		//将消息体装入临时缓冲中 等待具体结构来解码
		deMsgBodyTemp = new DeHSMsgBodyTemp(dataInputStream, head.getLength());
		
		
	}

	@Override
	public void encode(DataOutputStream dataOutputStream) throws IOException {
		ByteArrayOutputStream cache = new ByteArrayOutputStream();
		DataOutputStream temp = new DataOutputStream(cache);
		if (head != null) {
			{
			    //将消息体先编码 放在缓存中 计算大小 填充消息头
				if (body != null) {
					body.writeTo(temp);
					//body.encode(temp);
					int size = cache.toByteArray().length;

					head.setLength(size);
				} else {
					head.setLength(0);
				}
				//编码消息体
				head.encode(dataOutputStream);
			}
			if (body != null) {
				//写出已经编码的消息体
				dataOutputStream.write(cache.toByteArray());
			}
			temp.close();
		}
	}

	public GeneratedMessageLite getBody() {
		return body;
	}

	public void setBody(GeneratedMessageLite body) {
		this.body = body;
	}

	public DeHSMsgBodyTemp getDeMsgBodyTemp() {
		return deMsgBodyTemp;
	}

	public void setDeMsgBodyTemp(DeHSMsgBodyTemp deMsgBodyTemp) {
		this.deMsgBodyTemp = deMsgBodyTemp;
	}

	
}
