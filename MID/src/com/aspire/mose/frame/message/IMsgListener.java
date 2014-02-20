package com.aspire.mose.frame.message;

import com.aspire.mose.frame.message.bean.IMsg;



/**
 * 消息处理监听器
 * @author liangbo
 *
 * 2011-6-27 下午06:04:01
 *  
 * IMsgListener
 *
 */
public interface IMsgListener {
	/**
	 * 消息回调
	 * @param msg
	 */
	public void msgProcess(IMsg<?,?> msg);
}
