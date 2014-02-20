package com.aspire.mose.frame.net.protocol.handshake.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.aspire.mose.frame.net.protocol.IDataProtocolObject;

public class HSMsgHead implements IDataProtocolObject{
	
	//  ClientConnectReq  	建立连接请求
	public static final byte CMD_CLIENT_CONNECT_REQ=0x00;
	
	// server_connect_resp  响应连接请求
	public static final byte CMD_SERVER_CONNECT_RESP=0x01;
	
	// server_cert_resp     返回服务器证书
	public static final byte CMD_SERVER_CERT_RESP=0x02;

	// client_ verify_req   验证客户端身份请求
	public static final byte CMD_CLIENT_VERIFY_REQ=0x03;
	
	//client_ verify_resp   验证客户端身份应答
	public static final byte CMD_CLIENT_VERIFY_RESP=0x04;
	
	//client_key_req        密钥协商请求
	public static final byte CMD_CLIENT_KEY_REQ=0x05;
	
	//client_key_resp		 密钥协商应答
	public static final byte CMD_CLIENT_KEY_RESP=0x06;
	
	//client_security_program_req	下载安全体检程序请求
	public static final byte CMD_CLIENT_SECURITY_PROGRAM_REQ=0x07;
	
	//server_ security_program_resp	返回安全体检程序
	public static final byte CMD_SERVER_SECURITY_PROGRAM_RESP=0x08;
	
	//server_ finish  握手结束
	public static final byte CMD_SERVER_FINISH=0x09;
	
	//内容类型 
	byte command;
	
	
	public byte getCommand() {
		return command;
	}

	public void setCommand(byte command) {
		this.command = command;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	//4.3.4 正文长度 只支持3字节  也就是不超过2M的消息体
	//bits 23..16	bits 15..8	bits 7..0
	int length; 
	
	@Override
	public void decode(DataInputStream dataInputStream) throws IOException {
		command = dataInputStream.readByte();		
		
//		byte length24 = dataInputStream.readByte();
//		byte length16 = dataInputStream.readByte();
//		byte length8 = dataInputStream.readByte();
//		
//		length = getInt(new byte[]{0x0,length24,length16,length8},true);
		length = dataInputStream.readInt();
	}

	@Override
	public void encode(DataOutputStream dataOutputStream) throws IOException {
		dataOutputStream.writeByte(command);		
		
//		byte[] blength =  getBytes(length,true);
//		dataOutputStream.writeByte(blength[1]);
//		dataOutputStream.writeByte(blength[2]);
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
