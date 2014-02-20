/**
 * @(#) WapOperationResp.java Created on 2012-5-8
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.mobile.msg;

import com.aspire.mobile.MobileConstants;
import com.aspire.util.ByteArray;
import com.aspire.util.ToolException;

/**
 * 
 * The class <code>WapOperationResp</code>
 * 
 * @author zhenghui
 * @version 1.0
 */
public class WapOperationResp extends MobileRespBase {

    /**
     * The count field
     */
    protected byte nCount;

    /**
     * Get the Count value
     * 
     * @return
     */
    public byte getCount() {
        return nCount;
    }

    /**
     * Get the Count value
     * 
     * @return
     */
    public int getCountInt() {
        return (int) nCount;
    }

    /**
     * Set the Count value
     * 
     * @param nCount
     */
    public void setCount(byte nCount) {
        setCountInt((int) nCount);
    }

    /**
     * Set the Count value
     * 
     * @param nCount
     */
    public void setCountInt(int nCount) {
        this.nCount = (byte) nCount;
        setVerify("COUNT", true);
    }

    /**
     * The constructor
     */
    public WapOperationResp() {
        super(MobileConstants.WAP_OPERATION_RESP, "WapOperationResp");
    }

    @Override
    public int decode(byte[] byMsg, int start, int length) throws ToolException {
        int nRead = super.decode(byMsg, start, length);

        int offset = start + nRead;
        if ((byMsg.length - 1) > offset) {
            nCount = byMsg[offset];
            offset++;
        }

        return offset - start;
    }

    @Override
    public int encode(ByteArray baMsg) throws ToolException {
        int nLen = baMsg.length();

        super.encode(baMsg);
        if (isPresent("COUNT")) {
            baMsg.append(nCount);
        }

        return baMsg.length() - nLen;
    }

}
