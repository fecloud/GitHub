/**
 * @(#) Message.java Created on 2014-1-2
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.gohome;

import net.sf.json.JSONObject;

/**
 * The class <code>Message</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class Message {

	/**
	 * 普通消息
	 */
	public static final String TYPE_GENERAL = "type_general";

	/**
	 * 下载成功验证码
	 */
	public static final String TYPE_PASS_CODE = "type_pass_code";
	/**
	 * 登录成功
	 */
	public static final String TYPE_LOGIN_SUCESS = "type_login_sucess";

	/**
	 * 输入验证码
	 */
	public static final String TYPE_INPUT_PASS_CODE = "type_input_pass_code";

	private String type = "";

	private String content = "";

	public Message() {

	}

	/**
	 * @param type
	 * @param content
	 */
	public Message(String type, String content) {
		super();
		this.type = type;
		this.content = content;
	}

	public String toJSON() {
		JSONObject object = new JSONObject();
		object.put("type", type);
		object.put("content", content);
		return object.toString();
	}

	public void toMessage(String json) {
		JSONObject object = JSONObject.fromObject(json);
		if (null != object) {

			if (object.getString("type") != null) {
				this.type = object.getString("type");
			}

			if (object.getString("content") != null) {
				this.content = object.getString("content");
			}
		}
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
}
