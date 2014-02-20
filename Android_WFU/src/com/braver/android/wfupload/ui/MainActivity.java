/**
 * @(#) MainActivity.java Created on 2013-9-10
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.android.wfupload.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.braver.android.wfupload.R;
import com.braver.android.wfupload.process.WFUAble;
import com.braver.android.wfupload.process.WFUConnection;
import com.braver.android.wfupload.process.WFUConnection.ConnecteListener;
import com.braver.android.wfupload.process.impl.WFUploadListenerImpl;
import com.braver.android.wfupload.service.WFUploadService;
import com.braver.android.wfupload.util.FileUitls;
import com.braver.android.wfupload.util.zip.UNzip;

/**
 * The class <code>MainActivity</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class MainActivity extends Activity implements ConnecteListener {

	static final String TAG = "MainActivity";

	private WFUConnection wfuConnection;

	private WFUploadListenerImpl impl;

	private WebView webView;

	private WFUViewHandler handler;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.main);
		super.onCreate(savedInstanceState);

		unWar();

		WFUploadService.startService(getApplicationContext());

		webView = (WebView) findViewById(R.id.webView);

		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		final String path = "file:///"+getFilesDir().getAbsoluteFile() + File.separator + "web"
				+ File.separator + "main.html";
		webView.loadUrl(path);
		handler = new WFUViewHandler(webView);
		
		wfuConnection = new WFUConnection(getApplicationContext(), this);
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		unRegisterListener();
		wfuConnection.unbindService();
		super.onDestroy();
		System.exit(1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		
		wfuConnection.bindService();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.android.wfupload.process.WFUConnection.ConnecteListener#onConnect
	 * (com.braver.android.wfupload.process.WFUAble)
	 */
	@Override
	public void onConnect(WFUAble service) {
		registerListener();
	}

	private void registerListener() {
		if (null == impl) {
			impl = new WFUploadListenerImpl(handler);
		}
		try {
			wfuConnection.getService().registerListener(impl);
		} catch (RemoteException e) {
			Log.e(TAG, "", e);
		}
	}

	private void unRegisterListener() {
		try {
			wfuConnection.getService().unRegisterListener();
		} catch (RemoteException e) {
			Log.e(TAG, "", e);
		}
	}

	private void unWar() {
		final String unWarPath = getFilesDir().getAbsoluteFile() + File.separator + "web";
		final File f = new File(unWarPath);
		if (!f.exists()) {
			long s =System.currentTimeMillis();
			unzipWar();
			Log.e(TAG, "" + (System.currentTimeMillis()-s));
		}
	}

	/**
	 * 解压war包
	 */
	private void unzipWar() {
		Log.d(TAG, "unzipWar start ....");
		try {
			String[] list = getAssets().list("");

			String targetFile = null;
			for (String f : list) {
				if (f.endsWith(".class")) {
					Log.d(TAG, "unzipWar war " + f);
					targetFile = getFilesDir() + File.separator + f;
					FileUitls.copyFile(getAssets().open(f), new FileOutputStream(targetFile));

					UNzip.unzip(targetFile, getFilesDir() + File.separator + f.replace(".class", ""));
					FileUitls.deleteFile(targetFile);
				}
			}

		} catch (IOException e) {
			Log.e(TAG, "", e);
		}
		Log.d(TAG, "unzipWar end ....");
	}

}
