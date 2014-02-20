package com.aspire.mose.frame.net.channel;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import android.util.Log;

import com.aspire.mose.frame.message.IMessageCenter;
import com.aspire.mose.frame.net.channel.hschannel.HSTransProtocolParamet;
import com.aspire.mose.frame.net.connect.IConnect;
import com.aspire.mose.frame.net.protocol.IDataProtocolObject;
import com.aspire.mose.frame.net.protocol.transport.ITransProtocol;
import com.aspire.mose.frame.net.protocol.transport.ITransProtocolObject;
import com.aspire.mose.frame.net.protocol.transport.example.TranMsgHead;
import com.aspire.mose.frame.net.status.NetStatus;

public abstract class BaseChannel implements IChannel{
    
    private static final String TAG="BaseChannel";

	// 通道状态
	int state = IChannel.CHANNEL_STATE_UNCONNECT;
	


	// 连接
	protected IConnect connect = null;	

	// 验证处理器
	protected IValidate validate = null;	
	
	// 传输协议处理器
	protected ITransProtocol transProtocol= null;
	
	//消息中心
	IMessageCenter messageCenter= null;
	
	//session
	protected ISession session= null;
	

	
	
	/*----复写 构建、开通、断开、释放、状态 函数 begin----*/
	
	@Override
	public void setConnect(IConnect connect) {
		//需要判断原来的连接清除等等
		
		this.connect = connect;//（挂接到网络输出数据流）
		
		//将本通道设置为连接的输入数据和网络数据的监听器（挂接到 网络输入数据流）		
		connect.setConnectInputListener(this);
		connect.setConnectErrorListener(this);
		
	}

	@Override
	public void setTransProtocol(ITransProtocol transProtocol) {
		
		// 发送时候 使用这个（挂接到 网络输出数据流）
		this.transProtocol = transProtocol;
		
		//将通道传递给 传输协议，主要是传输协议从中通道中获取session
		if(transProtocol!=null)
		{
			transProtocol.setChannel(this);
		}
		
		//将握手器和传输协议处理器挂接起来 主要是（挂接到 网络输入数据流）
		if(validate!=null && transProtocol!=null)
		{
			//将握手器设置给 传输处理器 这样在收服务武器传送的消息就会 传递给握手器
			transProtocol.addTransInputListener(IChannel.KEY_Validate, validate);
		}
		
		//将消息中心和传输协议处理器挂接起来 主要是（挂接到 网络输入数据流）
		if(messageCenter!=null && transProtocol!=null)
		{
			//将消息中心设置给 传输处理器 这样在收服务武器传送的消息就会 传递给握手器
			transProtocol.addTransInputListener(IChannel.KEY_MessageCenter, messageCenter);
		}
		
	}

	@Override
	public void setValidate(IValidate validate) {
		this.validate = validate;
		
		//将通道设置为握手器  主要是握手器需要通道的发送消息函数 以及从通道中获取session
		//（挂接到 网络输出数据流）
		validate.setChannel(this);
		
		
		//将握手器和传输协议处理器挂接起来 主要是（挂接到 网络输入数据流）
		if(transProtocol!=null && validate!=null)
		{
			//将握手器设置给 传输处理器 这样在收服务武器传送的消息就会 传递给握手器
			transProtocol.addTransInputListener(IChannel.KEY_Validate, validate);
		}
		
	}
	
	
	@Override
	public void setMessageCenter(IMessageCenter messageCenter)
	{
		this.messageCenter = messageCenter;
		//将消息中心和传输协议处理器挂接起来 主要是（挂接到 网络输入数据流）
		if(transProtocol!=null)
		{
			//将握手器设置给 传输处理器 这样在收服务武器传送的消息就会 传递给握手器
			transProtocol.addTransInputListener(IChannel.KEY_MessageCenter, messageCenter);
		}
		
	}
	
	@Override
	public ISession getSession() {		
		return session;
	}
	
	
	@Override
	public int action(String[] urlInfo) {
		//定义未知状态
		int errorCode = ERROR_NONE;

		// 首先检测是否是已经授权状态 并且连接是好用的
		if (this.getState()  == CHANNEL_STATE_AVAILABLE && connect != null
				&& connect.isValid()) {
			// 这种情况下需要判断 传递的连接参数是否修改了 如果没有修改 就直接使用
			if (connect.checkoutConnect(urlInfo)) {
				// 成功 直接返回
				errorCode = ERROR_SUCCESS;
				return errorCode;
			}
		}

		// 切断原来的连接
		//deactivate();

		if (connect != null) {
			// 建立连接诶 如果连接已存在并可以用 则直接使用 这个是由连接负责处理 这里不管
			int cError = connect.connect(urlInfo);
			if (cError == ERROR_SUCCESS) {
				// 设置状态 已经连接 尚未授权
				setState(CHANNEL_STATE_UNAVAILABLE);

				if (validate != null) {
					// 执行握手
					int vError = validate.doValidate();
					Log.d(TAG, "doValidate result:"+vError);
					// 握手成功
					if (vError == ERROR_SUCCESS) {
						// 正常 成功  设置状态成功
						setState(CHANNEL_STATE_AVAILABLE);
						
						/* 注意  这个需要放到握手器中去处理 */
						HSTransProtocolParamet hpp = (HSTransProtocolParamet)this.getSession().getTransProtocolParamet();
						hpp.setContentState(TranMsgHead.MSG_TYPE_DATA);
						
						errorCode = vError;
					} else {
						// 设置 握手失败  
						setState(CHANNEL_STATE_VALIDATE_ERROR);
						
						// 握手失败  记录握手失败错误码
						errorCode = vError;
					}
				} else {
					//设置状态 验证失败 即握手失败
					setState(CHANNEL_STATE_VALIDATE_ERROR);
					
					//握手器为空
					errorCode = ERROR_VALIDATE_NULL;
				}

			} else {
				// 连接失败状态				
				setState(CHANNEL_STATE_CONNECT_ERROR);
				
				// 连接失败  记录连接失败错误码
				errorCode = cError;
			}
		} else {
			// 没有设置连接 connect为空
			errorCode = ERROR_CONNECT_NULL;
		}

		return errorCode;
	}

	
	@Override
	public void deactivate() {
		
		//如果连接存在
		if(connect!=null)
		{
			//切断连接
			connect.release();
		}
		//设置状态为尚未连接
		setState(CHANNEL_STATE_UNCONNECT);
		
	}

	

	@Override
	public int getState() {
		return state;
	}

	@Override
	public void release() {
		//断开连接
		deactivate();
		
	    connect = null;	

	    validate = null;	
		
		transProtocol= null;
		
		if(session!=null)
		{
			session.release();
		}
		session= null;
		
		
	}

	/*----复写 构建、开通、断开、释放、状态 函数end----*/
	
	/*----复写 消息使用相关函数  begin----*/
	
	@Override
	public int send(IDataProtocolObject msg) {
		int errorCode = ERROR_NONE;// 未知错误
		// 定义缓冲流
		ByteArrayOutputStream sbos = new ByteArrayOutputStream();
		DataOutputStream sdos = new DataOutputStream(sbos);
		try {

			// 如果有传输包协议 则需要给对象包装入到传输包中
			if (transProtocol != null) {

				// 调用协议 编码成传输协议对象
				ITransProtocolObject<?, ?, ?> tmsg = transProtocol.pack(msg);
				// 将传输包编码
				tmsg.encode(sdos);
			} else {
				// 直接将对象包编码
				msg.encode(sdos);
			}

			// 将数据编码发送出去
			int cError = connect.send(sbos.toByteArray());
			
			if(cError!=ERROR_SUCCESS)
			{
				//将连接层错误码发送
				errorCode = cError;
			}
			else
			{
				errorCode = ERROR_SUCCESS;
			}

		} catch (IOException e) {
			errorCode = ERROR_SEND_IOEXCEPTION;//消息发送异常
			e.printStackTrace();
			
			
		} finally {
			try {
				sdos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			sdos = null;
		}
		return errorCode;
	}
	/*----复写 消息使用相关函数  end----*/


	/*----实现IConnectInputListener,IConnectErrorListener相关函数  begin----*/
	@Override
	public void receive(byte[] b) throws IOException {

	   //判断传输协议处理器是否为空
       if(transProtocol!=null)
       {
    	   //将通道传递过来的数据 交给传输协议处理器来处理
//    	   Log.d(TAG, "MSG 2 ==="+new String(b,"UTF-8"));
    	   transProtocol.receive(b);
       }
       else
       {
    	   throw new IOException("transProtocol is null");
       }
		
	}

	@Override
	public void connectException(int type, Object info) {
          //发现断链了  则设置状态为断链  清除通道状态 还是提交上去 交给上面处理
		
		//
		//修改当前的通道状态
		Log.d(TAG, "connectException() type="+type);
		this.setState(CHANNEL_STATE_DISCONNECT);
		
		//通知上层
		  
		
	}
	/*----实现IConnectInputListener,IConnectErrorListener相关函数  end----*/
	
	

	/*----内部函数   begin----*/
	private void setState(int state) {
        Log.d(TAG, "BaseChannel.setState(" + state + ")");
        this.state = state;
        // 发布网络状态到状态机和上层消息中心中。
        if (state == CHANNEL_STATE_AVAILABLE)
        {
            NetStatus.instance().updateState(NetStatus.ON_LINE);
            this.messageCenter.channelStateChange(IMessageCenter.STATE_CONNECTED);
        }
        else
        {
            NetStatus.instance().updateState(NetStatus.OFF_LINE);
            if(state == CHANNEL_STATE_UNCONNECT||state == CHANNEL_STATE_UNAVAILABLE)
            {
                //上面两种状态，分别表示正在尝试建立连接和正在尝试握手，因为为连接建立中
                this.messageCenter.channelStateChange(IMessageCenter.STATE_CONNECTING);
            }
            else
            {
                this.messageCenter.channelStateChange(IMessageCenter.STATE_DISCONNECTED);
            }
        }
	}

	
	/*----内部函数   end----*/
}
