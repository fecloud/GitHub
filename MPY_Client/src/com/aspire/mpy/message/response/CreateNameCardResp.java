package com.aspire.mpy.message.response;

import android.sax.Element;
import android.sax.EndTextElementListener;

import com.aspire.mpy.bean.CardID;

public class CreateNameCardResp extends XmlResponseMsg {

	public CardID cardID ;
	
	public CreateNameCardResp() {
		super("resp");
		Element id = root.getChild("CardID");
		if(null != id){
			cardID = new CardID();
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

}
