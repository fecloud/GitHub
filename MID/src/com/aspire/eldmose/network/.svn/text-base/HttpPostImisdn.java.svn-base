package com.aspire.eldmose.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

public class HttpPostImisdn {
	private static final String TAG = "HttpPostImisdn";
	private String ImisdnURL = android.mid.config.Config.URL_SERVICE;
	private Context mContext = null;
	private String imsi = "";

	public HttpPostImisdn(Context context) {
		this.mContext = context;
	}
	
	public void post(String _imis) {
		imsi = _imis;
		HttpConnectWrapper httpConn = new HttpConnectWrapper();
		InputStream is = null;
		try {
			is = httpConn.httpPost(getURL(), getContentType(),
					getData(), getNetworkType(), getNeedRespHeaderProps(),
					getRead_time());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = null;
		try {
			parser = factory.newSAXParser();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		try {
			Log.d(TAG, Is2Str(is));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		
		if(is == null || parser == null) return;
		InputSource source = new InputSource(is);

		RepHandler handler = new RepHandler();

		
		try {
			parser.parse(source, handler);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.d(TAG, SecretImisKey.logout());
	}
	protected String getURL() {
		// TODO Auto-generated method stub
		Log.d(TAG, "URL:" + ImisdnURL);
		return ImisdnURL;
	}

	public static String Is2Str(InputStream is) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = in.readLine()) != null) {
			buffer.append(line);
		}
		return buffer.toString();
	}

	protected int getRead_time() {
		// TODO Auto-generated method stub
		Log.d(TAG, "Read Time:" + 1000);
		return 10000;
	}

	protected int getNetworkType() {
		// TODO Auto-generated method stub
		Log.d(TAG, "NetworkType:" + 0);
		return 0;
	}

	protected Hashtable<String, String> getNeedRespHeaderProps() {
		// TODO Auto-generated method stub
		return new Hashtable<String, String>();
	}

	protected byte[] getData() {
		// TODO Auto-generated method stub
		try {
			String enc = "UTF-8";
			String xmlData = "";
			xmlData = getMsgHeadler() + toXml();
			Log.d(TAG, "xmlData:" + xmlData);
			return xmlData.getBytes(enc);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new byte[0];
		}
	}

	private String toXml() {
		// TODO Auto-generated method stub
		StringBuffer st = new StringBuffer();
		st.append("<IMSI>" + imsi + "</IMSI>");
		st.append("</req>");
		return st.toString();
	}

	private String getMsgHeadler() {
		// TODO Auto-generated method stub
		/**
		 * 区分上网本平台与支持平台获取动态密码包头信息 0：支撑平台 1：上网本平台
		 */
		int flag = 0;

		/** 请求类型 */
		String msgType = "QueryMsisdnReq";

		/** 协议版本 */
		String version = "1.0";

		/** 标识地址类别:0：MID支撑平台, 1：MID客户端 */
		int sendAddressType = 1;

		/** 标识地址的ID号: MID客户端：终端设备号, MID支撑平台：000 */
		String sendAddressId = getDeviceID();

		/** 标识地址类别:0：MID支撑平台, 1：MID客户端 */
		int destAddressType = 0;

		/** 标识地址的ID号: MID客户端：终端设备号, MID支撑平台：000 */
		String destAddressId = "000";

		/** 发起方标记，0=MID终端；1=BOSS发起(上网本平台) */
		String ncSendFlag = "0";

		StringBuffer st = new StringBuffer();
		st.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		st.append("<req>");
		st.append("<MsgType>" + msgType + "</MsgType>");
		st.append("<Version>" + version + "</Version>");
		st.append("<Send_Address>");
		st.append("<Type>" + sendAddressType + "</Type>");
		st.append("<ID>" + sendAddressId + "</ID>");
		st.append("</Send_Address>");
		st.append("<Dest_Address>");
		st.append("<Type>" + destAddressType + "</Type>");
		st.append("<ID>" + destAddressId + "</ID>");
		st.append("</Dest_Address>");
		return st.toString();
	}

	private String getDeviceID() {
		// TODO Auto-generated method stub
		String deviceId;
		TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
		deviceId = tm.getDeviceId();
		Log.d(TAG, "DeviceId:" + deviceId);
		return deviceId;
	}

	protected String getContentType() {
		// TODO Auto-generated method stub
		Log.d(TAG, "ContentType:" + "text/xml");
		return "text/xml";
	}
}
