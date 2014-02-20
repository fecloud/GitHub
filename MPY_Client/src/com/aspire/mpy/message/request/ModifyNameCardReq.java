package com.aspire.mpy.message.request;

import android.util.Log;

import com.aspire.mpy.bean.BindInfo;
import com.aspire.mpy.bean.UserInfo;
import com.aspire.mpy.message.ReqMsgType;

public class ModifyNameCardReq extends XmlRequestMsg {

	public UserInfo userInfo;
	
	public BindInfo bindInfo;
	
	public ModifyNameCardReq(){
		super(ReqMsgType.ModifyNameCardReq);
	}
	
	public ModifyNameCardReq(UserInfo userInfo, BindInfo bindInfo) {
		super(ReqMsgType.ModifyNameCardReq);
		this.userInfo = userInfo;
		this.bindInfo = bindInfo;
	}


	@Override
	public String toXml() {
		StringBuffer  st = new StringBuffer();
		st.append("<CardID>");
		st.append("<ID>" + userInfo.getCardID().getId() + "</ID>");
		st.append("<Version>" + userInfo.getCardID().getVersion() + "</Version>");
		st.append("</CardID>");
		st.append("<CardInfo>");
		st.append("<Name>" + userInfo.getCardInfo().getName() + "</Name>");
		st.append("<Duty>" + userInfo.getCardInfo().getDuty() + "</Duty>");
		st.append("<Mobile>" + userInfo.getCardInfo().getMobile() + "</Mobile>");
		st.append("<Email>" + userInfo.getCardInfo().getEmail() + "</Email>");
		st.append("<OfficeTel>" + userInfo.getCardInfo().getOfficeTel() + "</OfficeTel>");
		st.append("<OfficeFax>" + userInfo.getCardInfo().getOfficeFax() + "</OfficeFax>");
		st.append("<CompAddr>" + userInfo.getCardInfo().getCompAddr() + "</CompAddr>");
		st.append("<CompName>" + userInfo.getCardInfo().getCompName() + "</CompName>");
		st.append("<CompURL>" + userInfo.getCardInfo().getCompURL() + "</CompURL>");
		st.append("<GroupIDList>" + userInfo.getCardInfo().getGroupIDList() + "</GroupIDList>");
		st.append("<PhotoID>" + userInfo.getCardInfo().getPhotoID() + "</PhotoID>");
		st.append("<Template>" + userInfo.getCardInfo().getTemplate() + "</Template>");
		st.append("<ExtrInfo>" + userInfo.getCardInfo().getExtrInfo() + "</ExtrInfo>");
		st.append("<TradeAddr>" + userInfo.getCardInfo().getTradeAddr() + "</TradeAddr>");
		st.append("<TradeTime>" + userInfo.getCardInfo().getTradeTime()+ "</TradeTime>");
		st.append("<CreateTime>" + userInfo.getCardInfo().getCreateTime() + "</CreateTime>");
		st.append("</CardInfo>");
		st.append("<BindInfo>");
		st.append("<BindStr>" + bindInfo.getBindStr() + "</BindStr>");
		st.append("<Password>" + bindInfo.getPassword() + "</Password>");
		st.append("</BindInfo>");
		Log.i(TAG, "toXml :" + st.toString());
		return st.toString();
	}

}
