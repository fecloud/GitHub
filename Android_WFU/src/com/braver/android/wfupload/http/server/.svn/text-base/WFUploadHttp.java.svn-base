/**
 * @(#) WFUploadHttp.java Created on 2013-9-13
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.android.wfupload.http.server;

import java.io.IOException;

import android.content.Context;
import android.util.Log;

import com.braver.android.wfupload.http.server.WFUploadHttpHandler.FileUploadListener;
import com.braver.nanohttpd.NanoHTTPD;
import com.braver.nanohttpd.handler.HTTPHandler;

/**
 * The class <code>WFUploadHttp</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class WFUploadHttp extends NanoHTTPD {

	private Context mContext;

	private String uploadDir;

	private int port;

	private FileUploadListener fileUploadListener;

	/**
	 * @param arg0
	 */
	public WFUploadHttp(int arg0, Context mContext, String uploadDir,
			FileUploadListener fileUploadListener) {
		super(arg0);
		this.port = arg0;
		this.mContext = mContext;
		this.uploadDir = uploadDir;
		this.fileUploadListener = fileUploadListener;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.nanohttpd.NanoHTTPD#start()
	 */
	@Override
	public void start() throws IOException {
		Log.d("WFUploadHttp", "listen port:" + port);
		super.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.nanohttpd.NanoHTTPD#conHandler()
	 */
	@Override
	protected HTTPHandler conHandler() {
		return new WFUploadHttpHandler(mContext, uploadDir, fileUploadListener);
	}

}
