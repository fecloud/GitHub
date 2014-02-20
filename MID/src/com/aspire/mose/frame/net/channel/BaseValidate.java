package com.aspire.mose.frame.net.channel;

import android.util.Log;

import com.aspire.mose.frame.net.protocol.handshake.IHKInputListener;
import com.aspire.mose.frame.net.protocol.handshake.IHandShakeProtocol;
import com.aspire.mose.frame.net.protocol.transport.ITransProtocolObject;

/**基础的握手器 
 * 处理了ITransInputListener的响应，
 * receive(ITransProtocolObject<?, ?, ?> tMsg)中 使用IHandShakeProtocol来处理将传输消息转换成握手消息
 * 在IHandShakeProtocol中的响应中  执行调用IHKInputListener接口  
 * 子类需要实现IHKInputListener 的 receive(HSMsg msg)
 * 
 * @author liangbo
 *
 * 2011-8-4 上午10:25:05
 *  
 * BaseValidate
 *
 */
public abstract class BaseValidate implements IValidate,IHKInputListener{
	
	private static final String TAG = BaseValidate.class.getSimpleName();
	
	
	protected int validateState = VALIDATE_STATE_NONE;//初始化为未知状态
	
	
	//通道层 传递进来 用来发送消息
	protected IChannel channel;
	
	//握手协议处理器
	IHandShakeProtocol handshakeProtocol;
	
	@Override
	public int getValidateState() {		
		return validateState;
	}
	
	public void setValidateState(int validateState) {
		this.validateState = validateState;
	}

	@Override
	public final void setChannel(IChannel channel) {
		this.channel = channel;
	}

	@Override
	public final void setHandShakeProtocol(IHandShakeProtocol handshakeProtocol) {
		//设置握手协议处理器
		this.handshakeProtocol = handshakeProtocol;
		
		//挂接 从传输层的协议经过握手协议处理器处理后 转换为握手消息 交给握手消息处理器使用
		handshakeProtocol.setHkInputListener(this);
		
	}

	@Override
	public final void receive(ITransProtocolObject<?, ?, ?> msg) {
		
		//将传输层传递过来的服务器数据，交给握手协议处理器执行
		if(handshakeProtocol!=null)
		{
			handshakeProtocol.receive(msg);
		}
		else
		{
			Log.e(TAG, "receive handshakeProtocol is null");
		}
		
		
	}

}
