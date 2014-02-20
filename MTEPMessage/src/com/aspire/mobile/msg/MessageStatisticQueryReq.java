/**
 * @(#) MessageStatisticQueryReq.java Created on 2009-2-16
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
 * The class <code>MessageStatisticQueryReq</code>
 *
 * @author Link Wang
 * @version 1.0
 */
public class MessageStatisticQueryReq extends MobileMsgBase {
    
    /**
     * The message type, 0x01:SMS/0x02:MMS/0x03:Email
     */
    protected byte nType = MobileConstants.MESSAGE_TYPE_SMS;
    
    /**
     * The folder name
     */
    protected String sFolderName = "";

    /**
     * The constructor
     */
    public MessageStatisticQueryReq() {
        super(MobileConstants.MESSAGE_STATISTIC_QUERY_REQ, "MessageStatisticQueryReq");
    }
    
    /**
     * Sets the type
     * @param nType The type
     */
    public void setType(byte nType) {
        this.nType = nType;
        setVerify("Type");
    }

    /**
     * Sets the type
     * @param sType The type
     * @throws ToolException 
     */
    public void setType(String sType) throws ToolException {
        setType(GenApi.strToByte(sType, "Type"));
    }

    /**
     * Gets the type
     * @return Returns The type
     */
    public String getType() {
        return Byte.toString(nType);
    }

    /**
     * Gets the type
     * @return Returns the type
     */
    public byte getTypeValue() {
        return nType;
    }
      
    /**
     * Sets the folder name
     * @param sFolderName The folder name
     */
    public void setFolderName(String sFolderName) {
        this.sFolderName = sFolderName;
        setVerify("FolderName", true);
    }
    
    /**
     * Gets the folder name
     * @return Return the folder name
     */
    public String getFolderName() {
        return sFolderName;
    }  

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#decode(byte[], int, int)
     */
    public int decode(byte[] byMsg, int start, int length) throws ToolException {

        int nRead = super.decode(byMsg, start, length);
        int offset = start + nRead;
        
        // Decode Type
        nType = byMsg[offset];
        offset++;
        
        // Decode FolderName
        int nPathLen = length - offset;
        sFolderName = new String(byMsg, offset, nPathLen);
        offset += nPathLen;

        return offset - start;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#encode(com.aspire.util.ByteArray)
     */
    public int encode(ByteArray baMsg) throws ToolException {

        int nLen = baMsg.length();

        super.encode(baMsg);

        // Encodes Type
        baMsg.append(nType);

        // Encodes FolderName
        baMsg.append(sFolderName);

        return baMsg.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#format(java.lang.StringBuffer, java.lang.String)
     */
    public int format(StringBuffer buf, String sPrefix) throws ToolException {

        int nLen = buf.length();

        super.format(buf, sPrefix);

        // Format Type
        buf.append(sPrefix).
            append("Type = ").
            append(nType).
            append(Constants.LINE_SEPARATOR);

        // Format FolderName
        buf.append(sPrefix).
            append("FolderName = ").
            append(sFolderName).
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

        if (!(msgVerified instanceof MessageStatisticQueryReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'MessageStatisticQueryReq' message");
        }

        MessageStatisticQueryReq obj = (MessageStatisticQueryReq)msgVerified;

        doVerifyField("Type", nType, obj, obj.nType); 
        doVerifyField("FolderName", sFolderName, obj, obj.sFolderName);
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

        if (!(msgVerified instanceof MessageStatisticQueryReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'MessageStatisticQueryReq' message");
        }

        MessageStatisticQueryReq obj = (MessageStatisticQueryReq)msgVerified;

        doVerifyPresentField("Type", nType, obj, obj.nType);
        doVerifyPresentField("FolderName", sFolderName, obj, obj.sFolderName);

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

        if (!(msgVerified instanceof MessageStatisticQueryReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'MessageStatisticQueryReq' message");
        }

        MessageStatisticQueryReq obj = (MessageStatisticQueryReq)msgVerified;

        doVerifySpecifiedField("Type", nType, obj, obj.nType);
        doVerifySpecifiedField("FolderName", sFolderName, obj, obj.sFolderName);

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.FieldMsgBase#createDefaultResponse()
     */
    public MsgBase createDefaultResponse() {
        MessageStatisticQueryResp oResp = new MessageStatisticQueryResp();
        oResp.setSequence(nSeqNum);
        return oResp;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj)) return false;

        if (!(obj instanceof MessageStatisticQueryReq)) {
            return false;
        }

        MessageStatisticQueryReq oMsgObj = (MessageStatisticQueryReq)obj;

        return oMsgObj.nType == nType && oMsgObj.sFolderName.equals(sFolderName);
    }
}
