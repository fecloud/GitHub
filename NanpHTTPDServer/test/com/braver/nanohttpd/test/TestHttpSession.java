/**
 * @(#) TestHttpSession.java Created on 2013-9-13
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.nanohttpd.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import android.util.Log;

import com.braver.nanohttpd.MIMEType;
import com.braver.nanohttpd.handler.DefaultHTTPHandler;
import com.braver.nanohttpd.request.Method;
import com.braver.nanohttpd.response.Response;
import com.braver.nanohttpd.response.ResponseException;
import com.braver.nanohttpd.response.Status;

/**
 * The class <code>TestHttpSession</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class TestHttpSession extends DefaultHTTPHandler {

	/**
	 * get 文件
	 * 
	 * @param uri
	 * @param method
	 * @param headers
	 * @param parms
	 * @param files
	 * @return
	 */
	private Response executeGet(String uri, Method method, Map<String, String> headers,
			Map<String, String> parms, Map<String, String> files) {
		Log.d(TAG, "request path:" + uri);
		final StringBuilder path = new StringBuilder(System.getProperty("user.dir").toString());
		path.append(File.separator).append("web").append(uri);
		final File file = new File(path.toString());
		String resultPath = null;
		if (file.exists()) {
			if (file.isDirectory()) {
				// index.html index.htm

				final String indexF = findIndex(file);
				if (null != indexF) {
					try {
						resultPath = path.toString() + indexF;
						Log.d(TAG, "result path:" + resultPath);
						final MIMEType mimeType = MIMEType.lookup(resultPath);
						FileInputStream in = new FileInputStream(resultPath);
						return new Response(Status.OK, mimeType, in);
					} catch (FileNotFoundException e) {
						Log.e(TAG, "", e);
					}

				}

			} else {
				try {
					resultPath = path.toString();
					Log.d(TAG, "result path:" + resultPath);
					final MIMEType mimeType = MIMEType.lookup(resultPath);
					FileInputStream in = new FileInputStream(resultPath);
					return new Response(Status.OK, mimeType, in);
				} catch (FileNotFoundException e) {
					Log.e(TAG, "", e);
				}
			}
		}
		return super.serve(uri, method, headers, parms, files);
	}

	private String findIndex(File file) {

		String[] list = file.list();
		for (String f : list) {

			if (f.endsWith(".htm") || f.endsWith(".html")) {
				return f;
			}

		}

		return null;
	}

	/**
	 * 保存上传来的文件
	 * 
	 * @param parms
	 * @param files
	 * @throws ResponseException
	 */
	private Response saveUploadFiles(Map<String, String> parms, Map<String, String> files) {
		if (parms.containsKey("Upload") && parms.containsKey("Filedata")
				&& parms.containsKey("Filename")) {
			// final String saveFile = uploadDir + File.separator +
			// parms.get("Filename");
			// fileUploadListener.onFileUpload(parms.get("Filename"));
			final String srcFile = files.get("Filedata");
			// FileUitls.reNameFile(srcFile, saveFile);
			// Log.d(TAG, "save file to " + saveFile + " finish");
		}
		return new Response(Status.OK, MIMEType.HTML, "");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.android.wfupload.http.server.NanoHTTPD#serve(java.lang.String,
	 * com.braver.android.wfupload.http.server.NanoHTTPD.Method, java.util.Map,
	 * java.util.Map, java.util.Map)
	 */
	@Override
	public Response serve(String uri, Method method, Map<String, String> headers,
			Map<String, String> parms, Map<String, String> files) {
		Log.e(TAG, "request uri:" + uri);

		if (method.equals(Method.GET)) {
			return executeGet(uri, method, headers, parms, files);
		} else if (method.equals(Method.POST)) {
			//
			Log.e(TAG, "parms:" + parms + " files:" + files);
			if (null != parms && null != files) {
				return saveUploadFiles(parms, files);
			}

			return new Response(Status.OK, MIMEType.HTML, "");
		}

		return super.serve(uri, method, headers, parms, files);
	}

	public interface FileUploadListener {

		void onFileUpload(String filename);

	}

}
