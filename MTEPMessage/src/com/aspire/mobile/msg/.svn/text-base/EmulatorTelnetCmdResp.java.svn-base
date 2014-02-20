/**
 * @(#) EmulatorTelnetCmdResp.java Created on 2009-2-18
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
 * The class <code>EmulatorTelnetCmdResp</code>
 * 
 * @author xushengyong
 * @version 1.0
 */
public class EmulatorTelnetCmdResp extends MobileRespBase {

    /**
     * The Msg
     */
    protected String sMsg = "";

    /**
     * The constructor
     */
    public EmulatorTelnetCmdResp() {
        super(MobileConstants.EMULATOR_TELNET_CMD_RESP, "EmulatorTelnetCmdResp");
    }

    /**
     * Sets the Msg
     * 
     * @param sMsg
     *            The Msg
     */
    public void setMsg(String sMsg) {
        this.sMsg = sMsg;
        setVerify("Msg", true);
    }

    /**
     * Gets the Msg
     * 
     * @return Return the Msg
     */
    public String getMsg() {
        return sMsg;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.mobile.msg.MobileMsgBase#decode(byte[], int, int)
     */
    public int decode(byte[] byMsg, int start, int length) throws ToolException {

        int nRead = super.decode(byMsg, start, length);
        int offset = start + nRead;

        // Decodes the Msg length field
        if ((offset + 4) > length) {
            throw new ToolException("EmulatorTelnetCmdResp decode error: no Msg in the record");
        }
        int nLen = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;

        // Decodes the Msg value field
        if ((offset + nLen) > length) {
            throw new ToolException("EmulatorTelnetCmdResp decode error: no Msg in the record");
        }
        sMsg = new String(byMsg, offset, nLen);
        offset += nLen;

        return offset - start;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.mobile.msg.MobileMsgBase#encode(com.aspire.util.ByteArray)
     */
    public int encode(ByteArray baMsg) throws ToolException {

        int nLen = baMsg.length();

        super.encode(baMsg);
        if (isPresent("Msg")) {
            // Encodes the Msg
            baMsg.append(sMsg.getBytes().length);
            baMsg.append(sMsg);
        }

        return baMsg.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.mobile.msg.MobileMsgBase#format(java.lang.StringBuffer, java.lang.String)
     */
    public int format(StringBuffer buf, String sPrefix) throws ToolException {

        int nLen = buf.length();

        super.format(buf, sPrefix);

        // Format the Msg
        buf.append(sPrefix).append("Msg = ").append(sMsg).append(Constants.LINE_SEPARATOR);

        return buf.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.msg.soap.SoapMsgBase#verify(com.aspire.msg.MsgBase)
     */
    public boolean verify(MsgBase msgVerified) throws ToolException {

        super.verify(msgVerified);

        if (msgVerified == null) {
            throw new ToolException("Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof EmulatorTelnetCmdResp)) {
            throw new ToolException(
                    "Message verify error: the message to be verified is not a 'FileInfoQueryReq' message");
        }

        EmulatorTelnetCmdResp obj = (EmulatorTelnetCmdResp) msgVerified;

        doVerifyField("Msg", sMsg, obj, obj.sMsg);

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.msg.soap.SoapMsgBase#verifyPresent(com.aspire.msg.MsgBase)
     */
    public boolean verifyPresent(MsgBase msgVerified) throws ToolException {

        super.verify(msgVerified);

        if (msgVerified == null) {
            throw new ToolException("Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof EmulatorTelnetCmdResp)) {
            throw new ToolException(
                    "Message verify error: the message to be verified is not a 'EmulatorTelnetCmdResp' message");
        }

        EmulatorTelnetCmdResp obj = (EmulatorTelnetCmdResp) msgVerified;

        doVerifyPresentField("Msg", sMsg, obj, obj.sMsg);

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.msg.soap.SoapMsgBase#verifySpecified(com.aspire.msg.MsgBase)
     */
    public boolean verifySpecified(MsgBase msgVerified) throws ToolException {

        super.verify(msgVerified);

        if (msgVerified == null) {
            throw new ToolException("Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof EmulatorTelnetCmdResp)) {
            throw new ToolException(
                    "Message verify error: the message to be verified is not a 'EmulatorTelnetCmdResp' message");
        }

        EmulatorTelnetCmdResp obj = (EmulatorTelnetCmdResp) msgVerified;

        doVerifySpecifiedField("Msg", sMsg, obj, obj.sMsg);

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj))
            return false;

        if (!(obj instanceof EmulatorTelnetCmdResp)) {
            return false;
        }

        EmulatorTelnetCmdResp oMsgObj = (EmulatorTelnetCmdResp) obj;

        return oMsgObj.sMsg.equals(sMsg);
    }

}
