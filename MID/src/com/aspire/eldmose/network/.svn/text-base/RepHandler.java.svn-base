package com.aspire.eldmose.network;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RepHandler extends DefaultHandler {
	private static final String TAG = "RepHandler";
	private String tagName = new String();

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		String para = new String(ch,start,length);
		if(tagName == "Msisdn")
			SecretImisKey.msisdn = para;	
		else if(tagName == "Imsi")
			SecretImisKey.imsi = para;
		else if(tagName == "Encry")
			SecretImisKey.secretKey = para;
//		Log.d(TAG, para);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
//		Log.d(TAG, localName);
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		tagName = localName;
//		Log.d(TAG, localName);
	}

}
