/**
 * @(#) WFUploadServer.java Created on 2013-9-10
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.android.wfupload.http.server;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import android.content.Context;
import android.os.Environment;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.braver.android.wfupload.R;
import com.braver.android.wfupload.http.server.WFUploadHttpHandler.FileUploadListener;
import com.braver.android.wfupload.monitor.SdCardMonitor;
import com.braver.android.wfupload.monitor.SdCardMonitor.SdCardListener;
import com.braver.android.wfupload.monitor.WIFIMonitor;
import com.braver.android.wfupload.monitor.WIFIMonitor.WIFIListener;
import com.braver.android.wfupload.process.WFUAble;
import com.braver.android.wfupload.process.WFUploadListener;
import com.braver.android.wfupload.util.Utils;
import com.braver.nanohttpd.tmp.TempFileManager;
import com.braver.nanohttpd.tmp.TempFileManagerFactory;

/**
 * The class <code>WFUploadServer</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class WFUploadServer implements Runnable, WFUAble, WIFIListener, SdCardListener,
		FileUploadListener {

	static final String TAG = "WFUploadServer";

	private Context mContext;

	private WFUploadHttp wfUploadHttp;

	private int port = 5000;

	/**
	 * true为正常服务,false为不服务
	 */
	private AtomicBoolean wifi = new AtomicBoolean();

	private AtomicBoolean http = new AtomicBoolean();

	private WIFIMonitor wifiMonitor;

	private SdCardMonitor cardMonitor;

	private WFUploadListener wfUploadListener;

	private StringBuffer logBuffer = new StringBuffer();

	/**
	 * @param mContext
	 */
	public WFUploadServer(Context mContext) {
		super();
		this.mContext = mContext;
	}

	/**
	 * 开启http服务
	 */
	public void start() {
		new Thread(this, "WFUploadServer").start();
	}

	/**
	 * 关闭http服务
	 */
	public void stop() {
		if (null != cardMonitor)
			cardMonitor.stop(mContext);
		if (null != wifiMonitor)
			wifiMonitor.stop(mContext);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

		if (serverPre()) {
			loopStartServer();
			wifi.set(true);

		}
		notifyServerStatus();

		cardMonitor = new SdCardMonitor(this);
		cardMonitor.start(mContext);

		wifiMonitor = new WIFIMonitor(this);
		wifiMonitor.start(mContext);

	}

	private boolean serverPre() {
		final boolean wirelessOpen = Utils.wirelessOpen(mContext);
		return wirelessOpen;
	}

	/**
	 * 启动http服务器
	 * 
	 * @param port
	 * @throws IOException
	 */
	private void startServer(int port) throws IOException {
		wfUploadHttp = new WFUploadHttp(port, mContext, Environment.getExternalStorageDirectory()
				.getAbsolutePath(), this);
		wfUploadHttp.setTempFileManagerFactory(new TempFileManagerFactory() {

			@Override
			public TempFileManager create() {
				return new WFUTempFileManager();
			}
		});
		wfUploadHttp.start();
	}

	/**
	 * 循环启动,防止端口占用
	 */
	private void loopStartServer() {
		for (int i = 1; i < 10; i++) {
			try {
				startServer(port);
				break;
			} catch (IOException e) {
				Log.e(TAG, "", e);
			}
			port += i;
		}
		http.set(true);

	}

	/**
	 * 服务正常启动
	 */
	private void notifyServerStatus() {
		if (null != getWfUploadListener()) {
			String serviceAddrss = getString(R.string.wifi_wating);
			if (wifi.get()) {
				serviceAddrss = String.format("http://%1$s:%2$d", Utils.getWifiIP(mContext), port);
			}
			try {
				getWfUploadListener().onService(serviceAddrss);
			} catch (RemoteException e) {
				Log.e(TAG, "", e);
			}
		}
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
	 * com.braver.android.wfupload.service.WIFIMonitor.WIFIListener#onConnection
	 * ()
	 */
	@Override
	public void onConnection() {
		Log.d(TAG, "wifi onConnection");
		if (!http.get()) {
			loopStartServer();
		}
		wifi.set(true);
		notifyServerStatus();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.android.wfupload.service.WIFIMonitor.WIFIListener#onDisConnection
	 * ()
	 */
	@Override
	public void onDisConnection() {
		Log.d(TAG, "wifi onDisConnection");
		wifi.set(false);
		notifyServerStatus();
	}

	/**
	 * 打印日志
	 * 
	 * @param line
	 * @param isWaring
	 *            true为红色
	 */
	private void printLog(String line, boolean isWaring) {
		if (null != line) {
			logBuffer.insert(0, String.format("<div class=\"%1$s\">%2$s</div>", isWaring ? "linew"
					: "line", line));
		}
		if (null != getWfUploadListener() && logBuffer.length() > 0) {
			try {
				getWfUploadListener().printLog(logBuffer.toString());
			} catch (RemoteException e) {
				Log.d(TAG, "", e);
			}
		}
	}

	private String getString(int resId) {
		return mContext.getString(resId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.android.wfupload.process.WFUAble#registerListener(com.braver
	 * .android.wfupload.process.WFUploadListener)
	 */
	@Override
	public void registerListener(WFUploadListener listener) throws RemoteException {
		setWfUploadListener(listener);
		printLog(null, false);
		notifyServerStatus();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.android.wfupload.process.WFUAble#unRegisterListener()
	 */
	@Override
	public void unRegisterListener() throws RemoteException {
		setWfUploadListener(null);
	}

	/**
	 * @return the wfUploadListener
	 */
	public synchronized WFUploadListener getWfUploadListener() {
		return wfUploadListener;
	}

	/**
	 * @param wfUploadListener
	 *            the wfUploadListener to set
	 */
	public synchronized void setWfUploadListener(WFUploadListener wfUploadListener) {
		this.wfUploadListener = wfUploadListener;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.android.wfupload.monitor.SdCardMonitor.SdCardListener#onMount
	 * ()
	 */
	@Override
	public void onMount() {
		Log.d(TAG, "sdcard onMount");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.android.wfupload.monitor.SdCardMonitor.SdCardListener#onUnMount
	 * ()
	 */
	@Override
	public void onUnMount() {
		Log.d(TAG, "sdcard onUnMount");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.android.wfupload.http.server.WFUploadHttp.FileUploadListener
	 * #onFileUpload(java.lang.String)
	 */
	@Override
	public void onFileUpload(String filename) {
		printLog(filename, false);
	}

}
