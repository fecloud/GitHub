/**
 * @(#) WapOperationResp.java Created on 2012-5-8
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.mobile.msg;

import com.aspire.mobile.MobileConstants;

/**
 * 
 * The class <code>WapOperationResp</code>
 *
 * @author zhenghui
 * @version 1.0
 */
public class SMSOperationResp extends MobileRespBase {
    
    /**
     * The constructor
     */
    public SMSOperationResp() {
        super(MobileConstants.SMS_OPERATION_RESP, "SMSOperationResp");
    }
}
