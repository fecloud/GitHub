/**
 * @(#) CardFileCloseRequest.java Created on 2012-7-18
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;

import com.aspire.util.ToolException;

/**
 * The class <code>CardFileCloseRequest</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class CardFileCloseRequest extends APSMessage {

    /**
     * 文件描述符
     */
    private int fileDescription;

    /**
     * Constructor
     * 
     * @param type
     */
    public CardFileCloseRequest() {
        super(APSMessage.CFC_REQ);
        setBodyLen(LEN_CODE_LEN);
    }

    /**
     * Constructor
     * 
     * @param fileDescription
     */
    public CardFileCloseRequest(int fileDescription) {
        this();
        this.fileDescription = fileDescription;
    }

    @Override
    protected void decodeBody(ByteBuffer source) throws ToolException {
        if (source.remaining() != LEN_CODE_LEN) {
            throw new ToolException("Bad message cardFileOpen body length, need [" + LEN_CODE_LEN
                    + "], but remaining [" + source.remaining() + "]");
        }
        setFileDescription(source.getInt());
    }

    @Override
    protected int encodeBody(ByteBuffer destination) throws ToolException {
        destination.putInt(fileDescription);
        return this.getBodyLen();
    }

    public int getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(int fileDescription) {
        this.fileDescription = fileDescription;
    }

}
