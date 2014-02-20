package com.aspire.mose.frame.message.defaultcenter;

import com.aspire.mose.frame.message.IMsgFilter;
import com.aspire.mose.protocol.proto.MsgTransHeader.TransHeader;


public class DefaultMsgFilter implements IMsgFilter<TransHeader>{
	
	//消息类型
	int msgType =-1;//-1代表，检测全部类型 ，也就传入任何类型的消息 都返回true
	
	//命令吗
	int cmdID = -1;//-1代表全部检测，也就传入任何命令吗的消息 都返回true
	
	//消息序列号
	String transactionID=null; //null 代表全部检测，也就传入任何命令吗的消息 都返回true
	
	public DefaultMsgFilter()
	{
	}
	
	public DefaultMsgFilter(int msgType,int cmdID,String transactionID)
	{
		this.msgType = msgType;
		this.cmdID = cmdID;
		this.transactionID = transactionID;
		
	}

	@Override
	public boolean needProcess(TransHeader msgHead) {
		
		boolean flag= true;
		
		if(msgHead==null)
		{
			flag = false;
		}
		else
		{
			//检查消息类型
			if(checkMsgType(msgType,msgHead.getMsgType()))
			{
				//检测到消息类型匹配 继续检测
				
				if(checkCmdID(cmdID,msgHead.getCmdID()))
				{
					//检测到消息类型匹配 继续检测
					
					if(checkTransactionID(transactionID,msgHead.getTransactionID()))
					{
						//检测到消息类型匹配  则匹配
					}
					else
					{
						//检测到 命令吗不匹配  就算不匹配了
						flag = false;
					}
					
				}
				else
				{
					//检测到 命令吗不匹配  就算不匹配了
					flag = false;
				}
			}
			else
			{
				//检测到 消息类型不匹配  就算不匹配了
				flag = false;
			}
		}
		
		
		return flag;
	}
	
	
	private boolean checkMsgType(int filter,int msg)
	{
		boolean flag= true;
		
		if(filter!=-1)
		{
			//如果是监听具体的类型
			if(filter!=msg)
			{
				//如果条件 和传入的消息不符合
				flag = false;
			}
			else
			{
				//条件和传入消息符合
			}
		}
		else
		{
			//如果这个条件 是检测全部类型
		}
		
		return flag;
	}
	
	private boolean checkCmdID(int filter,int msg)
	{
		boolean flag= true;
		
		if(filter!=-1)
		{
			//如果是监听具体的类型
			if(filter!=msg)
			{
				//如果条件 和传入的消息不符合
				flag = false;
			}
			else
			{
				//条件和传入消息符合
			}
		}
		else
		{
			//如果这个条件 是检测全部类型
		}
		
		return flag;
	}
	
	
	private boolean checkTransactionID(String filter,String msg)
	{
		boolean flag= true;
		
		if(filter!=null)
		{
			//如果是监听具体的类型
			if(!filter.equals(msg))
			{
				//如果条件 和传入的消息不符合
				flag = false;
			}
			else
			{
				//条件和传入消息符合
			}
		}
		else
		{
			//如果这个条件 是检测全部类型
		}
		
		return flag;
	}

	
	public String toString()
	{
		
//		//消息类型
//		int msgType =-1;//-1代表，检测全部类型 ，也就传入任何类型的消息 都返回true
//		
//		//命令吗
//		int cmdID = -1;//-1代表全部检测，也就传入任何命令吗的消息 都返回true
//		
//		//消息序列号
//		String transactionID=null; //null 代表全部检测，也就传入任何命令吗的消息 都返回true
		
		return ""+msgType+":"+cmdID+":"+transactionID;
		
	}
	
}
