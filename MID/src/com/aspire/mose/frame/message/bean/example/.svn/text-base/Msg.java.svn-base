package com.aspire.mose.frame.message.bean.example;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.aspire.mose.frame.message.bean.IBodyCatch;
import com.aspire.mose.frame.message.bean.IMsg;
import com.aspire.mose.protocol.proto.MsgTransHeader.TransHeader;
import com.google.protobuf.GeneratedMessageLite;

public class Msg implements IMsg<TransHeader,GeneratedMessageLite>{


	//请求 消息
	public static int MSG_TYPE_REQ = 0;
	//响应消息
	public static int MSG_TYPE_RESP = 1;
	
	IBodyCatch bodyCatch = null;
	
	TransHeader head=null;
	
	GeneratedMessageLite body = null;

	@Override
	public GeneratedMessageLite getBody() {
		return body;
	}

	@Override
	public IBodyCatch getBodyCatch() {
		return bodyCatch;
	}

	@Override
	public TransHeader getHead() {
		return head;
	}

	@Override
	public void setBody(GeneratedMessageLite body) {
		this.body = body;
		
	}

	@Override
	public void setBodyCatch(IBodyCatch bodyCatch) {
		this.bodyCatch = bodyCatch;
		
	}

	@Override
	public void setHead(TransHeader head) {
		this.head = head;
		
	}

	@Override
	public void decode(DataInputStream dataInputStream) throws IOException {
				
		//此处获取的是一个完整的 gb协议 也就是 一个gb头  一个gb体
		//协议方式[头长度][head][体长度][body]
	    int headL =	dataInputStream.readInt();//读取头长处??
		byte[] bHead  = new byte[headL];
		dataInputStream.read(bHead);//读取头		
		head = TransHeader.parseFrom(bHead);//解码头
		
		int bodyL =	dataInputStream.readInt();//读取体长处
		bodyCatch = new BodyCatch();
		bodyCatch.doCatch(dataInputStream, bodyL);//将消息体装入缓冲
		
	}
	
	

	@Override
	public void encode(DataOutputStream dataOutputStream) throws IOException {
		
		if (head != null) {
			ByteArrayOutputStream headCache = new ByteArrayOutputStream();
			DataOutputStream temph = new DataOutputStream(headCache);
			
			head.writeTo(temph);
			dataOutputStream.writeInt(headCache.size());//??
			dataOutputStream.write(headCache.toByteArray());

			if (body != null) 
			{
				ByteArrayOutputStream bodyCache = new ByteArrayOutputStream();
				DataOutputStream tempb = new DataOutputStream(bodyCache);
				body.writeTo(tempb);
				
				dataOutputStream.writeInt(bodyCache.size());
				dataOutputStream.write(bodyCache.toByteArray());
				
				tempb.close();
			}
			
			temph.close();
		}
		
		
		
		
	}

	

}
