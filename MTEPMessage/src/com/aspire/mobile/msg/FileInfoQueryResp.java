/**
 * @(#) FileInfoQueryResp.java Created on 2009-2-16
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.msg;

import com.aspire.Constants;
import com.aspire.mobile.MobileConstants;
import com.aspire.msg.MsgBase;
import com.aspire.util.ByteArray;
import com.aspire.util.GenApi;
import com.aspire.util.GenHelper;
import com.aspire.util.ToolException;

/**
 * The class <code>FileInfoQueryResp</code>
 * 
 * @author xushengyong
 * @version 1.0
 */
public class FileInfoQueryResp extends MobileRespBase {

    /**
     * The file flag, 0:Inexistent/1:Existent
     */
    protected byte nExisted = MobileConstants.FLAG_INEXISTENT;

    /**
     * The file type, 0:Archive/1:Directory
     */
    protected byte nType = MobileConstants.FILE_TYPE_ARCHIVE;

    /**
     * The file size
     */
    protected long nFileSize = 0;

    /**
     * The create date
     */
    protected String sCreateDate = GenApi.getTime();

    /**
     * The last modified date
     */
    protected String sLastModDate = GenApi.getTime();

    /**
     * @param msgType
     * @param msgName
     */
    public FileInfoQueryResp() {
        super(MobileConstants.FILE_INFO_QUERY_RESP, "FileInfoQueryResp");
    }

    /**
     * Sets the in/existent flag
     * 
     * @param nExisted
     *            The in/existent flag
     */
    public void setExisted(byte nExisted) {
        this.nExisted = nExisted;
        setVerify("Existed");
    }

    /**
     * Sets the in/existent flag
     * 
     * @param sType
     *            The in/existent flag
     * @throws ToolException
     */
    public void setExisted(String sExisted) throws ToolException {
        setExisted(GenApi.strToByte(sExisted, "Existed"));
    }

    /**
     * Gets the in/existent flag
     * 
     * @return Returns The in/existent flag
     */
    public String getExisted() {
        return Byte.toString(nExisted);
    }

    /**
     * Gets the in/existent flag
     * 
     * @return Returns the in/existent flag
     */
    public byte getExistedValue() {
        return nExisted;
    }

    /**
     * Sets file type
     * 
     * @param nType
     *            The file type
     */
    public void setType(byte nType) {
        this.nType = nType;
        setVerify("Type");
    }

    /**
     * Sets file type
     * 
     * @param sType
     *            The file type
     * @throws ToolException
     */
    public void setType(String sType) throws ToolException {
        setType(GenApi.strToByte(sType, "Type"));
    }

    /**
     * Gets file type
     * 
     * @return Returns file type
     */
    public String getType() {
        return Byte.toString(nType);
    }

    /**
     * Gets file type
     * 
     * @return Returns file type
     */
    public byte getTypeValue() {
        return nType;
    }

    /**
     * Sets the file size
     * 
     * @param nFileSize
     *            The file size
     */
    public void setFileSize(long nFileSize) {
        this.nFileSize = nFileSize;
        setVerify("FileSize");
    }

    /**
     * Sets the file size
     * 
     * @param sFileSize
     *            The file size
     */
    public void setFileSize(String sFileSize) {
        setFileSize(Long.parseLong(sFileSize));
    }

    /**
     * Gets the file size
     * 
     * @return Returns file size
     */
    public String getFileSize() {
        return Long.toString(nFileSize);
    }

    /**
     * Gets file size
     * 
     * @return Returns file size
     */
    public long getFileSizeValue() {
        return nFileSize;
    }

    /**
     * Sets the file creation date
     * 
     * @param sCreateDate
     *            The creation date
     */
    public void setCreateDate(String sCreateDate) {
        this.sCreateDate = GenApi.formatDate(sCreateDate);
        setVerify("CreateDate");
    }

    /**
     * Gets the file creation date
     * 
     * @return Returns file creation date
     */
    public String getCreateDate() {
        return sCreateDate;
    }

    /**
     * Sets the file last modified date
     * 
     * @param sLastModDate
     *            The last modified date
     */
    public void setLastModDate(String sLastModDate) {
        this.sLastModDate = GenApi.formatDate(sLastModDate);
        setVerify("LastModDate");
    }

    /**
     * Gets file last modified date
     * 
     * @return Returns file last modified date
     */
    public String getLastModDate() {
        return sLastModDate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.mobile.msg.MobileMsgBase#decode(byte[], int, int)
     */
    public int decode(byte[] byMsg, int start, int length) throws ToolException {

        int nRead = super.decode(byMsg, start, length);
        int offset = start + nRead;

        // Decodes the in/existent flag
        if (offset >= length) {
            throw new ToolException("FileInfoQueryResp decode error: no in/existent flag in the record");
        }
        nExisted = byMsg[offset];
        setPresent("Existed", true);
        offset++;

        if (MobileConstants.FLAG_EXISTENT == nExisted) {

            // Decodes the file type
            if (offset >= length) {
                throw new ToolException("FileInfoQueryResp decode error: no Type in the record");
            }
            nType = byMsg[offset];
            setPresent("Type", true);
            offset++;

            // Decodes the file size
            if ((offset + 8) > length) {
                throw new ToolException("FileInfoQueryResp decode error: no FileSize in the record");
            }
            nFileSize = ByteArray.bytesToLong(byMsg, offset);
            setPresent("FileSize", true);
            offset += 8;

            // Decodes the create date
            if ((offset + MobileConstants.MAX_TIME_LEN) > length) {
                throw new ToolException("FileInfoQueryResp decode error: no CreateDate in the record");
            }
            sCreateDate = new String(byMsg, offset, MobileConstants.MAX_TIME_LEN).trim();
            setPresent("CreateDate", true);
            offset += MobileConstants.MAX_TIME_LEN;

            // Decodes the last modified date
            if ((offset + MobileConstants.MAX_TIME_LEN) > length) {
                throw new ToolException("FileInfoQueryResp decode error: no LastModDate in the record");
            }
            sLastModDate = new String(byMsg, offset, MobileConstants.MAX_TIME_LEN).trim();
            setPresent("LastModDate", true);
            offset += MobileConstants.MAX_TIME_LEN;
        }

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

        // Encodes the in/existent flag
        baMsg.append(nExisted);

        if (MobileConstants.FLAG_EXISTENT == nExisted) {

            // Encodes the file type
            if (isPresent("Type"))
                baMsg.append(nType);

            // Encodes the file size
            if (isPresent("FileSize"))
                baMsg.append(nFileSize);

            // Encodes the create date
            byte[] byTime = new byte[MobileConstants.MAX_TIME_LEN];
            if (isPresent("CreateDate")) {
                GenHelper.copyString(byTime, sCreateDate);
                baMsg.append(byTime);
            }

            // Encodes the last modified date
            if (isPresent("LastModDate")) {
                GenHelper.copyString(byTime, sLastModDate);
                baMsg.append(byTime);
            }

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

        // Format the in/existent flag
        buf.append(sPrefix).append("Existed = ").append(nExisted).append(Constants.LINE_SEPARATOR);

        if (MobileConstants.FLAG_EXISTENT == nExisted) {

            // Format the file type
            buf.append(sPrefix).append("Type = ").append(nType).append(Constants.LINE_SEPARATOR);

            // Format the file size
            buf.append(sPrefix).append("FileSize = ").append(nFileSize).append(Constants.LINE_SEPARATOR);

            // Format the file create date
            buf.append(sPrefix).append("CreateDate = ").append(sCreateDate).append(Constants.LINE_SEPARATOR);

            // Format the file last modified date
            buf.append(sPrefix).append("LastModDate = ").append(sLastModDate).append(Constants.LINE_SEPARATOR);

        }

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

        if (!(msgVerified instanceof FileInfoQueryResp)) {
            throw new ToolException(
                    "Message verify error: the message to be verified is not a 'FileInfoQueryReq' message");
        }

        FileInfoQueryResp obj = (FileInfoQueryResp) msgVerified;

        doVerifyField("Existed", nExisted, obj, obj.nExisted);
        if (MobileConstants.FLAG_EXISTENT == nExisted) {
            doVerifyField("Type", nType, obj, obj.nType);
            doVerifyField("FileSize", nFileSize, obj, obj.nFileSize);
            doVerifyField("CreateDate", sCreateDate, obj, obj.sCreateDate);
            doVerifyField("LastModDate", sLastModDate, obj, obj.sLastModDate);
        }

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

        if (!(msgVerified instanceof FileInfoQueryResp)) {
            throw new ToolException(
                    "Message verify error: the message to be verified is not a 'FileInfoQueryResp' message");
        }

        FileInfoQueryResp obj = (FileInfoQueryResp) msgVerified;

        doVerifyPresentField("Existed", nExisted, obj, obj.nExisted);
        if (MobileConstants.FLAG_EXISTENT == nExisted) {
            doVerifyPresentField("Type", nType, obj, obj.nType);
            doVerifyPresentField("FileSize", nFileSize, obj, obj.nFileSize);
            doVerifyPresentField("CreateDate", sCreateDate, obj, obj.sCreateDate);
            doVerifyPresentField("LastModDate", sLastModDate, obj, obj.sLastModDate);
        }

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

        if (!(msgVerified instanceof FileInfoQueryResp)) {
            throw new ToolException(
                    "Message verify error: the message to be verified is not a 'FileInfoQueryResp' message");
        }

        FileInfoQueryResp obj = (FileInfoQueryResp) msgVerified;

        doVerifySpecifiedField("Existed", nExisted, obj, obj.nExisted);
        if (MobileConstants.FLAG_EXISTENT == nExisted) {
            doVerifySpecifiedField("Type", nType, obj, obj.nType);
            doVerifySpecifiedField("FileSize", nFileSize, obj, obj.nFileSize);
            doVerifySpecifiedField("CreateDate", sCreateDate, obj, obj.sCreateDate);
            doVerifySpecifiedField("LastModDate", sLastModDate, obj, obj.sLastModDate);
        }

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

        if (!(obj instanceof FileInfoQueryResp)) {
            return false;
        }

        FileInfoQueryResp oMsgObj = (FileInfoQueryResp) obj;

        if (oMsgObj.nExisted == nExisted) {
            if (MobileConstants.FLAG_EXISTENT == nExisted) {
                return oMsgObj.nType == nType && oMsgObj.nFileSize == nFileSize
                        && oMsgObj.sCreateDate.equals(sCreateDate) && oMsgObj.sLastModDate.equals(sLastModDate);
            } else {
                return true;
            }
        } else {
            return false;
        }
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
