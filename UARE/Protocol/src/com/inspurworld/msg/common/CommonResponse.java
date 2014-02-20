package com.inspurworld.msg.common;

import java.nio.ByteBuffer;

import com.inspurworld.msg.APSMessage;
import com.inspurworld.msg.exception.ToolException;

/**
 * 
 * The class <code>CommonResponse</code>
 *
 * @author Fan Yang
 * @version 1.0
 */
public class CommonResponse extends APSMessage {
    /**
     * The ok statue
     */
    public final static int STATUS_OK = 0;
    /**
     * The fail statue
     */
    public final static int STATUS_FAIL = 1;
    /**
     * the error code of response return 
     */
    private int errcode = STATUS_OK;
    /**
     * the error message of response return
     */
    private String errormsg;
    /**
     * 
     * Constructor
     * @param type
     */
    public CommonResponse(short type){
        super(type);
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
        destination.putInt(errcode);
        if(null!=errormsg)
        {
            destination.put(errormsg.getBytes());
            nLen += errormsg.getBytes().length;
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
        if (source.remaining() > 1) {
            int bodylen = source.remaining();
            byte[] srtbyte = new byte[bodylen];
            source.get(srtbyte);
            setErrormsg(new String(srtbyte));
        }
    }
    
    /**
     * get the error code of response return
     * @return
     */
    public int getErrcode() {
        return errcode;
    }
    /**
     * set the error code of response return
     * @param errcode
     */
    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }
    /**
     * get the error message of response return
     * @return
     */
    public String getErrormsg() {
        return errormsg;
    }
    /**
     * set the error message of response return
     * @param errormsg
     */
    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }
}
