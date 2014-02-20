package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;
import com.aspire.util.ToolException;
/**
 * 
 * The class <code>GetScreenDimensionsRequest</code>
 *
 * @author Fan Yang
 * @version 1.0
 */
public class GetScreenDimensionsRequest extends APSMessage {
    
    /**
     * mobilephone screen ID
     */
    private int Display_ID;

    /**
     * 
     * Constructor
     */
    public GetScreenDimensionsRequest() {
        super(APSMessage.GSD_REQ);
        this.setBodyLen(4);
    }

    /**
     * 
     * Constructor
     * @param Display_ID
     */
    public GetScreenDimensionsRequest(int Display_ID) {
        super(APSMessage.GSD_REQ);
        this.Display_ID = Display_ID;
        this.setBodyLen(4);
    }

    /**
     * 
     * (non-Javadoc)
     * @see com.aspire.mcts.agent.msg.APSMessage#encodeBody(java.nio.ByteBuffer)
     */
    @Override
    protected int encodeBody(ByteBuffer destination) throws ToolException {
     // 正常返回0
        destination.putInt(Display_ID);
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
            throw new ToolException("Bad message display_ID length, need ["
                    + LEN_CODE_LEN + "], but remaining [" + source.remaining()
                    + "]");
        }
        setDisplay_ID(source.getInt());
    }
    /**
     * Set mobilephone screen ID
     * @return
     */
    public int getDisplay_ID() {
        return Display_ID;
    }

    /**
     * Get mobilephone screen ID
     * @param Display_ID
     */
    public void setDisplay_ID(int Display_ID) {
        this.Display_ID = Display_ID;
    }
}
