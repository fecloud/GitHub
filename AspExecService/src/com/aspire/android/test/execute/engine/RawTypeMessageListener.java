/**
 * @(#) RawTypeMessageListener.java Created on 2012-5-8
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute.engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.util.Log;

import com.aspire.android.common.exception.ExceptionHandler;
import com.aspire.android.test.Constants;
import com.aspire.mcts.agent.msg.APSMessage;
import com.aspire.mcts.agent.msg.RawTypeMessage;
import com.aspire.mobile.codec.MobileMsgCodec;
import com.aspire.mobile.codec.MobileMsgCreator;
import com.aspire.mobile.msg.MobileMsgBase;
import com.aspire.msg.MsgBase;
import com.aspire.util.ToolException;

/**
 * The class <code>RawTypeMessageListener</code>
 *
 * @author linjunsui
 * @version 1.0
 */
public class RawTypeMessageListener implements IAPSMessageListener {
    
    /**
     * MTEPMessage codec
     */
    private MobileMsgCodec mtepCodec = new MobileMsgCodec(new MobileMsgCreator());
    
    /**
     * MTEPMessage listener list
     */
    List<IMTEPMessageListener> mtepMessageListenerList = new ArrayList<IMTEPMessageListener>();

    /**
     * Getter of mtepMessageListenerList
     * @return the mtepMessageListenerList
     */
    public List<IMTEPMessageListener> getMtepMessageListenerList() {
        return mtepMessageListenerList;
    }

    /**
     * Setter of mtepMessageListenerList
     * @param mtepMessageListenerList the mtepMessageListenerList to set
     */
    public void setMtepMessageListenerList(List<IMTEPMessageListener> mtepMessageListenerList) {
        this.mtepMessageListenerList = mtepMessageListenerList;
    }

    /**
     * (non-Javadoc)
     * @see com.aspire.android.test.execute.engine.IAPSMessageListener#onMessage(com.aspire.mcts.agent.msg.APSMessage)
     */
    public boolean onMessage(APSMessage message) {
        if (message.getType() == APSMessage.TT_RESP) {
            try {
                MsgBase msgBase = mtepCodec.decode(((RawTypeMessage) message).getBody());
                if (msgBase == null || !(msgBase instanceof MobileMsgBase)) {
                    Log.e(Constants.LOGTAG, "Decode MTEP Message fail, unknown Message");
                    return false;
                }

                for (Iterator<IMTEPMessageListener> iterator = mtepMessageListenerList.iterator(); iterator
                        .hasNext();) {
                    IMTEPMessageListener listener = iterator.next();
                    if (!listener.onMessage((MobileMsgBase) msgBase)) {
                        break;
                    }
                }
                return false;
            } catch (ToolException e) {
                ExceptionHandler.handle(e, "Decode MTEP Message fail");
                return false;
            }
        }
        return true;
    }

}
