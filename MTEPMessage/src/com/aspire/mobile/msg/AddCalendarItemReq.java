/**
 * @(#) AddCalendarItemReq.java Created on 2009-2-18
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.msg;

import java.util.ArrayList;

import com.aspire.Constants;
import com.aspire.mobile.MobileConstants;
import com.aspire.mobile.element.MobileCalendarInfo;
import com.aspire.msg.MsgBase;
import com.aspire.util.ByteArray;
import com.aspire.util.GenApi;
import com.aspire.util.ToolException;

/**
 * 
 * The class <code>AddCalendarItemReq</code>
 *
 * @author Link Wang
 * @version 1.0
 */
public class AddCalendarItemReq extends MobileMsgBase {
    
    /**
     * The record count
     */
    protected int nRecordCount = 0;
    
    /**
     * The MobileCalendarInfo information
     */
    protected ArrayList<MobileCalendarInfo> aCalendarInfos =
        new ArrayList<MobileCalendarInfo>();

    /**
     * The constructor
     */
    public AddCalendarItemReq() {
        super(MobileConstants.ADD_CALENDAR_ITEM_REQ, "AddCalendarItemReq");
    }
    
    /**
     * Sets the record count
     * @param nRecordCount The the record count
     */
    public void setRecordCount(int nRecordCount) {
        this.nRecordCount = nRecordCount;
        setVerify("RecordCount");
    }
    
    /**
     * Sets the record count
     * @param sRecordCount The the record count
     * @throws ToolException 
     */
    public void setRecordCount(String sRecordCount) throws ToolException {
        setRecordCount(GenApi.strToInt(sRecordCount, "RecordCount"));
    }

    /**
     * Gets the record count
     * @return Returns the record count
     */
    public String getRecordCount() {
        return Integer.toString(nRecordCount);
    }

    /**
     * Gets the record count
     * @return Returns file type
     */
    public int getRecordCountValue() {
        return nRecordCount;
    }

    /**
     * Sets the message info
     * @param aCalendarInfos The the message info
     */
    public void setCalendarInfo(ArrayList<MobileCalendarInfo> aCalendarInfos) {
        this.aCalendarInfos = aCalendarInfos;
        setVerify("CalendarInfo", true);
    }

    /**
     * Gets the message info
     * @return Returns the message info
     */
    public ArrayList<MobileCalendarInfo> getCalendarInfo() {
        return aCalendarInfos;
    }

    /**
     * Adds the message info
     * @param oCalendarInfo The the message info object to be added
     */
    public void addCalendarInfo(MobileCalendarInfo oCalendarInfo) {
        if (aCalendarInfos.contains(oCalendarInfo)) return;
        aCalendarInfos.add(oCalendarInfo);
    }
    
    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileRespBase#decode(byte[], int, int)
     */
    public int decode(byte[] byMsg, int start, int length) throws ToolException {
        int nRead = super.decode(byMsg, start, length);
        int offset = start + nRead;

        // Decodes the record count
        nRecordCount = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;

        // Decodes the message info
        setPresent("CalendarInfo", nRecordCount > 0);
        for (int i = 0; i < nRecordCount; i++) {
            if (offset >= length) {
                break;
            }
            MobileCalendarInfo oInfo = new MobileCalendarInfo();
            offset += oInfo.decode(byMsg, offset, length);
            aCalendarInfos.add(oInfo);
        }

        return offset - start;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileRespBase#encode(com.aspire.util.ByteArray)
     */
    public int encode(ByteArray baMsg) throws ToolException {

        int nLen = baMsg.length();

        super.encode(baMsg);

        // Encodes the record count
        baMsg.append(aCalendarInfos.size());

        // Encodes the message info
        for (int i = 0; i < aCalendarInfos.size(); i++) {
            aCalendarInfos.get(i).encode(baMsg);
        }

        return baMsg.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileRespBase#format(java.lang.StringBuffer, java.lang.String)
     */
    public int format(StringBuffer buf, String sPrefix) throws ToolException {

        int nLen = buf.length();

        super.format(buf, sPrefix);

        // Formats the record count
        buf.append(sPrefix).
            append("RecordCount = ").
            append(nRecordCount).
            append(Constants.LINE_SEPARATOR);

        // Formats the message info
        String sInfoPrefix = sPrefix + "    ";
        for (int i = 0; i < aCalendarInfos.size(); i++) {
            buf.append(sPrefix).
                append("CalendarInfo[No ").
                append(i).
                append("]").
                append(Constants.LINE_SEPARATOR).
                append(sPrefix).
                append("{").
                append(Constants.LINE_SEPARATOR);
            aCalendarInfos.get(i).format(buf, sInfoPrefix);
            buf.append(sPrefix).
                append("}").
                append(Constants.LINE_SEPARATOR);
        }

        return buf.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileRespBase#verify(com.aspire.msg.MsgBase)
     */
    public boolean verify(MsgBase msgVerified) throws ToolException {

        super.verify(msgVerified);

        if (msgVerified == null) {
            throw new ToolException(
                 "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof AddCalendarItemReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'AddCalendarItemReq' message");
        }

        AddCalendarItemReq obj = (AddCalendarItemReq)msgVerified;

        doVerifyField("RecordCount", nRecordCount, obj, obj.nRecordCount);
        doVerifyField("CalendarInfo", aCalendarInfos, obj, obj.aCalendarInfos, false);

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileRespBase#verifyPresent(com.aspire.msg.MsgBase)
     */
    public boolean verifyPresent(MsgBase msgVerified) throws ToolException {

        if (this == msgVerified) return true;

        if (msgVerified == null) {
            throw new ToolException(
                 "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof AddCalendarItemReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'AddCalendarItemReq' message");
        }

        AddCalendarItemReq obj = (AddCalendarItemReq)msgVerified;
        
        doVerifyPresentField("RecordCount", nRecordCount, obj, obj.nRecordCount);
        doVerifyPresentField("CalendarInfo", aCalendarInfos, obj, obj.aCalendarInfos, false);

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileRespBase#verifySpecified(com.aspire.msg.MsgBase)
     */
    public boolean verifySpecified(MsgBase msgVerified) throws ToolException {

        super.verifySpecified(msgVerified);

        if (msgVerified == null) {
            throw new ToolException(
                 "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof AddCalendarItemReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'AddCalendarItemReq' message");
        }

        AddCalendarItemReq obj = (AddCalendarItemReq)msgVerified;
        
        doVerifySpecifiedField("RecordCount", nRecordCount, obj, obj.nRecordCount);
        doVerifySpecifiedField("CalendarInfo", aCalendarInfos, obj, obj.aCalendarInfos, false);

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.FieldMsgBase#createDefaultResponse()
     */
    public MsgBase createDefaultResponse() {
        AddCalendarItemResp oResp = new AddCalendarItemResp();
        oResp.setSequence(nSeqNum);
        return oResp;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileRespBase#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj)) return false;

        if (!(obj instanceof AddCalendarItemReq)) {
            return false;
        }

        AddCalendarItemReq oMsgObj = (AddCalendarItemReq)obj;

        return oMsgObj.nRecordCount == nRecordCount &&
            oMsgObj.aCalendarInfos.equals(aCalendarInfos);
    }
    
    /**
     * This method is used to clone a MessageBoxQueryResp object
     */
    public Object clone() throws CloneNotSupportedException {
        
        AddCalendarItemReq obj = (AddCalendarItemReq)super.clone();
        
        obj.aCalendarInfos = new ArrayList<MobileCalendarInfo>();
        
        for (int i = 0; i < aCalendarInfos.size(); i++) {
            obj.aCalendarInfos.add((MobileCalendarInfo)aCalendarInfos.get(i).clone());
        }
        
        return obj;
    }

}
