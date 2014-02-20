/**
 * @(#) CallRecordQueryResp.java Created on 2009-2-18
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.msg;

import java.util.ArrayList;

import com.aspire.Constants;
import com.aspire.mobile.MobileConstants;
import com.aspire.mobile.element.MobileCallRecordInfo;
import com.aspire.msg.MsgBase;
import com.aspire.util.ByteArray;
import com.aspire.util.GenApi;
import com.aspire.util.ToolException;

/**
 * The class <code>CallRecordQueryResp</code>
 *
 * @author xushengyong
 * @version 1.0
 */
public class CallRecordQueryResp extends MobileRespBase {

    /**
     * The record count
     */
    protected int nRecordCount = 0;
    
    /**
     * The call record information
     */
    protected ArrayList<MobileCallRecordInfo> aCallRecordInfos =
        new ArrayList<MobileCallRecordInfo>();
    
    /**
     * The constructor
     */
    public CallRecordQueryResp() {
        super(MobileConstants.CALL_RECORD_QUERY_RESP, "CallRecordQueryResp");
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
     * Sets the call record info
     * @param aCallRecordInfos The call record info
     */
    public void setCallRecordInfo(ArrayList<MobileCallRecordInfo> aCallRecordInfos) {
        this.aCallRecordInfos = aCallRecordInfos;
        setVerify("CallRecordInfo", true);
    }

    /**
     * Gets the call record info
     * @return Returns the call record info
     */
    public ArrayList<MobileCallRecordInfo> getCallRecordInfo() {
        return aCallRecordInfos;
    }

    /**
     * Adds the call record info
     * @param oCallRecordInfo The call record info object to be added
     */
    public void addCallRecordInfo(MobileCallRecordInfo oCallRecordInfo) {
        if (aCallRecordInfos.contains(oCallRecordInfo)) return;
        aCallRecordInfos.add(oCallRecordInfo);
    }
    
    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileRespBase#decode(byte[], int, int)
     */
    public int decode(byte[] byMsg, int start, int length) throws ToolException {
        int nRead = super.decode(byMsg, start, length);
        int offset = start + nRead;

        // Decodes the record count
        if ((offset + 4) > length) {
            throw new ToolException(
            "CallRecordQueryResp decode error: no RecordCount in the record");
        } 
        nRecordCount = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;
        
        // Decodes the call record info
        setPresent("CallRecordInfo", nRecordCount > 0);
        for (int i = 0; i < nRecordCount; i++) {
            if (offset >= length) {
                break;
            }
            MobileCallRecordInfo oInfo = new MobileCallRecordInfo();
            offset += oInfo.decode(byMsg, offset, length);
            aCallRecordInfos.add(oInfo);
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
        baMsg.append(aCallRecordInfos.size());

        // Encodes the call record info
        for (int i = 0; i < aCallRecordInfos.size(); i++) {
            aCallRecordInfos.get(i).encode(baMsg);
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

        // Formats the call record info
        String sInfoPrefix = sPrefix + "    ";
        for (int i = 0; i < aCallRecordInfos.size(); i++) {
            buf.append(sPrefix).
                append("CallRecordInfo[No ").
                append(i).
                append("]").
                append(Constants.LINE_SEPARATOR).
                append(sPrefix).
                append("{").
                append(Constants.LINE_SEPARATOR);
            aCallRecordInfos.get(i).format(buf, sInfoPrefix);
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

        if (!(msgVerified instanceof CallRecordQueryResp)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'CallRecordQueryResp' message");
        }

        CallRecordQueryResp obj = (CallRecordQueryResp)msgVerified;

        doVerifyField("RecordCount", nRecordCount, obj, obj.nRecordCount);
        doVerifyField("CallRecordInfo", aCallRecordInfos, obj, obj.aCallRecordInfos, false);

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

        if (!(msgVerified instanceof CallRecordQueryResp)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'CallRecordQueryResp' message");
        }

        CallRecordQueryResp obj = (CallRecordQueryResp)msgVerified;
        
        doVerifyPresentField("RecordCount", nRecordCount, obj, obj.nRecordCount);
        doVerifyPresentField("CallRecordInfo", aCallRecordInfos, obj, obj.aCallRecordInfos, false);

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

        if (!(msgVerified instanceof CallRecordQueryResp)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'CallRecordQueryResp' message");
        }

        CallRecordQueryResp obj = (CallRecordQueryResp)msgVerified;
        
        doVerifySpecifiedField("RecordCount", nRecordCount, obj, obj.nRecordCount);
        doVerifySpecifiedField("CallRecordInfo", aCallRecordInfos, obj, obj.aCallRecordInfos, false);

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileRespBase#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj)) return false;

        if (!(obj instanceof CallRecordQueryResp)) {
            return false;
        }

        CallRecordQueryResp oMsgObj = (CallRecordQueryResp)obj;

        return oMsgObj.nRecordCount == nRecordCount &&
            oMsgObj.aCallRecordInfos.equals(aCallRecordInfos);
    }
    
    /**
     * This method is used to clone a ContactsQueryResp object
     */
    public Object clone() throws CloneNotSupportedException {
        
        CallRecordQueryResp obj = (CallRecordQueryResp)super.clone();
        
        obj.aCallRecordInfos = new ArrayList<MobileCallRecordInfo>();
        
        for (int i = 0; i < aCallRecordInfos.size(); i++) {
            obj.aCallRecordInfos.add((MobileCallRecordInfo)aCallRecordInfos.get(i).clone());
        }
        
        return obj;
    }
    
}
