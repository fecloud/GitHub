/**
 * @(#) MonitorFrequencyConfig.java Created on 2009-2-19
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
 * The class <code>MonitorFrequencyConfig</code>
 *
 * @author xushengyong
 * @version 1.0
 */
public class MonitorFrequencyConfig extends MonitorConfig {
    
    /**
     * The Frequency
     */
    protected int nFrequency = 0;
     
    /**
     * The constructor
     */
    public MonitorFrequencyConfig() {
    }

    /**
     * Sets the Frequency
     * @param nFrequency The Frequency
     * @throws ToolException 
     */    
    public void setFrequency(int nFrequency) throws ToolException {
        this.nFrequency = nFrequency;
        setVerify("Frequency", true);
    }
    
    /**
     * Sets the Frequency
     * @param sFrequency The Frequency
     * @throws ToolException 
     */
    public void setFrequency(String sFrequency) throws ToolException {
        setFrequency(GenApi.strToInt(sFrequency, "Frequency"));
    }
    
    /**
     * Gets the Frequency
     * @return Returns the Frequency
     */
    public String getFrequency() {
        return Integer.toString(nFrequency);
    }

    /**
     * Gets the Frequency
     * @return Returns the Frequency
     */
    public int getFrequencyValue() {
        return nFrequency;
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

        int nRead = super.decode(byMsg, start, length);
        int offset = start + nRead;
        
        // Decodes the Frequency
        if ((offset + 4) > length) {
            throw new ToolException(
                    "MonitorFrequencyConfig decode error: no Frequency in the record");
        }
        nFrequency = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;
        
        return offset - start;
    }

    /**
     * Encodes the MonitorFrequencyConfig object into a buffer
     * 
     * @param baMsg The message buffer
     * @return Returns the encoded buffer length
     * @throws Throws a ToolException when some errors occur
     */
    public int encode(ByteArray baMsg) throws ToolException {

        int nLen = baMsg.length();
        
        super.encode(baMsg);

        // Encodes the Frequency
        baMsg.append(nFrequency);     
                
        return baMsg.length() - nLen;
    }

    /**
     * Formats the MonitorFrequencyConfig object to a readable string
     * 
     * @param buf  The buffer to save the format string
     * @param sPrefix The Prefix
     * @return Returns the format string length
     * @throws Throws a ToolException when some errors occur
     */
    public int format(StringBuffer buf, String sPrefix) throws ToolException {

        int nLen = buf.length();

        super.format(buf, sPrefix);
        
        // Formats the Frequency
        buf.append(sPrefix).
            append("Frequency = ").
            append(nFrequency).
            append(Constants.LINE_SEPARATOR);
        
        return buf.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.MsgField#verifyField(com.aspire.msg.MsgField)
     */
    public boolean verifyField(MsgField field) throws ToolException {

        super.verifyField(field);

        if (field == null) {
            throw new ToolException(
                "Message verify error: the field 'MonitorFrequencyConfig' is null");
        }

        if (!(field instanceof MonitorFrequencyConfig)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MonitorFrequencyConfig' object");
        }

        MonitorFrequencyConfig obj = (MonitorFrequencyConfig)field;
        
        doVerifyField("Frequency", nFrequency, obj, obj.nFrequency);
        
        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.MsgField#verifyPresentField(com.aspire.msg.MsgField)
     */
    public boolean verifyPresentField(MsgField field) throws ToolException {

        super.verifyPresentField(field);

        if (field == null) {
            throw new ToolException(
                "Message verify error: the field 'MonitorFrequencyConfig' is null");
        }

        if (!(field instanceof MonitorConfig)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MonitorFrequencyConfig' object");
        }
        
        MonitorFrequencyConfig obj = (MonitorFrequencyConfig)field;

        doVerifyPresentField("Frequency", nFrequency, obj, obj.nFrequency);
        
        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.MsgField#verifySpecifiedField(com.aspire.msg.MsgField)
     */
    public boolean verifySpecifiedField(MsgField field) throws ToolException {

        super.verifySpecifiedField(field);

        if (field == null) {
            throw new ToolException(
                "Message verify error: the field 'MonitorFrequencyConfig' is null");
        }

        if (!(field instanceof MonitorFrequencyConfig)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MonitorFrequencyConfig' object");
        }

        MonitorFrequencyConfig obj = (MonitorFrequencyConfig)field;
        
        doVerifySpecifiedField("Frequency", nFrequency, obj, obj.nFrequency);
        
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj)) return false;

        if (!(obj instanceof MonitorFrequencyConfig)) {
            return false;
        }
        
        MonitorFrequencyConfig oMsgField = (MonitorFrequencyConfig)obj;

        return oMsgField.nFrequency == nFrequency;
    }
    
}
