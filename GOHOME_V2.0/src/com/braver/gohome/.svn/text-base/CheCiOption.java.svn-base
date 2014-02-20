/**
 * @(#) CheCiOption.java Created on 2014-1-1
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.gohome;

import java.util.ArrayList;
import java.util.List;

/**
 * The class <code>CheCiOption</code> 车次选择
 * 
 * @author braver
 * @version 1.0
 */
public class CheCiOption {

	/**
	 * swz_num 商务 tz_num 特等 zy_num 一等 ze_num 二等 rw_num 软卧 yw_num 硬卧 rz_num 软座
	 * yz_num 硬座 wz_num 无座
	 */
	public static ZuoWei zuoweiDing[] = { new ZuoWei(), new ZuoWei("swz_num", "商务座"),
			new ZuoWei("tz_num", "特等座"), new ZuoWei("zy_num", "一等座"), new ZuoWei("ze_num", " 二等座"),
			new ZuoWei("rw_num", "软卧"), new ZuoWei("yw_num", "硬卧"), new ZuoWei("rz_num", "软座"),
			new ZuoWei("yz_num", "硬座"), new ZuoWei("wz_num", "无座") };

	/**
	 * 车次
	 */
	List<String> checi = new ArrayList<String>();

	private String checi_String;

	/**
	 * 坐位
	 */
	List<ZuoWei> zuowei = new ArrayList<ZuoWei>();

	/**
	 * 发车时间
	 */
	String start_time;
	/**
	 * 
	 */
	String end_time;

	/**
	 * @return the checi
	 */
	public List<String> getCheci() {
		return checi;
	}

	/**
	 * @param checi
	 *            the checi to set
	 */
	public void setCheci(List<String> checi) {
		this.checi = checi;
	}

	public void setCheciString(String str) {
		checi_String = str;
		if (null != str && !"".equals(str.trim()) && !"*".equals(str.trim())) {
			parserChici(str);
		}
	}

	/**
	 * @return the zuowei
	 */
	public List<ZuoWei> getZuowei() {
		return zuowei;
	}

	/**
	 * @return the start_time
	 */
	public String getStart_time() {
		return start_time;
	}

	/**
	 * @param start_time
	 *            the start_time to set
	 */
	public void setStart_time(String start_time) {
		if (start_time != null) {
			this.start_time = start_time;
		}

	}

	/**
	 * @return the end_time
	 */
	public String getEnd_time() {
		return end_time;
	}

	/**
	 * @param end_time
	 *            the end_time to set
	 */
	public void setEnd_time(String end_time) {
		if (end_time != null) {
			this.end_time = end_time;
		}
	}

	/**
	 * @param zuowei
	 *            the zuowei to set
	 */
	public void setZuowei(List<ZuoWei> zuowei) {
		this.zuowei = zuowei;
	}

	public void setZuoweiString(String str) {
		parserZuowei(str);
	}

	public void parserChici(String chici) {
		if (null != chici && !"".equals(chici.trim())) {
			final String strings[] = chici.trim().split("-");
			if (null != strings) {
				for (String s : strings) {
					if (!"".equals(s.trim())) {
						checi.add(s);
					}
				}
			}
		}
	}

	public void parserZuowei(String zuowei) {

		if (null != zuowei && !"".equals(zuowei.trim())) {
			final String strings[] = zuowei.trim().split("|");
			if (null != strings) {
				for (String s : strings) {
					if (!"".equals(s.trim())) {
						this.zuowei.add(zuoweiDing[Integer.valueOf(s)]);
					}
				}
			}
		}
	}

	/**
	 * 座位优先顺序
	 * 
	 * @return
	 */
	public String zuoweiToString() {
		final StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < this.zuowei.size(); i++) {
			buffer.append(this.zuowei.get(i).getZh_name());
			if (i < this.zuowei.size() - 1) {
				buffer.append(" > ");
			}
		}
		return buffer.toString();
	}

	/**
	 * 格式化车次选择
	 * 
	 * @return
	 */
	public String checiToString() {
		final StringBuffer buffer = new StringBuffer();
		for (String str : checi) {
			buffer.append(str).append("  ");
		}
		return buffer.toString();
	}

	/**
	 * @return the checi_String
	 */
	public String getCheci_String() {
		return checi_String;
	}

}
