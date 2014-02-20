package com.aspire.mose.frame.message;

import android.util.Log;

import com.aspire.mose.protocol.proto.MsgTransHeader.TransHeader;

public class MessageUtil
{
    private final static int transactionID_MIN = 10000000;
    private final static int transactionID_MAX = 99999999;
    private static int transactionID_IDX = transactionID_MIN;
    private final static Object lockObj = new Object();
    
    public static TransHeader newTransHeader(int cmd,int msgType)
    {
    	Log a;
    	
        int id;
        synchronized(lockObj)
        {
            id=transactionID_IDX++;
            if(transactionID_IDX>transactionID_MAX)
            {
                transactionID_IDX = transactionID_MIN;
            }
        }
        TransHeader header = TransHeader.newBuilder()
        .setCmdID(cmd)
        .setMsgType(msgType)
        .setTransactionID(String.valueOf(id))
        .build();
        return header;
    }

}
