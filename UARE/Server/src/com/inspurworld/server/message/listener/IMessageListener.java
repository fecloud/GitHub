/**
 * @(#) IMessageListener.java Created on 2012-10-20
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server.message.listener;

import com.inspurworld.msg.APSMessage;

/**
 * The class <code>IMessageListener</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public interface IMessageListener {
    /**
     * @param message
     * @return true while need to pass the message to the next listener
     */
    public boolean onMessage(APSMessage message);

    public APSMessage receiveMessage();
}
