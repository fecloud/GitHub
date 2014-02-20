/**
 * @(#) FileOperationResponse.java Created on 2012-8-9
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;

import com.aspire.util.ToolException;

/**
 * The class <code>FileOperationResponse</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class FileOperationResponse extends CommonResponse {

    protected long fileSize;

    private boolean fileSizeFlag = false;

    /**
     * Constructor
     * 
     * @param type
     */
    public FileOperationResponse() {
        super(APSMessage.FO_RESP);
    }

    @Override
    protected int encodeBody(ByteBuffer destination) throws ToolException {
        // 正常返回0
        int nLen = 4;
        destination.putInt(getErrcode());
        final String errormsg = getErrormsg();
        if (null != errormsg) {
            final int errormsg_len = errormsg.getBytes().length;
            destination.putInt(errormsg_len);
            nLen += 4;
            destination.put(errormsg.getBytes());
            nLen += errormsg_len;
        } else {
            if (fileSizeFlag) {
                destination.putLong(fileSize);
                nLen += 8;
            }
        }
        return nLen;
    }

    @Override
    protected void decodeBody(ByteBuffer source) throws ToolException {
        if (source.remaining() < LEN_CODE_LEN) {
            throw new ToolException("Bad message code length, need ["
                    + LEN_CODE_LEN + "], but remaining [" + source.remaining()
                    + "]");
        }
        setErrcode(source.getInt());

        if (getErrcode() != STATUS_OK) { // 有错误信息不解析fileSize
            if (source.remaining() <= 0 ) {
             return;   
            }
            if(source.remaining() < 4){
                throw new ToolException("Bad message code length, need [" + 4
                        + "], but remaining [" + source.remaining() + "]");
            }
            final int errormsg_len = source.getInt();

            if (source.remaining() < errormsg_len) {
                throw new ToolException("Bad message code length, need ["
                        + errormsg_len + "], but remaining ["
                        + source.remaining() + "]");
            }

            byte[] dest = new byte[errormsg_len];
            source.get(dest);
            setErrormsg(new String(dest));

        } else {
            // 没有错误信息 解析fileSize
            if (source.remaining() == 8) {
                fileSize = source.getLong();
            }

        }

    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSizeFlag = true;
        this.fileSize = fileSize;
    }

}
