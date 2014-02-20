package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;
import com.aspire.util.ToolException;
/**
 * 
 * The class <code>TouchDownResponse</code>
 *
 * @author Fan Yang
 * @version 1.0
 */
public class TouchDownResponse extends CommonResponse {
    /**
     * 
     * Constructor
     */
    public TouchDownResponse(){
        super(APSMessage.TD_RESP);
    }
    /**
     * 
     * Constructor
     * @param errcode
     */
    public TouchDownResponse(int errcode) {
        super(APSMessage.TD_RESP);
        super.setErrcode(errcode);
        super.setErrormsg(null);
    }
    /**
     * 
     * Constructor
     * @param errcode
     * @param errormsg
     */
    public TouchDownResponse(int errcode,String errormsg) {
        super(APSMessage.TD_RESP);
        super.setErrcode(errcode);
        super.setErrormsg(errormsg);
    }
}
