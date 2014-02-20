/**
 * @(#) EmulatorTelnetCmdReq.java Created on 2009-2-18
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
 * The class <code>EmulatorTelnetCmdReq</code>
 *
 * @author xushengyong
 * @version 1.0
 */
public class EmulatorTelnetCmdReq extends MobileMsgBase {
    
    /**
     * The Timeout
     */
    protected int nTimeout = 0;
    
    /**
     * The CmdString
     */
    protected String sCmdString = "";

    /**
     * The constructor
     */
    public EmulatorTelnetCmdReq() {
        super(MobileConstants.EMULATOR_TELNET_CMD_REQ, "EmulatorTelnetCmdReq");
    }
    
    /**
     * Sets the Timeout
     * @param nTimeout The Timeout
     * @throws ToolException 
     */    
    public void setTimeout(int nTimeout) throws ToolException {
        this.nTimeout = nTimeout;
        setVerify("Timeout", true);
    }
    
    /**
     * Sets the Timeout
     * @param sTimeout The Timeout
     * @throws ToolException 
     */
    public void setTimeout(String sTimeout) throws ToolException {
        setTimeout(GenApi.strToInt(sTimeout, "Timeout"));
    }
    
    /**
     * Gets the Timeout
     * @return Returns the Timeout
     */
    public String getTimeout() {
        return Integer.toString(nTimeout);
    }

    /**
     * Gets the Timeout
     * @return Returns the Timeout
     */
    public int getTimeoutValue() {
        return nTimeout;
    }
    
    /**
     * Sets the CmdString
     * @param sCmdString The CmdString
     */
    public void setCmdString(String sCmdString) {
        this.sCmdString = sCmdString;
        setVerify("CmdString", true);
    }
    
    /**
     * Gets the CmdString
     * @return Return the CmdString
     */
    public String getCmdString() {
        return sCmdString;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#decode(byte[], int, int)
     */
    public int decode(byte[] byMsg, int start, int length) throws ToolException {
        
        int nRead = super.decode(byMsg, start, length);
        int offset = start + nRead;
        
        // Decodes the Timeout
        if ((offset + 4) > length) {
            throw new ToolException(
            "EmulatorTelnetCmdReq decode error: no Timeout in the record");
        } 
        nTimeout = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;
             
        // Decodes the CmdString length field
        if ((offset + 4) > length) {
            throw new ToolException(
            "EmulatorTelnetCmdReq decode error: no CmdString in the record");
        }                      
        int nLen = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;
        
        // Decodes the CmdString value field
        if ((offset + nLen) > length) {
            throw new ToolException(
            "EmulatorTelnetCmdReq decode error: no CmdString in the record");                    
        }
        sCmdString = new String(byMsg, offset, nLen);
        offset += nLen;
        
        return offset - start;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#encode(com.aspire.util.ByteArray)
     */
    public int encode(ByteArray baMsg) throws ToolException {

        int nLen = baMsg.length();

        super.encode(baMsg);

        // Encodes the Timeout
        baMsg.append(nTimeout);

        // Encodes the CmdString
        baMsg.append(sCmdString.getBytes().length);        
        baMsg.append(sCmdString);
        
        return baMsg.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#format(java.lang.StringBuffer, java.lang.String)
     */
    public int format(StringBuffer buf, String sPrefix) throws ToolException {

        int nLen = buf.length();

        super.format(buf, sPrefix);
        
        // Format the Timeout
        buf.append(sPrefix).
            append("Timeout = ").
            append(nTimeout).
            append(Constants.LINE_SEPARATOR);
        
        // Format the CmdString
        buf.append(sPrefix).
            append("CmdString = ").
            append(sCmdString).
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

        if (!(msgVerified instanceof EmulatorTelnetCmdReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'EmulatorTelnetCmdReq' message");
        }

        EmulatorTelnetCmdReq obj = (EmulatorTelnetCmdReq)msgVerified;

        doVerifyField("Timeout", nTimeout, obj, obj.nTimeout);
        doVerifyField("CmdString", sCmdString, obj, obj.sCmdString);
        
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
        
        if (!(msgVerified instanceof EmulatorTelnetCmdReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'EmulatorTelnetCmdReq' message");
        }

        EmulatorTelnetCmdReq obj = (EmulatorTelnetCmdReq)msgVerified;

        doVerifyPresentField("Timeout", nTimeout, obj, obj.nTimeout);
        doVerifyPresentField("CmdString", sCmdString, obj, obj.sCmdString);

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

        if (!(msgVerified instanceof EmulatorTelnetCmdReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'EmulatorTelnetCmdReq' message");
        }

        EmulatorTelnetCmdReq obj = (EmulatorTelnetCmdReq)msgVerified;
        
        doVerifySpecifiedField("Timeout", nTimeout, obj, obj.nTimeout);
        doVerifySpecifiedField("CmdString", sCmdString, obj, obj.sCmdString);

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.FieldMsgBase#createDefaultResponse()
     */
    public MsgBase createDefaultResponse() {
        
        EmulatorTelnetCmdResp oResp = new EmulatorTelnetCmdResp();
        
        oResp.setSequence(nSeqNum);
        
        return oResp;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj)) return false;

        if (!(obj instanceof EmulatorTelnetCmdReq)) {
            return false;
        }

        EmulatorTelnetCmdReq oMsgObj = (EmulatorTelnetCmdReq)obj;
        
        return oMsgObj.nTimeout == nTimeout &&
        oMsgObj.sCmdString.equals(sCmdString);
    }
    
}
