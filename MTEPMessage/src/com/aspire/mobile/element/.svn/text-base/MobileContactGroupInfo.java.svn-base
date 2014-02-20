/**
 * @(#) MobileContactGroupInfo.java Created on 2009-2-17
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.element;

import com.aspire.Constants;
import com.aspire.mobile.MobileConstants;
import com.aspire.msg.MsgField;
import com.aspire.util.ByteArray;
import com.aspire.util.GenHelper;
import com.aspire.util.ToolException;

/**
 * The class <code>MobileContactGroupInfo</code>
 *
 * @author xushengyong
 * @version 1.0
 */
public class MobileContactGroupInfo extends MobileElementBase {

    /**
     * The contact ID
     */
    protected String sID = "";
    
    /**
     * The group name
     */    
    protected String sGroupName = "";

    /**
     * The notes
     */    
    protected String sNotes = "";
    
    /**
     * The constructor
     */
    public MobileContactGroupInfo() {
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
     * Sets the notes
     * @param sNotes The notes
     */
    public void setNotes(String sNotes) {
        this.sNotes = sNotes;
        setVerify("Notes", true);
    }
    
    /**
     * Gets the notes
     * @return Return the notes
     */
    public String getNotes() {
        return sNotes;
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
                "MobileContactGroupInfo decode error: no ID in the record");
        }
        sID = new String(byMsg, offset,
                MobileConstants.MAX_ID_LEN).trim();
        offset += MobileConstants.MAX_ID_LEN;
        
        // Decodes the length field
        if ((offset + 4) > length) {
            throw new ToolException(
            "MobileContactGroupInfo decode error: no GroupName in the record");
        }                      
        nLen = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;
        
        // Decodes the value field
        if ((offset + nLen) > length) {
            throw new ToolException(
            "MobileContactGroupInfo decode error: no GroupName in the record");                    
        }
        sGroupName = new String(byMsg, offset, nLen);
        offset += nLen;   
        
        // Decodes the length field
        if ((offset + 4) > length) {
            throw new ToolException(
            "MobileContactGroupInfo decode error: no Notes in the record");
        }                      
        nLen = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;
        
        // Decodes the value field
        if ((offset + nLen) > length) {
            throw new ToolException(
            "MobileContactGroupInfo decode error: no Notes in the record");                    
        }
        sNotes = new String(byMsg, offset, nLen);
        offset += nLen;        
        
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
        
        // Encodes the group name
        baMsg.append(sGroupName.getBytes().length); 
        baMsg.append(sGroupName);
        
        // Encodes the notes
        baMsg.append(sNotes.getBytes().length);        
        baMsg.append(sNotes);
        
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

        // Formats the group name
        buf.append(sPrefix).
            append("GroupName = ").
            append(sGroupName).
            append(Constants.LINE_SEPARATOR);

        // Formats the notes
        buf.append(sPrefix).
            append("Notes = ").
            append(sNotes).
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
                "Message verify error: the field 'MobileContactGroupInfo' is null");
        }

        if (!(field instanceof MobileContactGroupInfo)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MobileContactGroupInfo' object");
        }

        MobileContactGroupInfo obj = (MobileContactGroupInfo)field;
        
        doVerifyField("ID", sID, obj, obj.sID);
        doVerifyField("GroupName", sGroupName, obj, obj.sGroupName);     
        doVerifyField("Notes", sNotes, obj, obj.sNotes);       
        
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
                "Message verify error: the field 'MobileContactGroupInfo' is null");
        }

        if (!(field instanceof MobileContactGroupInfo)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MobileContactGroupInfo' object");
        }
        
        MobileContactGroupInfo obj = (MobileContactGroupInfo)field;
        
        doVerifyPresentField("ID", sID, obj, obj.sID);
        doVerifyPresentField("GroupName", sGroupName, obj, obj.sGroupName);     
        doVerifyPresentField("Notes", sNotes, obj, obj.sNotes);
        
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
                "Message verify error: the field 'MobileContactGroupInfo' is null");
        }

        if (!(field instanceof MobileContactGroupInfo)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MobileContactGroupInfo' object");
        }

        MobileContactGroupInfo obj = (MobileContactGroupInfo)field;
        
        doVerifySpecifiedField("ID", sID, obj, obj.sID);
        doVerifySpecifiedField("GroupName", sGroupName, obj, obj.sGroupName);     
        doVerifySpecifiedField("Notes", sNotes, obj, obj.sNotes);
        
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || !(obj instanceof MobileContactGroupInfo)) {
            return false;
        }
        
        MobileContactGroupInfo oMsgField = (MobileContactGroupInfo)obj;

        return oMsgField.sID.equals(sID)
                && oMsgField.sGroupName.equals(sGroupName)
                && oMsgField.sNotes.equals(sNotes);
    }
    
}
