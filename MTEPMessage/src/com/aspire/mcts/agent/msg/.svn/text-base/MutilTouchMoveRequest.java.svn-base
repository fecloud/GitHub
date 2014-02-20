/**
 * @(#) MutilTouchMoveRequest.java Created on 2012-8-8
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;

import com.aspire.util.ToolException;

/**
 * The class <code>MutilTouchMoveRequest</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class MutilTouchMoveRequest extends APSMessage {

    protected int[] pointOne;

    protected int[] pointTwo;

    protected int[] pointThree;

    /**
     * Constructor
     * 
     * @param type
     */
    public MutilTouchMoveRequest() {
        super(APSMessage.MTM_REQ);
    }
    public MutilTouchMoveRequest(short type) {
        super(type);
    }

    /**
     * Constructor
     * @param x
     * @param y
     * @param x1
     * @param y1
     */
    public MutilTouchMoveRequest(int x, int y, int x1, int y1) {
        this();
        this.pointOne = new int[] { x, y };
        this.pointTwo = new int[] { x1, y1 };
    }

    /**
     * Constructor
     * @param x
     * @param y
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public MutilTouchMoveRequest(int x, int y, int x1, int y1, int x2, int y2) {
        this(x, y, x1, y1);
        this.pointThree = new int[] { x2, y2 };
    }

    @Override
    protected void decodeBody(ByteBuffer source) throws ToolException {
        if (source.remaining() < LEN_XY_LEN) {
            throw new ToolException("Bad message mutilTouchMoveRequest body length, need [" + LEN_XY_LEN
                    + "], but remaining [" + source.remaining() + "]");
        }
        setPointOne(new int[] { source.getInt(), source.getInt() });
        if (source.remaining() < LEN_XY_LEN) {
            throw new ToolException("Bad message mutilTouchMoveRequest body length, need [" + LEN_XY_LEN
                    + "], but remaining [" + source.remaining() + "]");
        }
        setPointTwo(new int[] { source.getInt(), source.getInt() });
        if (source.remaining() >= LEN_XY_LEN) {
            setPointThree(new int[] { source.getInt(), source.getInt() });
        }
    }

    @Override
    protected int encodeBody(ByteBuffer destination) throws ToolException {
        int nLen = 0;
        if (null != pointOne && pointOne.length == 2) {
            destination.putInt(pointOne[0]);
            destination.putInt(pointOne[1]);
            nLen += 8;
        }
        if (null != pointTwo && pointTwo.length == 2) {
            destination.putInt(pointTwo[0]);
            destination.putInt(pointTwo[1]);
            nLen += 8;
        }
        if (null != pointThree && pointThree.length == 2) {
            destination.putInt(pointThree[0]);
            destination.putInt(pointThree[1]);
            nLen += 8;
        }

        return nLen;
    }

    public int[] getPointOne() {
        return pointOne;
    }

    public void setPointOne(int[] pointOne) {
        this.pointOne = pointOne;
    }

    public int[] getPointTwo() {
        return pointTwo;
    }

    public void setPointTwo(int[] pointTwo) {
        this.pointTwo = pointTwo;
    }

    public int[] getPointThree() {
        return pointThree;
    }

    public void setPointThree(int[] pointThree) {
        this.pointThree = pointThree;
    }

}
