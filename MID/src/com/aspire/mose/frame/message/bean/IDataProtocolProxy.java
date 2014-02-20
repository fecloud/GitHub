package com.aspire.mose.frame.message.bean;

import com.aspire.mose.frame.net.protocol.IDataProtocolObject;

/**
 * 协议对象代理 负责将其他的编码解码协议 转换成我们自己的IDataProtocolObject协议
 * 我的消息中心都是依赖于IDataProtocolObject协议
 * @author liangbo
 *
 * 2011-7-26 下午03:22:31
 *  
 * IDataProtocolProxy
 *
 */
public interface IDataProtocolProxy<OtherProtocol> extends IDataProtocolObject {

	/**
	 * 获取第三方的对象协议对象实例
	 * @return
	 */
	public OtherProtocol getProtocolObject();
	
	/**
	 * 设置第三方的对象协议对象实例
	 * @param otherProtocol
	 */
	public void setProtocolObject(OtherProtocol otherProtocol) ;

}
