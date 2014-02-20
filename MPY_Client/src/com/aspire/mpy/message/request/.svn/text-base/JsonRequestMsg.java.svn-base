package com.aspire.mpy.message.request;

import java.util.Hashtable;

import org.json.JSONObject;

import android.util.Log;

import com.aspire.mpy.config.Config;
import com.aspire.mpy.exception.MpyException;

public class JsonRequestMsg implements IRequestMsg{

	private static final String TAG = "JsonRequestMsg";
	
	public JSONObject holder = new JSONObject();
	
	@Override
	public void addNeedRespHeaderProp(String key, String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getData() throws MpyException {
		String json = holder.toString();
		Log.i(TAG, "json is :" + holder);
		return json.getBytes();
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
	public Hashtable<String, String> getNeedRespHeaderProps() {
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
		return Config.URL_SERVICE_GSM_GOOGLE;
	}

}
