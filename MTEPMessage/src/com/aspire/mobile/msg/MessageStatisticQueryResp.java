/**
 * @(#) MessageStatisticQueryResp.java Created on 2009-3-24
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
 * The class <code>MessageStatisticQueryResp</code>
 *
 * @author Link Wang
 * @version 1.0
 */
public class MessageStatisticQueryResp extends MobileRespBase {

    /**
     * The total counts of message
     */
    protected int nTotal = 0;

    /**
     * The read counts of message
     */
    protected int nRead = 0;

    /**
     * The unread counts of message
     */
    protected int nUnread = 0;

    /**
     * The constructor.
     */
    public MessageStatisticQueryResp() {
        super(MobileConstants.MESSAGE_STATISTIC_QUERY_RESP, "MessageStatisticQueryResp");
    }

    /**
     * @return the nTotal
     */
    public int getTotalValue() {
        return nTotal;
    }

    /**
     * Gets total
     * @return Returns total
     */
    public String getTotal() {
        return Integer.toString(nTotal);
    }

    /**
     * @param total the nTotal to set
     */
    public void setTotal(int total) {
        nTotal = total;
        setVerify("Total");
    }
    
    /**
     * Sets total
     * @param total The total
     * @throws ToolException 
     */
    public void setTotal(String total) throws ToolException {
        setTotal(GenApi.strToInt(total, "Total"));
    }

    /**
     * @return the nRead
     */
    public int getReadValue() {
        return nRead;
    }

    /**
     * @return the nRead
     */
    public String getRead() {
        return Integer.toString(nRead);
    }

    /**
     * @param read the nRead to set
     */
    public void setRead(int read) {
        nRead = read;
    }

    /**
     * @param read the nRead to set
     */
    public void setRead(String read) throws ToolException {
        setRead(GenApi.strToInt(read, "Read"));
    }

    /**
     * @return the nUnread
     */
    public int getUnreadValue() {
        return nUnread;
    }

    /**
     * @return the nUnread
     */
    public String getUnread() {
        return Integer.toString(nUnread);
    }

    /**
     * @param unread the nUnread to set
     */
    public void setUnread(int unread) {
        nUnread = unread;
    }

    /**
     * @param read the nUnread to set
     */
    public void setUnRead(String unRead) throws ToolException {
        setRead(GenApi.strToInt(unRead, "Unread"));
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#decode(byte[], int, int)
     */
    public int decode(byte[] byMsg, int start, int length) throws ToolException {
        
        int nRead = super.decode(byMsg, start, length);
        int offset = start + nRead;
        
        // Decodes the Action
        if (offset >= length) {
            throw new ToolException(
                    "MessageStatisticQueryResp decode error: no Action in the record");
        }
        
        // Decodes Total field
        this.nTotal = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;
        
        // Decodes Read field
        this.nRead = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;
        
        // Decodes Unread field
        this.nUnread = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;
        
        return offset - start;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#encode(com.aspire.util.ByteArray)
     */
    public int encode(ByteArray baMsg) throws ToolException {

        int nLen = baMsg.length();

        super.encode(baMsg);

        // Encodes the Total field
        baMsg.append(nTotal);
        
        // Encodes the Read field
        baMsg.append(nRead);
        
        // Encodes the Unread field
        baMsg.append(nUnread);
        
        return baMsg.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#format(java.lang.StringBuffer, java.lang.String)
     */
    public int format(StringBuffer buf, String sPrefix) throws ToolException {

        int nLen = buf.length();

        super.format(buf, sPrefix);
        
        // Format the Action
        buf.append(sPrefix).
            append("Total = ").
            append(nTotal).
            append(Constants.LINE_SEPARATOR);
        
        // The Interval field
        buf.append(sPrefix).
            append("Read = ").
            append(nRead).
            append(Constants.LINE_SEPARATOR);
        
        // The Duration field
        buf.append(sPrefix).
            append("Unread = ").
            append(nUnread).
            append(Constants.LINE_SEPARATOR);

        return buf.length() - nLen;
    }
    
    /*
     * (non-Javadoc)
     * @see com.aspire.msg.soap.SoapMsgBase#verify(com.aspire.msg.MsgBase)
     */
    public boolean verify(MsgBase msgVerified) throws ToolException {

        if (this == msgVerified) return true;
        
        if (msgVerified == null) {
            throw new ToolException(
                 "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof MessageStatisticQueryResp)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'MessageStatisticQueryResp' message");
        }

        MessageStatisticQueryResp obj = (MessageStatisticQueryResp)msgVerified;

        doVerifyField("Total", nTotal, obj, obj.nTotal);
        doVerifyField("Read", nRead, obj, obj.nRead);
        doVerifyField("Unread", nUnread, obj, obj.nUnread);
        
        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.soap.SoapMsgBase#verifyPresent(com.aspire.msg.MsgBase)
     */
    public boolean verifyPresent(MsgBase msgVerified) throws ToolException {

        if (this == msgVerified) return true;

        if (msgVerified == null) {
            throw new ToolException(
                 "Message verify error: the message to be verified is null");
        }
        
        if (!(msgVerified instanceof MessageStatisticQueryResp)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'MessageStatisticQueryResp' message");
        }

        MessageStatisticQueryResp obj = (MessageStatisticQueryResp)msgVerified;

        doVerifyPresentField("Total", nTotal, obj, obj.nTotal);
        doVerifyPresentField("Read", nRead, obj, obj.nRead);
        doVerifyPresentField("Unread", nUnread, obj, obj.nUnread);

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.soap.SoapMsgBase#verifySpecified(com.aspire.msg.MsgBase)
     */
    public boolean verifySpecified(MsgBase msgVerified) throws ToolException {

        if (this == msgVerified) return true;
        
        if (msgVerified == null) {
            throw new ToolException(
                 "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof MessageStatisticQueryResp)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'MessageStatisticQueryResp' message");
        }

        MessageStatisticQueryResp obj = (MessageStatisticQueryResp)msgVerified;

        doVerifySpecifiedField("Total", nTotal, obj, obj.nTotal);
        doVerifySpecifiedField("Read", nRead, obj, obj.nRead);
        doVerifySpecifiedField("Unread", nUnread, obj, obj.nUnread);

        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj)) return false;

        if (!(obj instanceof MessageStatisticQueryResp)) {
            return false;
        }

        MessageStatisticQueryResp oMsgObj = (MessageStatisticQueryResp)obj;
        
        return oMsgObj.nTotal == nTotal
                && oMsgObj.nRead == nRead
                && oMsgObj.nUnread == nUnread;
    }
}
