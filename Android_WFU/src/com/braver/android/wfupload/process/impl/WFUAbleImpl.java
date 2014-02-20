/**
 * @(#) WFUAbleImpl.java Created on 2013-9-11
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.android.wfupload.process.impl;

import android.os.RemoteException;

import com.braver.android.wfupload.process.WFUAble;
import com.braver.android.wfupload.process.WFUAble.Stub;
import com.braver.android.wfupload.process.WFUploadListener;

/**
 * The class <code>WFUAbleImpl</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class WFUAbleImpl extends Stub {

	private WFUAble wfuAble;

	/**
	 * @param wfuAble
	 */
	public WFUAbleImpl(WFUAble wfuAble) {
		super();
		this.wfuAble = wfuAble;
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
		wfuAble.registerListener(listener);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.android.wfupload.process.WFUAble#unRegisterListener()
	 */
	@Override
	public void unRegisterListener() throws RemoteException {
		wfuAble.unRegisterListener();

	}

}
