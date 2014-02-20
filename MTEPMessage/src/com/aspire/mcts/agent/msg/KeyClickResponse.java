package com.aspire.mcts.agent.msg;

public class KeyClickResponse extends CommonResponse {
    /**
     * 
     * Constructor
     */
    public KeyClickResponse(){
        super(APSMessage.KC_RESP);
    }
    /**
     * 
     * Constructor
     * @param errcode
     */
    public KeyClickResponse(int errcode) {
        super(APSMessage.KC_RESP);
        super.setErrcode(errcode);
        super.setErrormsg(null);
    }
    /**
     * 
     * Constructor
     * @param errcode
     * @param errormsg
     */
    public KeyClickResponse(int errcode,String errormsg) {
        super(APSMessage.KC_RESP);
        super.setErrcode(errcode);
        super.setErrormsg(errormsg);
    }
}
