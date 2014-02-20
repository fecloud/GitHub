/**
 * @(#) ConfigModifyReq.java Created on 2009-2-18
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.msg;

import java.util.ArrayList;

import com.aspire.Constants;
import com.aspire.mobile.MobileConstants;
import com.aspire.mobile.element.MonitorBatteryConfig;
import com.aspire.mobile.element.MonitorConfig;
import com.aspire.mobile.element.MonitorCpuMemConfig;
import com.aspire.mobile.element.MonitorEventConfig;
import com.aspire.mobile.element.MonitorFrequencyConfig;
import com.aspire.mobile.element.MonitorNetResConfig;
import com.aspire.mobile.element.MonitorTimeConfig;
import com.aspire.msg.MsgBase;
import com.aspire.util.ByteArray;
import com.aspire.util.GenApi;
import com.aspire.util.ToolException;

/**
 * The class <code>ConfigModifyReq</code>
 *
 * @author xushengyong
 * @version 1.0
 */
public class ConfigModifyReq extends MobileMsgBase {
    
    /**
     * The ConfigNum
     */
    protected byte nConfigNum = 0;

    /**
     * The file information
     */
    protected ArrayList<MonitorConfig> aConfigs = new ArrayList<MonitorConfig>();
    
    /**
     * The constructor
     */
    public ConfigModifyReq() {
        super(MobileConstants.CONFIG_MODIFY_REQ, "ConfigModifyReq");
    }
    
    /**
     * Sets the ConfigNum
     * @param nConfigNum The ConfigNum
     */
    public void setConfigNum(byte nConfigNum) {
        this.nConfigNum = nConfigNum;
        setVerify("ConfigNum");
    }

    /**
     * Sets the ConfigNum
     * @param sConfigNum The ConfigNum
     * @throws ToolException 
     */
    public void setConfigNum(String sConfigNum) throws ToolException {
        setConfigNum(GenApi.strToByte(sConfigNum, "ConfigNum"));
    }

    /**
     * Gets the ConfigNum
     * @return Returns the ConfigNum
     */
    public String getConfigNum() {
        return Byte.toString(nConfigNum);
    }

    /**
     * Gets the ConfigNum
     * @return Returns the ConfigNum
     */
    public byte getConfigNumValue() {
        return nConfigNum;
    }
    
    /**
     * Sets the Config
     * @param aConfigs The the Config
     */
    public void setConfig(ArrayList<MonitorConfig> aConfigs) {
        this.aConfigs = aConfigs;
        setVerify("Config", true);
    }

    /**
     * Gets the Config
     * @return Returns the Config
     */
    public ArrayList<MonitorConfig> getConfig() {
        return aConfigs;
    }

    /**
     * Adds the Config
     * @param oConfig The the Config object to be added
     */
    public void addConfig(MonitorConfig oConfig) {
        if (aConfigs.contains(oConfig)) return;
        aConfigs.add(oConfig);
    }
    
    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#decode(byte[], int, int)
     */
    public int decode(byte[] byMsg, int start, int length) throws ToolException {
        
        int nRead = super.decode(byMsg, start, length);
        int offset = start + nRead;
        
        // Decodes the ConfigNum
        if (offset >= length) {
            throw new ToolException(
                    "ConfigModifyReq decode error: no ConfigNum in the record");
        }
        nConfigNum = byMsg[offset];
        offset++;
        
        int nTag = 0;
        // Decodes the Configs
        for (int i = 0; i < nConfigNum; i++) {
            if (offset >= length) {
                throw new ToolException(
                "ConfigModifyReq decode error: ConfigNum is wrong");
            }           
            nTag = byMsg[offset];
            
            switch (nTag) {
            case MobileConstants.CONFIG_TYPE_CPU_MEM:
            {            
                MonitorCpuMemConfig oConfig = new MonitorCpuMemConfig();               
                offset += oConfig.decode(byMsg, offset, length);
                aConfigs.add(oConfig);
                break;
            }
            case MobileConstants.CONFIG_TYPE_NET_RES:
            {
                MonitorNetResConfig oConfig = new MonitorNetResConfig();               
                offset += oConfig.decode(byMsg, offset, length);
                aConfigs.add(oConfig);
                break;
            }   
            case MobileConstants.CONFIG_TYPE_BATTERY:
            {
                MonitorBatteryConfig oConfig = new MonitorBatteryConfig();               
                offset += oConfig.decode(byMsg, offset, length);
                aConfigs.add(oConfig);
                break;
            }
            case MobileConstants.CONFIG_TYPE_EVENT:
            {
                MonitorEventConfig oConfig = new MonitorEventConfig();               
                offset += oConfig.decode(byMsg, offset, length);
                aConfigs.add(oConfig);
                break;
            }
            case MobileConstants.CONFIG_TYPE_FREQUENCY:
            {
                MonitorFrequencyConfig oConfig = new MonitorFrequencyConfig();               
                offset += oConfig.decode(byMsg, offset, length);
                aConfigs.add(oConfig);
                break;
            }           
            case MobileConstants.CONFIG_TYPE_TIME:
            {
                MonitorTimeConfig oConfig = new MonitorTimeConfig();               
                offset += oConfig.decode(byMsg, offset, length);
                aConfigs.add(oConfig);
                break;
            }
            default:
            {
                throw new ToolException(
                        "ConfigModifyReq decode error: unkown tag type: " + nTag);
            }
            }
        }
        
        return offset - start;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#encode(com.aspire.util.ByteArray)
     */
    public int encode(ByteArray baMsg) throws ToolException {

        int nLen = baMsg.length();

        super.encode(baMsg);

        // Encodes the ConfigNum
        baMsg.append(nConfigNum);
        
        // Encodes the Configs
        for (int i = 0; i < aConfigs.size(); i++) {
            aConfigs.get(i).encode(baMsg);
        }
        
        return baMsg.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#format(java.lang.StringBuffer, java.lang.String)
     */
    public int format(StringBuffer buf, String sPrefix) throws ToolException {

        int nLen = buf.length();

        super.format(buf, sPrefix);
        
        // Format the ConfigNum
        buf.append(sPrefix).
            append("ConfigNum = ").
            append(nConfigNum).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the Configs
        String sInfoPrefix = sPrefix + "    ";
        for (int i = 0; i < aConfigs.size(); i++) {
            buf.append(sPrefix).
                append("Config[No ").
                append(i).
                append("]").
                append(Constants.LINE_SEPARATOR).
                append(sPrefix).
                append("{").
                append(Constants.LINE_SEPARATOR);
            aConfigs.get(i).format(buf, sInfoPrefix);                       
            buf.append(sPrefix).
                append("}").
                append(Constants.LINE_SEPARATOR);
        }

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

        if (!(msgVerified instanceof ConfigModifyReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'ConfigModifyReq' message");
        }

        ConfigModifyReq obj = (ConfigModifyReq)msgVerified;

        doVerifyField("ConfigNum", nConfigNum, obj, obj.nConfigNum);
        doVerifyField("Config", aConfigs, obj, obj.aConfigs, false);

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
        
        if (!(msgVerified instanceof ConfigModifyReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'ConfigModifyReq' message");
        }

        ConfigModifyReq obj = (ConfigModifyReq)msgVerified;
        
        doVerifyPresentField("ConfigNum", nConfigNum, obj, obj.nConfigNum);
        doVerifyPresentField("Config", aConfigs, obj, obj.aConfigs, false);

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

        if (!(msgVerified instanceof ConfigModifyReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'ConfigModifyReq' message");
        }

        ConfigModifyReq obj = (ConfigModifyReq)msgVerified;
        
        doVerifySpecifiedField("ConfigNum", nConfigNum, obj, obj.nConfigNum);
        doVerifySpecifiedField("Config", aConfigs, obj, obj.aConfigs, false);
        
        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.FieldMsgBase#createDefaultResponse()
     */
    public MsgBase createDefaultResponse() {
        
        ConfigModifyResp oResp = new ConfigModifyResp();
        
        oResp.setSequence(nSeqNum);
        
        return oResp;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj)) return false;

        if (!(obj instanceof ConfigModifyReq)) {
            return false;
        }

        ConfigModifyReq oMsgObj = (ConfigModifyReq)obj;
        
        boolean bRet = oMsgObj.nConfigNum == nConfigNum &&
            oMsgObj.aConfigs.equals(aConfigs);

        return bRet;
    }

}
