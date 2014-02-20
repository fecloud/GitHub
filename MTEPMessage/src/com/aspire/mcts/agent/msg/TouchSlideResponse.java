package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;
import com.aspire.util.ToolException;
/**
 * 
 * The class <code>TouchSlideResponse</code>
 *
 * @author Fan Yang
 * @version 1.0
 */
public class TouchSlideResponse extends CommonResponse {
    /**
     * 
     * Constructor
     */
    public TouchSlideResponse(){
        super(APSMessage.TS_RESP);
    }
    /**
     * 
     * Constructor
     * @param errcode
     */
    public TouchSlideResponse(int errcode) {
        super(APSMessage.TS_RESP);
        super.setErrcode(errcode);
        super.setErrormsg(null);
    }
    /**
     * 
     * Constructor
     * @param errcode
     * @param errormsg
     */
    public TouchSlideResponse(int errcode,String errormsg) {
        super(APSMessage.TS_RESP);
        super.setErrcode(errcode);
        super.setErrormsg(errormsg);
    }
}
