package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;

import com.aspire.util.ToolException;

public class AgentResponse extends APSMessage {
    
    public final static int STATUS_OK = 0;
    public final static int STATUS_FAIL = 1;
    
    protected int code;
    
//    private String message;

    public AgentResponse(short type, int code) {
        super(type);
        this.code = code;
    }

	@Override
	protected int encodeBody(ByteBuffer destination) throws ToolException {
		destination.putInt(code);
		return 4;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
