/**
 * @(#) AspRequest.java Created on 2012-7-25
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.service.request;

/**
 * The class <code>AspRequest</code>
 * 
 * @author wuyanlong
 * @version 1.0
 */
public class AspRequest extends AspMessage {
    /**
     * to Sync response
     */
    protected long syncFlag;

    /**
     * Constructor
     * 
     * @param command
     */
    public AspRequest() {
        super(0);
    }

    /**
     * Constructor
     * 
     * @param command
     */
    public AspRequest(int command) {
        super(command);
        syncFlag = this.hashCode();
    }

    /**
     * Getter of flag
     * 
     * @return the flag
     */
    public long getSyncFlag() {
        return syncFlag;
    }
}
