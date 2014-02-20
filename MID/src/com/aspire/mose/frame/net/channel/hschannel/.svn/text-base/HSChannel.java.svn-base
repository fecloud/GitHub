package com.aspire.mose.frame.net.channel.hschannel;

import com.aspire.mose.business.module.device.M_Device;
import com.aspire.mose.frame.config.Config;
import com.aspire.mose.frame.net.channel.BaseChannel;
import com.aspire.mose.frame.net.channel.IChannel;
import com.aspire.mose.frame.net.channel.ISession;
import com.aspire.mose.frame.net.channel.IValidate;
import com.aspire.mose.frame.net.connect.IConnect;
import com.aspire.mose.frame.net.connect.socket.SocketConnect;
import com.aspire.mose.frame.net.protocol.handshake.example.HKProtocol;
import com.aspire.mose.frame.net.protocol.transport.ITransProtocol;
import com.aspire.mose.frame.net.protocol.transport.ITransProtocolParamet;
import com.aspire.mose.frame.net.protocol.transport.example.TransProtocol;

/**
 * 一个默认的握手通道
 * 
 * @author liangbo
 * 
 *         2011-7-13 下午03:26:29
 * 
 *         HSChannel
 * 
 */
public class HSChannel extends BaseChannel {
	
	
	
	//@Override
	public static IChannel builder() {
		return builder(null,null,null);
	}

	//@Override
	public static IChannel builder(IConnect connect, ITransProtocol transProtocol,
			IValidate validate) {
		HSChannel channel = new HSChannel();

		//设置 连接
		if(connect==null)
		{
			connect = new SocketConnect();
		}
		//挂接了 （连接层的 发【网络数据输出流】收【 网络数据输入流】） 
		channel.setConnect(connect);
		
		
		//构建传输协议器
		if(transProtocol==null)
		{
			
			transProtocol = new TransProtocol();
		}
		
		//挂接了 （连接层到传输层的 发【网络数据输出流】收【 网络数据输入流】） 
		//挂接了 （传输层到握手层    传输层到数据协议层 的   发【网络数据输出流】收【 网络数据输入流】）
		channel.setTransProtocol(transProtocol);	
		
				
		
		//构造握手器
		if(validate==null)
		{			
			validate = new HSValidate();
			//需要给握手器设置 握手协议处理器(这个处理器 负责 接收传输协议 转换成握手协议  然后回调HSValidate 握手器的响应处理)
			validate.setHandShakeProtocol(new HKProtocol());
	
		}
				
		// 挂接了 （握手层到传输层的 发【网络数据输出流】收【 网络数据输入流】）			
		channel.setValidate(validate);
		
			
		if(channel.session==null)
		{
			channel.session = new ISession(){
					String sessionID;
					ITransProtocolParamet transProtocolParamet;
					@Override
					public String getSessionID() {
						if(sessionID==null)
						{
							sessionID = "";
						}
						return sessionID;
					}
	
					@Override
					public ITransProtocolParamet getTransProtocolParamet() {
						if(transProtocolParamet==null)
						{
							transProtocolParamet = new HSTransProtocolParamet();
//							((HSTransProtocolParamet)transProtocolParamet).setMac(M_Device.getMac());
						}
						return transProtocolParamet;
					}
	
					@Override
					public void setSessionID(String sessionID) {
						this.sessionID = sessionID;
						
					}
	
					@Override
					public void setTransProtocolParameter(ITransProtocolParamet aramet) {
						this.transProtocolParamet = aramet;
						
					}

					@Override
					public void release() {
						sessionID = null;
						transProtocolParamet = null;
					}
					
				};
		}		
		return channel;
	}

	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
	
}
