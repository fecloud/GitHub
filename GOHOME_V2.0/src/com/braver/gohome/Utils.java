/**
 * @(#) Utils.java Created on 2014-1-1
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.gohome;

import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.log4j.Logger;

import com.braver.gohome.business.cookie.Cookie;

/**
 * The class <code>Utils</code>
 * 
 * @author braver
 * @version 1.0
 */
public class Utils {

	static Logger logger = Logger.getLogger(Utils.class);

	public static void sleep(int s) {
		try {
			Thread.sleep(s * 1000);
		} catch (InterruptedException e) {
			logger.error("", e);
		}
	}

	/**
	 * 格式化当前时间
	 * 
	 * @return
	 */
	public static String dataString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(new Date());
	}

	/**
	 * 格式化当前时间20天后
	 * 
	 * @return
	 */
	public static String dataStringAfter(int day) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, day);
		return dateFormat.format(c.getTime());
	}

	/**
	 * HttpResponse 设置Cookie
	 * 
	 * @param httpResponse
	 */
	public static final void setCookie(HttpResponse httpResponse, Cookie cookieContainer) {
		if (null != httpResponse) {
			final Header[] header = httpResponse.getAllHeaders();
			if (null != header) {
				for (Header h : header) {
					if (h.getName().equals("Set-Cookie")) {
						final String cookie = h.getValue();
						final String[] c = cookie.split(";");
						if (null != c) {
							for (String s : c) {
								if (null != s.trim()) {
									final Properties properties = new Properties();
									try {
										properties.load(new StringReader(s));
										for (Object key : properties.keySet()) {
											if (key.equals("JSESSIONID")
													|| key.equals("BIGipServerotn"))
												cookieContainer.addCookie(key.toString(),
														properties.getProperty((String) key));
										}
									} catch (IOException e) {
										logger.error("", e);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 请求头添加Cookie
	 * 
	 * @param requestBase
	 */
	public static final void addCookie(HttpRequestBase requestBase, Cookie cookieContainer) {
		if (null != requestBase && !cookieContainer.getCookie().isEmpty()) {
			Set<Entry<String, String>> sets = cookieContainer.getCookie().entrySet();
			final StringBuffer buffer = new StringBuffer();
			Iterator<Entry<String, String>> it = sets.iterator();
			Entry<String, String> en = null;
			while (it.hasNext()) {
				en = it.next();
				buffer.append(en.getKey()).append("=").append(en.getValue()).append(";");
			}
			if (buffer.toString().endsWith(";")) {
				requestBase.addHeader("Cookie", buffer.substring(0, buffer.length() - 1));
			} else {
				requestBase.addHeader("Cookie", buffer.substring(0, buffer.length()));
			}
		}
	}

	public static void stringLineToHeader(HttpRequestBase request, String line) {
		if (null != line) {
			String[] headers = null;
			line = line.trim();
			headers = line.split(":");
			if (headers != null && headers.length == 2) {
				request.addHeader(headers[0].trim(), headers[1].trim());
			}
		}
	}

	public static String jsDateToString(long time) {
		final String[] EN_M = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep",
				"Oct", "Nov", "Dec" };
		HashMap<String, String> dayofweek = new HashMap<String, String>();
		dayofweek.put("Mon", "Mon");
		dayofweek.put("Tue", "Tue");
		dayofweek.put("Wed", "Wed");
		dayofweek.put("Thu", "Thu");
		dayofweek.put("Fri", "Fri");
		dayofweek.put("Sat", "Sat");
		dayofweek.put("Sun", "Sun");

		dayofweek.put("星期一", "Mon");
		dayofweek.put("星期二", "Tue");
		dayofweek.put("星期三", "Wed");
		dayofweek.put("星期四", "Thu");
		dayofweek.put("星期五", "Fri");
		dayofweek.put("星期六", "Sat");
		dayofweek.put("星期天", "Sun");
		final String string = "%1$s %2$s %3$s %4$s 00:00:00 GMT%5$s (中国标准时间)";
		return String.format(string, dayofweek.get(formatTime(time, "E")),
				EN_M[Integer.valueOf(formatTime(time, "M")) - 1], formatTime(time, "d"),
				formatTime(time, "yyyy"), formatTime(time, "Z"));

	}

	public static String formatTime(long time, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(new Date(time));
	}
}
