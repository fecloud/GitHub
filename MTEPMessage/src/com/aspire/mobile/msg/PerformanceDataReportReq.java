/**
 * @(#) PerformanceDataReportReq.java Created on 2009-2-18
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.msg;

//import java.util.ArrayList;

import com.aspire.Constants;
import com.aspire.mobile.MobileConstants;
//import com.aspire.mobile.element.MobilePerformanceDataInfo;
import com.aspire.msg.MsgBase;
import com.aspire.util.ByteArray;
import com.aspire.util.GenApi;
import com.aspire.util.GenHelper;
import com.aspire.util.ToolException;

/**
 * The class <code>PerformanceDataReportReq</code>
 *
 * @author xushengyong
 * @version 1.0
 */
public class PerformanceDataReportReq extends MobileMsgBase {
    
    /**
     * The TimeStamp
     */
    protected String sTimeStamp = GenApi.getTime();
    
    /**
     * The CpuData
     */    
    protected int nCpuData = 0;    
    
    /**
     * The MemData
     */    
    protected int nMemData = 0;   
    
    /**
     * The TotalMemData
     */    
    protected int nTotalMemData = 0;   
    
    /**
     * The BandWidth
     */    
    protected int nBandWidth = 0;

    /**
     * The BatteryUsage
     */    
    protected int nBatteryUsage = 0;
 
    /**
     * The ProcessNum
     */    
    protected int nProcessNum = 0;

    /**
     * The MonitorProcessNum
     */    
    //protected int nMonitorProcessNum = 0;

    /**
     * The performance data information
     */
    //protected ArrayList<MobilePerformanceDataInfo> aPerformanceDataInfos =
    //    new ArrayList<MobilePerformanceDataInfo>();    

    /**
     * The constructor
     */
    public PerformanceDataReportReq() {
        super(MobileConstants.PERF_DATA_REPORT_REQ, "PerformanceDataReportReq");
    }
    
    /**
     * Sets the time stamp
     * @param sTimeStamp The time stamp
     */
    public void setTimeStamp(String sTimeStamp) {
        this.sTimeStamp = sTimeStamp;
        setVerify("TimeStamp", true);
    }
    
    /**
     * Gets the time stamp
     * @return Return the time stamp
     */
    public String getTimeStamp() {
        return sTimeStamp;
    }
    
    /**
     * Sets the CpuData
     * @param nCpuData The CpuData
     * @throws ToolException 
     */    
    public void setCpuData(int nCpuData) throws ToolException {
        this.nCpuData = nCpuData;
        setVerify("CpuData", true);
    }
    
    /**
     * Sets the CpuData
     * @param sCpuData The CpuData
     * @throws ToolException 
     */
    public void setCpuData(String sCpuData) throws ToolException {
        setCpuData(GenApi.strToInt(sCpuData, "CpuData"));
    }
    
    /**
     * Gets the CpuData
     * @return Returns the CpuData
     */
    public String getCpuData() {
        return Integer.toString(nCpuData);
    }

    /**
     * Gets the CpuData
     * @return Returns the CpuData
     */
    public int getCpuDataValue() {
        return nCpuData;
    }
    
    /**
     * Sets the MemData
     * @param nMemData The MemData
     * @throws ToolException 
     */    
    public void setMemData(int nMemData) throws ToolException {
        this.nMemData = nMemData;
        setVerify("MemData", true);
    }
    
    /**
     * Sets the MemData
     * @param sMemData The MemData
     * @throws ToolException 
     */
    public void setMemData(String sMemData) throws ToolException {
        setMemData(GenApi.strToInt(sMemData, "MemData"));
    }
    
    /**
     * Gets the MemData
     * @return Returns the MemData
     */
    public String getMemData() {
        return Integer.toString(nMemData);
    }

    /**
     * Gets the MemData
     * @return Returns the MemData
     */
    public int getMemDataValue() {
        return nMemData;
    }
    
    /**
     * Sets the TotalMemData
     * @param nTotalMemData The TotalMemData
     * @throws ToolException 
     */    
    public void setTotalMemData(int nTotalMemData) throws ToolException {
        this.nTotalMemData = nTotalMemData;
        setVerify("TotalMemData", true);
    }
    
    /**
     * Sets the TotalMemData
     * @param sTotalMemData The TotalMemData
     * @throws ToolException 
     */
    public void setTotalMemData(String sTotalMemData) throws ToolException {
        setTotalMemData(GenApi.strToInt(sTotalMemData, "TotalMemData"));
    }
    
    /**
     * Gets the TotalMemData
     * @return Returns the TotalMemData
     */
    public String getTotalMemData() {
        return Integer.toString(nTotalMemData);
    }

    /**
     * Gets the TotalMemData
     * @return Returns the TotalMemData
     */
    public int getTotalMemDataValue() {
        return nTotalMemData;
    }
    
    /**
     * Sets the BandWidth
     * @param nBandWidth The BandWidth
     * @throws ToolException 
     */    
    public void setBandWidth(int nBandWidth) throws ToolException {
        this.nBandWidth = nBandWidth;
        setVerify("BandWidth", true);
    }
    
    /**
     * Sets the BandWidth
     * @param sBandWidth The BandWidth
     * @throws ToolException 
     */
    public void setBandWidth(String sBandWidth) throws ToolException {
        setBandWidth(GenApi.strToInt(sBandWidth, "BandWidth"));
    }
    
    /**
     * Gets the BandWidth
     * @return Returns the BandWidth
     */
    public String getBandWidth() {
        return Integer.toString(nBandWidth);
    }

    /**
     * Gets the BandWidth
     * @return Returns the BandWidth
     */
    public int getBandWidthValue() {
        return nBandWidth;
    }
    
    /**
     * Sets the BatteryUsage
     * @param nBatteryUsage The BatteryUsage
     * @throws ToolException 
     */    
    public void setBatteryUsage(int nBatteryUsage) throws ToolException {
        this.nBatteryUsage = nBatteryUsage;
        setVerify("BatteryUsage", true);
    }
    
    /**
     * Sets the BatteryUsage
     * @param sBatteryUsage The BatteryUsage
     * @throws ToolException 
     */
    public void setBatteryUsage(String sBatteryUsage) throws ToolException {
        setBatteryUsage(GenApi.strToInt(sBatteryUsage, "BatteryUsage"));
    }
    
    /**
     * Gets the BatteryUsage
     * @return Returns the BatteryUsage
     */
    public String getBatteryUsage() {
        return Integer.toString(nBatteryUsage);
    }

    /**
     * Gets the BatteryUsage
     * @return Returns the BatteryUsage
     */
    public int getBatteryUsageValue() {
        return nBatteryUsage;
    }
    
    /**
     * Sets the ProcessNum
     * @param nProcessNum The ProcessNum
     * @throws ToolException 
     */    
    public void setProcessNum(int nProcessNum) throws ToolException {
        this.nProcessNum = nProcessNum;
        setVerify("ProcessNum", true);
    }
    
    /**
     * Sets the ProcessNum
     * @param sProcessNum The ProcessNum
     * @throws ToolException 
     */
    public void setProcessNum(String sProcessNum) throws ToolException {
        setProcessNum(GenApi.strToInt(sProcessNum, "ProcessNum"));
    }
    
    /**
     * Gets the ProcessNum
     * @return Returns the ProcessNum
     */
    public String getProcessNum() {
        return Integer.toString(nProcessNum);
    }

    /**
     * Gets the ProcessNum
     * @return Returns the ProcessNum
     */
    public int getProcessNumValue() {
        return nProcessNum;
    }
    
    /**
     * Sets the MonitorProcessNum
     * @param nMonitorProcessNum The MonitorProcessNum
     * @throws ToolException 
     */    
//    public void setMonitorProcessNum(int nMonitorProcessNum) throws ToolException {
//        this.nMonitorProcessNum = nMonitorProcessNum;
//        setVerify("MonitorProcessNum", true);
//    }
    
    /**
     * Sets the MonitorProcessNum
     * @param sMonitorProcessNum The MonitorProcessNum
     * @throws ToolException 
     */
//    public void setMonitorProcessNum(String sMonitorProcessNum) throws ToolException {
//        setMonitorProcessNum(GenApi.strToInt(sMonitorProcessNum, "MonitorProcessNum"));
//    }
    
    /**
     * Gets the MonitorProcessNum
     * @return Returns the MonitorProcessNum
     */
//    public String getMonitorProcessNum() {
//        return Integer.toString(nMonitorProcessNum);
//    }

    /**
     * Gets the MonitorProcessNum
     * @return Returns the MonitorProcessNum
     */
//    public int getMonitorProcessNumValue() {
//        return nMonitorProcessNum;
//    }
    
    /**
     * Sets the performance data info
     * @param aPerformanceDataInfos The the performance data info
     */
//    public void setPerformanceDataInfo(
//            ArrayList<MobilePerformanceDataInfo> aPerformanceDataInfos) {
//        this.aPerformanceDataInfos = aPerformanceDataInfos;
//        setVerify("PerformanceDataInfo", true);
//    }

    /**
     * Gets the performance data info
     * @return Returns the performance data info
     */
//    public ArrayList<MobilePerformanceDataInfo> getPerformanceDataInfo() {
//        return aPerformanceDataInfos;
//    }

    /**
     * Adds the performance data info
     * @param oPerformanceDataInfo The the performance data info object to be added
     */
//    public void addPerformanceDataInfo(
//            MobilePerformanceDataInfo oPerformanceDataInfo) {
//        if (aPerformanceDataInfos.contains(oPerformanceDataInfo))
//            return;
//        aPerformanceDataInfos.add(oPerformanceDataInfo);
//    }
    
    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#decode(byte[], int, int)
     */
    public int decode(byte[] byMsg, int start, int length) throws ToolException {
        
        int nRead = super.decode(byMsg, start, length);
        int offset = start + nRead;
                  
        // Decodes the TimeStamp
        if ((offset + MobileConstants.MAX_TIME_LEN) > length) {
            throw new ToolException(
                "PerformanceDataReportReq decode error: no TimeStamp in the record");
        }
        sTimeStamp = new String(byMsg, offset,
                MobileConstants.MAX_TIME_LEN).trim();
        offset += MobileConstants.MAX_TIME_LEN;  
      
        // Decodes the CpuData
        if ((offset + 4) > length) {
            throw new ToolException(
            "PerformanceDataReportReq decode error: no CpuData in the record");
        }                      
        nCpuData = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;
                
        // Decodes the MemData
        if ((offset + 4) > length) {
            throw new ToolException(
            "PerformanceDataReportReq decode error: no MemData in the record");
        }                      
        nMemData = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;
        
        // Decodes the TotalMemData
        if ((offset + 4) > length) {
            throw new ToolException(
            "PerformanceDataReportReq decode error: no TotalMemData in the record");
        }                      
        nTotalMemData = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;
        
        // Decodes the BandWidth
        if ((offset + 4) > length) {
            throw new ToolException(
            "PerformanceDataReportReq decode error: no BandWidth in the record");
        }                      
        nBandWidth = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;
        
        // Decodes the BatteryUsage
        if ((offset + 4) > length) {
            throw new ToolException(
            "PerformanceDataReportReq decode error: no BatteryUsage in the record");
        }                      
        nBatteryUsage = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;
        
        // Decodes the ProcessNum
        if ((offset + 4) > length) {
            throw new ToolException(
            "PerformanceDataReportReq decode error: no ProcessNum in the record");
        }                      
        nProcessNum = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;
        
        // Decodes the MonitorProcessNum
//        if ((offset + 4) > length) {
//            throw new ToolException(
//            "PerformanceDataReportReq decode error: no MonitorProcessNum in the record");
//        }                      
//        nMonitorProcessNum = ByteArray.bytesToInt(byMsg, offset);
//        offset += 4;
   
        // Decodes the performance data information
//        for (int i = 0; i < nMonitorProcessNum; i++) {
//            if (offset >= length) {
//                break;
//            }
//            MobilePerformanceDataInfo oInfo = new MobilePerformanceDataInfo();
//            offset += oInfo.decode(byMsg, offset, length);
//            aPerformanceDataInfos.add(oInfo);
//        }   
       
        return offset - start;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#encode(com.aspire.util.ByteArray)
     */
    public int encode(ByteArray baMsg) throws ToolException {

        int nLen = baMsg.length();

        super.encode(baMsg);
        
        // Encodes the TimeStamp
        byte[] byTime = new byte[MobileConstants.MAX_TIME_LEN];
        GenHelper.copyString(byTime, sTimeStamp);
        baMsg.append(byTime);
        
        // Encodes the CpuData
        baMsg.append(nCpuData);
        
        // Encodes the MemData
        baMsg.append(nMemData);
        
        // Encodes the TotalMemData
        baMsg.append(nTotalMemData);
        
        // Encodes the BandWidth
        baMsg.append(nBandWidth);
        
        // Encodes the BatteryUsage
        baMsg.append(nBatteryUsage);
        
        // Encodes the ProcessNum
        baMsg.append(nProcessNum);       
        
        // Encodes the MonitorProcessNum
//        baMsg.append(aPerformanceDataInfos.size());

        // Encodes the performance data information
//        for (int i = 0; i < aPerformanceDataInfos.size(); i++) {
//            aPerformanceDataInfos.get(i).encode(baMsg);
//        }
        
        return baMsg.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.mobile.msg.MobileMsgBase#format(java.lang.StringBuffer, java.lang.String)
     */
    public int format(StringBuffer buf, String sPrefix) throws ToolException {

        int nLen = buf.length();

        super.format(buf, sPrefix);
        
        // Formats the TimeStamp
        buf.append(sPrefix).
            append("TimeStamp = ").
            append(sTimeStamp).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the CpuData
        buf.append(sPrefix).
            append("CpuData = ").
            append(nCpuData).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the MemData
        buf.append(sPrefix).
            append("MemData = ").
            append(nMemData).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the TotalMemData
        buf.append(sPrefix).
            append("TotalMemData = ").
            append(nTotalMemData).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the BandWidth
        buf.append(sPrefix).
            append("BandWidth = ").
            append(nBandWidth).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the BatteryUsage
        buf.append(sPrefix).
            append("BatteryUsage = ").
            append(nBatteryUsage).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the ProcessNum
        buf.append(sPrefix).
            append("ProcessNum = ").
            append(nProcessNum).
            append(Constants.LINE_SEPARATOR);
        
        // Formats the MonitorProcessNum
//        buf.append(sPrefix).
//            append("MonitorProcessNum = ").
//            append(nMonitorProcessNum).
//            append(Constants.LINE_SEPARATOR);   

        // Formats the performance data information
//        String sInfoPrefix = sPrefix + "    ";
//        for (int i = 0; i < aPerformanceDataInfos.size(); i++) {
//            buf.append(sPrefix).
//                append("PerformanceDataInfo[No ").
//                append(i).
//                append("]").
//                append(Constants.LINE_SEPARATOR).
//                append(sPrefix).
//                append("{").
//                append(Constants.LINE_SEPARATOR);
//            aPerformanceDataInfos.get(i).format(buf, sInfoPrefix);
//            buf.append(sPrefix).
//                append("}").
//                append(Constants.LINE_SEPARATOR);
//        }
        
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

        if (!(msgVerified instanceof PerformanceDataReportReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'PerformanceDataReportReq' message");
        }
        
        PerformanceDataReportReq obj = (PerformanceDataReportReq)msgVerified;
        
        doVerifyField("TimeStamp", sTimeStamp, obj, obj.sTimeStamp);  
        doVerifyField("CpuData", nCpuData, obj, obj.nCpuData);
        doVerifyField("MemData", nMemData, obj, obj.nMemData);  
        doVerifyField("TotalMemData", nTotalMemData, obj, obj.nTotalMemData);
        doVerifyField("BandWidth", nBandWidth, obj, obj.nBandWidth);
        doVerifyField("BatteryUsage", nBatteryUsage, obj, obj.nBatteryUsage);  
        doVerifyField("ProcessNum", nProcessNum, obj, obj.nProcessNum);
//        doVerifyField("MonitorProcessNum", nMonitorProcessNum, obj, obj.nMonitorProcessNum);       
//        doVerifyField("PerformanceDataInfo", aPerformanceDataInfos, obj, obj.aPerformanceDataInfos, false);
        
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
        
        if (!(msgVerified instanceof PerformanceDataReportReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'PerformanceDataReportReq' message");
        }

        PerformanceDataReportReq obj = (PerformanceDataReportReq)msgVerified;

        doVerifyPresentField("TimeStamp", sTimeStamp, obj, obj.sTimeStamp);  
        doVerifyPresentField("CpuData", nCpuData, obj, obj.nCpuData);
        doVerifyPresentField("MemData", nMemData, obj, obj.nMemData);  
        doVerifyPresentField("TotalMemData", nTotalMemData, obj, obj.nTotalMemData);
        doVerifyPresentField("BandWidth", nBandWidth, obj, obj.nBandWidth);
        doVerifyPresentField("BatteryUsage", nBatteryUsage, obj, obj.nBatteryUsage);  
        doVerifyPresentField("ProcessNum", nProcessNum, obj, obj.nProcessNum);
//        doVerifyPresentField("MonitorProcessNum", nMonitorProcessNum, obj, obj.nMonitorProcessNum);       
//        doVerifyPresentField("PerformanceDataInfo", aPerformanceDataInfos, obj, obj.aPerformanceDataInfos, false);

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

        if (!(msgVerified instanceof PerformanceDataReportReq)) {
            throw new ToolException(
                 "Message verify error: the message to be verified is not a 'PerformanceDataReportReq' message");
        }

        PerformanceDataReportReq obj = (PerformanceDataReportReq)msgVerified;

        doVerifySpecifiedField("TimeStamp", sTimeStamp, obj, obj.sTimeStamp);  
        doVerifySpecifiedField("CpuData", nCpuData, obj, obj.nCpuData);
        doVerifySpecifiedField("MemData", nMemData, obj, obj.nMemData);  
        doVerifySpecifiedField("TotalMemData", nTotalMemData, obj, obj.nTotalMemData);
        doVerifySpecifiedField("BandWidth", nBandWidth, obj, obj.nBandWidth);
        doVerifySpecifiedField("BatteryUsage", nBatteryUsage, obj, obj.nBatteryUsage);  
        doVerifySpecifiedField("ProcessNum", nProcessNum, obj, obj.nProcessNum);
//        doVerifySpecifiedField("MonitorProcessNum", nMonitorProcessNum, obj, obj.nMonitorProcessNum);       
//        doVerifySpecifiedField("PerformanceDataInfo", aPerformanceDataInfos, obj, obj.aPerformanceDataInfos, false);

        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.FieldMsgBase#createDefaultResponse()
     */
    public MsgBase createDefaultResponse() {
        
        PerformanceDataReportResp oResp = new PerformanceDataReportResp();
        
        oResp.setSequence(nSeqNum);
        
        return oResp;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (!super.equals(obj)) return false;

        if (!(obj instanceof PerformanceDataReportReq)) {
            return false;
        }

        PerformanceDataReportReq oMsgObj = (PerformanceDataReportReq)obj;

        return  oMsgObj.sTimeStamp.equals(sTimeStamp) && 
            oMsgObj.nCpuData == nCpuData &&      
            oMsgObj.nMemData == nMemData &&         
            oMsgObj.nTotalMemData == nTotalMemData && 
            oMsgObj.nBandWidth == nBandWidth && 
            oMsgObj.nBatteryUsage == nBatteryUsage &&         
            oMsgObj.nProcessNum == nProcessNum; 
//            oMsgObj.nMonitorProcessNum == nMonitorProcessNum &&       
//            oMsgObj.aPerformanceDataInfos.equals(aPerformanceDataInfos);
    }

    /**
     * This method is used to clone a PerformanceDataReportReq object
     */
    public Object clone() throws CloneNotSupportedException {

        PerformanceDataReportReq obj = (PerformanceDataReportReq) super.clone();

//        obj.aPerformanceDataInfos = new ArrayList<MobilePerformanceDataInfo>();
//
//        for (int i = 0; i < aPerformanceDataInfos.size(); i++) {
//            obj.aPerformanceDataInfos.add(
//                    (MobilePerformanceDataInfo) aPerformanceDataInfos.get(i).clone());
//        }

        return obj;
    }
    
}
