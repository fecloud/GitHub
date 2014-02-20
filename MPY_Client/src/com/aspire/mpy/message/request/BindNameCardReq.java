package com.aspire.mpy.message.request;

import android.util.Log;

import com.aspire.mpy.bean.BindInfo;
import com.aspire.mpy.bean.CardID;
import com.aspire.mpy.message.ReqMsgType;

public class BindNameCardReq extends XmlRequestMsg {
	
	public CardID cardID;
	
	public BindInfo bindInfo;

	public BindNameCardReq() {
		super(ReqMsgType.BindNameCardReq);
	}

	public BindNameCardReq(CardID cardID, BindInfo bindInfo) {
		super(ReqMsgType.BindNameCardReq);
		this.cardID = cardID;
		this.bindInfo = bindInfo;
	}

	@Override
	public String toXml() {
		StringBuffer st = new StringBuffer();
		st.append("<CardID>" );
		st.append("<ID>" + cardID.getId() + "</ID>");
		st.append("<Version>" + cardID.getVersion() + "</Version>");
		st.append("</CardID>" );
		st.append("<BindInfo>" );
		st.append("<BindStr>" + bindInfo.getBindStr() + "</BindStr>");
		st.append("<Password>" + bindInfo.getPassword() + "</Password>");
		st.append("</BindInfo>" );
		Log.i(TAG, "toXml :" + st.toString());
		return st.toString();
	}

}
