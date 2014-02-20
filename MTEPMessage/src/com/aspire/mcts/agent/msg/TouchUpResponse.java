package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;
import com.aspire.util.ToolException;
/**
 * 
 * The class <code>TouchUpResponse</code>
 *
 * @author Fan Yang
 * @version 1.0
 */
public class TouchUpResponse extends CommonResponse {
    /**
     * 
     * Constructor
     */
    public TouchUpResponse(){
        super(APSMessage.TU_RESP);
    }
    /**
     * 
     * Constructor
     * @param errcode
     */
    public TouchUpResponse(int errcode) {
        super(APSMessage.TU_RESP);
        super.setErrcode(errcode);
        super.setErrormsg(null);
    }
    /**
     * 
     * Constructor
     * @param errcode
     * @param errormsg
     */
    public TouchUpResponse(int errcode,String errormsg) {
        super(APSMessage.TU_RESP);
        super.setErrcode(errcode);
        super.setErrormsg(errormsg);
    }
}
