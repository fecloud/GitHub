package com.aspire.mose.frame.message.defaultcenter;


import java.util.Vector;

import android.util.Log;

import com.aspire.mose.frame.message.IRequestMsgContainer;
import com.aspire.mose.frame.message.bean.IMsg;
import com.aspire.mose.frame.net.channel.IChannel;

public class RequestMsgContainer implements IRequestMsgContainer{
	private static final String	TAG =RequestMsgContainer.class.getSimpleName();
	
	//发送消息器
	IChannel channel;
	
	//发送消息线程
	SendThread sendThread = new SendThread();
	
	//阻塞通知器
	Object objNotify = new Object();
	
	Vector<IMsg<?, ?>> msgContainer = new Vector<IMsg<?, ?>>();
	
	
	public void addMsg(IMsg<?, ?> msg)
	{
		msgContainer.add(msg);
				
		synchronized (objNotify) {
			objNotify.notify();
		}
		
	}
	
	public void removeMsg(IMsg msg)
	{
		msgContainer.remove(msg);
	}
	
	
	
	public void start()
	{
		sendThread.startFlag = true;
		sendThread.start();
	}
	
	public void stop()
	{
		sendThread.startFlag = false;
		sendThread.stop();
	}
	
	
	public class SendThread extends Thread
	{
		//发送消息线程标志
		public boolean startFlag = false;
		
		public void run()
		{
			while(startFlag)
			{
				//逐个消息取出
				while(msgContainer.size()>0)
				{					
					
					//取出第0个 
					IMsg<?, ?> msg = msgContainer.get(0);
					
					
					//去发送
					int error = channel.send(msg);
					Log.e(TAG, "send a msg");
					
					if(error==0)
					{
						//成功  不处理 都交给返回消息处理 或是交给超时处理
					}
					else
					{
						//如果失败 通知消息监听器 发送失败
					
					}
					
					//发送完成  删除第0个
					msgContainer.remove(0);
					
				}
				
				//如果消息队列中没有了  则等待
				synchronized (objNotify) {
					try {
						Log.e(TAG, "msgContainer is null ,begin wait");
						objNotify.wait(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
