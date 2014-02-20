/**
 * @(#) ContactGroupQueryResp.java Created on 2009-2-17
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.msg;

import java.util.ArrayList;

import com.aspire.Constants;
import com.aspire.mobile.MobileConstants;
import com.aspire.mobile.element.MobileContactGroupInfo;
import com.aspire.msg.MsgBase;
import com.aspire.util.ByteArray;
import com.aspire.util.GenApi;
import com.aspire.util.ToolException;

/**
 * The class <code>ContactGroupQueryResp</code>
 *
 * @author xushengyong
 * @version 1.0
 */
public class ContactGroupQueryResp extends MobileRespBase {
    
    /**
     * The record count
     */
    protected int nRecordCount = 0;
    
    /**
     * The file information
     */
    protected ArrayList<MobileContactGroupInfo> aContactGroupInfos =
        new ArrayList<MobileContactGroupInfo>();

    /**
     * The constructor
     */
    public ContactGroupQueryResp() {
        super(MobileConstants.CONTACT_GROUP_QUERY_RESP, "ContactGroupQueryResp");
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
     * Sets the contact group info
     * @param aContactGroupInfos The contact group info
     */
    public void setContactGroupInfo(
            ArrayList<MobileContactGroupInfo> aContactGroupInfos) {
        this.aContactGroupInfos = aContactGroupInfos;
        setVerify("ContactGroupInfo", true);
    }

    /**
     * Gets the contact group info
     * @return Returns the contact group info
     */
    public ArrayList<MobileContactGroupInfo> getContactGroupInfo() {
        return aContactGroupInfos;
    }

    /**
     * Adds the contact group info
     * @param oContactGroupInfo The contact group info object to be added
     */
    public void addContactGroupInfo(MobileContactGroupInfo oContactGroupInfo) {
        if (aContactGroupInfos.contains(oContactGroupInfo)) return;
        aContactGroupInfos.add(oContactGroupInfo);
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
                    "ContactGroupQueryResp decode error: no RecordCount in the record");
        }
        nRecordCount = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;

        // Decodes the contact group info
        setPresent("ContactGroupInfo", nRecordCount > 0);
        for (int i = 0; i < nRecordCount; i++) {
            if (offset >= length) {
                break;
            }
            MobileContactGroupInfo oInfo = new MobileContactGroupInfo();
            offset += oInfo.decode(byMsg, offset, length);
            aContactGroupInfos.add(oInfo);
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
        baMsg.append(aContactGroupInfos.size());

        // Encodes the contact group info
        for (int i = 0; i < aContactGroupInfos.size(); i++) {
            aContactGroupInfos.get(i).encode(baMsg);
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

        // Formats the contact group info
        String sInfoPrefix = sPrefix + "    ";
        for (int i = 0; i < aContactGroupInfos.size(); i++) {
            buf.append(sPrefix).
                append("ContactGroupInfo[No ").
                append(i).
                append("]").
                append(Constants.LINE_SEPARATOR).
                append(sPrefix).
                append("{").
                append(Constants.LINE_SEPARATOR);
            aContactGroupInfos.get(i).format(buf, sInfoPrefix);
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

        if (!(msgVerified instanceof ContactGroupQueryResp)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'ContactGroupQueryResp' message");
        }

        ContactGroupQueryResp obj = (ContactGroupQueryResp)msgVerified;

        doVerifyField("RecordCount", nRecordCount, obj, obj.nRecordCount);
        doVerifyField("ContactGroupInfo", aContactGroupInfos, obj, obj.aContactGroupInfos, false);

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

        if (!(msgVerified instanceof ContactGroupQueryResp)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'ContactGroupQueryResp' message");
        }

        ContactGroupQueryResp obj = (ContactGroupQueryResp)msgVerified;
        
        doVerifyPresentField("RecordCount", nRecordCount, obj, obj.nRecordCount);
        doVerifyPresentField("ContactGroupInfo", aContactGroupInfos, obj, obj.aContactGroupInfos, false);

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

        if (!(msgVerified instanceof ContactGroupQueryResp)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'ContactGroupQueryResp' message");
        }

        ContactGroupQueryResp obj = (ContactGroupQueryResp)msgVerified;
        
        doVerifySpecifiedField("RecordCount", nRecordCount, obj, obj.nRecordCount);
        doVerifySpecifiedField("ContactGroupInfo", aContactGroupInfos, obj, obj.aContactGroupInfos, false);

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileRespBase#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj)) return false;

        if (!(obj instanceof ContactsQueryResp)) {
            return false;
        }

        ContactGroupQueryResp oMsgObj = (ContactGroupQueryResp)obj;

        return oMsgObj.nRecordCount == nRecordCount &&
            oMsgObj.aContactGroupInfos.equals(aContactGroupInfos);
    }
    
    /**
     * This method is used to clone a ContactsQueryResp object
     */
    public Object clone() throws CloneNotSupportedException {
        
        ContactGroupQueryResp obj = (ContactGroupQueryResp)super.clone();
        
        obj.aContactGroupInfos = new ArrayList<MobileContactGroupInfo>();
        
        for (int i = 0; i < aContactGroupInfos.size(); i++) {
            obj.aContactGroupInfos.add((MobileContactGroupInfo)aContactGroupInfos.get(i).clone());
        }
        
        return obj;
    }

}
