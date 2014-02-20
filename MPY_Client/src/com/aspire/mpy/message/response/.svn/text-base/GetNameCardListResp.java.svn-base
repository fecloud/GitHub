package com.aspire.mpy.message.response;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import com.aspire.mpy.bean.CardID;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.StartElementListener;

public class GetNameCardListResp extends XmlResponseMsg {

	public Integer number;
	
	public List<CardID> cardIDs ;
	
	private CardID cardID;
	
	public GetNameCardListResp() {
		super("resp");
		cardIDs = new ArrayList<CardID>();
		root.getChild("Number").setEndTextElementListener(new EndTextElementListener() {
			
			@Override
			public void end(String body) {
				number = Integer.parseInt(body);
			}
		});
		
		Element cardIDElement = root.getChild("CardID");
		if(null != cardIDElement){
			cardIDElement.setStartElementListener(new StartElementListener() {
				
				@Override
				public void start(Attributes attributes) {
					cardID = new CardID();
				}
			});
			cardIDElement.setEndElementListener(new EndElementListener() {
				
				@Override
				public void end() {
					cardIDs.add(cardID);
					cardID = null;
				}
			});
			
		}
		getCardID(root);
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

}
