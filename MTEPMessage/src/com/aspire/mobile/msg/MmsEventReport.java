/**
 * @(#) MmsEventReport.java Created on 2008-12-30
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
 * The class <code>MmsEventReport</code> is used for MmsEventReport
 *
 * @author xuyong
 * @version 1.0
 */
public class MmsEventReport extends EventReportReq {

    /**
     * 彩信事件类型，0x01：接收彩信事件；0x02：发送彩信事件
     */
    protected byte nType = 1;

    /**
     * 对方电话号码
     */
    protected String phoneNumber = "";

    /**
     * 彩信内容
     */
    protected String message = "";

    /**
     * Constructs a new MmsEventReport with default values
     */
    public MmsEventReport() {
        super("MmsEventReport", MobileConstants.EVENT_TYPE_MMS);
    }

    /**
     * 设置彩信事件类型，0x01：接收彩信事件；0x02：发送彩信事件
     * @param sType 彩信事件类型，0x01：接收彩信事件；0x02：发送彩信事件
     * @throws ToolException 
     */
    public void setType(String sType) throws ToolException {
        setType(GenApi.strToByte(sType, "Type"));
    }

    /**
     * 设置彩信事件类型，0x01：接收彩信事件；0x02：发送彩信事件
     * @param type 彩信事件类型，0x01：接收彩信事件；0x02：发送彩信事件
     */
    public void setType(byte nType) {
        this.nType = nType;
        setVerify("Type", true);
    }

    /**
     * 取彩信事件类型，0x01：接收彩信事件；0x02：发送彩信事件
     * @return 返回彩信事件类型，0x01：接收彩信事件；0x02：发送彩信事件
     */
    public String getType() {
        return Byte.toString(nType);
    }

    /**
     * 取彩信事件类型，0x01：接收彩信事件；0x02：发送彩信事件
     * @return 返回彩信事件类型，0x01：接收彩信事件；0x02：发送彩信事件
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
     * 设置彩信内容
     * @param message 彩信内容
     */
    public void setMessage(String message) {
        this.message = message;
        setVerify("Message", true);
    }

    /**
     * 取彩信内容
     * @return 返回彩信内容
     */
    public String getMessage() {
        return message;
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
                "MmsEventReport decode error: no type in the message buffer");
        }

        nType = byMsg[offset];
        offset++;

        // Decode phone number
        if (offset + MobileConstants.MAX_PHONE_NUM_LEN > length) {
            throw new ToolException(
                "MmsEventReport decode error: no phone number in the message buffer");
        }
        phoneNumber = new String(byMsg, offset,
                MobileConstants.MAX_PHONE_NUM_LEN).trim();
        offset += MobileConstants.MAX_PHONE_NUM_LEN;

        // Decode message
        int nMsgLen = length - offset;
        message = new String(byMsg, offset, nMsgLen);
        offset += nMsgLen;

        return offset - start;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#encode(com.aspire.util.ByteArray)
     */
    public int encode(ByteArray baMsg) throws ToolException {

        int nLen = baMsg.length();

        super.encode(baMsg);

        // Encodes mms type
        baMsg.append(nType);

        // Encodes phone number
        byte[] byPhoneNum = new byte[MobileConstants.MAX_PHONE_NUM_LEN];
        GenHelper.copyString(byPhoneNum, phoneNumber, (byte)0);
        baMsg.append(byPhoneNum);

        // Encodes message
        baMsg.append(message);

        return baMsg.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#format(java.lang.StringBuffer, java.lang.String)
     */
    public int format(StringBuffer buf, String sPrefix) throws ToolException {

        int nLen = buf.length();

        super.format(buf, sPrefix);

        // Format mms type
        buf.append(sPrefix).
            append("Type = ").
            append(nType).
            append(Constants.LINE_SEPARATOR);

        // Format Phone number
        buf.append(sPrefix).
            append("PhoneNumber = ").
            append(phoneNumber).
            append(Constants.LINE_SEPARATOR);

        // Format message
//        buf.append(sPrefix).
//            append("Message = ").
//            append(message).
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

        if (!(msgVerified instanceof MmsEventReport)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'MmsEventReport' message");
        }

        MmsEventReport obj = (MmsEventReport)msgVerified;

        doVerifyField("Type", nType, obj, obj.nType);
        doVerifyField("PhoneNumber", phoneNumber, obj, obj.phoneNumber);
        doVerifyField("Message", message, obj, obj.message);
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

        if (!(msgVerified instanceof MmsEventReport)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'MmsEventReport' message");
        }

        MmsEventReport obj = (MmsEventReport)msgVerified;

        doVerifyPresentField("Type", nType, obj, obj.nType);
        doVerifyPresentField("PhoneNumber", phoneNumber, obj, obj.phoneNumber);
        doVerifyPresentField("Message", message, obj, obj.message);
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

        if (!(msgVerified instanceof MmsEventReport)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'MmsEventReport' message");
        }

        MmsEventReport obj = (MmsEventReport)msgVerified;

        doVerifySpecifiedField("Type", nType, obj, obj.nType);
        doVerifySpecifiedField("PhoneNumber", phoneNumber, obj, obj.phoneNumber);
        doVerifySpecifiedField("Message", message, obj, obj.message);
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
//        setPresent("Message", bPresent);
//    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj)) return false;

        if (!(obj instanceof MmsEventReport)) {
            return false;
        }

        MmsEventReport oMsgObj = (MmsEventReport)obj;

        return oMsgObj.nType == nType &&
            oMsgObj.phoneNumber.equals(phoneNumber) &&
            oMsgObj.message.equals(message);
    }
}
