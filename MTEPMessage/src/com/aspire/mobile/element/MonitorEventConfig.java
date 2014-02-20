/**
 * @(#) MonitorEventConfig.java Created on 2009-2-19
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
 * The class <code>MonitorEventConfig</code>
 *
 * @author xushengyong
 * @version 1.0
 */
public class MonitorEventConfig extends MonitorConfig {
    
    /**
     * The MonitorPoint, 32 bits represents 32 monitor points
     */
    protected int nMonitorPoint = 0;
     
    /**
     * The constructor
     */
    public MonitorEventConfig() {
    }

    /**
     * Sets the MonitorPoint
     * @param nMonitorPoint The MonitorPoint
     * @throws ToolException 
     */    
    public void setMonitorPoint(int nMonitorPoint) throws ToolException {
        this.nMonitorPoint = nMonitorPoint;
        setVerify("MonitorPoint", true);
    }
    
    /**
     * Sets the MonitorPoint
     * @param sMonitorPoint The MonitorPoint
     * @throws ToolException 
     */
    public void setMonitorPoint(String sMonitorPoint) throws ToolException {
        setMonitorPoint(GenApi.strToInt(sMonitorPoint, "MonitorPoint"));
    }
    
    /**
     * Gets the MonitorPoint
     * @return Returns the MonitorPoint
     */
    public String getMonitorPoint() {
        return Integer.toString(nMonitorPoint);
    }

    /**
     * Gets the MonitorPoint
     * @return Returns the MonitorPoint
     */
    public int getMonitorPointValue() {
        return nMonitorPoint;
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
        
        // Decodes the MonitorPoint
        if ((offset + 4) > length) {
            throw new ToolException(
                    "MonitorEventConfig decode error: no MonitorPoint in the record");
        }
        nMonitorPoint = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;
        
        return offset - start;
    }

    /**
     * Encodes the MonitorEventConfig object into a buffer
     * 
     * @param baMsg The message buffer
     * @return Returns the encoded buffer length
     * @throws Throws a ToolException when some errors occur
     */
    public int encode(ByteArray baMsg) throws ToolException {

        int nLen = baMsg.length();
        
        super.encode(baMsg);

        // Encodes the MonitorPoint
        baMsg.append(nMonitorPoint);     
                
        return baMsg.length() - nLen;
    }

    /**
     * Formats the MonitorEventConfig object to a readable string
     * 
     * @param buf  The buffer to save the format string
     * @param sPrefix The Prefix
     * @return Returns the format string length
     * @throws Throws a ToolException when some errors occur
     */
    public int format(StringBuffer buf, String sPrefix) throws ToolException {

        int nLen = buf.length();

        super.format(buf, sPrefix);
        
        // Formats the MonitorPoint
        buf.append(sPrefix).
            append("MonitorPoint = ").
            append(nMonitorPoint).
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
                "Message verify error: the field 'MonitorEventConfig' is null");
        }

        if (!(field instanceof MonitorEventConfig)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MonitorEventConfig' object");
        }

        MonitorEventConfig obj = (MonitorEventConfig)field;
        
        doVerifyField("MonitorPoint", nMonitorPoint, obj, obj.nMonitorPoint);
        
        
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
                "Message verify error: the field 'MonitorEventConfig' is null");
        }

        if (!(field instanceof MonitorConfig)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MonitorEventConfig' object");
        }
        
        MonitorEventConfig obj = (MonitorEventConfig)field;

        doVerifyPresentField("MonitorPoint", nMonitorPoint, obj, obj.nMonitorPoint);
        
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
                "Message verify error: the field 'MonitorEventConfig' is null");
        }

        if (!(field instanceof MonitorEventConfig)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MonitorEventConfig' object");
        }

        MonitorEventConfig obj = (MonitorEventConfig)field;
        
        doVerifySpecifiedField("MonitorPoint", nMonitorPoint, obj, obj.nMonitorPoint);
        
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj)) return false;

        if (!(obj instanceof MonitorEventConfig)) {
            return false;
        }
        
        MonitorEventConfig oMsgField = (MonitorEventConfig)obj;

        return oMsgField.nMonitorPoint == nMonitorPoint;
    }
    
}
