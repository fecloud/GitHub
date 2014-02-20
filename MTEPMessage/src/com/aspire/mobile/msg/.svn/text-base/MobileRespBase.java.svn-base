/**
 * @(#) MobileRespBase.java Created on 2009-2-15
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
 * The class <code>MobileRespBase</code>
 *
 * @author xuyong
 * @version 1.0
 */
public class MobileRespBase extends MobileMsgBase {
    /**
     * 返回码，成功则返回0，失败返回1
     */
    protected byte nStatus = MobileConstants.STATUS_OK;

    /**
     * Constructor
     * @param nMsgType Message type
     * @param sMsgName Message name
     */
    public MobileRespBase(int nMsgType, String sMsgName) {
        super(nMsgType, sMsgName);
    }

    /**
     * Sets status
     * @param nStatus The status
     */
    public void setStatus(byte nStatus) {
        this.nStatus = nStatus;
        setVerify("Status", true);
    }

    /**
     * Sets status
     * @param nStatus The status
     * @throws ToolException 
     */
    public void setStatus(String sStatus) throws ToolException {
        setStatus(GenApi.strToByte(sStatus, "Status"));
    }

    /**
     * Gets status
     * @return Returns status
     */
    public byte getStatusValue() {
        return nStatus;
    }

    /**
     * Gets status
     * @return Returns status
     */
    public String getStatus() {
        return Byte.toString(nStatus);
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#decode(byte[], int, int)
     */
    public int decode(byte[] byMsg, int start, int length) throws ToolException {
        
        int nRead = super.decode(byMsg, start, length);
        int offset = start + nRead;
        
        // Decodes status
        if (offset >= length) {
            throw new ToolException(
                "MobileRespBase decode error: no status in the message buffer");
        }

        nStatus = byMsg[offset];
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
        
        baMsg.append(nStatus);
        
        return baMsg.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#format(java.lang.StringBuffer, java.lang.String)
     */
    public int format(StringBuffer buf, String sPrefix) throws ToolException {

        int nLen = buf.length();

        super.format(buf, sPrefix);

        // Format sequence number
        buf.append(sPrefix).
            append("Status = ").
            append(nStatus).
            append(Constants.LINE_SEPARATOR);

        return buf.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.soap.SoapMsgBase#verify(com.aspire.msg.MsgBase)
     */
    public boolean verify(MsgBase msgVerified) throws ToolException {

        // 需重写FieldMsgBase的同名函数，此处不调用super
        if (this == msgVerified) return true;

        if (msgVerified == null) {
            throw new ToolException(
                 "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof MobileRespBase)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'MobileRespBase' message");
        }

        MobileRespBase obj = (MobileRespBase)msgVerified;

        doVerifyField("Status", nStatus, obj, obj.nStatus);
        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.soap.SoapMsgBase#verifyPresent(com.aspire.msg.MsgBase)
     */
    public boolean verifyPresent(MsgBase msgVerified) throws ToolException {

        // 需重写FieldMsgBase的同名函数，此处不调用super
        //super.verifyPresent(msgVerified);
        if (this == msgVerified) return true;

        if (msgVerified == null) {
            throw new ToolException(
                 "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof MobileRespBase)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'MobileRespBase' message");
        }

        MobileRespBase obj = (MobileRespBase)msgVerified;

        doVerifyPresentField("Status", nStatus, obj, obj.nStatus);
        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.soap.SoapMsgBase#verifySpecified(com.aspire.msg.MsgBase)
     */
    public boolean verifySpecified(MsgBase msgVerified) throws ToolException {

        // 需重写FieldMsgBase的同名函数，此处不调用super
        //super.verifySpecified(msgVerified);
        if (this == msgVerified) return true;

        if (msgVerified == null) {
            throw new ToolException(
                 "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof MobileRespBase)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'MobileRespBase' message");
        }

        MobileRespBase obj = (MobileRespBase)msgVerified;

        doVerifySpecifiedField("Status", nStatus, obj, obj.nStatus);
        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        
        if (!super.equals(obj)) return false;

        if (!(obj instanceof MobileRespBase)) {
            return false;
        }
        
        MobileRespBase oMsgObj = (MobileRespBase)obj;
        
        return oMsgObj.nStatus == nStatus;
    }
}
