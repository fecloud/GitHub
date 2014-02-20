package com.inspurworld.msg.common;

import java.nio.ByteBuffer;

import com.inspurworld.msg.APSMessage;
import com.inspurworld.msg.exception.ToolException;
/**
 * 
 * The class <code>GrabImageRequest</code>
 *
 * @author Fan Yang
 * @version 1.0
 */
public class GrabImageRequest extends APSMessage {
    /**
     * the start coordinate of x-axis 
     */
    private int xStart;
    /**
     * the start coordinate of y-axis
     */
    private int yStart;
    /**
     * the image width
     */
    private int width;
    /**
     * the image height
     */
    private int height;

    /**
     * 
     * Constructor
     */
    public GrabImageRequest() {
        super(APSMessage.GI_REQ);
        this.setBodyLen(16);
    }
    /**
     * 
     * Constructor
     * @param xStart
     * @param yStart
     * @param width
     * @param height
     */
    public GrabImageRequest(int xStart,int yStart,int width,int height) {
        super(APSMessage.GI_REQ);
        this.xStart = xStart;
        this.yStart = yStart;
        this.width = width;
        this.height = height;
        this.setBodyLen(16);
    }
    /**
     * 
     * (non-Javadoc)
     * @see com.aspire.mcts.agent.msg.APSMessage#encodeBody(java.nio.ByteBuffer)
     */
    @Override
    protected int encodeBody(ByteBuffer destination) throws ToolException {
     // 正常返回0
        destination.putInt(xStart);
        destination.putInt(yStart);
        destination.putInt(width);
        destination.putInt(height);
        return 16;
    }
    /**
     * 
     * (non-Javadoc)
     * @see com.aspire.mcts.agent.msg.APSMessage#decodeBody(java.nio.ByteBuffer)
     */
    @Override
    protected void decodeBody(ByteBuffer source) throws ToolException {
        if (source.remaining() < 16) {
            throw new ToolException("Bad message grabImage body length, need ["
                    + 16 + "], but remaining [" + source.remaining()
                    + "]");
        }
        setXStart(source.getInt());
        source.position(LEN_CODE_LEN);
        setYStart(source.getInt());
        source.position(LEN_CODE_LEN);
        setWidth(source.getInt());
        source.position(LEN_CODE_LEN);
        setHeight(source.getInt());
    }
    /**
     * get the start coordinate of x-axis 
     * @return
     */
    public int getXStart() {
        return xStart;
    }
    /**
     * set the start coordinate of x-axis 
     * @param xStart
     */
    public void setXStart(int xStart) {
        this.xStart = xStart;
    }
    /**
     * get the start coordinate of y-axis 
     * @return
     */
    public int getYStart() {
        return yStart;
    }
    /**
     * set the start coordinate of y-axis 
     * @param yStart
     */
    public void setYStart(int yStart) {
        this.yStart = yStart;
    }
    /**
     * get the image width
     * @return
     */
    public int getWidth() {
        return width;
    }
    /**
     * set the image width
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }
    /**
     * get the image height
     * @return
     */
    public int getHeight() {
        return height;
    }
    /**
     * set the image height
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

}
