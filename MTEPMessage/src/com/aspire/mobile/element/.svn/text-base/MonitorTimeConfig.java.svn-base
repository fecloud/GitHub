/**
 * @(#) MonitorTimeConfig.java Created on 2009-2-19
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
 * The class <code>MonitorTimeConfig</code>
 *
 * @author xushengyong
 * @version 1.0
 */
public class MonitorTimeConfig extends MonitorConfig {

    /**
     * The MonitorTime
     */
    protected int nMonitorTime = 0;
     
    /**
     * The constructor
     */
    public MonitorTimeConfig() {
    }

    /**
     * Sets the MonitorTime
     * @param nMonitorTime The MonitorTime
     * @throws ToolException 
     */    
    public void setMonitorTime(int nMonitorTime) throws ToolException {
        this.nMonitorTime = nMonitorTime;
        setVerify("MonitorTime", true);
    }
    
    /**
     * Sets the MonitorTime
     * @param sMonitorTime The MonitorTime
     * @throws ToolException 
     */
    public void setMonitorTime(String sMonitorTime) throws ToolException {
        setMonitorTime(GenApi.strToInt(sMonitorTime, "MonitorTime"));
    }
    
    /**
     * Gets the MonitorTime
     * @return Returns the MonitorTime
     */
    public String getMonitorTime() {
        return Integer.toString(nMonitorTime);
    }

    /**
     * Gets the MonitorTime
     * @return Returns the MonitorTime
     */
    public int getMonitorTimeValue() {
        return nMonitorTime;
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
        
        // Decodes the MonitorTime
        if ((offset + 4) > length) {
            throw new ToolException(
                    "MonitorTimeConfig decode error: no MonitorTime in the record");
        }
        nMonitorTime = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;
        
        return offset - start;
    }

    /**
     * Encodes the MonitorTimeConfig object into a buffer
     * 
     * @param baMsg The message buffer
     * @return Returns the encoded buffer length
     * @throws Throws a ToolException when some errors occur
     */
    public int encode(ByteArray baMsg) throws ToolException {

        int nLen = baMsg.length();
        
        super.encode(baMsg);

        // Encodes the MonitorTime
        baMsg.append(nMonitorTime);     
                
        return baMsg.length() - nLen;
    }

    /**
     * Formats the MonitorTimeConfig object to a readable string
     * 
     * @param buf  The buffer to save the format string
     * @param sPrefix The Prefix
     * @return Returns the format string length
     * @throws Throws a ToolException when some errors occur
     */
    public int format(StringBuffer buf, String sPrefix) throws ToolException {

        int nLen = buf.length();

        super.format(buf, sPrefix);
        
        // Formats the MonitorTime
        buf.append(sPrefix).
            append("MonitorTime = ").
            append(nMonitorTime).
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
                "Message verify error: the field 'MonitorTimeConfig' is null");
        }

        if (!(field instanceof MonitorTimeConfig)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MonitorTimeConfig' object");
        }

        MonitorTimeConfig obj = (MonitorTimeConfig)field;
        
        doVerifyField("MonitorTime", nMonitorTime, obj, obj.nMonitorTime);
        
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
                "Message verify error: the field 'MonitorTimeConfig' is null");
        }

        if (!(field instanceof MonitorConfig)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MonitorTimeConfig' object");
        }
        
        MonitorTimeConfig obj = (MonitorTimeConfig)field;

        doVerifyPresentField("MonitorTime", nMonitorTime, obj, obj.nMonitorTime);
        
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
                "Message verify error: the field 'MonitorTimeConfig' is null");
        }

        if (!(field instanceof MonitorTimeConfig)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MonitorTimeConfig' object");
        }

        MonitorTimeConfig obj = (MonitorTimeConfig)field;
        
        doVerifySpecifiedField("MonitorTime", nMonitorTime, obj, obj.nMonitorTime);
        
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj)) return false;

        if (!(obj instanceof MonitorTimeConfig)) {
            return false;
        }
        
        MonitorTimeConfig oMsgField = (MonitorTimeConfig)obj;

        return oMsgField.nMonitorTime == nMonitorTime;
    }
    
}
