/**
 * @(#) Response.java Created on 2013-9-13
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.nanohttpd.response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import com.braver.nanohttpd.MIMEType;
import com.braver.nanohttpd.request.Method;


/**
 * The class <code>Response</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class Response {
	/**
	 * HTTP status code after processing, e.g. "200 OK", HTTP_OK
	 */
	private Status status;
	/**
	 * MIME type of content, e.g. "text/html"
	 */
	private MIMEType mimeType;
	/**
	 * Data of the response, may be null.
	 */
	private InputStream data;
	/**
	 * Headers for the HTTP response. Use addHeader() to add lines.
	 */
	private Map<String, String> header = new HashMap<String, String>();
	/**
	 * The request method that spawned this response.
	 */
	private Method requestMethod;

	/**
	 * Default constructor: response = HTTP_OK, mime = MIME_HTML and your
	 * supplied message
	 */
	public Response(String msg) {
		this(Status.OK, MIMEType.HTML, msg);
	}

	/**
	 * Basic constructor.
	 */
	public Response(Status status, MIMEType mimeType, InputStream data) {
		this.status = status;
		this.mimeType = mimeType;
		this.data = data;
	}

	/**
	 * Convenience method that makes an InputStream out of given text.
	 */
	public Response(Status status, MIMEType mimeType, String txt) {
		this.status = status;
		this.mimeType = mimeType;
		try {
			this.data = txt != null ? new ByteArrayInputStream(txt.getBytes("UTF-8")) : null;
		} catch (java.io.UnsupportedEncodingException uee) {
			uee.printStackTrace();
		}
	}

	/**
	 * Adds given line to the header.
	 */
	public void addHeader(String name, String value) {
		header.put(name, value);
	}

	/**
	 * Sends given response to the socket.
	 */
	public void send(OutputStream outputStream) {
		MIMEType mime = mimeType;
		SimpleDateFormat gmtFrmt = new SimpleDateFormat("E, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
		gmtFrmt.setTimeZone(TimeZone.getTimeZone("GMT"));

		try {
			if (status == null) {
				throw new Error("sendResponse(): Status can't be null.");
			}
			PrintWriter pw = new PrintWriter(outputStream);
			pw.print("HTTP/1.1 " + status.getDesc() + " \r\n");

			if (mime != null) {
				pw.print("Content-Type: " + mime + "\r\n");
			}

			if (header == null || header.get("Date") == null) {
				pw.print("Date: " + gmtFrmt.format(new Date()) + "\r\n");
			}

			if (header != null) {
				for (String key : header.keySet()) {
					String value = header.get(key);
					pw.print(key + ": " + value + "\r\n");
				}
			}

			int pending = data != null ? data.available() : 0; // This is to
																// support
																// partial
																// sends, see
																// serveFile()
			pw.print("Connection: keep-alive\r\n");
			pw.print("Content-Length: " + pending + "\r\n");

			pw.print("\r\n");
			pw.flush();

			if (requestMethod != Method.HEAD && data != null) {
				int BUFFER_SIZE = 16 * 1024;
				byte[] buff = new byte[BUFFER_SIZE];
				while (pending > 0) {
					int read = data
							.read(buff, 0, ((pending > BUFFER_SIZE) ? BUFFER_SIZE : pending));
					if (read <= 0) {
						break;
					}
					outputStream.write(buff, 0, read);

					pending -= read;
				}
			}
			outputStream.flush();
			//TODO safeClose(data);
		} catch (IOException ioe) {
			// Couldn't write? No can do.
		}
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public MIMEType getMimeType() {
		return mimeType;
	}

	public void setMimeType(MIMEType mimeType) {
		this.mimeType = mimeType;
	}

	public InputStream getData() {
		return data;
	}

	public void setData(InputStream data) {
		this.data = data;
	}

	public Method getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(Method requestMethod) {
		this.requestMethod = requestMethod;
	}
}
