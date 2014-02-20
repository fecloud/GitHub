/**
 * @(#) ConnectionMessageListener.java Created on 2012-10-23
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server.message;

import com.inspurworld.msg.APSMessage;
import com.inspurworld.msg.common.ConnectionResponse;
import com.inspurworld.server.message.listener.IMessageListener;

/**
 * The class <code>ConnectionMessageListener</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class ConnectionMessageListener implements IMessageListener {

    /**
     * {@inheritDoc}
     * 
     * @see com.inspurworld.server.message.listener.IMessageListener#onMessage(com.inspurworld.msg.APSMessage)
     */
    @Override
    public boolean onMessage(APSMessage message) {
        switch (message.getType()) {
        case APSMessage.C_REQ:
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.inspurworld.server.message.listener.IMessageListener#receiveMessage()
     */
    @Override
    public APSMessage receiveMessage() {
        return new ConnectionResponse();
    }

}
