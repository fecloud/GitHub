/**
 * @(#) MutilTouchUpRequest.java Created on 2012-8-8
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.mcts.agent.msg;

/**
 * The class <code>MutilTouchUpRequest</code>
 *
 * @author ouyangfeng
 * @version 1.0
 */
public class MutilTouchUpRequest extends MutilTouchDownRequest {

    /**
     * Constructor
     * 
     * @param type
     */
    public MutilTouchUpRequest() {
        super(APSMessage.MTU_REQ);
    }

    /**
     * Constructor
     * 
     * @param x
     * @param y
     * @param interDelay
     * @param x1
     * @param y1
     * @param interDelay1
     */
    public MutilTouchUpRequest(int x, int y, int interDelay, int x1, int y1, int interDelay1) {
        super(x, y, interDelay, x1, y1, interDelay1);
        setType(APSMessage.MTU_REQ);
    }

    /**
     * Constructor
     * 
     * @param x
     * @param y
     * @param interDelay
     * @param x1
     * @param y1
     * @param interDelay1
     * @param x2
     * @param y2
     * @param interDelay2
     */
    public MutilTouchUpRequest(int x, int y, int interDelay, int x1, int y1, int interDelay1, int x2, int y2,
            int interDelay2) {
        super(x, y, interDelay, x1, y1, interDelay1, x2, y2, interDelay2);
        setType(APSMessage.MTU_REQ);
    }
}
