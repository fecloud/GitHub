/**
 * @(#) ContactsQueryResp.java Created on 2009-2-16
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.msg;

import java.util.ArrayList;

import com.aspire.Constants;
import com.aspire.mobile.MobileConstants;
import com.aspire.mobile.element.MobileContactInfo;
import com.aspire.msg.MsgBase;
import com.aspire.util.ByteArray;
import com.aspire.util.GenApi;
import com.aspire.util.ToolException;

/**
 * The class <code>ContactsQueryResp</code>
 *
 * @author xushengyong
 * @version 1.0
 */
public class ContactsQueryResp extends MobileRespBase {
    
    /**
     * The record count
     */
    protected int nRecordCount = 0;
    
    /**
     * The file information
     */
    protected ArrayList<MobileContactInfo> aContactInfos =
        new ArrayList<MobileContactInfo>();

    
    /**
     * The constructor
     */
    public ContactsQueryResp() {
        super(MobileConstants.CONTACTS_QUERY_RESP, "ContactsQueryResp");
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
     * Sets the contact info
     * @param aContactInfos The contact info
     */
    public void setContactInfo(ArrayList<MobileContactInfo> aContactInfos) {
        this.aContactInfos = aContactInfos;
        setVerify("ContactInfo", true);
    }

    /**
     * Gets the contact info
     * @return Returns the contact info
     */
    public ArrayList<MobileContactInfo> getContactInfo() {
        return aContactInfos;
    }

    /**
     * Adds the contact info
     * @param oContactInfo The contact info object to be added
     */
    public void addContactInfo(MobileContactInfo oContactInfo) {
        if (aContactInfos.contains(oContactInfo)) return;
        aContactInfos.add(oContactInfo);
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
                    "ContactsQueryResp decode error: no RecordCount in the record");
        }
        nRecordCount = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;

        // Decodes the contact info
        setPresent("ContactInfo", nRecordCount > 0);
        int rLen = 0; // Length of each record
        for (int i = 0; i < nRecordCount; i++) {
            if (offset >= length) {
                break;
            }
            // Calculate length of this record
            rLen = ByteArray.bytesToInt(byMsg, offset);
            offset += 4;
            
            MobileContactInfo oInfo = new MobileContactInfo();
            offset += oInfo.decode(byMsg, offset, offset + rLen);
            aContactInfos.add(oInfo);
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
        baMsg.append(aContactInfos.size());

        // Encodes the contact info
        ByteArray rBuf = new ByteArray(); // Byte code for each record
        for (int i = 0; i < aContactInfos.size(); i++) {
            rBuf.clear();
            // Append length of this record
            baMsg.append(aContactInfos.get(i).encode(rBuf));
            // Append record's code
            baMsg.append(rBuf);
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

        // Formats the contact info
        String sInfoPrefix = sPrefix + "    ";
        for (int i = 0; i < aContactInfos.size(); i++) {
            buf.append(sPrefix).
                append("ContactInfo[No ").
                append(i).
                append("]").
                append(Constants.LINE_SEPARATOR).
                append(sPrefix).
                append("{").
                append(Constants.LINE_SEPARATOR);
            aContactInfos.get(i).format(buf, sInfoPrefix);
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

        if (!(msgVerified instanceof ContactsQueryResp)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'ContactsQueryResp' message");
        }

        ContactsQueryResp obj = (ContactsQueryResp)msgVerified;

        doVerifyField("RecordCount", nRecordCount, obj, obj.nRecordCount);
        doVerifyField("ContactInfo", aContactInfos, obj, obj.aContactInfos, false);

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

        if (!(msgVerified instanceof ContactsQueryResp)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'ContactsQueryResp' message");
        }

        ContactsQueryResp obj = (ContactsQueryResp)msgVerified;
        
        doVerifyPresentField("RecordCount", nRecordCount, obj, obj.nRecordCount);
        doVerifyPresentField("ContactInfo", aContactInfos, obj, obj.aContactInfos, false);

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

        if (!(msgVerified instanceof ContactsQueryResp)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'ContactsQueryResp' message");
        }

        ContactsQueryResp obj = (ContactsQueryResp)msgVerified;
        
        doVerifySpecifiedField("RecordCount", nRecordCount, obj, obj.nRecordCount);
        doVerifySpecifiedField("ContactInfo", aContactInfos, obj, obj.aContactInfos, false);

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

        ContactsQueryResp oMsgObj = (ContactsQueryResp)obj;

        return oMsgObj.nRecordCount == nRecordCount &&
            oMsgObj.aContactInfos.equals(aContactInfos);
    }
    
    /**
     * This method is used to clone a ContactsQueryResp object
     */
    public Object clone() throws CloneNotSupportedException {
        
        ContactsQueryResp obj = (ContactsQueryResp)super.clone();
        
        obj.aContactInfos = new ArrayList<MobileContactInfo>();
        
        for (int i = 0; i < aContactInfos.size(); i++) {
            obj.aContactInfos.add((MobileContactInfo)aContactInfos.get(i).clone());
        }
        
        return obj;
    }
    
}
