package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;
import com.aspire.util.ToolException;
/**
 * 
 * The class <code>KeyUpRequest</code>
 *
 * @author Fan Yang
 * @version 1.0
 */
public class KeyUpRequest extends APSMessage {
    
    /**
     * the keys code
     */
    private short[] keys;
    /**
     * 
     * Constructor
     */
    public KeyUpRequest() {
        super(APSMessage.KU_REQ);
    }
    /**
     * 
     * Constructor
     * @param keys
     */
    public KeyUpRequest(short[] keys) {
        super(APSMessage.KU_REQ);
        this.keys = keys;
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
            destination.putShort(keys[i]);
            nLen += 2;
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
        if (source.remaining() < LEN_KEY_LEN) {
            throw new ToolException("Bad message key length, need ["
                    + LEN_KEY_LEN + "], but remaining [" + source.remaining()
                    + "]");
        }
        int len = source.remaining()/2;
        short[] mkeys = new short[len];
        
        for(int i=0;i<(len);i++){
            mkeys[i] = source.getShort();
        }
        this.setKeys(mkeys);
    }
    /**
     * get the keys code
     * @return
     */
    public short[] getKeys() {
        return keys;
    }
    /**
     * set the keys code
     * @param keys
     */
    public void setKeys(short[] keys) {
        this.keys = keys;
    }
}
