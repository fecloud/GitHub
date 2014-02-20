package com.aspire.eldmose.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;

import android.util.Log;

public class HttpConnectWrapper {
	private static final String TAG = "HttpConnectWrapper";
	protected static String cmwapip = "http://10.0.0.172:80/";
	private HttpURLConnection httpConn = null;
	private InputStream is = null;

	/**
	 * 发起�?��http post请求，返回结果InputStream
	 * 
	 * @param url
	 *            String 请求的url
	 * @param contentType
	 *            String httprequest头中的ContentType
	 * @param postdata
	 *            byte[] post的数�?
	 * @param isCMWap
	 *            boolean 是否使用CMWAP 连接
	 * @param requestProperty
	 *            HashMap �?setRequestProperty 中设置的属�?和�?
	 * @return InputStream
	 * @throws IOException
	 */
	public InputStream httpPost(String url, String contentType, byte[] postdata, int networkType,
			Hashtable<String, String> requestProperty ,int read_timeout) throws IOException {
		InputStream is = null;
		httpConn = openConnectionPost(url, contentType, networkType, requestProperty, postdata ,read_timeout);
		Tools.isLegalHttpUrl(url);
		try {
			is = getHttpContent(httpConn);
		} catch (Exception e) {
			// TODO: handle exception
			try {
				if (httpConn != null) {
					httpConn.disconnect();
				}
			} catch (Exception ex) {
			} finally {
				httpConn = null;
			}
		}

		return is;
	}

	public HttpURLConnection getConnection() {
		return httpConn;
	}

	public InputStream getHttpContent(HttpURLConnection httpConn) throws IOException {
		try {
			int code = httpConn.getResponseCode();
			Log.e(TAG, "httpConn.getResponseCode()" + code);
			if (code != HttpURLConnection.HTTP_OK && code != HttpURLConnection.HTTP_MOVED_TEMP) {
				Log.e(TAG, "connecting to the server error occurs ( response code =" + code + ")");
				throw new IOException("connecting to the server error occurs,response code = "
						+ code);
			}

			is = httpConn.getInputStream();
		} catch (IOException e) {
			Log.e(TAG, "reading input stream timeout");
			try {
				if (is != null)
					is.close();
				is = null;
			} catch (Exception ex) {
			}
			throw e;
		}

		return is;
	}

	public HttpURLConnection openConnectionPost(String url, String contentType, int networkType,
			Hashtable<String , String> requestProperty, byte[] postdata, int readTimeout) throws IOException {
		Log.i(TAG, "POST url = " + url);
		HttpURLConnection httpConn = null;
		OutputStream dos = null;
		Tools.isLegalHttpUrl(url);
		URL urlObj = null;
		try {
			// 如果是CMWAP
			if (Const.NetworkType.CMWAP == networkType
					|| Const.NetworkType.CMWAP == Tools.checkNetWorkType()) {
				Log.i(TAG, "is CMWAP  = true");
				HttpURLParam urlparam = new HttpURLParam(url);
				String CMWAPurl = cmwapip + urlparam.getPath();
				urlObj = new URL(CMWAPurl);
				httpConn = (HttpURLConnection) urlObj.openConnection();
				httpConn.setRequestProperty("X-Online-Host", urlparam.getHost() + ":"
						+ urlparam.getPort());
				urlparam = null;
			} else {
				Log.i(TAG, "is CMWAP  = false");
				urlObj = new URL(url);
				httpConn = (HttpURLConnection) urlObj.openConnection();
			}
			httpConn.setDoOutput(true);
			httpConn.setRequestMethod("POST");

			httpConn.setConnectTimeout(Config.HTTP_CONNECT_TIMEOUT);
			httpConn.setReadTimeout(readTimeout);
			if (requestProperty != null) {
				Enumeration<String> perpertyKeys = requestProperty.keys();
				while (perpertyKeys.hasMoreElements()) {
					String key = (String) perpertyKeys.nextElement();
					String value = (String) requestProperty.get(key);
					httpConn.setRequestProperty(key, value);
				}
			}
			httpConn.setRequestProperty("Content-Type", contentType);
			httpConn.setRequestProperty("contentlength", Integer.toString(postdata.length));
			dos = httpConn.getOutputStream();
			dos.write(postdata);
			// if (postdata.length > 1024) {
			// dos.flush();
			// }
			return httpConn;
		} finally {
			try {
				if (dos != null) {
					dos.close();
				}
			} catch (Exception ex) {
			} finally {
				dos = null;
			}

		}
	}

	public void releaseConnect() {
		try {
			if (is != null) {
				is.close();
			}
			if (httpConn != null) {
				httpConn.disconnect();
			}
		} catch (Exception ex) {
		} finally {
			is = null;
			httpConn = null;
		}
	}

	/**
	 * 发起�?��http get请求，返回结果byte[]
	 * 
	 * @param url
	 *            String http请求的完整url
	 * @param isCMWap
	 *            boolean 是否使用CMWAP接入点，false表示不用，也就是使用cmnet
	 * @param requestProperty
	 *            �?setRequestProperty 中设置的属�?和�?
	 * @return byte[]
	 */
	public InputStream httpGet(String url, boolean isCMWap, Hashtable<String , String> requestProperty)
			throws IOException {
		HttpURLConnection httpConn = openConnectionGet(url, isCMWap, requestProperty);
		// #if "${wtk.debug}" == "true"
		Tools.isLegalHttpUrl(url);
		// #endif
		try {
			return getHttpContent(httpConn);
		} finally {
			try {
				if (httpConn != null) {
					httpConn.disconnect();
				}
			} catch (Exception ex) {
			} finally {
				httpConn = null;
			}
		}
	}

	public static HttpURLConnection openConnectionGet(String url, boolean isCMWap,
			Hashtable<String , String> requestProperty) throws IOException {
		HttpURLConnection httpConn = null;
		// #if "${wtk.debug}" == "true"
		Tools.isLegalHttpUrl(url);
		// #endif

		URL urlObj = null;
		HttpURLParam urlparam = new HttpURLParam(url);
		// 如果是CMWAP
		if (isCMWap) {
			String CMWAPurl = cmwapip + urlparam.getPath();
			urlObj = new URL(CMWAPurl);
			httpConn = (HttpURLConnection) urlObj.openConnection();
			httpConn.setRequestProperty("X-Online-Host", urlparam.getHost() + ":"
					+ urlparam.getPort());
		} else {
			urlObj = new URL(url);
			httpConn = (HttpURLConnection) urlObj.openConnection();
		}
		httpConn.setDoOutput(true);
		httpConn.setRequestMethod("Get");

		if (requestProperty != null) {
			Enumeration<String> perpertyKeys = requestProperty.keys();
			while (perpertyKeys.hasMoreElements()) {
				String key = (String) perpertyKeys.nextElement();
				String value = (String) requestProperty.get(key);
				httpConn.setRequestProperty(key, value);
			}
		}
		httpConn.setRequestProperty("Connection", "close");
		return httpConn;
	}
}
