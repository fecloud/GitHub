package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;
import com.aspire.util.ToolException;
/**
 * 
 * The class <code>TouchDownRequest</code>
 *
 * @author Fan Yang
 * @version 1.0
 */
public class TouchDownRequest extends APSMessage {
    
    /**
     * the coordinate X and Y
     */
    private int[] XY;
    /**
     * 
     * Constructor
     */
    public TouchDownRequest() {
        super(APSMessage.TD_REQ);
    }
    /**
     * 
     * Constructor
     * @param XY
     */
    public TouchDownRequest(int[] XY) {
        super(APSMessage.TD_REQ);
        this.XY = XY;
    }
    /**
     * 
     * (non-Javadoc)
     * @see com.aspire.mcts.agent.msg.APSMessage#encodeBody(java.nio.ByteBuffer)
     */
    @Override
    protected int encodeBody(ByteBuffer destination) throws ToolException {
     // 正常返回0
        destination.putInt(XY[0]);
        destination.putInt(XY[1]);
        return 8;
    }
    /**
     * 
     * (non-Javadoc)
     * @see com.aspire.mcts.agent.msg.APSMessage#decodeBody(java.nio.ByteBuffer)
     */
    @Override
    protected void decodeBody(ByteBuffer source) throws ToolException {
        if (source.remaining() < LEN_XY_LEN) {
            throw new ToolException("Bad message coordinate length, need ["
                    + LEN_XY_LEN + "], but remaining [" + source.remaining()
                    + "]");
        }
        int x = source.getInt();
        int y = source.getInt();
        int[] mXY = {x,y};
        setXY(mXY);
    }
    /**
     * get the coordinate X and Y
     * @return
     */
    public int[] getXY() {
        return XY;
    }
    /**
     * set the coordinate X and Y
     * @param XY
     */
    public void setXY(int[] XY) {
        this.XY = XY;
    }
}
