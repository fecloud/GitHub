/**
 * @(#) MobileMsgCodec.java Created on 2009-2-13
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.codec;

import com.aspire.Constants;
import com.aspire.mobile.msg.MobileMsgBase;
import com.aspire.msg.MsgBase;
import com.aspire.msg.MsgCodec;
import com.aspire.msg.MsgCreator;
import com.aspire.util.ByteArray;
import com.aspire.util.CodecUtil;
import com.aspire.util.ToolException;

/**
 * The class <code>MobileMsgCodec</code>
 *
 * @author xuyong
 * @version 1.0
 */
public class MobileMsgCodec extends MsgCodec {

    /**
     * Constructs a new MobileMsgCodec with null as its message creator
     */
    public MobileMsgCodec() {
        super();
    }

    /**
     * Constructs a new MobileMsgCodec with the specified message creator msgCreator
     * as its message creator
     * @param msgCreator The specified message creator
     */
    public MobileMsgCodec(MsgCreator msgCreator) {
        super(msgCreator);
    }

    @Override
    public MsgBase decode(byte[] byMsg, int start, int length)
            throws ToolException {
        
        if (msgCreator == null) {
            throw new ToolException(
                "Mobile message decode error: the message creator is null");
        }
        
        MsgBase msgBase = msgCreator.create(byMsg, start + 4, length);
        
        if (msgBase == null) {
            throw new ToolException("Decode error, msgCreator create a null message:" + CodecUtil.toHexString(byMsg));
        }
        
        if (!(msgBase instanceof MobileMsgBase)) {
            throw new ToolException("Decode error: msgCreator create a unknown message:" + CodecUtil.toHexString(byMsg));
        }
        
        ((MobileMsgBase)msgBase).decode(byMsg, start + 4, length);
        return msgBase;
    }

    @Override
    public int encode(MsgBase msgBase, ByteArray baMsg) throws ToolException {
        if (msgBase == null) {
            throw new ToolException(
                "Mobile message encode error: the message object is null");
        }

        if (!(msgBase instanceof MobileMsgBase)) {
            throw new ToolException(
                "Mobile message encode error: the message is not a Mobile message");
        }
        
        ByteArray bodyArray = new ByteArray(12);
        
        int bodyLen = ((MobileMsgBase)msgBase).encode(bodyArray);
        int msgLen = bodyLen + 4;
        baMsg.append(msgLen);
        baMsg.append(bodyArray);

        return msgLen;
    }

    @Override
    public int format(MsgBase msgBase, StringBuffer buf) throws ToolException {
        int len = buf.length();

        if (msgBase == null) {
            throw new ToolException(
                "Mobile message format error: the message object is null");
        }

        if (!(msgBase instanceof MobileMsgBase)) {
            throw new ToolException(
                "DCC message fromat error: the message is not a Mobile message");
        }

        buf.append(msgBase.getName());
        buf.append(Constants.LINE_SEPARATOR);
        buf.append("{");
        buf.append(Constants.LINE_SEPARATOR);

        ((MobileMsgBase)msgBase).format(buf, "    ");

        buf.append("}");
        buf.append(Constants.LINE_SEPARATOR);

        return buf.length() - len;
    }
}
