package com.aspire.mose.frame.message.defaultcenter;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.aspire.mose.business.module.device.M_Device;
import com.aspire.mose.frame.message.IMessageCenter;
import com.aspire.mose.frame.message.IMsgFilter;
import com.aspire.mose.frame.message.IMsgListener;
import com.aspire.mose.frame.message.IMsgListenerContainer;
import com.aspire.mose.frame.message.MessageManager;
import com.aspire.mose.frame.message.MessageUtil;
import com.aspire.mose.frame.message.bean.IMsg;
import com.aspire.mose.frame.message.bean.example.BodyCatch;
import com.aspire.mose.frame.message.bean.example.CmdID;
import com.aspire.mose.frame.message.bean.example.DataProtocolProxy;
import com.aspire.mose.frame.message.bean.example.Msg;
import com.aspire.mose.frame.net.channel.IChannel;
import com.aspire.mose.frame.net.channel.hschannel.HSChannel;
import com.aspire.mose.frame.net.protocol.IDataProtocolObject;
import com.aspire.mose.frame.net.protocol.transport.ITransProtocolObject;
import com.aspire.mose.protocol.proto.MsgConnectionHeartbeatDetectionRequest.ConnectionHeartbeatDetectionRequest;
import com.aspire.mose.protocol.proto.MsgConnectionHeartbeatDetectionResponse.ConnectionHeartbeatDetectionResponse;
import com.aspire.mose.protocol.proto.MsgTransHeader.TransHeader;
import com.aspire.mose.protocol.proto.OfflineMsg.MsgTerminalOfflineMsgObtainRequest.TerminalOfflineMsgObtainRequest;
import com.aspire.mose.protocol.proto.OfflineMsg.MsgTerminalOfflineMsgObtainResponse.TerminalOfflineMsgObtainResponse;
import com.aspire.mose.shore.broadcast.BroadcastManager;

/**
 * 默认实现的消息中心
 * @author liangbo
 *
 * 2011-6-28 上午10:27:35
 *  
 * DefaultMessageCenter
 *
 */
public class DefaultMessageCenter implements IMessageCenter{
	
	public static final String TAG = DefaultMessageCenter.class.getSimpleName();
	
	private int TIME_OUT = 10000;//默认超时间隔
	
   
    //保存 连接信息
    private String[] info = null;
    //最后一次消息时间
    private long lastTime = 0;
    //心跳间隔时间  默认五分钟
    private long PALPITANT_TIME=5*60*1000;
    //和MOSP的链路状态，定义值请参见IMessageCenter
    //private volatile int state;
    
    private MsgCenterLoop msgCenterLoop = null;
    private DeviceNetworkConnListener connlistener = new DeviceNetworkConnListener();

	//网络通道
	IChannel channel;
	
	//监听器容器
	IMsgListenerContainer listenerContainer= new MsgListenerContainer();
	
	public DefaultMessageCenter()
	{
		listenerContainer = new MsgListenerContainer();
	}

	@Override
	public int addMessageListener(IMsgFilter<?> msgFilter, IMsgListener listener) {
		if(listenerContainer!=null)
		{
			return listenerContainer.addMsgListener(msgFilter, listener);
		}
		return -1;//错误码需要定义
	}


	public IMsgListenerContainer getMsgListenerContainer() {
		return listenerContainer;
	}

	@Override
	public void pushResponseMsg(IMsg<?,?> msg) {
		Log.d(TAG, "msg "+msg.getHead().toString());
		listenerContainer.doMsgListenerProcess(msg);
		
	}

	@Override
	public int sendMessage(IMsg<?,?> msg) {
		if(channel!=null && channel.getState()==IChannel.CHANNEL_STATE_AVAILABLE)
		{
			int errorCode = channel.send(msg);	
			if(errorCode==IMessageCenter.ERROR_SUCCESS)
			{
				//记录 最后一次发送成功时间
				lastTime = System.currentTimeMillis();
			}
			
			return errorCode;
		}
		return -1;//错误码需要定义;
	}

	@Override
	public int sendMessage(IMsg<?,?> msg, IMsgListener listener, int timeout) {
		
		// 此函数超时暂时未处理
		
		//未知错误
		int errorCode = IMessageCenter.ERROR_NONE;
		
		if(channel!=null && channel.getState()==IChannel.CHANNEL_STATE_AVAILABLE)
		{
			//首先处理消息
			Msg tmsg = (Msg)msg;
			//自动添加消息序列码
			TransHeader head = tmsg.getHead();
			//等待添加（自动给消息头添加序列号）
			/*   待添加        */
			
			//定义一个监听过滤器
			IMsgFilter<?> filter= SynMsgFilterFactory.createFilterFromReq(head);
			
			//添加到监听器中
			listenerContainer.addMsgListener(filter, listener);
			
			//发送消息			
			errorCode = channel.send(msg);	
			if(errorCode==IMessageCenter.ERROR_SUCCESS)
			{
				//记录 最后一次发送成功时间
				lastTime = System.currentTimeMillis();
			}
			
			//开始超时等待 等待回应
			//.......
		}
		return errorCode;//错误码需要定义;
	}
	
	@Override
	public int sendMessage(IMsg<?,?> msg, IDataProtocolObject responseMsg, int timeout) {
		

		
		//未知错误
		int errorCode = IMessageCenter.ERROR_NONE;
		
		if(channel!=null && channel.getState()==IChannel.CHANNEL_STATE_AVAILABLE)
		{
			//首先处理消息
			Msg tmsg = (Msg)msg;
			//自动添加消息序列码
			TransHeader head = tmsg.getHead();
			//等待添加（自动给消息头添加序列号）
			/*   待添加        */
			
			//定义一个接受容器
			Vector<IMsg<?,?>> resqV = new Vector<IMsg<?,?>>();
			
			//添加一个消息监听
			SynMsgListener listener = new SynMsgListener(resqV);
			//定义一个监听过滤器
			IMsgFilter<?> filter= SynMsgFilterFactory.createFilterFromReq(head);
			
			//添加到监听器中
			listenerContainer.addMsgListener(filter, listener);
			
			//发送消息			
			errorCode = channel.send(msg);	
			if(errorCode==IMessageCenter.ERROR_SUCCESS)
			{
				//记录 最后一次发送成功时间
				lastTime = System.currentTimeMillis();
				
				//如果消息发送成功
				
				//开始超时等待 等待回应
				synchronized (resqV) {
					try {
						resqV.wait(timeout);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				
				//判断返回结果
				if(resqV.size()>0)
				{
					Msg respMsg = (Msg)resqV.get(0);
					BodyCatch bodyCatch = (BodyCatch)respMsg.getBodyCatch();
					
					try {
						bodyCatch.decode(responseMsg);
					} catch (IOException e) {
						e.printStackTrace();
						//响应消息编解码失败
						errorCode = IMessageCenter.ERROR_DECODE;
					}
					//bodyCatch.  decode(responseMsg);
					
					//返回成功
					return 0;
					
				}
				else
				{
					//说明超时
					errorCode = IMessageCenter.ERROR_TIMEOUT;
				}
				
				
			}
			else
			{
				//发送消息失败
			}
			
			
			//删除监听消息
			//listenerContainer.delMsgListener(filter);
			listenerContainer.delMsgListener(listener);
			
			
			
			
		}
		else
		{
			//通道不可用
			errorCode = IMessageCenter.ERROR_CHANNEL_STATE;
		}
		
			
		return errorCode;//错误码需要定义;
	}



	public void setMsgListenerContainer(IMsgListenerContainer listenerContainer) {
		this.listenerContainer = listenerContainer;
		
	}

	@Override
	public IChannel getChannel() {		
		return channel;
	}

	@Override
	public void setChannel(IChannel channel) {
		//挂接 输出网络数据流
		this.channel = channel;	
		
		if(channel!=null)
		{
			//挂接 网络输入数据流
			channel.setMessageCenter(this);
		}
		else
		{
			Log.e(TAG, "setChannel()  channel is null");
		}
		
	}

	@Override
	public void channelStateChange(int channelState) {
		//this.state = channelState;
		if(this.msgCenterLoop!=null)
		{
	        this.msgCenterLoop.notifyNetState(2, channelState);
		}
	}

   
	/**
	 * 同步消息的 返回消息监听器
	 * @author liangbo
	 *
	 * 2011-7-26 上午10:46:24
	 *  
	 * SynMsgListener
	 *
	 */
	private class SynMsgListener implements IMsgListener
	{
		//返回消息容器
		Vector<IMsg<?,?>> resqV;
		
		public SynMsgListener(Vector<IMsg<?,?>> resqV)
		{
			this.resqV = resqV;
			
		}

		@Override
		public void msgProcess(IMsg<?,?> msg) {

			resqV.add(msg);
			synchronized (resqV) {
				resqV.notifyAll();
			}
			
		}
		
	}
	
	
	public static class SynMsgFilterFactory
	{
		public static IMsgFilter<?> createFilterFromReq(TransHeader reqMsgHead)
		{
			if(reqMsgHead!=null)
			{
				DefaultMsgFilter filter = new DefaultMsgFilter(Msg.MSG_TYPE_RESP,reqMsgHead.getCmdID(),reqMsgHead.getTransactionID());
				return filter;
			}
			
			
			return null;
		}
	}


	@Override
	public void receive(ITransProtocolObject<?, ?, ?> msg) {
		
		
		//判断步骤 来区分出收到的是什么消息  用什么来解（注意  在某种状态下  可能回来的是 2个消息  所以要做判断）
		DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(((byte[])msg.getBody())));
		try {
			while(dataInputStream.available()>0)
			{
				Msg tHSMsg = new Msg();
				tHSMsg.decode(dataInputStream);					
				
				pushResponseMsg(tHSMsg);
				
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

	@Override
	public int start(String[] info) {
		
		Log.d(TAG, " start()");
		
		//缓存住连接信息  防止断线后的重新连接
		this.info = info;
		
		try
		{
	        if(info.length>2)
	        {
	            //第三个参数，是超时间隔，单位是秒
	            TIME_OUT = Integer.parseInt(info[2])*1000;
	        }
            if(info.length>3)
            {
                //第四个参数，是心跳间隔，单位是秒
                PALPITANT_TIME = Integer.parseInt(info[3])*1000;
            }
		}
		catch(Exception e)
		{
		    Log.e(TAG, "parse from config error,but don't worry, i'm still running.  ^_^ ",e);
		}

		//注册设备网络连接状态监听
		IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION); 
		Context c = MessageManager.getInstance().getContext();
		c.registerReceiver(connlistener, filter);
        
		//启动轮询线程  检查
		if(msgCenterLoop==null)
		{
			//msgCenterLoop = new MsgCenterLoop();
		}
		else
		{
			try {
				msgCenterLoop.setLoopRunflag(false);
				msgCenterLoop.stop();
				msgCenterLoop = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//loopRunflag =true;
		msgCenterLoop = new MsgCenterLoop();
		msgCenterLoop.setLoopRunflag(true);
		Log.d(TAG, " msgCenterLoop.start()");
		msgCenterLoop.start();
		
		
		int error =  channel.action(info);
		//发现网络设置改变 就重新连接（由外而内）
		
		//监听mose网络状态  （由内而外）
		
		return error;
	}
	
	@Override
	public void stop() {
		Log.d(TAG, " stop()");
		if(channel!=null)
		{
			channel.release();
		}
		
		
		//loopRunflag = false;
		
		
		if(msgCenterLoop!=null)
		{
			try{
				Log.d(TAG, " msgCenterLoop.stop()");
				msgCenterLoop.setLoopRunflag(false);
				msgCenterLoop.stop();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			msgCenterLoop = null;
		}
		
		try{
		//取消设备网络连接状态监听
        Context c = MessageManager.getInstance().getContext();
        c.unregisterReceiver(connlistener);
        }
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static IMessageCenter builder()
	{
		return builder(null);
	}
	
	public static IMessageCenter builder(HSChannel channel)
	{
		DefaultMessageCenter messageCenter = new DefaultMessageCenter();
		if(channel==null)
		{
			channel = (HSChannel) HSChannel.builder();		
			
			//消息中心设置通道
			messageCenter.setChannel(channel);
		}
				
		return messageCenter;
	}
	
	
    private class MsgCenterLoop extends Thread
    {
    	 //守护线程运行标志
        private boolean loopRunflag = true;
        private Object lockObj = new Object();        
        private volatile int hadewareNetState;//设备的网络状态(移动网络/wifi)，1:通，2:不通
        //从错误网络状态切换回了 第一次必须先发送一个心跳
        private boolean needFastHeart = true;

        /**
         * 通知监控线程网络状态变化了。
         * 网络状态有两种，一种是设备网络状态，一种是MOSE和MOSP的链路状态
         * 设备网络状态值，1:通，2:不通
         * MOSE和MOSP的链路状态参见IMessageCenter中的定义。
         * @param type int,1:设备网络状态，2:MOSE和MOSP的链路状态
         * @param state int，状态值
         */
        public void notifyNetState(int type,int state)
        {
//            Log.d(TAG, "notifyNetState("+type+","+state+")");
            synchronized (lockObj)
            {
                if(type==1)
                {
                    hadewareNetState = state;
                }
                //不管是什么情况，只要网络相关的状态变化了，都唤醒目前的等待。
                lockObj.notifyAll();   
            }
        }

        public void run()
        {
            while (loopRunflag)
            {
            	
            	//Log.d(TAG, " loop Run="+ this+ " loop Run begin time = "+System.currentTimeMillis() );
            	
            	
                // 得到当前连接状态
                int currState = DefaultMessageCenter.this.getChannel().getState();
//            	public static final int CHANNEL_STATE_AVAILABLE = 0;// 正常可用
//            	public static final int CHANNEL_STATE_UNCONNECT = 1;// 尚未连接
//            	public static final int CHANNEL_STATE_UNAVAILABLE = 2;// 已经连接 尚未验证授权可以使用频道
//            	public static final int CHANNEL_STATE_DISCONNECT = 3;// 连接中断
//            	public static final int CHANNEL_STATE_CONNECT_ERROR = 4;// 连接错误
//            	public static final int CHANNEL_STATE_VALIDATE_ERROR = 5;// 验证失败 也就是握手失败
                
                //Log.d(TAG, "  loop Run currState = "+currState );
                
                if (currState == IChannel.CHANNEL_STATE_AVAILABLE)//正常连接
                {
                	//Log.d(TAG, "  loop Run currState == IMessageCenter.STATE_CONNECTED " );
                	
                    // 通，则wait间隔时间(心跳间隔-当前时间+上次消息时间)，或者直到不通。
                    long now = System.currentTimeMillis();
                    
//                    Log.d(TAG, "  loop Run lastTime= "+ lastTime );
//                    Log.d(TAG, "  loop Run (now - lastTime)= "+ (now - lastTime) );
//                    Log.d(TAG, "  loop Run currState= "+ currState );
                    //如果没有lastTime 或者是超过了定时的心跳5分钟
                    if(lastTime==0 || ((now - lastTime)>PALPITANT_TIME) || needFastHeart)
                    {
                        // wait结束，当前连接通。当前连接不通，可能是被唤醒，啥事也不用做。
                        if (currState == IChannel.CHANNEL_STATE_AVAILABLE)
                        {
                        	//一次成功后 不需要再次网络状态切换心跳
                        	needFastHeart = false; 
                        	
                            // 心跳一下
                            heartBeat();
                        }
                    }                    
                }
                //正在连接中 和正在我手中
                else if (currState == IChannel.CHANNEL_STATE_UNCONNECT || currState == IChannel.CHANNEL_STATE_UNAVAILABLE)
                {
                	Log.d(TAG, "  loop Run currState == IMessageCenter.STATE_CONNECTING " );
                	if(!loopRunflag)
					{
						break;
					}
                }
                //连接中断  连接失败 握手失败
                else if (currState == IChannel.CHANNEL_STATE_DISCONNECT ||currState == IChannel.CHANNEL_STATE_CONNECT_ERROR || currState == IChannel.CHANNEL_STATE_VALIDATE_ERROR)
                {
                	                	
                	//一次失败后 需要再次成功 网络状态切换心跳
                	needFastHeart = true; 
                	
                	//Log.d(TAG, "  loop Run currState == IMessageCenter.STATE_DISCONNECTED " );
                	if(!loopRunflag)
					{
						break;
					}
                	
                    // 不通，根据当前设备网络状态
                    if(hadewareNetState==1)
                    {

                        Log.d(TAG, "loop Run DISCONNECTED,device network ready,MsgCenterLoop begin to connect to MOSP.");
                        if(loopRunflag)
                        {
                        	Log.d(TAG, "MsgCenterLoop  channel.action(info)");
                        	channel.action(info);
                        }
                        
                    }
                    else 
                    {
                    	if(!loopRunflag)
    					{
    						break;
    					}
                    }
                }               
                else 
                {
                	if(!loopRunflag)
					{
						break;
					}
                    // 不可能有其它值了！
                    throw new RuntimeException("unknown state:"+currState);
                }
                
                //loop检测
                synchronized (lockObj)
            	{
            		try {
						lockObj.wait(20000);
						
						//Log.d(TAG, "  loop Run wait ok time = "+System.currentTimeMillis() );
						if(!loopRunflag)
						{
							break;
						}
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	}
                
            }
            
            Log.d(TAG, " loop Run="+ this+ " stop for msgCenterLoop"); 
        }

        private void heartBeat()
        {
            Log.d(TAG, "heartBeat()");
            TransHeader head = MessageUtil.newTransHeader(CmdID.CMD_HEART_BEAT, Msg.MSG_TYPE_REQ);
            Msg msg = new Msg();
            msg.setHead(head);
            ConnectionHeartbeatDetectionRequest.Builder body = ConnectionHeartbeatDetectionRequest.newBuilder();
            Log.d(TAG, "getImisID ="+M_Device.getImisID());
            body.setDeviceId(M_Device.getImisID());
            msg.setBody(body.build());
            DataProtocolProxy resp = new DataProtocolProxy(ConnectionHeartbeatDetectionResponse.getDefaultInstance());
            int hearBeatResult = sendMessage(msg, resp, TIME_OUT);
            Log.d(TAG, "HEART_BEAT Message send result:" + hearBeatResult);
            if (hearBeatResult == 0)
            {
                // 发送成功了
                ConnectionHeartbeatDetectionResponse realResp = ( ConnectionHeartbeatDetectionResponse ) resp.getMessageLite();
                if(ConnectionHeartbeatDetectionResponse.ResponseRetCode.SUCCESS != realResp.getRetCode()){
                	Log.d(TAG, "Heartbeat Faile="+realResp.getRetCode());
                	return;
                }
                // Kill OffMsg deal
                /*
                int offlineMsgCount = realResp.getOfflineMessages();
                Log.d(TAG, "OfflineMessages count:[" + offlineMsgCount + "]..");
                if (offlineMsgCount > 0)
                {
                    // TODO 有离线消息，要让谁来处理呢？需求未定
                	dealOfflineMsg();
                }
                */
            }
            else
            {
                // 发送失败，啥也不用管。
            }
        }

		public boolean isLoopRunflag() {
			return loopRunflag;
		}

		public void setLoopRunflag(boolean loopRunflag) {
			this.loopRunflag = loopRunflag;
			if(!loopRunflag)
			{
				synchronized (lockObj) {
					lockObj.notifyAll();
				}
				
			}
		}
    }

    private class DeviceNetworkConnListener extends BroadcastReceiver
    {
        private String TAG2 = "MOSE.DeviceNetwork";
        @Override
        public void onReceive(Context context,Intent intent)
        {
            Log.d(TAG2, "DeviceNetworkConnListener.onReceive("+context+","+intent+")");
            Context c = MessageManager.getInstance().getContext();
            if(c!=null)
            {
                NetworkInfo network = (NetworkInfo)intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
                Log.d(TAG2, "Chnage Network type="+network.getTypeName()+","+network.getType());
                Log.d(TAG2, "Chnage Network state="+network.getState().name());
                
                ConnectivityManager connManager =  (ConnectivityManager)c.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();
                Log.d(TAG2, "curr connect="+activeNetwork);
                if(msgCenterLoop!=null)
                {
                    msgCenterLoop.notifyNetState(1, activeNetwork!=null?1:2);
                }
            }
        }
        
    }
    
    private void dealOfflineMsg(){
    	while(true){
    		Msg offlineMsg = new Msg();
    		TransHeader offlineHead = MessageUtil.newTransHeader(CmdID.CMD_TERMINAL_OffLINE_MSG, Msg.MSG_TYPE_REQ);
    		offlineMsg.setHead(offlineHead);       
    		TerminalOfflineMsgObtainRequest.Builder offlineBody = TerminalOfflineMsgObtainRequest.newBuilder();
    		offlineBody.setDeviceID(M_Device.getImisID());
    		offlineMsg.setBody(offlineBody.build());
    		DataProtocolProxy offlineResp = new DataProtocolProxy(TerminalOfflineMsgObtainResponse.getDefaultInstance());
    		int retCode = sendMessage(offlineMsg, offlineResp, TIME_OUT);
    		Log.d(TAG, "send offlineMsg retCode= "+ retCode);
    		if(0 != retCode){
    			break;
    		}
    		TerminalOfflineMsgObtainResponse realOfflineResp = (TerminalOfflineMsgObtainResponse) offlineResp.getMessageLite();
    		if(TerminalOfflineMsgObtainResponse.ResponseRetCode.SUCCESS != realOfflineResp.getRetCode() &&
    				TerminalOfflineMsgObtainResponse.ResponseRetCode.NOOFFLINEMESSAGE != realOfflineResp.getRetCode())
    			{
    			Log.d(TAG, "ResponseRetCode retCode= "+ realOfflineResp.getRetCode());
    			break;
    			}
    		Log.d(TAG, "TerminalOfflineMsgObtainResponse.ResponseRetCode.SUCCESS" );
    		
//    		//处理函数
    		int listCount = realOfflineResp.getMessageList().size();
    		Log.d(TAG, "listCount = "+ listCount);
    		if(0 == listCount){
    			break;
    		}
    		for(int i =0 ; i< listCount;i++){
    			TerminalOfflineMsgObtainResponse.MessageInfo msgInfo =realOfflineResp.getMessage(i);
    			Log.d(TAG, "Msg："+i+"content:"+ msgInfo.getMsgContent().toStringUtf8());
    			//发广播 ？	
    			BroadcastManager.sendPushMessageNotifyBroadcast(null, msgInfo.getAppId(), msgInfo.getMsgContent().toStringUtf8());
    		}
    		//当服务器发送没有信息了，中断循环
    		if(TerminalOfflineMsgObtainResponse.ResponseRetCode.NOOFFLINEMESSAGE == realOfflineResp.getRetCode()){
    			Log.d(TAG, "NO OfflineMsg");
    			break;
    			
    		}
    	}
    }
    
}
