package com.aspire.mpy.message.request;

import java.util.List;

import android.util.Log;

import com.aspire.mpy.bean.CardID;
import com.aspire.mpy.message.ReqMsgType;

public class GetNameCardReq extends XmlRequestMsg {

	public Integer number;
	
	public CardID cardID;
	
	public List<CardID> cardIDs ;
	
//	public GetNameCardReq() {
//		super(ReqMsgType.GetNameCardReq);
//	}
	
	public GetNameCardReq(Integer number , CardID cardID , List<CardID> cardIDs) {
		super(ReqMsgType.GetNameCardReq);
		this.number = number;
		this.cardID = cardID;
		this.cardIDs = cardIDs;
	}

	@Override
	public String toXml() {
		StringBuffer st = new StringBuffer();
		st.append("<Number>" + number + "</Number>" );
		st.append("<MyCardID>");
		st.append("<ID>" + cardID.getId() + "</ID>");
		st.append("<Version>" + cardID.getVersion() + "</Version>");
		st.append("</MyCardID>");
		for(CardID cardID : cardIDs){
			st.append("<CardID>" );
			st.append("<ID>" + cardID.getId() + "</ID>");
			st.append("<Version>" + cardID.getVersion() + "</Version>");
			st.append("</CardID>" );
		}
		
		Log.i(TAG, "toXml :" + st.toString());
		return st.toString();
	}

}
