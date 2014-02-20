/**
 * @(#) APSMessageCodec.java Created on 2012-3-2
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.mcts.agent.msg;

import java.nio.ByteBuffer;

import com.aspire.util.ToolException;

/**
 * The class <code>APSMessageCodec</code>
 * 
 * @author Link Wang
 * @version 1.0
 */
public class APSMessageCodec {

    public APSMessage decode(ByteBuffer source) throws ToolException {
        APSMessage message = null;
        short msgType = source.getShort(APSMessage.OFFSET_TYPE);
        switch (msgType) {
        case APSMessage.HB_REQ:
            message = new HeartBeatRequest();
            break;
        case APSMessage.HB_RESP:
            message = new HeartBeatResponse();
            break;    
        case APSMessage.GI_REQ:
            message = new GrabImageRequest();
            break;
        case APSMessage.GI_RESP:
            message = new GrabImageResponse();
            break;
        case APSMessage.GSD_REQ:
            message = new GetScreenDimensionsRequest();
            break;
        case APSMessage.GSD_RESP:
            message = new GetScreenDimensionsResponse();
            break;
        case APSMessage.SIP_REQ:
            message = new SetImageParamRequest();
            break;
        case APSMessage.SIP_RESP:
            message = new SetImageParamResponse();
            break;
        case APSMessage.KC_REQ:
            message = new KeyClickRequest();
            break;
        case APSMessage.KC_RESP:
            message = new KeyClickResponse();
            break;
        case APSMessage.KU_REQ:
            message = new KeyUpRequest();
            break;
        case APSMessage.KU_RESP:
            message = new KeyUpResponse();
            break;
        case APSMessage.KD_REQ:
            message = new KeyDownRequest();
            break;
        case APSMessage.KD_RESP:
            message = new KeyDownResponse();
            break;
        case APSMessage.KS_REQ:
            message = new KeySequenceRequest();
            break;
        case APSMessage.KS_RESP:
            message = new KeySequenceResponse();
            break;
        case APSMessage.TC_REQ:
            message = new TouchClickRequest();
            break;
        case APSMessage.TC_RESP:
            message = new TouchClickResponse();
            break;
        case APSMessage.TU_REQ:
            message = new TouchUpRequest();
            break;
        case APSMessage.TU_RESP:
            message = new TouchUpResponse();
            break;
        case APSMessage.TD_REQ:
            message = new TouchDownRequest();
            break;
        case APSMessage.TD_RESP:
            message = new TouchDownResponse();
            break;
        case APSMessage.TDC_REQ:
            message = new TouchDClickRequest();
            break;
        case APSMessage.TDC_RESP:
            message = new TouchDClickResponse();
            break;
        case APSMessage.TM_REQ:
            message = new TouchMoveRequest();
            break;
        case APSMessage.TM_RESP:
            message = new TouchMoveResponse();
            break;
        case APSMessage.TS_REQ:
            message = new TouchSlideRequest();
            break;
        case APSMessage.TS_RESP:
            message = new TouchSlideResponse();
            break;
        case APSMessage.SKI_REQ:
            message = new SetKeyIntervalRequest();
            break;
        case APSMessage.SKI_RESP:
            message = new SetKeyIntervalResponse();
            break;
        case APSMessage.CFO_REQ:
            message = new CardFileOpenRequest();
            break;
        case APSMessage.CFO_RESP:
            message = new CardFileOpenResponse();
            break;
        case APSMessage.CFC_REQ:
            message = new CardFileCloseRequest();
            break;
        case APSMessage.CFC_RESP:
            message = new CardFileCloseResponse();
            break;
        case APSMessage.CFR_REQ:
            message = new CardFileReadRequset();
            break;
        case APSMessage.CFR_RESP:
            message = new CardFileReadResponse();
            break;
        case APSMessage.CFW_REQ:
            message = new CardFileWriteRequset();
            break;
        case APSMessage.CFW_RESP:
            message = new CardFileWriteResponse();
            break;
        case APSMessage.MTD_REQ:
            message = new MutilTouchDownRequest();
            break;
        case APSMessage.MTD_RESP:
            message = new MutilTouchDownResponse();
            break;
        case APSMessage.MTM_REQ:
            message = new MutilTouchMoveRequest();
            break;
        case APSMessage.MTM_RESP:
            message = new MutilTouchMoveResponse();
            break;
        case APSMessage.MTU_REQ:
            message = new MutilTouchUpRequest();
            break;
        case APSMessage.MTU_RESP:
            message = new MutilTouchUpResponse();
            break;
        case APSMessage.FO_REQ:
            message = new FileOperationRequest();
            break;
        case APSMessage.FO_RESP:
            message = new FileOperationResponse();
            break;
        case APSMessage.ER_REQ:
            message = new ErrorReportRequest();
            break;
        case APSMessage.TT_RESP:
            message = new TTResponse();
            break;
        case APSMessage.TT_REQ:
            message = new TTRequest();
            break;
        default:
            throw new ToolException("UnSupport Type of APSMessage.");
        }
        message.decode(source);
        return message;
    }

    public ByteBuffer encode(APSMessage message) throws ToolException {
        switch (message.getType()) {
        case APSMessage.TT_RESP:
            return encodeASPMTEPmsg(message, null);
        default:
            return encode(message, null);
        }
    }

    public ByteBuffer encodeASPMTEPmsg(APSMessage message, ByteBuffer destination) throws ToolException {
        if (destination == null) {
            destination = ByteBuffer.allocate(APSMessage.MAX_MSG_LEN);
        }
        return message.encode(destination);
    }

    public ByteBuffer encode(APSMessage message, ByteBuffer destination) throws ToolException {
        if (destination == null) {
            destination = ByteBuffer.allocate(APSMessage.MAX_MSG_LEN);
        }
        return message.encode(destination);
    }
}
