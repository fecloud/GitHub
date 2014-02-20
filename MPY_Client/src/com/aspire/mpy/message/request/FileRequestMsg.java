package com.aspire.mpy.message.request;

import java.util.Hashtable;

import com.aspire.mpy.config.Config;
import com.aspire.mpy.exception.MpyException;

public class FileRequestMsg implements IRequestMsg {

	private String contentType = "";

	public String cmd ;
	
	protected Hashtable<String, String> requestProperty;

	public FileRequestMsg(String cmd) {
		requestProperty = new Hashtable<String, String>();
	}

	@Override
	public String getContentType() {
		return contentType;
	}


	@Override
	public Hashtable<String, String> getNeedRespHeaderProps() {
		return this.requestProperty;
	}

	@Override
	public byte[] getData() throws MpyException {
		return null;
	}

	@Override
	public void addNeedRespHeaderProp(String key, String value) {
		
	}

	@Override
	public byte[] getData(int length) throws MpyException {
		return null;
	}

	@Override
	public String getMsgType() {
		return null;
	}

	@Override
	public int getNetworkType() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getURL() {
		return Config.URL_RESOURCE;
	}
	
	

}
