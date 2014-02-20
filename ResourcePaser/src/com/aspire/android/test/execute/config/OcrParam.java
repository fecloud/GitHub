/**
 * @(#) OcrParam.java Created on 2012-7-24
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute.config;

/**
 * The class <code>OcrParam</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class OcrParam {

	/**
	 * OCR Constants
	 */
	public static final int OCR_NUMBER = 0;
	public static final int OCR_ENGLISH = 1;
	public static final int OCR_SIMPLIPIED_CHINESE = 2;
	public static final int OCR_TRADITIONAL_CHINESE = 3;

	public String refId = null;
	public String key;
	public int x1;
	public int y1;
	public int x2;
	public int y2;
	public int scale;
	public int threshold;
	public boolean doTrim;
	public boolean doJoin;
	public int lang = -1;
	public String matcher;

	public String area = null;
	public String trim = null;
	public String join = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "{key:" + key + ", area:(" + x1 + ", " + y1 + ", " + x2 + ", "
				+ y2 + "), scale: " + scale + ", threshold: " + threshold
				+ ", trim:" + doTrim + ", join:" + doJoin + ", matcher:"
				+ matcher +", lang:" + lang + "}";
	}

	/**
	 * @return the refId
	 */
	public String getRefId() {
		return refId;
	}

	/**
	 * @param refId
	 *            the refId to set
	 */
	public void setRefId(String refId) {
		this.refId = refId;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the matcher
	 */
	public String getMatcher() {
		return matcher;
	}

	/**
	 * @param matcher
	 *            the matcher to set
	 */
	public void setMatcher(String matcher) {
		this.matcher = matcher;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(String area) {
		this.area = area;
		if (area != null && area.length() > 0) {
			String[] vs = area.split(",");
			this.x1 = (vs.length > 0 && vs[0].length() > 0) ? Integer
					.parseInt(vs[0]) : 0;
			this.y1 = (vs.length > 1 && vs[1].length() > 0) ? Integer
					.parseInt(vs[1]) : 0;
			this.x2 = (vs.length > 2 && vs[2].length() > 0) ? Integer
					.parseInt(vs[2]) : 0;
			this.y2 = (vs.length > 3 && vs[3].length() > 0) ? Integer
					.parseInt(vs[3]) : 0;
		}
	}

	/**
	 * @return the trim
	 */
	public String getTrim() {
		return trim;
	}

	/**
	 * @param trim
	 *            the trim to set
	 */
	public void setTrim(String trim) {
		this.trim = trim;
		this.doTrim = (trim != null && trim.length() > 0) ? Boolean
				.parseBoolean(trim) : false;
	}

	/**
	 * @return the join
	 */
	public String getJoin() {
		return join;
	}

	/**
	 * @param join
	 *            the join to set
	 */
	public void setJoin(String join) {
		this.join = join;
		this.doJoin = (join != null && join.length() > 0) ? Boolean
				.parseBoolean(join) : false;
	}

	/**
	 * @return the x1
	 */
	public int getX1() {
		return x1;
	}

	/**
	 * @return the y1
	 */
	public int getY1() {
		return y1;
	}

	/**
	 * @return the x2
	 */
	public int getX2() {
		return x2;
	}

	/**
	 * @return the y2
	 */
	public int getY2() {
		return y2;
	}

	/**
	 * @return the doTrim
	 */
	public boolean isDoTrim() {
		return doTrim;
	}

	/**
	 * @return the doJoin
	 */
	public boolean isDoJoin() {
		return doJoin;
	}

	public int getLang() {
		return lang;
	}

	public static int parseLang(String lang) {
		if ("number".equalsIgnoreCase(lang)) {
			return OCR_NUMBER;
		} else if ("english".equalsIgnoreCase(lang)) {
			return OCR_ENGLISH;
		} else if ("simplipied_chinese".equalsIgnoreCase(lang)) {
			return OCR_SIMPLIPIED_CHINESE;
		} else if ("traditional_chinese".equalsIgnoreCase(lang)) {
			return OCR_TRADITIONAL_CHINESE;
		} else {
			throw new IllegalArgumentException("no found type:" + lang);
		}
	}

}
