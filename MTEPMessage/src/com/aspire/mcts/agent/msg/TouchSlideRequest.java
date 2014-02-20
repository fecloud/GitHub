package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;
import com.aspire.util.ToolException;
/**
 * 
 * The class <code>TouchSlideRequest</code>
 *
 * @author Fan Yang
 * @version 1.0
 */
public class TouchSlideRequest extends APSMessage {
    /**
     * the inter delay time
     */
    private short interDelay; 
    /**
     * the array of coordinate X and Y
     */
    private int[][] XY;
    /**
     * 
     * Constructor
     */
    public TouchSlideRequest() {
        super(APSMessage.TS_REQ);
    }
    /**
     * 
     * Constructor
     * @param XY
     */
    public TouchSlideRequest(short interDelay,int[][] XY) {
        super(APSMessage.TS_REQ);
        this.interDelay = interDelay;
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
        
        destination.putInt(interDelay);
        for(int i=0;i<XY[0].length;i++){
            destination.putInt(XY[0][0]);
            destination.putInt(XY[1][0]);
        }
        
        return 4+8*XY[0].length;
    }
    /**
     * 
     * (non-Javadoc)
     * @see com.aspire.mcts.agent.msg.APSMessage#decodeBody(java.nio.ByteBuffer)
     */
    @Override
    protected void decodeBody(ByteBuffer source) throws ToolException {
        if (source.remaining() < LEN_KEY_LEN) {
            throw new ToolException("Bad message interDelay length, need ["
                    + LEN_KEY_LEN + "], but remaining [" + source.remaining()
                    + "]");
        }
        setInterDelay(source.getShort());
        if (source.remaining() > 1) {
            int bodylen = source.remaining();
            int[][] mXY = new int[2][bodylen/8];
            for(int i=0;i<bodylen/8;i++){
                mXY[0][i]= source.getInt();
                mXY[1][i]= source.getInt();
            }
            setXY(mXY);
        }
    }
    /**
     * get the array of coordinate X and Y
     * @return
     */
    public int[][] getXY() {
        return XY;
    }
    /**
     * set the array of coordinate X and Y
     * @param XY
     */
    public void setXY(int[][] XY) {
        this.XY = null;
        this.XY = new int[2][XY.length/2];
        this.XY = XY;
    }
    /**
     * get the inter delay time
     * @return
     */
    public short getInterDelay() {
        return interDelay;
    }
    /**
     * set the inter delay time
     * @param interDelay
     */
    public void setInterDelay(short interDelay) {
        this.interDelay = interDelay;
    }
}
