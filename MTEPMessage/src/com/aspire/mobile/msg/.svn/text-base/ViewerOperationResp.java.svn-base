/**
 * @(#) ViewerOperationResp.java Created on 2012-5-8
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.mobile.msg;

import com.aspire.mobile.MobileConstants;
import com.aspire.util.ByteArray;
import com.aspire.util.ToolException;

/**
 * 
 * The class <code>ViewerOperationResp</code>
 * 
 * @author zhenghui
 * @version 1.0
 */
public class ViewerOperationResp extends MobileRespBase {
    /**
     * the viewer tree value
     */
    private String message = "";

    /**
     * 分包flag
     */
    private byte flag = MobileConstants.TE_SINGLE_MSG;

    /**
     * @return the flag
     */
    public byte getFlag() {
        return flag;
    }

    /**
     * @param flag
     *            the flag to set
     */
    public void setFlag(byte flag) {
        this.flag = flag;
        setVerify("Flag");
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
        setVerify("Message", true);
    }

    /**
     * The constructor
     */
    public ViewerOperationResp() {
        super(MobileConstants.VIEWER_OPERATION_RESP, "ViewerOperationResp");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.mobile.msg.MobileRespBase#decode(byte[], int, int)
     */
    @Override
    public int decode(byte[] byMsg, int start, int length) throws ToolException {
        int nRead = super.decode(byMsg, start, length);
        int offset = start + nRead;

        flag = byMsg[offset];
        offset++;

        // decodes the len
        int len = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;

        setMessage(new String(byMsg, offset, len));

        return offset - start;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.mobile.msg.MobileRespBase#encode(com.aspire.util.ByteArray)
     */
    @Override
    public int encode(ByteArray baMsg) throws ToolException {
        int nLen = baMsg.length();

        super.encode(baMsg);
        if (isPresent("Flag"))
            baMsg.append(flag);

        // Encodes message length
        if (isPresent("Message")){
            baMsg.append(message.getBytes().length);

            // Encodes message
            baMsg.append(message);
        }

        return baMsg.length() - nLen;
    }

}
