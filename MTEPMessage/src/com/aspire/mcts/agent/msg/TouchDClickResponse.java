package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;
import com.aspire.util.ToolException;
/**
 * 
 * The class <code>TouchDClickResponse</code>
 *
 * @author Fan Yang
 * @version 1.0
 */
public class TouchDClickResponse extends CommonResponse {
    /**
     * 
     * Constructor
     */
    public TouchDClickResponse(){
        super(APSMessage.TDC_RESP);
    }
    /**
     * 
     * Constructor
     * @param errcode
     */
    public TouchDClickResponse(int errcode) {
        super(APSMessage.TDC_RESP);
        super.setErrcode(errcode);
        super.setErrormsg(null);
    }
    /**
     * 
     * Constructor
     * @param errcode
     * @param errormsg
     */
    public TouchDClickResponse(int errcode,String errormsg) {
        super(APSMessage.TDC_RESP);
        super.setErrcode(errcode);
        super.setErrormsg(errormsg);
    }
}
