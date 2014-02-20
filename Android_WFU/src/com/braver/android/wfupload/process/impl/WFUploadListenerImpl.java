/**
 * @(#) WFUploadListenerImpl.java Created on 2013-9-12
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.android.wfupload.process.impl;

import android.os.RemoteException;

import com.braver.android.wfupload.process.WFUploadListener;
import com.braver.android.wfupload.process.WFUploadListener.Stub;

/**
 * The class <code>WFUploadListenerImpl</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class WFUploadListenerImpl extends Stub {

	private WFUploadListener listener;

	/**
	 * @param listener
	 */
	public WFUploadListenerImpl(WFUploadListener listener) {
		super();
		this.listener = listener;
	}

	/**
	 * @param listener
	 *            the listener to set
	 */
	public void setListener(WFUploadListener listener) {
		this.listener = listener;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.android.wfupload.process.WFUploadListener#onService(java.lang
	 * .String)
	 */
	@Override
	public void onService(String serviceAddrss) throws RemoteException {
		if (null != listener)
			listener.onService(serviceAddrss);
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
		if (null != listener)
			listener.printLog(message);
	}

}
