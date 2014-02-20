package com.aspire.mose.frame.net.protocol.handshake.example;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import android.util.Log;

import com.aspire.mose.frame.net.protocol.handshake.IHKInputListener;
import com.aspire.mose.frame.net.protocol.handshake.IHandShakeProtocol;
import com.aspire.mose.frame.net.protocol.transport.ITransProtocolObject;

public class HKProtocol implements IHandShakeProtocol{
	
	IHKInputListener hkInputListener = null;
	

	public IHKInputListener getHkInputListener() {
		return hkInputListener;
	}


	public void setHkInputListener(IHKInputListener hkInputListener) {
		this.hkInputListener = hkInputListener;
	}


	@Override
	public void receive(ITransProtocolObject<?, ?, ?> msg) {
		//判断步骤 来区分出收到的是什么消息  用什么来解（注意  在某种状态下  可能回来的是 2个消息  所以要做判断）
		DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(((byte[])msg.getBody())));
		try {
			while(dataInputStream.available()>0)
			{
				HSMsg tHSMsg = new HSMsg();
				tHSMsg.decode(dataInputStream);					
				
				if(hkInputListener!=null )
				{
					hkInputListener.receive(tHSMsg);
				}
				else
				{
					Log.e("TAG","receive() hkInputListener is null");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				dataInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			dataInputStream = null;
		}
		
	}

}
