/**
 * @(#) MessageBoxQueryReq.java Created on 2009-2-18
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.msg;

import java.util.ArrayList;

import com.aspire.Constants;
import com.aspire.mobile.MobileConstants;
import com.aspire.msg.MsgBase;
import com.aspire.util.ByteArray;
import com.aspire.util.GenApi;
import com.aspire.util.GenHelper;
import com.aspire.util.ToolException;

/**
 * The class <code>MessageBoxQueryReq</code>
 *
 * @author xushengyong
 * @version 1.0
 */
public class SMSOperationReq extends MobileMsgBase {

    public final static byte TAG_ID = 0x01;              // ID
    public final static byte TAG_ISREAD = 0x11;          // IsRead   
    public final static byte TAG_SOURCE_ADDRESS = 0x12;  // SourceAddress    
    public final static byte TAG_DEST_ADDRESS = 0x13;    // DestAddress
    public final static byte TAG_FILE_NAME = 0x14;       // FileName
    public final static byte TAG_MIME_TYPE = 0x15;       // MimeType    
    public final static byte TAG_START_TIME = 0x16;      // StartTime
    public final static byte TAG_END_TIME = 0x17;        // EndTime
    public final static byte TAG_FOLDER_NAME = 0x18;     // FolderName 
    
    /**
     * The message action
     */
    protected byte nAction = MobileConstants.SMS_OPERATION_ACTION_UPDATE;
    
    /**
     * The contact ID
     */
    protected String sID = "";
    
    /**
     * The message type, 0x00:Unreaded/0x01:Readed
     */
    protected byte nIsRead = MobileConstants.FLAG_UNREADED;

    /**
     * The destination address
     */
    protected ArrayList<String> aDestAddresses = new ArrayList<String>();
    
    /**
     * The source address
     */
    protected ArrayList<String> aSourceAddresses = new ArrayList<String>(); 
    
    /**
     * The start time
     */
    protected String sStartTime = GenApi.getTime();
    
    /**
     * The end time
     */
    protected String sEndTime = GenApi.getTime();
    
    /**
     * The folder name
     */
    protected String sFolderName = "";

    /**
     * The constructor
     */
    public SMSOperationReq() {
        super(MobileConstants.SMS_OPERATION_REQ, "SMSOperationReq");
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
     * Sets the un/readed flag
     * @param nIsRead The un/readed flag
     */
    public void setIsRead(byte nIsRead) {
        this.nIsRead = nIsRead;
        setVerify("IsRead");
    }

    /**
     * Sets the un/readed flag
     * @param sIsRead The un/readed flag
     * @throws ToolException 
     */
    public void setIsRead(String sIsRead) throws ToolException {
        setIsRead(GenApi.strToByte(sIsRead, "IsRead"));
    }

    /**
     * Gets the un/readed flag
     * @return Returns The un/readed flag
     */
    public String getIsRead() {
        return Byte.toString(nIsRead);
    }

    /**
     * Gets the un/readed flag
     * @return Returns the un/readed flag
     */
    public byte getIsReadValue() {
        return nIsRead;
    }
    
    /**
     * Sets Action
     * @param nAction
     */
    public void setAction(byte nAction) {
        this.nAction = nAction;
        setVerify("Action");
    }

    /**
     * Sets Action
     * @param nAction 
     * @throws ToolException 
     */
    public void setAction(String nAction) throws ToolException {
        setAction(GenApi.strToByte(nAction, "Action"));
    }

    /**
     * Gets Action
     * @return Returns Action
     */
    public String getAction() {
        return Byte.toString(nAction);
    }

    /**
     * Gets the Action
     * @return Returns the Action
     */
    public byte getActionValue() {
        return nAction;
    }
    
    /**
     * Sets the destination address
     * @param aDestAddresses The destination addresses
     */
    public void setSourceAddress(ArrayList<String> aSourceAddresses) {
        this.aSourceAddresses = aSourceAddresses;
        setVerify("SourceAddress", true);
    }
    
    /**
     * Sets the destination address
     * @param sDestAddresses The destination addresses
     */
    public void setSourceAddress(String sSourceAddresses) {
        setSourceAddress(GenHelper.parseStrToList(
                sSourceAddresses, MobileConstants.SEPARATOR_SEMICOLON));
    }

    /**
     * Gets the Sourceination addresses list
     * @return Return the Sourceination address
     */
    public ArrayList<String> getSourceAddressList() {
        return aSourceAddresses;
    }
    
    /**
     * Gets the Sourceination address
     * @return Return the Sourceination address
     */
    public String getSourceAddress() {
        StringBuffer sSourceAddresses = new StringBuffer();
        int i = 0;
        int size = aSourceAddresses.size();
        for ( ; i < (size - 1); i++) {
            sSourceAddresses.append(aSourceAddresses.get(i))
            .append(MobileConstants.SEPARATOR_SEMICOLON);
        }
        if (0 != size) {
            sSourceAddresses.append(aSourceAddresses.get(i));
        }
        return sSourceAddresses.toString();
    }
    
    /**
     * Adds the Sourceination address
     * @param sSourceAddress The Sourceination address
     */
    public void addSourceAddress(String sSourceAddress) {
        if (aSourceAddresses.contains(sSourceAddress)) return;
        aSourceAddresses.add(sSourceAddress);
        setVerify("SourceAddress");
    } 

    /**
     * Sets the destination address
     * @param aDestAddresses The destination addresses
     */
    public void setDestAddress(ArrayList<String> aDestAddresses) {
        this.aDestAddresses = aDestAddresses;
        setVerify("DestAddress", true);
    }
    
    /**
     * Sets the destination address
     * @param sDestAddresses The destination addresses
     */
    public void setDestAddress(String sDestAddresses) {
        setDestAddress(GenHelper.parseStrToList(
                sDestAddresses, MobileConstants.SEPARATOR_SEMICOLON));
    }

    /**
     * Gets the destination addresses list
     * @return Return the destination address
     */
    public ArrayList<String> getDestAddressList() {
        return aDestAddresses;
    }
    
    /**
     * Gets the destination address
     * @return Return the destination address
     */
    public String getDestAddress() {
        StringBuffer sDestAddresses = new StringBuffer();
        int i = 0;
        int size = aDestAddresses.size();
        for ( ; i < (size - 1); i++) {
            sDestAddresses.append(aDestAddresses.get(i))
            .append(MobileConstants.SEPARATOR_SEMICOLON);
        }
        if (0 != size) {
            sDestAddresses.append(aDestAddresses.get(i));
        }
        return sDestAddresses.toString();
    }
    
    /**
     * Adds the destination address
     * @param sDestAddress The destination address
     */
    public void addDestAddress(String sDestAddress) {
        if (aDestAddresses.contains(sDestAddress)) return;
        aDestAddresses.add(sDestAddress);
        setVerify("DestAddress");
    }     
    
    /**
     * Sets the start time
     * @param sStartTime The creation date
     */
    public void setStartTime(String sStartTime) {
        this.sStartTime = GenApi.formatDate(sStartTime);
        setVerify("StartTime");
    }

    /**
     * Gets the start time
     * @return Returns start time
     */
    public String getStartTime() {
        return sStartTime;
    }
    
    /**
     * Sets the end time
     * @param sEndTime The creation date
     */
    public void setEndTime(String sEndTime) {
        this.sEndTime = GenApi.formatDate(sEndTime);
        setVerify("EndTime");
    }

    /**
     * Gets the end time
     * @return Returns end time
     */
    public String getEndTime() {
        return sEndTime;
    }
      
    /**
     * Sets the folder name  available value(all, inbox sendbox)
     * @param sFolderName The folder name
     */
    public void setFolderName(String sFolderName) {
        this.sFolderName = sFolderName;
        setVerify("FolderName", true);
    }
    
    /**
     * Gets the folder name
     * @return Return the folder name
     */
    public String getFolderName() {
        return sFolderName;
    }
    
    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#decode(byte[], int, int)
     */
    public int decode(byte[] byMsg, int start, int length) throws ToolException {
        
        int nRead = super.decode(byMsg, start, length);
        int offset = start + nRead;
        
        nAction = byMsg[offset];
        offset++;
        
        while (offset < length) {            
            // Decodes the tag field
            byte nTag = byMsg[offset];
            offset++;
            
            switch (nTag) {
            case SMSOperationReq.TAG_ID:
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
            case SMSOperationReq.TAG_ISREAD:
            {             
                // Decodes the type
                if (offset >= length) {
                    throw new ToolException(
                            "MessageBoxQueryReq decode error: no IsRead in the record");
                }
                nIsRead = byMsg[offset];
                setPresent("IsRead", true);
                offset++; 
                break;
            }
            case SMSOperationReq.TAG_DEST_ADDRESS:
            {             
                // Decodes the length field
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "MessageBoxQueryReq decode error: no DestAddress in the record");
                }                      
                int nLen = ByteArray.bytesToInt(byMsg, offset);
                offset += 4;
                
                // Decodes the value field
                if ((offset + nLen) > length) {
                    throw new ToolException(
                    "MessageBoxQueryReq decode error: no DestAddress in the record");                    
                }
                setDestAddress(new String(byMsg, offset, nLen));
                setPresent("DestAddress", true);
                offset += nLen;  
                break;
            }
            case SMSOperationReq.TAG_SOURCE_ADDRESS:
            {             
                // Decodes the length field
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "MessageBoxQueryReq decode error: no SourceAddress in the record");
                }                      
                int nLen = ByteArray.bytesToInt(byMsg, offset);
                offset += 4;
                
                // Decodes the value field
                if ((offset + nLen) > length) {
                    throw new ToolException(
                    "MessageBoxQueryReq decode error: no SourceAddress in the record");                    
                }
                setSourceAddress(new String(byMsg, offset, nLen));
                setPresent("SourceAddress", true);
                offset += nLen;  
                break;
            }  
            case SMSOperationReq.TAG_START_TIME:
            {             
                // Decodes the start time
                if ((offset + MobileConstants.MAX_TIME_LEN) > length) {
                    throw new ToolException(
                        "MessageBoxQueryReq decode error: no StartTime in the record");
                }
                sStartTime = new String(byMsg, offset,
                        MobileConstants.MAX_TIME_LEN).trim();
                setPresent("StartTime", true);
                offset += MobileConstants.MAX_TIME_LEN;       
                break;
            }
            case SMSOperationReq.TAG_END_TIME:
            {             
                // Decodes the end time
                if ((offset + MobileConstants.MAX_TIME_LEN) > length) {
                    throw new ToolException(
                        "MessageBoxQueryReq decode error: no EndTime in the record");
                }
                sEndTime = new String(byMsg, offset,
                        MobileConstants.MAX_TIME_LEN).trim();
                setPresent("EndTime", true);
                offset += MobileConstants.MAX_TIME_LEN;       
                break;
            }         
            case SMSOperationReq.TAG_FOLDER_NAME:
            {             
                // Decodes the length field
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "MessageBoxQueryReq decode error: no FolderName in the record");
                }                      
                int nLen = ByteArray.bytesToInt(byMsg, offset);
                offset += 4;
                
                // Decodes the value field
                if ((offset + nLen) > length) {
                    throw new ToolException(
                    "MessageBoxQueryReq decode error: no FolderName in the record");                    
                }
                sFolderName = new String(byMsg, offset, nLen);
                setPresent("FolderName", true);
                offset += nLen;  
                break;
            }
            default:
            {
                throw new ToolException(
                        "MessageBoxQueryReq decode error: unkown tag type: " + nTag);
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
        
        baMsg.append(nAction);
        
        if (isPresent("ID"))
        {
            // Encodes the ID
            baMsg.append(SMSOperationReq.TAG_ID);
            byte[] byID = new byte[MobileConstants.MAX_ID_LEN];
            GenHelper.copyString(byID, sID);
            baMsg.append(byID);
        }
       
        
        if (isPresent("IsRead"))
        {
            // Encodes the IsRead
            baMsg.append(SMSOperationReq.TAG_ISREAD);    
            baMsg.append(nIsRead);
        }
        
        if (isPresent("DestAddress"))
        {
            // Encodes the DestAddress
            baMsg.append(SMSOperationReq.TAG_DEST_ADDRESS);
            String sDestAddress = getDestAddress();
            baMsg.append(sDestAddress.getBytes().length);
            baMsg.append(sDestAddress);
        }
        
        if (isPresent("SourceAddress"))
        {
            // Encodes the DestAddress
            baMsg.append(SMSOperationReq.TAG_SOURCE_ADDRESS);
            String sSourceAddress = getSourceAddress();
            baMsg.append(sSourceAddress.getBytes().length);
            baMsg.append(sSourceAddress);
        }
        
        if (isPresent("StartTime"))
        {
            // Encodes the start time
            baMsg.append(SMSOperationReq.TAG_START_TIME);
            byte[] byTime = new byte[MobileConstants.MAX_TIME_LEN];
            GenHelper.copyString(byTime, sStartTime);
            baMsg.append(byTime);
        }
        
        if (isPresent("EndTime"))
        {
            // Encodes the end time
            baMsg.append(SMSOperationReq.TAG_END_TIME);
            byte[] byTime = new byte[MobileConstants.MAX_TIME_LEN];
            GenHelper.copyString(byTime, sEndTime);
            baMsg.append(byTime);
        }
        
        if (isPresent("FolderName"))
        {
            // Encodes the FolderName
            baMsg.append(SMSOperationReq.TAG_FOLDER_NAME);
            baMsg.append(sFolderName.getBytes().length);
            baMsg.append(sFolderName);
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

        
        
        if (isPresent("IsRead"))
        {
            // Formats the IsRead
            buf.append(sPrefix).
                append("IsRead = ").
                append(nIsRead).
                append(Constants.LINE_SEPARATOR);
        }
               
        
        if (isPresent("DestAddress"))
        {
            
            // Formats the DestAddress
            buf.append(sPrefix).
                append("DestAddress = ").
                append(getDestAddress()).
                append(Constants.LINE_SEPARATOR);
        }
        
        if (isPresent("SourceAddress"))
        {
            
            // Formats the DestAddress
            buf.append(sPrefix).
                append("SourceAddress = ").
                append(getSourceAddress()).
                append(Constants.LINE_SEPARATOR);
        }
                
        
        if (isPresent("StartTime"))
        {
            // Formats the start time
            buf.append(sPrefix).
                append("StartTime = ").
                append(sStartTime).
                append(Constants.LINE_SEPARATOR);
        }
        
        if (isPresent("EndTime"))
        {
            // Formats the end time
            buf.append(sPrefix).
                append("EndTime = ").
                append(sEndTime).
                append(Constants.LINE_SEPARATOR);
        }
        
        if (isPresent("FolderName"))
        {
            // Formats the FolderName
            buf.append(sPrefix).
                append("FolderName = ").
                append(sFolderName).
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

        if (!(msgVerified instanceof SMSOperationReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'MessageBoxQueryReq' message");
        }
        
        SMSOperationReq obj = (SMSOperationReq)msgVerified;
        
        // All fields below is optional, so call doVerifyPresentField
        doVerifyPresentField("ID", sID, obj, obj.sID);
        doVerifyPresentField("IsRead", nIsRead, obj, obj.nIsRead);   
        doVerifyPresentField("DestAddress", aDestAddresses, obj, obj.aDestAddresses, false); 
        doVerifyPresentField("SourceAddress", aSourceAddresses, obj, obj.aSourceAddresses, false); 
        doVerifyPresentField("StartTime", sStartTime, obj, obj.sStartTime);       
        doVerifyPresentField("EndTime", sEndTime, obj, obj.sEndTime);
        doVerifyPresentField("FolderName", sFolderName, obj, obj.sFolderName); 
        
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
        
        if (!(msgVerified instanceof SMSOperationReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'MessageBoxQueryReq' message");
        }

        SMSOperationReq obj = (SMSOperationReq)msgVerified;

        doVerifyPresentField("ID", sID, obj, obj.sID);
        doVerifyPresentField("IsRead", nIsRead, obj, obj.nIsRead);   
        doVerifyPresentField("DestAddress", aDestAddresses, obj, obj.aDestAddresses, false);
        doVerifyPresentField("SourceAddress", aSourceAddresses, obj, obj.aSourceAddresses, false); 
        doVerifyPresentField("StartTime", sStartTime, obj, obj.sStartTime);       
        doVerifyPresentField("EndTime", sEndTime, obj, obj.sEndTime);
        doVerifyPresentField("FolderName", sFolderName, obj, obj.sFolderName);

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

        if (!(msgVerified instanceof SMSOperationReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'MessageBoxQueryReq' message");
        }

        SMSOperationReq obj = (SMSOperationReq)msgVerified;
        
        doVerifySpecifiedField("ID", sID, obj, obj.sID); 
        doVerifySpecifiedField("IsRead", nIsRead, obj, obj.nIsRead);    
        doVerifySpecifiedField("DestAddress", aDestAddresses, obj, obj.aDestAddresses, false);
        doVerifySpecifiedField("SourceAddress", aSourceAddresses, obj, obj.aSourceAddresses, false); 
        doVerifySpecifiedField("StartTime", sStartTime, obj, obj.sStartTime);       
        doVerifySpecifiedField("EndTime", sEndTime, obj, obj.sEndTime);
        doVerifySpecifiedField("FolderName", sFolderName, obj, obj.sFolderName);

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.FieldMsgBase#createDefaultResponse()
     */
    public MsgBase createDefaultResponse() {
        
        SMSOperationResp oResp = new SMSOperationResp();
        
        oResp.setSequence(nSeqNum);
        
        return oResp;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj)) return false;

        if (!(obj instanceof SMSOperationReq)) {
            return false;
        }

        SMSOperationReq oMsgObj = (SMSOperationReq)obj;
        
        if (isPresent("ID") && oMsgObj.isPresent("ID"))
        {
            if (!oMsgObj.sID.equals(sID))
            {
                return false;
            }
        } else {
            return false;
        }
                
        
        if (isPresent("IsRead") && oMsgObj.isPresent("IsRead"))
        {
            if (!(oMsgObj.nIsRead == nIsRead))
            {
                return false;
            }
        } else {
            return false;
        }
                
        
        if (isPresent("DestAddress") && oMsgObj.isPresent("DestAddress"))
        {
            if (!oMsgObj.aDestAddresses.equals(aDestAddresses))
            {
                return false;
            }
        } else {
            return false;
        }
               
        
        if (isPresent("StartTime") && oMsgObj.isPresent("StartTime"))
        {
            if (!oMsgObj.sStartTime.equals(sStartTime))
            {
                return false;
            }
        } else {
            return false;
        }
        
        if (isPresent("EndTime") && oMsgObj.isPresent("EndTime"))
        {
            if (!oMsgObj.sEndTime.equals(sEndTime))
            {
                return false;
            }
        } else {
            return false;
        }
        
        if (isPresent("FolderName") && oMsgObj.isPresent("FolderName"))
        {
            if (!oMsgObj.sFolderName.equals(sFolderName))
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
