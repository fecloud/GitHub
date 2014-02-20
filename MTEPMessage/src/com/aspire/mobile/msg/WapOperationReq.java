/**
 * @(#) WapOperationReq.java Created on 2012-5-8
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
 * The class <code>WapOperationReq</code>
 * 
 * @author zhenghui
 * @version 1.0
 */
public class WapOperationReq extends MobileMsgBase {

    /**
     * The action type, 0x01:start wap
     */
    protected byte nAction = MobileConstants.WAP_OPERATION_ACTION_START;

    /**
     * The URL field
     */
    protected String sURL = "";

    /**
     * The Key field
     */
    protected String sKey = "";
    /**
     * The value field
     */
    protected String sValue = "";

    /**
     * The constructor
     */
    public WapOperationReq() {
        super(MobileConstants.WAP_OPERATION_REQ, "WapOperationReq");
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
    public void setURL(String sURL) {
        this.sURL = sURL;
        setVerify("URL", true);
    }

    /**
     * Gets the URL value
     * 
     * @return URL
     */
    public String getURL() {
        return sURL;
    }

    /**
     * Gets the Key value
     * 
     * @return
     */
    public String getKey() {
        return sKey;
    }

    /**
     * set the key value
     * 
     * @param sKey
     */
    public void setKey(String sKey) {
        this.sKey = sKey;
        setVerify("KEY", true);
    }

    /**
     * Get the Value value
     * 
     * @return
     */
    public String getValue() {
        return sValue;
    }

    /**
     * Set the Value value
     * 
     * @param sValue
     */
    public void setValue(String sValue) {
        this.sValue = sValue;
        setVerify("VALUE", true);
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
            throw new ToolException("CallOperationReq decode error: no Action in the record");
        }
        nAction = byMsg[offset];
        offset++;

        if (MobileConstants.WAP_OPERATION_ACTION_START == nAction
                || MobileConstants.WAP_OPERATION_ACTION_WAP_DRIVER == nAction) {
            // Decodes the Data length field
            if ((offset + 4) > length) {
                throw new ToolException("WapOperationReq decode error: no URL in the record");
            }
            int nLen = ByteArray.bytesToInt(byMsg, offset);
            offset += 4;

            // Decodes the Data value field
            if ((offset + nLen) > length) {
                throw new ToolException("WapOperationReq decode error: no URL in the record");
            }
            sURL = new String(byMsg, offset, nLen);
            offset += nLen;
            setPresent("URL", true);
        } else if (MobileConstants.WAP_OPERATION_ACTION_FIND == nAction
                || MobileConstants.WAP_OPERATION_ACTION_CLICK == nAction) {
            if ((offset + 4) > length) {
                throw new ToolException("WapOperationReq decode error: no URL in the record");
            }
            int nLen = ByteArray.bytesToInt(byMsg, offset);
            offset += 4;

            // Decodes the Data value field
            if ((offset + nLen) > length) {
                throw new ToolException("WapOperationReq decode error: no URL in the record");
            }
            sKey = new String(byMsg, offset, nLen);
            offset += nLen;
            setPresent("KEY", true);

            // read value field

            if ((offset + 4) > length) {
                throw new ToolException("WapOperationReq decode error: no URL in the record");
            }
            nLen = ByteArray.bytesToInt(byMsg, offset);
            offset += 4;

            // Decodes the Data value field
            if ((offset + nLen) > length) {
                throw new ToolException("WapOperationReq decode error: no URL in the record");
            }
            sValue = new String(byMsg, offset, nLen);
            offset += nLen;
            setPresent("VALUE", true);

        }
        // MobileConstants.WAP_OPERATION_ACTION_CLOSE_DRIVER == nAction nothing

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

        // Encodes the Action
        baMsg.append(nAction);

        // Encodes the URL field
        if (MobileConstants.WAP_OPERATION_ACTION_START == nAction
                || MobileConstants.WAP_OPERATION_ACTION_WAP_DRIVER == nAction) {
            baMsg.append(sURL.getBytes().length);
            baMsg.append(sURL);
        } else if (MobileConstants.WAP_OPERATION_ACTION_FIND == nAction
                || MobileConstants.WAP_OPERATION_ACTION_CLICK == nAction) {
            baMsg.append(sKey.getBytes().length);
            baMsg.append(sKey);
            baMsg.append(sValue.getBytes().length);
            baMsg.append(sValue);
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

        // Format the Action
        buf.append(sPrefix).append("Action = ").append(nAction).append(Constants.LINE_SEPARATOR);

        // Format the Data field
        if (MobileConstants.WAP_OPERATION_ACTION_START == nAction) {
            buf.append(sPrefix).append("URL = ").append(sURL).append(Constants.LINE_SEPARATOR);
        }

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
            throw new ToolException("Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof WapOperationReq)) {
            throw new ToolException(
                    "Message verify error: the message to be verified is not a 'CallOperationReq' message");
        }

        WapOperationReq obj = (WapOperationReq) msgVerified;

        doVerifyField("Action", nAction, obj, obj.nAction);

        // Verify data field
        if (MobileConstants.WAP_OPERATION_ACTION_START == nAction) {
            doVerifyField("URL", sURL, obj, obj.sURL);
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.msg.soap.SoapMsgBase#verifyPresent(com.aspire.msg.MsgBase)
     */
    public boolean verifyPresent(MsgBase msgVerified) throws ToolException {

        if (this == msgVerified)
            return true;

        if (msgVerified == null) {
            throw new ToolException("Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof WapOperationReq)) {
            throw new ToolException(
                    "Message verify error: the message to be verified is not a 'WapOperationReq' message");
        }

        WapOperationReq obj = (WapOperationReq) msgVerified;

        doVerifyPresentField("Action", nAction, obj, obj.nAction);

        // Verify data field
        if (MobileConstants.WAP_OPERATION_ACTION_START == nAction) {
            doVerifyPresentField("URL", sURL, obj, obj.sURL);
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.msg.soap.SoapMsgBase#verifySpecified(com.aspire.msg.MsgBase)
     */
    public boolean verifySpecified(MsgBase msgVerified) throws ToolException {

        if (this == msgVerified)
            return true;

        if (msgVerified == null) {
            throw new ToolException("Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof WapOperationReq)) {
            throw new ToolException(
                    "Message verify error: the message to be verified is not a 'WapOperationReq' message");
        }

        WapOperationReq obj = (WapOperationReq) msgVerified;

        doVerifySpecifiedField("Action", nAction, obj, obj.nAction);

        // Verify data field
        if (MobileConstants.CALL_OPERATION_ACTION_INITIATE == nAction) {
            doVerifySpecifiedField("URL", sURL, obj, obj.sURL);
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.msg.FieldMsgBase#createDefaultResponse()
     */
    public MsgBase createDefaultResponse() {

        WapOperationResp oResp = new WapOperationResp();

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

        if (!(obj instanceof WapOperationReq)) {
            return false;
        }

        WapOperationReq oMsgObj = (WapOperationReq) obj;

        if (MobileConstants.WAP_OPERATION_ACTION_START == nAction) {
            // Check url field
            return oMsgObj.nAction == nAction && oMsgObj.sURL.equals(sURL);
        }

        return oMsgObj.nAction == nAction && oMsgObj.sURL == sURL;
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
