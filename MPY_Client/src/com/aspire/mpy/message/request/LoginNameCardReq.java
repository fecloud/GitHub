package com.aspire.mpy.message.request;

import android.util.Log;

import com.aspire.mpy.bean.BindInfo;
import com.aspire.mpy.message.ReqMsgType;

public class LoginNameCardReq extends XmlRequestMsg {
	
	public BindInfo info;

	public LoginNameCardReq(BindInfo info) {
		super(ReqMsgType.LoginNameCardReq);
		this.info  = info;
	}

	@Override
	public String toXml() {
		StringBuffer st = new StringBuffer();
		st.append("<BindInfo>");
		st.append("<BindStr>" + info.getBindStr() + "</BindStr>");
		st.append("<Password>" + info.getPassword() + "</Password>");
		st.append("</BindInfo>");
		Log.i(TAG, "LoginNameCardReq toXml :" + st.toString());
		return st.toString();
	}

}
