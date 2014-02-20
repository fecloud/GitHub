/**
 * @(#) MobileMessageInfo.java Created on 2009-2-18
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.element;

import java.util.ArrayList;

import com.aspire.Constants;
import com.aspire.mobile.MobileConstants;
import com.aspire.mobile.msg.MessageBoxQueryReq;
import com.aspire.msg.MsgField;
import com.aspire.util.ByteArray;
import com.aspire.util.GenApi;
import com.aspire.util.GenHelper;
import com.aspire.util.ToolException;

/**
 * The class <code>MobileMessageInfo</code>
 *
 * @author xushengyong
 * @version 1.0
 */
public class MobileMessageInfo extends MobileElementBase {
    
    /**
     * The contact ID
     */
    protected String sID = "";
    
    /**
     * The message type, 0x01:SMS/0x02:MMS/0x03:Email
     */
    protected byte nType = MobileConstants.MESSAGE_TYPE_SMS;
    
    /**
     * The priority, 0x00/0x01
     */
    protected byte nPriority = MobileConstants.MMS_PRI_NORMAL;
    
    /**
     * The time
     */
    protected String sTime = GenApi.getTime();
    
    /**
     * The validity period
     */
    protected int nValidityPeriod = 0;
    
    /**
     * The message type, 0x00:Unreaded/0x01:Readed
     */
    protected byte nIsRead = MobileConstants.FLAG_UNREADED;
    
    /**
     * The source address
     */
    protected String sSourceAddress = "";
    
    /**
     * The callback number
     */
    protected String sCallbackNumber = "";

    /**
     * The destination addresses
     */
    protected ArrayList<String> aDestAddresses = new ArrayList<String>();
    
    /**
     * The cc addresses
     */
    protected ArrayList<String> aCcAddresses = new ArrayList<String>();    
    
    /**
     * The bcc address
     */
    protected ArrayList<String> aBccAddresses = new ArrayList<String>();    
    
    /**
     * The subject
     */
    protected String sSubject = "";
    
    /**
     * The body
     */
    protected String sBody = "";
    
    /**
     * The attachment flag, 0x00:Without/0x01:With
     */
    protected byte nHasAttachment = MobileConstants.FLAG_WITHOUT;
       
    /**
     * The attachment count
     */
    protected int nAttachmentCount = 0;
    
    /**
     * The attachments
     */
    protected ArrayList<MobileMsgAttachment> aAttachments =
        new ArrayList<MobileMsgAttachment>();
    
    /**
     * The constructor
     */
    public MobileMessageInfo() {      
    }
    
    /**
     * Sets the ID
     * @param sID The ID
     */
    public void setID(String sID) {
        this.sID = sID;
        setVerify("ID");
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
     * Sets the time
     * @param sTime The creation date
     */
    public void setTime(String sTime) {
        this.sTime = GenApi.formatDate(sTime);
        setVerify("Time");
    }

    /**
     * Gets the time
     * @return Returns time
     */
    public String getTime() {
        return sTime;
    }
    
    /**
     * Sets the ValidityPeriod
     * @param nValidityPeriod The ValidityPeriod
     * @throws ToolException 
     */    
    public void setValidityPeriod(int nValidityPeriod) throws ToolException {
        this.nValidityPeriod = nValidityPeriod;
        setVerify("ValidityPeriod");
    }
    
    /**
     * Sets the ValidityPeriod
     * @param sValidityPeriod The ValidityPeriod
     * @throws ToolException 
     */
    public void setValidityPeriod(String sValidityPeriod) throws ToolException {
        setValidityPeriod(GenApi.strToInt(sValidityPeriod, "ValidityPeriod"));
    }
    
    /**
     * Gets the ValidityPeriod
     * @return Returns the ValidityPeriod
     */
    public String getValidityPeriod() {
        return Integer.toString(nValidityPeriod);
    }

    /**
     * Gets the ValidityPeriod
     * @return Returns the ValidityPeriod
     */
    public int getValidityPeriodValue() {
        return nValidityPeriod;
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
     * Sets the source address
     * @param sSourceAddress The source address
     */
    public void setSourceAddress(String sSourceAddress) {
        this.sSourceAddress = sSourceAddress;
        setVerify("SourceAddress");
    }
    
    /**
     * Gets the source address
     * @return Return the source address
     */
    public String getSourceAddress() {
        return sSourceAddress;
    }
    
    /**
     * Sets the callback number
     * @param sCallbackNumber The callback number
     */
    public void setCallbackNumber(String sCallbackNumber) {
        this.sCallbackNumber = sCallbackNumber;
        setVerify("CallbackNumber");
    }
    
    /**
     * Gets the callback number
     * @return Return the callback number
     */
    public String getCallbackNumber() {
        return sCallbackNumber;
    }   
    
    /**
     * Sets the destination address
     * @param aDestAddresses The destination addresses
     */
    public void setDestAddress(ArrayList<String> aDestAddresses) {
        this.aDestAddresses = aDestAddresses;
        setVerify("DestAddress");
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
     * Sets the cc address
     * @param aCcAddresses The cc addresses
     */
    public void setCcAddress(ArrayList<String> aCcAddresses) {
        this.aCcAddresses = aCcAddresses;
        setVerify("CcAddress");
    }
    
    /**
     * Sets the cc address
     * @param sCcAddresses The cc addresses
     */
    public void setCcAddress(String sCcAddresses) {
        setCcAddress(GenHelper.parseStrToList(
                sCcAddresses, MobileConstants.SEPARATOR_SEMICOLON));
    }

    /**
     * Gets the cc addresses list
     * @return Return the cc address
     */
    public ArrayList<String> getCcAddressList() {
        return aCcAddresses;
    }
    
    /**
     * Gets the cc address
     * @return Return the cc address
     */
    public String getCcAddress() {
        StringBuffer sCcAddresses = new StringBuffer();
        int i = 0;
        int size = aCcAddresses.size();
        for ( ; i < (size - 1); i++) {
            sCcAddresses.append(aCcAddresses.get(i))
            .append(MobileConstants.SEPARATOR_SEMICOLON);
        }
        if (0 != size) {
            sCcAddresses.append(aCcAddresses.get(i));
        }
        return sCcAddresses.toString();
    }
    
    /**
     * Adds the cc address
     * @param sCcAddress The cc address
     */
    public void addCcAddress(String sCcAddress) {
        if (aCcAddresses.contains(sCcAddress)) return;
        aCcAddresses.add(sCcAddress);
    }  
    
    /**
     * Sets the Bcc address
     * @param aBccAddresses The Bcc addresses
     */
    public void setBccAddress(ArrayList<String> aBccAddresses) {
        this.aBccAddresses = aBccAddresses;
        setVerify("BccAddress");
    }
    
    /**
     * Sets the Bcc address
     * @param sBccAddresses The Bcc addresses
     */
    public void setBccAddress(String sBccAddresses) {
        setBccAddress(GenHelper.parseStrToList(
                sBccAddresses, MobileConstants.SEPARATOR_SEMICOLON));
    }

    /**
     * Gets the Bcc addresses list
     * @return Return the Bcc address
     */
    public ArrayList<String> getBccAddressList() {
        return aBccAddresses;
    }
    
    /**
     * Gets the Bcc address
     * @return Return the Bcc address
     */
    public String getBccAddress() {
        StringBuffer sBccAddresses = new StringBuffer();
        int i = 0;
        int size = aBccAddresses.size();
        for ( ; i < (size - 1); i++) {
            sBccAddresses.append(aBccAddresses.get(i))
            .append(MobileConstants.SEPARATOR_SEMICOLON);
        }
        if (0 != size) {
            sBccAddresses.append(aBccAddresses.get(i));
        }
        return sBccAddresses.toString();
    }
    
    /**
     * Adds the Bcc address
     * @param sBccAddress The Bcc address
     */
    public void addBccAddress(String sBccAddress) {
        if (aBccAddresses.contains(sBccAddress)) return;
        aBccAddresses.add(sBccAddress);
    }   
    
    /**
     * Sets the subject
     * @param sSubject The subject
     */
    public void setSubject(String sSubject) {
        this.sSubject = sSubject;
        setVerify("Subject");
    }
    
    /**
     * Gets the subject
     * @return Return the subject
     */
    public String getSubject() {
        return sSubject;
    }      
    
    /**
     * Sets the body
     * @param sBody The body
     */
    public void setBody(String sBody) {
        this.sBody = sBody;
        setVerify("Body");
    }
    
    /**
     * Gets the body
     * @return Return the body
     */
    public String getBody() {
        return sBody;
    } 
    
    /**
     * Sets the attachment flag
     * @param nHasAttachment The attachment flag
     */
    public void setHasAttachment(byte nHasAttachment) {
        this.nHasAttachment = nHasAttachment;
        setVerify("HasAttachment");
    }

    /**
     * Sets the attachment flag
     * @param sHasAttachment The attachment flag
     * @throws ToolException 
     */
    public void setHasAttachment(String sHasAttachment) throws ToolException {
        setHasAttachment(GenApi.strToByte(sHasAttachment, "HasAttachment"));
    }

    /**
     * Gets the attachment flag
     * @return Returns The attachment flag
     */
    public String getHasAttachment() {
        return Byte.toString(nHasAttachment);
    }

    /**
     * Gets the attachment flag
     * @return Returns the attachment flag
     */
    public byte getHasAttachmentValue() {
        return nHasAttachment;
    }
    
    /**
     * Sets the AttachmentCount
     * @param nAttachmentCount The AttachmentCount
     * @throws ToolException 
     */    
    public void setAttachmentCount(int nAttachmentCount) throws ToolException {
        this.nAttachmentCount = nAttachmentCount;
        setVerify("AttachmentCount");
    }
    
    /**
     * Sets the AttachmentCount
     * @param sAttachmentCount The AttachmentCount
     * @throws ToolException 
     */
    public void setAttachmentCount(String sAttachmentCount) throws ToolException {
        setAttachmentCount(GenApi.strToInt(sAttachmentCount, "AttachmentCount"));
    }
    
    /**
     * Gets the AttachmentCount
     * @return Returns the AttachmentCount
     */
    public String getAttachmentCount() {
        return Integer.toString(nAttachmentCount);
    }

    /**
     * Gets the AttachmentCount
     * @return Returns the AttachmentCount
     */
    public int getAttachmentCountValue() {
        return nAttachmentCount;
    }
    
    /**
     * Sets the attachments
     * @param aAttachments The the attachments
     */
    public void setAttachment(ArrayList<MobileMsgAttachment> aAttachments) {
        this.aAttachments = aAttachments;
        setVerify("Attachment");
    }

    /**
     * Gets the attachments
     * @return Returns the attachments
     */
    public ArrayList<MobileMsgAttachment> getAttachment() {
        return aAttachments;
    }
    
    /**
     * Adds the attachments
     * @param oAttachment The the attachments object to be added
     */
    public void addAttachment(MobileMsgAttachment oAttachment) {
        if (aAttachments.contains(oAttachment)) return;
        aAttachments.add(oAttachment);
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
                "MobileMessageInfo decode error: no ID in the record");
        }
        sID = new String(byMsg, offset,
                MobileConstants.MAX_ID_LEN).trim();
        setPresent("ID", true);
        offset += MobileConstants.MAX_ID_LEN;
        
        // Decodes the type
        if (offset >= length) {
            throw new ToolException(
                    "MobileMessageInfo decode error: no Type in the record");
        }
        nType = byMsg[offset];
        setPresent("Type", true);
        offset++;
        
        // Decodes the time
        if ((offset + MobileConstants.MAX_TIME_LEN) > length) {
            throw new ToolException(
                "MobileMessageInfo decode error: no Time in the record");
        }
        sTime = new String(byMsg, offset,
                MobileConstants.MAX_TIME_LEN).trim();
        setPresent("Time", true);
        offset += MobileConstants.MAX_TIME_LEN;
        
        // Decodes the IsRead
        if (offset >= length) {
            throw new ToolException(
                    "MobileMessageInfo decode error: no IsRead in the record");
        }
        nIsRead = byMsg[offset];
        setPresent("IsRead", true);
        offset++;
        
        while (offset < length) {
            // Decodes the tag field
            byte nTag = byMsg[offset];
            offset++;
            
            switch (nTag) {
            case MessageBoxQueryReq.TAG_PRIORITY:
            {             
                // Decodes the Priority
                if (offset >= length) {
                    throw new ToolException(
                            "MobileMessageInfo decode error: no Priority in the record");
                }
                setPriority(byMsg[offset]);
                offset++; 
                break;
            }
            case MessageBoxQueryReq.TAG_VALIDITY_PERIOD:
            {             
                // Decodes the ValidityPeriod
                if (offset >= length) {
                    throw new ToolException(
                            "MobileMessageInfo decode error: no ValidityPeriod in the record");
                }
                setValidityPeriod(ByteArray.bytesToInt(byMsg, offset));
                offset += 4; 
                break;
            }
            case MessageBoxQueryReq.TAG_SOURCE_ADDRESS:
            {             
                // Decodes the SourceAddress field
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "MobileMessageInfo decode error: no SourceAddress in the record");
                }                      
                nLen = ByteArray.bytesToInt(byMsg, offset);
                offset += 4;
                
                // Decodes the value field
                if ((offset + nLen) > length) {
                    throw new ToolException(
                    "MobileMessageInfo decode error: no SourceAddress in the record");                    
                }
                setSourceAddress(new String(byMsg, offset, nLen));
                offset += nLen;  
                break;
            }
            case MessageBoxQueryReq.TAG_CALLBACK_NUMBER:
            {             
                // Decodes the CallbackNumber field
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "MobileMessageInfo decode error: no CallbackNumber in the record");
                }                      
                nLen = ByteArray.bytesToInt(byMsg, offset);
                offset += 4;
                
                // Decodes the value field
                if ((offset + nLen) > length) {
                    throw new ToolException(
                    "MobileMessageInfo decode error: no CallbackNumber in the record");                    
                }
                setCallbackNumber(new String(byMsg, offset, nLen));
                offset += nLen;  
                break;
            }
            case MessageBoxQueryReq.TAG_DEST_ADDRESS:
            {             
                // Decodes the DestAddress field
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "MobileMessageInfo decode error: no DestAddress in the record");
                }                      
                nLen = ByteArray.bytesToInt(byMsg, offset);
                offset += 4;
                
                // Decodes the value field
                if ((offset + nLen) > length) {
                    throw new ToolException(
                    "MobileMessageInfo decode error: no DestAddress in the record");                    
                }
                setDestAddress(new String(byMsg, offset, nLen));
                offset += nLen;  
                break;
            }
            case MessageBoxQueryReq.TAG_CC_ADDRESS:
            {             
                // Decodes the CcAddress field
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "MobileMessageInfo decode error: no CcAddress in the record");
                }                      
                nLen = ByteArray.bytesToInt(byMsg, offset);
                offset += 4;
                
                // Decodes the value field
                if ((offset + nLen) > length) {
                    throw new ToolException(
                    "MobileMessageInfo decode error: no CcAddress in the record");                    
                }
                setCcAddress(new String(byMsg, offset, nLen));
                offset += nLen;  
                break;
            }
            case MessageBoxQueryReq.TAG_BCC_ADDRESS:
            {             
                // Decodes the BccAddress field
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "MobileMessageInfo decode error: no BccAddress in the record");
                }                      
                nLen = ByteArray.bytesToInt(byMsg, offset);
                offset += 4;
                
                // Decodes the value field
                if ((offset + nLen) > length) {
                    throw new ToolException(
                    "MobileMessageInfo decode error: no BccAddress in the record");                    
                }
                setBccAddress(new String(byMsg, offset, nLen));
                offset += nLen;  
                break;
            }
            case MessageBoxQueryReq.TAG_SUBJECT:
            {             
                // Decodes the length field
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "MobileMessageInfo decode error: no Subject in the record");
                }                      
                nLen = ByteArray.bytesToInt(byMsg, offset);
                offset += 4;
                
                // Decodes the value field
                if ((offset + nLen) > length) {
                    throw new ToolException(
                    "MobileMessageInfo decode error: no Subject in the record");                    
                }
                setSubject(new String(byMsg, offset, nLen));
                offset += nLen;  
                break;
            }
            case MessageBoxQueryReq.TAG_BODY:
            {             
                // Decodes the Body field
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "MobileMessageInfo decode error: no Body in the record");
                }                      
                nLen = ByteArray.bytesToInt(byMsg, offset);
                offset += 4;
                
                // Decodes the value field
                if ((offset + nLen) > length) {
                    throw new ToolException(
                    "MobileMessageInfo decode error: no Body in the record");                    
                }
                setBody(new String(byMsg, offset, nLen));
                offset += nLen;  
                break;
            }
            case MessageBoxQueryReq.TAG_HAS_ATTACHMENT:
            {
                setHasAttachment(byMsg[offset]);
                offset++;
                
                if (MobileConstants.FLAG_WITH == nHasAttachment) {

                    // Decodes the AttachmentCount
                    if ((offset + 4) > length) {
                        throw new ToolException(
                        "MobileMessageInfo decode error: no AttachmentCount in the record");
                    }  
                    setAttachmentCount(ByteArray.bytesToInt(byMsg, offset));
                    offset += 4;
                    
                    // Decodes the MobileMsgAttachment
                    for (int i = 0; i < nAttachmentCount; i++) {
                        if (offset >= length) {
                            break;
                        }
                        MobileMsgAttachment oAttachment = new MobileMsgAttachment();
                        offset += oAttachment.decode(byMsg, offset, length);
                        aAttachments.add(oAttachment);
                    }
                    setVerify("Attachment", nAttachmentCount > 0);
                    
                }
                break;
            }
            default:
            {
                throw new ToolException(
                        "MobileMessageInfo decode error: unkown tag type: " + nTag);
            }
            }     
        } // End of while (offset < length)
        
        return offset - start;
    }

    /**
     * Encodes the MobileMessageInfo object into a buffer
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
        
        // Encodes the Type
        baMsg.append(nType);
            
        // Encodes the Time
        byte[] byTime = new byte[MobileConstants.MAX_TIME_LEN];
        GenHelper.copyString(byTime, sTime);
        baMsg.append(byTime);
     
        // Encodes the IsRead
        baMsg.append(nIsRead);
        
        if (isPresent("Priority")) {
            // Encodes the Priority
            baMsg.append(MessageBoxQueryReq.TAG_PRIORITY);    
            baMsg.append(nPriority);
        }
        
        if (isPresent("ValidityPeriod")) {
            // Encodes the ValidityPeriod
            baMsg.append(MessageBoxQueryReq.TAG_VALIDITY_PERIOD);  
            baMsg.append(nValidityPeriod);
        }
        
        if (isPresent("SourceAddress")) {
            // Encodes the SourceAddress
            baMsg.append(MessageBoxQueryReq.TAG_SOURCE_ADDRESS); 
            baMsg.append(sSourceAddress.getBytes().length); 
            baMsg.append(sSourceAddress);
        }
             
        if (isPresent("CallbackNumber")) {
            // Encodes the CallbackNumber
            baMsg.append(MessageBoxQueryReq.TAG_CALLBACK_NUMBER); 
            baMsg.append(sCallbackNumber.getBytes().length); 
            baMsg.append(sCallbackNumber);
        }
             
        if (isPresent("DestAddress")) {
            // Encodes the DestAddress
            baMsg.append(MessageBoxQueryReq.TAG_DEST_ADDRESS); 
            String sDestAddress = getDestAddress();        
            baMsg.append(sDestAddress.getBytes().length); 
            baMsg.append(sDestAddress);
        }
        
        if (isPresent("CcAddress")) {
            // Encodes the CcAddress
            baMsg.append(MessageBoxQueryReq.TAG_CC_ADDRESS);
            String sCcAddress = getCcAddress();
            baMsg.append(sCcAddress.getBytes().length); 
            baMsg.append(sCcAddress);
        }

        if (isPresent("BccAddress")) {
            // Encodes the BccAddress
            baMsg.append(MessageBoxQueryReq.TAG_BCC_ADDRESS);
            String sBccAddress = getBccAddress();        
            baMsg.append(sBccAddress.getBytes().length); 
            baMsg.append(sBccAddress);            
        }

        if (isPresent("Subject")) {       
            // Encodes the Subject
            baMsg.append(MessageBoxQueryReq.TAG_SUBJECT);
            baMsg.append(sSubject.getBytes().length); 
            baMsg.append(sSubject);
        }

        if (isPresent("Body")) {
            // Encodes the Body
            baMsg.append(MessageBoxQueryReq.TAG_BODY);
            baMsg.append(sBody.getBytes().length); 
            baMsg.append(sBody); 
        }           
        
        if (isPresent("HasAttachment")) {
            // Encodes the HasAttachment
            baMsg.append(MessageBoxQueryReq.TAG_HAS_ATTACHMENT);
            baMsg.append(nHasAttachment);
            
            if (MobileConstants.FLAG_WITH == nHasAttachment) {               
                // Encodes the AttachmentCount
                baMsg.append(aAttachments.size());
                
                // Encodes the MobileMsgAttachment
                for (int i = 0; i < aAttachments.size(); i++) {
                    aAttachments.get(i).encode(baMsg);
                }
            }
        }
        
        return baMsg.length() - nLen;
    }

    /**
     * Formats the MobileMessageInfo object to a readable string
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
        
        // Formats the Type
        buf.append(sPrefix).
            append("Type = ").
            append(nType).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the Priority
        buf.append(sPrefix).
            append("Priority = ").
            append(nPriority).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the time
        buf.append(sPrefix).
            append("Time = ").
            append(sTime).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the ValidityPeriod
        buf.append(sPrefix).
            append("ValidityPeriod = ").
            append(nValidityPeriod).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the IsRead
        buf.append(sPrefix).
            append("IsRead = ").
            append(nIsRead).
            append(Constants.LINE_SEPARATOR);
       
        // Formats the SourceAddress
        buf.append(sPrefix).
            append("SourceAddress = ").
            append(sSourceAddress).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the CallbackNumber
        buf.append(sPrefix).
            append("CallbackNumber = ").
            append(sCallbackNumber).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the DestAddress
        buf.append(sPrefix).
            append("DestAddress = ").
            append(getDestAddress()).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the CcAddress
        buf.append(sPrefix).
            append("CcAddress = ").
            append(getCcAddress()).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the BccAddress
        buf.append(sPrefix).
            append("BccAddress = ").
            append(getBccAddress()).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the Subject
        buf.append(sPrefix).
            append("Subject = ").
            append(sSubject).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the Body
        buf.append(sPrefix).
            append("Body = ").
            append(sBody).
            append(Constants.LINE_SEPARATOR);
      
        // Formats the HasAttachment
        buf.append(sPrefix).
            append("HasAttachment = ").
            append(nHasAttachment).
            append(Constants.LINE_SEPARATOR);
        
        if (MobileConstants.FLAG_WITH == nHasAttachment) {
            
            // Formats the AttachmentCount
            buf.append(sPrefix).
                append("AttachmentCount = ").
                append(nAttachmentCount).
                append(Constants.LINE_SEPARATOR);
            
            // Formats the MobileMsgAttachment
            String sInfoPrefix = sPrefix + "    ";
            for (int i = 0; i < aAttachments.size(); i++) {
                buf.append(sPrefix).
                    append("Attachment[No ").
                    append(i).
                    append("]").
                    append(Constants.LINE_SEPARATOR).
                    append(sPrefix).
                    append("{").
                    append(Constants.LINE_SEPARATOR);
                aAttachments.get(i).format(buf, sInfoPrefix);
                buf.append(sPrefix).
                    append("}").
                    append(Constants.LINE_SEPARATOR);
            }
        }
        
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
                "Message verify error: the field 'MobileMessageInfo' is null");
        }

        if (!(field instanceof MobileMessageInfo)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MobileMessageInfo' object");
        }

        MobileMessageInfo obj = (MobileMessageInfo)field;
        
        doVerifyField("ID", sID, obj, obj.sID);
        doVerifyField("Type", nType, obj, obj.nType);
        doVerifyField("Priority", nPriority, obj, obj.nPriority);
        doVerifyField("Time", sTime, obj, obj.sTime); 
        doVerifyField("ValidityPeriod", nValidityPeriod, obj, obj.nValidityPeriod);
        doVerifyField("IsRead", nIsRead, obj, obj.nIsRead);
        doVerifyField("SourceAddress", sSourceAddress, obj, obj.sSourceAddress);    
        doVerifyField("CallbackNumber", sCallbackNumber, obj, obj.sCallbackNumber);       
        doVerifyField("DestAddress", aDestAddresses, obj, obj.aDestAddresses, false);
        doVerifyField("CcAddress", aCcAddresses, obj, obj.aCcAddresses, false);       
        doVerifyField("BccAddress", aBccAddresses, obj, obj.aBccAddresses, false);
        doVerifyField("Subject", sSubject, obj, obj.sSubject);       
        doVerifyField("Body", sBody, obj, obj.sBody);     
        doVerifyField("HasAttachment", nHasAttachment, obj, obj.nHasAttachment);
        
        if (MobileConstants.FLAG_WITH == nHasAttachment) { 
            doVerifyField("AttachmentCount", nAttachmentCount, obj, obj.nAttachmentCount);
            doVerifyField("Attachment", aAttachments, obj, obj.aAttachments, false);
        }
        
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
                "Message verify error: the field 'MobileMessageInfo' is null");
        }

        if (!(field instanceof MobileMessageInfo)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MobileMessageInfo' object");
        }
        
        MobileMessageInfo obj = (MobileMessageInfo)field;
        
        doVerifyPresentField("ID", sID, obj, obj.sID);
        doVerifyPresentField("Type", nType, obj, obj.nType);
        doVerifyPresentField("Priority", nPriority, obj, obj.nPriority);
        doVerifyPresentField("Time", sTime, obj, obj.sTime); 
        doVerifyPresentField("ValidityPeriod", nValidityPeriod, obj, obj.nValidityPeriod);
        doVerifyPresentField("IsRead", nIsRead, obj, obj.nIsRead);
        doVerifyPresentField("SourceAddress", sSourceAddress, obj, obj.sSourceAddress);    
        doVerifyPresentField("CallbackNumber", sCallbackNumber, obj, obj.sCallbackNumber);       
        doVerifyPresentField("DestAddress", aDestAddresses, obj, obj.aDestAddresses, false);
        doVerifyPresentField("CcAddress", aCcAddresses, obj, obj.aCcAddresses, false);       
        doVerifyPresentField("BccAddress", aBccAddresses, obj, obj.aBccAddresses, false);
        doVerifyPresentField("Subject", sSubject, obj, obj.sSubject);       
        doVerifyPresentField("Body", sBody, obj, obj.sBody);     
        doVerifyPresentField("HasAttachment", nHasAttachment, obj, obj.nHasAttachment);
        
        if (MobileConstants.FLAG_WITH == nHasAttachment) { 
            doVerifyPresentField("AttachmentCount", nAttachmentCount, obj, obj.nAttachmentCount);
            doVerifyPresentField("Attachment", aAttachments, obj, obj.aAttachments, false);
        }
        
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
                "Message verify error: the field 'MobileMessageInfo' is null");
        }

        if (!(field instanceof MobileMessageInfo)) {
            throw new ToolException(
                "Message verify error: the field is not a 'MobileMessageInfo' object");
        }

        MobileMessageInfo obj = (MobileMessageInfo)field;
        
        doVerifySpecifiedField("ID", sID, obj, obj.sID);
        doVerifySpecifiedField("Type", nType, obj, obj.nType);
        doVerifySpecifiedField("Priority", nPriority, obj, obj.nPriority);
        doVerifySpecifiedField("Time", sTime, obj, obj.sTime); 
        doVerifySpecifiedField("ValidityPeriod", nValidityPeriod, obj, obj.nValidityPeriod);
        doVerifySpecifiedField("IsRead", nIsRead, obj, obj.nIsRead);
        doVerifySpecifiedField("SourceAddress", sSourceAddress, obj, obj.sSourceAddress);    
        doVerifySpecifiedField("CallbackNumber", sCallbackNumber, obj, obj.sCallbackNumber);       
        doVerifySpecifiedField("DestAddress", aDestAddresses, obj, obj.aDestAddresses, false);
        doVerifySpecifiedField("CcAddress", aCcAddresses, obj, obj.aCcAddresses, false);       
        doVerifySpecifiedField("BccAddress", aBccAddresses, obj, obj.aBccAddresses, false);
        doVerifySpecifiedField("Subject", sSubject, obj, obj.sSubject);       
        doVerifySpecifiedField("Body", sBody, obj, obj.sBody);     
        doVerifySpecifiedField("HasAttachment", nHasAttachment, obj, obj.nHasAttachment);
        
        if (MobileConstants.FLAG_WITH == nHasAttachment) { 
            doVerifySpecifiedField("AttachmentCount", nAttachmentCount, obj, obj.nAttachmentCount);
            doVerifySpecifiedField("Attachment", aAttachments, obj, obj.aAttachments, false);
        }
        
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || !(obj instanceof MobileMessageInfo)) {
            return false;
        }
        
        MobileMessageInfo oMsgField = (MobileMessageInfo)obj;
        
        boolean bRet = oMsgField.sID.equals(sID) && 
        oMsgField.nType == nType && 
        oMsgField.nPriority == nPriority && 
        oMsgField.sTime.equals(sTime) && 
        oMsgField.nValidityPeriod == nValidityPeriod && 
        oMsgField.nIsRead == nIsRead && 
        oMsgField.sSourceAddress.equals(sSourceAddress) &&
        oMsgField.sCallbackNumber.equals(sCallbackNumber) &&
        oMsgField.aDestAddresses.equals(aDestAddresses) &&
        oMsgField.aCcAddresses.equals(aCcAddresses) &&       
        oMsgField.aBccAddresses.equals(aBccAddresses) &&
        oMsgField.sSubject.equals(sSubject) &&
        oMsgField.sBody.equals(sBody) &&    
        oMsgField.nHasAttachment == nHasAttachment;
      
        if (bRet && (MobileConstants.FLAG_WITH == nHasAttachment)) {
            bRet = bRet && oMsgField.nAttachmentCount == nAttachmentCount
                    && oMsgField.aAttachments.equals(aAttachments);
        }
        
        return bRet;
    }
    
    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#isPresent(java.lang.String)
     */
    public boolean isPresent(String field) {
        return buildFields.contains(field);
    }
    
    /**
     * This method is used to clone a MobileMessageInfo object
     */
    public Object clone() throws CloneNotSupportedException {
        
        MobileMessageInfo obj = (MobileMessageInfo)super.clone();
        
        obj.aAttachments = new ArrayList<MobileMsgAttachment>();
        
        for (int i = 0; i < aAttachments.size(); i++) {
            obj.aAttachments.add((MobileMsgAttachment)aAttachments.get(i).clone());
        }
        
        return obj;
    }
    
}
