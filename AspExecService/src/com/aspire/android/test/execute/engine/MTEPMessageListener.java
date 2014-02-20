/**
 * @(#) MTEPMessageListener.java Created on 2012-5-11
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute.engine;

import java.util.ArrayList;
import java.util.List;

import com.aspire.mobile.MobileConstants;
import com.aspire.mobile.msg.MessageBoxQueryResp;
import com.aspire.mobile.msg.MobileMsgBase;

/**
 * The class <code>MTEPMessageListener</code>
 * 
 * @author linjunsui
 * @version 1.0
 */
public class MTEPMessageListener implements IMTEPMessageListener {

    /**
     * response Message list
     */
    protected List<MobileMsgBase> responseMessageList = new ArrayList<MobileMsgBase>();

    /**
     * get a complete responsed
     */
    protected boolean responsed = false;

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.execute.engine.IMTEPMessageListener#onMessage(com.aspire.mobile.msg.MobileMsgBase)
     */
    public boolean onMessage(MobileMsgBase message) {
        boolean result = true;
        switch (message.getMsgType()) {
        case MobileConstants.WAP_OPERATION_RESP:
        case MobileConstants.ADD_CALENDAR_ITEM_RESP:
        case MobileConstants.ADD_CALL_RECORD_RESP:
        case MobileConstants.ADD_CONTACTS_RESP:
        case MobileConstants.ADD_MESSAGE_RESP:
        case MobileConstants.APP_OPERATION_RESP:
        case MobileConstants.CALENDAR_QUERY_RESP:
        case MobileConstants.SMS_OPERATION_RESP:
        case MobileConstants.CALL_OPERATION_RESP:
        case MobileConstants.INPUT_OPERATION_RESP:
        case MobileConstants.GRAB_NETWORKPACKAGE_RESP:
            // case MobileConstants.TE_SMS_SEND :
            responseMessageList.add(message);
            responsed = true;
            result = false;
            break;
        case MobileConstants.MESSAGE_BOX_QUERY_RESP:
            final MessageBoxQueryResp messageBoxQueryResp = (MessageBoxQueryResp) message;
            responseMessageList.add(messageBoxQueryResp);
            final byte flag = messageBoxQueryResp.getnFlag();
            if (flag == MobileConstants.TE_SINGLE_MSG || flag == MobileConstants.TE_LAST_MSG) {
                responsed = true;
            }
            result = false;
        }
        return result;
    }

    /**
     * Getter of responseMessageList
     * 
     * @return the responseMessageList
     */
    public List<MobileMsgBase> getResponseMessageList() {
        return responseMessageList;
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
     * reset
     */
    public void reset() {
        responseMessageList.clear();
        responsed = false;
    }

}
