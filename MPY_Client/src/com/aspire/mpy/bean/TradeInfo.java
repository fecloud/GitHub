package com.aspire.mpy.bean;

import java.io.Serializable;

public class TradeInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static interface TradeType{
		
		public static final int GPS = 1;  // 
		
		public static final int OTHER = 0;
	}
	
	private int type ;
	
	private String xinfo;
	
	private String yinfo;
	
	private String zinfo;

	public TradeInfo(){}
	
	public TradeInfo(int type, String xinfo, String yinfo, String zinfo) {
		super();
		this.type = type;
		this.xinfo = xinfo;
		this.yinfo = yinfo;
		this.zinfo = zinfo;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getXinfo() {
		return xinfo;
	}

	public void setXinfo(String xinfo) {
		this.xinfo = xinfo;
	}

	public String getYinfo() {
		return yinfo;
	}

	public void setYinfo(String yinfo) {
		this.yinfo = yinfo;
	}

	public String getZinfo() {
		return zinfo;
	}

	public void setZinfo(String zinfo) {
		this.zinfo = zinfo;
	}
	
	

}
