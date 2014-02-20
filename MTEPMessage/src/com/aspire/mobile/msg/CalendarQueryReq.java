/**
 * @(#) CalendarQueryReq.java Created on 2009-2-18
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.msg;

import com.aspire.Constants;
import com.aspire.mobile.MobileConstants;
import com.aspire.msg.MsgBase;
import com.aspire.util.ByteArray;
import com.aspire.util.GenApi;
import com.aspire.util.GenHelper;
import com.aspire.util.ToolException;

/**
 * The class <code>CalendarQueryReq</code>
 *
 * @author xushengyong
 * @version 1.0
 */
public class CalendarQueryReq extends MobileMsgBase {
    
    public final static byte TAG_ID = 0x01;              // ID
    public final static byte TAG_ALARM_DATE = 0x11;      // AlarmDate
    public final static byte TAG_START_TIME = 0x12;      // StartTime
    public final static byte TAG_END_TIME = 0x13;        // EndTime
    public final static byte TAG_START_INDEX = 0x02;     // StartIndex
    public final static byte TAG_COUNT = 0x03;           // Counts
    
    /**
     * The contact ID
     */
    protected String sID = "";
    
    /**
     * The alarm date
     */
    protected String sAlarmDate = GenApi.getTime();

    /**
     * The start time
     */
    protected String sStartTime = GenApi.getTime();
    
    /**
     * The end time
     */
    protected String sEndTime = GenApi.getTime();
    
    /**
     * The start index
     */    
    protected int nStartIndex = 0;    

    /**
     * The count
     */    
    protected int nCount = 0;    

    
    /**
     * The constructor
     */
    public CalendarQueryReq() {
        super(MobileConstants.CALENDAR_QUERY_REQ, "CalendarQueryReq");
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
     * Sets the start index
     * @param nStartIndex The start index
     * @throws ToolException 
     */    
    public void setStartIndex(int nStartIndex) throws ToolException {
        this.nStartIndex = nStartIndex;
        setVerify("StartIndex", true);
    }
    
    /**
     * Sets the start index
     * @param sStartIndex The start index
     * @throws ToolException 
     */
    public void setStartIndex(String sStartIndex) throws ToolException {
        setStartIndex(GenApi.strToInt(sStartIndex, "StartIndex"));
    }
    
    /**
     * Gets the start index
     * @return Returns the start index
     */
    public String getStartIndex() {
        return Integer.toString(nStartIndex);
    }

    /**
     * Gets the start index
     * @return Returns the start index
     */
    public int getStartIndexValue() {
        return nStartIndex;
    }
    
    /**
     * Sets the Count
     * @param nCount The Count
     * @throws ToolException 
     */    
    public void setCount(int nCount) throws ToolException {
        this.nCount = nCount;
        setVerify("Count", true);
    }
    
    /**
     * Sets the Count
     * @param sCount The Count
     * @throws ToolException 
     */
    public void setCount(String sCount) throws ToolException {
        setCount(GenApi.strToInt(sCount, "Count"));
    }
    
    /**
     * Gets the Count
     * @return Returns the Count
     */
    public String getCount() {
        return Integer.toString(nCount);
    }

    /**
     * Gets the Count
     * @return Returns the Count
     */
    public int getCountValue() {
        return nCount;
    }
    
    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#decode(byte[], int, int)
     */
    public int decode(byte[] byMsg, int start, int length) throws ToolException {
        
        int nRead = super.decode(byMsg, start, length);
        int offset = start + nRead;
        
        while (offset < length) {            
            // Decodes the tag field
            byte nTag = byMsg[offset];
            offset++;
            
            switch (nTag) {
            case CalendarQueryReq.TAG_ID:
            {
                // Decodes the ID
                if ((offset + MobileConstants.MAX_ID_LEN) > length) {
                    throw new ToolException(
                        "CalendarQueryReq decode error: no ID in the record");
                }
                sID = new String(byMsg, offset,
                        MobileConstants.MAX_ID_LEN).trim();
                setPresent("ID", true);
                offset += MobileConstants.MAX_ID_LEN;           
                break;
            }
            case CalendarQueryReq.TAG_ALARM_DATE:
            {             
                // Decodes the alarm date
                if ((offset + MobileConstants.MAX_TIME_LEN) > length) {
                    throw new ToolException(
                        "CalendarQueryReq decode error: no AlarmDate in the record");
                }
                sAlarmDate = new String(byMsg, offset,
                        MobileConstants.MAX_TIME_LEN).trim();
                setPresent("AlarmDate", true);
                offset += MobileConstants.MAX_TIME_LEN;       
                break;
            }
            case CalendarQueryReq.TAG_START_TIME:
            {             
                // Decodes the start time
                if ((offset + MobileConstants.MAX_TIME_LEN) > length) {
                    throw new ToolException(
                        "CalendarQueryReq decode error: no StartTime in the record");
                }
                sStartTime = new String(byMsg, offset,
                        MobileConstants.MAX_TIME_LEN).trim();
                setPresent("StartTime", true);
                offset += MobileConstants.MAX_TIME_LEN;       
                break;
            }
            case CalendarQueryReq.TAG_END_TIME:
            {             
                // Decodes the end time
                if ((offset + MobileConstants.MAX_TIME_LEN) > length) {
                    throw new ToolException(
                        "CalendarQueryReq decode error: no EndTime in the record");
                }
                sEndTime = new String(byMsg, offset,
                        MobileConstants.MAX_TIME_LEN).trim();
                setPresent("EndTime", true);
                offset += MobileConstants.MAX_TIME_LEN;       
                break;
            }            
            case CalendarQueryReq.TAG_START_INDEX:
            {
                // Decodes the value filed
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "CalendarQueryReq decode error: no StartIndex in the record");
                }                      
                nStartIndex = ByteArray.bytesToInt(byMsg, offset);
                setPresent("StartIndex", true);
                offset += 4;            
                break;
            }
            case CalendarQueryReq.TAG_COUNT:
            {
                // Decodes the value filed
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "CalendarQueryReq decode error: no Count in the record");
                }                      
                nCount = ByteArray.bytesToInt(byMsg, offset);
                setPresent("Count", true);
                offset += 4;            
                break;
            }             
            default:
            {
                throw new ToolException(
                        "CalendarQueryReq decode error: unkown tag type: " + nTag);
            }
            }     
        } // End of while (offset < length)     
        
        return offset - start;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#encode(com.aspire.util.ByteArray)
     */
    public int encode(ByteArray baMsg) throws ToolException {

        int nLen = baMsg.length();

        super.encode(baMsg);
        
        if (isPresent("ID"))
        {
            // Encodes the contact ID
            baMsg.append(CalendarQueryReq.TAG_ID);
            byte[] byID = new byte[MobileConstants.MAX_ID_LEN];
            GenHelper.copyString(byID, sID);
            baMsg.append(byID);
        }

        if (isPresent("AlarmDate"))
        {
            // Encodes the alarm date
            baMsg.append(CalendarQueryReq.TAG_ALARM_DATE);
            byte[] byTime = new byte[MobileConstants.MAX_TIME_LEN];
            GenHelper.copyString(byTime, sAlarmDate);
            baMsg.append(byTime);
        }
        
        if (isPresent("StartTime"))
        {
            // Encodes the start time
            baMsg.append(CalendarQueryReq.TAG_START_TIME);
            byte[] byTime = new byte[MobileConstants.MAX_TIME_LEN];
            GenHelper.copyString(byTime, sStartTime);
            baMsg.append(byTime);
        }      
        
        if (isPresent("EndTime"))
        {
            // Encodes the end time
            baMsg.append(CalendarQueryReq.TAG_END_TIME);
            byte[] byTime = new byte[MobileConstants.MAX_TIME_LEN];
            GenHelper.copyString(byTime, sEndTime);
            baMsg.append(byTime);
        } 

        if (isPresent("StartIndex"))
        {        
            // Encodes the start index
            baMsg.append(CalendarQueryReq.TAG_START_INDEX);
            baMsg.append(nStartIndex);
        }

        if (isPresent("Count"))
        {        
            // Encodes the count
            baMsg.append(CalendarQueryReq.TAG_COUNT);
            baMsg.append(nCount);      
        }
        
        return baMsg.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#format(java.lang.StringBuffer, java.lang.String)
     */
    public int format(StringBuffer buf, String sPrefix) throws ToolException {

        int nLen = buf.length();

        super.format(buf, sPrefix);
        
        if (isPresent("ID"))
        {
            // Formats the contact ID
            buf.append(sPrefix).
                append("ID = ").
                append(sID).
                append(Constants.LINE_SEPARATOR);
        }

        if (isPresent("AlarmDate"))
        {
            // Formats the alarm date
            buf.append(sPrefix).
                append("AlarmDate = ").
                append(sAlarmDate).
                append(Constants.LINE_SEPARATOR);
        }
        
        if (isPresent("StartTime"))
        {
            // Formats the start time
            buf.append(sPrefix).
                append("StartTime = ").
                append(sStartTime).
                append(Constants.LINE_SEPARATOR);
        }
        
        if (isPresent("EndTime"))
        {
            // Formats the end time
            buf.append(sPrefix).
                append("EndTime = ").
                append(sEndTime).
                append(Constants.LINE_SEPARATOR);
        }
        
        if (isPresent("StartIndex"))
        {
            // Formats the start index
            buf.append(sPrefix).
                append("StartIndex = ").
                append(nStartIndex).
                append(Constants.LINE_SEPARATOR);
        }

        if (isPresent("Count"))
        {
            // Formats the count
            buf.append(sPrefix).
                append("Count = ").
                append(nCount).
                append(Constants.LINE_SEPARATOR);            
        }
        
        return buf.length() - nLen;
    }
    
    /*
     * (non-Javadoc)
     * @see com.aspire.msg.soap.SoapMsgBase#verify(com.aspire.msg.MsgBase)
     */
    public boolean verify(MsgBase msgVerified) throws ToolException {

        if (this == msgVerified) return true;
        
        if (msgVerified == null) {
            throw new ToolException(
                 "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof CalendarQueryReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'CalendarQueryReq' message");
        }
        
        CalendarQueryReq obj = (CalendarQueryReq)msgVerified;
        
        // All fields below is optional, so call doVerifyPresentField
        doVerifyPresentField("ID", sID, obj, obj.sID);
        doVerifyPresentField("AlarmDate", sAlarmDate, obj, obj.sAlarmDate);     
        doVerifyPresentField("StartTime", sStartTime, obj, obj.sStartTime);       
        doVerifyPresentField("EndTime", sEndTime, obj, obj.sEndTime);
        doVerifyPresentField("StartIndex", nStartIndex, obj, obj.nStartIndex);
        doVerifyPresentField("Count", nCount, obj, obj.nCount);
        
        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.soap.SoapMsgBase#verifyPresent(com.aspire.msg.MsgBase)
     */
    public boolean verifyPresent(MsgBase msgVerified) throws ToolException {

        if (this == msgVerified) return true;

        if (msgVerified == null) {
            throw new ToolException(
                 "Message verify error: the message to be verified is null");
        }
        
        if (!(msgVerified instanceof CalendarQueryReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'CalendarQueryReq' message");
        }

        CalendarQueryReq obj = (CalendarQueryReq)msgVerified;

        doVerifyPresentField("ID", sID, obj, obj.sID);
        doVerifyPresentField("AlarmDate", sAlarmDate, obj, obj.sAlarmDate);     
        doVerifyPresentField("StartTime", sStartTime, obj, obj.sStartTime);       
        doVerifyPresentField("EndTime", sEndTime, obj, obj.sEndTime);
        doVerifyPresentField("StartIndex", nStartIndex, obj, obj.nStartIndex);
        doVerifyPresentField("Count", nCount, obj, obj.nCount);

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.soap.SoapMsgBase#verifySpecified(com.aspire.msg.MsgBase)
     */
    public boolean verifySpecified(MsgBase msgVerified) throws ToolException {

        if (this == msgVerified) return true;
        
        if (msgVerified == null) {
            throw new ToolException(
                 "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof CalendarQueryReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'CalendarQueryReq' message");
        }

        CalendarQueryReq obj = (CalendarQueryReq)msgVerified;
        
        doVerifySpecifiedField("ID", sID, obj, obj.sID);
        doVerifySpecifiedField("AlarmDate", sAlarmDate, obj, obj.sAlarmDate);     
        doVerifySpecifiedField("StartTime", sStartTime, obj, obj.sStartTime);       
        doVerifySpecifiedField("EndTime", sEndTime, obj, obj.sEndTime);
        doVerifySpecifiedField("StartIndex", nStartIndex, obj, obj.nStartIndex);
        doVerifySpecifiedField("Count", nCount, obj, obj.nCount);

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.FieldMsgBase#createDefaultResponse()
     */
    public MsgBase createDefaultResponse() {
        
        CalendarQueryResp oResp = new CalendarQueryResp();
        
        oResp.setSequence(nSeqNum);
        
        return oResp;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj)) return false;

        if (!(obj instanceof CalendarQueryReq)) {
            return false;
        }

        CalendarQueryReq oMsgObj = (CalendarQueryReq)obj;
        
        if (isPresent("ID") && oMsgObj.isPresent("ID"))
        {
            if (!oMsgObj.sID.equals(sID))
            {
                return false;
            }
        } else {
            return false;
        }
        
        if (isPresent("AlarmDate") && oMsgObj.isPresent("AlarmDate"))
        {
            if (!oMsgObj.sAlarmDate.equals(sAlarmDate))
            {
                return false;
            }
        } else {
            return false;
        }
        
        if (isPresent("StartTime") && oMsgObj.isPresent("StartTime"))
        {
            if (!oMsgObj.sStartTime.equals(sStartTime))
            {
                return false;
            }
        } else {
            return false;
        }
        
        if (isPresent("EndTime") && oMsgObj.isPresent("EndTime"))
        {
            if (!oMsgObj.sEndTime.equals(sEndTime))
            {
                return false;
            }
        } else {
            return false;
        }
        
        if (isPresent("StartIndex") && oMsgObj.isPresent("StartIndex"))
        {
            if (!(oMsgObj.nStartIndex == nStartIndex))
            {
                return false;
            }
        } else {
            return false;
        }

        if (isPresent("Count") && oMsgObj.isPresent("Count"))
        {
            if (!(oMsgObj.nCount == nCount))
            {
                return false;
            }
        } else {
            return false;
        }

        return true;
    }
    
    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#isPresent(java.lang.String)
     */
    public boolean isPresent(String field) {
        return buildFields.contains(field);
    }

}
