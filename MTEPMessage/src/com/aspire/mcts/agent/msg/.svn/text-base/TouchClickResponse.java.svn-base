package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;
import com.aspire.util.ToolException;
/**
 * 
 * The class <code>TouchClickResponse</code>
 *
 * @author Fan Yang
 * @version 1.0
 */
public class TouchClickResponse extends CommonResponse {
    /**
     * 
     * Constructor
     */
    public TouchClickResponse(){
        super(APSMessage.TC_RESP);
    }
    /**
     * 
     * Constructor
     * @param errcode
     */
    public TouchClickResponse(int errcode) {
        super(APSMessage.TC_RESP);
        super.setErrcode(errcode);
        super.setErrormsg(null);
    }
    /**
     * 
     * Constructor
     * @param errcode
     * @param errormsg
     */
    public TouchClickResponse(int errcode,String errormsg) {
        super(APSMessage.TC_RESP);
        super.setErrcode(errcode);
        super.setErrormsg(errormsg);
    }
}
