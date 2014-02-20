/**
 * @(#) ProcessQueryReq.java Created on 2009-2-18
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.msg;

import com.aspire.mobile.MobileConstants;
import com.aspire.msg.MsgBase;

/**
 * The class <code>ProcessQueryReq</code>
 *
 * @author xushengyong
 * @version 1.0
 */
public class ProcessQueryReq extends MobileMsgBase {

    /**
     * The constructor
     */
    public ProcessQueryReq() {
        super(MobileConstants.PROCESS_QUERY_REQ, "ProcessQueryReq");
    }
    
    /*
     * (non-Javadoc)
     * @see com.aspire.msg.FieldMsgBase#createDefaultResponse()
     */
    public MsgBase createDefaultResponse() {
        ProcessQueryResp oResp = new ProcessQueryResp();
        oResp.setSequence(nSeqNum);
        return oResp;
    }
    
}
