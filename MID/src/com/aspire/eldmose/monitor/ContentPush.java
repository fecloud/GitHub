package com.aspire.eldmose.monitor;

public class ContentPush extends PushObject {

	
//	public String appID = "";
//	public String platformCode = "";
//	public String serviceCode = "";
//	public String tmpContentID = "";
	
	public String contentID = "";
	
	public ContentPush(){
		this.smsType = PushType.CONTENT_PUSH;
	}
}
