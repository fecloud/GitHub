package com.aspire.mpy.message.request;

import android.util.Log;

import com.aspire.mpy.bean.CardID;
import com.aspire.mpy.bean.TradeInfo;
import com.aspire.mpy.message.ReqMsgType;

public class TradeNameCardReq extends XmlRequestMsg {

	public CardID cardID;
	
	public TradeInfo tradeInfo;
	
	public TradeNameCardReq() {
		super(ReqMsgType.TradeNameCardReq);
	}

	public TradeNameCardReq(CardID cardID, TradeInfo tradeInfo) {
		super(ReqMsgType.TradeNameCardReq);
		this.cardID = cardID;
		this.tradeInfo = tradeInfo;
	}

	@Override
	public String toXml() {
		StringBuffer st = new StringBuffer();
		st.append("<CardID>" );
		st.append("<ID>" + cardID.getId() + "</ID>");
		st.append("<Version>" + cardID.getVersion() + "</Version>");
		st.append("</CardID>" );
		st.append("<TradeInfo>");
		st.append("<Type>" + tradeInfo.getType() + "</Type>");
		st.append("<XInfo>" + tradeInfo.getXinfo() + "</XInfo>");
		st.append("<YInfo>" + tradeInfo.getXinfo() + "</YInfo>");
		st.append("<ZInfo>" + tradeInfo.getZinfo() + "</ZInfo>");
		st.append("</TradeInfo>");
		Log.i(TAG, "toXml :" + st.toString());
		return st.toString();
	}

}
