package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;
import com.aspire.util.ToolException;
/**
 * 
 * The class <code>GetScreenDimensionsResponse</code>
 *
 * @author Fan Yang
 * @version 1.0
 */
public class GetScreenDimensionsResponse extends CommonResponse {
    /**
     * the screen width
     */
    private int width;
    /**
     * the ecreen height
     */
    private int height;
    /**
     * 
     * Constructor
     */
    public GetScreenDimensionsResponse(){
        super(APSMessage.GSD_RESP);
    }
    /**
     * 
     * Constructor
     * @param errcode
     */
    public GetScreenDimensionsResponse(int errcode) {
        super(APSMessage.GSD_RESP);
        super.setErrcode(errcode);
        super.setErrormsg(null);
    }
    /**
     * 
     * Constructor
     * @param errcode
     * @param errormsg
     */
    public GetScreenDimensionsResponse(int errcode,String errormsg) {
        super(APSMessage.GSD_RESP);
        super.setErrcode(errcode);
        super.setErrormsg(errormsg);
    }
    /**
     * 
     * Constructor
     * @param width
     * @param height
     */
    public GetScreenDimensionsResponse(int width,int height) {
        super(APSMessage.KC_RESP);
        super.setErrcode(0);
        this.width = width;
        this.height = height;
    }
    /**
     * 
     * (non-Javadoc)
     * @see com.aspire.mcts.agent.msg.APSMessage#encodeBody(java.nio.ByteBuffer)
     */
    @Override
    protected int encodeBody(ByteBuffer destination) throws ToolException {
        // 正常返回0
        int nLen = 4;
        destination.putInt(super.getErrcode());
        if(null!=super.getErrormsg() && 0<super.getErrcode())
        {
            destination.put(super.getErrormsg().getBytes());
            nLen += super.getErrormsg().getBytes().length;
        }else if(0==super.getErrcode())
        {
            destination.putInt(width);
            destination.putInt(height);
            nLen += 8;
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
        setErrcode(source.getInt());
        if(0<super.getErrcode())
        {
            if (source.remaining() > 1) {
                int bodylen = source.remaining();
                byte[] srtbyte = new byte[bodylen];
                source.get(srtbyte);
                setErrormsg(new String(srtbyte));
            }
        }else if(0==super.getErrcode())
        {
            setWidth(source.getInt());
            setHeight(source.getInt());
        }
    }
    /**
     * get the screen width
     * @return
     */
    public int getWidth() {
        return width;
    }
    /**
     * set the screen width
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }
    /**
     * get the screen height
     * @return
     */
    public int getHeight() {
        return height;
    }
    /**
     * set the screen height
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }
}
