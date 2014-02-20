package com.aspire.mose.frame.net.protocol.transport;

/**
 * 传输协议的收到处理器，这个负责处理收到已经经过传输协议层处理过的完整的传输消息包
 * 由上层的协议层来实现，传输层负责将数据处理成ITransProtocol后 通过这个接口回调给上层
 * @author liangbo
 *
 * 2011-7-13 上午11:27:18
 *  
 * ITransInputListener
 *
 */
public interface ITransInputListener {
	public void receive(ITransProtocolObject<?, ?, ?> tMsg);
	
	/**
	 * 设置业务处理消息对象  即通道模块再调用receive 之后 将传输对象转换为业务对象
	 * 并将对象回调给上层的IDataProtocolInputListener 去处理
	 * @param dataProtocolInputListener
	 */
	//public void setDataProtocolInputListener(IDataProtocolInputListener dataProtocolInputListener);
}
