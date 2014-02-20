package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;

import com.aspire.util.ToolException;

/**
 * 
 * The class <code>RawTypeMessage</code>
 *
 * @author zhenghui
 * @version 1.0
 */
public class RawTypeMessage extends APSMessage {

	public RawTypeMessage(short type) {
		super(type);
	}

	private byte[] body;

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}

	@Override
	protected void decodeBody(ByteBuffer source) throws ToolException {
		body = new byte[source.remaining()];
		source.get(body);
	}

	@Override
	protected int encodeBody(ByteBuffer destination) throws ToolException {
		destination.put(body);
		return body.length;
	}
}
