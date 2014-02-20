/**
 * @(#) CallOperationResp.java Created on 2009-2-18
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.msg;

import com.aspire.mobile.MobileConstants;

/**
 * 
 * The class <code>CallOperationResp</code>
 *
 * @author Link Wang
 * @version 1.0
 */
public class CallOperationResp extends MobileRespBase {
    
    /**
     * The constructor
     */
    public CallOperationResp() {
        super(MobileConstants.CALL_OPERATION_RESP, "CallOperationResp");
    }
}
