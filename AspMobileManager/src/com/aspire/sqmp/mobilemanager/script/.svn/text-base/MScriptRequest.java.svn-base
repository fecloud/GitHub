/**
 * @(#) TMScriptResquest.java Created on 2012-6-11
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.script;

import com.aspire.mgt.ats.tm.sync.HttpRequestTransmitter;
import com.aspire.mgt.ats.tm.sync.IFTPClient;
import com.aspire.mgt.ats.tm.sync.IHTTPClient;
import com.aspire.mgt.ats.tm.sync.XmlResponseProcessor;
import com.aspire.mgt.ats.tm.sync.script.entity.QueryScriptRequest;

/**
 * The class <code>AtsHttpJob</code>用于发起脚本查询的http请求
 * 
 * @author likai
 *
 */
public class MScriptRequest {
	
private HttpRequestTransmitter reqTransmitter;
    
	/**
	 * 处理xml数据流的处理器
	 */
    private XmlResponseProcessor respProcessor;
	
	/**
	 * 设置并发起脚本更新查询
	 * @param testMode 拨测方式
	 * @param venderCode 厂商代码
	 * @param provinceCode 省份编码
	 * @param url Http连接地址
	 * @param devType 设备型号
	 * @param lastUpdate 最后一次获取的时间
	 * @param ftpClient ftp连接客户端
	 * @param imei 手机机器码
	 * @param ftpLocalPath 本地保存的主路径
	 * @param ftpPath ftp服务器上的路径
	 * @param separator 分隔符
	 */
	public void request(String testMode, String venderCode,
            String provinceCode, String url, String devType, String lastUpdate,
            IFTPClient ftpClient, IHTTPClient httpClient, String imei, String ftpLocalPath, String ftpPath){
        String separator = "/";
		QueryScriptRequest queryScriptReq = new QueryScriptRequest();
		queryScriptReq.setDevType(devType);
		queryScriptReq.setImei(imei);
		queryScriptReq.setProvCode(provinceCode);
		queryScriptReq.setTestMode(testMode);
		queryScriptReq.setVenderCode(venderCode);
		if(lastUpdate == null)
		    lastUpdate = "";
        queryScriptReq.setLastDownloadTime(lastUpdate);                               
		reqTransmitter = new HttpRequestTransmitter(httpClient);
		MScriptSearchHandler tmScriptSearchHandler = new MScriptSearchHandler();
        tmScriptSearchHandler.setFtpClient(ftpClient);
        tmScriptSearchHandler.setFtpLocalPath(ftpLocalPath);
        tmScriptSearchHandler.setScriptRequest(queryScriptReq);
        tmScriptSearchHandler.setFtpPath(ftpPath);
        tmScriptSearchHandler.setSeparator(separator);
		respProcessor = new XmlResponseProcessor(tmScriptSearchHandler);
		reqTransmitter.send(url, queryScriptReq, respProcessor);
	}
}
