/**
 * @(#) MonitorCpuMemConfig.java Created on 2009-2-19
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
 * The class <code>MonitorCpuMemConfig</code>
 *
 * @author xushengyong
 * @version 1.0
 */
public class MonitorCpuMemConfig extends MonitorConfig {
    
    public final static byte MONITOR_POINT_NOTHING = 0x00;   // Nothing
    public final static byte MONITOR_POINT_CPU = 0x01;       // CPU
    public final static byte MONITOR_POINT_MEMORY = 0x02;    // Memory
    public final static byte MONITOR_POINT_ALL = 0x03;       // All
    
    /**
     * The Length
     */
    protected int nLength = 0;
    
    /**
     * The MonitorPoint
     * 0x00:Nothing/0x01:CPU/0x02:Memory/0x03:CPU&Memory
     */
    protected byte nMonitorPoint = MonitorCpuMemConfig.MONITOR_POINT_NOTHING;
    
    /**
     * The ProcessList
     */    
    protected String sProcessList = "";
    
    /**
     * The constructor
     */
    public MonitorCpuMemConfig() {
    }
    
    /**
     * Sets the Length
     * @param nLength The Length
     * @throws ToolException 
     */    
    public void setLength(int nLength) throws ToolException {
        this.nLength = nLength;
        setVerify("Length", true);
    }
    
    /**
     * Sets the Length
     * @param sLength The Length
     * @throws ToolException 
     */
    public void setLength(String sLength) throws ToolException {
        setLength(GenApi.strToInt(sLength, "Length"));
    }
    
    /**
     * Gets the Length
     * @return Returns the Length
     */
    public String getLength() {
        return Integer.toString(nLength);
    }

    /**
     * Gets the Length
     * @return Returns the Length
     */
    public int getLengthValue() {
        return nLength;
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
     * Sets the process list
     * @param sProcessList The process list
     */
    public void setProcessList(String sProcessList) {
        this.sProcessList = sProcessList;
        setVerify("ProcessList", true);
    }
    
    /**
     * Gets the process list
     * @return Return the process list
     */
    public String getProcessList() {
        return sProcessList;
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
                    
        // Decodes the Length
        if ((offset + 4) > length) {
            throw new ToolException(
                    "MonitorCpuMemConfig decode error: no Length in the record");
        }
        nLength = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;
        
        // Decodes the MonitorPoint
        if (offset >= length) {
            throw new ToolException(
                    "MonitorCpuMemConfig decode error: no MonitorPoint in the record");
        }
        nMonitorPoint = byMsg[offset];
        offset++;
        
        // Decode the ProcessList
        int nLen = nLength - (offset - start);
        sProcessList = new String(byMsg, offset, nLen);
        offset += nLen;
        
        return offset - start;
    }

    /**
     * Encodes the MonitorCpuMemConfig object into a buffer
     * 
     * @param baMsg The message buffer
     * @return Returns the encoded buffer length
     * @throws Throws a ToolException when some errors occur
     */
    public int encode(ByteArray baMsg) throws ToolException {

        int nLen = baMsg.length();
        
        super.encode(baMsg);
        
        // Encodes the Length
        baMsg.append(nLength);

        // Encodes the MonitorPoint
        baMsg.append(nMonitorPoint);
        
        // Encodes the ProcessList
        baMsg.append(sProcessList);       
                
        return baMsg.length() - nLen;
    }

    /**
     * Formats the MonitorCpuMemConfig object to a readable string
     * 
     * @param buf  The buffer to save the format string
     * @param sPrefix The Prefix
     * @return Returns the format string length
     * @throws Throws a ToolException when some errors occur
     */
    public int format(StringBuffer buf, String sPrefix) throws ToolException {

        int nLen = buf.length();

        super.format(buf, sPrefix);
        
        // Formats the Length
        buf.append(sPrefix).
            append("Length = ").
            append(nLength).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the MonitorPoint
        buf.append(sPrefix).
            append("MonitorPoint = ").
            append(nMonitorPoint).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the ProcessList
        buf.append(sPrefix).
            append("ProcessList = ").
            append(sProcessList).
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
                "Message verify error: the field 'MonitorCpuMemConfig' is null");
        }

        if (!(field instanceof MonitorCpuMemConfig)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MonitorCpuMemConfig' object");
        }

        MonitorCpuMemConfig obj = (MonitorCpuMemConfig)field;
        
        doVerifyField("Length", nLength, obj, obj.nLength);
        doVerifyField("MonitorPoint", nMonitorPoint, obj, obj.nMonitorPoint);
        doVerifyField("ProcessList", sProcessList, obj, obj.sProcessList);
        
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
                "Message verify error: the field 'MonitorCpuMemConfig' is null");
        }

        if (!(field instanceof MonitorConfig)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MonitorCpuMemConfig' object");
        }
        
        MonitorCpuMemConfig obj = (MonitorCpuMemConfig)field;
        
        doVerifyPresentField("Length", nLength, obj, obj.nLength);
        doVerifyPresentField("MonitorPoint", nMonitorPoint, obj, obj.nMonitorPoint);
        doVerifyPresentField("ProcessList", sProcessList, obj, obj.sProcessList);
        
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
                "Message verify error: the field 'MonitorCpuMemConfig' is null");
        }

        if (!(field instanceof MonitorCpuMemConfig)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MonitorCpuMemConfig' object");
        }

        MonitorCpuMemConfig obj = (MonitorCpuMemConfig)field;
        
        doVerifySpecifiedField("Length", nLength, obj, obj.nLength);
        doVerifySpecifiedField("MonitorPoint", nMonitorPoint, obj, obj.nMonitorPoint);
        doVerifySpecifiedField("ProcessList", sProcessList, obj, obj.sProcessList);
        
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj)) return false;

        if (!(obj instanceof MonitorCpuMemConfig)) {
            return false;
        }
        
        MonitorCpuMemConfig oMsgField = (MonitorCpuMemConfig)obj;

        return oMsgField.nLength == nLength &&
            oMsgField.nMonitorPoint == nMonitorPoint &&
            oMsgField.sProcessList.equals(sProcessList);
    }
}
