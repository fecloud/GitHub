/**
 * @(#) CardFileOpenRequest.java Created on 2012-7-18
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;

import com.aspire.util.ToolException;

/**
 * The class <code>CardFileOpenRequest</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class CardFileOpenRequest extends APSMessage {

    /**
     * 全路径文件名
     */
    private String fileName;

    /**
     * ‘r’表示读,’w’表示写
     */
    private char readOrWrite;

    /**
     * Constructor
     * 
     * @param type
     */
    public CardFileOpenRequest() {
        super(CFO_REQ);
    }

    public CardFileOpenRequest(String fileName, char readOrWrite) {
        this();
        this.fileName = fileName;
        this.readOrWrite = readOrWrite;
    }

    @Override
    protected void decodeBody(ByteBuffer source) throws ToolException {
        if (source.remaining() < 2) {
            throw new ToolException("Bad message cardFileOpen body length, need [2+], but remaining ["
                    + source.remaining() + "]");
        }
        final int fnLeng = source.remaining() - 2;// 取得filename长度
        final byte[] fnbytes = new byte[fnLeng];
        source.get(fnbytes);
        setFileName(new String(fnbytes));

        setReadOrWrite(source.getChar());

    }

    @Override
    protected int encodeBody(ByteBuffer destination) throws ToolException {
        int len = 0;
        if (null != fileName) {
            final byte[] fnbytes = fileName.getBytes();
            destination.put(fnbytes);
            len += fnbytes.length;
        }
        destination.putChar(readOrWrite);
        len += 2;
        return len;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public char getReadOrWrite() {
        return readOrWrite;
    }

    /**
     * ‘r’表示读,’w’表示写
     * 
     * @param readOrWrite
     */
    public void setReadOrWrite(char readOrWrite) {
        this.readOrWrite = readOrWrite;
    }

}
