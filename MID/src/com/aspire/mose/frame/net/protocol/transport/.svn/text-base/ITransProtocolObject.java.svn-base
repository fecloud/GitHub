package com.aspire.mose.frame.net.protocol.transport;

import com.aspire.mose.frame.net.protocol.IDataProtocolObject;

/**
 * 传输协议对象 负责保证传输中消包的完整
 * 一个完整的传输消息报文
 * Head 报文头 需要定长 并且其中有包长度的设定，这样才能保证网络读取一个完整的消息包 再交给上层协议处理
 * Body 报文体
 * Summary 摘要或校验数据 
 * @author liangbo
 *
 * 2011-7-12 下午01:31:33
 *  
 * ITransProtocolObject
 *
 */
public interface ITransProtocolObject<Head,Body,Summary> extends IDataProtocolObject{
	
	public Head getHead();
	
	public Body getBody();
	
	public Summary getSummary();	
	
	public void setHead(Head head);
	
	public void setBody(Body body);
	
	public void setSummary(Summary summary);
	
	public void setUpLayerDataProtocol(IDataProtocolObject dataProtocol);
	
}
