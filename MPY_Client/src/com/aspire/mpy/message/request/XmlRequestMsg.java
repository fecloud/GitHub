package com.aspire.mpy.message.request;

import java.io.UnsupportedEncodingException;
import java.util.Hashtable;

import android.util.Log;

import com.aspire.mpy.config.Config;
import com.aspire.mpy.exception.MpyException;

public abstract class XmlRequestMsg implements IRequestMsg{
	
	public static  String  TAG ;
	
	protected MessageHeader header = new MessageHeader();

	private String contentType = "text/xml";

    private static String enc = "UTF-8";
	
    public XmlRequestMsg(String reuqeseType){
    	TAG = getClass().getSimpleName();
    	header.cmd = reuqeseType;
    }
    
	@Override
	public void addNeedRespHeaderProp(String key, String value) {		
	}

	@Override
	public String getContentType() {
		return contentType;
	}

	@Override
	public byte[] getData() throws MpyException {
		String xmlData = "";
		xmlData = header.getMsgHeadler() + toXml() + header.getMsgHeadlerEnd();
		try {
			Log.i(TAG, "request xml :" + xmlData);
			return xmlData.getBytes(enc);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public byte[] getData(int length) throws MpyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMsgType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Hashtable<String , String> getNeedRespHeaderProps() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNetworkType() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getURL() {
		// TODO Auto-generated method stub
		return Config.URL_SERVICE;
	}
	
	 public abstract String toXml();

}
