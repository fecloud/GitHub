package com.aspire.mose.shore.broadcast;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BroadcastManager
{
    /**
     * 发送给应用，推送消息到达，应用应该过来获取推送消息
     */
    private final static String ACTION_PUSH_NOTIFY = "com.aspire.mose.PUSH_MESSAGE_NOTIFY.";

    private final static String EXTRA_MESSAGE = "message";
    
    private static Context mContext;

    /**
     * 发送推送消息达到广播
     * @param c Context,发送广播的上下文，可以为null。如果为null则用初始化的上下文来发送广播
     * @param appID String,推送消息接收的应用的id
     * @param messageID String,推送消息的id
     */
    public static void sendPushMessageNotifyBroadcast(Context c,String appID, String message)
    {
        //TODO，需要确认广播内容是否这样的？
        Context context = c;
        if(context==null)
        {
            context=mContext;
        }
        Log.d("BroadcastManager", ACTION_PUSH_NOTIFY+appID);
        Intent i = new Intent(ACTION_PUSH_NOTIFY+appID);
        i.putExtra(EXTRA_MESSAGE, message);
        context.sendBroadcast(i);
    }
    
    public static void setContext(Context context)
    {
        mContext = context;
    }

}
