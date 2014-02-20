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
public class MessageBoxQueryReq extends MobileMsgBase {

    public final static byte TAG_ID = 0x01;              // ID
    public final static byte TAG_TYPE = 0x11;            // Type
    public final static byte TAG_ISREAD = 0x12;          // IsRead
    public final static byte TAG_PRIORITY = 0x13;        // Priority    
    public final static byte TAG_SOURCE_ADDRESS = 0x14;  // SourceAddress    
    public final static byte TAG_CALLBACK_NUMBER = 0x15; // CallbackNumber    
    public final static byte TAG_DEST_ADDRESS = 0x16;    // DestAddress
    public final static byte TAG_CC_ADDRESS = 0x17;      // CcAddress
    public final static byte TAG_BCC_ADDRESS = 0x18;     // BccAddress  
    public final static byte TAG_SUBJECT = 0x19;         // Subject 
    public final static byte TAG_BODY = 0x1A;            // Body 
    public final static byte TAG_FILE_NAME = 0x1B;       // FileName
    public final static byte TAG_MIME_TYPE = 0x1C;       // MimeType    
    public final static byte TAG_START_TIME = 0x1D;      // StartTime
    public final static byte TAG_END_TIME = 0x1E;        // EndTime
    public final static byte TAG_FOLDER_NAME = 0x1F;     // FolderName 
    public final static byte TAG_START_INDEX = 0x02;     // StartIndex
    public final static byte TAG_COUNT = 0x03;           // Counts
    public final static byte TAG_VALIDITY_PERIOD = 0x20; // ValidityPeriod
    public final static byte TAG_HAS_ATTACHMENT = 0x21;  // HasAttachment
    
    /**
     * The contact ID
     */
    protected String sID = "";
    
    /**
     * The message type, 0x01:SMS/0x02:MMS/0x03:Email
     */
    protected byte nType = MobileConstants.MESSAGE_TYPE_SMS;
    
    /**
     * The message type, 0x00:Unreaded/0x01:Readed
     */
    protected byte nIsRead = MobileConstants.FLAG_UNREADED;
    
    /**
     * The priority, 0x00/0x01
     */
    protected byte nPriority = 0x00;

    /**
     * The destination address
     */
    protected ArrayList<String> aDestAddresses = new ArrayList<String>();
    
    /**
     * The subject
     */
    protected String sSubject = "";
    
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
    public MessageBoxQueryReq() {
        super(MobileConstants.MESSAGE_BOX_QUERY_REQ, "MessageBoxQueryReq");
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
     * Sets the type
     * @param nType The type
     */
    public void setType(byte nType) {
        this.nType = nType;
        setVerify("Type");
    }

    /**
     * Sets the type
     * @param sType The type
     * @throws ToolException 
     */
    public void setType(String sType) throws ToolException {
        setType(GenApi.strToByte(sType, "Type"));
    }

    /**
     * Gets the type
     * @return Returns The type
     */
    public String getType() {
        return Byte.toString(nType);
    }

    /**
     * Gets the type
     * @return Returns the type
     */
    public byte getTypeValue() {
        return nType;
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
     * Sets the priority
     * @param nPriority The priority
     */
    public void setPriority(byte nPriority) {
        this.nPriority = nPriority;
        setVerify("Priority");
    }

    /**
     * Sets the priority
     * @param sPriority The priority
     * @throws ToolException 
     */
    public void setPriority(String sPriority) throws ToolException {
        setPriority(GenApi.strToByte(sPriority, "Priority"));
    }

    /**
     * Gets the priority
     * @return Returns The priority
     */
    public String getPriority() {
        return Byte.toString(nPriority);
    }

    /**
     * Gets the priority
     * @return Returns the priority
     */
    public byte getPriorityValue() {
        return nPriority;
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
    }    
    
    /**
     * Sets the subject
     * @param sSubject The subject
     */
    public void setSubject(String sSubject) {
        this.sSubject = sSubject;
        setVerify("Subject", true);
    }
    
    /**
     * Gets the subject
     * @return Return the subject
     */
    public String getSubject() {
        return sSubject;
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
            case MessageBoxQueryReq.TAG_ID:
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
            case MessageBoxQueryReq.TAG_TYPE:
            {             
                // Decodes the type
                if (offset >= length) {
                    throw new ToolException(
                            "MessageBoxQueryReq decode error: no Type in the record");
                }
                nType = byMsg[offset];
                setPresent("Type", true);
                offset++; 
                break;
            }
            case MessageBoxQueryReq.TAG_ISREAD:
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
            case MessageBoxQueryReq.TAG_PRIORITY:
            {             
                // Decodes the type
                if (offset >= length) {
                    throw new ToolException(
                            "MessageBoxQueryReq decode error: no Priority in the record");
                }
                nPriority = byMsg[offset];
                setPresent("Priority", true);
                offset++; 
                break;
            }
            case MessageBoxQueryReq.TAG_DEST_ADDRESS:
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
            case MessageBoxQueryReq.TAG_SUBJECT:
            {             
                // Decodes the length field
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "MessageBoxQueryReq decode error: no Subject in the record");
                }                      
                int nLen = ByteArray.bytesToInt(byMsg, offset);
                offset += 4;
                
                // Decodes the value field
                if ((offset + nLen) > length) {
                    throw new ToolException(
                    "MessageBoxQueryReq decode error: no Subject in the record");                    
                }
                sSubject = new String(byMsg, offset, nLen);
                setPresent("Subject", true);
                offset += nLen;  
                break;
            }
            case MessageBoxQueryReq.TAG_START_TIME:
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
            case MessageBoxQueryReq.TAG_END_TIME:
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
            case MessageBoxQueryReq.TAG_FOLDER_NAME:
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
            case MessageBoxQueryReq.TAG_START_INDEX:
            {
                // Decodes the value filed
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "MessageBoxQueryReq decode error: no StartIndex in the record");
                }                      
                nStartIndex = ByteArray.bytesToInt(byMsg, offset);
                setPresent("StartIndex", true);
                offset += 4;            
                break;
            }
            case MessageBoxQueryReq.TAG_COUNT:
            {
                // Decodes the value filed
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "MessageBoxQueryReq decode error: no Count in the record");
                }                      
                nCount = ByteArray.bytesToInt(byMsg, offset);
                setPresent("Count", true);
                offset += 4;            
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
        
        if (isPresent("ID"))
        {
            // Encodes the ID
            baMsg.append(MessageBoxQueryReq.TAG_ID);
            byte[] byID = new byte[MobileConstants.MAX_ID_LEN];
            GenHelper.copyString(byID, sID);
            baMsg.append(byID);
        }
        
        if (isPresent("Type"))
        {
            // Encodes the type
            baMsg.append(MessageBoxQueryReq.TAG_TYPE);    
            baMsg.append(nType);
        }
        
        if (isPresent("IsRead"))
        {
            // Encodes the IsRead
            baMsg.append(MessageBoxQueryReq.TAG_ISREAD);    
            baMsg.append(nIsRead);
        }
        
        if (isPresent("Priority"))
        {
            // Encodes the Priority
            baMsg.append(MessageBoxQueryReq.TAG_PRIORITY);    
            baMsg.append(nPriority);
        }
        
        if (isPresent("DestAddress"))
        {
            // Encodes the DestAddress
            baMsg.append(MessageBoxQueryReq.TAG_DEST_ADDRESS);
            String sDestAddress = getDestAddress();
            baMsg.append(sDestAddress.getBytes().length);
            baMsg.append(sDestAddress);
        }
        
        if (isPresent("Subject"))
        {
            // Encodes the Subject
            baMsg.append(MessageBoxQueryReq.TAG_SUBJECT);
            baMsg.append(sSubject.getBytes().length);
            baMsg.append(sSubject);
        }
        
        if (isPresent("StartTime"))
        {
            // Encodes the start time
            baMsg.append(MessageBoxQueryReq.TAG_START_TIME);
            byte[] byTime = new byte[MobileConstants.MAX_TIME_LEN];
            GenHelper.copyString(byTime, sStartTime);
            baMsg.append(byTime);
        }
        
        if (isPresent("EndTime"))
        {
            // Encodes the end time
            baMsg.append(MessageBoxQueryReq.TAG_END_TIME);
            byte[] byTime = new byte[MobileConstants.MAX_TIME_LEN];
            GenHelper.copyString(byTime, sEndTime);
            baMsg.append(byTime);
        }
        
        if (isPresent("FolderName"))
        {
            // Encodes the FolderName
            baMsg.append(MessageBoxQueryReq.TAG_FOLDER_NAME);
            baMsg.append(sFolderName.getBytes().length);
            baMsg.append(sFolderName);
        }
        
        if (isPresent("StartIndex"))
        {        
            // Encodes the start index
            baMsg.append(MessageBoxQueryReq.TAG_START_INDEX);
            baMsg.append(nStartIndex);
        }

        if (isPresent("Count"))
        {        
            // Encodes the count
            baMsg.append(MessageBoxQueryReq.TAG_COUNT);
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

        if (isPresent("Type"))
        {
            // Formats the type
            buf.append(sPrefix).
                append("Type = ").
                append(nType).
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
        
        if (isPresent("Priority"))
        {
            // Formats the Priority
            buf.append(sPrefix).
                append("Priority = ").
                append(nPriority).
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
        
        if (isPresent("Subject"))
        {
            // Formats the Subject
            buf.append(sPrefix).
                append("Subject = ").
                append(sSubject).
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

        if (!(msgVerified instanceof MessageBoxQueryReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'MessageBoxQueryReq' message");
        }
        
        MessageBoxQueryReq obj = (MessageBoxQueryReq)msgVerified;
        
        // All fields below is optional, so call doVerifyPresentField
        doVerifyPresentField("ID", sID, obj, obj.sID);
        doVerifyPresentField("Type", nType, obj, obj.nType); 
        doVerifyPresentField("IsRead", nIsRead, obj, obj.nIsRead);
        doVerifyPresentField("Priority", nPriority, obj, obj.nPriority);    
        doVerifyPresentField("DestAddress", aDestAddresses, obj, obj.aDestAddresses, false);  
        doVerifyPresentField("Subject", sSubject, obj, obj.sSubject);      
        doVerifyPresentField("StartTime", sStartTime, obj, obj.sStartTime);       
        doVerifyPresentField("EndTime", sEndTime, obj, obj.sEndTime);
        doVerifyPresentField("FolderName", sFolderName, obj, obj.sFolderName);  
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
        
        if (!(msgVerified instanceof MessageBoxQueryReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'MessageBoxQueryReq' message");
        }

        MessageBoxQueryReq obj = (MessageBoxQueryReq)msgVerified;

        doVerifyPresentField("ID", sID, obj, obj.sID);
        doVerifyPresentField("Type", nType, obj, obj.nType); 
        doVerifyPresentField("IsRead", nIsRead, obj, obj.nIsRead);
        doVerifyPresentField("Priority", nPriority, obj, obj.nPriority);    
        doVerifyPresentField("DestAddress", aDestAddresses, obj, obj.aDestAddresses, false);  
        doVerifyPresentField("Subject", sSubject, obj, obj.sSubject);      
        doVerifyPresentField("StartTime", sStartTime, obj, obj.sStartTime);       
        doVerifyPresentField("EndTime", sEndTime, obj, obj.sEndTime);
        doVerifyPresentField("FolderName", sFolderName, obj, obj.sFolderName);  
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

        if (!(msgVerified instanceof MessageBoxQueryReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'MessageBoxQueryReq' message");
        }

        MessageBoxQueryReq obj = (MessageBoxQueryReq)msgVerified;
        
        doVerifySpecifiedField("ID", sID, obj, obj.sID);
        doVerifySpecifiedField("Type", nType, obj, obj.nType); 
        doVerifySpecifiedField("IsRead", nIsRead, obj, obj.nIsRead);
        doVerifySpecifiedField("Priority", nPriority, obj, obj.nPriority);    
        doVerifySpecifiedField("DestAddress", aDestAddresses, obj, obj.aDestAddresses, false);  
        doVerifySpecifiedField("Subject", sSubject, obj, obj.sSubject);      
        doVerifySpecifiedField("StartTime", sStartTime, obj, obj.sStartTime);       
        doVerifySpecifiedField("EndTime", sEndTime, obj, obj.sEndTime);
        doVerifySpecifiedField("FolderName", sFolderName, obj, obj.sFolderName);  
        doVerifySpecifiedField("StartIndex", nStartIndex, obj, obj.nStartIndex);
        doVerifySpecifiedField("Count", nCount, obj, obj.nCount);

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.FieldMsgBase#createDefaultResponse()
     */
    public MsgBase createDefaultResponse() {
        
        MessageBoxQueryResp oResp = new MessageBoxQueryResp();
        
        oResp.setSequence(nSeqNum);
        
        return oResp;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj)) return false;

        if (!(obj instanceof MessageBoxQueryReq)) {
            return false;
        }

        MessageBoxQueryReq oMsgObj = (MessageBoxQueryReq)obj;
        
        if (isPresent("ID") && oMsgObj.isPresent("ID"))
        {
            if (!oMsgObj.sID.equals(sID))
            {
                return false;
            }
        } else {
            return false;
        }
        
        if (isPresent("Type") && oMsgObj.isPresent("Type"))
        {
            if (!(oMsgObj.nType == nType))
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
        
        if (isPresent("Priority") && oMsgObj.isPresent("Priority"))
        {
            if (!(oMsgObj.nPriority == nPriority))
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
        
        if (isPresent("Subject") && oMsgObj.isPresent("Subject"))
        {
            if (!oMsgObj.sSubject.equals(sSubject))
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
