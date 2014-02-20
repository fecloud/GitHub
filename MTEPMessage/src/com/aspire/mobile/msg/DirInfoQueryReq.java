/**
 * @(#) DirInfoQueryReq.java Created on 2009-2-16
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
 * The class <code>DirInfoQueryReq</code>
 *
 * @author xuyong
 * @version 1.0
 */
public class DirInfoQueryReq extends MobileMsgBase {
    /**
     * The path name: path
     */
    protected String sPath = "";

    /**
     * The constructor
     */
    public DirInfoQueryReq() {
        super(MobileConstants.DIR_INFO_QUERY_REQ, "DirInfoQueryReq");
    }

    /**
     * Sets path
     * @param sPath The path name
     */
    public void setPath(String sPath) {
        this.sPath = sPath;
        setVerify("Path", true);
    }

    /**
     * Gets path name
     * @return Return path name
     */
    public String getPath() {
        return sPath;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#decode(byte[], int, int)
     */
    public int decode(byte[] byMsg, int start, int length) throws ToolException {

        int nRead = super.decode(byMsg, start, length);
        int offset = start + nRead;

        // Decode Path
        int nPathLen = length - offset;
        sPath = new String(byMsg, offset, nPathLen);
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

        // Encodes Path
        baMsg.append(sPath);

        return baMsg.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#format(java.lang.StringBuffer, java.lang.String)
     */
    public int format(StringBuffer buf, String sPrefix) throws ToolException {

        int nLen = buf.length();

        super.format(buf, sPrefix);

        // Format Path
        buf.append(sPrefix).
            append("Path = ").
            append(sPath).
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

        if (!(msgVerified instanceof DirInfoQueryReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'DirInfoQueryReq' message");
        }

        DirInfoQueryReq obj = (DirInfoQueryReq)msgVerified;

        doVerifyField("Path", sPath, obj, obj.sPath);
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

        if (!(msgVerified instanceof DirInfoQueryReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'DirInfoQueryReq' message");
        }

        DirInfoQueryReq obj = (DirInfoQueryReq)msgVerified;

        doVerifyPresentField("Path", sPath, obj, obj.sPath);

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

        if (!(msgVerified instanceof DirInfoQueryReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'DirInfoQueryReq' message");
        }

        DirInfoQueryReq obj = (DirInfoQueryReq)msgVerified;

        doVerifySpecifiedField("Path", sPath, obj, obj.sPath);

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.FieldMsgBase#createDefaultResponse()
     */
    public MsgBase createDefaultResponse() {
        DirInfoQueryResp oResp = new DirInfoQueryResp();
        oResp.setSequence(nSeqNum);
        return oResp;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj)) return false;

        if (!(obj instanceof DirInfoQueryReq)) {
            return false;
        }

        DirInfoQueryReq oMsgObj = (DirInfoQueryReq)obj;

        return oMsgObj.sPath.equals(sPath);
    }
}
