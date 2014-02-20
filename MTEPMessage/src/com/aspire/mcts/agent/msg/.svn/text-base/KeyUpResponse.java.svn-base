package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;
import com.aspire.util.ToolException;
/**
 * 
 * The class <code>KeyUpResponse</code>
 *
 * @author Fan Yang
 * @version 1.0
 */
public class KeyUpResponse extends CommonResponse {
    /**
     * 
     * Constructor
     */
    public KeyUpResponse(){
        super(APSMessage.KU_RESP);
    }
    /**
     * 
     * Constructor
     * @param errcode
     */
    public KeyUpResponse(int errcode) {
        super(APSMessage.KU_RESP);
        super.setErrcode(errcode);
        super.setErrormsg(null);
    }
    /**
     * 
     * Constructor
     * @param errcode
     * @param errormsg
     */
    public KeyUpResponse(int errcode,String errormsg) {
        super(APSMessage.KU_RESP);
        super.setErrcode(errcode);
        super.setErrormsg(errormsg);
    }
}
