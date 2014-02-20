package com.aspire.mose.frame.message;


import com.aspire.mose.frame.message.bean.IMsg;
import com.aspire.mose.frame.net.channel.IChannel;
import com.aspire.mose.frame.net.protocol.IDataProtocolObject;
import com.aspire.mose.frame.net.protocol.transport.ITransInputListener;

/**
 * 消息中心 负责消息的分发和监听注册、
 * 包含 请求消息队列 响应消息队列 消息监听器队列
 * @author liangbo
 *
 * 2011-6-27 下午05:54:46
 *  
 * IMessageCenter
 *
 */
public interface IMessageCenter extends ITransInputListener{
	
	public static final int ERROR_NONE=-1;//未知错误
	public static final int ERROR_SUCCESS=0;//未知错误
	public static final int ERROR_CHANNEL_STATE=1;//通道不可用
	public static final int ERROR_TIMEOUT=2;//超时
	public static final int ERROR_DECODE=3;//解码失败
	
    public static final int STATE_CONNECTING=1;//状态：连接建立中
    public static final int STATE_CONNECTED=2;//状态：已经连接
    public static final int STATE_DISCONNECTED=3;//状态：断连
	
	
	
	
	
	/*----上层应用调用者使用接口begin----*/	
	/**
	 * 发送消息
	 * @param msg 消息
	 * @return 错误码
	 */
	public int sendMessage(IMsg<?,?> msg);
	
	/**
	 * 发送消息
	 * @param msg 消息
	 * @param listener 消息监听器
	 * @param timeout 超时时间
	 * @return 错误码
	 */
	public int sendMessage(IMsg<?,?> msg,IMsgListener listener,int timeout);
	
	
	/**
	 * 发送消息（这个函数负责将响应的消息体根据传入的IDataProtocol 进行解码 使用者可以直接使用 ）
	 * @param msg 消息
	 * @param responseMsg 响应消息
	 * @param timeout 超时时间
	 * @return 错误码
	 */
	public int sendMessage(IMsg<?,?> msg,IDataProtocolObject responseMsg,int timeout);
	
    /**
     * 添加消息监听
     * @param msgFilter 监听过滤器
     * @param listener 消息监听器
     * @return 错误码
     */
	public int addMessageListener(IMsgFilter<?> msgFilter,IMsgListener listener);
	
	/*----上层应用调用者使用接口end----*/
	
	
	
	/*----消息模块构建者用者使用接口begin----*/
	/**
	 * 设置消息监听器容器
	 * @param listenerContainer
	 */
	//public void setMsgListenerContainer(IMsgListenerContainer listenerContainer);	
	
	/**
	 * 取得消息监听器容器
	 * @return IMsgListenerContainer 消息监听器容器
	 */
	//public IMsgListenerContainer getMsgListenerContainer();
	
	/**
	 * 设置请求消息容器
	 * @param requestMsgContainer
	 */
	//public void setRequestMsgContainer(IRequestMsgContainer requestMsgContainer);
	
	/**
	 * 取得请求消息容器
	 * @return IRequestMsgContainer 请求消息容器
	 */
	//public IRequestMsgContainer setRequestMsgContainer();
	
	
	/**
	 * 设置响应消息容器
	 * @param responseMsgContainer
	 */
	//public void setResponseMsgContainer(IResponseMsgContainer responseMsgContainer);
	
	/**
	 * 取得响应消息容器
	 * @return IResponseMsgContainer 请求消息容器
	 */
	//public IResponseMsgContainer setResponseMsgContainer();
	
	/**
	 * 设置网络通道
	 * @param channel
	 */
	public void setChannel(IChannel channel);
	
	
	/**
	 * 取得网络通道
	 * @return
	 */
	public IChannel getChannel();
	
	
	//需要一个 开始 和关闭方法
	public int start(String[] info);
	
	public void stop();
	
	
	/*----消息模块构建者用者使用接口begin----*/
	
	
	/*----下层网络模块使用接口begin----*/	
	/**
	 * 加入一条响应消息 （所谓响应消息，可能是后台返回的消息，也可能是后台通知的消息）
	 * @param msg 响应消息以及通知消息 （后台给前台的消息）
	 */
	public void pushResponseMsg(IMsg<?,?> msg);
	
	/**
	 * 下层通道状态变化通知接口
	 * @param channelState
	 */
	public void channelStateChange(int channelState);
	
	/*----下层网络模块使用接口end----*/	
	
}
