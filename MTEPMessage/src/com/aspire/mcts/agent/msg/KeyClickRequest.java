package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;
import java.util.List;

import com.aspire.util.ToolException;

/**
 * 
 * The class <code>KeyClickRequest</code>
 * 
 * @author Fan Yang
 * @version 1.0
 */
public class KeyClickRequest extends APSMessage {

    /**
     * the keys code
     */
    private short[] keys;

    /**
     * the keys release delay time
     */
    private short[] keyIntervals;

    /**
     * 
     * Constructor
     */
    public KeyClickRequest() {
        super(APSMessage.KC_REQ);
    }
    /**
     * 
     * Constructor
     * @param keys
     * @param keyIntervals
     */
    public KeyClickRequest(short[] keys,short[] keyIntervals) {
        super(APSMessage.KC_REQ);
        this.keys = keys;
        this.keyIntervals = keyIntervals;
    }

    /**
     * 
     * (non-Javadoc)
     * 
     * @see com.aspire.mcts.agent.msg.APSMessage#encodeBody(java.nio.ByteBuffer)
     */
    @Override
    protected int encodeBody(ByteBuffer destination) throws ToolException {
     // 正常返回0
        int nLen = 0;
        for(int i=0;i<keys.length;i++){
            destination.putShort(keys[i]);
            destination.putShort(keyIntervals[i]);
            nLen += 4;
        }
        return nLen;
    }

    /**
     * 
     * (non-Javadoc)
     * 
     * @see com.aspire.mcts.agent.msg.APSMessage#decodeBody(java.nio.ByteBuffer)
     */
    @Override
    protected void decodeBody(ByteBuffer source) throws ToolException {
        if (source.remaining() < (LEN_KEY_LEN + LEN_KEYDELAY_LEN)) {
            throw new ToolException("Bad message key and delay length, need [" + (LEN_KEY_LEN + LEN_KEYDELAY_LEN)
                    + "], but remaining [" + source.remaining() + "]");
        }
        int len = source.remaining()/4;
        short[] mkeys = new short[len];
        short[] mkeyIntervals = new short[len];
        
        for(int i=0;i<(source.remaining()/4);i++){
            mkeys[i] = source.getShort();
            mkeyIntervals[i] = source.getShort();
        }
        this.setKeys(mkeys);
        this.setKeyIntervals(mkeyIntervals);
    }

    /**
     * get the keys code
     * 
     * @return
     */
    public short[] getKeys() {
        return keys;
    }

    /**
     * set the keys code
     * 
     * @param keys
     */
    public void setKeys(short[] keys) {
        this.keys = keys;
    }

    /**
     * get the keys release delay time
     * 
     * @return
     */
    public short[] getKeyIntervals() {
        return keyIntervals;
    }
    /**
     * 
     * @param keyIntervals
     */
    public void setKeyIntervals(short[] keyIntervals) {
        this.keyIntervals = keyIntervals;
    }
}
