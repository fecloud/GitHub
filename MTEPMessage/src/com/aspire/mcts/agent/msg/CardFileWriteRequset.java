/**
 * @(#) CardFileWriteRequset.java Created on 2012-7-18
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;

import com.aspire.util.ToolException;

/**
 * The class <code>CardFileWriteRequset</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class CardFileWriteRequset extends CardFileReadRequset {

    /**
     * 写入文件块大小
     */
    private int fileDataLength;
    /**
     * 文件内容
     */
    private byte[] fileData;

    public CardFileWriteRequset() {
        super(APSMessage.CFW_REQ);
    }

    /**
     * Constructor
     * 
     * @param fileDescription
     * @param seekPosition
     * @param fileDataLength
     * @param fileData
     */
    public CardFileWriteRequset(int fileDescription, Long seekPosition, int fileDataLength, byte[] fileData) {
        this();
        setFileDescription(fileDescription);
        setSeekPosition(seekPosition);
        this.fileDataLength = fileDataLength;
        this.fileData = fileData;
    }

    @Override
    protected void decodeBody(ByteBuffer source) throws ToolException {
        super.decodeBody(source);
        if (source.remaining() < LEN_CODE_LEN) {
            throw new ToolException("Bad message code length, need [" + LEN_CODE_LEN + "], but remaining ["
                    + source.remaining() + "]");
        }
        setFileDataLength(source.getInt());

        if (source.remaining() != 0) {
            final byte[] dataBytes = new byte[source.remaining()];
            source.get(dataBytes);
            this.fileData = dataBytes;
        }
    }

    @Override
    protected int encodeBody(ByteBuffer destination) throws ToolException {
        int len = super.encodeBody(destination);
        destination.putInt(fileDataLength);
        len += 4;
        if (null != fileData) {
            destination.put(fileData);
            len += fileData.length;
        }
        return len;
    }

    public int getFileDataLength() {
        return fileDataLength;
    }

    public void setFileDataLength(int fileDataLength) {
        this.fileDataLength = fileDataLength;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

}
