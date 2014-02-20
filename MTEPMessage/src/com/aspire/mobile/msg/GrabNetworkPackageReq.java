/**
 * @(#) GrabNetworkPackageReq.java Created on 2012-7-9
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.mobile.msg;

import com.aspire.mobile.MobileConstants;
import com.aspire.msg.MsgBase;
import com.aspire.util.ByteArray;
import com.aspire.util.GenApi;
import com.aspire.util.ToolException;

/**
 * The class <code>GrabNetworkPackageReq</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class GrabNetworkPackageReq extends MobileMsgBase {

    /**
     * The action type, 0x01:start 0x02:stop
     */
    protected byte nAction = MobileConstants.GRAB_NETWORKPACKAGE_ACTION_START;

    /**
     * The Path field
     */
    protected String sPath = "";

    /**
     * Constructor
     * 
     * @param nMsgType
     * @param sMsgName
     */
    public GrabNetworkPackageReq() {
        super(MobileConstants.GRAB_NETWORKPACKAGE_REQ, "GrabNetworkPackageReq");
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
     * @return the sPath
     */
    public String getPath() {
        return sPath;
    }

    /**
     * @param sPath
     *            the sPath to set
     */
    public void setPath(String sPath) {
        this.sPath = sPath;
        setVerify("PATH", true);
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
            throw new ToolException(
                    "GrabNetworkPackageReq decode error: no Action in the record");
        }
        nAction = byMsg[offset];
        offset++;

        if (nAction == MobileConstants.GRAB_NETWORKPACKAGE_ACTION_START) {
            if ((offset + 4) < length) {
                int nLen = ByteArray.bytesToInt(byMsg, offset);
                offset += 4;

                if ((offset + nLen) > length) {
                    throw new ToolException(
                            "GrabNetworkPackageReq decode error: no Path in the record");
                }
                sPath = new String(byMsg, offset, nLen);
                offset += nLen;
                setPresent("PATH", true);
            }
        }

        return offset - start;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.aspire.mobile.msg.MobileMsgBase#encode(com.aspire.util.ByteArray)
     */
    public int encode(ByteArray baMsg) throws ToolException {

        int nLen = baMsg.length();

        super.encode(baMsg);

        // Encodes the Action
        baMsg.append(nAction);

        if (nAction == MobileConstants.GRAB_NETWORKPACKAGE_ACTION_START) {
            baMsg.append(sPath.getBytes().length);
            baMsg.append(sPath);
        }

        return baMsg.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.msg.FieldMsgBase#createDefaultResponse()
     */
    public MsgBase createDefaultResponse() {

        GrabNetworkPackageResp oResp = new GrabNetworkPackageResp();

        oResp.setSequence(nSeqNum);

        return oResp;
    }

}
