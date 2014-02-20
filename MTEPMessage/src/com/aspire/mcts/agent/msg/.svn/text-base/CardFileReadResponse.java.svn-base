/**
 * @(#) CardFileReadResponse.java Created on 2012-7-18
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;

import com.aspire.util.ToolException;

/**
 * The class <code>CardFileReadResponse</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class CardFileReadResponse extends CommonResponse {

    private FileData fileData;

    /**
     * Constructor
     * 
     * @param type
     */
    public CardFileReadResponse() {
        super(APSMessage.CFR_RESP);
    }

    /**
     * Constructor
     * 
     * @param type
     * @param fileData
     */
    public CardFileReadResponse(FileData fileData) {
        this();
        this.fileData = fileData;
    }

    @Override
    protected int encodeBody(ByteBuffer destination) throws ToolException {
        // 正常返回0
        int nLen = 4;
        destination.putInt(super.getErrcode());
        if (null != super.getErrormsg() && 0 != super.getErrcode()) {
            destination.put(super.getErrormsg().getBytes());
            nLen += super.getErrormsg().getBytes().length;
        } else if (null != fileData && 0 == super.getErrcode()) {
            destination.putLong(fileData.getFileTotalLength());
            nLen += 8;
            destination.putLong(fileData.getFileCurrentPosition());
            nLen += 8;
            destination.put(fileData.getFileBlock());
            nLen += fileData.getFileBlock().length;
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
            final FileData fileData = new FileData();
            // 读取总长度
            if (source.remaining() < 8) {
                throw new ToolException("Bad message code length, need [8], but remaining [" + source.remaining() + "]");
            }
            fileData.setFileTotalLength(source.getLong());

            // 读取当前进度
            if (source.remaining() < 8) {
                throw new ToolException("Bad message code length, need [8], but remaining [" + source.remaining() + "]");
            }
            fileData.setFileCurrentPosition(source.getLong());

            // 读取文件数据块
            int dataLen = source.remaining();
            final byte[] dataBytes = new byte[dataLen];
            source.get(dataBytes);
            fileData.setFileBlock(dataBytes);

            this.fileData = fileData;
        }
    }

    public FileData getFileData() {
        return fileData;
    }

    public void setFileData(FileData fileData) {
        this.fileData = fileData;
    }

}
