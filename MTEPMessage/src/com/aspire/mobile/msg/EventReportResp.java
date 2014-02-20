/**
 * @(#) EventReportResp.java Created on 2009-2-13
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.msg;

import com.aspire.mobile.MobileConstants;

/**
 * The class <code>EventReportResp</code>
 *
 * @author xuyong
 * @version 1.0
 */
public class EventReportResp extends MobileRespBase {
    
    /**
     * Constructor
     */
    public EventReportResp() {
        super(MobileConstants.EVENT_REPORT_RESP, "EventReportResp");
    }
}
