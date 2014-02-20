package com.aspire.mose.frame.net.protocol.handshake;

import java.io.IOException;

import com.aspire.mose.frame.net.protocol.handshake.example.HSMsg;

public interface IHKInputListener {
	public void receive(HSMsg msg) throws IOException;
}
