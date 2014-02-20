package com.aspire.mose.frame.net.connect;

/**
 * 网络连接异常短线后，通知监听网络异常短信的监听者
 * @author liangbo
 *
 * 2011-7-7 下午04:37:13
 *  
 * IConnectErrorListener
 *
 */
public interface IConnectErrorListener {
	//因为j2me网络如果服务器断开链接,则read函数就变为非阻塞,
    //所以这里要判断是否超过规定读取错误次数来判断是否网络断开
	public static final int CONNECT_ERROR_SERVER_DIS = 1;//服务器断开连接 （客户端检测到服务器断开连接）
	//网络数据读入线程 网络数据读入线程异常
	public static final int CONNECT_ERROR_RECVTHRAD_EXCEPTION=2;//网络数据读入线程 网络数据读入线程异常
	
	//public static final int CONNECT_ERROR_RECVTHRAD_END=3;//网络数据读入线程 结束
	
	
	public void connectException(int type ,Object info);
}
