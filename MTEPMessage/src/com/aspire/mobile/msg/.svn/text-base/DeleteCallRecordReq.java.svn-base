/**
 * @(#) DeleteCallRecordReq.java Created on 2009-2-16
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.msg;

import com.aspire.Constants;
import com.aspire.mobile.MobileConstants;
import com.aspire.msg.MsgBase;
import com.aspire.util.ByteArray;
import com.aspire.util.GenApi;
import com.aspire.util.ToolException;

/**
 * 
 * The class <code>DeleteCallRecordReq</code>
 *
 * @author Link Wang
 * @version 1.0
 */
public class DeleteCallRecordReq extends MobileMsgBase {
    
    /**
     * The call type, 0x01:Incoming call/0x02:Outcoming call/0x03:Missed call 
     */
    protected byte nCallType = MobileConstants.CALL_TYPE_INCOMING;

    /**
     * The constructor
     */
    public DeleteCallRecordReq() {
        super(MobileConstants.DELETE_CALL_RECORD_REQ, "DeleteCallRecordReq");
    }
    
    /**
     * Sets the call type
     * @param nCallType The call type
     */
    public void setCallType(byte nCallType) {
        this.nCallType = nCallType;
        setVerify("CallType");
    }

    /**
     * Sets the call type
     * @param sType The call type
     * @throws ToolException 
     */
    public void setCallType(String sCallType) throws ToolException {
        setCallType(GenApi.strToByte(sCallType, "CallType"));
    }

    /**
     * Gets the call type
     * @return Returns The call type
     */
    public String getCallType() {
        return Byte.toString(nCallType);
    }

    /**
     * Gets the call type
     * @return Returns the call type
     */
    public byte getCallTypeValue() {
        return nCallType;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#decode(byte[], int, int)
     */
    public int decode(byte[] byMsg, int start, int length) throws ToolException {

        int nRead = super.decode(byMsg, start, length);
        int offset = start + nRead;

        // Decode CallType
        nCallType = byMsg[offset];
        offset++;

        return offset - start;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#encode(com.aspire.util.ByteArray)
     */
    public int encode(ByteArray baMsg) throws ToolException {

        int nLen = baMsg.length();

        super.encode(baMsg);
        
        // Encode the call type
        baMsg.append(nCallType);

        return baMsg.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#format(java.lang.StringBuffer, java.lang.String)
     */
    public int format(StringBuffer buf, String sPrefix) throws ToolException {

        int nLen = buf.length();

        super.format(buf, sPrefix);

        // Format Path
        buf.append(sPrefix).
            append("CallType = ").
            append(nCallType).
            append(Constants.LINE_SEPARATOR);

        return buf.length() - nLen;
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

        if (!(msgVerified instanceof DeleteCallRecordReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'DeleteCallRecordReq' message");
        }

        DeleteCallRecordReq obj = (DeleteCallRecordReq)msgVerified;

        doVerifyField("CallType", nCallType, obj, obj.nCallType);
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

        if (!(msgVerified instanceof DeleteCallRecordReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'DeleteCallRecordReq' message");
        }

        DeleteCallRecordReq obj = (DeleteCallRecordReq)msgVerified;

        doVerifyPresentField("CallType", nCallType, obj, obj.nCallType);

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

        if (!(msgVerified instanceof DeleteCallRecordReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'DeleteCallRecordReq' message");
        }

        DeleteCallRecordReq obj = (DeleteCallRecordReq)msgVerified;

        doVerifySpecifiedField("CallType", nCallType, obj, obj.nCallType);

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.FieldMsgBase#createDefaultResponse()
     */
    public MsgBase createDefaultResponse() {
        DeleteCallRecordResp oResp = new DeleteCallRecordResp();
        oResp.setSequence(nSeqNum);
        return oResp;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj)) return false;

        if (!(obj instanceof DeleteCallRecordReq)) {
            return false;
        }

        DeleteCallRecordReq oMsgObj = (DeleteCallRecordReq)obj;

        return oMsgObj.nCallType == nCallType;
    }
}
