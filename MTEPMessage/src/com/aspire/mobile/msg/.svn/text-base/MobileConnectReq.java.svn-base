/**
 * @(#) MobileConnectReq.java Created on 2009-2-15
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.msg;

import com.aspire.Constants;
import com.aspire.mobile.MobileConstants;
import com.aspire.msg.MsgBase;
import com.aspire.util.ByteArray;
import com.aspire.util.GenHelper;
import com.aspire.util.ToolException;

/**
 * The class <code>MobileConnectReq</code>
 *
 * @author xuyong
 * @version 1.0
 */
public class MobileConnectReq extends MobileMsgBase {
    
    /**
     * The mobile identity name: imei
     */
    protected String sImei = "";

    /**
     * The constructor
     */
    public MobileConnectReq() {
        super(MobileConstants.MOBILE_CONNECT_REQ, "MobileConnectReq");
    }

    /**
     * Sets mobile identity name: imei
     * @param sImei mobile identity name: imei
     */
    public void setIMEI(String sImei) {
        this.sImei = sImei;
        setVerify("IMEI", true);
    }

    /**
     * Gets mobile identity name: imei
     * @return Return mobile identity name: imei
     */
    public String getIMEI() {
        return sImei;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#decode(byte[], int, int)
     */
    public int decode(byte[] byMsg, int start, int length) throws ToolException {

        int nRead = super.decode(byMsg, start, length);
        int offset = start + nRead;

        // Decode IMEI
        if (offset + MobileConstants.MAX_IMEI_LEN > length) {
            throw new ToolException(
                "MobileConnectReq decode error: no imei in the message buffer");
        }
        sImei = new String(byMsg, offset,
                MobileConstants.MAX_IMEI_LEN).trim();
        offset += MobileConstants.MAX_IMEI_LEN;

        return offset - start;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#encode(com.aspire.util.ByteArray)
     */
    public int encode(ByteArray baMsg) throws ToolException {

        int nLen = baMsg.length();

        super.encode(baMsg);

        // Encodes IMEI
        byte[] byImei = new byte[MobileConstants.MAX_IMEI_LEN];
        GenHelper.copyString(byImei, sImei, (byte)0);
        baMsg.append(byImei);

        return baMsg.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#format(java.lang.StringBuffer, java.lang.String)
     */
    public int format(StringBuffer buf, String sPrefix) throws ToolException {

        int nLen = buf.length();

        super.format(buf, sPrefix);

        // Format IMEI
        buf.append(sPrefix).
            append("IMEI = ").
            append(sImei).
            append(Constants.LINE_SEPARATOR);

        return buf.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.soap.SoapMsgBase#verify(com.aspire.msg.MsgBase)
     */
    public boolean verify(MsgBase msgVerified) throws ToolException {

        // 需重写FieldMsgBase的同名函数，此处不调用super
        //super.verify(msgVerified);
        if (this == msgVerified) return true;

        if (msgVerified == null) {
            throw new ToolException(
                 "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof MobileConnectReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'MobileConnectReq' message");
        }

        MobileConnectReq obj = (MobileConnectReq)msgVerified;

        doVerifyField("IMEI", sImei, obj, obj.sImei);
        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.soap.SoapMsgBase#verifyPresent(com.aspire.msg.MsgBase)
     */
    public boolean verifyPresent(MsgBase msgVerified) throws ToolException {

        // 需重写FieldMsgBase的同名函数，此处不调用super
        //super.verifyPresent(msgVerified);
        if (this == msgVerified) return true;

        if (msgVerified == null) {
            throw new ToolException(
                 "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof MobileConnectReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'MobileConnectReq' message");
        }

        MobileConnectReq obj = (MobileConnectReq)msgVerified;

        doVerifyPresentField("IMEI", sImei, obj, obj.sImei);

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.soap.SoapMsgBase#verifySpecified(com.aspire.msg.MsgBase)
     */
    public boolean verifySpecified(MsgBase msgVerified) throws ToolException {

        // 需重写FieldMsgBase的同名函数，此处不调用super
        //super.verifySpecified(msgVerified);
        if (this == msgVerified) return true;

        if (msgVerified == null) {
            throw new ToolException(
                 "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof MobileConnectReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'MobileConnectReq' message");
        }

        MobileConnectReq obj = (MobileConnectReq)msgVerified;

        doVerifySpecifiedField("IMEI", sImei, obj, obj.sImei);

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.FieldMsgBase#createDefaultResponse()
     */
    public MsgBase createDefaultResponse() {
        MobileConnectResp oResp = new MobileConnectResp();
        oResp.setSequence(nSeqNum);
        return oResp;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj)) return false;

        if (!(obj instanceof MobileConnectReq)) {
            return false;
        }

        MobileConnectReq oMsgObj = (MobileConnectReq)obj;

        return oMsgObj.sImei.equals(sImei);
    }
}
