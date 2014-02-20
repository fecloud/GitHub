/**
 * @(#) GOHomWeb.java Created on 2014-1-2
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.gohome.webapp;

import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

/**
 * The class <code>GOHomeWeb</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class GOHomeWeb extends WebSocketServlet {

	private static final long serialVersionUID = 1L;
	private volatile int byteBufSize;
	private volatile int charBufSize;

	private Hashtable<WebGOHomeConsole, WebGoHomeMessageInbound> hashtable = new Hashtable<WebGOHomeConsole, WebGoHomeMessageInbound>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		byteBufSize = getInitParameterIntValue("byteBufferMaxSize", 2097152);
		charBufSize = getInitParameterIntValue("charBufferMaxSize", 2097152);
	}

	public int getInitParameterIntValue(String name, int defaultValue) {
		String val = this.getInitParameter(name);
		int result;
		if (null != val) {
			try {
				result = Integer.parseInt(val);
			} catch (Exception x) {
				result = defaultValue;
			}
		} else {
			result = defaultValue;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.catalina.websocket.WebSocketServlet#createWebSocketInbound
	 * (java.lang.String, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected StreamInbound createWebSocketInbound(String arg0, HttpServletRequest arg1) {
		String goHomeArgs = arg1.getParameter("args");
		final String webRoot = arg1.getServletContext().getRealPath("/");
		System.out.println("goHomeArgs:" + goHomeArgs + " webRoot:" + webRoot);
		final WebGOHomeConsole console = new WebGOHomeConsole(goHomeArgs, hashtable);
		console.setWebRoot(webRoot);
		final WebGoHomeMessageInbound inbound = new WebGoHomeMessageInbound(console, byteBufSize,
				charBufSize);
		hashtable.put(console, inbound);
		return inbound;
	}

}
