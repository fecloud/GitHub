package com.aspire.mose.frame.net.protocol.transport;

import com.aspire.mose.frame.net.channel.IChannel;
import com.aspire.mose.frame.net.connect.IConnectInputListener;

/**
 * 传输协议处理器 
 * 实现IConnectInputListener 接口，处理网络传输的数据
 * 实现ITransProtocolPack 接口，负责将上层协议转换成传输协议
 * 本身除了处理byte到传输的转换之外，还负责分发传输协议给不同的传输协议对象处理器
 * @author liangbo
 *
 * 2011-8-3 下午03:17:46
 *  
 * ITransProtocol
 *
 */
public interface ITransProtocol  extends IConnectInputListener,ITransProtocolPack{
	
	/**
	 * 设置通道
	 * @param channel
	 */
	public void setChannel(IChannel channel) ;
	
	/**
	 * 设置上层的传输协议对象处理监听接口 负责将转换好的传输协议交给上层处理
	 * @param listener
	 */
	//public void setTransInputListener(ITransInputListener listener);
	
	/**
	 * 设置上层的传输协议对象处理监听接口 负责将转换好的传输协议交给上层处理
	 * @param listener
	 */
	public void addTransInputListener(String key,ITransInputListener listener);
	
}
