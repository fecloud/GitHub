/**
 * @(#) MonitorControlReq.java Created on 2009-2-18
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
 * The class <code>MonitorControlReq</code>
 *
 * @author xushengyong
 * @version 1.0
 */
public class MonitorControlReq extends MobileMsgBase {
    
    /**
     * The action type, 0x01:Start/0x02:Stop/0x03:Restart
     */
    protected byte nAction = MobileConstants.MONITOR_CONTROL_TYPE_START;
    
    /**
     * The constructor
     */
    public MonitorControlReq() {
        super(MobileConstants.MONITOR_CONTROL_REQ, "MonitorControlReq");
    }
    
    /**
     * Sets the action type
     * @param nAction The action type
     */
    public void setAction(byte nAction) {
        this.nAction = nAction;
        setVerify("Action");
    }

    /**
     * Sets the action type
     * @param sType The action type
     * @throws ToolException 
     */
    public void setAction(String sAction) throws ToolException {
        setAction(GenApi.strToByte(sAction, "Action"));
    }

    /**
     * Gets the action type
     * @return Returns The action type
     */
    public String getAction() {
        return Byte.toString(nAction);
    }

    /**
     * Gets the action type
     * @return Returns the action type
     */
    public byte getActionValue() {
        return nAction;
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
                    "MonitorControlReq decode error: no Action in the record");
        }
        nAction = byMsg[offset];
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

        // Encodes the Action
        baMsg.append(nAction);
        
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
            append("Action = ").
            append(nAction).
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

        if (!(msgVerified instanceof MonitorControlReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'MonitorControlReq' message");
        }

        MonitorControlReq obj = (MonitorControlReq)msgVerified;

        doVerifyField("Action", nAction, obj, obj.nAction);
        
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
        
        if (!(msgVerified instanceof MonitorControlReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'MonitorControlReq' message");
        }

        MonitorControlReq obj = (MonitorControlReq)msgVerified;

        doVerifyPresentField("Action", nAction, obj, obj.nAction);

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

        if (!(msgVerified instanceof MonitorControlReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'MonitorControlReq' message");
        }

        MonitorControlReq obj = (MonitorControlReq)msgVerified;

        doVerifySpecifiedField("Action", nAction, obj, obj.nAction);

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.FieldMsgBase#createDefaultResponse()
     */
    public MsgBase createDefaultResponse() {
        
        MonitorControlResp oResp = new MonitorControlResp();
        
        oResp.setSequence(nSeqNum);
        
        return oResp;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj)) return false;

        if (!(obj instanceof MonitorControlReq)) {
            return false;
        }

        MonitorControlReq oMsgObj = (MonitorControlReq)obj;
        
        return oMsgObj.nAction == nAction;
    }
    
}
