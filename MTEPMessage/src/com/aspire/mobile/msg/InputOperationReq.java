/**
 * @(#) WapOperationReq.java Created on 2012-5-23
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
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
 * The class <code>InputOperationReq</code>
 * 
 * @author zhenghui
 * @version 1.0
 */
public class InputOperationReq extends MobileMsgBase {

    /**
     * The action type, 0x01:add text 0x02:del text
     */
    protected byte nAction = MobileConstants.INPUT_TYPE_ADD;

    /**
     * The command field
     */
    protected String sCommand = "";

    /**
     * The constructor
     */
    public InputOperationReq() {
        super(MobileConstants.INPUT_OPERATION_REQ, "InputOperationReq");
    }

    /**
     * Sets the action type
     * 
     * @param nAction
     *            The action type
     */
    public void setAction(byte nAction) {
        this.nAction = nAction;
        setVerify("Action");
    }

    /**
     * Sets the action type
     * 
     * @param sType
     *            The action type
     * @throws ToolException
     */
    public void setAction(String sAction) throws ToolException {
        setAction(GenApi.strToByte(sAction, "Action"));
    }

    /**
     * Gets the action type
     * 
     * @return Returns The action type
     */
    public String getAction() {
        return Byte.toString(nAction);
    }

    /**
     * Gets the action type
     * 
     * @return Returns the action type
     */
    public byte getActionValue() {
        return nAction;
    }

    /**
     * set the URL value
     * 
     * @param sURL
     */
    public void setCommand(String sCommand) {
        this.sCommand = sCommand;
        setVerify("Command", true);
    }

    /**
     * Gets the URL value
     * 
     * @return URL
     */
    public String getCommand() {
        return sCommand;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.mobile.msg.MobileMsgBase#decode(byte[], int, int)
     */
    public int decode(byte[] byMsg, int start, int length) throws ToolException {

        int nRead = super.decode(byMsg, start, length);
        int offset = start + nRead;

        // Decodes the Action
        if (offset >= length) {
            throw new ToolException(
                    "InputOperationReq decode error: no Action in the record");
        }
        nAction = byMsg[offset];
        offset++;

        // Decodes the Data length field
        if ((offset + 4) > length) {
            throw new ToolException(
                    "InputOperationReq decode error: no Command in the record");
        }
        int nLen = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;

        // Decodes the Data value field
        if ((offset + nLen) > length) {
            throw new ToolException(
                    "InputOperationReq decode error: no Command in the record");
        }
        sCommand = new String(byMsg, offset, nLen);
        offset += nLen;
        setPresent("Command", true);

        return offset - start;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.aspire.mobile.msg.MobileMsgBase#encode(com.aspire.util.ByteArray)
     */
    public int encode(ByteArray baMsg) throws ToolException {

        int nLen = baMsg.length();

        super.encode(baMsg);

        // Encodes the Action
        baMsg.append(nAction);

        // Encodes the Command field

        baMsg.append(sCommand.getBytes().length);
        baMsg.append(sCommand);

        return baMsg.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.mobile.msg.MobileMsgBase#format(java.lang.StringBuffer,
     * java.lang.String)
     */
    public int format(StringBuffer buf, String sPrefix) throws ToolException {

        int nLen = buf.length();

        super.format(buf, sPrefix);

        // Format the Action
        buf.append(sPrefix).append("Action = ").append(nAction)
                .append(Constants.LINE_SEPARATOR);

        // Format the Command field
        buf.append(sPrefix).append("Command = ").append(sCommand)
                .append(Constants.LINE_SEPARATOR);

        return buf.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.msg.soap.SoapMsgBase#verify(com.aspire.msg.MsgBase)
     */
    public boolean verify(MsgBase msgVerified) throws ToolException {

        if (this == msgVerified)
            return true;

        if (msgVerified == null) {
            throw new ToolException(
                    "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof InputOperationReq)) {
            throw new ToolException(
                    "Message verify error: the message to be verified is not a 'CallOperationReq' message");
        }

        InputOperationReq obj = (InputOperationReq) msgVerified;

        doVerifyField("Action", nAction, obj, obj.nAction);

        // Verify data field
        doVerifyField("Command", sCommand, obj, obj.sCommand);

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.aspire.msg.soap.SoapMsgBase#verifyPresent(com.aspire.msg.MsgBase)
     */
    public boolean verifyPresent(MsgBase msgVerified) throws ToolException {

        if (this == msgVerified)
            return true;

        if (msgVerified == null) {
            throw new ToolException(
                    "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof InputOperationReq)) {
            throw new ToolException(
                    "Message verify error: the message to be verified is not a 'WapOperationReq' message");
        }

        InputOperationReq obj = (InputOperationReq) msgVerified;

        doVerifyPresentField("Action", nAction, obj, obj.nAction);

        // Verify data field
        doVerifyPresentField("Command", sCommand, obj, obj.sCommand);

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.aspire.msg.soap.SoapMsgBase#verifySpecified(com.aspire.msg.MsgBase)
     */
    public boolean verifySpecified(MsgBase msgVerified) throws ToolException {

        if (this == msgVerified)
            return true;

        if (msgVerified == null) {
            throw new ToolException(
                    "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof InputOperationReq)) {
            throw new ToolException(
                    "Message verify error: the message to be verified is not a 'WapOperationReq' message");
        }

        InputOperationReq obj = (InputOperationReq) msgVerified;

        doVerifySpecifiedField("Action", nAction, obj, obj.nAction);

        // Verify data field
        doVerifySpecifiedField("Command", sCommand, obj, obj.sCommand);

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.msg.FieldMsgBase#createDefaultResponse()
     */
    public MsgBase createDefaultResponse() {

        InputOperationResp oResp = new InputOperationResp();

        oResp.setSequence(nSeqNum);

        return oResp;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj))
            return false;

        if (!(obj instanceof InputOperationReq)) {
            return false;
        }

        InputOperationReq oMsgObj = (InputOperationReq) obj;           

        return oMsgObj.nAction == nAction && oMsgObj.sCommand == sCommand;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.mobile.msg.MobileMsgBase#isPresent(java.lang.String)
     */
    public boolean isPresent(String field) {
        return buildFields.contains(field);
    }
}
