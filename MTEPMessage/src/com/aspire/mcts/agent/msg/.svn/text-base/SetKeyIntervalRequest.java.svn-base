package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;
import com.aspire.util.ToolException;
/**
 * 
 * The class <code>SetKeyIntervalRequest</code>
 *
 * @author Fan Yang
 * @version 1.0
 */
public class SetKeyIntervalRequest extends APSMessage {
    /**
     * the key interval
     */
    private int key_Interval;
    /**
     * 
     * Constructor
     */
    public SetKeyIntervalRequest() {
        super(APSMessage.SKI_REQ);
    }
    /**
     * 
     * Constructor
     * @param key_Interval
     */
    public SetKeyIntervalRequest(int key_Interval) {
        super(APSMessage.SKI_REQ);
        this.key_Interval = key_Interval;
    }
    /**
     * 
     * (non-Javadoc)
     * @see com.aspire.mcts.agent.msg.APSMessage#encodeBody(java.nio.ByteBuffer)
     */
    @Override
    protected int encodeBody(ByteBuffer destination) throws ToolException {
     // 正常返回0
        destination.putInt(key_Interval);
        return 4;
    }
    /**
     * 
     * (non-Javadoc)
     * @see com.aspire.mcts.agent.msg.APSMessage#decodeBody(java.nio.ByteBuffer)
     */
    @Override
    protected void decodeBody(ByteBuffer source) throws ToolException {
        if (source.remaining() < LEN_CODE_LEN) {
            throw new ToolException("Bad message key_Interval length, need ["
                    + LEN_CODE_LEN + "], but remaining [" + source.remaining()
                    + "]");
        }
        setKey_Interval(source.getInt());
    }
    /**
     * get the key interval
     * @return
     */
    public int getKey_Interval() {
        return key_Interval;
    }
    /**
     * set the key interval
     * @param key_Interval
     */
    public void setKey_Interval(int key_Interval) {
        this.key_Interval = key_Interval;
    }
}
