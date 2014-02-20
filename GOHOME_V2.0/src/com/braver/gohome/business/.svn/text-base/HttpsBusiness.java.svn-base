/**
 * @(#) HttpsBusiness.java Created on 2014-1-3
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.gohome.business;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import com.braver.gohome.GoHome;

/**
 * The class <code>HttpsBusiness</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class HttpsBusiness extends HttpBusiness {

	Logger logger = Logger.getLogger(HttpsBusiness.class);

	/**
	 * @param home
	 */
	public HttpsBusiness(GoHome home) {
		super(home);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.business.HttpBusiness#getHttpClient()
	 */
	@Override
	public HttpClient getHttpClient() {

		InputStream instream = null;
		SSLContext sslcontext = null;
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			instream = getClass().getResourceAsStream("/12306.keystore");
			trustStore.load(instream, "12306cer".toCharArray());
			sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore).build();
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			try {
				instream.close();
			} catch (IOException e) {
				logger.error("", e);
			}
		}

		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext,
				SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		return httpclient;
	}
}
