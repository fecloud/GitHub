package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;
import com.aspire.util.ToolException;
/**
 * 
 * The class <code>KeySequenceResponse</code>
 *
 * @author Fan Yang
 * @version 1.0
 */
public class KeySequenceResponse extends CommonResponse {
    /**
     * 
     * Constructor
     */
    public KeySequenceResponse(){
        super(APSMessage.KS_RESP);
    }
    /**
     * 
     * Constructor
     * @param errcode
     */
    public KeySequenceResponse(int errcode) {
        super(APSMessage.KS_RESP);
        super.setErrcode(errcode);
        super.setErrormsg(null);
    }
    /**
     * 
     * Constructor
     * @param errcode
     * @param errormsg
     */
    public KeySequenceResponse(int errcode,String errormsg) {
        super(APSMessage.KS_RESP);
        super.setErrcode(errcode);
        super.setErrormsg(errormsg);
    }
}
