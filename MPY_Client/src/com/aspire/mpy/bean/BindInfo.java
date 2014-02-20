package com.aspire.mpy.bean;

import java.io.Serializable;

public class BindInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String bindStr;
	
	private String password;

	public String getBindStr() {
		return bindStr;
	}

	public void setBindStr(String bindStr) {
		this.bindStr = bindStr;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
