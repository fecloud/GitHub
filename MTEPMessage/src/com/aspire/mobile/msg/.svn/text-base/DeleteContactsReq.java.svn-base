/**
 * @(#) DeleteContactsReq.java Created on 2009-3-24
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.msg;

import com.aspire.mobile.MobileConstants;
import com.aspire.msg.MsgBase;
import com.aspire.util.ToolException;

/**
 * The class <code>DeleteContactsReq</code>
 *
 * @author Link Wang
 * @version 1.0
 */
public class DeleteContactsReq extends MobileMsgBase {

    /**
     * The Constructor.
     */
    public DeleteContactsReq() {
        super(MobileConstants.DELETE_CONTACTS_REQ, "DeleteContactsReq");
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.soap.SoapMsgBase#verify(com.aspire.msg.MsgBase)
     */
    public boolean verify(MsgBase msgVerified) throws ToolException {

        //super.verify(msgVerified);
        if (this == msgVerified) return true;

        if (msgVerified == null) {
            throw new ToolException(
                 "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof DeleteContactsReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'DeleteContactsReq' message");
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.soap.SoapMsgBase#verifyPresent(com.aspire.msg.MsgBase)
     */
    public boolean verifyPresent(MsgBase msgVerified) throws ToolException {

        //super.verifyPresent(msgVerified);
        if (this == msgVerified) return true;

        if (msgVerified == null) {
            throw new ToolException(
                 "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof DeleteContactsReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'DeleteContactsReq' message");
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.soap.SoapMsgBase#verifySpecified(com.aspire.msg.MsgBase)
     */
    public boolean verifySpecified(MsgBase msgVerified) throws ToolException {

        //super.verifySpecified(msgVerified);
        if (this == msgVerified) return true;

        if (msgVerified == null) {
            throw new ToolException(
                 "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof DeleteContactsReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'DeleteContactsReq' message");
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.FieldMsgBase#createDefaultResponse()
     */
    public MsgBase createDefaultResponse() {
        DeleteContactsResp oResp = new DeleteContactsResp();
        oResp.setSequence(nSeqNum);
        return oResp;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        return obj instanceof DeleteContactsReq;
    }
}
