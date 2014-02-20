/**
 * @(#) MonitorConfig.java Created on 2009-2-19
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
 * The class <code>MonitorConfig</code>
 *
 * @author xushengyong
 * @version 1.0
 */
public class MonitorConfig extends MobileElementBase {
    
    /**
     * The tag
     * 0x01:CPU&Memory/0x02:Bandwidth/0x03:Battery
     * 0x04:Event/0x05:Frequency/0x06:Period
     */
    protected byte nTag = 0;
    
    /**
     * The constructor
     */
    public MonitorConfig() {
    }
    
    /**
     * Sets the tag
     * @param nTag The tag
     */
    public void setTag(byte nTag) {
        this.nTag = nTag;
        setVerify("Tag");
    }

    /**
     * Sets the tag
     * @param sTag The tag
     * @throws ToolException 
     */
    public void setTag(String sTag) throws ToolException {
        setTag(GenApi.strToByte(sTag, "Tag"));
    }

    /**
     * Gets the tag
     * @return Returns the tag
     */
    public String getTag() {
        return Byte.toString(nTag);
    }

    /**
     * Gets the tag
     * @return Returns the tag
     */
    public byte getTagValue() {
        return nTag;
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

        int offset = start;
                
        // Decodes the Tag
        if (offset >= length) {
            throw new ToolException(
                    "MonitorConfig decode error: no Tag in the record");
        }
        nTag = byMsg[offset];
        offset++;
        
        return offset - start;
    }

    /**
     * Encodes the MonitorConfig object into a buffer
     * 
     * @param baMsg The message buffer
     * @return Returns the encoded buffer length
     * @throws Throws a ToolException when some errors occur
     */
    public int encode(ByteArray baMsg) throws ToolException {

        int nLen = baMsg.length();

        // Encodes the Tag
        baMsg.append(nTag);
                
        return baMsg.length() - nLen;
    }

    /**
     * Formats the MonitorConfig object to a readable string
     * 
     * @param buf  The buffer to save the format string
     * @param sPrefix The Prefix
     * @return Returns the format string length
     * @throws Throws a ToolException when some errors occur
     */
    public int format(StringBuffer buf, String sPrefix) throws ToolException {

        int nLen = buf.length();
        
        // Formats the Tag
        buf.append(sPrefix).
            append("Tag = ").
            append(nTag).
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
                "Message verify error: the field 'MonitorConfig' is null");
        }

        if (!(field instanceof MonitorConfig)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MonitorConfig' object");
        }

        MonitorConfig obj = (MonitorConfig)field;
        
        doVerifyField("Tag", nTag, obj, obj.nTag);

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
                "Message verify error: the field 'MonitorConfig' is null");
        }

        if (!(field instanceof MonitorConfig)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MonitorConfig' object");
        }
        
        MonitorConfig obj = (MonitorConfig)field;

        doVerifyPresentField("Tag", nTag, obj, obj.nTag);
        
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
                "Message verify error: the field 'MonitorConfig' is null");
        }

        if (!(field instanceof MonitorConfig)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MonitorConfig' object");
        }

        MonitorConfig obj = (MonitorConfig)field;

        doVerifySpecifiedField("Tag", nTag, obj, obj.nTag);
        
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || !(obj instanceof MonitorConfig)) {
            return false;
        }
        
        MonitorConfig oMsgField = (MonitorConfig)obj;

        return oMsgField.nTag == nTag;
    }
    
}
