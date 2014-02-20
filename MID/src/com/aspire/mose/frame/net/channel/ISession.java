package com.aspire.mose.frame.net.channel;

import com.aspire.mose.frame.net.protocol.transport.ITransProtocolParamet;

public interface ISession {
	
	/**
	 * 释放
	 */
	public void release();
	
	
	/**
	 * 获取阐述协议状态参数
	 * @return
	 */
	public ITransProtocolParamet getTransProtocolParamet();
	
	public void setTransProtocolParameter(ITransProtocolParamet aramet);
	
	
	
	
	/**
	 * 获取sessionID
	 * @return
	 */
	public String getSessionID();
	
	public void setSessionID(String sessionID);
	
}
