/**
 * @(#) MobileCallRecordInfo.java Created on 2009-2-18
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
 * The class <code>MobileCallRecordInfo</code>
 *
 * @author xushengyong
 * @version 1.0
 */
public class MobileCallRecordInfo extends MobileElementBase {

    /**
     * The contact ID
     */
    protected String sID = "";
    
    /**
     * The call type, 0x01:Incoming call/0x02:Outcoming call/0x03:Missed call 
     */
    protected byte nCallType = MobileConstants.CALL_TYPE_INCOMING;
    
    /**
     * The address
     */
    protected String sAddress = "";
    
    /**
     * The name
     */
    protected String sName = "";

    /**
     * The start date
     */
    protected String sStartDate = GenApi.getTime();
    
    /**
     * The duration
     */
    protected int nDuration = 0;
    
    /**
     * The constructor
     */
    public MobileCallRecordInfo() {
    }
    
    /**
     * Sets the ID
     * @param sID The ID
     */
    public void setID(String sID) {
        this.sID = sID;
        setVerify("ID", true);
    }

    /**
     * Gets the ID
     * @return Return the ID
     */
    public String getID() {
        return sID;
    }
    
    /**
     * Sets the call type
     * @param nCallType The call type
     */
    public void setCallType(byte nCallType) {
        this.nCallType = nCallType;
        setVerify("CallType");
    }

    /**
     * Sets the call type
     * @param sType The call type
     * @throws ToolException 
     */
    public void setCallType(String sCallType) throws ToolException {
        setCallType(GenApi.strToByte(sCallType, "CallType"));
    }

    /**
     * Gets the call type
     * @return Returns The call type
     */
    public String getCallType() {
        return Byte.toString(nCallType);
    }

    /**
     * Gets the call type
     * @return Returns the call type
     */
    public byte getCallTypeValue() {
        return nCallType;
    }
    
    /**
     * Sets the address
     * @param sAddress The address
     */
    public void setAddress(String sAddress) {
        this.sAddress = sAddress;
        setVerify("Address", true);
    }
    
    /**
     * Gets the address
     * @return Return the address
     */
    public String getAddress() {
        return sAddress;
    }    
    
    /**
     * Sets the name
     * @param sName The name
     */
    public void setName(String sName) {
        this.sName = sName;
        setVerify("Name", true);
    }
    
    /**
     * Gets the name
     * @return Return the name
     */
    public String getName() {
        return sName;
    }    
    
    /**
     * Sets the start date
     * @param sStartDate The start date
     */
    public void setStartDate(String sStartDate) {
        this.sStartDate = GenApi.formatDate(sStartDate);
        setVerify("StartDate");
    }

    /**
     * Gets the start date
     * @return Returns start date
     */
    public String getStartDate() {
        return sStartDate;
    }
    
    /**
     * Sets the Duration
     * @param nDuration The duration
     * @throws ToolException 
     */    
    public void setDuration(int nDuration) throws ToolException {
        this.nDuration = nDuration;
        setVerify("Duration", true);
    }
    
    /**
     * Sets the Duration
     * @param sDuration The duration
     * @throws ToolException 
     */
    public void setDuration(String sDuration) throws ToolException {
        setDuration(GenApi.strToInt(sDuration, "Duration"));
    }
    
    /**
     * Gets the Duration
     * @return Returns the duration
     */
    public String getDuration() {
        return Integer.toString(nDuration);
    }

    /**
     * Gets the Duration
     * @return Returns the duration
     */
    public int getDurationValue() {
        return nDuration;
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
        
        // Decodes the contact ID
        if ((offset + MobileConstants.MAX_ID_LEN) > length) {
            throw new ToolException(
                "MobileCallRecordInfo decode error: no ID in the record");
        }
        sID = new String(byMsg, offset,
                MobileConstants.MAX_ID_LEN).trim();
        offset += MobileConstants.MAX_ID_LEN;
        
        // Decodes the call type
        if (offset >= length) {
            throw new ToolException(
                    "MobileCallRecordInfo decode error: no CallType in the record");
        }
        nCallType = byMsg[offset];
        offset++;
        
        // Decodes the length field
        if ((offset + 4) > length) {
            throw new ToolException(
            "MobileCallRecordInfo decode error: no Address in the record");
        }                      
        nLen = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;
        
        // Decodes the value field
        if ((offset + nLen) > length) {
            throw new ToolException(
            "MobileCallRecordInfo decode error: no Address in the record");                    
        }
        sAddress = new String(byMsg, offset, nLen);
        offset += nLen;  
               
        // Decodes the length field
        if ((offset + 4) > length) {
            throw new ToolException(
            "MobileCallRecordInfo decode error: no Name in the record");
        }                      
        nLen = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;
        
        // Decodes the value field
        if ((offset + nLen) > length) {
            throw new ToolException(
            "MobileCallRecordInfo decode error: no Name in the record");                    
        }
        sName = new String(byMsg, offset, nLen);
        offset += nLen;
        
        // Decodes the start date
        if ((offset + MobileConstants.MAX_TIME_LEN) > length) {
            throw new ToolException(
                "MobileCallRecordInfo decode error: no StartDate in the record");
        }
        sStartDate = new String(byMsg, offset,
                MobileConstants.MAX_TIME_LEN).trim();
        offset += MobileConstants.MAX_TIME_LEN;
           
        // Decodes the duration times
        if ((offset + 4) > length) {
            throw new ToolException(
            "MobileCallRecordInfo decode error: no Duration in the record");
        }  
        nDuration = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;
        
        return offset - start;
    }

    /**
     * Encodes the MobileCallRecordInfo object into a buffer
     * 
     * @param baMsg The message buffer
     * @return Returns the encoded buffer length
     * @throws Throws a ToolException when some errors occur
     */
    public int encode(ByteArray baMsg) throws ToolException {

        int nLen = baMsg.length();
        
        // Encodes the contact ID
        byte[] byID = new byte[MobileConstants.MAX_ID_LEN];
        GenHelper.copyString(byID, sID);
        baMsg.append(byID);
        
        // Encodes the call type
        baMsg.append(nCallType);
        
        // Encodes the address
        baMsg.append(sAddress.getBytes().length); 
        baMsg.append(sAddress);
        
        // Encodes the name
        baMsg.append(sName.getBytes().length);        
        baMsg.append(sName);
                    
        // Encodes the start date
        byte[] byTime = new byte[MobileConstants.MAX_TIME_LEN];
        GenHelper.copyString(byTime, sStartDate);
        baMsg.append(byTime);
        
        // Encodes the duration
        baMsg.append(nDuration);
                
        return baMsg.length() - nLen;
    }

    /**
     * Formats the MobileCallRecordInfo object to a readable string
     * 
     * @param buf  The buffer to save the format string
     * @param sPrefix The Prefix
     * @return Returns the format string length
     * @throws Throws a ToolException when some errors occur
     */
    public int format(StringBuffer buf, String sPrefix) throws ToolException {

        int nLen = buf.length();
        
        // Formats the contact ID
        buf.append(sPrefix).
            append("ID = ").
            append(sID).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the call type
        buf.append(sPrefix).
            append("CallType = ").
            append(nCallType).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the address
        buf.append(sPrefix).
            append("Address = ").
            append(sAddress).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the name
        buf.append(sPrefix).
            append("Name = ").
            append(sName).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the start date
        buf.append(sPrefix).
            append("StartDate = ").
            append(sStartDate).
            append(Constants.LINE_SEPARATOR);
       
        // Formats the duration
        buf.append(sPrefix).
            append("Duration = ").
            append(nDuration).
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
                "Message verify error: the field 'MobileCallRecordInfo' is null");
        }

        if (!(field instanceof MobileCallRecordInfo)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MobileCallRecordInfo' object");
        }

        MobileCallRecordInfo obj = (MobileCallRecordInfo)field;
        
        doVerifyField("ID", sID, obj, obj.sID);
        doVerifyField("CallType", nCallType, obj, obj.nCallType);
        doVerifyField("Address", sAddress, obj, obj.sAddress);       
        doVerifyField("Name", sName, obj, obj.sName);
        doVerifyField("StartDate", sStartDate, obj, obj.sStartDate);       
        doVerifyField("Duration", nDuration, obj, obj.nDuration);

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
                "Message verify error: the field 'MobileCallRecordInfo' is null");
        }

        if (!(field instanceof MobileCallRecordInfo)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MobileCallRecordInfo' object");
        }
        
        MobileCallRecordInfo obj = (MobileCallRecordInfo)field;
        
        doVerifyPresentField("ID", sID, obj, obj.sID);
        doVerifyPresentField("CallType", nCallType, obj, obj.nCallType);
        doVerifyPresentField("Address", sAddress, obj, obj.sAddress);       
        doVerifyPresentField("Name", sName, obj, obj.sName);
        doVerifyPresentField("StartDate", sStartDate, obj, obj.sStartDate);       
        doVerifyPresentField("Duration", nDuration, obj, obj.nDuration);
        
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
                "Message verify error: the field 'MobileCallRecordInfo' is null");
        }

        if (!(field instanceof MobileCallRecordInfo)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MobileCallRecordInfo' object");
        }

        MobileCallRecordInfo obj = (MobileCallRecordInfo)field;
        
        doVerifySpecifiedField("ID", sID, obj, obj.sID);
        doVerifySpecifiedField("CallType", nCallType, obj, obj.nCallType);
        doVerifySpecifiedField("Address", sAddress, obj, obj.sAddress);       
        doVerifySpecifiedField("Name", sName, obj, obj.sName);
        doVerifySpecifiedField("StartDate", sStartDate, obj, obj.sStartDate);       
        doVerifySpecifiedField("Duration", nDuration, obj, obj.nDuration);
        
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || !(obj instanceof MobileCallRecordInfo)) {
            return false;
        }
        
        MobileCallRecordInfo oMsgField = (MobileCallRecordInfo)obj;

        return oMsgField.sID.equals(sID)
                && oMsgField.nCallType == nCallType
                && oMsgField.sAddress.equals(sAddress)
                && oMsgField.sName.equals(sName)
                && oMsgField.sStartDate.equals(sStartDate)
                && oMsgField.nDuration == nDuration;
    }
    
}
