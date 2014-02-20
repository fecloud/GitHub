/**
 * @(#) GoHome.java Created on 2014-1-1
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.gohome;

import java.io.File;

import org.apache.log4j.Logger;

import com.braver.gohome.business.cookie.Cookie;
import com.braver.gohome.business.cookie.HashCookie;
import com.braver.gohome.business.task.InitTaskExecute;
import com.braver.gohome.resouce.Station_Name;
import com.braver.gohome.task.BaseTask;
import com.braver.gohome.task.Task;
import com.braver.gohome.task.service.SingleThreadTaskService;

/**
 * The class <code>GoHome</code>
 * 
 * @author braver
 * @version 1.0
 */
public class GoHome {

	Logger logger = Logger.getLogger(GoHome.class);

	private GoHomeIO goHomeIO;

	protected DingPiaoInfo piaoInfo = new DingPiaoInfo();

	protected CheCiOption cheCiOption = new CheCiOption();

	protected Cookie cookie = new HashCookie();

	protected SingleThreadTaskService service = new SingleThreadTaskService();

	/**
	 * 网址
	 */
	protected String site = "http://kyfw.12306.cn";

	/**
	 * 访问模式
	 */
	protected String model = "http";
	/**
	 * http超时时间
	 */
	protected int timeout = 10000;
	/**
	 * 验证码存放地址
	 */
	protected String passcodeimage;;
	/**
	 * 用户名
	 */
	protected String username;
	/**
	 * 用户密码
	 */
	protected String password;

	/**
	 * 代理地址
	 */
	protected String proxy;

	protected String redirect;

	/**
	 * @param goHomeIO
	 */
	public GoHome(GoHomeIO goHomeIO) {
		super();
		this.passcodeimage = System.getProperty("user.dir") + File.separator + "PassCode.jpg";
		this.goHomeIO = goHomeIO;
		Station_Name.getInstance();
	}

	/**
	 * @param goHomeIO
	 * @param username
	 * @param passwor
	 */
	public GoHome(GoHomeIO goHomeIO, String username, String password) {
		super();
		this.passcodeimage = System.setProperty("passcodeimage", System.getProperty("user.dir")
				+ File.separator + "PassCode.jpg");
		this.goHomeIO = goHomeIO;
		this.username = username;
		this.password = password;
	}

	public void startGoHome() {
		final Task task = new BaseTask(new InitTaskExecute(this));
		service.addTask(task);
	}

	public void stopGOHome() {
		service.getPool().shutdownNow();
		service.getContainer().clear();
	}

	/**
	 * @return the goHomeIO
	 */
	public GoHomeIO getGoHomeIO() {
		return goHomeIO;
	}

	/**
	 * @param goHomeIO
	 *            the goHomeIO to set
	 */
	public void setGoHomeIO(GoHomeIO goHomeIO) {
		this.goHomeIO = goHomeIO;
	}

	/**
	 * @return the piaoInfo
	 */
	public DingPiaoInfo getPiaoInfo() {
		return piaoInfo;
	}

	/**
	 * @param piaoInfo
	 *            the piaoInfo to set
	 */
	public void setPiaoInfo(DingPiaoInfo piaoInfo) {
		this.piaoInfo = piaoInfo;
	}

	/**
	 * @return the site
	 */
	public String getSite() {
		return site;
	}

	/**
	 * @param site
	 *            the site to set
	 */
	public void setSite(String site) {
		this.site = site;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the timeout
	 */
	public int getTimeout() {
		return timeout;
	}

	/**
	 * @param timeout
	 *            the timeout to set
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	/**
	 * @return the passcodeimage
	 */
	public String getPasscodeimage() {
		return passcodeimage;
	}

	/**
	 * @param passcodeimage
	 *            the passcodeimage to set
	 */
	public void setPasscodeimage(String passcodeimage) {
		this.passcodeimage = passcodeimage;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
		logger.error("username:" + username);
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
		logger.error("password:" + password);
	}

	/**
	 * @return the proxy
	 */
	public String getProxy() {
		return proxy;
	}

	/**
	 * @param proxy
	 *            the proxy to set
	 */
	public void setProxy(String proxy) {
		this.proxy = proxy;
	}

	/**
	 * @return the cheCiOption
	 */
	public CheCiOption getCheCiOption() {
		return cheCiOption;
	}

	/**
	 * @param cheCiOption
	 *            the cheCiOption to set
	 */
	public void setCheCiOption(CheCiOption cheCiOption) {
		this.cheCiOption = cheCiOption;
	}

	/**
	 * @return the cookie
	 */
	public Cookie getCookie() {
		return cookie;
	}

	/**
	 * @return the service
	 */
	public SingleThreadTaskService getService() {
		return service;
	}

	/**
	 * @return the redirect
	 */
	public String getRedirect() {
		return redirect;
	}

	/**
	 * @param redirect the redirect to set
	 */
	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

}
