package com.aspire.mose.business.module.msgpush;

import java.io.UnsupportedEncodingException;

public class MsgPushData /* implements IDataProtocolObject */{

	public final static int UNREADED_MSG = 0; // 未读消息
	public final static int READED_MSG = 1; // 已读消息

	/**
	 *  设备ID
	 */
	private String iDeviceID = null;
	/**
	 * 用户ID
	 */
	private String iUserID = null;
	/**
	 * 用户安全凭证
	 */
	private String iUserToken = null;
	/**
	 * 终端应用程序ID
	 */
	private String iAppID = null;
	/**
	 * 推送消息ID，由服务端生成，并保证唯一性
	 */
	private String iMsgID = null; 
	/**
	 * 是否需要状态报告：0：不需要 , 1：需要
	 */
	private boolean iStateReport = false;
	/**
	 * 消息格式：0：二进制内容, 1：文本内容
	 */
	private int iFormat = 0;
	/**
	 * 消息推送类型：1：推送到通知栏, 2：推送到收件箱, 3：推送到应用图标 ,4：推送到应用,
	 * 5：推送到应用（需用户确认）
	 */
	private int iMode = 0;
	/**
	 * 消息内容，消息长度不超过512K,如果iFormat是文本内容 ,要读取时调用GetContentStr
	 */
	private byte[] iContent = null;
	/**
	 * 消息时间
	 */
	private long iTime = 0;
	/**
	 * 消息标示 ， 0标示未读， 1代表已读
	 */
	private int iFlag = 0;

	public int getFlag() {
		return iFlag;
	}

	public void setFlag(int iFlag) {
		this.iFlag = iFlag;
	}

	public long getTime() {
		return iTime;
	}

	public void setTime(long iTime) {
		this.iTime = iTime;
	}

	public void SetDeviceID(String deviceID) {
		iDeviceID = deviceID;
	}

	public void SetUserID(String userID) {
		iUserID = userID;
	}

	public void SetUserToken(String userToken) {
		iUserToken = userToken;
	}

	public void SetAppID(String appID) {
		iAppID = appID;
	}

	public void SetMsgID(String msgID) {
		iMsgID = msgID;
	}

	public void SetStateReport(boolean stateReport) {
		iStateReport = stateReport;
	}

	public void SetStateReport(int stateReport) {
		if (0 == stateReport) {
			iStateReport = false;
		} else {
			iStateReport = true;
		}
	}

	public void SetFormat(int format) {
		iFormat = format;
	}

	public void SetMode(int mode) {
		iMode = mode;
	}

	public void SetContent(byte[] content) {
		iContent = content;
	}

	/*
	 * public void SetContent(String content) { iContent = content; }
	 */

	public String GetDeviceID() {
		return iDeviceID;
	}

	public String GetUserID() {
		return iUserID;
	}

	public String GetUserToken() {
		return iUserToken;
	}

	public String GetAppID() {
		return iAppID;
	}

	public String GetMsgID() {
		return iMsgID;
	}

	public boolean GetStateReport() {
		return iStateReport;
	}

	public int GetFormat() {
		return iFormat;
	}

	public int GetMode() {
		return iMode;
	}

	public byte[] GetContent() {
		return iContent;
	}

	public String GetContentStr() {
		String reStr = null;
		try {
			reStr = new String(iContent, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return reStr;
	}

}
