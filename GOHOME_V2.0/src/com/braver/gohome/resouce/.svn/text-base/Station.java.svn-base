/**
 * @(#) Station.java Created on 2014-1-1
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.gohome.resouce;

import java.util.ArrayList;

/**
 * The class <code>Station</code>
 * 
 * @author braver
 * @version 1.0
 */
public class Station {

	private String zh_name;

	private String cz;
	
	private ArrayList<String> mathers = new ArrayList<String>();

	public void parse(String s) {
		if (null != s) {
			final String[] strings =s .split("\\|");
			if (strings != null && strings.length == 6) {
				zh_name = strings[1];
				cz = strings[2];
				mathers.add(strings[0]);
				mathers.add(strings[1]);
				mathers.add(strings[2]);
				mathers.add(strings[3]);
				mathers.add(strings[4]);
			}
		}
	}

	/**
	 * @return the zh_name
	 */
	public String getZh_name() {
		return zh_name;
	}

	/**
	 * @param zh_name
	 *            the zh_name to set
	 */
	public void setZh_name(String zh_name) {
		this.zh_name = zh_name;
	}

	/**
	 * @return the cz
	 */
	public String getCz() {
		return cz;
	}

	/**
	 * @param cz
	 *            the cz to set
	 */
	public void setCz(String cz) {
		this.cz = cz;
	}

	/**
	 * @return the mathers
	 */
	public ArrayList<String> getMathers() {
		return mathers;
	}

	/**
	 * @param mathers the mathers to set
	 */
	public void setMathers(ArrayList<String> mathers) {
		this.mathers = mathers;
	}
	
	public boolean mathersContain(String m){
		for(String s:mathers){
			if(s.startsWith(m)){
				return true;
			}
		}
		return false;
	}
}
