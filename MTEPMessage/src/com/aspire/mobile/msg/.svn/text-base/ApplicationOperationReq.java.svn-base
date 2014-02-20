/**
 * @(#) ApplicationOperationReq.java Created on 2012-5-8
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.mobile.msg;

import com.aspire.Constants;
import com.aspire.mobile.MobileConstants;
import com.aspire.msg.MsgBase;
import com.aspire.util.ByteArray;
import com.aspire.util.GenApi;
import com.aspire.util.ToolException;

/**
 * 
 * The class <code>ApplicationOperationReq</code>
 * 
 * @author zhenghui
 * @version 1.0
 */
public class ApplicationOperationReq extends MobileMsgBase {

    /**
     * The action type, 0x01:start wap
     */
    protected byte nAction = MobileConstants.APP_OPERATION_ACTION_START;

    /**
     * The path field
     */
    protected String path = "";

    /**
     * The packageName field
     */
    protected String packageName = "";

    /**
     * The className field
     */
    protected String className = "";

    /**
     * The constructor
     */
    public ApplicationOperationReq() {
        super(MobileConstants.APP_OPERATION_REQ, "ApplicationOperationReq");
    }

    /**
     * Sets the action type
     * 
     * @param nAction
     *            The action type
     */
    public void setAction(byte nAction) {
        this.nAction = nAction;
        setVerify("Action");
    }

    /**
     * Sets the action type
     * 
     * @param sType
     *            The action type
     * @throws ToolException
     */
    public void setAction(String sAction) throws ToolException {
        setAction(GenApi.strToByte(sAction, "Action"));
    }

    /**
     * Gets the action type
     * 
     * @return Returns The action type
     */
    public String getAction() {
        return Byte.toString(nAction);
    }

    /**
     * Gets the action type
     * 
     * @return Returns the action type
     */
    public byte getActionValue() {
        return nAction;
    }

    /**
     * set the Path value
     * 
     * @param Path
     */
    public void setPath(String path) {
        this.path = path;
        setVerify("Path", true);
    }

    /**
     * Gets the Path value
     * 
     * @return Path
     */
    public String getPath() {
        return path;
    }

    /**
     * set the Path value
     * 
     * @param Path
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
        setVerify("PackageName", true);
    }

    /**
     * Gets the PackageName value
     * 
     * @return PackageName
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Gets the className value
     * 
     * @return className
     */
    public String getClassName() {
        return className;
    }

    /**
     * set the className value
     * 
     * @param className
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.mobile.msg.MobileMsgBase#decode(byte[], int, int)
     */
    public int decode(byte[] byMsg, int start, int length) throws ToolException {

        int nRead = super.decode(byMsg, start, length);
        int offset = start + nRead;

        // Decodes the Action
        if (offset >= length) {
            throw new ToolException("CallOperationReq decode error: no Action in the record");
        }
        nAction = byMsg[offset];
        offset++;

        if (MobileConstants.APP_OPERATION_ACTION_INSTALL == nAction) {
            // Decodes the Data length field
            if ((offset + 4) > length) {
                throw new ToolException("ApplicationOperationReq decode error: no PATH in the record");
            }
            int nLen = ByteArray.bytesToInt(byMsg, offset);
            offset += 4;

            // Decodes the Data value field
            if ((offset + nLen) > length) {
                throw new ToolException("ApplicationOperationReq decode error: no PATH in the record");
            }
            path = new String(byMsg, offset, nLen);
            offset += nLen;
            setPresent("Path", true);
        } else if (MobileConstants.APP_OPERATION_ACTION_STARTCLASS == nAction
                || MobileConstants.APP_OPERATION_ACTION_STOPCLASS == nAction) {
            if ((offset + 4) > length) {
                throw new ToolException("ApplicationOperationReq decode error: no packageName in the record");
            }
            int nLen = ByteArray.bytesToInt(byMsg, offset);
            offset += 4;

            // Decodes the Data value field
            if ((offset + nLen) > length) {
                throw new ToolException("ApplicationOperationReq decode error: no packageName in the record");
            }
            packageName = new String(byMsg, offset, nLen);
            offset += nLen;
            setPresent("PackageName", true);

            if ((offset + 4) > length) {
                throw new ToolException("ApplicationOperationReq decode error: no packageName in the record");
            }
            nLen = ByteArray.bytesToInt(byMsg, offset);
            offset += 4;

            // Decodes the Data value field
            if ((offset + nLen) > length) {
                throw new ToolException("ApplicationOperationReq decode error: no className in the record");
            }
            className = new String(byMsg, offset, nLen);
            offset += nLen;
            setPresent("ClassName", true);
        } else {
            // Decodes the Data length field
            if ((offset + 4) > length) {
                throw new ToolException("ApplicationOperationReq decode error: no packageName in the record");
            }
            int nLen = ByteArray.bytesToInt(byMsg, offset);
            offset += 4;

            // Decodes the Data value field
            if ((offset + nLen) > length) {
                throw new ToolException("ApplicationOperationReq decode error: no packageName in the record");
            }
            packageName = new String(byMsg, offset, nLen);
            offset += nLen;
            setPresent("PackageName", true);
        }

        return offset - start;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.mobile.msg.MobileMsgBase#encode(com.aspire.util.ByteArray)
     */
    public int encode(ByteArray baMsg) throws ToolException {

        int nLen = baMsg.length();

        super.encode(baMsg);

        // Encodes the Action
        baMsg.append(nAction);

        // Encodes the path or packageName field
        if (MobileConstants.APP_OPERATION_ACTION_INSTALL == nAction) {
            baMsg.append(path.getBytes().length);
            baMsg.append(path);
        } else if (MobileConstants.APP_OPERATION_ACTION_STARTCLASS == nAction
                || MobileConstants.APP_OPERATION_ACTION_STOPCLASS == nAction) {
            baMsg.append(packageName.getBytes().length);
            baMsg.append(packageName);
            baMsg.append(className.getBytes().length);
            baMsg.append(className);
        } else {
            baMsg.append(packageName.getBytes().length);
            baMsg.append(packageName);
        }

        return baMsg.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.mobile.msg.MobileMsgBase#format(java.lang.StringBuffer, java.lang.String)
     */
    public int format(StringBuffer buf, String sPrefix) throws ToolException {

        int nLen = buf.length();

        super.format(buf, sPrefix);

        // Format the Action
        buf.append(sPrefix).append("Action = ").append(nAction).append(Constants.LINE_SEPARATOR);

        // Format the Data field
        if (MobileConstants.APP_OPERATION_ACTION_INSTALL == nAction) {
            buf.append(sPrefix).append("Path = ").append(path).append(Constants.LINE_SEPARATOR);
        } else if (MobileConstants.APP_OPERATION_ACTION_STARTCLASS == nAction
                || MobileConstants.APP_OPERATION_ACTION_STOPCLASS == nAction) {
            buf.append(sPrefix).append("PackageName = ").append(packageName).append(sPrefix).append("ClassName = ")
                    .append(className).append(Constants.LINE_SEPARATOR);
        } else {
            buf.append(sPrefix).append("PackageName = ").append(packageName).append(Constants.LINE_SEPARATOR);
        }

        return buf.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.msg.soap.SoapMsgBase#verify(com.aspire.msg.MsgBase)
     */
    public boolean verify(MsgBase msgVerified) throws ToolException {

        if (this == msgVerified)
            return true;

        if (msgVerified == null) {
            throw new ToolException("Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof ApplicationOperationReq)) {
            throw new ToolException(
                    "Message verify error: the message to be verified is not a 'CallOperationReq' message");
        }

        ApplicationOperationReq obj = (ApplicationOperationReq) msgVerified;

        doVerifyField("Action", nAction, obj, obj.nAction);

        // Verify data field
        if (MobileConstants.APP_OPERATION_ACTION_INSTALL == nAction) {
            doVerifyField("Path", path, obj, obj.path);
        } else if (MobileConstants.APP_OPERATION_ACTION_STARTCLASS == nAction
                || MobileConstants.APP_OPERATION_ACTION_STOPCLASS == nAction) {
            doVerifyField("PackageName", packageName, obj, obj.packageName);
            doVerifyField("ClassName", className, obj, obj.className);
        } else {
            doVerifyField("PackageName", packageName, obj, obj.packageName);
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.msg.soap.SoapMsgBase#verifyPresent(com.aspire.msg.MsgBase)
     */
    public boolean verifyPresent(MsgBase msgVerified) throws ToolException {

        if (this == msgVerified)
            return true;

        if (msgVerified == null) {
            throw new ToolException("Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof ApplicationOperationReq)) {
            throw new ToolException(
                    "Message verify error: the message to be verified is not a 'ApplicationOperationReq' message");
        }

        ApplicationOperationReq obj = (ApplicationOperationReq) msgVerified;

        doVerifyPresentField("Action", nAction, obj, obj.nAction);

        // Verify data field
        if (MobileConstants.APP_OPERATION_ACTION_INSTALL == nAction) {
            doVerifyPresentField("Path", path, obj, obj.path);
        } else if (MobileConstants.APP_OPERATION_ACTION_STARTCLASS == nAction
                || MobileConstants.APP_OPERATION_ACTION_STOPCLASS == nAction) {
            doVerifyPresentField("PackageName", packageName, obj, obj.packageName);
            doVerifyPresentField("ClassName", className, obj, obj.className);
        } else {
            doVerifyPresentField("PackageName", packageName, obj, obj.packageName);
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.msg.soap.SoapMsgBase#verifySpecified(com.aspire.msg.MsgBase)
     */
    public boolean verifySpecified(MsgBase msgVerified) throws ToolException {

        if (this == msgVerified)
            return true;

        if (msgVerified == null) {
            throw new ToolException("Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof ApplicationOperationReq)) {
            throw new ToolException(
                    "Message verify error: the message to be verified is not a 'ApplicationOperationReq' message");
        }

        ApplicationOperationReq obj = (ApplicationOperationReq) msgVerified;

        doVerifySpecifiedField("Action", nAction, obj, obj.nAction);

        // Verify data field
        if (MobileConstants.APP_OPERATION_ACTION_INSTALL == nAction) {
            doVerifySpecifiedField("Path", path, obj, obj.path);
        } else if (MobileConstants.APP_OPERATION_ACTION_STARTCLASS == nAction
                || MobileConstants.APP_OPERATION_ACTION_STOPCLASS == nAction) {
            doVerifySpecifiedField("PackageName", packageName, obj, obj.packageName);
            doVerifySpecifiedField("ClassName", className, obj, obj.className);
        } else {
            doVerifySpecifiedField("PackageName", packageName, obj, obj.packageName);
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.msg.FieldMsgBase#createDefaultResponse()
     */
    public MsgBase createDefaultResponse() {

        ApplicationOperationResp oResp = new ApplicationOperationResp();

        oResp.setSequence(nSeqNum);

        return oResp;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj))
            return false;

        if (!(obj instanceof ApplicationOperationReq)) {
            return false;
        }

        ApplicationOperationReq oMsgObj = (ApplicationOperationReq) obj;

        if (MobileConstants.APP_OPERATION_ACTION_INSTALL == nAction) {
            // Check url field
            return oMsgObj.nAction == nAction && oMsgObj.path.equals(path);
        } else if (MobileConstants.APP_OPERATION_ACTION_STARTCLASS == nAction
                || MobileConstants.APP_OPERATION_ACTION_STOPCLASS == nAction) {
            return oMsgObj.nAction == nAction && oMsgObj.packageName.equals(packageName)
                    && oMsgObj.className.equals(className);
        } else {
            return oMsgObj.nAction == nAction && oMsgObj.packageName.equals(packageName);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.mobile.msg.MobileMsgBase#isPresent(java.lang.String)
     */
    public boolean isPresent(String field) {
        return buildFields.contains(field);
    }
}
