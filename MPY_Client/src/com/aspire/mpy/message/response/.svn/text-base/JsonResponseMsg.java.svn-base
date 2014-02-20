package com.aspire.mpy.message.response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.util.Log;

import com.aspire.mpy.exception.MpyException;

public class JsonResponseMsg implements IResponseMsg {

	private static final String TAG = "JsonResponseMsg";

	@Override
	public void parseInputStream(InputStream in) throws MpyException, IOException {
		// TODO Auto-generated method stub
		parseInputStream(in, null);
	}

	@Override
	public void parseInputStream(InputStream in, HttpURLConnection httpConn) throws MpyException,
			IOException {
		if(null != in){
			String json = convertStreamToString(in);
			 try {
				JSONObject object = (JSONObject)new JSONTokener(json).nextValue();
				parserJson(object);
			} catch (JSONException e) {
				e.printStackTrace();
				Log.e(TAG, "parser json error");
			}
		}else{
			throw new NullPointerException("JsonResponseMsg not inputstream");
		}

	}

	private String convertStreamToString(InputStream is) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// MidLog.servLogger.info("mid MidResp "+sb.toString());
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Log.i(TAG, "response json :" + sb.toString());
		return sb.toString();
	}

	public void parserJson(JSONObject object)throws JSONException{}
}
