/**
 * @(#) HashCookie.java Created on 2014-1-1
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.gohome.business.cookie;

import java.util.Hashtable;

/**
 * The class <code>HashCookie</code>
 * 
 * @author braver
 * @version 1.0
 */
public class HashCookie implements Cookie{

	private Hashtable<String, String> hashtable = new Hashtable<String, String>();
	
	/* (non-Javadoc)
	 * @see com.braver.gohome.business.cookie.Cookie#getCookie()
	 */
	@Override
	public Hashtable<String, String> getCookie() {
		return hashtable;
	}

	/* (non-Javadoc)
	 * @see com.braver.gohome.business.cookie.Cookie#addCookie(java.lang.String, java.lang.String)
	 */
	@Override
	public void addCookie(String key, String value) {
		hashtable.put(key, value);
	}

}
