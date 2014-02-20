package com.aspire.mose.frame.message;

import com.aspire.mose.frame.message.bean.IMsg;

/**
 * 请求消息容器
 * @author liangbo
 *
 * 2011-6-28 上午09:54:59
 *  
 * IRequestMsgContainer
 *
 */
public interface IRequestMsgContainer {
	
	public void addMsg(IMsg<?, ?> msg);
	
	public void removeMsg(IMsg<?, ?> msg);
}
