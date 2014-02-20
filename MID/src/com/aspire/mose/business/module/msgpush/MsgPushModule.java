package com.aspire.mose.business.module.msgpush;

import com.aspire.mose.frame.message.IMessageCenter;
import com.aspire.mose.frame.message.IMsgFilter;
import com.aspire.mose.frame.message.bean.example.CmdID;
import com.aspire.mose.frame.message.bean.example.Msg;
import com.aspire.mose.frame.message.defaultcenter.DefaultMsgFilter;

public class MsgPushModule
{
    // 监听器
    private MsgPushListener iMsgPushListener = null;

    IMessageCenter messageCenter;
    
    private static MsgPushModule instance = new MsgPushModule();
    public static MsgPushModule getInstance()
    {
        return instance;
    }
    
    public void init(IMessageCenter mc)
    {
        this.messageCenter = mc;
        // 实例化侦听器
        iMsgPushListener = new MsgPushListener(messageCenter);

        IMsgFilter<?> msgFilter = new DefaultMsgFilter(Msg.MSG_TYPE_REQ, CmdID.CMD_MSG_PUSH, null);

        int RetCoder = mc.addMessageListener(msgFilter, iMsgPushListener);
        if (RetCoder != 0)
        {
            // 出错提示
        }
    }

}
