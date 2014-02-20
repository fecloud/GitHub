/**
 * @(#) MessageBoxQueryResp.java Created on 2009-2-18
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.msg;

import java.util.ArrayList;

import com.aspire.Constants;
import com.aspire.mobile.MobileConstants;
import com.aspire.mobile.element.MobileMessageInfo;
import com.aspire.msg.MsgBase;
import com.aspire.util.ByteArray;
import com.aspire.util.GenApi;
import com.aspire.util.ToolException;

/**
 * The class <code>MessageBoxQueryResp</code>
 *
 * @author xushengyong
 * @version 1.0
 */
public class MessageBoxQueryResp extends MobileRespBase {
    
    /**
     * The record count
     */
    protected int nRecordCount = 0;
    
    /**
     * The flag of 分包
     */
    protected byte nFlag = MobileConstants.TE_SINGLE_MSG;
    
    /**
     * The file information
     */
    protected ArrayList<MobileMessageInfo> aMessageInfos =
        new ArrayList<MobileMessageInfo>();

    /**
     * The constructor
     */
    public MessageBoxQueryResp() {
        super(MobileConstants.MESSAGE_BOX_QUERY_RESP, "MessageBoxQueryResp");
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
     * Gets the flag 
     * @return
     */
    public byte getnFlag() {
        return nFlag;
    }

    /**
     * set the flag 
     * @param nFlag
     */
    public void setnFlag(byte nFlag) {
        this.nFlag = nFlag;
        setVerify("Flag", true);
    }

    /**
     * Sets the message info
     * @param aMessageInfos The the message info
     */
    public void setMessageInfo(ArrayList<MobileMessageInfo> aMessageInfos) {
        this.aMessageInfos = aMessageInfos;
        setVerify("MessageInfo", true);
    }

    /**
     * Gets the message info
     * @return Returns the message info
     */
    public ArrayList<MobileMessageInfo> getMessageInfo() {
        return aMessageInfos;
    }

    /**
     * Adds the message info
     * @param oMessageInfo The the message info object to be added
     */
    public void addMessageInfo(MobileMessageInfo oMessageInfo) {
        if (aMessageInfos.contains(oMessageInfo)) return;
        aMessageInfos.add(oMessageInfo);
    }
    
    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileRespBase#decode(byte[], int, int)
     */
    public int decode(byte[] byMsg, int start, int length) throws ToolException {
        int nRead = super.decode(byMsg, start, length);
        int offset = start + nRead;
        
        //decodes the flag
        nFlag = byMsg[offset];
        offset ++;

        // Decodes the record count
        nRecordCount = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;

        // Decodes the message info
        setPresent("MessageInfo", nRecordCount > 0);
        int rLen = 0; // Length of each record
        for (int i = 0; i < nRecordCount; i++) {
            if (offset >= length) {
                break;
            }
            // Calculate length of this record
            rLen = ByteArray.bytesToInt(byMsg, offset);
            offset += 4;
            
            MobileMessageInfo oInfo = new MobileMessageInfo();
            offset += oInfo.decode(byMsg, offset, offset + rLen);
            aMessageInfos.add(oInfo);
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
        
        // Encodes the flag
        baMsg.append(nFlag);

        // Encodes the record count
        baMsg.append(aMessageInfos.size());

        // Encodes the message info
        ByteArray rBuf = new ByteArray(); // Byte code for each record
        for (int i = 0; i < aMessageInfos.size(); i++) {
            rBuf.clear();
            // Append length of this record
            baMsg.append(aMessageInfos.get(i).encode(rBuf));
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
        
        // Formats the flag
        buf.append(sPrefix).
            append("Flag = ").
            append(nFlag).
            append(Constants.LINE_SEPARATOR);

        // Formats the record count
        buf.append(sPrefix).
            append("RecordCount = ").
            append(nRecordCount).
            append(Constants.LINE_SEPARATOR);

        // Formats the message info
        String sInfoPrefix = sPrefix + "    ";
        for (int i = 0; i < aMessageInfos.size(); i++) {
            buf.append(sPrefix).
                append("MessageInfo[No ").
                append(i).
                append("]").
                append(Constants.LINE_SEPARATOR).
                append(sPrefix).
                append("{").
                append(Constants.LINE_SEPARATOR);
            aMessageInfos.get(i).format(buf, sInfoPrefix);
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

        if (!(msgVerified instanceof MessageBoxQueryResp)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'MessageBoxQueryResp' message");
        }

        MessageBoxQueryResp obj = (MessageBoxQueryResp)msgVerified;

        doVerifyField("Flag", nFlag, obj, obj.nFlag);
        doVerifyField("RecordCount", nRecordCount, obj, obj.nRecordCount);
        doVerifyField("MessageInfo", aMessageInfos, obj, obj.aMessageInfos, false);

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

        if (!(msgVerified instanceof MessageBoxQueryResp)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'MessageBoxQueryResp' message");
        }

        MessageBoxQueryResp obj = (MessageBoxQueryResp)msgVerified;
        
        doVerifyPresentField("Flag", nFlag, obj, obj.nFlag);
        doVerifyPresentField("RecordCount", nRecordCount, obj, obj.nRecordCount);
        doVerifyPresentField("MessageInfo", aMessageInfos, obj, obj.aMessageInfos, false);

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

        if (!(msgVerified instanceof MessageBoxQueryResp)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'MessageBoxQueryResp' message");
        }

        MessageBoxQueryResp obj = (MessageBoxQueryResp)msgVerified;
        
        doVerifySpecifiedField("Flag", nFlag, obj, obj.nFlag);
        doVerifySpecifiedField("RecordCount", nRecordCount, obj, obj.nRecordCount);
        doVerifySpecifiedField("MessageInfo", aMessageInfos, obj, obj.aMessageInfos, false);

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileRespBase#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj)) return false;

        if (!(obj instanceof MessageBoxQueryResp)) {
            return false;
        }

        MessageBoxQueryResp oMsgObj = (MessageBoxQueryResp)obj;

        return oMsgObj.nFlag == nFlag && oMsgObj.nRecordCount == nRecordCount &&
            oMsgObj.aMessageInfos.equals(aMessageInfos);
    }
    
    /**
     * This method is used to clone a MessageBoxQueryResp object
     */
    public Object clone() throws CloneNotSupportedException {
        
        MessageBoxQueryResp obj = (MessageBoxQueryResp)super.clone();
        
        obj.aMessageInfos = new ArrayList<MobileMessageInfo>();
        
        for (int i = 0; i < aMessageInfos.size(); i++) {
            obj.aMessageInfos.add((MobileMessageInfo)aMessageInfos.get(i).clone());
        }
        
        return obj;
    }

}
