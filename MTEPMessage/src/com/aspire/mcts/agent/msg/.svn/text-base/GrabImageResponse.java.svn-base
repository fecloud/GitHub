package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;
import com.aspire.util.ToolException;
/**
 * 
 * The class <code>GrabImageResponse</code>
 *
 * @author Fan Yang
 * @version 1.0
 */
public class GrabImageResponse extends CommonResponse {
    /**
     * the image message
     */
    private ImageMessage imgmsg;
    /**
     * 
     * Constructor
     */
    public GrabImageResponse(){
        super(APSMessage.GI_RESP);
    }
    /**
     * 
     * Constructor
     * @param errcode
     */
    public GrabImageResponse(int errcode) {
        super(APSMessage.GI_RESP);
        super.setErrcode(errcode);
        super.setErrormsg(null);
    }
    /**
     * 
     * Constructor
     * @param errcode
     * @param errormsg
     */
    public GrabImageResponse(int errcode,String errormsg) {
        super(APSMessage.GI_RESP);
        super.setErrcode(errcode);
        super.setErrormsg(errormsg);
    }
    /**
     * 
     * (non-Javadoc)
     * @see com.aspire.mcts.agent.msg.CommonResponse#encodeBody(java.nio.ByteBuffer)
     */
    @Override
    protected int encodeBody(ByteBuffer destination) throws ToolException {
     // 正常返回0
        int nLen = 4;
        destination.putInt(super.getErrcode());
        if(null!=super.getErrormsg() && 0!=super.getErrcode())
        {
            destination.put(super.getErrormsg().getBytes());
            nLen += super.getErrormsg().getBytes().length;
        }else if(null!=imgmsg && 0==super.getErrcode()){
            destination.putInt(imgmsg.getTotalPackageCount());
            destination.putInt(imgmsg.getCurrentPackageSequenceNumber());
            destination.put(imgmsg.getImageData());                
        }
        return nLen;
    }
    /**
     * 
     * (non-Javadoc)
     * @see com.aspire.mcts.agent.msg.CommonResponse#decodeBody(java.nio.ByteBuffer)
     */
    @Override
    protected void decodeBody(ByteBuffer source) throws ToolException {
        if (source.remaining() < LEN_CODE_LEN) {
            throw new ToolException("Bad message code length, need ["
                    + LEN_CODE_LEN + "], but remaining [" + source.remaining()
                    + "]");
        }
        setErrcode(source.getInt());
        if(0!=super.getErrcode())
        {
            if (source.position(LEN_CODE_LEN).remaining() > 1) {
                int bodylen = source.remaining();
                byte[] srtbyte = new byte[bodylen];
                source.get(srtbyte);
                setErrormsg(new String(srtbyte));
            }
        }else if(0==super.getErrcode()){
            if (source.remaining() > 1) {
                ImageMessage mimgmsg = new ImageMessage();
                mimgmsg.setTotalPackageCount(source.getInt());
                if (source.remaining() > 1) {
                    mimgmsg.setCurrentPackageSequenceNumber(source.getInt());
                    if (source.remaining() > 1) {
                        int bodylen = source.remaining();
                        byte[] srtbyte = new byte[bodylen];
                        source.get(srtbyte);
                        mimgmsg.setImageData(srtbyte);
                        setImageMessage(mimgmsg);
                    }
                }
            }              
        }
    }
    /**
    * get the image message
    * @return
    */
    public ImageMessage getImageMessage() {
        return imgmsg;
    }
    /**
     * set the image message
     * @param imgmsg
     */
    public void setImageMessage(ImageMessage imgmsg) {
        this.imgmsg = imgmsg;
    }
}
