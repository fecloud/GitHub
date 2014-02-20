package com.inspurworld.msg;

import java.nio.ByteBuffer;

import com.inspurworld.msg.exception.ToolException;

public abstract class APSMessage {

    public static final short C_REQ = 221;

    public static final short C_RESP = 222;
    /**
     * GrabImage Request message
     */
    public static final short GI_REQ = 321;
    /**
     * GrabImage Response message
     */
    public static final short GI_RESP = 322;

    public static final short IU_REQ = 323;

    public static final short IU_RESP = 324;

    public static final int LEN_TAG = 3;
    public static final int LEN_VERSION = 1;
    public static final int LEN_TYPE = 2;
    public static final int LEN_BODY_LEN = 4;
    public static final int LEN_HEADER = LEN_TAG + LEN_VERSION + LEN_TYPE + LEN_BODY_LEN;
    public static final int OFFSET_BODY_LEN = LEN_TAG + LEN_VERSION + LEN_TYPE;
    public static final int OFFSET_TYPE = LEN_TAG + LEN_VERSION;

    /**
     * 缓冲区大小
     */
    public final static int MAX_MSG_LEN = 500 * 1024;
    public static final int LEN_CODE_LEN = 4;
    public static final int LEN_KEY_LEN = 2;
    public static final int LEN_KEYDELAY_LEN = 2;
    public static final int LEN_KEYOPE_LEN = 1;
    public static final int LEN_XY_LEN = 8;

    /**
     * Message identifier.
     */
    public final static byte[] TAG = "APS".getBytes();
    public final static byte VERSION = 1;

    private short type;

    private int bodyLen;

    protected APSMessage(short type) {
        this.type = type;
    }

    /**
     * Decode message body
     * 
     * @param source
     * @throws MsgCodecException
     */
    protected void decodeBody(ByteBuffer source) throws ToolException {
        // No body
    }

    /**
     * Encode message body
     * 
     * @param destination
     * @return length of body
     * @throws MsgCodecException
     */
    protected int encodeBody(ByteBuffer destination) throws ToolException {
        return 0; // No body
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.mcts.msg.MctsMessage#decode(java.nio.ByteBuffer)
     */
    public void decode(ByteBuffer source) throws ToolException {
        if (source.remaining() < LEN_HEADER) {
            throw new ToolException("Bad message header length, need [" + LEN_HEADER + "], but remaining ["
                    + source.remaining() + "]");
        }
        try {
            // Body-length field
            source.position(LEN_TAG + LEN_VERSION + LEN_TYPE);
            bodyLen = source.getInt();
        } catch (Exception ex) {
            throw new ToolException("Fail to decode message head: " + ex.getMessage(), ex);
        }

        // Decode Message Body
        if (bodyLen != source.remaining()) {
            throw new ToolException("Bad message buffer, the remaining(" + source.remaining()
                    + ") is not equal with the legnth of body: " + bodyLen);
        }

        decodeBody(source);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.mcts.msg.MctsMessage#encode(java.nio.ByteBuffer)
     */
    public ByteBuffer encode(ByteBuffer destination) throws ToolException {
        if (destination != null) {
            try {
                destination.put(TAG);
                destination.put(VERSION);
                destination.putShort(type);
                // !NOTE!Write 0 as POS_BODY_LEN field place holder first
                destination.putInt(0);
                // Encode body and rewrite POS_BODY_LEN field
                destination.putInt(LEN_TAG + LEN_VERSION + LEN_TYPE, encodeBody(destination));

                // Must do flip after write
                destination.flip();
            } catch (ToolException ex) {
                throw ex;
            } catch (Exception ex) {
                throw new ToolException("Fail to encode message head: " + ex.getMessage(), ex);
            }
        }
        return destination;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public int getBodyLen() {
        return bodyLen;
    }

    public void setBodyLen(int bodyLen) {
        this.bodyLen = bodyLen;
    }

}
