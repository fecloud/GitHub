/**
 * @(#) FileData.java Created on 2012-7-18
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.mcts.agent.msg;

/**
 * The class <code>FileData</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class FileData {

    /**
     * 文件总长度
     */
    private long fileTotalLength;

    /*
     * 当前文件读取位置，不包括本次正在发送的数据长度。在最后一个文件消息包中，FileCurrentPosition为 FileTotalLength减去本次正在发送的文件数据长度。
     */
    private long fileCurrentPosition;

    /**
     * 文件数据
     */
    private byte[] fileBlock;

    /**
     * Constructor
     */
    public FileData() {
    }

    /**
     * 
     * Constructor
     * 
     * @param fileTotalLength
     * @param fileCurrentPosition
     * @param fileBlock
     */
    public FileData(long fileTotalLength, long fileCurrentPosition, byte[] fileBlock) {
        super();
        this.fileTotalLength = fileTotalLength;
        this.fileCurrentPosition = fileCurrentPosition;
        this.fileBlock = fileBlock;
    }

    public long getFileTotalLength() {
        return fileTotalLength;
    }

    public void setFileTotalLength(long fileTotalLength) {
        this.fileTotalLength = fileTotalLength;
    }

    public long getFileCurrentPosition() {
        return fileCurrentPosition;
    }

    public void setFileCurrentPosition(long fileCurrentPosition) {
        this.fileCurrentPosition = fileCurrentPosition;
    }

    public byte[] getFileBlock() {
        return fileBlock;
    }

    public void setFileBlock(byte[] fileBlock) {
        this.fileBlock = fileBlock;
    }

}
