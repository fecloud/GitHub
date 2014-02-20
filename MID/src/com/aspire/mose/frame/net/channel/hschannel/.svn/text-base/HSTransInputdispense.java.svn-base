package com.aspire.mose.frame.net.channel.hschannel;

import android.util.Log;

import com.aspire.mose.frame.net.protocol.transport.ITransInputListener;
import com.aspire.mose.frame.net.protocol.transport.ITransProtocolObject;
import com.aspire.mose.frame.net.protocol.transport.example.TranMsgHead;
import com.aspire.mose.frame.net.protocol.transport.example.TransProtocol;

/**
 * 传输协议分发处理器，负责分发网络传输过来的 传输协议 交给握手协议处理器 或数据协议处理器
 * @author liangbo
 *
 * 2011-8-3 下午03:47:19
 *  
 * HSTransInputdispense
 *
 */
public class HSTransInputdispense implements ITransInputListener{
	
	private static final String TAG = TransProtocol.class.getSimpleName();
	
	//上册握手协议处理监听  获取到完整的传输消息后，丢给上层协议处理
	private ITransInputListener hkTransInputListener=null;
	
	//上册数据协议处理监听  获取到完整的传输消息后，丢给上层协议处理
	private ITransInputListener protoTransInputListener=null;
	
	public ITransInputListener getHkTransInputListener() {
		return hkTransInputListener;
	}

	public void setHkTransInputListener(ITransInputListener hkTransInputListener) {
		this.hkTransInputListener = hkTransInputListener;
	}

	public ITransInputListener getProtoTransInputListener() {
		return protoTransInputListener;
	}

	public void setProtoTransInputListener(
		ITransInputListener protoTransInputListener) {
		this.protoTransInputListener = protoTransInputListener;
	}

	@Override
	public void receive(ITransProtocolObject<?, ?, ?> msg) {
		//负责将传输协议处理好的传输协议，根据类型传递给不握手协议处理器 或数据协议处理器
		
		TranMsgHead head = (TranMsgHead)msg.getHead();
		if(head.getMsgType() == TranMsgHead.MSG_TYPE_HANDSHAKE)
		{
			if(hkTransInputListener!=null)
			{
				hkTransInputListener.receive(msg);
			}
			else
			{
				Log.e(TAG, "receive() hkTransInputListener is null ");
			}
			
		}
		else if(head.getMsgType() == TranMsgHead.MSG_TYPE_DATA)
		{
			if(protoTransInputListener!=null)
			{
				protoTransInputListener.receive(msg);
			}
			else
			{
				Log.e(TAG, "receive() protoTransInputListener is null ");
			}
			
		}
		else
		{
			Log.e(TAG, "receive() head.msgType= "+head.getMsgType() +" is error");
		}

		
	}
}
