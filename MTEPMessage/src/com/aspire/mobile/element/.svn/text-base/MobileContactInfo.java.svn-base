/**
 * @(#) MobileContactInfo.java Created on 2009-2-17
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.element;

import com.aspire.Constants;
import com.aspire.mobile.MobileConstants;
import com.aspire.mobile.msg.ContactsQueryReq;
import com.aspire.msg.MsgField;
import com.aspire.util.ByteArray;
import com.aspire.util.GenHelper;
import com.aspire.util.ToolException;

/**
 * The class <code>MobileContactInfo</code>
 *
 * @author xushengyong
 * @version 1.0
 */
public class MobileContactInfo extends MobileElementBase {
  
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
     * The title
     */    
    protected String sTitle = "";

    /**
     * The company
     */    
    protected String sCompany = "";

    /**
     * The address
     */    
    protected String sAddress = "";   
    
    /**
     * The constructor
     */
    public MobileContactInfo() {
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
     * Sets the title
     * @param sTitle The title
     */
    public void setTitle(String sTitle) {
        this.sTitle = sTitle;
        setVerify("Title", true);
    }
    
    /**
     * Gets the title
     * @return Return the title
     */
    public String getTitle() {
        return sTitle;
    }
    
    /**
     * Sets the company
     * @param sCompany The company
     */
    public void setCompany(String sCompany) {
        this.sCompany = sCompany;
        setVerify("Company", true);
    }
    
    /**
     * Gets the company
     * @return Return the company
     */
    public String getCompany() {
        return sCompany;
    }
    
    /**
     * Sets the address
     * @param sAddress The address
     */
    public void setAddress(String sAddress) {
        this.sAddress = sAddress;
        setVerify("Address", true);
    }
    
    /**
     * Gets the address
     * @return Return the address
     */
    public String getAddress() {
        return sAddress;
    }
    
    /**
     * Decodes all fields, decode a message object from the buffer
     * 
     * @param byMsg The message buffer
     * @param start The start index
     * @param length The message length
     * @return Returns the decoded buffer length
     * @throws Throws a ToolException when some errors occur
     */
    public int decode(byte[] byMsg, int start, int length) throws ToolException {

        int nLen = 0;
        int offset = start;
        
        // Decodes the ID
        if ((offset + MobileConstants.MAX_ID_LEN) > length) {
            throw new ToolException(
                "MobileContactInfo decode error: no ID in the record");
        }
        sID = new String(byMsg, offset,
                MobileConstants.MAX_ID_LEN).trim();
        setPresent("ID", true);
        offset += MobileConstants.MAX_ID_LEN;
        
        // Decodes the full name length field
        if ((offset + 4) > length) {
            throw new ToolException(
            "MobileContactInfo decode error: no FullName in the record");
        }                      
        nLen = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;
        
        // Decodes the full name value field
        if ((offset + nLen) > length) {
            throw new ToolException(
            "MobileContactInfo decode error: no FullName in the record");                    
        }
        sFullName = new String(byMsg, offset, nLen);
        setPresent("FullName", true);
        offset += nLen;
        
        while (offset < length) {
            // Decodes the tag field
            byte nTag = byMsg[offset];
            offset++;
            
            switch (nTag) {
            case ContactsQueryReq.TAG_MOBILE_PHONE:
            {                
                // Decodes the MobilePhone length field
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "MobileContactInfo decode error: no MobilePhone in the record");
                }                      
                nLen = ByteArray.bytesToInt(byMsg, offset);
                offset += 4;
                
                // Decodes the MobilePhone value field
                if ((offset + nLen) > length) {
                    throw new ToolException(
                    "MobileContactInfo decode error: no MobilePhone in the record");                    
                }
                setMobilePhone(new String(byMsg, offset, nLen));
                offset += nLen;
                break;
            }
            case ContactsQueryReq.TAG_WORK_PHONE:
            {
                // Decodes the WorkPhone length field
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "MobileContactInfo decode error: no WorkPhone in the record");
                }                      
                nLen = ByteArray.bytesToInt(byMsg, offset);
                offset += 4;
                
                // Decodes the WorkPhone value field
                if ((offset + nLen) > length) {
                    throw new ToolException(
                    "MobileContactInfo decode error: no WorkPhone in the record");                    
                }
                setWorkPhone(new String(byMsg, offset, nLen));
                offset += nLen;
                break;
            }
            case ContactsQueryReq.TAG_HOME_PHONE:
            {
                // Decodes the HomePhone length field
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "MobileContactInfo decode error: no HomePhone in the record");
                }                      
                nLen = ByteArray.bytesToInt(byMsg, offset);
                offset += 4;
                
                // Decodes the HomePhone value field
                if ((offset + nLen) > length) {
                    throw new ToolException(
                    "MobileContactInfo decode error: no HomePhone in the record");                    
                }
                setHomePhone(new String(byMsg, offset, nLen));
                offset += nLen;
                break;
            }
            case ContactsQueryReq.TAG_EMAIL:
            {
                // Decodes the Email length field
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "MobileContactInfo decode error: no Email in the record");
                }                      
                nLen = ByteArray.bytesToInt(byMsg, offset);
                offset += 4;
                
                // Decodes the Email value field
                if ((offset + nLen) > length) {
                    throw new ToolException(
                    "MobileContactInfo decode error: no Email in the record");                    
                }
                setEmail(new String(byMsg, offset, nLen));
                offset += nLen;
                break;
            }
            case ContactsQueryReq.TAG_ADDRESS:
            {
                // Decodes the Address length field
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "MobileContactInfo decode error: no Address in the record");
                }                      
                nLen = ByteArray.bytesToInt(byMsg, offset);
                offset += 4;
                
                // Decodes the Address value field
                if ((offset + nLen) > length) {
                    throw new ToolException(
                    "MobileContactInfo decode error: no Address in the record");                    
                }
                setAddress(new String(byMsg, offset, nLen));
                offset += nLen;
                break;
            }
            case ContactsQueryReq.TAG_COMPANY:
            {
                // Decodes the Company length field
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "MobileContactInfo decode error: no Company in the record");
                }                      
                nLen = ByteArray.bytesToInt(byMsg, offset);
                offset += 4;
                
                // Decodes the Company value field
                if ((offset + nLen) > length) {
                    throw new ToolException(
                    "MobileContactInfo decode error: no Company in the record");                    
                }
                setCompany(new String(byMsg, offset, nLen));
                offset += nLen;
                break;
            }
            case ContactsQueryReq.TAG_TITLE:
            {
                // Decodes the Title length field
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "MobileContactInfo decode error: no Title in the record");
                }                      
                nLen = ByteArray.bytesToInt(byMsg, offset);
                offset += 4;
                
                // Decodes the Title value field
                if ((offset + nLen) > length) {
                    throw new ToolException(
                    "MobileContactInfo decode error: no Title in the record");                    
                }
                setTitle(new String(byMsg, offset, nLen));
                offset += nLen;
                break;
            }
            default:
            {
                throw new ToolException(
                        "MobileContactInfo decode error: unkown tag type: " + nTag);
            }
            }
        }  // End of while (offset < length)
        return offset - start;
    }

    /**
     * Encodes the MobileContactInfo object into a buffer
     * 
     * @param baMsg The message buffer
     * @return Returns the encoded buffer length
     * @throws Throws a ToolException when some errors occur
     */
    public int encode(ByteArray baMsg) throws ToolException {

        int nLen = baMsg.length();
        
        // Encodes the contact ID
        byte[] byID = new byte[MobileConstants.MAX_ID_LEN];
        GenHelper.copyString(byID, sID);
        baMsg.append(byID);
        
        // Encodes the full name
        baMsg.append(sFullName.getBytes().length); 
        baMsg.append(sFullName);
        
        if (isPresent("MobilePhone")) {
            // Encodes the mobile phone
            baMsg.append(ContactsQueryReq.TAG_MOBILE_PHONE);
            baMsg.append(sMobilePhone.getBytes().length);        
            baMsg.append(sMobilePhone);
        }
        
        if (isPresent("WorkPhone")) {
            // Encodes the work phone
            baMsg.append(ContactsQueryReq.TAG_WORK_PHONE);
            baMsg.append(sWorkPhone.getBytes().length);   
            baMsg.append(sWorkPhone);
        }
        
        if (isPresent("HomePhone")) {
            // Encodes the home phone
            baMsg.append(ContactsQueryReq.TAG_HOME_PHONE);
            baMsg.append(sHomePhone.getBytes().length);
            baMsg.append(sHomePhone);
        }
        
        if (isPresent("Email")) {
            // Encodes the email
            baMsg.append(ContactsQueryReq.TAG_EMAIL);
            baMsg.append(sEmail.getBytes().length);
            baMsg.append(sEmail);
        }
        
        if (isPresent("Address")) {
            // Encodes the address
            baMsg.append(ContactsQueryReq.TAG_ADDRESS);
            baMsg.append(sAddress.getBytes().length);
            baMsg.append(sAddress);
        }
        
        if (isPresent("Company")) {
            // Encodes the company
            baMsg.append(ContactsQueryReq.TAG_COMPANY);
            baMsg.append(sCompany.getBytes().length);
            baMsg.append(sCompany);
        }
        
        if (isPresent("Title")) {
            // Encodes the title
            baMsg.append(ContactsQueryReq.TAG_TITLE);
            baMsg.append(sTitle.getBytes().length);
            baMsg.append(sTitle);
        }
        
        return baMsg.length() - nLen;
    }

    /**
     * Formats the MobileContactInfo object to a readable string
     * 
     * @param buf  The buffer to save the format string
     * @param sPrefix The Prefix
     * @return Returns the format string length
     * @throws Throws a ToolException when some errors occur
     */
    public int format(StringBuffer buf, String sPrefix) throws ToolException {

        int nLen = buf.length();
        
        // Formats the contact ID
        buf.append(sPrefix).
            append("ID = ").
            append(sID).
            append(Constants.LINE_SEPARATOR);

        // Formats the full name
        buf.append(sPrefix).
            append("FullName = ").
            append(sFullName).
            append(Constants.LINE_SEPARATOR);

        // Formats the mobile phone
        buf.append(sPrefix).
            append("MobilePhone = ").
            append(sMobilePhone).
            append(Constants.LINE_SEPARATOR);

        // Formats the work phone
        buf.append(sPrefix).
            append("WorkPhone = ").
            append(sWorkPhone).
            append(Constants.LINE_SEPARATOR);

        // Formats the home phone
        buf.append(sPrefix).
            append("HomePhone = ").
            append(sHomePhone).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the email
        buf.append(sPrefix).
            append("Email = ").
            append(sEmail).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the title
        buf.append(sPrefix).
            append("Title = ").
            append(sTitle).
            append(Constants.LINE_SEPARATOR);

        // Formats the company
        buf.append(sPrefix).
            append("Company = ").
            append(sCompany).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the address
        buf.append(sPrefix).
            append("Address = ").
            append(sAddress).
            append(Constants.LINE_SEPARATOR);
        
        return buf.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.MsgField#verifyField(com.aspire.msg.MsgField)
     */
    public boolean verifyField(MsgField field) throws ToolException {

        if (this == field) return true;

        if (field == null) {
            throw new ToolException(
                "Message verify error: the field 'MobileContactInfo' is null");
        }

        if (!(field instanceof MobileContactInfo)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MobileContactInfo' object");
        }

        MobileContactInfo obj = (MobileContactInfo)field;
        
        doVerifyField("ID", sID, obj, obj.sID);
        doVerifyField("FullName", sFullName, obj, obj.sFullName);     
        doVerifyField("MobilePhone", sMobilePhone, obj, obj.sMobilePhone);       
        doVerifyField("WorkPhone", sWorkPhone, obj, obj.sWorkPhone);
        doVerifyField("HomePhone", sHomePhone, obj, obj.sHomePhone);          
        doVerifyField("Email", sEmail, obj, obj.sEmail); 
        doVerifyField("Title", sTitle, obj, obj.sTitle);
        doVerifyField("Company", sCompany, obj, obj.sCompany);          
        doVerifyField("Address", sAddress, obj, obj.sAddress); 

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.MsgField#verifyPresentField(com.aspire.msg.MsgField)
     */
    public boolean verifyPresentField(MsgField field) throws ToolException {

        if (this == field) return true;

        if (field == null) {
            throw new ToolException(
                "Message verify error: the field 'MobileContactInfo' is null");
        }

        if (!(field instanceof MobileContactInfo)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MobileContactInfo' object");
        }
        
        MobileContactInfo obj = (MobileContactInfo)field;
        
        doVerifyPresentField("ID", sID, obj, obj.sID);
        doVerifyPresentField("FullName", sFullName, obj, obj.sFullName);     
        doVerifyPresentField("MobilePhone", sMobilePhone, obj, obj.sMobilePhone);       
        doVerifyPresentField("WorkPhone", sWorkPhone, obj, obj.sWorkPhone);
        doVerifyPresentField("HomePhone", sHomePhone, obj, obj.sHomePhone);          
        doVerifyPresentField("Email", sEmail, obj, obj.sEmail); 
        doVerifyPresentField("Title", sTitle, obj, obj.sTitle);
        doVerifyPresentField("Company", sCompany, obj, obj.sCompany);          
        doVerifyPresentField("Address", sAddress, obj, obj.sAddress);
        
        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.MsgField#verifySpecifiedField(com.aspire.msg.MsgField)
     */
    public boolean verifySpecifiedField(MsgField field) throws ToolException {

        if (this == field) return true;

        if (field == null) {
            throw new ToolException(
                "Message verify error: the field 'MobileContactInfo' is null");
        }

        if (!(field instanceof MobileContactInfo)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MobileContactInfo' object");
        }

        MobileContactInfo obj = (MobileContactInfo)field;
        
        doVerifySpecifiedField("ID", sID, obj, obj.sID);
        doVerifySpecifiedField("FullName", sFullName, obj, obj.sFullName);     
        doVerifySpecifiedField("MobilePhone", sMobilePhone, obj, obj.sMobilePhone);       
        doVerifySpecifiedField("WorkPhone", sWorkPhone, obj, obj.sWorkPhone);
        doVerifySpecifiedField("HomePhone", sHomePhone, obj, obj.sHomePhone);          
        doVerifySpecifiedField("Email", sEmail, obj, obj.sEmail); 
        doVerifySpecifiedField("Title", sTitle, obj, obj.sTitle);
        doVerifySpecifiedField("Company", sCompany, obj, obj.sCompany);          
        doVerifySpecifiedField("Address", sAddress, obj, obj.sAddress);
        
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || !(obj instanceof MobileContactInfo)) {
            return false;
        }
        
        MobileContactInfo oMsgField = (MobileContactInfo)obj;

        return oMsgField.sID.equals(sID)
                && oMsgField.sFullName.equals(sFullName)
                && oMsgField.sMobilePhone.equals(sMobilePhone)
                && oMsgField.sWorkPhone.equals(sWorkPhone)
                && oMsgField.sHomePhone.equals(sHomePhone)
                && oMsgField.sEmail.equals(sEmail)
                && oMsgField.sTitle.equals(sTitle)
                && oMsgField.sCompany.equals(sCompany)
                && oMsgField.sAddress.equals(sAddress);
    }
    
    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#isPresent(java.lang.String)
     */
    public boolean isPresent(String field) {
        return buildFields.contains(field);
    }    
}
