/**
 * @(#) CallOperationReq.java Created on 2009-2-18
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
 * The class <code>CallOperationReq</code>
 *
 * @author Link Wang
 * @version 1.0
 */
public class CallOperationReq extends MobileMsgBase {
    
    /**
     * The action type, 0x01:Hang up/0x02:Pick up/0x03:Initiate
     */
    protected byte nAction = MobileConstants.CALL_OPERATION_ACTION_HANG_UP;
    
    /**
     * The delay time for doing action
     */
    protected int nDelay = 0;
    
    /**
     * The Data field
     */
    protected String sData = "";
    
    /**
     * The constructor
     */
    public CallOperationReq() {
        super(MobileConstants.CALL_OPERATION_REQ, "CallOperationReq");
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
    
    /**
     * Sets the delay
     * @param nDelay The delay time
     */
    public void setDelay(int nDelay) {
        this.nDelay = nDelay;
        setVerify("Delay");
    }

    /**
     * Sets the delay
     * @param sDelay The delay time
     */
    public void setDelay(String sDelay) throws ToolException {
        setDelay(GenApi.strToInt(sDelay, "Delay"));
    }

    /**
     * Gets the file size
     * @return Returns file size
     */
    public String getDelay() {
        return Integer.toString(nDelay);
    }

    /**
     * Gets file size
     * @return Returns file size
     */
    public int getDelayValue() {
        return nDelay;
    }
    
    /**
     * Sets the Data
     * @param sPath The path name
     */
    public void setData(String sData) {
        this.sData = sData;
        setVerify("Data", true);
    }

    /**
     * Gets the Data name
     * @return Return the path name
     */
    public String getData() {
        return sData;
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
                    "CallOperationReq decode error: no Action in the record");
        }
        nAction = byMsg[offset];
        offset++;
        
        // Decodes the Delay field
        if ((offset + 4) > length) {
            throw new ToolException(
            "CallOperationReq decode error: no Delay in the record");
        }                      
        nDelay = ByteArray.bytesToInt(byMsg, offset);
        setPresent("Delay", true);
        offset += 4;
        
        if (MobileConstants.CALL_OPERATION_ACTION_INITIATE == nAction) {
            // Decodes the Data length field
            if ((offset + 4) > length) {
                throw new ToolException(
                "CallOperationReq decode error: no Data in the record");
            }                      
            int nLen = ByteArray.bytesToInt(byMsg, offset);
            offset += 4;
            
            // Decodes the Data value field
            if ((offset + nLen) > length) {
                throw new ToolException(
                "CallOperationReq decode error: no Data in the record");                    
            }
            sData = new String(byMsg, offset, nLen);
            offset += nLen;
            setPresent("Data", true);
        }
        
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
        
        // Encodes the Delay
        baMsg.append(nDelay);
        
        // Encodes the Data field
        if (MobileConstants.CALL_OPERATION_ACTION_INITIATE == nAction) {
            baMsg.append(sData.getBytes().length);        
            baMsg.append(sData);
        }
        
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
        
        // Formats the delay
        buf.append(sPrefix).
            append("Delay = ").
            append(nDelay).
            append(Constants.LINE_SEPARATOR);
        
        // Format the Data field
        if (MobileConstants.CALL_OPERATION_ACTION_INITIATE == nAction) {
            buf.append(sPrefix).
                append("Data = ").
                append(sData).
                append(Constants.LINE_SEPARATOR);
        }

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

        if (!(msgVerified instanceof CallOperationReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'CallOperationReq' message");
        }

        CallOperationReq obj = (CallOperationReq)msgVerified;

        doVerifyField("Action", nAction, obj, obj.nAction);
        doVerifyField("Delay", nDelay, obj, obj.nDelay);
        
        // Verify data field
        if (MobileConstants.CALL_OPERATION_ACTION_INITIATE == nAction) {
            doVerifyField("Data", sData, obj, obj.sData);
        }
        
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
        
        if (!(msgVerified instanceof CallOperationReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'CallOperationReq' message");
        }

        CallOperationReq obj = (CallOperationReq)msgVerified;

        doVerifyPresentField("Action", nAction, obj, obj.nAction);
        doVerifyPresentField("Delay", nDelay, obj, obj.nDelay);
        
        // Verify data field
        if (MobileConstants.CALL_OPERATION_ACTION_INITIATE == nAction) {
            doVerifyPresentField("Data", sData, obj, obj.sData);
        }

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

        if (!(msgVerified instanceof CallOperationReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'CallOperationReq' message");
        }

        CallOperationReq obj = (CallOperationReq)msgVerified;

        doVerifySpecifiedField("Action", nAction, obj, obj.nAction);
        doVerifySpecifiedField("Delay", nDelay, obj, obj.nDelay);
        
        // Verify data field
        if (MobileConstants.CALL_OPERATION_ACTION_INITIATE == nAction) {
            doVerifySpecifiedField("Data", sData, obj, obj.sData);
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.FieldMsgBase#createDefaultResponse()
     */
    public MsgBase createDefaultResponse() {
        
        CallOperationResp oResp = new CallOperationResp();
        
        oResp.setSequence(nSeqNum);
        
        return oResp;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj)) return false;

        if (!(obj instanceof CallOperationReq)) {
            return false;
        }

        CallOperationReq oMsgObj = (CallOperationReq)obj;
        
        if (MobileConstants.CALL_OPERATION_ACTION_INITIATE == nAction) {
            // Check data field
            return oMsgObj.nAction == nAction
                && oMsgObj.nDelay == nDelay && oMsgObj.sData.equals(sData);
        }
        
        return oMsgObj.nAction == nAction && oMsgObj.nDelay == nDelay;
    }
    
    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#isPresent(java.lang.String)
     */
    public boolean isPresent(String field) {
        return buildFields.contains(field);
    }
}
