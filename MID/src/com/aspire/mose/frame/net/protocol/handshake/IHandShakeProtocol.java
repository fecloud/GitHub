package com.aspire.mose.frame.net.protocol.handshake;

import com.aspire.mose.frame.net.protocol.transport.ITransInputListener;

/**
 * 握手协议处理器 负责将传入的传输消息转换为握手消息 再交付给握手处理器处理
 * @author liangbo
 *
 * 2011-8-4 上午09:49:04
 *  
 * IHandShakeProtocol
 *
 */
public interface IHandShakeProtocol extends ITransInputListener{

	/**
	 * 设置握手消息处理器
	 * @param hkInputListener
	 */
	public void setHkInputListener(IHKInputListener hkInputListener);
}
