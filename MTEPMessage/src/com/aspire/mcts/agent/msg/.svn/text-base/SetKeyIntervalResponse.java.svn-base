package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;
import com.aspire.util.ToolException;
/**
 * 
 * The class <code>SetKeyIntervalResponse</code>
 *
 * @author Fan Yang
 * @version 1.0
 */
public class SetKeyIntervalResponse extends CommonResponse {
    /**
     * 
     * Constructor
     */
    public SetKeyIntervalResponse(){
        super(APSMessage.SKI_RESP);
    }
    /**
     * 
     * Constructor
     * @param errcode
     */
    public SetKeyIntervalResponse(int errcode) {
        super(APSMessage.SKI_RESP);
        super.setErrcode(errcode);
        super.setErrormsg(null);
    }
    /**
     * 
     * Constructor
     * @param errcode
     * @param errormsg
     */
    public SetKeyIntervalResponse(int errcode,String errormsg) {
        super(APSMessage.SKI_RESP);
        super.setErrcode(errcode);
        super.setErrormsg(errormsg);
    }
}
