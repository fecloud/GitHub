package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;
import com.aspire.util.ToolException;
/**
 * 
 * The class <code>SetImageParamRequest</code>
 *
 * @author Fan Yang
 * @version 1.0
 */
public class SetImageParamRequest extends APSMessage {
    /**
     * the display ID
     */
    private int Display_ID;
    /**
     * the length of param
     */
    private short Length1;
    /**
     * the param ID
     */
    private short Param_ID1;
    /**
     * the array of param
     */
    private short[] Param1;
    /**
     * 
     * Constructor
     */
    public SetImageParamRequest() {
        super(APSMessage.SIP_REQ);
    }
    /**
     * 
     * Constructor
     * @param Display_ID
     * @param Length1
     * @param Param_ID1
     * @param Param1
     */
    public SetImageParamRequest(int Display_ID,short Length1,short Param_ID1,short[] Param1) {
        super(APSMessage.SIP_REQ);
        this.Display_ID = Display_ID;
        this.Length1 = Length1;
        this.Param_ID1 = Param_ID1;
        this.Param1 = Param1;
        //this.Param1 = new short[Param1.length];
        //System.arraycopy(Param1,0,this.Param1,0,Param1.length);
    }
    /**
     * 
     * (non-Javadoc)
     * @see com.aspire.mcts.agent.msg.APSMessage#encodeBody(java.nio.ByteBuffer)
     */
    @Override
    protected int encodeBody(ByteBuffer destination) throws ToolException {
     // 正常返回0
        int nLen = 6;
        destination.putInt(Display_ID);
        destination.putShort((short)(Param1.length*2 +2));
        destination.putShort(Param_ID1);
        for(int i=0;i<Param1.length;i++){
            destination.putShort(Param1[i]);
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
        if (source.remaining() < LEN_CODE_LEN) {
            throw new ToolException("Bad message code length, need ["
                    + LEN_CODE_LEN + "], but remaining [" + source.remaining()
                    + "]");
        }
        setDisplay_ID(source.getInt());
        if (source.remaining() > 1) {
            setLength1(source.getShort());
        }
        if (source.remaining() > 1) {
            setParam_ID1(source.getShort());
        }
        if (source.remaining() > 1) {
            int len = (Length1-2)/2;
            short[] tmp = new short[len];
            for(int i=0;i<len;i++){
                tmp[i] = source.getShort();
            }
            setParam1(tmp);
        }
    }
    /**
     * get the display ID
     * @return
     */
    public int getDisplay_ID() {
        return Display_ID;
    }
    /**
     * set the display ID
     * @param Display_ID
     */
    public void setDisplay_ID(int Display_ID) {
        this.Display_ID = Display_ID;
    }
    /**
     * get the length of param
     * @return
     */
    public short getLength1() {
        return Length1;
    }
    /**
     * set the length of param
     * @param Length1
     */
    public void setLength1(short Length1) {
        this.Length1 = Length1;
    }
    /**
     * get the param ID
     * @return
     */
    public short getParam_ID1() {
        return Param_ID1;
    }
    /**
     * set the param ID
     * @param Param_ID1
     */
    public void setParam_ID1(short Param_ID1) {
        this.Param_ID1 = Param_ID1;
    }
    /**
     * get the array of param
     * @return
     */
    public short[] getParam1() {
        return Param1;
    }
    /**
     * set the array of param
     * @param Param1
     */
    public void setParam1(short[] Param1) {
        this.Param1 = Param1;
        //this.Param1 = null;
        //this.Param1 = new short[Param1.length];
        //System.arraycopy(Param1,0,this.Param1,0,Param1.length);
    }
}

