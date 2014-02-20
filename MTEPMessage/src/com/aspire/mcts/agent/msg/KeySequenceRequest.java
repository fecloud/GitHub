package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;
import com.aspire.util.ToolException;
/**
 * 
 * The class <code>KeySequenceRequest</code>
 *
 * @author Fan Yang
 * @version 1.0
 */
public class KeySequenceRequest extends APSMessage {
    
    /**
     * the keys operate
     */
    private char[] keyOpes;

    /**
     * the keys code
     */
    private short[] keys;

    /**
     * the keys delay time
     */
    private short[] keyIntervals;

    /**
     * 
     * Constructor
     */
    public KeySequenceRequest() {
        super(APSMessage.KS_REQ);
    }
    /**
     * 
     * Constructor
     * @param keyOpes
     * @param keys
     * @param keyIntervals
     */
    public KeySequenceRequest(char[] keyOpes,short[] keys,short[] keyIntervals) {
        super(APSMessage.KS_REQ);
        this.keyOpes = keyOpes;
        this.keys = keys;
        this.keyIntervals = keyIntervals;
    }

    /**
     * 
     * (non-Javadoc)
     * @see com.aspire.mcts.agent.msg.APSMessage#encodeBody(java.nio.ByteBuffer)
     */
    @Override
    protected int encodeBody(ByteBuffer destination) throws ToolException {
     // 正常返回0
        int nLen = 0;
        for(int i=0;i<keys.length;i++){
            destination.putChar(keyOpes[i]);
            destination.putShort(keys[i]);
            destination.putShort(keyIntervals[i]);
            nLen += 6;
        }
        return nLen;
    }
    /**
     * 
     * (non-Javadoc)
     * @see com.aspire.mcts.agent.msg.APSMessage#decodeBody(java.nio.ByteBuffer)
     */
    @Override
    protected void decodeBody(ByteBuffer source) throws ToolException {
        if (source.remaining() < (LEN_KEY_LEN + LEN_KEYOPE_LEN + LEN_KEY_LEN + LEN_KEYOPE_LEN)) {
            throw new ToolException("Bad message key and delay length, need ["
                    + (LEN_KEY_LEN + LEN_KEYOPE_LEN + LEN_KEY_LEN + LEN_KEYOPE_LEN) + "], but remaining [" + source.remaining()
                    + "]");
        }
        int len = source.remaining()/6;
        char[] mkeyOpes = new char[len];
        short[] mkeys = new short[len];
        short[] mkeyIntervals = new short[len];
        
        for(int i=0;i<(source.remaining()/6);i++){
            mkeyOpes[i] = source.getChar();
            mkeys[i] = source.getShort();
            mkeyIntervals[i] = source.getShort();
        }
        this.setKeyOpes(mkeyOpes);
        this.setKeys(mkeys);
        this.setKeyIntervals(mkeyIntervals);
    }

    /**
     * get the keys operate
     * @param keys
     */
    public char[] getKeyOpes() {
        return keyOpes;
    }
    /**
     * set the keys operate
     * @param key1
     */
    public void setKeyOpes(char[] keyOpes) {
        this.keyOpes = keyOpes;
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
     * set the keys release delay time
     * @param keyIntervals
     */
    public void setKeyIntervals(short[] keyIntervals) {
        this.keyIntervals = keyIntervals;
    }
}
