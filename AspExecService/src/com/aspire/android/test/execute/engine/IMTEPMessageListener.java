/**
 * @(#) IMTEPMessageListener.java Created on 2012-5-8
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute.engine;

import com.aspire.mobile.msg.MobileMsgBase;

/**
 * The class <code>IMTEPMessageListener</code>
 *
 * @author linjunsui
 * @version 1.0
 */
public interface IMTEPMessageListener {
    /**
     * 
     * @param message
     * @return true while need to pass the message to the next listener
     */
    public boolean onMessage(MobileMsgBase message);
}
