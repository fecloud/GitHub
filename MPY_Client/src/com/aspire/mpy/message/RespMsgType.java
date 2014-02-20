/*
 * Copyright (C) 2010 Aspire
 * 
 * ResoMsgType.java Version 1.0
 *
 */
package com.aspire.mpy.message;

public interface RespMsgType {
    
public final static String CreateNameCardResp = "" + 0x00000002;//5.1 创建名片
	
	public final static String BindNameCardResp = "" + 0x00000004;//5.2 绑定名片
	
	public final static String LoginNameCardResp = "" + 0x00000006;//5.3 登录名片
	
	public final static String GetNameCardListResp = "" + 0x0000000E;//5.4 查询名片列表
	
	public final static String ModifyNameCardResp = "" + 0x00000008;//5.5 修改名片
	
	public final static String GetNameCardResp = "" + 0x0000000A;//5.6 获取名片
	
	public final static String TradeNameCardResp = "" + 0x0000000C;//5.7 交换名片
	
	public final static String UploadPhotoResp = "" + 0x10000002;//5.8 上传图像
	
	public final static String DownloadPhotoResp = "" + 0x10000004;//5.9 下载图像
    
    
}
