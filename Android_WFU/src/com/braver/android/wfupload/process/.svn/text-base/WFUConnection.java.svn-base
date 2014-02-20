/**
 * @(#) MessageServiceConnection.java Created on 2013-3-26
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.android.wfupload.process;

import com.braver.android.wfupload.service.WFUploadService;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

/**
 * The class <code>WFUConnection</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class WFUConnection implements ServiceConnection {

	static final String TAG = "OatosConnection";

	private Context mContext;

	private ConnecteListener listener;

	private WFUAble service;

	private boolean connect;

	/**
	 * @param connected
	 */
	public WFUConnection(Context mContext, ConnecteListener listener) {
		super();
		this.mContext = mContext;
		this.listener = listener;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.content.ServiceConnection#onServiceConnected(android.content.
	 * ComponentName, android.os.IBinder)
	 */
	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		Log.d(TAG, "onServiceConnected");
		setService(WFUAble.Stub.asInterface(service));
		setConnect(true);
		if (null != listener)
			listener.onConnect(this.service);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.content.ServiceConnection#onServiceDisconnected(android.content
	 * .ComponentName)
	 */
	@Override
	public void onServiceDisconnected(ComponentName name) {
		Log.d(TAG, "onServiceDisconnected");
		setService(null);
		setConnect(false);
	}

	/**
	 * @return the service
	 */
	public synchronized WFUAble getService() {
		return service;
	}

	/**
	 * @param service
	 *            the service to set
	 */
	public synchronized void setService(WFUAble service) {
		this.service = service;
	}

	/**
	 * 绑定服务
	 * 
	 * @param mContext
	 * @return
	 */
	public synchronized boolean bindService() {
		if (null != mContext && !isConnect()) {
			final boolean con = mContext.bindService(new Intent(WFUploadService.ACTION), this,
					Context.BIND_AUTO_CREATE);
			return con;
		}
		return isConnect();
	}

	/**
	 * 解除绑定服务
	 * 
	 * @return
	 */
	public synchronized void unbindService() {
		if (null != mContext) {
			mContext.unbindService(this);
			setService(null);
			setConnect(false);
		}
	}

	/**
	 * @return the connect
	 */
	public synchronized boolean isConnect() {
		return connect;
	}

	/**
	 * @param connect
	 *            the connect to set
	 */
	public synchronized void setConnect(boolean connect) {
		this.connect = connect;
	}

	/**
	 * The class <code>ConnecteListener</code> </p>服务连接器回调
	 * 
	 * @author Feng OuYang
	 * @version 1.0
	 */
	public interface ConnecteListener {

		/**
		 * 接器回调
		 * 
		 * @param service
		 */
		void onConnect(WFUAble service);

	}

}
