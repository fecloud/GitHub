/**
 * @(#) WFUHandler.java Created on 2013-9-12
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.android.wfupload.ui;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.webkit.WebView;

import com.braver.android.wfupload.process.WFUploadListener;

/**
 * The class <code>WFUViewHandler</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class WFUViewHandler extends Handler implements WFUploadListener {

	static final String TAG = "WFUViewHandler";

	private WebView webView;

	private static final int ONSERVICE = 0x1;

	private static final int PRINTLOG = 0x2;

	/**
	 * @param webView
	 */
	public WFUViewHandler(WebView webView) {
		super();
		this.webView = webView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.Handler#handleMessage(android.os.Message)
	 */
	@Override
	public void handleMessage(Message msg) {
		switch (msg.what) {
		case ONSERVICE:
			loadHttpAddress((String) msg.obj);
			break;
		case PRINTLOG:
			printLogToView((String) msg.obj);
			break;

		default:
			break;
		}
	}

	private void loadHttpAddress(String httpAddress) {
		Log.d(TAG, "loadHttpAddress " + httpAddress);

		JSONObject object = new JSONObject();
		try {
			object.put("add", httpAddress);
		} catch (JSONException e) {
			Log.e(TAG, "", e);
		}
		String js = null;
		if (httpAddress.contains("http")) {
			js = String.format("javascript:loadHttpAddress(%1$s)", object.toString());
		} else {
			js = String.format("javascript:waringHttpAddress(%1$s)", object.toString());
		}
		Log.d(TAG, "js " + js);
		webView.loadUrl(js);
	}

	/**
	 * 打印日志
	 * 
	 * @param log
	 */
	private void printLogToView(String log) {
		Log.d(TAG, "printLogToView log:" + log);
		JSONObject object = new JSONObject();
		try {
			object.put("log", log);
		} catch (JSONException e) {
			Log.e(TAG, "", e);
		}
		webView.loadUrl(String.format("javascript:addLog(%1$s)", object.toString()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.IInterface#asBinder()
	 */
	@Override
	public IBinder asBinder() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.android.wfupload.process.WFUploadListener#onService(java.lang
	 * .String)
	 */
	@Override
	public void onService(String httpAddress) throws RemoteException {
		sendMessage(obtainMessage(ONSERVICE, httpAddress));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.android.wfupload.process.WFUploadListener#printLog(java.lang
	 * .String)
	 */
	@Override
	public void printLog(String message) throws RemoteException {
		sendMessage(obtainMessage(PRINTLOG, message));
	}

}
