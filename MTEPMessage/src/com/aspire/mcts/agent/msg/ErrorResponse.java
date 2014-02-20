package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;

import com.aspire.util.ToolException;

public class ErrorResponse extends AgentResponse {
    
    public final static int ERROR_CODE = 1;

    public ErrorResponse(short type) {
        super(type, ERROR_CODE);
    }

	@Override
	protected int encodeBody(ByteBuffer destination) throws ToolException {
		destination.putInt(code);
		return 4;
	}
}
