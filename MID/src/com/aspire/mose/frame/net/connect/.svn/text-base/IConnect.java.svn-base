package com.aspire.mose.frame.net.connect;


/**
 * 抽象连接接口，负责socket或者是http的网络连接的建立
 * 提供建立连接 连接状态   发送数据  设置网络输入数据接听的方法
 * @author liangbo
 *
 * 2011-8-1 下午01:36:26
 *  
 * IConnect
 *
 */
public interface IConnect {
	
	public static final int ERROR_SUCCESS = 0;//成功
	//连接层错误码定义  分配1100~1200;
	public static final int ERROR_ICONNECT_BEGIN = 1100;//连接层错误码开始
	public static final int ERROR_NONE = ERROR_ICONNECT_BEGIN+1;//未知错误
	public static final int ERROR_CONNECT_PARAMENTER_INVALID = ERROR_ICONNECT_BEGIN+2;//错误的连接参数
	public static final int ERROR_HOST_INVALID = ERROR_ICONNECT_BEGIN+3;//无效的主机地址
	public static final int ERROR_CONNECT_TIMEOUT = ERROR_ICONNECT_BEGIN+4;//获取输入输出流失败
	public static final int ERROR_GET_STREAM_INVALID = ERROR_ICONNECT_BEGIN+5;//获取输入输出流失败
	public static final int ERROR_OUT_STREAM_NULL = ERROR_ICONNECT_BEGIN+6;//输出流为空
	public static final int ERROR_OUT_IOEXCEPTION = ERROR_ICONNECT_BEGIN+7;//写出IO异常
	
	
	
	
	public static final int CHANNEL_STATE_AVAILABLE = 0;//正常可用
	
	/**
	 * 发送消息
	 * @param msg
	 * @return
	 */
	public int send(byte[] b);
	
	/**
	 * 连接状态
	 * @return
	 */
	public boolean isValid();
	
	/**
	 * 设置网络输入的监听器
	 * @param listener
	 */
	public void setConnectInputListener(IConnectInputListener listener);
	
	/**
	 * 设置网络断开异常的监听器
	 * @param listener
	 */
	public void setConnectErrorListener(IConnectErrorListener listener);
	
	/**
	 * 建立连接
	 * @param urlInfo
	 * @return
	 */
	int connect(String[] urlInfo) ;
	
	/**
	 *  清除关闭连接
	 */
	 public void release() ;
	 
	 /**
	  * 校验连接参数是否一致
	  * @param urlInfo
	  * @return
	  */
	 boolean checkoutConnect(String[] urlInfo) ;
}
