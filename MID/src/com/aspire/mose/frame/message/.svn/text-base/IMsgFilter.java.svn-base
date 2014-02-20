package com.aspire.mose.frame.message;



/**
 * 消息监听过滤接口 用来确定什么消息是需要这个过滤器处理的
 * @author liangbo
 *
 * 2011-6-28 上午09:33:24
 *  
 * IMsgFilter
 *
 */
public interface IMsgFilter<Head> {
	
	/**
	 * 根据消息头，判断是否是拦截器设定的需要处理的消息
	 * @param msgHead
	 * @return
	 */
	public boolean needProcess(Head msgHead);
	

}
