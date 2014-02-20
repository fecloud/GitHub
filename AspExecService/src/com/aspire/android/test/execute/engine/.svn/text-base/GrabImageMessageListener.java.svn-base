/**
 * @(#) GrabImageMessageListener.java Created on 2012-5-18
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute.engine;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.aspire.mcts.agent.msg.APSMessage;
import com.aspire.mcts.agent.msg.GrabImageResponse;
import com.aspire.mcts.agent.msg.ImageMessage;

/**
 * The class <code>GrabImageMessageListener</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class GrabImageMessageListener implements IAPSMessageListener {

    private static final String TAG = "GrabImageMessageListener";

    /**
     * responseMessages
     */
    protected List<APSMessage> responseMessages = new ArrayList<APSMessage>();

    /**
     * get a complete responsed
     */
    protected boolean responsed = false;

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.execute.engine.IAPSMessageListener#onMessage(com.aspire.mcts.agent.msg.APSMessage)
     */
    public boolean onMessage(APSMessage message) {
        switch (message.getType()) {
        case APSMessage.GI_RESP:
            if (message instanceof GrabImageResponse) {
                final GrabImageResponse responseMessage = (GrabImageResponse) message;
                final ImageMessage imageMessage = responseMessage.getImageMessage();
                // revice a package
                if (null != imageMessage) {
                    Log.d(TAG, "revice a package TotalPackageCount=" + imageMessage.getTotalPackageCount()
                            + " CurrentPackageSequenceNumber=" + imageMessage.getCurrentPackageSequenceNumber());
                    responseMessages.add(message);
                    // CurrentPackageSequenceNumber==TotalPackageCount revice finish
                    if (imageMessage.getCurrentPackageSequenceNumber() == imageMessage.getTotalPackageCount()) {
                        responsed = true;
                    }
                }
            }
            return false;
        default:
            break;
        }
        return true;
    }

    /**
     * reset
     */
    public void reset() {
        responseMessages.clear();
        responsed = false;
    }

    /**
     * Getter of responsed
     * 
     * @return the responsed
     */
    public boolean isResponsed() {
        return responsed;
    }

    /**
     * Getter of responseMessages
     * 
     * @return the responseMessages
     */
    public List<APSMessage> getResponseMessages() {
        return responseMessages;
    }

}
