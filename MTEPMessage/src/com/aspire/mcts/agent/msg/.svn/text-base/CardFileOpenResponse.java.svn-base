/**
 * @(#) CardFileOpenResponse.java Created on 2012-7-18
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;

import com.aspire.util.ToolException;

/**
 * The class <code>CardFileOpenResponse</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class CardFileOpenResponse extends CommonResponse {

    /**
     * 文件描述符
     */
    private int fileDescription;

    /**
     * Constructor
     * 
     * @param type
     */
    public CardFileOpenResponse() {
        super(APSMessage.CFO_RESP);
    }

    public CardFileOpenResponse(int fileDescription) {
        this();
        this.fileDescription = fileDescription;
    }

    @Override
    protected int encodeBody(ByteBuffer destination) throws ToolException {
        // 正常返回0
        int nLen = super.encodeBody(destination);
        if (0 == getErrcode()) {
            destination.putInt(fileDescription);
            nLen += 4;
        }
        return nLen;
    }

    @Override
    protected void decodeBody(ByteBuffer source) throws ToolException {
        if (source.remaining() < LEN_CODE_LEN) {
            throw new ToolException("Bad message code length, need [" + LEN_CODE_LEN + "], but remaining ["
                    + source.remaining() + "]");
        }
        setErrcode(source.getInt());
        if (0 != super.getErrcode()) {
            if (source.remaining() > 1) {
                int bodylen = source.remaining();
                byte[] srtbyte = new byte[bodylen];
                source.get(srtbyte);
                setErrormsg(new String(srtbyte));
            }
        } else if (0 == super.getErrcode()) {
            if (source.remaining() != LEN_CODE_LEN) {
                throw new ToolException("Bad message code length, need [" + LEN_CODE_LEN + "], but remaining ["
                        + source.remaining() + "]");
            } else {
                setFileDescription(source.getInt());
            }
        }
    }

    public int getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(int fileDescription) {
        this.fileDescription = fileDescription;
    }

}
