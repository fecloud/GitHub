package com.aspire.mpy.message.request;

import android.util.Log;

import com.aspire.mpy.bean.CardInfo;
import com.aspire.mpy.message.ReqMsgType;

public class CreateNameCardReq extends XmlRequestMsg {

	public CardInfo info ;
	
	public CreateNameCardReq(CardInfo info) {
		super(ReqMsgType.CreateNameCardReq);
		this.info = info;
	}

	@Override
	public String toXml() {
		StringBuffer  st = new StringBuffer();
		st.append("<CardInfo>");
		st.append("<Name>" + info.getName() + "</Name>");
		st.append("<Duty>" + info.getDuty() + "</Duty>");
		st.append("<Mobile>" + info.getMobile() + "</Mobile>");
		st.append("<Email>" + info.getEmail() + "</Email>");
		st.append("<OfficeTel>" + info.getOfficeTel() + "</OfficeTel>");
		st.append("<OfficeFax>" + info.getOfficeFax() + "</OfficeFax>");
		st.append("<CompAddr>" + info.getCompAddr() + "</CompAddr>");
		st.append("<CompName>" + info.getCompName() + "</CompName>");
		st.append("<CompURL>" + info.getCompURL() + "</CompURL>");
		st.append("<GroupIDList>" + info.getGroupIDList() + "</GroupIDList>");
		st.append("<PhotoID>" + info.getPhotoID() + "</PhotoID>");
		st.append("<Template>" + info.getTemplate() + "</Template>");
		st.append("<ExtrInfo>" + info.getExtrInfo() + "</ExtrInfo>");
		st.append("<TradeAddr>" + info.getTradeAddr() + "</TradeAddr>");
		st.append("<TradeTime>" + info.getTradeTime()+ "</TradeTime>");
		st.append("<CreateTime>" + info.getCreateTime() + "</CreateTime>");
		st.append("</CardInfo>");
		Log.i(TAG, "toXml :" + st.toString());
		return st.toString();
	}

}
