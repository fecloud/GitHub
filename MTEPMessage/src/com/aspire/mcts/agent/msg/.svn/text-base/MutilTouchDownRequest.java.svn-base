/**
 * @(#) MutilTouchDownRequest.java Created on 2012-8-8
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;

import com.aspire.util.ToolException;

/**
 * The class <code>MutilTouchDownRequest</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class MutilTouchDownRequest extends MutilTouchMoveRequest {

    protected int interDelayOne;

    protected int interDelayTwo;

    protected int interDelayThree;

    /**
     * Constructor
     * 
     * @param type
     */
    public MutilTouchDownRequest() {
        super(APSMessage.MTD_REQ);
    }

    /**
     * Constructor
     * 
     * @param type
     */
    public MutilTouchDownRequest(short type) {
        super(type);
    }

    /**
     * Constructor
     * 
     * @param x
     * @param y
     * @param interDelay
     * @param x1
     * @param y1
     * @param interDelay1
     */
    public MutilTouchDownRequest(int x, int y, int interDelay, int x1, int y1, int interDelay1) {
        super(x, y, x1, y1);
        this.interDelayOne = interDelay;
        this.interDelayTwo = interDelay1;
        setType(APSMessage.MTD_REQ);
    }

    /**
     * Constructor
     * 
     * @param x
     * @param y
     * @param interDelay
     * @param x1
     * @param y1
     * @param interDelay1
     * @param x2
     * @param y2
     * @param interDelay2
     */
    public MutilTouchDownRequest(int x, int y, int interDelay, int x1, int y1, int interDelay1, int x2, int y2,
            int interDelay2) {
        super(x, y, x1, y1, x2, y2);
        this.interDelayOne = interDelay;
        this.interDelayTwo = interDelay1;
        this.interDelayThree = interDelay2;
        setType(APSMessage.MTD_REQ);
    }

    @Override
    protected void decodeBody(ByteBuffer source) throws ToolException {
        if (source.remaining() < LEN_XY_LEN + LEN_CODE_LEN) {
            throw new ToolException("Bad message mutilTouchMoveRequest body length, need [" + LEN_XY_LEN
                    + "], but remaining [" + source.remaining() + "]");
        }
        setPointOne(new int[] { source.getInt(), source.getInt() });
        setInterDelayOne(source.getInt());

        if (source.remaining() < LEN_XY_LEN + LEN_CODE_LEN) {
            throw new ToolException("Bad message mutilTouchMoveRequest body length, need [" + LEN_XY_LEN
                    + "], but remaining [" + source.remaining() + "]");
        }
        setPointTwo(new int[] { source.getInt(), source.getInt() });
        setInterDelayTwo(source.getInt());

        if (source.remaining() >= LEN_XY_LEN + LEN_CODE_LEN) {
            setPointThree(new int[] { source.getInt(), source.getInt() });
            setInterDelayThree(source.getInt());
        }
    }

    @Override
    protected int encodeBody(ByteBuffer destination) throws ToolException {
        int nLen = 0;
        if (null != pointOne && pointOne.length == 2) {
            destination.putInt(pointOne[0]);
            destination.putInt(pointOne[1]);
            destination.putInt(interDelayOne);
            nLen += 12;
        }
        if (null != pointTwo && pointTwo.length == 2) {
            destination.putInt(pointTwo[0]);
            destination.putInt(pointTwo[1]);
            destination.putInt(interDelayTwo);
            nLen += 12;
        }
        if (null != pointThree && pointThree.length == 2) {
            destination.putInt(pointThree[0]);
            destination.putInt(pointThree[1]);
            destination.putInt(interDelayThree);
            nLen += 12;
        }
        return nLen;
    }

    public int getInterDelayOne() {
        return interDelayOne;
    }

    public void setInterDelayOne(int interDelayOne) {
        this.interDelayOne = interDelayOne;
    }

    public int getInterDelayTwo() {
        return interDelayTwo;
    }

    public void setInterDelayTwo(int interDelayTwo) {
        this.interDelayTwo = interDelayTwo;
    }

    public int getInterDelayThree() {
        return interDelayThree;
    }

    public void setInterDelayThree(int interDelayThree) {
        this.interDelayThree = interDelayThree;
    }

}
