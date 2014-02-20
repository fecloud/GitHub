package com.aspire.mose.frame.message;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import android.content.Context;

import com.aspire.mose.frame.config.Config;
import com.aspire.mose.frame.config.ConfigModule;
import com.aspire.mose.frame.message.defaultcenter.DefaultMessageCenter;

/**
 * 消息管理者，所有的消息类服务使用这个如果获取 单例的
 * 负责管理多个消息中心
 * 目前负责 通行消息中心 安全消息中心 资源消息中心 这3个
 * @author liangbo
 *
 * 2011-6-27 下午05:52:52
 *  
 * MessageManager
 *
 */
public class MessageManager {
    
    public static final String D_MSG_CENTER = "DefaultMessageCenter";

    // 上下文，保存下来提供给本包下的其它类用。
    private Context context;

    private static MessageManager instance;
    
    public static final MessageManager getInstance()
    {
        if(instance==null)
        {
            instance = new MessageManager();
        }
        return instance;
    }
    
    private MessageManager()
    {
        
    } 
    
    //消息中心容器
    Map<String,IMessageCenter> messageCenterMap = new HashMap<String, IMessageCenter>();
    
    public Context getContext()
    {
        return this.context;
    }
    
    /**
     * 取得消息中心 根据名字
     * @param name 根据名字
     * @return
     */
    public IMessageCenter getMessageCenter(String name)
    {
        return messageCenterMap.get(name);
    }
    
    /**
     * 取得全部消息中心的名字
     * @return
     */
    public Set<String> getMessageCenterNames()
    {
        return messageCenterMap.keySet();
    }
    
    /**
     * 初始化消息管理器
     */
    public static void initFromConfig(Context context)
    {
        if(instance==null)
        {
            instance = new MessageManager();
        }
        instance.context = context;
        
        instance.messageCenterMap.clear();
        
        //....加载各个消息中心
        final DefaultMessageCenter dmc = (DefaultMessageCenter) DefaultMessageCenter.builder();
        instance.messageCenterMap.put(D_MSG_CENTER, dmc);
        
        //用异步的方式来启动，让init可以快速返回。
        //外部如果想知道啥时候启动完毕了，请关注状态机。
        
        //TODO，需要把参数修改为从外部传入，可接收的参数请见DefaultMessageCenter的start方法
        Runnable startMessageCenter = new Runnable(){
			public void run() {
				ConfigModule m = Config.getInstance().getMoudle("System");
				dmc.start(new String[] { m.getStringItem("mosp.address", null),
                        m.getStringItem("mosp.port", null),
                        m.getStringItem("mosp.time-out", null),
                        m.getStringItem("mosp.heart-beat-interval", null)
                        });
			}
        };
        new Thread(startMessageCenter).start();   
    }
    
    /**
     * 销毁消息管理
     */
    public static void destroy()
    {
    	
        //销毁各个消息中心
    	for(IMessageCenter messageCenter : instance.messageCenterMap.values())
    	{
    		messageCenter.stop();
    	}
    }
    

}
