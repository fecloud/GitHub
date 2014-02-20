package com.aspire.mose.frame.message;

import java.util.List;

import com.aspire.mose.frame.message.bean.IMsg;


/**
 * 消息监听器容器
 * @author liangbo
 *
 * 2011-6-28 上午09:57:35
 *  
 * IMsgListenerContainer
 *
 */
public interface IMsgListenerContainer {
	
	/**
	 * 添加一个消息监听器
	 * @param msgFilter
	 * @param listener
	 * @return
	 */
	public int addMsgListener(IMsgFilter msgFilter,IMsgListener listener);
	
	/**
	 * 删除消息监听器
	 * @param msgFilter 消息监听过滤器
	 * @return
	 */
	public int delMsgListener(IMsgFilter msgFilter);
	
	/**
	 * 删除一个消息监听器
	 * @param listener 消息监听器
	 * @return
	 */
	public int delMsgListener(IMsgListener listener);
	
	/**
	 * 获取消息监听器 根据监听过滤器
	 * @param msgFilter 监听过滤器
	 * @return
	 */
	public List<IMsgListener> getMsgListener(IMsgFilter msgFilter);
	
	/**
	 * 获取消息监听器 根据消息
	 * @param msg 消息
	 * @return
	 */
	public List<IMsgListener> getMsgListener(IMsg msg);
	
	/**
	 * 执行消息监听器 根据消息（其中根据消息匹配到的监听过滤条件来执行响应的消息监听器）
	 * @param msg 消息
	 */
	public void doMsgListenerProcess(IMsg msg);
	
	

}
