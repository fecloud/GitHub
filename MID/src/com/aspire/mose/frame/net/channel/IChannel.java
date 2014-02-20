package com.aspire.mose.frame.net.channel;

import com.aspire.mose.frame.message.IMessageCenter;
import com.aspire.mose.frame.net.connect.IConnect;
import com.aspire.mose.frame.net.connect.IConnectErrorListener;
import com.aspire.mose.frame.net.connect.IConnectInputListener;
import com.aspire.mose.frame.net.protocol.IDataProtocolObject;
import com.aspire.mose.frame.net.protocol.transport.ITransProtocol;


/**
 * 通道 
 * 通道负责
 * 1.使用连接IChannel,将上层的对象协议,压入到传输协议，转换成byte[]发送给服务器
 * 2.实现IConnectInputListener，负责将连接层的网络输入数据[] 通过ItransProtocol传输协议处理器，转换成上层的对象协议
 * 3.利用IValidate验证处理器，实现通道的许可使用
 * @author liangbo
 *
 * 2011-8-1 下午02:21:54
 *  
 * IChannel
 *
 */
public interface IChannel extends IConnectInputListener,IConnectErrorListener{
	
	public static final int ERROR_SUCCESS = 0;//成功
	//通道层错误码定义  分配1200~1300;	
	public static final int ERROR_ICHANNEL_BEGIN = 1200;//通道层错误码开始
	public static final int ERROR_NONE = ERROR_ICHANNEL_BEGIN+1;//未知错误
	public static final int ERROR_CONNECT_NULL = ERROR_ICHANNEL_BEGIN+2;//连接器为空
	public static final int ERROR_VALIDATE_NULL = ERROR_ICHANNEL_BEGIN+3;//握手器为空
	public static final int ERROR_SEND_IOEXCEPTION = ERROR_ICHANNEL_BEGIN+4;//消息发送 IO异常
	
	

	public static final int CHANNEL_STATE_AVAILABLE = 0;// 正常可用
	public static final int CHANNEL_STATE_UNCONNECT = 1;// 尚未连接
	public static final int CHANNEL_STATE_UNAVAILABLE = 2;// 已经连接 尚未验证授权可以使用频道
	public static final int CHANNEL_STATE_DISCONNECT = 3;// 连接中断
	public static final int CHANNEL_STATE_CONNECT_ERROR = 4;// 连接错误
	public static final int CHANNEL_STATE_VALIDATE_ERROR = 5;// 验证失败 也就是握手失败
	
	//握手器的Key
	public static final String KEY_Validate = "KEY_Validate";	
	//消息中心的 Key
	public static final String KEY_MessageCenter = "KEY_MessageCenter";
	
	

	/*----通道 构建、开通、断开、释放、状态 函数 begin----*/
	
	/**
	 * 构建一个通道
	 */
	//public IChannel builder();
	
	/**
	 * 构建一个通道
	 * @param connect 连接
	 * @param ItransProtocol 传输处理器
	 * @param validate 验证处理器
	 * @return
	 */
	//public IChannel builder(IConnect connect,ITransProtocol ItransProtocol,IValidate validate);

	/**
	 * 激活 也就是连接 + 许可使用（对于握手的 就是说握手成功）	 * 
	 * @param urlInfo    连接服务器参数
	 * @return 错误码
	 */
	public int action(String[] urlInfo);
	
	/**
	 * 去激活，也就是断开连接
	 */
	public void deactivate();

	/**
	 * 清除释放数据
	 */
	public void release();
	
	
	/**
	 * 通道状态
	 * 
	 * @return
	 */
	public int getState();
	
	/**
	 * 设置传输协议处理器
	 * @param transProtocol 传输协议处理器
	 */
	public void setTransProtocol(ITransProtocol transProtocol);
	
	/**
	 * 设置验证处理器
	 * @param validate 验证处理器
	 */
	public void setValidate(IValidate validate);

	/**
	 * 设置连接
	 * @param connect 连接
	 */
	public void setConnect(IConnect connect);
	
	
	/**
	 * 设置消息中心，目的是利用消息中心的ITransInputListener 将传输协议处理好的传输数据交给消息中心
	 */
	public void setMessageCenter(IMessageCenter messageCenter);
	
	

	/*----通道 构建、开通、断开、释放、状态 函数 end----*/
	
	
	
	/*----消息使用相关函数  begin----*/

	/**
	 * 发送消息
	 * 
	 * @param msg  一个完整的对象协议消息包 在传输层内部负责将这个转换成传输协议包
	 * @return
	 */
	public int send(IDataProtocolObject msg);
	
	/*----消息使用相关函数 end----*/
	
	/*----ISession相关函数  begin----*/
	/**
	 * 获取ISession
	 * 
	 * @return
	 */
	public ISession getSession();
	/*----ISession相关函数  end----*/
	
	

}
