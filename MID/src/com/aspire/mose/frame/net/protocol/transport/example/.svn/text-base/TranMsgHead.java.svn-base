package com.aspire.mose.frame.net.protocol.transport.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.aspire.mose.frame.net.protocol.IDataProtocolObject;

/**
 * 实现的目前使用的传输协议报文消息头
 * @author liangbo
 *
 * 2011-7-18 上午11:37:58
 *  
 * TranMsgHead
 *
 */
public class TranMsgHead implements IDataProtocolObject{
	
	//握手协议
	public static final byte  MSG_TYPE_HANDSHAKE =  0x01;
	//应用协议
	public static final byte  MSG_TYPE_DATA =  0x02;
	
	private static final byte  MSG_VERSIONS =  0x01;
	
	

	public static final int HEAD_LENGTH= 6;//包头大小 固定5个byte
	
	//定义消息体可以填写的最大长度 24位 即三个字节
	private static final int BODY_LENGTH_MAX =  0x00FFFFFF;
	
	//内容类型 
	//0x01	1	握手协议
	//0x02	2	应用数据协议
	byte msgType;
	
	//主版本
	byte versions=MSG_VERSIONS;
	
	//4.3.4 正文长度 只支持3字节  也就是不超过2M的消息体
	//bits 23..16	bits 15..8	bits 7..0
	int length; 
	
	
	public byte getMsgType() {
		return msgType;
	}



	public void setMsgType(byte msgType) {
		this.msgType = msgType;
	}



	public byte getVersions() {
		return versions;
	}



	public void setVersions(byte versions) {
		this.versions = versions;
	}



	public int getLength() {
		return length;
	}



	public void setLength(int length) {
		this.length = length;
	}


	@Override
	public void decode(DataInputStream dataInputStream) throws IOException {
		msgType = dataInputStream.readByte();
		versions = dataInputStream.readByte();
		
//		byte length32 = dataInputStream.readByte();
//		byte length24 = dataInputStream.readByte();
//		byte length16 = dataInputStream.readByte();
//		byte length8 = dataInputStream.readByte();
//		
//		length = getInt(new byte[]{length32,length24,length16,length8},true);
		length = dataInputStream.readInt();
	    int a;
	}
	
	

	@Override
	public void encode(DataOutputStream dataOutputStream) throws IOException {
		dataOutputStream.writeByte(msgType);
		dataOutputStream.writeByte(versions);
		
//		byte[] blength =  getBytes(length,true);
//		dataOutputStream.writeByte(blength[1]);
//		dataOutputStream.writeByte(blength[2]);
//		dataOutputStream.writeByte(blength[3]);
//		dataOutputStream.writeByte(blength[3]);
		dataOutputStream.writeInt(length);
		
	}
	
	
	public final static byte[] getBytes(int s, boolean asc) {
	    byte[] buf = new byte[4];
	    if (asc)
	      for (int i = buf.length - 1; i >= 0; i--) {
	        buf[i] = (byte) (s & 0x000000ff);
	        s >>= 8;
	      }
	    else
	      for (int i = 0; i < buf.length; i++) {
	        buf[i] = (byte) (s & 0x000000ff);
	        s >>= 8;
	      }
	    return buf;
	  }
	
	public final static int getInt(byte[] buf, boolean asc) {
	    if (buf == null) {
	      throw new IllegalArgumentException("byte array is null!");
	    }
	    if (buf.length > 4) {
	      throw new IllegalArgumentException("byte array size > 4 !");
	    }
	    int r = 0;
	    if (asc)
	      for (int i = buf.length - 1; i >= 0; i--) {
	        r <<= 8;
	        r |= (buf[i] & 0x000000ff);
	      }
	    else
	      for (int i = 0; i < buf.length; i++) {
	        r <<= 8;
	        r |= (buf[i] & 0x000000ff);
	      }
	    return r;
	  }


	

	
}
