/**
 * @(#) MobilePerformanceDataInfo.java Created on 2009-2-19
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
 * The class <code>MobilePerformanceDataInfo</code>
 *
 * @author xushengyong
 * @version 1.0
 */
public class MobilePerformanceDataInfo extends MobileElementBase {
    
    /**
     * The ProcessName
     */
    protected String sProcessName = "";
    
    /**
     * The ProcessCpuData
     */    
    protected int nProcessCpuData = 0;
    
    /**
     * The ProcessMemData
     */    
    protected int nProcessMemData = 0;
    
    /**
     * The constructor
     */
    public MobilePerformanceDataInfo() {      
    }
    
    /**
     * Sets the process name
     * @param sProcessName The process name
     */
    public void setProcessName(String sProcessName) {
        this.sProcessName = sProcessName;
        setVerify("ProcessName", true);
    }
    
    /**
     * Gets the process name
     * @return Return the process name
     */
    public String getProcessName() {
        return sProcessName;
    }  
    
    /**
     * Sets the ProcessCpuData
     * @param nProcessCpuData The ProcessCpuData
     * @throws ToolException 
     */    
    public void setProcessCpuData(int nProcessCpuData) throws ToolException {
        this.nProcessCpuData = nProcessCpuData;
        setVerify("ProcessCpuData", true);
    }
    
    /**
     * Sets the ProcessCpuData
     * @param sProcessCpuData The ProcessCpuData
     * @throws ToolException 
     */
    public void setProcessCpuData(String sProcessCpuData) throws ToolException {
        setProcessCpuData(GenApi.strToInt(sProcessCpuData, "ProcessCpuData"));
    }
    
    /**
     * Gets the ProcessCpuData
     * @return Returns the ProcessCpuData
     */
    public String getProcessCpuData() {
        return Integer.toString(nProcessCpuData);
    }

    /**
     * Gets the ProcessCpuData
     * @return Returns the ProcessCpuData
     */
    public int getProcessCpuDataValue() {
        return nProcessCpuData;
    }
    
    /**
     * Sets the ProcessMemData
     * @param nProcessMemData The ProcessMemData
     * @throws ToolException 
     */    
    public void setProcessMemData(int nProcessMemData) throws ToolException {
        this.nProcessMemData = nProcessMemData;
        setVerify("ProcessMemData", true);
    }
    
    /**
     * Sets the ProcessMemData
     * @param sProcessMemData The ProcessMemData
     * @throws ToolException 
     */
    public void setProcessMemData(String sProcessMemData) throws ToolException {
        setProcessMemData(GenApi.strToInt(sProcessMemData, "ProcessMemData"));
    }
    
    /**
     * Gets the ProcessMemData
     * @return Returns the ProcessMemData
     */
    public String getProcessMemData() {
        return Integer.toString(nProcessMemData);
    }

    /**
     * Gets the ProcessMemData
     * @return Returns the ProcessMemData
     */
    public int getProcessMemDataValue() {
        return nProcessMemData;
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
        
        // Decodes the process name, the process name ends with '\0'
        while (byMsg[offset + nLen] != 0x00) {
            if ((offset + nLen) > length) {
                throw new ToolException(
                "MobilePerformanceDataInfo decode error: no ProcessName in the record");                  
            }            
            nLen++;
        }
        sProcessName = new String(byMsg, offset, nLen);
        offset = offset + nLen + 1;
        
        // Decodes the process CPU data
        if ((offset + 4) > length) {
            throw new ToolException(
            "MobilePerformanceDataInfo decode error: no ProcessCpuData in the record");
        }  
        nProcessCpuData = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;
        
        // Decodes the process memory data
        if ((offset + 4) > length) {
            throw new ToolException(
            "MobilePerformanceDataInfo decode error: no ProcessMemData in the record");
        }  
        nProcessMemData = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;
        
        return offset - start;
    }

    /**
     * Encodes the MobileContactInfo object into a buffer
     * 
     * @param baMsg The message buffer
     * @return Returns the encoded buffer length
     * @throws Throws a ToolException when some errors occur
     */
    public int encode(ByteArray baMsg) throws ToolException {

        int nLen = baMsg.length();
        
        // Encodes the process name
        baMsg.append(sProcessName);
        byte endByte = 0x00;
        baMsg.append(endByte);
        
        // Encodes the process CPU data
        baMsg.append(nProcessCpuData);
        
        // Encodes the process memory data
        baMsg.append(nProcessMemData);
        
        return baMsg.length() - nLen;
    }

    /**
     * Formats the MobileContactInfo object to a readable string
     * 
     * @param buf  The buffer to save the format string
     * @param sPrefix The Prefix
     * @return Returns the format string length
     * @throws Throws a ToolException when some errors occur
     */
    public int format(StringBuffer buf, String sPrefix) throws ToolException {

        int nLen = buf.length();
        
        // Formats the process name
        buf.append(sPrefix).
            append("ProcessName = ").
            append(sProcessName).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the process CPU data
        buf.append(sPrefix).
            append("ProcessCpuData = ").
            append(nProcessCpuData).
            append(Constants.LINE_SEPARATOR);

        // Formats the process memory data
        buf.append(sPrefix).
            append("ProcessMemData = ").
            append(nProcessMemData).
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
                "Message verify error: the field 'MobilePerformanceDataInfo' is null");
        }

        if (!(field instanceof MobilePerformanceDataInfo)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MobilePerformanceDataInfo' object");
        }

        MobilePerformanceDataInfo obj = (MobilePerformanceDataInfo)field;
        
        doVerifyField("ProcessName", sProcessName, obj, obj.sProcessName);  
        doVerifyField("ProcessCpuData", nProcessCpuData, obj, obj.nProcessCpuData);
        doVerifyField("ProcessMemData", nProcessMemData, obj, obj.nProcessMemData);
        
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
                "Message verify error: the field 'MobilePerformanceDataInfo' is null");
        }

        if (!(field instanceof MobilePerformanceDataInfo)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MobilePerformanceDataInfo' object");
        }
        
        MobilePerformanceDataInfo obj = (MobilePerformanceDataInfo)field;
               
        doVerifyPresentField("ProcessName", sProcessName, obj, obj.sProcessName);  
        doVerifyPresentField("ProcessCpuData", nProcessCpuData, obj, obj.nProcessCpuData);
        doVerifyPresentField("ProcessMemData", nProcessMemData, obj, obj.nProcessMemData);   
        
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
                "Message verify error: the field 'MobilePerformanceDataInfo' is null");
        }

        if (!(field instanceof MobilePerformanceDataInfo)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MobilePerformanceDataInfo' object");
        }

        MobilePerformanceDataInfo obj = (MobilePerformanceDataInfo)field;
        
        doVerifySpecifiedField("ProcessName", sProcessName, obj, obj.sProcessName);  
        doVerifySpecifiedField("ProcessCpuData", nProcessCpuData, obj, obj.nProcessCpuData);
        doVerifySpecifiedField("ProcessMemData", nProcessMemData, obj, obj.nProcessMemData);   
        
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || !(obj instanceof MobilePerformanceDataInfo)) {
            return false;
        }
        
        MobilePerformanceDataInfo oMsgField = (MobilePerformanceDataInfo)obj;

        return oMsgField.sProcessName.equals(sProcessName) && 
            oMsgField.nProcessCpuData == nProcessCpuData && 
            oMsgField.nProcessMemData == nProcessMemData;
    }

}
