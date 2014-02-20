package com.aspire.mpy.message;

/**
 * 
 * @author x_luoqingbo
 *
 */
public interface ReqMsgType {
	
	public final static String CreateNameCardReq = "" + 0x00000001;//5.1 创建名片
	
	public final static String BindNameCardReq = "" + 0x00000003;//5.2 绑定名片
	
	public final static String LoginNameCardReq = "" + 0x00000005;//5.3 登录名片
	
	public final static String GetNameCardListReq = "" + 0x0000000D;//5.4 查询名片列表
	
	public final static String ModifyNameCardReq = "" + 0x00000007;//5.5 修改名片
	
	public final static String GetNameCardReq = "" + 0x00000009;//5.6 获取名片
	
	public final static String TradeNameCardReq = "" + 0x0000000B;//5.7 交换名片
	
	public final static String UploadPhotoReq = "" + 0x10000001;//5.8 上传图像
	
	public final static String DownloadPhotoReq = "" + 0x10000003;//5.9 下载图像
	
}
