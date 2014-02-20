/**
 * @(#) SyncMessageListener.java Created on 2012-5-11
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute.engine;

import com.aspire.mcts.agent.msg.APSMessage;

/**
 * The class <code>SyncMessageListener</code>
 * 
 * @author linjunsui
 * @version 1.0
 */
public class SyncMessageListener implements IAPSMessageListener {

    /**
     * responseMessage
     */
    protected APSMessage responseMessage;

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
        case APSMessage.GSD_RESP:
        case APSMessage.HB_RESP:
        case APSMessage.KC_RESP:
        case APSMessage.KD_RESP:
        case APSMessage.KS_RESP:
        case APSMessage.KU_RESP:
        case APSMessage.SIP_RESP:
        case APSMessage.SKI_RESP:
        case APSMessage.TC_RESP:
        case APSMessage.TD_RESP:
        case APSMessage.TDC_RESP:
        case APSMessage.TM_RESP:
        case APSMessage.TS_RESP:
        case APSMessage.TU_RESP:
        case APSMessage.FO_RESP:
        case APSMessage.MTD_RESP:
        case APSMessage.MTM_RESP:
        case APSMessage.MTU_RESP:
            responseMessage = message;
            responsed = true;
            return false;
        }
        /*
         * if (message.getType() == APSMessage.KC_RESP) { responseMessage = message; responsed = true; return false; }
         */
        return true;
    }

    /**
     * Getter of responseMessage
     * 
     * @return the responseMessage
     */
    public APSMessage getResponseMessage() {
        return responseMessage;
    }

    /**
     * reset
     */
    public void reset() {
        responseMessage = null;
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
}
