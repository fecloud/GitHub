package com.aspire.eldmose.monitor;

public class SmsWarn extends PushObject {

	public String content = "";

	public SmsWarn() {
		this.smsType = PushType.CONTENT_PUSH;
	}
}
