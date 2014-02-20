package com.aspire.mose.frame.message.defaultcenter;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import android.util.Log;

import com.aspire.mose.frame.message.IMsgFilter;
import com.aspire.mose.frame.message.IMsgListener;
import com.aspire.mose.frame.message.IMsgListenerContainer;
import com.aspire.mose.frame.message.bean.IMsg;
import com.aspire.mose.protocol.proto.MsgTransHeader.TransHeader;

/**
 * 消息监听器容器
 * @author liangbo
 *
 * 2011-7-25 下午01:25:38
 *  
 * MsgListenerContainer
 *
 */
public class MsgListenerContainer  implements IMsgListenerContainer{
	
	//第一层 存储消息类型  监听荣器  首先简单实现
	//HashMap<DefaultMsgFilter, Vector<IMsgListenerContainer>> ListenerContainer = new HashMap<DefaultMsgFilter,  Vector<IMsgListenerContainer>>();

	HashMap<String, List<IMsgListener>> listenerContainer = new HashMap<String,  List<IMsgListener>>();

	

	@Override
	public int addMsgListener(IMsgFilter msgFilter, IMsgListener listener) {
		
		String key = msgFilter.toString();
		
		List<IMsgListener> trmpListenerVector= listenerContainer.get(key);
		if(trmpListenerVector==null)
		{
			trmpListenerVector = new Vector<IMsgListener>();
			listenerContainer.put(key, trmpListenerVector);
		}
		
		trmpListenerVector.add(listener);
		
		return 0;
	}

	@Override
	public int delMsgListener(IMsgFilter msgFilter) {
		
		String key = msgFilter.toString();
		
		List<IMsgListener> trmpListenerVector= listenerContainer.get(key);
		if(trmpListenerVector==null)
		{
			trmpListenerVector.clear();
		}
		listenerContainer.remove(key);
		
		return 0;
	}

	@Override
	public int delMsgListener(IMsgListener listener) {

		Collection<List<IMsgListener>> valuse= listenerContainer.values();
		for(List<IMsgListener> trmpListenerVector:valuse)
		{
			
			trmpListenerVector.remove(listener);
			
		}
		
		
		return 0;
	}

	@Override
	public void doMsgListenerProcess(IMsg msg) {
		List<IMsgListener> msgListener = getMsgListener(msg);
		
		//说明需要处理
		if(msgListener!=null && msgListener.size()>0)
		{
			
			Log.d("TAG", "msgListener"+msgListener.size());
			
			for(IMsgListener listener:msgListener)
			{
				//驱动一个新线程去执行响应 这样就不会阻塞服务器返回
				//可以讨论
//				new Thread()
//				{
//					public void run()
//					{
						listener.msgProcess(msg);
//					}
//				}.start();
				
				
			}
		}
		
	}

	@Override
	public List<IMsgListener> getMsgListener(IMsgFilter msgFilter) {
		
		String key = msgFilter.toString();
		
		List<IMsgListener> trmpListenerVector= listenerContainer.get(key);
		
		return trmpListenerVector;
	}

	@Override
	public List<IMsgListener> getMsgListener(IMsg msg) {
		
		//根据消息头  构造出3个 过滤器  目前只做一个监听器  具体消息的 
		TransHeader head = (TransHeader)msg.getHead();
		DefaultMsgFilter msgFilter = new DefaultMsgFilter(head.getMsgType(),head.getCmdID(),head.getTransactionID());
       
		
		
		String key = msgFilter.toString();
		
		List<IMsgListener> trmpListenerVector= listenerContainer.get(key);
		
		 //还需要构造多个过滤器
		//监听该命令号码 和消息类型的 
		DefaultMsgFilter msgFilter1 = new DefaultMsgFilter(head.getMsgType(),head.getCmdID(),null);
        //还需要构造多个过滤器
		String key2 = msgFilter1.toString();
		
		List<IMsgListener> trmpListenerVector1= listenerContainer.get(key2);
		
		
		if(trmpListenerVector!=null)
		{
			if(trmpListenerVector1!=null)
			{
				trmpListenerVector.addAll(trmpListenerVector1);
			}
			
		}
		else
		{
			trmpListenerVector = trmpListenerVector1;
		}
		
		return trmpListenerVector;
	}

}
