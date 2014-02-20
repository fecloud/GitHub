/**
 * @(#) DeleteCallRecordResp.java Created on 2009-3-24
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.msg;

import com.aspire.mobile.MobileConstants;

/**
 * The class <code>DeleteCallRecordResp</code>
 *
 * @author Link Wang
 * @version 1.0
 */
public class DeleteCallRecordResp extends MobileRespBase {

    /**
     * The constructor.
     */
    public DeleteCallRecordResp() {
        super(MobileConstants.DELETE_CALL_RECORD_RESP, "DeleteCallRecordResp");
    }
}
