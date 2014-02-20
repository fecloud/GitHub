/**
 * @(#) MobileFileInfo.java Created on 2009-2-16
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.element;

import com.aspire.Constants;
import com.aspire.mobile.MobileConstants;
import com.aspire.msg.MsgField;
import com.aspire.util.ByteArray;
import com.aspire.util.GenApi;
import com.aspire.util.GenHelper;
import com.aspire.util.ToolException;

/**
 * The class <code>MobileFileInfo</code>
 *
 * @author xuyong
 * @version 1.0
 */
public class MobileFileInfo extends MobileElementBase {

    /**
     * The file name
     */
    protected String sFileName = "";

    /**
     * The file type, 0: file; 1: dir
     */
    protected byte nType = 0;

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
     * The constructor
     */
    public MobileFileInfo() {
    }

    /**
     * Sets file name
     * @param sFileName The file name
     */
    public void setFileName(String sFileName) {
        this.sFileName = sFileName;
        setVerify("FileName");
    }

    /**
     * Gets file name
     * @return Returns file name
     */
    public String getFileName() {
        return sFileName;
    }

    /**
     * Sets file type
     * @param nType The file type
     */
    public void setType(byte nType) {
        this.nType = nType;
        setVerify("Type");
    }

    /**
     * Sets file type
     * @param sType The file type
     * @throws ToolException 
     */
    public void setType(String sType) throws ToolException {
        setType(GenApi.strToByte(sType, "Type"));
    }

    /**
     * Gets file type
     * @return Returns file type
     */
    public String getType() {
        return Byte.toString(nType);
    }

    /**
     * Gets file type
     * @return Returns file type
     */
    public byte getTypeValue() {
        return nType;
    }

    /**
     * Sets file size
     * @param nFileSize The file size
     */
    public void setFileSize(long nFileSize) {
        this.nFileSize = nFileSize;
        setVerify("FileSize");
    }

    /**
     * Sets file size
     * @param sFileSize The file size
     */
    public void setFileSize(String sFileSize) throws ToolException {
        setFileSize(GenApi.strToLong(sFileSize, "FileSize"));
    }

    /**
     * Gets file size
     * @return Returns file size
     */
    public String getFileSize() {
        return Long.toString(nFileSize);
    }

    /**
     * Gets file size
     * @return Returns file size
     */
    public long getFileSizeValue() {
        return nFileSize;
    }

    /**
     * Sets file creation date
     * @param sCreateDate The creation date
     */
    public void setCreateDate(String sCreateDate) {
        this.sCreateDate = GenApi.formatDate(sCreateDate);
        setVerify("CreateDate");
    }

    /**
     * Gets file creation date
     * @return Returns file creation date
     */
    public String getCreateDate() {
        return sCreateDate;
    }

    /**
     * Sets file last modified date
     * @param sLastModDate The last modified date
     */
    public void setLastModDate(String sLastModDate) {
        this.sLastModDate = GenApi.formatDate(sLastModDate);
        setVerify("LastModDate");
    }

    /**
     * Gets file last modified date
     * @return Returns file last modified date
     */
    public String getLastModDate() {
        return sLastModDate;
    }

    /**
     * 对字段进行解码，将字段序列解码成消息对象
     * 
     * @param byMsg 要解码的字段
     * @param start 消息的起始索引
     * @param length 消息总长度
     * @return 解码成功后，返回从消息中读取的总长度
     * @throws 当发生错误时，抛出ToolException异常
     */
    public int decode(byte[] byMsg, int start, int length) throws ToolException {

        int offset = start;

        // Decodes file name
        int nLen = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;

        if (offset + nLen > length) {
            throw new ToolException(
                    "MobileFileInfo Decode error: no file name in the record.");
        }

        sFileName = new String(byMsg, offset, nLen);
        offset += nLen;
        
        // Decodes type
        if (offset >= length) {
            throw new ToolException(
                    "MobileFileInfo Decode error: no file type in the record");
        }
        nType = byMsg[offset];
        offset++;
        
        // Decodes file size
        nFileSize = ByteArray.bytesToLong(byMsg, offset);
        offset += 8;

        // Decodes CreateDate
        if (offset + MobileConstants.MAX_TIME_LEN > length) {
            throw new ToolException(
                "MobileFileInfo decode error: no create date in the message buffer");
        }
        sCreateDate = new String(byMsg, offset,
                MobileConstants.MAX_TIME_LEN).trim();
        offset += MobileConstants.MAX_TIME_LEN;

        // Decodes LastModDate
        if (offset + MobileConstants.MAX_TIME_LEN > length) {
            throw new ToolException(
                "MobileFileInfo decode error: no last modified date in the message buffer");
        }
        sLastModDate = new String(byMsg, offset,
                MobileConstants.MAX_TIME_LEN).trim();
        offset += MobileConstants.MAX_TIME_LEN;

        return offset - start;
    }

    /**
     * 对消息进行编码
     * 
     * @param baMsg 用来保存编码后的消息数组
     * @return 编码成功后，返回添加到消息中总长度
     * @throws 当发生错误时，抛出ToolException异常
     */
    public int encode(ByteArray baMsg) throws ToolException {

        int nLen = baMsg.length();

        // encodes file name
        byte[] byName = sFileName.getBytes();
        baMsg.append(byName.length);
        baMsg.append(byName);

        // encodes file type
        baMsg.append(nType);
        
        // Encodes file size
        baMsg.append(nFileSize);
        
        // Encodes create date
        byte[] byTime = new byte[MobileConstants.MAX_TIME_LEN];
        GenHelper.copyString(byTime, sCreateDate);
        baMsg.append(byTime);
        
        // Encodes modified date
        GenHelper.copyString(byTime, sLastModDate);
        baMsg.append(byTime);

        return baMsg.length() - nLen;
    }

    /**
     * 将本消息格式化成可读形式
     * 
     * @param buf 用来保存格式化后的消息信息
     * @param sPrefix 格式化信息的前缀
     * @return 格式化成功后，返回添加到buf中的总长度
     * @throws 当发生错误时，抛出ToolException异常
     */
    public int format(StringBuffer buf, String sPrefix) throws ToolException {

        int nLen = buf.length();

        // Format File name
        buf.append(sPrefix).
            append("FileName = ").
            append(sFileName).
            append(Constants.LINE_SEPARATOR);

        // Format file type
        buf.append(sPrefix).
            append("Type = ").
            append(nType).
            append(Constants.LINE_SEPARATOR);

        // Format file size
        buf.append(sPrefix).
            append("FileSize = ").
            append(nFileSize).
            append(Constants.LINE_SEPARATOR);

        // Format file create date
        buf.append(sPrefix).
            append("CreateDate = ").
            append(sCreateDate).
            append(Constants.LINE_SEPARATOR);

        // Format file last modified date
        buf.append(sPrefix).
            append("LastModDate = ").
            append(sLastModDate).
            append(Constants.LINE_SEPARATOR);

        return buf.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.MsgField#verifyField(com.aspire.msg.MsgField)
     */
    public boolean verifyField(MsgField field) throws ToolException {

        if (this == field) return true;

        if (field == null) {
            throw new ToolException(
                "Message verify error: the field 'MobileFileInfo' is null");
        }

        if (!(field instanceof MobileFileInfo)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MobileFileInfo' object");
        }

        MobileFileInfo obj = (MobileFileInfo)field;

        doVerifyField("FileName", sFileName, obj, obj.sFileName);
        doVerifyField("Type", nType, obj, obj.nType);
        doVerifyField("FileSize", nFileSize, obj, obj.nFileSize);
        doVerifyField("CreateDate", sCreateDate, obj, obj.sCreateDate);
        doVerifyField("LastModDate", sLastModDate, obj, obj.sLastModDate);

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.MsgField#verifyPresentField(com.aspire.msg.MsgField)
     */
    public boolean verifyPresentField(MsgField field) throws ToolException {

        if (this == field) return true;

        if (field == null) {
            throw new ToolException(
                "Message verify error: the field 'MobileFileInfo' is null");
        }

        if (!(field instanceof MobileFileInfo)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MobileFileInfo' object");
        }

        MobileFileInfo obj = (MobileFileInfo)field;

        doVerifyPresentField("FileName", sFileName, obj, obj.sFileName);
        doVerifyPresentField("Type", nType, obj, obj.nType);
        doVerifyPresentField("FileSize", nFileSize, obj, obj.nFileSize);
        doVerifyPresentField("CreateDate", sCreateDate, obj, obj.sCreateDate);
        doVerifyPresentField("LastModDate", sLastModDate, obj, obj.sLastModDate);

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.MsgField#verifySpecifiedField(com.aspire.msg.MsgField)
     */
    public boolean verifySpecifiedField(MsgField field) throws ToolException {

        if (this == field) return true;

        if (field == null) {
            throw new ToolException(
                "Message verify error: the field 'MobileFileInfo' is null");
        }

        if (!(field instanceof MobileFileInfo)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MobileFileInfo' object");
        }

        MobileFileInfo obj = (MobileFileInfo)field;

        doVerifySpecifiedField("FileName", sFileName, obj, obj.sFileName);
        doVerifySpecifiedField("Type", nType, obj, obj.nType);
        doVerifySpecifiedField("FileSize", nFileSize, obj, obj.nFileSize);
        doVerifySpecifiedField("CreateDate", sCreateDate, obj, obj.sCreateDate);
        doVerifySpecifiedField("LastModDate", sLastModDate, obj, obj.sLastModDate);

        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || !(obj instanceof MobileFileInfo)) {
            return false;
        }

        return ((MobileFileInfo)obj).nType == nType &&
            ((MobileFileInfo)obj).nFileSize == nFileSize &&
            ((MobileFileInfo)obj).sFileName.equals(sFileName) &&
            ((MobileFileInfo)obj).sCreateDate.equals(sCreateDate) &&
            ((MobileFileInfo)obj).sLastModDate.equals(sLastModDate);
    }
}
