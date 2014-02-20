/**
 * @(#) MutilTouchUpResponse.java Created on 2012-8-8
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.mcts.agent.msg;

/**
 * The class <code>MutilTouchUpResponse</code>
 *
 * @author ouyangfeng
 * @version 1.0
 */
public class MutilTouchUpResponse extends CommonResponse {

    /**
     * Constructor
     * @param type
     */
    public MutilTouchUpResponse() {
        super(APSMessage.MTU_RESP);
    }

}
