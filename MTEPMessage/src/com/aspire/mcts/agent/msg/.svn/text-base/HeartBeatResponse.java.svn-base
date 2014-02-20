package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;

import com.aspire.util.ToolException;

public class HeartBeatResponse extends APSMessage {
    
    public final static int STATUS_OK = 0;
    public final static int STATUS_FAIL = 1;
    
    private int status;

    public HeartBeatResponse() {
        super(APSMessage.HB_RESP);
    }    
    
    public HeartBeatResponse(int status) {
        super(APSMessage.HB_RESP);
        this.status = status;
    }

	@Override
	protected int encodeBody(ByteBuffer destination) throws ToolException {
	 // 正常返回0
		destination.putInt(status);
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
            throw new ToolException("Bad message code length, need ["
                    + LEN_CODE_LEN + "], but remaining [" + source.remaining()
                    + "]");
        }
        setStatus(source.getInt());
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
