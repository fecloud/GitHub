package com.aspire.mpy.message.response;

import android.sax.Element;
import android.sax.EndTextElementListener;

import com.aspire.mpy.bean.CardID;
import com.aspire.mpy.bean.CardInfo;
import com.aspire.mpy.bean.UserInfo;

public class TradeNameCardResp extends XmlResponseMsg {

	public UserInfo userInfo;
	
	public TradeNameCardResp() {
		super("resp");
		Element user = root.getChild("UserInfo");
		if(null != user){
			userInfo = new UserInfo();
			getCardID(user);
			getCardInfo(user);
		}
	}
	
	protected void getCardID(Element parent){
		Element id = parent.getChild("CardID");
		if(null != id){
			final CardID cardID = new CardID();
			userInfo.setCardID(cardID);
			id.getChild("ID").setEndTextElementListener(new EndTextElementListener() {
				
				@Override
				public void end(String body) {
					cardID.setId(Integer.parseInt(body));
				}
			});
			id.getChild("Version").setEndTextElementListener(new EndTextElementListener() {
				
				@Override
				public void end(String body) {
					cardID.setVersion(Integer.parseInt(body));
				}
			});
		}
	}
	
	protected  void getCardInfo(Element parent){
		Element card = parent.getChild("CardInfo");
		if(null != card){
			final CardInfo cardInfo = new CardInfo();
			userInfo.setCardInfo(cardInfo);
			card.getChild("Name").setEndTextElementListener(new EndTextElementListener() {
				
				@Override
				public void end(String body) {
					cardInfo.setName(body);
				}
				
			});
			
			card.getChild("Duty").setEndTextElementListener(new EndTextElementListener() {
				
				@Override
				public void end(String body) {
					cardInfo.setDuty(body);
				}
				
			});
			card.getChild("Mobile").setEndTextElementListener(new EndTextElementListener() {
				
				@Override
				public void end(String body) {
					cardInfo.setMobile(body);
				}
				
			});
			card.getChild("Email").setEndTextElementListener(new EndTextElementListener() {
				
				@Override
				public void end(String body) {
					cardInfo.setEmail(body);
				}
				
			});
			card.getChild("OfficeTel").setEndTextElementListener(new EndTextElementListener() {
				
				@Override
				public void end(String body) {
					cardInfo.setOfficeTel(body);
				}
				
			});
			card.getChild("OfficeFax").setEndTextElementListener(new EndTextElementListener() {
				
				@Override
				public void end(String body) {
					cardInfo.setOfficeFax(body);
				}
				
			});
			card.getChild("CompAddr").setEndTextElementListener(new EndTextElementListener() {
				
				@Override
				public void end(String body) {
					cardInfo.setCompAddr(body);
				}
				
			});
			card.getChild("CompName").setEndTextElementListener(new EndTextElementListener() {
				
				@Override
				public void end(String body) {
					cardInfo.setCompName(body);
				}
				
			});
			card.getChild("CompURL").setEndTextElementListener(new EndTextElementListener() {
				
				@Override
				public void end(String body) {
					cardInfo.setCompURL(body);
				}
				
			});
			card.getChild("GroupIDList").setEndTextElementListener(new EndTextElementListener() {
				
				@Override
				public void end(String body) {
					cardInfo.setGroupIDList(body);
				}
				
			});
			card.getChild("PhotoID").setEndTextElementListener(new EndTextElementListener() {
				
				@Override
				public void end(String body) {
					cardInfo.setPhotoID(Integer.parseInt(body));
				}
				
			});
			card.getChild("Template").setEndTextElementListener(new EndTextElementListener() {
				
				@Override
				public void end(String body) {
					cardInfo.setTemplate(Integer.parseInt(body));
				}
				
			});
			card.getChild("ExtrInfo").setEndTextElementListener(new EndTextElementListener() {
				
				@Override
				public void end(String body) {
					cardInfo.setExtrInfo(body);
				}
				
			});
			card.getChild("CreateTime").setEndTextElementListener(new EndTextElementListener() {
				
				@Override
				public void end(String body) {
					cardInfo.setExtrInfo(body);
				}
				
			});
		}
	}

}
