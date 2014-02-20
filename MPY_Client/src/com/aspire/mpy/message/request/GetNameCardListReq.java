package com.aspire.mpy.message.request;

import android.util.Log;

import com.aspire.mpy.bean.CardID;
import com.aspire.mpy.message.ReqMsgType;

public class GetNameCardListReq extends XmlRequestMsg {

	public CardID cardID;
	
	public GetNameCardListReq(){
		super(ReqMsgType.GetNameCardListReq);
	}
	
	@Override
	public String toXml() {
		StringBuffer st = new StringBuffer();
		st.append("<CardID>" );
		st.append("<ID>" + cardID.getId() + "</ID>");
		st.append("<Version>" + cardID.getVersion() + "</Version>");
		st.append("</CardID>" );
		Log.i(TAG, "toXml :" + st.toString());
		return st.toString();
	}

}
