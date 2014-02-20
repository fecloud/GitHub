package com.aspire.mcts.agent.msg;


public class HeartBeatRequest extends APSMessage {
    public HeartBeatRequest() {
        super(APSMessage.HB_REQ);
    }
}
