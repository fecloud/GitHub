/**
 * @(#) APSMessageCodec.java Created on 2012-3-2
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.msg.codec;

import java.nio.ByteBuffer;

import com.inspurworld.msg.APSMessage;
import com.inspurworld.msg.common.ConnectionRequest;
import com.inspurworld.msg.common.ConnectionResponse;
import com.inspurworld.msg.common.GrabImageRequest;
import com.inspurworld.msg.common.GrabImageResponse;
import com.inspurworld.msg.common.ImageUpLoadRequest;
import com.inspurworld.msg.common.ImageUpLoadRespone;
import com.inspurworld.msg.exception.ToolException;

/**
 * The class <code>APSMessageCodec</code>
 * 
 * @author Link Wang
 * @version 1.0
 */
public class APSMessageCodec {

    public APSMessage decode(ByteBuffer source) throws ToolException {
        APSMessage message = null;
        short msgType = source.getShort(APSMessage.OFFSET_TYPE);
        switch (msgType) {
        case APSMessage.GI_REQ:
            message = new GrabImageRequest();
            break;
        case APSMessage.GI_RESP:
            message = new GrabImageResponse();
            break;
        case APSMessage.IU_REQ:
            message = new ImageUpLoadRequest();
            break;
        case APSMessage.IU_RESP:
            message = new ImageUpLoadRespone();
            break;
        case APSMessage.C_REQ:
            message = new ConnectionRequest();
            break;
        case APSMessage.C_RESP:
            message = new ConnectionResponse();
            break;
        default:
            throw new ToolException("UnSupport Type of APSMessage.");
        }
        message.decode(source);
        return message;
    }

    public ByteBuffer encode(APSMessage message) throws ToolException {
        switch (message.getType()) {
        // case APSMessage.TT_RESP:
        // return encodeASPMTEPmsg(message, null);
        default:
            return encode(message, null);
        }
    }

    public ByteBuffer encodeASPMTEPmsg(APSMessage message, ByteBuffer destination) throws ToolException {
        if (destination == null) {
            destination = ByteBuffer.allocate(APSMessage.MAX_MSG_LEN);
        }
        return message.encode(destination);
    }

    public ByteBuffer encode(APSMessage message, ByteBuffer destination) throws ToolException {
        if (destination == null) {
            destination = ByteBuffer.allocate(APSMessage.MAX_MSG_LEN);
        }
        return message.encode(destination);
    }
}
