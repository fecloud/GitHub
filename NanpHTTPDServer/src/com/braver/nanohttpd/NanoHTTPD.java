/**
 * @(#) NanoHttp.java Created on 2013-9-13
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.nanohttpd;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.braver.nanohttpd.handler.DefaultHTTPHandler;
import com.braver.nanohttpd.handler.HTTPHandler;
import com.braver.nanohttpd.tmp.TempFileManager;
import com.braver.nanohttpd.tmp.TempFileManagerFactory;
import com.braver.nanohttpd.tmp.impl.DefaultTempFileManagerFactory;

/**
 * The class <code>NanoHTTPD</code> A simple, tiny, nicely embeddable HTTP
 * server in Java
 * <p/>
 * <p/>
 * NanoHTTPD
 * <p>
 * </p>
 * Copyright (c) 2012-2013 by Paul S. Hawke, 2001,2005-2013 by Jarno Elonen,
 * 2010 by Konstantinos Togias</p>
 * <p/>
 * <p/>
 * <b>Features + limitations: </b>
 * <ul>
 * <p/>
 * <li>Only one Java file</li>
 * <li>Java 5 compatible</li>
 * <li>Released as open source, Modified BSD licence</li>
 * <li>No fixed config files, logging, authorization etc. (Implement yourself if
 * you need them.)</li>
 * <li>Supports parameter parsing of GET and POST methods (+ rudimentary PUT
 * support in 1.25)</li>
 * <li>Supports both dynamic content and file serving</li>
 * <li>Supports file upload (since version 1.2, 2010)</li>
 * <li>Supports partial content (streaming)</li>
 * <li>Supports ETags</li>
 * <li>Never caches anything</li>
 * <li>Doesn't limit bandwidth, request time or simultaneous connections</li>
 * <li>Default code serves files and shows all HTTP parameters and headers</li>
 * <li>File server supports directory listing, index.html and index.htm</li>
 * <li>File server supports partial content (streaming)</li>
 * <li>File server supports ETags</li>
 * <li>File server does the 301 redirection trick for directories without '/'</li>
 * <li>File server supports simple skipping for files (continue download)</li>
 * <li>File server serves also very long files without memory overhead</li>
 * <li>Contains a built-in list of most common mime types</li>
 * <li>All header names are converted lowercase so they don't vary between
 * browsers/clients</li>
 * <p/>
 * </ul>
 * <p/>
 * <p/>
 * <b>How to use: </b>
 * <ul>
 * <p/>
 * <li>Subclass and implement serve() and embed to your own program</li>
 * <p/>
 * </ul>
 * <p/>
 * See the separate "LICENSE.md" file for the distribution license (Modified BSD
 * licence)
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public abstract class NanoHTTPD {
	/**
	 * Pseudo-Parameter to use to store the actual query string in the
	 * parameters map for later re-processing.
	 */
	public static final String QUERY_STRING_PARAMETER = "NanoHttpd.QUERY_STRING";
	private final String hostname;
	private final int myPort;
	private ServerSocket myServerSocket;

	private Thread myThread;
	/**
	 * Pluggable strategy for asynchronously executing requests.
	 */
	private AsyncRunner asyncRunner;
	/**
	 * Pluggable strategy for creating and cleaning up temporary files.
	 */
	private TempFileManagerFactory tempFileManagerFactory;

	/**
	 * Constructs an HTTP server on given port.
	 */
	public NanoHTTPD(int port) {
		this(null, port);
	}

	/**
	 * Constructs an HTTP server on given hostname and port.
	 */
	public NanoHTTPD(String hostname, int port) {
		this.hostname = hostname;
		this.myPort = port;
		setTempFileManagerFactory(new DefaultTempFileManagerFactory());
		setAsyncRunner(new DefaultAsyncRunner());
	}

	/**
	 * Start the server.
	 * 
	 * @throws IOException
	 *             if the socket is in use.
	 */
	public void start() throws IOException {
		myServerSocket = new ServerSocket();
		myServerSocket.bind((hostname != null) ? new InetSocketAddress(hostname, myPort)
				: new InetSocketAddress(myPort));

		myThread = new Thread(new Runnable() {
			@Override
			public void run() {
				do {
					try {
						final Socket finalAccept = myServerSocket.accept();
						final InputStream inputStream = finalAccept.getInputStream();
						if (inputStream == null) {
							Utils.safeClose(finalAccept);
						} else {
							asyncRunner.exec(new Runnable() {
								@Override
								public void run() {
									OutputStream outputStream = null;
									try {
										outputStream = finalAccept.getOutputStream();
										TempFileManager tempFileManager = tempFileManagerFactory
												.create();
										HTTPHandler session = conHandler();

										while (!finalAccept.isClosed()) {
											session.execute(tempFileManager, inputStream,
													outputStream);
										}
									} catch (Exception e) {
										// When the socket is closed by the
										// client, we throw our own
										// SocketException
										// to break the "keep alive" loop above.
										if (!(e instanceof SocketException && "NanoHttpd Shutdown"
												.equals(e.getMessage()))) {
											e.printStackTrace();
										}
									} finally {
										Utils.safeClose(outputStream);
										Utils.safeClose(inputStream);
										Utils.safeClose(finalAccept);
									}
								}
							});
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				} while (!myServerSocket.isClosed());
			}
		});
		myThread.setDaemon(false);
		myThread.setName("NanoHttpd Main Listener");
		myThread.start();
	}

	protected HTTPHandler conHandler() {
		return new DefaultHTTPHandler();
	}

	/**
	 * Stop the server.
	 */
	public void stop() {
		try {
			Utils.safeClose(myServerSocket);
			myThread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public final int getListeningPort() {
		return myServerSocket == null ? -1 : myServerSocket.getLocalPort();
	}

	public final boolean wasStarted() {
		return myServerSocket != null && myThread != null;
	}

	public final boolean isAlive() {
		return wasStarted() && !myServerSocket.isClosed() && myThread.isAlive();
	}

	/**
	 * Decode parameters from a URL, handing the case where a single parameter
	 * name might have been supplied several times, by return lists of values.
	 * In general these lists will contain a single element.
	 * 
	 * @param parms
	 *            original <b>NanoHttpd</b> parameters values, as passed to the
	 *            <code>serve()</code> method.
	 * @return a map of <code>String</code> (parameter name) to
	 *         <code>List&lt;String&gt;</code> (a list of the values supplied).
	 */
	protected Map<String, List<String>> decodeParameters(Map<String, String> parms) {
		return this.decodeParameters(parms.get(QUERY_STRING_PARAMETER));
	}

	/**
	 * Decode parameters from a URL, handing the case where a single parameter
	 * name might have been supplied several times, by return lists of values.
	 * In general these lists will contain a single element.
	 * 
	 * @param queryString
	 *            a query string pulled from the URL.
	 * @return a map of <code>String</code> (parameter name) to
	 *         <code>List&lt;String&gt;</code> (a list of the values supplied).
	 */
	protected Map<String, List<String>> decodeParameters(String queryString) {
		Map<String, List<String>> parms = new HashMap<String, List<String>>();
		if (queryString != null) {
			StringTokenizer st = new StringTokenizer(queryString, "&");
			while (st.hasMoreTokens()) {
				String e = st.nextToken();
				int sep = e.indexOf('=');
				String propertyName = (sep >= 0) ? Utils.decodePercent(e.substring(0, sep)).trim()
						: Utils.decodePercent(e).trim();
				if (!parms.containsKey(propertyName)) {
					parms.put(propertyName, new ArrayList<String>());
				}
				String propertyValue = (sep >= 0) ? Utils.decodePercent(e.substring(sep + 1))
						: null;
				if (propertyValue != null) {
					parms.get(propertyName).add(propertyValue);
				}
			}
		}
		return parms;
	}

	// -------------------------------------------------------------------------------
	// //
	//
	// Threading Strategy.
	//
	// -------------------------------------------------------------------------------
	// //

	/**
	 * Pluggable strategy for asynchronously executing requests.
	 * 
	 * @param asyncRunner
	 *            new strategy for handling threads.
	 */
	public void setAsyncRunner(AsyncRunner asyncRunner) {
		this.asyncRunner = asyncRunner;
	}

	// -------------------------------------------------------------------------------
	// //
	//
	// Temp file handling strategy.
	//
	// -------------------------------------------------------------------------------
	// //

	/**
	 * Pluggable strategy for creating and cleaning up temporary files.
	 * 
	 * @param tempFileManagerFactory
	 *            new strategy for handling temp files.
	 */
	public void setTempFileManagerFactory(TempFileManagerFactory tempFileManagerFactory) {
		this.tempFileManagerFactory = tempFileManagerFactory;
	}

}
