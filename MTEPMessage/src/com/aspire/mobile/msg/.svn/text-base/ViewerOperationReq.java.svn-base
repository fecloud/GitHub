/**
 * @(#) ViewerOperationReq.java Created on 2012-5-8
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
 * The class <code>ViewerOperationReq</code>
 * 
 * @author zhenghui
 * @version 1.0
 */
public class ViewerOperationReq extends MobileMsgBase {

    /**
     * The action type, 0x01:start wap
     */
    protected byte nAction = MobileConstants.VIEWER_OPERATION_ACTION_GET;

    /**
     * The Path field
     */
    protected String sPath = "";

    /**
     * The constructor
     */
    public ViewerOperationReq() {
        super(MobileConstants.VIEWER_OPERATION_REQ, "ViewerOperationReq");
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
     * @return the sPath
     */
    public String getPath() {
        return sPath;
    }

    /**
     * @param sPath the sPath to set
     */
    public void setPath(String sPath) {
        this.sPath = sPath;
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
                    "ViewerOperationReq decode error: no Action in the record");
        }
        nAction = byMsg[offset];
        offset++;
        
        if(nAction == MobileConstants.VIEWER_OPERATION_ACTION_SAVE){
            if ((offset + 4) > length) {
                throw new ToolException(
                        "ViewerOperationReq decode error: no Path in the record");
            }
            int nLen = ByteArray.bytesToInt(byMsg, offset);
            offset += 4;
            
            if ((offset + nLen) > length) {
                throw new ToolException(
                        "ViewerOperationReq decode error: no Path in the record");
            }
            sPath = new String(byMsg, offset, nLen);
            offset += nLen;
            setPresent("Path", true);
        }
        
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
        
        if(nAction == MobileConstants.VIEWER_OPERATION_ACTION_SAVE){
            baMsg.append(sPath.getBytes().length);
            baMsg.append(sPath);
        }

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
        
        if(nAction == MobileConstants.VIEWER_OPERATION_ACTION_SAVE){
            buf.append(sPrefix).append("Path = ").append(sPath)
            .append(Constants.LINE_SEPARATOR); 
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
            throw new ToolException(
                    "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof ViewerOperationReq)) {
            throw new ToolException(
                    "Message verify error: the message to be verified is not a 'ViewerOperationReq' message");
        }

        ViewerOperationReq obj = (ViewerOperationReq) msgVerified;

        doVerifyField("Action", nAction, obj, obj.nAction);       
        doVerifyField("Path", sPath, obj, obj.sPath);       

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

        if (!(msgVerified instanceof ViewerOperationReq)) {
            throw new ToolException(
                    "Message verify error: the message to be verified is not a 'ViewerOperationReq' message");
        }

        ViewerOperationReq obj = (ViewerOperationReq) msgVerified;

        doVerifyPresentField("Action", nAction, obj, obj.nAction);
        doVerifyPresentField("Path", sPath, obj, obj.sPath);

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

        if (!(msgVerified instanceof ViewerOperationReq)) {
            throw new ToolException(
                    "Message verify error: the message to be verified is not a 'ViewerOperationReq' message");
        }

        ViewerOperationReq obj = (ViewerOperationReq) msgVerified;

        doVerifySpecifiedField("Action", nAction, obj, obj.nAction);

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.msg.FieldMsgBase#createDefaultResponse()
     */
    public MsgBase createDefaultResponse() {

        ViewerOperationResp oResp = new ViewerOperationResp();

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

        if (!(obj instanceof ViewerOperationReq)) {
            return false;
        }

        ViewerOperationReq oMsgObj = (ViewerOperationReq) obj;

        return oMsgObj.nAction == nAction;
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
