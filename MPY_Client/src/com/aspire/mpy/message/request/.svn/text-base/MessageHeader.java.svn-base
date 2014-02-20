package com.aspire.mpy.message.request;

import android.util.Log;

public class MessageHeader {

	public static final String  TAG = "MessageHeader";
	
	public String cmd; // 消息命令字

	public String version = "1.0"; // 协议版本号，当前取值“1.0”

	// 向支撑平台请求报文包头
	protected String getMsgHeadler() {
		StringBuffer st = new StringBuffer();
		st.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		st.append("<req>");
		st.append("<Cmd>" + cmd + "</Cmd>");
		st.append("<Version>" + version + "</Version>");
		Log.i(TAG, "getMsgHeadler :" + st.toString());
		return st.toString();
	}
	
	protected String getMsgHeadlerEnd(){
		StringBuffer st = new StringBuffer();
		st.append("</req>");
		Log.i(TAG, "getMsgHeadlerEnd :" + st.toString());
		return st.toString();
	}
}
