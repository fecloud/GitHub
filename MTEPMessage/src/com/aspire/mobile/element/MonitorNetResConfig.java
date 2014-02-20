/**
 * @(#) MonitorNetResConfig.java Created on 2009-2-19
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
 * The class <code>MonitorNetResConfig</code>
 *
 * @author xushengyong
 * @version 1.0
 */
public class MonitorNetResConfig extends MonitorConfig {
    
    public final static int MONITOR_POINT_OFF = 0x00;   // Off
    public final static int MONITOR_POINT_ON = 0x01;    // On
     
    /**
     * The MonitorPoint, 0x00:Off/0x01:On
     */
    protected byte nMonitorPoint = MonitorNetResConfig.MONITOR_POINT_OFF;
     
    /**
     * The constructor
     */
    public MonitorNetResConfig() {
    }
        
    /**
     * Sets the monitor point
     * @param nMonitorPoint The monitor point
     */
    public void setMonitorPoint(byte nMonitorPoint) {
        this.nMonitorPoint = nMonitorPoint;
        setVerify("MonitorPoint");
    }

    /**
     * Sets the monitor point
     * @param sMonitorPoint The monitor point
     * @throws ToolException 
     */
    public void setMonitorPoint(String sMonitorPoint) throws ToolException {
        setMonitorPoint(GenApi.strToByte(sMonitorPoint, "MonitorPoint"));
    }

    /**
     * Gets the monitor point
     * @return Returns the monitor point
     */
    public String getMonitorPoint() {
        return Byte.toString(nMonitorPoint);
    }

    /**
     * Gets the monitor point
     * @return Returns the monitor point
     */
    public byte getMonitorPointValue() {
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
        if (offset >= length) {
            throw new ToolException(
                    "MonitorNetResConfig decode error: no MonitorPoint in the record");
        }
        nMonitorPoint = byMsg[offset];
        offset++;
        
        return offset - start;
    }

    /**
     * Encodes the MonitorNetResConfig object into a buffer
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
     * Formats the MonitorNetResConfig object to a readable string
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
                "Message verify error: the field 'MonitorNetResConfig' is null");
        }

        if (!(field instanceof MonitorNetResConfig)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MonitorNetResConfig' object");
        }

        MonitorNetResConfig obj = (MonitorNetResConfig)field;
        
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
                "Message verify error: the field 'MonitorNetResConfig' is null");
        }

        if (!(field instanceof MonitorConfig)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MonitorNetResConfig' object");
        }
        
        MonitorNetResConfig obj = (MonitorNetResConfig)field;

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
                "Message verify error: the field 'MonitorNetResConfig' is null");
        }

        if (!(field instanceof MonitorNetResConfig)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MonitorNetResConfig' object");
        }

        MonitorNetResConfig obj = (MonitorNetResConfig)field;
        
        doVerifySpecifiedField("MonitorPoint", nMonitorPoint, obj, obj.nMonitorPoint);
        
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj)) return false;

        if (!(obj instanceof MonitorNetResConfig)) {
            return false;
        }
        
        MonitorNetResConfig oMsgField = (MonitorNetResConfig)obj;

        return oMsgField.nMonitorPoint == nMonitorPoint;
    }
    
}
