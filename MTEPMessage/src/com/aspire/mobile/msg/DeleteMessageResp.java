/**
 * @(#) DeleteMessageResp.java Created on 2009-3-24
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.msg;

import com.aspire.mobile.MobileConstants;

/**
 * The class <code>DeleteMessageResp</code>
 *
 * @author Link Wang
 * @version 1.0
 */
public class DeleteMessageResp extends MobileRespBase {

    /**
     * The constructor.
     */
    public DeleteMessageResp() {
        super(MobileConstants.DELETE_MESSAGE_RESP, "DeleteMessageResp");
    }
}
