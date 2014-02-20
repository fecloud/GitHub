/**
 * @(#) FileOperationRequest.java Created on 2012-8-9
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;

import com.aspire.mobile.MobileConstants;
import com.aspire.util.ToolException;

/**
 * The class <code>FileOperationRequest</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class FileOperationRequest extends APSMessage {

    /**
     * action
     */
    protected byte nAction = MobileConstants.FILE_OPERATION_ACTION_DELETE;

    /**
     * 文件夹名
     */
    protected String path;

    /**
     * 文件名
     */
    protected String filename;

    /**
     * Constructor
     * 
     * @param type
     */
    public FileOperationRequest() {
        super(APSMessage.FO_REQ);
    }

    @Override
    protected void decodeBody(ByteBuffer source) throws ToolException {
        super.decodeBody(source);
        if (source.remaining() < LEN_KEYOPE_LEN) {
            throw new ToolException("Bad message code length, need [" + LEN_KEYOPE_LEN + "], but remaining ["
                    + source.remaining() + "]");
        }

        setAction(source.get());

        if (source.remaining() >= 4) {
            final int path_length = source.getInt();

            if (source.remaining() < path_length) {
                throw new ToolException("Bad message code length, need [" + path_length + "], but remaining ["
                        + source.remaining() + "]");
            }

            final byte[] dest = new byte[path_length];
            source.get(dest);
            path = new String(dest);
        }

        if (source.remaining() >= 4) {
            final int filename_length = source.getInt();

            if (source.remaining() < filename_length) {
                throw new ToolException("Bad message code length, need [" + filename_length + "], but remaining ["
                        + source.remaining() + "]");
            }

            final byte[] dest = new byte[filename_length];
            source.get(dest);
            filename = new String(dest);
        }
    }

    @Override
    protected int encodeBody(ByteBuffer destination) throws ToolException {
        int nLen = super.encodeBody(destination);
        destination.put(nAction);
        nLen += 1;
        if (null != path) {
            final int path_length = path.getBytes().length;
            destination.putInt(path_length);
            nLen += 4;
            destination.put(path.getBytes());
            nLen += path_length;
        }

        if (null != filename) {
            final int filename_length = filename.getBytes().length;
            destination.putInt(filename_length);
            nLen += 4;
            destination.put(filename.getBytes());
            nLen += filename_length;
        }
        return nLen;
    }

    public byte getAction() {
        return nAction;
    }

    public void setAction(byte nAction) {
        this.nAction = nAction;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

}
