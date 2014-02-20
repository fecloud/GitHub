package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;

import com.aspire.util.ToolException;

public abstract class APSMessage {

    /**
     * KeyDown Request message
     */
    public static final short KD_REQ = 111;
    /**
     * KeyDown Response message
     */
    public static final short KD_RESP = 112;
    /**
     * KeyUp Request message
     */
    public static final short KU_REQ = 121;
    /**
     * KeyUp Response message
     */
    public static final short KU_RESP = 122;
    /**
     * KeyClick Request message
     */
    public static final short KC_REQ = 131;
    /**
     * KeyClick Response message
     */
    public static final short KC_RESP = 132;
    /**
     * KeySequence Request message
     */
    public static final short KS_REQ = 141;
    /**
     * KeySequence Response message
     */
    public static final short KS_RESP = 142;
    /**
     * SetKeyInterval Request message
     */
    public static final short SKI_REQ = 151;
    /**
     * SetKeyInterval Response message
     */
    public static final short SKI_RESP = 152;

    /**
     * TouchDown Request message
     */
    public static final short TD_REQ = 211;
    /**
     * TouchDown Response message
     */
    public static final short TD_RESP = 212;
    /**
     * TouchUp Request message
     */
    public static final short TU_REQ = 221;
    /**
     * TouchUp Response message
     */
    public static final short TU_RESP = 222;
    /**
     * TouchClick Request message
     */
    public static final short TC_REQ = 231;
    /**
     * TouchClick Response message
     */
    public static final short TC_RESP = 232;
    /**
     * TouchSlide Request message
     */
    public static final short TS_REQ = 241;
    /**
     * TouchSlide Response message
     */
    public static final short TS_RESP = 242;
    /**
     * TouchDClick Request message
     */
    public static final short TDC_REQ = 251;
    /**
     * TouchDClick Response message
     */
    public static final short TDC_RESP = 252;
    /**
     * TouchMove Request message
     */
    public static final short TM_REQ = 271;
    /**
     * TouchMove Response message
     */
    public static final short TM_RESP = 272;

    /**
     * GetScreenDimensions Request message
     */
    public static final short GSD_REQ = 311;
    /**
     * GetScreenDimensions Response message
     */
    public static final short GSD_RESP = 312;
    /**
     * GrabImage Request message
     */
    public static final short GI_REQ = 321;
    /**
     * GrabImage Response message
     */
    public static final short GI_RESP = 322;
    /**
     * SetImageParam Request message
     */
    public static final short SIP_REQ = 331;
    /**
     * SetImageParam Response message
     */
    public static final short SIP_RESP = 332;

    /**
     * card file open Request message
     */
    public static final short CFO_REQ = 611;
    /**
     * card file open Response message
     */
    public static final short CFO_RESP = 612;
    /**
     * card file close Request message
     */
    public static final short CFC_REQ = 621;
    /**
     * card file close Response message
     */
    public static final short CFC_RESP = 622;
    /**
     * card file read Request message
     */
    public static final short CFR_REQ = 631;
    /**
     * card file read Response message
     */
    public static final short CFR_RESP = 632;

    /**
     * card file write Request message
     */
    public static final short CFW_REQ = 641;
    /**
     * card file write Response message
     */
    public static final short CFW_RESP = 642;

    /**
     * mutil touch down request
     */
    public static final short MTD_REQ = 281;

    /**
     * mutil touch down response
     */
    public static final short MTD_RESP = 282;

    /**
     * mutil touch move request
     */
    public static final short MTM_REQ = 283;

    /**
     * mutil touch move response
     */
    public static final short MTM_RESP = 284;

    /**
     * mutil touch up request
     */
    public static final short MTU_REQ = 285;

    /**
     * mutil touch up response
     */
    public static final short MTU_RESP = 286;

    /**
     * file operation request
     */
    public static final short FO_REQ = 653;

    /**
     * file operation response
     */
    public static final short FO_RESP = 654;
    
    /**
     * eror report request
     */
    public static final short ER_REQ = 812;
    /**
     * mutil touch move response
     */
    public static final short HB_REQ = 821;
    public static final short HB_RESP = 822;
    public static final short TT_REQ = 851; // 透传请求消息 agent engine处理此消息
    public static final short TT_RESP = 852; // 透传应答消息

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
