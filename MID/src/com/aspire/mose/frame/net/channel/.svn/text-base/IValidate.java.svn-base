package com.aspire.mose.frame.net.channel;

import com.aspire.mose.frame.net.protocol.handshake.IHandShakeProtocol;
import com.aspire.mose.frame.net.protocol.transport.ITransInputListener;

/**
 * Channel的验证处理器接口，负责处理和服务器协商验证后，怎么保证这个通道是有效的
 * @author liangbo
 *
 * 2011-7-13 上午11:32:37
 *  
 * IValidate
 *
 */
public interface IValidate extends ITransInputListener/*IHKInputListener*/{
	
	public static final int VALIDATE_STATE_SUCCESS = 0;// 握手状态_成功
	public static final int VALIDATE_STATE_NONE = -1;// 握手状态_未知
	
	/**
	 * 执行验证
	 */
	public int doValidate();
	
	/**
	 * 获取验证状态
	 * @return
	 */
	public int getValidateState();
	
	/**
	 * 设置通道
	 * @param channel
	 */
	public void setChannel(IChannel channel) ;
	
	/**
	 * 设置握手协议
	 * @param handshakeProtocol
	 */
	public void setHandShakeProtocol(IHandShakeProtocol handshakeProtocol);
	
	
	

}
