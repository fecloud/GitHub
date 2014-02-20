/**
 * @(#) CardFileReadRequse.java Created on 2012-7-18
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;

import com.aspire.util.ToolException;

/**
 * The class <code>CardFileReadRequse</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class CardFileReadRequset extends APSMessage {

    /**
     * 文件描述符
     */
    private int fileDescription;

    /**
     * 读取的开始位置，支持大于3G文件
     */
    private long seekPosition;

    /**
     * Constructor
     * 
     * @param type
     */
    public CardFileReadRequset() {
        this(APSMessage.CFR_REQ);
    }

    /**
     * 
     * Constructor
     * 
     * @param type
     */
    public CardFileReadRequset(short type) {
        super(type);
    }

    /**
     * Constructor
     * 
     * @param fileDescription
     * @param seekPosition
     */
    public CardFileReadRequset(int fileDescription, Long seekPosition) {
        this();
        this.fileDescription = fileDescription;
        this.seekPosition = seekPosition;
    }

    @Override
    protected void decodeBody(ByteBuffer source) throws ToolException {
        if (source.remaining() < LEN_CODE_LEN) {
            throw new ToolException("Bad message cardFileOpen body length, need [" + LEN_CODE_LEN
                    + "], but remaining [" + source.remaining() + "]");
        }
        setFileDescription(source.getInt());
        if (source.remaining() < 8) {
            throw new ToolException("Bad message cardFileOpen body length, need [8], but remaining ["
                    + source.remaining() + "]");
        }
        setSeekPosition(source.getLong());
    }

    @Override
    protected int encodeBody(ByteBuffer destination) throws ToolException {
        int len = 0;

        destination.putInt(fileDescription);
        len += 4;

        destination.putLong(seekPosition);
        len += 8;
        return len;
    }

    public int getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(int fileDescription) {
        this.fileDescription = fileDescription;
    }

    public Long getSeekPosition() {
        return seekPosition;
    }

    public void setSeekPosition(Long seekPosition) {
        this.seekPosition = seekPosition;
    }

}
