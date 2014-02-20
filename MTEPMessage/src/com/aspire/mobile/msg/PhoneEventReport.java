/**
 * @(#) PhoneEventReport.java Created on 2008-12-30
 * 
 * Copyright (c) 2008 Aspire. All Rights Reserved
 */
package com.aspire.mobile.msg;

import com.aspire.Constants;
import com.aspire.mobile.MobileConstants;
import com.aspire.util.ByteArray;
import com.aspire.util.GenApi;
import com.aspire.util.GenHelper;
import com.aspire.util.ToolException;
import com.aspire.msg.MsgBase;

/**
 * The class <code>PhoneEventReport</code> is used for PhoneEventReport
 *
 * @author xuyong
 * @version 1.0
 */
public class PhoneEventReport extends EventReportReq {
    /**
     * 电话事件类型，0x01：电话拨入事件；0x02：电话拨出事件；0x03：电话拨入未接事件；
     */
    protected byte nType = 1;

    /**
     * 对方电话号码
     */
    protected String phoneNumber = "";
    
    /**
     * 通话结束时间
     */
    protected String sEndTime = "";

    /**
     * Constructs a new PhoneEventReport with default values
     */
    public PhoneEventReport() {
        super("PhoneEventReport", MobileConstants.EVENT_TYPE_PHONE);
    }

    /**
     * 设置短信事件类型，0x01：接收短信事件；0x02：发送短信事件
     * @param sType 短信事件类型，0x01：接收短信事件；0x02：发送短信事件
     * @throws ToolException 
     */
    public void setType(String sType) throws ToolException {
        setType(GenApi.strToByte(sType, "Type"));
    }

    /**
     * 设置短信事件类型，0x01：接收短信事件；0x02：发送短信事件
     * @param type 短信事件类型，0x01：接收短信事件；0x02：发送短信事件
     */
    public void setType(byte nType) {
        this.nType = nType;
        setVerify("Type", true);
    }

    /**
     * 取短信事件类型，0x01：接收短信事件；0x02：发送短信事件
     * @return 返回短信事件类型，0x01：接收短信事件；0x02：发送短信事件
     */
    public String getType() {
        return Byte.toString(nType);
    }

    /**
     * 取短信事件类型，0x01：接收短信事件；0x02：发送短信事件
     * @return 返回短信事件类型，0x01：接收短信事件；0x02：发送短信事件
     */
    public byte getTypeValue() {
        return nType;
    }

    /**
     * 设置对方电话号码
     * @param phoneNumber 对方电话号码
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        setVerify("PhoneNumber", true);
    }

    /**
     * 取对方电话号码
     * @return 返回对方电话号码
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets Phone End time
     * @param sEndTime Phone End time
     */
    public void setEndTime(String sEndTime) {
        this.sEndTime = sEndTime;
        setVerify("EndTime");
    }

    /**
     * Gets Phone End time in default time format 'yyyyMMddHHmmss'
     * @return Returns Phone End time in default time format
     */
    public String getEndTime() {
        return sEndTime;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#decode(byte[], int, int)
     */
    public int decode(byte[] byMsg, int start, int length) throws ToolException {

        int nRead = super.decode(byMsg, start, length);
        int offset = start + nRead;

        // Decodes type
        if (offset >= length) {
            throw new ToolException(
                "PhoneEventReport decode error: no type in the message buffer");
        }

        nType = byMsg[offset];
        offset++;

        // Decode phone number
        if (offset + MobileConstants.MAX_PHONE_NUM_LEN > length) {
            throw new ToolException(
                "PhoneEventReport decode error: no phone number in the message buffer");
        }
        phoneNumber = new String(byMsg, offset,
                MobileConstants.MAX_PHONE_NUM_LEN).trim();
        offset += MobileConstants.MAX_PHONE_NUM_LEN;
        
        // Decode end time
        if (offset + MobileConstants.MAX_TIME_LEN > length) {
            throw new ToolException(
                "PhoneEventReport decode error: no end time in the message buffer");
        }
        sEndTime = new String(byMsg, offset,
                MobileConstants.MAX_TIME_LEN).trim();
        offset += MobileConstants.MAX_TIME_LEN;

        return offset - start;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#encode(com.aspire.util.ByteArray)
     */
    public int encode(ByteArray baMsg) throws ToolException {

        int nLen = baMsg.length();

        super.encode(baMsg);

        // Encodes sms type
        baMsg.append(nType);

        // Encodes phone number
        byte[] byPhoneNum = new byte[MobileConstants.MAX_PHONE_NUM_LEN];
        GenHelper.copyString(byPhoneNum, phoneNumber, (byte)0);
        baMsg.append(byPhoneNum);
        
        // Encodes end time
        byte[] byTime = new byte[MobileConstants.MAX_TIME_LEN];
        GenHelper.copyString(byTime, sEndTime);
        baMsg.append(byTime);

        return baMsg.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#format(java.lang.StringBuffer, java.lang.String)
     */
    public int format(StringBuffer buf, String sPrefix) throws ToolException {

        int nLen = buf.length();

        super.format(buf, sPrefix);

        // Format sms type
        buf.append(sPrefix).
            append("Type = ").
            append(nType).
            append(Constants.LINE_SEPARATOR);

        // Format Phone number
        buf.append(sPrefix).
            append("PhoneNumber = ").
            append(phoneNumber).
            append(Constants.LINE_SEPARATOR);

        // Format Phone end time
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        buf.append(sPrefix).
//            append("EndTime = ").
//            append(sdf.format(new Date(nEndTime))).
//            append(Constants.LINE_SEPARATOR);

        return buf.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.soap.SoapMsgBase#verify(com.aspire.msg.MsgBase)
     */
    public boolean verify(MsgBase msgVerified) throws ToolException {

        super.verify(msgVerified);

        if (msgVerified == null) {
            throw new ToolException(
                 "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof PhoneEventReport)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'PhoneEventReport' message");
        }

        PhoneEventReport obj = (PhoneEventReport)msgVerified;

        doVerifyField("Type", nType, obj, obj.nType);
        doVerifyField("PhoneNumber", phoneNumber, obj, obj.phoneNumber);
        doVerifyField("EndTime", sEndTime, obj, obj.sEndTime);
        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.soap.SoapMsgBase#verifyPresent(com.aspire.msg.MsgBase)
     */
    public boolean verifyPresent(MsgBase msgVerified) throws ToolException {

        super.verifyPresent(msgVerified);

        if (msgVerified == null) {
            throw new ToolException(
                 "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof PhoneEventReport)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'PhoneEventReport' message");
        }

        PhoneEventReport obj = (PhoneEventReport)msgVerified;

        doVerifyPresentField("Type", nType, obj, obj.nType);
        doVerifyPresentField("PhoneNumber", phoneNumber, obj, obj.phoneNumber);
        doVerifyPresentField("EndTime", sEndTime, obj, obj.sEndTime);
        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.soap.SoapMsgBase#verifySpecified(com.aspire.msg.MsgBase)
     */
    public boolean verifySpecified(MsgBase msgVerified) throws ToolException {

        super.verifySpecified(msgVerified);

        if (msgVerified == null) {
            throw new ToolException(
                 "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof PhoneEventReport)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'PhoneEventReport' message");
        }

        PhoneEventReport obj = (PhoneEventReport)msgVerified;

        doVerifySpecifiedField("Type", nType, obj, obj.nType);
        doVerifySpecifiedField("PhoneNumber", phoneNumber, obj, obj.phoneNumber);
        doVerifySpecifiedField("EndTime", sEndTime, obj, obj.sEndTime);
        return true;
    }

//    /*
//     * (non-Javadoc)
//     * @see com.aspire.msg.MsgField#setAllPresent(boolean)
//     */
//    public void setAllPresent(boolean bPresent) {
//        super.setAllPresent(bPresent);
//        //setPresent("TimeStamp", bPresent);
//        setPresent("Type", bPresent);
//        setPresent("PhoneNumber", bPresent);
//        setPresent("EndTime", bPresent);
//    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj)) return false;

        if (!(obj instanceof PhoneEventReport)) {
            return false;
        }

        PhoneEventReport oMsgObj = (PhoneEventReport)obj;

        return oMsgObj.nType == nType &&
            oMsgObj.phoneNumber.equals(phoneNumber) &&
            oMsgObj.sEndTime.equals(sEndTime);
    }
}
