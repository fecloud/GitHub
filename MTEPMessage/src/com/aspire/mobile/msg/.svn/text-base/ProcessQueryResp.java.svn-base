/**
 * @(#) ProcessQueryResp.java Created on 2009-2-18
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.msg;

import com.aspire.Constants;
import com.aspire.mobile.MobileConstants;
import com.aspire.msg.MsgBase;
import com.aspire.util.ByteArray;
import com.aspire.util.ToolException;

/**
 * The class <code>ProcessQueryResp</code>
 * 
 * @author xushengyong
 * @version 1.0
 */
public class ProcessQueryResp extends MobileRespBase {

    /**
     * The ProcessList
     */
    protected String sProcessList = "";

    /**
     * The constructor
     */
    public ProcessQueryResp() {
        super(MobileConstants.PROCESS_QUERY_RESP, "ProcessQueryResp");
    }

    /**
     * Sets the process list
     * 
     * @param sProcessList
     *            The process list name
     */
    public void setProcessList(String sProcessList) {
        this.sProcessList = sProcessList;
        setVerify("ProcessList", true);
    }

    /**
     * Gets the process list name
     * 
     * @return Return the process list name
     */
    public String getProcessList() {
        return sProcessList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.mobile.msg.MobileRespBase#decode(byte[], int, int)
     */
    public int decode(byte[] byMsg, int start, int length) throws ToolException {
        int nRead = super.decode(byMsg, start, length);
        int offset = start + nRead;

        // Decode the ProcessList
        int nLen = length - offset;
        sProcessList = new String(byMsg, offset, nLen);
        offset += nLen;

        return offset - start;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.mobile.msg.MobileRespBase#encode(com.aspire.util.ByteArray)
     */
    public int encode(ByteArray baMsg) throws ToolException {

        int nLen = baMsg.length();

        super.encode(baMsg);

        // Encodes the ProcessList
        if (isPresent("ProcessList"))
            baMsg.append(sProcessList);

        return baMsg.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.mobile.msg.MobileRespBase#format(java.lang.StringBuffer, java.lang.String)
     */
    public int format(StringBuffer buf, String sPrefix) throws ToolException {

        int nLen = buf.length();

        super.format(buf, sPrefix);

        // Formats the ProcessList
        buf.append(sPrefix).append("ProcessList = ").append(sProcessList).append(Constants.LINE_SEPARATOR);

        return buf.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.mobile.msg.MobileRespBase#verify(com.aspire.msg.MsgBase)
     */
    public boolean verify(MsgBase msgVerified) throws ToolException {

        super.verify(msgVerified);

        if (msgVerified == null) {
            throw new ToolException("Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof ProcessQueryResp)) {
            throw new ToolException(
                    "Message verify error: the message to be verified is not a 'ProcessQueryResp' message");
        }

        ProcessQueryResp obj = (ProcessQueryResp) msgVerified;

        doVerifyField("ProcessList", sProcessList, obj, obj.sProcessList);

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.mobile.msg.MobileRespBase#verifyPresent(com.aspire.msg.MsgBase)
     */
    public boolean verifyPresent(MsgBase msgVerified) throws ToolException {

        if (this == msgVerified)
            return true;

        if (msgVerified == null) {
            throw new ToolException("Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof ProcessQueryResp)) {
            throw new ToolException(
                    "Message verify error: the message to be verified is not a 'ProcessQueryResp' message");
        }

        ProcessQueryResp obj = (ProcessQueryResp) msgVerified;

        doVerifyPresentField("ProcessList", sProcessList, obj, obj.sProcessList);

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.mobile.msg.MobileRespBase#verifySpecified(com.aspire.msg.MsgBase)
     */
    public boolean verifySpecified(MsgBase msgVerified) throws ToolException {

        super.verifySpecified(msgVerified);

        if (msgVerified == null) {
            throw new ToolException("Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof ProcessQueryResp)) {
            throw new ToolException(
                    "Message verify error: the message to be verified is not a 'ProcessQueryResp' message");
        }

        ProcessQueryResp obj = (ProcessQueryResp) msgVerified;

        doVerifySpecifiedField("ProcessList", sProcessList, obj, obj.sProcessList);

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.mobile.msg.MobileRespBase#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj))
            return false;

        if (!(obj instanceof ProcessQueryResp)) {
            return false;
        }

        ProcessQueryResp oMsgObj = (ProcessQueryResp) obj;

        return oMsgObj.sProcessList.equals(sProcessList);
    }

}
