/**
 * @(#) MobileMsgAttachment.java Created on 2009-2-26
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.element;

import com.aspire.Constants;
import com.aspire.msg.MsgField;
import com.aspire.util.ByteArray;
import com.aspire.util.GenApi;
import com.aspire.util.ToolException;

/**
 * The class <code>MobileMsgAttachment</code>
 *
 * @author xushengyong
 * @version 1.0
 */
public class MobileMsgAttachment extends MobileElementBase {
    
    /**
     * The attachment file name
     */
    protected String sFileName = "";
    
    /**
     * The mime type
     */
    protected String sMimeType = "";
    
    /**
     * The attachment size
     */
    protected long nSize = 0;
    
    /**
     * The constructor
     */
    public MobileMsgAttachment() {      
    }
       
    /**
     * Sets the file name
     * @param sFileName The file name
     */
    public void setFileName(String sFileName) {
        this.sFileName = sFileName;
        setVerify("FileName", true);
    }
    
    /**
     * Gets the file name
     * @return Return the file name
     */
    public String getFileName() {
        return sFileName;
    } 
   
    /**
     * Sets the mime type
     * @param sMimeType The mime type
     */
    public void setMimeType(String sMimeType) {
        this.sMimeType = sMimeType;
        setVerify("MimeType", true);
    }
    
    /**
     * Gets the mime type
     * @return Return the mime type
     */
    public String getMimeType() {
        return sMimeType;
    } 
    
    /**
     * Sets the size
     * @param nSize The size
     */
    public void setSize(long nSize) {
        this.nSize = nSize;
        setVerify("Size");
    }

    /**
     * Sets the size
     * @param sSize The size
     */
    public void setSize(String sSize) throws ToolException {
        setSize(GenApi.strToLong(sSize, "Size"));
    }

    /**
     * Gets the size
     * @return Returns size
     */
    public String getSize() {
        return Long.toString(nSize);
    }

    /**
     * Gets size
     * @return Returns size
     */
    public long getSizeValue() {
        return nSize;
    }    
    
    /**
     * Decodes all fields, decode a message object from the buffer
     * 
     * @param byMsg The message buffer
     * @param start The start index
     * @param length The message length
     * @return Returns the decoded buffer length
     * @throws Throws a ToolException when some errors occur
     */
    public int decode(byte[] byMsg, int start, int length) throws ToolException {
 
        int nLen = 0;
        int offset = start;
        
        // Decodes the FileName length field
        if ((offset + 4) > length) {
            throw new ToolException(
                    "MobileMsgAttachment decode error: no FileName in the record");
        }
        nLen = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;

        // Decodes the FileName value field
        if ((offset + nLen) > length) {
            throw new ToolException(
                    "MobileMsgAttachment decode error: no FileName in the record");
        }
        sFileName = new String(byMsg, offset, nLen);
        offset += nLen;

        // Decodes the MimeType length field
        if ((offset + 4) > length) {
            throw new ToolException(
                    "MobileMsgAttachment decode error: no MimeType in the record");
        }
        nLen = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;

        // Decodes the MimeType value field
        if ((offset + nLen) > length) {
            throw new ToolException(
                    "MobileMsgAttachment decode error: no MimeType in the record");
        }
        sMimeType = new String(byMsg, offset, nLen);
        offset += nLen;

        // Decodes the Size
        nSize = ByteArray.bytesToLong(byMsg, offset);
        offset += 8;
        
        return offset - start;
    }

    /**
     * Encodes the MobileMsgAttachment object into a buffer
     * 
     * @param baMsg The message buffer
     * @return Returns the encoded buffer length
     * @throws Throws a ToolException when some errors occur
     */
    public int encode(ByteArray baMsg) throws ToolException {

        int nLen = baMsg.length();
        
        // Encodes the FileName
        baMsg.append(sFileName.getBytes().length);
        baMsg.append(sFileName);

        // Encodes the MimeType
        baMsg.append(sMimeType.getBytes().length);
        baMsg.append(sMimeType);

        // Encodes the Size
        baMsg.append(nSize);

        return baMsg.length() - nLen;
    }

    /**
     * Formats the MobileMsgAttachment object to a readable string
     * 
     * @param buf  The buffer to save the format string
     * @param sPrefix The Prefix
     * @return Returns the format string length
     * @throws Throws a ToolException when some errors occur
     */
    public int format(StringBuffer buf, String sPrefix) throws ToolException {

        int nLen = buf.length();
        
        // Formats the FileName
        buf.append(sPrefix).
        append("FileName = ").
        append(sFileName).
        append(Constants.LINE_SEPARATOR);

        // Formats the MimeType
        buf.append(sPrefix).
        append("MimeType = ").
        append(sMimeType).append(Constants.LINE_SEPARATOR);

        // Formats the Size
        buf.append(sPrefix).
        append("Size = ").
        append(nSize).
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
                "Message verify error: the field 'MobileMsgAttachment' is null");
        }

        if (!(field instanceof MobileMsgAttachment)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MobileMsgAttachment' object");
        }

        MobileMsgAttachment obj = (MobileMsgAttachment) field;
        
        doVerifyField("FileName", sFileName, obj, obj.sFileName);
        doVerifyField("MimeType", sMimeType, obj, obj.sMimeType);
        doVerifyField("Size", nSize, obj, obj.nSize);
        
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
                "Message verify error: the field 'MobileMsgAttachment' is null");
        }

        if (!(field instanceof MobileMsgAttachment)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MobileMsgAttachment' object");
        }
        
        MobileMsgAttachment obj = (MobileMsgAttachment)field;
         
        doVerifyPresentField("FileName", sFileName, obj, obj.sFileName);
        doVerifyPresentField("MimeType", sMimeType, obj, obj.sMimeType);
        doVerifyPresentField("Size", nSize, obj, obj.nSize);
        
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
                "Message verify error: the field 'MobileMsgAttachment' is null");
        }

        if (!(field instanceof MobileMsgAttachment)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MobileMsgAttachment' object");
        }

        MobileMsgAttachment obj = (MobileMsgAttachment) field;

        doVerifySpecifiedField("FileName", sFileName, obj, obj.sFileName);
        doVerifySpecifiedField("MimeType", sMimeType, obj, obj.sMimeType);
        doVerifySpecifiedField("Size", nSize, obj, obj.nSize);

        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || !(obj instanceof MobileMsgAttachment)) {
            return false;
        }
        
        MobileMsgAttachment oMsgField = (MobileMsgAttachment) obj;

        return oMsgField.sFileName.equals(sFileName)
                && oMsgField.sMimeType.equals(sMimeType)
                && oMsgField.nSize == nSize;
    }
    
}
