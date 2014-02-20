/**
 * @(#) EventReportReq.java Created on 2009-2-13
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
 * The class <code>EventReportReq</code>
 *
 * @author xuyong
 * @version 1.0
 */
public class EventReportReq extends MobileMsgBase {
    /**
     * 事件发生时的时间戳
     */
    protected String sTimeStamp = GenApi.getTime();

    /**
     * 事件类型<br>
     * 0x01：程序事件(启动、停止、安装)<br>
     * 0x02：短信事件<br>
     * 0x03：彩信事件<br>
     * 0x04：电话事件<br>
     * 0x05：网络访问事件<br>
     * 0x06：磁盘读写删事件<br>
     * 0x07：本地程序调用事件<br>
     */
    protected byte nEventType = 1;

    /**
     * Constructor
     * @param sMsgName The message name
     * @param nEventType Event type
     */
    public EventReportReq(String sMsgName, byte nEventType) {
        super(MobileConstants.EVENT_REPORT_REQ, sMsgName);
        this.nEventType = nEventType;
    }

    /**
     * Sets event time stamp
     * @param sTimeStamp The event time stamp
     */
    public void setTimeStamp(String sTimeStamp) {
        this.sTimeStamp = GenApi.formatDate(sTimeStamp);
        setVerify("TimeStamp");
    }

    /**
     * Gets the time stamp
     * @return Returns the time stamp
     */
    public String getTimeStamp() {
        return sTimeStamp;
    }
    
    /**
     * Sets the event type
     * @param nEventType The the event type
     */
    public void setEventType(byte nEventType) {
        this.nEventType = nEventType;
        setVerify("EventType");
    }

    /**
     * Sets the event type
     * @param sEventType The the event type
     * @throws ToolException 
     */
    public void setEventType(String sEventType) throws ToolException {
        setEventType(GenApi.strToByte(sEventType, "EventType"));
    }

    /**
     * Gets the event type
     * @return Returns the event type
     */
    public String getEventType() {
        return Byte.toString(nEventType);
    }

    /**
     * Gets the event type
     * @return Returns the event type
     */
    public byte getEventTypeValue() {
        return nEventType;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#decode(byte[], int, int)
     */
    public int decode(byte[] byMsg, int start, int length) throws ToolException {
        
        int nRead = super.decode(byMsg, start, length);
        int offset = start + nRead;
        
        // Decodes time stamp
        if (offset + MobileConstants.MAX_TIME_LEN > length) {
            throw new ToolException(
                "EventReportReq decode error: no time stamp in the message buffer");
        }
        sTimeStamp = new String(byMsg, offset,
                MobileConstants.MAX_TIME_LEN).trim();
        offset += MobileConstants.MAX_TIME_LEN;

        // Decodes event type
        if (offset >= length) {
            throw new ToolException(
                "EventReportReq decode error: no event-type in the message buffer");
        }

        nEventType = byMsg[offset];
        offset++;
        
        return offset - start;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#encode(com.aspire.util.ByteArray)
     */
    public int encode(ByteArray baMsg) throws ToolException {

        int nLen = baMsg.length();
        byte[] byTime = new byte[MobileConstants.MAX_TIME_LEN];
        
        super.encode(baMsg);

        GenHelper.copyString(byTime, sTimeStamp);

        baMsg.append(byTime);
        baMsg.append(nEventType);
        
        return baMsg.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#format(java.lang.StringBuffer, java.lang.String)
     */
    public int format(StringBuffer buf, String sPrefix) throws ToolException {

        int nLen = buf.length();

        super.format(buf, sPrefix);

        // Format Time-Stamp
        buf.append(sPrefix).
            append("TimeStamp = ").
            append(sTimeStamp).
            append(Constants.LINE_SEPARATOR);

        // Format Event Type
        buf.append(sPrefix).
            append("EventType = ").
            append(nEventType).
            append(Constants.LINE_SEPARATOR);

        return buf.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.FieldMsgBase#verify(com.aspire.msg.MsgBase)
     */
    public boolean verify(MsgBase msgVerified) throws ToolException {

        if (this == msgVerified) return true;

        if (msgVerified == null) {
            throw new ToolException(
                 "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof EventReportReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'EventReportReq' message");
        }

        EventReportReq obj = (EventReportReq)msgVerified;

        doVerifyField("TimeStamp", sTimeStamp, obj, obj.sTimeStamp);

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.FieldMsgBase#verifyPresent(com.aspire.msg.MsgBase)
     */
    public boolean verifyPresent(MsgBase msgVerified) throws ToolException {

        if (this == msgVerified) return true;

        if (msgVerified == null) {
            throw new ToolException(
                 "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof EventReportReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'EventReportReq' message");
        }

        EventReportReq obj = (EventReportReq)msgVerified;

        doVerifyPresentField("TimeStamp", sTimeStamp, obj, obj.sTimeStamp);
        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.FieldMsgBase#verifySpecified(com.aspire.msg.MsgBase)
     */
    public boolean verifySpecified(MsgBase msgVerified) throws ToolException {

        if (this == msgVerified) return true;

        if (msgVerified == null) {
            throw new ToolException(
                 "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof EventReportReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'EventReportReq' message");
        }

        EventReportReq obj = (EventReportReq)msgVerified;

        doVerifySpecifiedField("TimeStamp", sTimeStamp, obj, obj.sTimeStamp);

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.FieldMsgBase#createDefaultResponse()
     */
    public MsgBase createDefaultResponse() {
        EventReportResp oResp = new EventReportResp();
        oResp.setSequence(nSeqNum);
        return oResp;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        
        if (!super.equals(obj)) return false;

        if (!(obj instanceof EventReportReq)) {
            return false;
        }
        
        EventReportReq oMsgObj = (EventReportReq)obj;
        
        return oMsgObj.nEventType == nEventType &&
            oMsgObj.sTimeStamp.equals(sTimeStamp);
    }
}
