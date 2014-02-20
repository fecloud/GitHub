/**
 * @(#) FileInfoQueryReq.java Created on 2009-2-16
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
 * The class <code>FileInfoQueryReq</code>
 *
 * @author xushengyong
 * @version 1.0
 */
public class FileInfoQueryReq extends MobileMsgBase {
    /**
     * The path name: "Path"
     */
    protected String sPath = "";
    
    /**
     * The constructor
     */
    public FileInfoQueryReq() {
        super(MobileConstants.FILE_INFO_QUERY_REQ, "FileInfoQueryReq");
    }
    
    /**
     * Sets the path
     * @param sPath The path name
     */
    public void setPath(String sPath) {
        this.sPath = sPath;
        setVerify("Path", true);
    }

    /**
     * Gets the path name
     * @return Return the path name
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

        // Decode the path
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

        // Encodes the path
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

        // Format the path
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

        if (this == msgVerified) return true;
        
        if (msgVerified == null) {
            throw new ToolException(
                 "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof FileInfoQueryReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'FileInfoQueryReq' message");
        }

        FileInfoQueryReq obj = (FileInfoQueryReq)msgVerified;

        doVerifyField("Path", sPath, obj, obj.sPath);
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
        
        if (!(msgVerified instanceof FileInfoQueryReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'FileInfoQueryReq' message");
        }

        FileInfoQueryReq obj = (FileInfoQueryReq)msgVerified;

        doVerifyPresentField("Path", sPath, obj, obj.sPath);

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

        if (!(msgVerified instanceof FileInfoQueryReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'FileInfoQueryReq' message");
        }

        FileInfoQueryReq obj = (FileInfoQueryReq)msgVerified;

        doVerifySpecifiedField("Path", sPath, obj, obj.sPath);

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.FieldMsgBase#createDefaultResponse()
     */
    public MsgBase createDefaultResponse() {
        
        FileInfoQueryResp oResp = new FileInfoQueryResp();
        
        oResp.setSequence(nSeqNum);
        
        return oResp;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj)) return false;

        if (!(obj instanceof FileInfoQueryReq)) {
            return false;
        }

        FileInfoQueryReq oMsgObj = (FileInfoQueryReq)obj;

        return oMsgObj.sPath.equals(sPath);
    }
   
}
