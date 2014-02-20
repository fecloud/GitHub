/**
 * @(#) DirInfoQueryResp.java Created on 2009-2-16
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.msg;

import java.util.ArrayList;

import com.aspire.Constants;
import com.aspire.mobile.MobileConstants;
import com.aspire.mobile.element.MobileFileInfo;
import com.aspire.msg.MsgBase;
import com.aspire.util.ByteArray;
import com.aspire.util.GenApi;
import com.aspire.util.ToolException;

/**
 * The class <code>DirInfoQueryResp</code>
 *
 * @author xuyong
 * @version 1.0
 */
public class DirInfoQueryResp extends MobileRespBase {

    /**
     * The file number
     */
    protected int nFileNumber = 0;

    /**
     * The file information
     */
    protected ArrayList<MobileFileInfo> aFileInfos = new ArrayList<MobileFileInfo>();

    /**
     * The constructor
     */
    public DirInfoQueryResp() {
        super(MobileConstants.DIR_INFO_QUERY_RESP, "DirInfoQueryResp");
    }

    /**
     * Sets file number
     * @param nFileNumber The file number
     */
    public void setFileNumber(int nFileNumber) {
        this.nFileNumber = nFileNumber;
        setVerify("FileNumber");
    }
    
    /**
     * Sets file number
     * @param sFileNumber The file number
     * @throws ToolException 
     */
    public void setFileNumber(String sFileNumber) throws ToolException {
        setFileNumber(GenApi.strToInt(sFileNumber, "FileNumber"));
    }

    /**
     * Gets file number
     * @return Returns file number
     */
    public String getFileNumber() {
        return Integer.toString(nFileNumber);
    }

    /**
     * Gets file number
     * @return Returns file type
     */
    public int getFileNumberValue() {
        return nFileNumber;
    }

    /**
     * Sets file information
     * @param aFileInfos The file information
     */
    public void setFileInfo(ArrayList<MobileFileInfo> aFileInfos) {
        this.aFileInfos = aFileInfos;
        setVerify("FileInfo", true);
    }

    /**
     * Gets file information
     * @return Returns file information
     */
    public ArrayList<MobileFileInfo> getFileInfo() {
        return aFileInfos;
    }

    /**
     * Adds file information
     * @param oFileInfo The file information object to be added
     */
    public void addFileInfo(MobileFileInfo oFileInfo) {
        if (aFileInfos.contains(oFileInfo)) return;
        aFileInfos.add(oFileInfo);
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileRespBase#decode(byte[], int, int)
     */
    public int decode(byte[] byMsg, int start, int length) throws ToolException {
        int nRead = super.decode(byMsg, start, length);
        int offset = start + nRead;

        // Decodes file number
        if ((offset + 4) > length) {
            throw new ToolException(
                    "DirInfoQueryResp decode error: no FileNumber in the record");
        }
        nFileNumber = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;

        // Decodes fileInfo
        setPresent("FileInfo", nFileNumber > 0);
        for (int i = 0; i < nFileNumber; i++) {
            if (offset >= length) {
                break;
            }
            MobileFileInfo oInfo = new MobileFileInfo();
            offset += oInfo.decode(byMsg, offset, length);
            aFileInfos.add(oInfo);
        }

        return offset - start;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileRespBase#encode(com.aspire.util.ByteArray)
     */
    public int encode(ByteArray baMsg) throws ToolException {

        int nLen = baMsg.length();

        super.encode(baMsg);

        // Encodes file number
        baMsg.append(aFileInfos.size());

        // Encodes file information
        for (int i = 0; i < aFileInfos.size(); i++) {
            aFileInfos.get(i).encode(baMsg);
        }

        return baMsg.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileRespBase#format(java.lang.StringBuffer, java.lang.String)
     */
    public int format(StringBuffer buf, String sPrefix) throws ToolException {

        int nLen = buf.length();

        super.format(buf, sPrefix);

        // Format file number
        buf.append(sPrefix).
            append("FileNumber = ").
            append(nFileNumber).
            append(Constants.LINE_SEPARATOR);

        // Format file information
        String sInfoPrefix = sPrefix + "    ";
        for (int i = 0; i < aFileInfos.size(); i++) {
            buf.append(sPrefix).
                append("FileInfo[No ").
                append(i).
                append("]").
                append(Constants.LINE_SEPARATOR).
                append(sPrefix).
                append("{").
                append(Constants.LINE_SEPARATOR);
            aFileInfos.get(i).format(buf, sInfoPrefix);
            buf.append(sPrefix).
                append("}").
                append(Constants.LINE_SEPARATOR);
        }

        return buf.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileRespBase#verify(com.aspire.msg.MsgBase)
     */
    public boolean verify(MsgBase msgVerified) throws ToolException {

        super.verify(msgVerified);

        if (msgVerified == null) {
            throw new ToolException(
                 "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof DirInfoQueryResp)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'DirInfoQueryResp' message");
        }

        DirInfoQueryResp obj = (DirInfoQueryResp)msgVerified;

        doVerifyField("FileNumber", nFileNumber, obj, obj.nFileNumber);
        doVerifyField("FileInfo", aFileInfos, obj, obj.aFileInfos, false);

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileRespBase#verifyPresent(com.aspire.msg.MsgBase)
     */
    public boolean verifyPresent(MsgBase msgVerified) throws ToolException {

        if (this == msgVerified) return true;

        if (msgVerified == null) {
            throw new ToolException(
                 "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof DirInfoQueryResp)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'DirInfoQueryResp' message");
        }

        DirInfoQueryResp obj = (DirInfoQueryResp)msgVerified;

        doVerifyPresentField("FileNumber", nFileNumber, obj, obj.nFileNumber);
        doVerifyPresentField("FileInfo", aFileInfos, obj, obj.aFileInfos, false);

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileRespBase#verifySpecified(com.aspire.msg.MsgBase)
     */
    public boolean verifySpecified(MsgBase msgVerified) throws ToolException {

        super.verifySpecified(msgVerified);

        if (msgVerified == null) {
            throw new ToolException(
                 "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof DirInfoQueryResp)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'DirInfoQueryResp' message");
        }

        DirInfoQueryResp obj = (DirInfoQueryResp)msgVerified;

        doVerifySpecifiedField("FileNumber", nFileNumber, obj, obj.nFileNumber);
        doVerifySpecifiedField("FileInfo", aFileInfos, obj, obj.aFileInfos, false);

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileRespBase#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj)) return false;

        if (!(obj instanceof DirInfoQueryResp)) {
            return false;
        }

        DirInfoQueryResp oMsgObj = (DirInfoQueryResp)obj;

        return oMsgObj.nFileNumber == nFileNumber &&
            oMsgObj.aFileInfos.equals(aFileInfos);
    }
    
    /**
     * This method is used to clone a DirInfoQueryResp object
     */
    public Object clone() throws CloneNotSupportedException {
        
        DirInfoQueryResp obj = (DirInfoQueryResp)super.clone();
        
        obj.aFileInfos = new ArrayList<MobileFileInfo>();
        
        for (int i = 0; i < aFileInfos.size(); i++) {
            obj.aFileInfos.add((MobileFileInfo)aFileInfos.get(i).clone());
        }
        
        return obj;
    }
}
