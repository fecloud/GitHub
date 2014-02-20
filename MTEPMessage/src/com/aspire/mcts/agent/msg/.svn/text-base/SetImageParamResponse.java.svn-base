package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;
import com.aspire.util.ToolException;
/**
 * 
 * The class <code>SetImageParamResponse</code>
 *
 * @author Fan Yang
 * @version 1.0
 */
public class SetImageParamResponse extends CommonResponse {
    /**
     * 
     * Constructor
     */
    public SetImageParamResponse(){
        super(APSMessage.SIP_RESP);
    }
    /**
     * 
     * Constructor
     * @param errcode
     */
    public SetImageParamResponse(int errcode) {
        super(APSMessage.SIP_RESP);
        super.setErrcode(errcode);
        super.setErrormsg(null);
    }
    /**
     * 
     * Constructor
     * @param errcode
     * @param errormsg
     */
    public SetImageParamResponse(int errcode,String errormsg) {
        super(APSMessage.SIP_RESP);
        super.setErrcode(errcode);
        super.setErrormsg(errormsg);
    }
}
