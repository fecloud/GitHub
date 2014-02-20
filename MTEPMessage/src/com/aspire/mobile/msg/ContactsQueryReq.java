/**
 * @(#) ContactsQueryReq.java Created on 2009-2-16
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
 * The class <code>ContactsQueryReq</code>
 *
 * @author xushengyong
 * @version 1.0
 */
public class ContactsQueryReq extends MobileMsgBase {
    
    public final static byte TAG_ID = 0x01;              // ID
    public final static byte TAG_FULL_NAME = 0x11;       // FullName
    public final static byte TAG_MOBILE_PHONE = 0x12;    // MobilePhone
    public final static byte TAG_WORK_PHONE = 0x13;      // WorkPhone
    public final static byte TAG_HOME_PHONE = 0x14;      // HomePhone
    public final static byte TAG_EMAIL = 0x15;           // Email
    public final static byte TAG_TITLE = 0x16;           // Title
    public final static byte TAG_COMPANY = 0x17;         // Company
    public final static byte TAG_ADDRESS = 0x18;         // Address
    public final static byte TAG_START_INDEX = 0x02;     // StartIndex
    public final static byte TAG_COUNT = 0x03;           // Counts  
    
    /**
     * The contact ID
     */
    protected String sID = "";
    
    /**
     * The full name
     */    
    protected String sFullName = "";
    
    /**
     * The mobile phone
     */    
    protected String sMobilePhone = "";

    /**
     * The work phone
     */    
    protected String sWorkPhone = "";
    
    /**
     * The home phone
     */    
    protected String sHomePhone = "";
    
    /**
     * The email
     */    
    protected String sEmail = "";
    
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
    public ContactsQueryReq() {
        super(MobileConstants.CONTACTS_QUERY_REQ, "ContactsQueryReq");
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
     * Sets the full name
     * @param sFullName The full name
     */
    public void setFullName(String sFullName) {
        this.sFullName = sFullName;
        setVerify("FullName", true);
    }

    /**
     * Gets the full name
     * @return Return the full name
     */
    public String getFullName() {
        return sFullName;
    }
    
    /**
     * Sets the mobile phone
     * @param sMobilePhone The mobile phone
     */
    public void setMobilePhone(String sMobilePhone) {
        this.sMobilePhone = sMobilePhone;
        setVerify("MobilePhone", true);
    }

    /**
     * Gets the mobile phone
     * @return Return the mobile phone
     */
    public String getMobilePhone() {
        return sMobilePhone;
    }
    
    /**
     * Sets the work phone
     * @param sWorkPhone The work phone
     */
    public void setWorkPhone(String sWorkPhone) {
        this.sWorkPhone = sWorkPhone;
        setVerify("WorkPhone", true);
    }
    
    /**
     * Gets the work phone
     * @return Return the work phone
     */
    public String getWorkPhone() {
        return sWorkPhone;
    }
    
    /**
     * Sets the home phone
     * @param sHomePhone The home phone
     */
    public void setHomePhone(String sHomePhone) {
        this.sHomePhone = sHomePhone;
        setVerify("HomePhone", true);
    }
    
    /**
     * Gets the home phone
     * @return Return the home phone
     */
    public String getHomePhone() {
        return sHomePhone;
    }
    
    /**
     * Sets the email
     * @param sEmail The email
     */
    public void setEmail(String sEmail) {
        this.sEmail = sEmail;
        setVerify("Email", true);
    }

    /**
     * Gets the email
     * @return Return the email
     */
    public String getEmail() {
        return sEmail;
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
            case ContactsQueryReq.TAG_ID:
            {
                // Decodes the ID
                if ((offset + MobileConstants.MAX_ID_LEN) > length) {
                    throw new ToolException(
                        "CalendarQueryReq decode error: no ID in the record");
                }
                sID = new String(byMsg, offset,
                        MobileConstants.MAX_ID_LEN).trim();
                setPresent("ID", true);
                offset += MobileConstants.MAX_ID_LEN;          
                break;
            }
            case ContactsQueryReq.TAG_FULL_NAME:
            {
                // Decodes the length field
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "ContactsQueryReq decode error: no FullName in the record");
                }                      
                int nLen = ByteArray.bytesToInt(byMsg, offset);
                offset += 4;
                
                // Decodes the value field
                if ((offset + nLen) > length) {
                    throw new ToolException(
                    "ContactsQueryReq decode error: no FullName in the record");                    
                }
                sFullName = new String(byMsg, offset, nLen);
                setPresent("FullName", true);
                offset += nLen;            
                break;
            }        
            case ContactsQueryReq.TAG_MOBILE_PHONE:
            {
                // Decodes the length field
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "ContactsQueryReq decode error: no MobilePhone in the record");
                }                      
                int nLen = ByteArray.bytesToInt(byMsg, offset);
                offset += 4;
                
                // Decodes the value field
                if ((offset + nLen) > length) {
                    throw new ToolException(
                    "ContactsQueryReq decode error: no MobilePhone in the record");                    
                }
                sMobilePhone = new String(byMsg, offset, nLen);
                setPresent("MobilePhone", true);
                offset += nLen;            
                break;
            }
            case ContactsQueryReq.TAG_WORK_PHONE:
            {
                // Decodes the length field
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "ContactsQueryReq decode error: no WorkPhone in the record");
                }                      
                int nLen = ByteArray.bytesToInt(byMsg, offset);
                offset += 4;
                
                // Decodes the value field
                if ((offset + nLen) > length) {
                    throw new ToolException(
                    "ContactsQueryReq decode error: no WorkPhone in the record");                    
                }
                sWorkPhone = new String(byMsg, offset, nLen);
                setPresent("WorkPhone", true);
                offset += nLen;            
                break;
            }
            case ContactsQueryReq.TAG_HOME_PHONE:
            {
                // Decodes the length field
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "ContactsQueryReq decode error: no HomePhone in the record");
                }                      
                int nLen = ByteArray.bytesToInt(byMsg, offset);
                offset += 4;
                
                // Decodes the value field
                if ((offset + nLen) > length) {
                    throw new ToolException(
                    "ContactsQueryReq decode error: no HomePhone in the record");                    
                }
                sHomePhone = new String(byMsg, offset, nLen);
                setPresent("HomePhone", true);
                offset += nLen;            
                break;
            }
            case ContactsQueryReq.TAG_EMAIL:
            {
                // Decodes the length field
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "ContactsQueryReq decode error: no Email in the record");
                }                      
                int nLen = ByteArray.bytesToInt(byMsg, offset);
                offset += 4;
                
                // Decodes the value field
                if ((offset + nLen) > length) {
                    throw new ToolException(
                    "ContactsQueryReq decode error: no Email in the record");                    
                }
                sEmail = new String(byMsg, offset, nLen);
                setPresent("Email", true);
                offset += nLen;            
                break;
            }
            case ContactsQueryReq.TAG_START_INDEX:
            {
                // Decodes the value filed
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "ContactsQueryReq decode error: no StartIndex in the record");
                }                      
                nStartIndex = ByteArray.bytesToInt(byMsg, offset);
                setPresent("StartIndex", true);
                offset += 4;            
                break;
            }
            case ContactsQueryReq.TAG_COUNT:
            {
                // Decodes the value filed
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "ContactsQueryReq decode error: no Count in the record");
                }                      
                nCount = ByteArray.bytesToInt(byMsg, offset);
                setPresent("Count", true);
                offset += 4;            
                break;
            }             
            default:
            {
                throw new ToolException(
                        "ContactsQueryReq decode error: unkown tag type: " + nTag);
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
            baMsg.append(ContactsQueryReq.TAG_ID);
            byte[] byID = new byte[MobileConstants.MAX_ID_LEN];
            GenHelper.copyString(byID, sID);
            baMsg.append(byID);
        }

        if (isPresent("FullName"))
        {
            // Encodes the full name
            baMsg.append(ContactsQueryReq.TAG_FULL_NAME);
            baMsg.append(sFullName.getBytes().length);
            baMsg.append(sFullName);
        }

        if (isPresent("MobilePhone"))
        {        
            // Encodes the mobile phone
            baMsg.append(ContactsQueryReq.TAG_MOBILE_PHONE);
            baMsg.append(sMobilePhone.getBytes().length);
            baMsg.append(sMobilePhone);
        }

        if (isPresent("WorkPhone"))
        {        
            // Encodes the work phone
            baMsg.append(ContactsQueryReq.TAG_WORK_PHONE);
            baMsg.append(sWorkPhone.getBytes().length);
            baMsg.append(sWorkPhone);
        }

        if (isPresent("HomePhone"))
        {
            // Encodes the home phone
            baMsg.append(ContactsQueryReq.TAG_HOME_PHONE);
            baMsg.append(sHomePhone.getBytes().length);
            baMsg.append(sHomePhone);
        }

        if (isPresent("Email"))
        {        
            // Encodes the email.
            baMsg.append(ContactsQueryReq.TAG_EMAIL);
            baMsg.append(sEmail.getBytes().length);
            baMsg.append(sEmail);
        }

        if (isPresent("StartIndex"))
        {        
            // Encodes the start index
            baMsg.append(ContactsQueryReq.TAG_START_INDEX);
            baMsg.append(nStartIndex);
        }

        if (isPresent("Count"))
        {        
            // Encodes the count
            baMsg.append(ContactsQueryReq.TAG_COUNT);
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

        if (isPresent("FullName"))
        {
            // Formats the contact name
            buf.append(sPrefix).
                append("FullName = ").
                append(sFullName).
                append(Constants.LINE_SEPARATOR);
        }

        if (isPresent("MobilePhone"))
        {        
            // Formats the mobile phone
            buf.append(sPrefix).
                append("MobilePhone = ").
                append(sMobilePhone).
                append(Constants.LINE_SEPARATOR);
        }

        if (isPresent("WorkPhone"))
        {         
            // Formats the work phone
            buf.append(sPrefix).
                append("WorkPhone = ").
                append(sWorkPhone).
                append(Constants.LINE_SEPARATOR);
        }

        if (isPresent("HomePhone"))
        {
            // Formats the home phone
            buf.append(sPrefix).
                append("HomePhone = ").
                append(sHomePhone).
                append(Constants.LINE_SEPARATOR);            
        }

        if (isPresent("Email"))
        {
            // Formats the email
            buf.append(sPrefix).
                append("Email = ").
                append(sEmail).
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

        if (!(msgVerified instanceof ContactsQueryReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'ContactsQueryReq' message");
        }
        
        ContactsQueryReq obj = (ContactsQueryReq)msgVerified;
        
        // All fields below is optional, so call doVerifyPresentField
        doVerifyPresentField("ID", sID, obj, obj.sID);
        doVerifyPresentField("FullName", sFullName, obj, obj.sFullName);     
        doVerifyPresentField("MobilePhone", sMobilePhone, obj, obj.sMobilePhone);       
        doVerifyPresentField("WorkPhone", sWorkPhone, obj, obj.sWorkPhone);
        doVerifyPresentField("HomePhone", sHomePhone, obj, obj.sHomePhone);          
        doVerifyPresentField("Email", sEmail, obj, obj.sEmail); 
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
        
        if (!(msgVerified instanceof ContactsQueryReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'ContactsQueryReq' message");
        }

        ContactsQueryReq obj = (ContactsQueryReq)msgVerified;

        doVerifyPresentField("ID", sID, obj, obj.sID);
        doVerifyPresentField("FullName", sFullName, obj, obj.sFullName);     
        doVerifyPresentField("MobilePhone", sMobilePhone, obj, obj.sMobilePhone);       
        doVerifyPresentField("WorkPhone", sWorkPhone, obj, obj.sWorkPhone);
        doVerifyPresentField("HomePhone", sHomePhone, obj, obj.sHomePhone);          
        doVerifyPresentField("Email", sEmail, obj, obj.sEmail); 
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

        if (!(msgVerified instanceof ContactsQueryReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'ContactsQueryReq' message");
        }

        ContactsQueryReq obj = (ContactsQueryReq)msgVerified;
        
        doVerifySpecifiedField("ID", sID, obj, obj.sID);
        doVerifySpecifiedField("FullName", sFullName, obj, obj.sFullName);     
        doVerifySpecifiedField("MobilePhone", sMobilePhone, obj, obj.sMobilePhone);       
        doVerifySpecifiedField("WorkPhone", sWorkPhone, obj, obj.sWorkPhone);
        doVerifySpecifiedField("HomePhone", sHomePhone, obj, obj.sHomePhone);          
        doVerifySpecifiedField("Email", sEmail, obj, obj.sEmail); 
        doVerifySpecifiedField("StartIndex", nStartIndex, obj, obj.nStartIndex);
        doVerifySpecifiedField("Count", nCount, obj, obj.nCount);

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.FieldMsgBase#createDefaultResponse()
     */
    public MsgBase createDefaultResponse() {
        
        ContactsQueryResp oResp = new ContactsQueryResp();
        
        oResp.setSequence(nSeqNum);
        
        return oResp;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj)) return false;

        if (!(obj instanceof ContactsQueryReq)) {
            return false;
        }

        ContactsQueryReq oMsgObj = (ContactsQueryReq)obj;
        
        if (isPresent("ID") && oMsgObj.isPresent("ID"))
        {
            if (!oMsgObj.sID.equals(sID))
            {
                return false;
            }
        } else {
            return false;
        }
        
        if (isPresent("FullName") && oMsgObj.isPresent("FullName"))
        {
            if (!oMsgObj.sFullName.equals(sFullName))
            {
                return false;
            }
        } else {
            return false;
        }
        
        if (isPresent("MobilePhone") && oMsgObj.isPresent("MobilePhone"))
        {
            if (!oMsgObj.sMobilePhone.equals(sMobilePhone))
            {
                return false;
            }
        } else {
            return false;
        }
        
        if (isPresent("WorkPhone") && oMsgObj.isPresent("WorkPhone"))
        {
            if (!oMsgObj.sWorkPhone.equals(sWorkPhone))
            {
                return false;
            }
        } else {
            return false;
        }
        
        if (isPresent("HomePhone") && oMsgObj.isPresent("HomePhone"))
        {
            if (!oMsgObj.sHomePhone.equals(sHomePhone))
            {
                return false;
            }
        } else {
            return false;
        }
        
        if (isPresent("Email") && oMsgObj.isPresent("Email"))
        {
            if (!oMsgObj.sEmail.equals(sEmail))
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
