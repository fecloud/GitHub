package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;
import com.aspire.util.ToolException;
/**
 * 
 * The class <code>TouchMoveResponse</code>
 *
 * @author Fan Yang
 * @version 1.0
 */
public class TouchMoveResponse extends CommonResponse {
    /**
     * 
     * Constructor
     */
    public TouchMoveResponse(){
        super(APSMessage.TM_RESP);
    }
    /**
     * 
     * Constructor
     * @param errcode
     */
    public TouchMoveResponse(int errcode) {
        super(APSMessage.TM_RESP);
        super.setErrcode(errcode);
        super.setErrormsg(null);
    }
    /**
     * 
     * Constructor
     * @param errcode
     * @param errormsg
     */
    public TouchMoveResponse(int errcode,String errormsg) {
        super(APSMessage.TM_RESP);
        super.setErrcode(errcode);
        super.setErrormsg(errormsg);
    }
}
