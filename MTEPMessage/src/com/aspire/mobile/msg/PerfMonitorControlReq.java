/**
 * @(#) PerfMonitorControlReq.java Created on 2009-2-18
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.msg;

import com.aspire.Constants;
import com.aspire.mobile.MobileConstants;
import com.aspire.mobile.perf.PerfMonitorConfig;
import com.aspire.msg.MsgBase;
import com.aspire.util.ByteArray;
import com.aspire.util.GenApi;
import com.aspire.util.ToolException;

/**
 * 
 * The class <code>PerfMonitorControlReq</code>
 *
 * @author Link Wang
 * @version 1.0
 */
public class PerfMonitorControlReq extends MobileMsgBase {
    
    /**
     * The action type, 0x01:Start/0x02:Stop/0x03:Restart
     */
    protected byte nAction = MobileConstants.MONITOR_CONTROL_TYPE_START;
    
    /**
     * The monitor config.
     */
    protected PerfMonitorConfig config;

    /**
     * The constructor
     */
    public PerfMonitorControlReq() {
        super(MobileConstants.PERF_MONITOR_CONTROL_REQ, "PerfMonitorControlReq");
        config = new PerfMonitorConfig();
    }
    
    /**
     * @return the config
     */
    public PerfMonitorConfig getConfig() {
        return config;
    }

    /**
     * @param config the config to set
     */
    public void setConfig(PerfMonitorConfig config) {
        if (config != null) {
            this.config = config;
        }
    }
    
    /**
     * Sets the action type
     * @param nAction The action type
     */
    public void setAction(byte nAction) {
        this.nAction = nAction;
        setVerify("Action");
    }

    /**
     * Sets the action type
     * @param sType The action type
     * @throws ToolException 
     */
    public void setAction(String sAction) throws ToolException {
        setAction(GenApi.strToByte(sAction, "Action"));
    }

    /**
     * Gets the action type
     * @return Returns The action type
     */
    public String getAction() {
        return Byte.toString(nAction);
    }

    /**
     * Gets the action type
     * @return Returns the action type
     */
    public byte getActionValue() {
        return nAction;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#decode(byte[], int, int)
     */
    public int decode(byte[] byMsg, int start, int length) throws ToolException {
        
        int nRead = super.decode(byMsg, start, length);
        int offset = start + nRead;
        
        // Decodes the Action
        if (offset >= length) {
            throw new ToolException(
                    "PerfMonitorControlReq decode error: no Action in the record");
        }
        nAction = byMsg[offset];
        offset++;
        
        // Decodes Interval field
        config.setInterval(ByteArray.bytesToInt(byMsg, offset));
        offset += 4;
        
        // Decodes Duration field
        config.setDuration(ByteArray.bytesToInt(byMsg, offset));
        offset += 4;
        
        // Decodes MonitorPoint field
        config.setMonitorPoint(ByteArray.bytesToInt(byMsg, offset));
        offset += 4;
        
        return offset - start;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#encode(com.aspire.util.ByteArray)
     */
    public int encode(ByteArray baMsg) throws ToolException {

        int nLen = baMsg.length();

        super.encode(baMsg);

        // Encodes the Action
        baMsg.append(nAction);
        
        // Encodes the Interval field
        baMsg.append(config.getInterval());
        
        // Encodes the Duration field
        baMsg.append(config.getDuration());
        
        // Encodes the MonitorPoint field
        baMsg.append(config.getMonitorPoint());
        
        return baMsg.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#format(java.lang.StringBuffer, java.lang.String)
     */
    public int format(StringBuffer buf, String sPrefix) throws ToolException {

        int nLen = buf.length();

        super.format(buf, sPrefix);
        
        // Format the Action
        buf.append(sPrefix).
            append("Action = ").
            append(nAction).
            append(Constants.LINE_SEPARATOR);
        
        // The Interval field
        buf.append(sPrefix).
            append("MonitorConfig").
            append(Constants.LINE_SEPARATOR).
            append(sPrefix).append("{").
            append(Constants.LINE_SEPARATOR).
            append(sPrefix).append("    ").
            append("Interval = ").append(config.getInterval()).
            append(Constants.LINE_SEPARATOR).
            append(sPrefix).append("    ").
            append("Duration = ").append(config.getDuration()).
            append(Constants.LINE_SEPARATOR).
            append(sPrefix).append("    ").
            append("MonitorPoint = ").append(config.getMonitorPoint())
            .append(Constants.LINE_SEPARATOR).
            append(sPrefix).append("}");

        return buf.length() - nLen;
    }
    
    /*
     * (non-Javadoc)
     * @see com.aspire.msg.soap.SoapMsgBase#verify(com.aspire.msg.MsgBase)
     */
    public boolean verify(MsgBase msgVerified) throws ToolException {

        if (this == msgVerified) return true;
        
        if (msgVerified == null) {
            throw new ToolException(
                 "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof PerfMonitorControlReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'PerfMonitorControlReq' message");
        }

        PerfMonitorControlReq obj = (PerfMonitorControlReq)msgVerified;

        doVerifyField("Action", nAction, obj, obj.nAction);
        
        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.soap.SoapMsgBase#verifyPresent(com.aspire.msg.MsgBase)
     */
    public boolean verifyPresent(MsgBase msgVerified) throws ToolException {

        if (this == msgVerified) return true;

        if (msgVerified == null) {
            throw new ToolException(
                 "Message verify error: the message to be verified is null");
        }
        
        if (!(msgVerified instanceof PerfMonitorControlReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'PerfMonitorControlReq' message");
        }

        PerfMonitorControlReq obj = (PerfMonitorControlReq)msgVerified;

        doVerifyPresentField("Action", nAction, obj, obj.nAction);

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.soap.SoapMsgBase#verifySpecified(com.aspire.msg.MsgBase)
     */
    public boolean verifySpecified(MsgBase msgVerified) throws ToolException {

        if (this == msgVerified) return true;
        
        if (msgVerified == null) {
            throw new ToolException(
                 "Message verify error: the message to be verified is null");
        }

        if (!(msgVerified instanceof PerfMonitorControlReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'PerfMonitorControlReq' message");
        }

        PerfMonitorControlReq obj = (PerfMonitorControlReq)msgVerified;

        doVerifySpecifiedField("Action", nAction, obj, obj.nAction);

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.FieldMsgBase#createDefaultResponse()
     */
    public MsgBase createDefaultResponse() {
        
        PerfMonitorControlResp oResp = new PerfMonitorControlResp();
        
        oResp.setSequence(nSeqNum);
        
        return oResp;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj)) return false;

        if (!(obj instanceof PerfMonitorControlReq)) {
            return false;
        }

        PerfMonitorControlReq oMsgObj = (PerfMonitorControlReq)obj;
        
        return oMsgObj.nAction == nAction && oMsgObj.config.equals(config);
    }    
}
