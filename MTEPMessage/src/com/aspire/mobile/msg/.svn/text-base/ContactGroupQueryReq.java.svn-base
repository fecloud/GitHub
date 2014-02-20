/**
 * @(#) ContactGroupQueryReq.java Created on 2009-2-17
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
 * The class <code>ContactGroupQueryReq</code>
 *
 * @author xushengyong
 * @version 1.0
 */
public class ContactGroupQueryReq extends MobileMsgBase {
    
    public final static byte TAG_ID = 0x01;              // ID
    public final static byte TAG_GROUP_NAME = 0x11;      // GroupName
    public final static byte TAG_START_INDEX = 0x02;     // StartIndex
    public final static byte TAG_COUNT = 0x03;           // Counts
    
    /**
     * The contact ID
     */
    protected String sID = "";
    
    /**
     * The group name
     */    
    protected String sGroupName = "";
    
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
    public ContactGroupQueryReq() {
        super(MobileConstants.CONTACT_GROUP_QUERY_REQ, "ContactGroupQueryReq");
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
     * Sets the group name
     * @param sGroupName The group name
     */
    public void setGroupName(String sGroupName) {
        this.sGroupName = sGroupName;
        setVerify("GroupName", true);
    }
    
    /**
     * Gets the group name
     * @return Return the group name
     */
    public String getGroupName() {
        return sGroupName;
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
            case ContactGroupQueryReq.TAG_ID:
            {
                // Decodes the ID
                if ((offset + MobileConstants.MAX_ID_LEN) > length) {
                    throw new ToolException(
                        "ContactGroupQueryReq decode error: no ID in the record");
                }
                sID = new String(byMsg, offset,
                        MobileConstants.MAX_ID_LEN).trim();
                setPresent("ID", true);
                offset += MobileConstants.MAX_ID_LEN;         
                break;
            }
            case ContactGroupQueryReq.TAG_GROUP_NAME:
            {
                // Decodes the length field
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "ContactGroupQueryReq decode error: no GroupName in the record");
                }                      
                int nLen = ByteArray.bytesToInt(byMsg, offset);
                offset += 4;
                
                // Decodes the value field
                if ((offset + nLen) > length) {
                    throw new ToolException(
                    "ContactGroupQueryReq decode error: no GroupName in the record");                    
                }
                sGroupName = new String(byMsg, offset, nLen);
                setPresent("GroupName", true);
                offset += nLen;            
                break;
            }
            case ContactGroupQueryReq.TAG_START_INDEX:
            {
                // Decodes the value filed
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "ContactGroupQueryReq decode error: no StartIndex in the record");
                }                      
                nStartIndex = ByteArray.bytesToInt(byMsg, offset);
                setPresent("StartIndex", true);
                offset += 4;            
                break;
            }
            case ContactGroupQueryReq.TAG_COUNT:
            {
                // Decodes the value filed
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "ContactGroupQueryReq decode error: no Count in the record");
                }                      
                nCount = ByteArray.bytesToInt(byMsg, offset);
                setPresent("Count", true);
                offset += 4;            
                break;
            }             
            default:
            {
                throw new ToolException(
                        "ContactGroupQueryReq decode error: unkown tag type: " + nTag);
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
            baMsg.append(ContactGroupQueryReq.TAG_ID);
            byte[] byID = new byte[MobileConstants.MAX_ID_LEN];
            GenHelper.copyString(byID, sID);
            baMsg.append(byID);
        }

        if (isPresent("GroupName"))
        {
            // Encodes the group name
            baMsg.append(ContactGroupQueryReq.TAG_GROUP_NAME);
            baMsg.append(sGroupName.getBytes().length);
            baMsg.append(sGroupName);
        }

        if (isPresent("StartIndex"))
        {        
            // Encodes the start index
            baMsg.append(ContactGroupQueryReq.TAG_START_INDEX);
            baMsg.append(nStartIndex);
        }

        if (isPresent("Count"))
        {        
            // Encodes the count
            baMsg.append(ContactGroupQueryReq.TAG_COUNT);
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

        if (isPresent("GroupName"))
        {
            // Formats the contact name
            buf.append(sPrefix).
                append("GroupName = ").
                append(sGroupName).
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

        if (!(msgVerified instanceof ContactGroupQueryReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'ContactGroupQueryReq' message");
        }
        
        ContactGroupQueryReq obj = (ContactGroupQueryReq)msgVerified;
        
        // All fields below is optional, so call doVerifyPresentField
        doVerifyPresentField("ID", sID, obj, obj.sID);
        doVerifyPresentField("GroupName", sGroupName, obj, obj.sGroupName);     
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
        
        if (!(msgVerified instanceof ContactGroupQueryReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'ContactGroupQueryReq' message");
        }

        ContactGroupQueryReq obj = (ContactGroupQueryReq)msgVerified;

        doVerifyPresentField("ID", sID, obj, obj.sID);
        doVerifyPresentField("GroupName", sGroupName, obj, obj.sGroupName);     
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

        if (!(msgVerified instanceof ContactGroupQueryReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'ContactGroupQueryReq' message");
        }
        
        ContactGroupQueryReq obj = (ContactGroupQueryReq)msgVerified;
        
        doVerifySpecifiedField("ID", sID, obj, obj.sID);
        doVerifySpecifiedField("GroupName", sGroupName, obj, obj.sGroupName);     
        doVerifySpecifiedField("StartIndex", nStartIndex, obj, obj.nStartIndex);
        doVerifySpecifiedField("Count", nCount, obj, obj.nCount);

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.FieldMsgBase#createDefaultResponse()
     */
    public MsgBase createDefaultResponse() {
        
        ContactGroupQueryResp oResp = new ContactGroupQueryResp();
        
        oResp.setSequence(nSeqNum);
        
        return oResp;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj)) return false;

        if (!(obj instanceof ContactGroupQueryReq)) {
            return false;
        }

        ContactGroupQueryReq oMsgObj = (ContactGroupQueryReq)obj;
        
        if (isPresent("ID") && oMsgObj.isPresent("ID"))
        {
            if (!oMsgObj.sID.equals(sID))
            {
                return false;
            }
        } else {
            return false;
        }
        
        if (isPresent("GroupName") && oMsgObj.isPresent("GroupName"))
        {
            if (!oMsgObj.sGroupName.equals(sGroupName))
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
