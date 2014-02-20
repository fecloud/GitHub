/**
 * @(#) ConnectionRequest.java Created on 2012-10-23
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.msg.common;

import java.nio.ByteBuffer;

import com.inspurworld.msg.APSMessage;
import com.inspurworld.msg.exception.ToolException;

/**
 * The class <code>ConnectionRequest</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class ConnectionRequest extends APSMessage {

    /**
     * 连接
     */
    public static final byte CONNECT = 1;

    /**
     * 断开
     */
    public static final byte DISCONNECT = 0;

    private int connectionType;

    /**
     * Constructor
     * 
     * @param type
     */
    public ConnectionRequest() {
        super(APSMessage.C_REQ);
    }

    @Override
    protected void decodeBody(ByteBuffer source) throws ToolException {
        if (source.remaining() < 4) {
            throw new ToolException("Bad message header length, need [" + 4 + "], but remaining [" + source.remaining()
                    + "]");
        } else {
            this.connectionType = source.getInt();
        }
    }

    @Override
    protected int encodeBody(ByteBuffer destination) throws ToolException {
        destination.putInt(connectionType);
        return 4;
    }

    public int getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(int connectionType) {
        this.connectionType = connectionType;
    }

}
