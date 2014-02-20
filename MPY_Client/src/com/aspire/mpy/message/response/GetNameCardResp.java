package com.aspire.mpy.message.response;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import com.aspire.mpy.bean.CardID;
import com.aspire.mpy.bean.CardInfo;
import com.aspire.mpy.bean.UserInfo;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.StartElementListener;

public class GetNameCardResp extends XmlResponseMsg {

	public Integer number;
	
	public List<UserInfo> infos ;
	
	private UserInfo info;
	
	private CardID cardID ;
	
	private CardInfo cardInfo;
	
	public GetNameCardResp() {
		super("resp");
		infos = new ArrayList<UserInfo>();
		root.getChild("Number").setEndTextElementListener(new EndTextElementListener() {
			
			@Override
			public void end(String body) {
				number  = Integer.parseInt(body);
			}
			
		});
		getUserInfos(root);
	}

	protected void getUserInfos(Element root){
		Element userInfo = root.getChild("UserInfo");
		userInfo.setStartElementListener(new StartElementListener() {
			
			@Override
			public void start(Attributes attributes) {
				info = new UserInfo();
				cardID = new CardID();
				cardInfo = new CardInfo();
			}
		});
		
		userInfo.setEndElementListener(new EndElementListener() {
			
			@Override
			public void end() {
				info.setCardID(cardID);
				info.setCardInfo(cardInfo);
				infos.add(info);
				info = null;
			}
		});
		getCardID(userInfo);
		getCardInfo(userInfo);
	}
	
	protected void getCardID(Element parent){
		Element id = parent.getChild("CardID");
		if(null != id){
			
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
					cardInfo.setCreateTime(body);
				}
				
			});
		}
	}
}
