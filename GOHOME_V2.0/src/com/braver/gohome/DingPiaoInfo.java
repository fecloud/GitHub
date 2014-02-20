/**
 * @(#) DianPiaoInfo.java Created on 2013-12-31
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.gohome;

import com.braver.gohome.resouce.Station;

/**
 * The class <code>DianPiaoInfo</code>
 * 
 * @author Administrator
 * @version 1.0
 */
public class DingPiaoInfo {

	private String date;

	private Station start;

	private Station end;

	private String purpose_codes = "ADULT";

	private String ren;

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
		if (null == date || "".equals(date.trim())) {
			this.date = Utils.dataStringAfter(19);
		}
	}

	/**
	 * @return the start
	 */
	public Station getStart() {
		return start;
	}

	/**
	 * @param start
	 *            the start to set
	 */
	public void setStart(Station start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public Station getEnd() {
		return end;
	}

	/**
	 * @param end
	 *            the end to set
	 */
	public void setEnd(Station end) {
		this.end = end;
	}

	/**
	 * @return the purpose_codes
	 */
	public String getPurpose_codes() {
		return purpose_codes;
	}

	/**
	 * @param purpose_codes
	 *            the purpose_codes to set
	 */
	public void setPurpose_codes(String purpose_codes) {
		this.purpose_codes = purpose_codes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString();
	}

	/**
	 * @return the ren
	 */
	public String getRen() {
		return ren;
	}

	/**
	 * @param ren
	 *            the ren to set
	 */
	public void setRen(String ren) {
		if (null == ren || "".equals(ren.trim())) {
			ren = "欧阳锋";
		}
		this.ren = ren;
	}

}
