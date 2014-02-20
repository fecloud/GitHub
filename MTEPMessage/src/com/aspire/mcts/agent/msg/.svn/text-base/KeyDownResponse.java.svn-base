package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;
import com.aspire.util.ToolException;
/**
 * 
 * The class <code>KeyDownResponse</code>
 *
 * @author Fan Yang
 * @version 1.0
 */
public class KeyDownResponse extends CommonResponse {
    /**
     * 
     * Constructor
     */
    public KeyDownResponse(){
        super(APSMessage.KD_RESP);
    }
    /**
     * 
     * Constructor
     * @param errcode
     */
    public KeyDownResponse(int errcode) {
        super(APSMessage.KD_RESP);
        super.setErrcode(errcode);
        super.setErrormsg(null);
    }
    /**
     * 
     * Constructor
     * @param errcode
     * @param errormsg
     */
    public KeyDownResponse(int errcode,String errormsg) {
        super(APSMessage.KD_RESP);
        super.setErrcode(errcode);
        super.setErrormsg(errormsg);
    }
}
