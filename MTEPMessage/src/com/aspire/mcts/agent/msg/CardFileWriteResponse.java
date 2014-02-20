/**
 * @(#) CardFileWriteResponse.java Created on 2012-7-18
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.mcts.agent.msg;

/**
 * The class <code>CardFileWriteResponse</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class CardFileWriteResponse extends CommonResponse {

    /**
     * Constructor
     * 
     * @param type
     */
    public CardFileWriteResponse() {
        super(APSMessage.CFW_RESP);
    }

}
