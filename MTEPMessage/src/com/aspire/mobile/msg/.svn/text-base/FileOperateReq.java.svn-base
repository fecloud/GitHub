/**
 * @(#) FileOperateReq.java Created on 2009-2-18
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.msg;

import com.aspire.Constants;
import com.aspire.mobile.MobileConstants;
import com.aspire.msg.MsgBase;
import com.aspire.util.ByteArray;
import com.aspire.util.GenApi;
import com.aspire.util.ToolException;

/**
 * The class <code>FileOperateReq</code>
 *
 * @author xushengyong
 * @version 1.0
 */
public class FileOperateReq extends MobileMsgBase {
    
    /**
     * The action type, 0x01:Create/0x02:Delete
     */
    protected byte nAction = MobileConstants.FILE_OPERATION_TYPE_CREATE;
    
    /**
     * The path
     */
    protected String sPath = "";
    
    /**
     * The file size
     */
    protected long nFileSize = 0;
    
    /**
     * The Permission field
     */
    protected String sPermission = "";
    
    /**
     * The file data
     */
    protected String sFileData = "";
    
    /**
     * The constructor
     */
    public FileOperateReq() {
        super(MobileConstants.FILE_OPERATE_REQ, "FileOperateReq");
    }
    
    /**
     * Sets the action type
     * @param nAction The action type
     */
    public void setAction(byte nAction) {
        this.nAction = nAction;
        setVerify("Action");
    }

    /**
     * Sets the action type
     * @param sType The action type
     * @throws ToolException 
     */
    public void setAction(String sAction) throws ToolException {
        setAction(GenApi.strToByte(sAction, "Action"));
    }

    /**
     * Gets the action type
     * @return Returns The action type
     */
    public String getAction() {
        return Byte.toString(nAction);
    }

    /**
     * Gets the action type
     * @return Returns the action type
     */
    public byte getActionValue() {
        return nAction;
    }  
    
    /**
     * Sets the path
     * @param sPath The path name
     */
    public void setPath(String sPath) {
        this.sPath = sPath;
        setVerify("Path", true);
    }

    /**
     * Gets the path name
     * @return Return the path name
     */
    public String getPath() {
        return sPath;
    }
    
    /**
     * Sets the file size
     * @param nFileSize The file size
     */
    public void setFileSize(long nFileSize) {
        this.nFileSize = nFileSize;
        setVerify("FileSize");
    }

    /**
     * Sets the file size
     * @param sFileSize The file size
     */
    public void setFileSize(String sFileSize) throws ToolException {
        setFileSize(GenApi.strToLong(sFileSize, "FileSize"));
    }

    /**
     * Gets the file size
     * @return Returns file size
     */
    public String getFileSize() {
        return Long.toString(nFileSize);
    }

    /**
     * Gets file size
     * @return Returns file size
     */
    public long getFileSizeValue() {
        return nFileSize;
    }
    
    /**
     * Sets the Permission string
     * @param sPermission The path name
     */
    public void setPermission(String sPermission) {
        this.sPermission = sPermission;
        setVerify("Permission", true);
    }

    /**
     * Gets the Permission string
     * @return Return the Permission string
     */
    public String getPermission() {
        return sPermission;
    }
    
    /**
     * Sets the file data
     * @param sFileData The file data
     */
    public void setFileData(String sFileData) {
        this.sFileData = sFileData;
        setVerify("FileData", true);
    }

    /**
     * Gets the file data
     * @return Return the file data
     */
    public String getFileData() {
        return sFileData;
    }
    
    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#decode(byte[], int, int)
     */
    public int decode(byte[] byMsg, int start, int length) throws ToolException {
        
        int nRead = super.decode(byMsg, start, length);
        int offset = start + nRead;
        
        // Decodes the Action
        if (offset >= length) {
            throw new ToolException(
                    "FileOperateReq decode error: no Action in the record");
        }
        nAction = byMsg[offset];
        offset++; 
        setPresent("Action", true);
        
        // Decodes the Path length field
        if ((offset + 4) > length) {
            throw new ToolException(
            "FileOperateReq decode error: no Path in the record");
        }                      
        int nLen = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;
        
        // Decodes the Path value field
        if ((offset + nLen) > length) {
            throw new ToolException(
            "FileOperateReq decode error: no Path in the record");                    
        }
        sPath = new String(byMsg, offset, nLen);
        offset += nLen;
        setPresent("Path", true);
        
        if (MobileConstants.FILE_OPERATION_TYPE_CREATE == nAction) {
            
            // Decodes the file size
            if ((offset + 8) > length) {
                throw new ToolException(
                "FileOperateReq decode error: no FileSize in the record");
            } 
            nFileSize = ByteArray.bytesToLong(byMsg, offset);
            offset += 8;
            setPresent("FileSize", true);
            
            // Decodes the Permission length field
            if ((offset + 4) > length) {
                throw new ToolException(
                "FileOperateReq decode error: no Permission specified");
            }                      
            nLen = ByteArray.bytesToInt(byMsg, offset);
            offset += 4;
            
            // Decodes the Permission value field
            if ((offset + nLen) > length) {
                throw new ToolException(
                "FileOperateReq decode error: no Permission specified");                    
            }
            sPermission = new String(byMsg, offset, nLen);
            offset += nLen;
            setPresent("Permission", true);
            
            if (-1 == nFileSize) {           
                // Decodes the FileData length field
                if ((offset + 4) > length) {
                    throw new ToolException(
                    "FileOperateReq decode error: no FileData in the record");
                }                      
                nLen = ByteArray.bytesToInt(byMsg, offset);
                offset += 4;
                
                // Decodes the FileData value field
                if ((offset + nLen) > length) {
                    throw new ToolException(
                    "FileOperateReq decode error: no FileData in the record");                    
                }
                sFileData = new String(byMsg, offset, nLen);
                setPresent("FileData", true);
                offset += nLen;
            }    
        }

        return offset - start;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#encode(com.aspire.util.ByteArray)
     */
    public int encode(ByteArray baMsg) throws ToolException {

        int nLen = baMsg.length();

        super.encode(baMsg);

        // Encodes the Action
        baMsg.append(nAction);
        
        // Encodes the Path
        baMsg.append(sPath.getBytes().length);        
        baMsg.append(sPath);
        
        if (MobileConstants.FILE_OPERATION_TYPE_CREATE == nAction) {
            
            // Encodes the file size
            baMsg.append(nFileSize);
            
            // Encode Permission field
            baMsg.append(sPermission.getBytes().length);        
            baMsg.append(sPermission);
            
            if (-1 == nFileSize) { 
                
                // Encodes the FileData
                baMsg.append(sFileData.getBytes().length);        
                baMsg.append(sFileData); 
                
            }    
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
        
        // Format the Action
        buf.append(sPrefix).
            append("Action = ").
            append(nAction).
            append(Constants.LINE_SEPARATOR);

        // Format the path
        buf.append(sPrefix).
            append("Path = ").
            append(sPath).
            append(Constants.LINE_SEPARATOR);
        
        if (MobileConstants.FILE_OPERATION_TYPE_CREATE == nAction) {
            
            // Format the FileSize
            buf.append(sPrefix).
                append("FileSize = ").
                append(nFileSize).
                append(Constants.LINE_SEPARATOR);

            // Format the Permission
            buf.append(sPrefix).
                append("Permission = ").
                append(sPermission).
                append(Constants.LINE_SEPARATOR);
            
            if (-1 == nFileSize) { 
                
                // Format the FileData
                buf.append(sPrefix).
                    append("FileData = ").
                    append(sFileData).
                    append(Constants.LINE_SEPARATOR);
                
            }    
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

        if (!(msgVerified instanceof FileOperateReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'FileOperateReq' message");
        }

        FileOperateReq obj = (FileOperateReq)msgVerified;

        doVerifyField("Action", nAction, obj, obj.nAction);
        doVerifyField("Path", sPath, obj, obj.sPath);
        if (MobileConstants.FILE_OPERATION_TYPE_CREATE == nAction) {

            doVerifyField("FileSize", nFileSize, obj, obj.nFileSize);
            doVerifyField("Permission", sPermission, obj, obj.sPermission);
            
            if (-1 == nFileSize) {   
                
                doVerifyField("FileData", sFileData, obj, obj.sFileData);
                
            }    
        }
        
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
        
        if (!(msgVerified instanceof FileOperateReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'FileOperateReq' message");
        }

        FileOperateReq obj = (FileOperateReq)msgVerified;

        doVerifyPresentField("Action", nAction, obj, obj.nAction);
        doVerifyPresentField("Path", sPath, obj, obj.sPath);
        if (MobileConstants.FILE_OPERATION_TYPE_CREATE == nAction) {

            doVerifyPresentField("FileSize", nFileSize, obj, obj.nFileSize);  
            doVerifyPresentField("Permission", sPermission, obj, obj.sPermission);
            
            if (-1 == nFileSize) {   
                
                doVerifyPresentField("FileData", sFileData, obj, obj.sFileData);
                
            }    
        }

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

        if (!(msgVerified instanceof FileOperateReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'FileOperateReq' message");
        }

        FileOperateReq obj = (FileOperateReq)msgVerified;

        doVerifySpecifiedField("Action", nAction, obj, obj.nAction);
        doVerifySpecifiedField("Path", sPath, obj, obj.sPath);
        if (MobileConstants.FILE_OPERATION_TYPE_CREATE == nAction) {

            doVerifySpecifiedField("FileSize", nFileSize, obj, obj.nFileSize);
            doVerifySpecifiedField("Permission", sPermission, obj, obj.sPermission);  
            
            if (-1 == nFileSize) {   
                
                doVerifySpecifiedField("FileData", sFileData, obj, obj.sFileData);
                
            }    
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.FieldMsgBase#createDefaultResponse()
     */
    public MsgBase createDefaultResponse() {
        
        FileOperateResp oResp = new FileOperateResp();
        
        oResp.setSequence(nSeqNum);
        
        return oResp;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj)) return false;

        if (!(obj instanceof FileOperateReq)) {
            return false;
        }

        FileOperateReq oMsgObj = (FileOperateReq)obj;
        
        boolean bRet = (oMsgObj.nAction == nAction) && 
            oMsgObj.sPath.equals(sPath);
        
        if (MobileConstants.FILE_OPERATION_TYPE_CREATE == nAction) {

            bRet = bRet && (oMsgObj.nFileSize == nFileSize)
                        && oMsgObj.sPermission.equals(sPermission); 
            
            if (-1 == nFileSize) {   
                
                bRet = bRet && oMsgObj.sFileData.equals(sFileData); 
               
            }    
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

}
