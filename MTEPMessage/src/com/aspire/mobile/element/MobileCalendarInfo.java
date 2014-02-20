/**
 * @(#) MobileCalendarInfo.java Created on 2009-2-18
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
 * The class <code>MobileCalendarInfo</code>
 *
 * @author xushengyong
 * @version 1.0
 */
public class MobileCalendarInfo extends MobileElementBase {
    
    /**
     * The contact ID
     */
    protected String sID = ""; 

    /**
     * The alarm date
     */
    protected String sAlarmDate = GenApi.getTime();
    
    /**
     * The alarmed flag
     */
    protected byte nAlarmed = 0x00;

    /**
     * The start time
     */
    protected String sStartTime = GenApi.getTime();
    
    /**
     * The end time
     */
    protected String sEndTime = GenApi.getTime();
 
    /**
     * The recurrence times
     */
    protected byte nRecurrence = MobileConstants.CAL_EVENT_RECURRENT_NO_REPEAT;    

    /**
     * The event name
     */
    protected String sEventName = "";
    
    /**
     * The event notes
     */
    protected String sEventNotes = "";
    
    /**
     * The constructor
     */
    public MobileCalendarInfo() {
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
     * Sets the alarm date
     * @param sAlarmDate The creation date
     */
    public void setAlarmDate(String sAlarmDate) {
        this.sAlarmDate = GenApi.formatDate(sAlarmDate);
        setVerify("AlarmDate");
    }

    /**
     * Gets the alarm date
     * @return Returns alarm date
     */
    public String getAlarmDate() {
        return sAlarmDate;
    }
    
    /**
     * Sets the alarmed flag
     * @param nAlarmed The alarmed flag
     */
    public void setAlarmed(byte nAlarmed) {
        this.nAlarmed = nAlarmed;
        setVerify("Alarmed");
    }

    /**
     * Sets the alarmed flag
     * @param sType The alarmed flag
     * @throws ToolException 
     */
    public void setAlarmed(String sAlarmed) throws ToolException {
        setAlarmed(GenApi.strToByte(sAlarmed, "Alarmed"));
    }

    /**
     * Gets the alarmed flag
     * @return Returns The alarmed flag
     */
    public String getAlarmed() {
        return Byte.toString(nAlarmed);
    }

    /**
     * Gets the alarmed flag
     * @return Returns the alarmed flag
     */
    public byte getAlarmedValue() {
        return nAlarmed;
    }      
    
    /**
     * Sets the start time
     * @param sStartTime The creation date
     */
    public void setStartTime(String sStartTime) {
        this.sStartTime = GenApi.formatDate(sStartTime);
        setVerify("StartTime");
    }

    /**
     * Gets the start time
     * @return Returns start time
     */
    public String getStartTime() {
        return sStartTime;
    }
    
    /**
     * Sets the end time
     * @param sEndTime The creation date
     */
    public void setEndTime(String sEndTime) {
        this.sEndTime = GenApi.formatDate(sEndTime);
        setVerify("EndTime");
    }

    /**
     * Gets the end time
     * @return Returns end time
     */
    public String getEndTime() {
        return sEndTime;
    }
    
    /**
     * Sets the Recurrence
     * @param nRecurrence The Recurrence
     */
    public void setRecurrence(byte nRecurrence) {
        this.nRecurrence = nRecurrence;
        setVerify("Recurrence");
    }

    /**
     * Sets the Recurrence
     * @param sType The Recurrence
     * @throws ToolException 
     */
    public void setRecurrence(String sRecurrence) throws ToolException {
        setRecurrence(GenApi.strToByte(sRecurrence, "Recurrence"));
    }

    /**
     * Gets the Recurrence
     * @return Returns The Recurrence
     */
    public String getRecurrence() {
        return Byte.toString(nRecurrence);
    }

    /**
     * Gets the Recurrence
     * @return Returns the Recurrence
     */
    public byte getRecurrenceValue() {
        return nRecurrence;
    }    

    /**
     * Sets the event name
     * @param sEventName The event name
     */
    public void setEventName(String sEventName) {
        this.sEventName = sEventName;
        setVerify("EventName", true);
    }
    
    /**
     * Gets the event name
     * @return Return the event name
     */
    public String getEventName() {
        return sEventName;
    }
 
    /**
     * Sets the event notes
     * @param sEventNotes The event notes
     */
    public void setEventNotes(String sEventNotes) {
        this.sEventNotes = sEventNotes;
        setVerify("EventNotes", true);
    }
    
    /**
     * Gets the event notes
     * @return Return the event notes
     */
    public String getEventNotes() {
        return sEventNotes;
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
        
        // Decodes the ID
        if ((offset + MobileConstants.MAX_ID_LEN) > length) {
            throw new ToolException(
                "MobileCalendarInfo decode error: no ID in the record");
        }
        sID = new String(byMsg, offset,
                MobileConstants.MAX_ID_LEN).trim();
        offset += MobileConstants.MAX_ID_LEN;
        
        // Decodes the alarm date
        if ((offset + MobileConstants.MAX_TIME_LEN) > length) {
            throw new ToolException(
                    "MobileCalendarInfo decode error: no AlarmDate in the record");
        }
        sAlarmDate = new String(byMsg, offset,
                MobileConstants.MAX_TIME_LEN).trim();
        offset += MobileConstants.MAX_TIME_LEN;
        
        // Decodes the alarmed flag
        if (offset >= length) {
            throw new ToolException(
                    "MobileCalendarInfo decode error: no Alarmed in the record");
        }
        nAlarmed = byMsg[offset];
        offset++;
        
        // Decodes the start time
        if ((offset + MobileConstants.MAX_TIME_LEN) > length) {
            throw new ToolException(
                "MobileCalendarInfo decode error: no StartTime in the record");
        }
        sStartTime = new String(byMsg, offset,
                MobileConstants.MAX_TIME_LEN).trim();
        offset += MobileConstants.MAX_TIME_LEN;
        
        // Decodes the end time
        if ((offset + MobileConstants.MAX_TIME_LEN) > length) {
            throw new ToolException(
                "MobileCalendarInfo decode error: no EndTime in the record");
        }
        sEndTime = new String(byMsg, offset,
                MobileConstants.MAX_TIME_LEN).trim();
        offset += MobileConstants.MAX_TIME_LEN;
        
        // Decodes the recurrence times
        if (offset >= length) {
            throw new ToolException(
                    "MobileCalendarInfo decode error: no Recurrence in the record");
        }
        nRecurrence = byMsg[offset];
        offset++;       
          
        // Decodes the length field
        if ((offset + 4) > length) {
            throw new ToolException(
            "MobileCalendarInfo decode error: no EventName in the record");
        }                      
        nLen = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;
        
        // Decodes the value field
        if ((offset + nLen) > length) {
            throw new ToolException(
            "MobileCalendarInfo decode error: no EventName in the record");                    
        }
        sEventName = new String(byMsg, offset, nLen);
        offset += nLen;  
               
        // Decodes the length field
        if ((offset + 4) > length) {
            throw new ToolException(
            "MobileCalendarInfo decode error: no EventNotes in the record");
        }                      
        nLen = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;
        
        // Decodes the value field
        if ((offset + nLen) > length) {
            throw new ToolException(
            "MobileCalendarInfo decode error: no EventNotes in the record");                    
        }
        sEventNotes = new String(byMsg, offset, nLen);
        offset += nLen;   
        
        return offset - start;
    }

    /**
     * Encodes the MobileCalendarInfo object into a buffer
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
              
        // Encodes the alarm date
        byte[] byTime = new byte[MobileConstants.MAX_TIME_LEN];
        GenHelper.copyString(byTime, sAlarmDate);
        baMsg.append(byTime);
    
        // Encodes the alarmed flag
        baMsg.append(nAlarmed);
        
        // Encodes the start time
        GenHelper.copyString(byTime, sStartTime);
        baMsg.append(byTime);
 
        // Encodes the end time
        GenHelper.copyString(byTime, sEndTime);
        baMsg.append(byTime);
        
        // Encodes the recurrence
        baMsg.append(nRecurrence);
        
        // Encodes the event name
        baMsg.append(sEventName.getBytes().length); 
        baMsg.append(sEventName);
        
        // Encodes the event notes
        baMsg.append(sEventNotes.getBytes().length);        
        baMsg.append(sEventNotes);
        
        return baMsg.length() - nLen;
    }

    /**
     * Formats the MobileCalendarInfo object to a readable string
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
        
        // Formats the alarm date
        buf.append(sPrefix).
            append("AlarmDate = ").
            append(sAlarmDate).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the alarmed flag
        buf.append(sPrefix).
            append("Alarmed = ").
            append(nAlarmed).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the start time
        buf.append(sPrefix).
            append("StartTime = ").
            append(sStartTime).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the end time
        buf.append(sPrefix).
            append("EndTime = ").
            append(sEndTime).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the recurrence
        buf.append(sPrefix).
            append("Recurrence = ").
            append(nRecurrence).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the event name
        buf.append(sPrefix).
            append("EventName = ").
            append(sEventName).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the event notes
        buf.append(sPrefix).
            append("EventNotes = ").
            append(sEventNotes).
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
                "Message verify error: the field 'MobileCalendarInfo' is null");
        }

        if (!(field instanceof MobileCalendarInfo)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MobileCalendarInfo' object");
        }

        MobileCalendarInfo obj = (MobileCalendarInfo)field;
        
        doVerifyField("ID", sID, obj, obj.sID);
        doVerifyField("AlarmDate", sAlarmDate, obj, obj.sAlarmDate); 
        doVerifyField("Alarmed", nAlarmed, obj, obj.nAlarmed);
        doVerifyField("StartTime", sStartTime, obj, obj.sStartTime);       
        doVerifyField("EndTime", sEndTime, obj, obj.sEndTime);
        doVerifyField("Recurrence", nRecurrence, obj, obj.nRecurrence);
        doVerifyField("EventName", sEventName, obj, obj.sEventName);       
        doVerifyField("EventNotes", sEventNotes, obj, obj.sEventNotes);

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
                "Message verify error: the field 'MobileCalendarInfo' is null");
        }

        if (!(field instanceof MobileCalendarInfo)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MobileCalendarInfo' object");
        }
        
        MobileCalendarInfo obj = (MobileCalendarInfo)field;
        
        doVerifyPresentField("ID", sID, obj, obj.sID);
        doVerifyPresentField("AlarmDate", sAlarmDate, obj, obj.sAlarmDate); 
        doVerifyPresentField("Alarmed", nAlarmed, obj, obj.nAlarmed);
        doVerifyPresentField("StartTime", sStartTime, obj, obj.sStartTime);       
        doVerifyPresentField("EndTime", sEndTime, obj, obj.sEndTime);
        doVerifyPresentField("Recurrence", nRecurrence, obj, obj.nRecurrence);
        doVerifyPresentField("EventName", sEventName, obj, obj.sEventName);       
        doVerifyPresentField("EventNotes", sEventNotes, obj, obj.sEventNotes);
        
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
                "Message verify error: the field 'MobileCalendarInfo' is null");
        }

        if (!(field instanceof MobileCalendarInfo)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MobileCalendarInfo' object");
        }

        MobileCalendarInfo obj = (MobileCalendarInfo)field;
        
        doVerifySpecifiedField("ID", sID, obj, obj.sID);
        doVerifySpecifiedField("AlarmDate", sAlarmDate, obj, obj.sAlarmDate); 
        doVerifySpecifiedField("Alarmed", nAlarmed, obj, obj.nAlarmed);
        doVerifySpecifiedField("StartTime", sStartTime, obj, obj.sStartTime);       
        doVerifySpecifiedField("EndTime", sEndTime, obj, obj.sEndTime);
        doVerifySpecifiedField("Recurrence", nRecurrence, obj, obj.nRecurrence);
        doVerifySpecifiedField("EventName", sEventName, obj, obj.sEventName);       
        doVerifySpecifiedField("EventNotes", sEventNotes, obj, obj.sEventNotes);
        
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || !(obj instanceof MobileCalendarInfo)) {
            return false;
        }
        
        MobileCalendarInfo oMsgField = (MobileCalendarInfo)obj;

        return oMsgField.sID.equals(sID)
                && oMsgField.sAlarmDate.equals(sAlarmDate)
                && oMsgField.nAlarmed == nAlarmed
                && oMsgField.sStartTime.equals(sStartTime)
                && oMsgField.sEndTime.equals(sEndTime)
                && oMsgField.nRecurrence == nRecurrence
                && oMsgField.sEventName.equals(sEventName)
                && oMsgField.sEventNotes.equals(sEventNotes);
    }
 
}
