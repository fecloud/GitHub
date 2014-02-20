/**
 * @(#) WTaskMessageHandle.java Created on 2012-7-25
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.service.client;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspire.sqmp.mobilemanager.service.request.AspRequest;
import com.aspire.sqmp.mobilemanager.service.request.AspResponse;
import com.aspire.sqmp.mobilemanager.service.request.IMessageHandle;

/**
 * The class <code>WTaskMessageHandle</code>
 * 
 * @author wuyanlong
 * @version 1.0
 */
public class WaitForResponseHandle implements IMessageHandle {
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(WaitForResponseHandle.class);

    /**
     * map of responseMap
     */
    private Map<Long, AspResponse> responseMap = new Hashtable<Long, AspResponse>();
    /**
     * list of wait for response
     */
    private List<Long> waitFlagList = new ArrayList<Long>();
    /**
     * Lock of waitFlagList
     */
    private Object WAITLIST_LOCK = waitFlagList;

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.sqmp.mobilemanager.service.request.IMessageHandle#handleMessage(com.aspire.sqmp.mobilemanager.service.request.AspMessage)
     */
    @Override
    public boolean handleMessage(Object message) {
        if (!(message instanceof AspResponse))
            return false;
        final AspResponse aspResponse = (AspResponse) message;
        synchronized (WAITLIST_LOCK) {
            for (long waitFlag : waitFlagList) {
                if (aspResponse.getSyncFlag() == waitFlag) {
                    logger.debug("Receive response ,command:" + aspResponse.getCommand() + ",syncFlag:"
                            + aspResponse.getSyncFlag());
                    responseMap.put(aspResponse.getSyncFlag(), aspResponse);
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * register asprequest wait for response
     * 
     * @param aspRequest
     */
    public void registerWaitResponse(AspRequest aspRequest) {
        synchronized (WAITLIST_LOCK) {
            if (!waitFlagList.contains(aspRequest.getSyncFlag()))
                waitFlagList.add(aspRequest.getSyncFlag());

        }
    }

    /**
     * unregister asprequest wait for response
     * 
     * @param aspRequest
     */
    public void unRegisterWaitResponse(AspRequest aspRequest) {
        synchronized (WAITLIST_LOCK) {
            waitFlagList.remove(aspRequest.getSyncFlag());
            responseMap.remove(aspRequest.getSyncFlag());
        }
    }

    /**
     * Gettor of response
     * 
     * @param aspRequest
     * @return
     */
    public AspResponse getResponse(AspRequest aspRequest) {
        return responseMap.get(aspRequest.getSyncFlag());
    }
}
