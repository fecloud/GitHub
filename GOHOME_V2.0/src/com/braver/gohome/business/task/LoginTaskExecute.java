/**
 * @(#) LoginTask.java Created on 2014-1-2
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.gohome.business.task;

import org.apache.log4j.Logger;

import com.braver.gohome.GoHome;
import com.braver.gohome.Message;
import com.braver.gohome.Utils;
import com.braver.gohome.business.Business;
import com.braver.gohome.business.HttpBusiness;
import com.braver.gohome.business.HttpsBusiness;
import com.braver.gohome.exception.BusinessException;
import com.braver.gohome.task.BaseTask;
import com.braver.gohome.task.Task;
import com.braver.gohome.task.TaskStatus;
import com.braver.gohome.task.executeable.TaskExecuteable;

/**
 * The class <code>LoginTask</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class LoginTaskExecute implements TaskExecuteable {

	Logger logger = Logger.getLogger(LoginTaskExecute.class);

	private GoHome goHome;

	protected Business business;

	protected String html;

	protected String passCode;

	/**
	 * @param goHome
	 */
	public LoginTaskExecute(GoHome goHome) {
		super();
		this.goHome = goHome;
		init();
	}

	protected void init() {
		if (goHome.getModel().equalsIgnoreCase("https")) {
			// https
			business = new HttpsBusiness(goHome);
		} else {
			// http
			business = new HttpBusiness(goHome);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.gohome.task.executeable.TaskExecuteable#executeTask(com.braver
	 * .gohome.task.Task)
	 */
	@Override
	public void executeTask(Task task) {
		if (task.getTaskStatus() == TaskStatus.SUBMIT) {
			task.setTaskStatus(TaskStatus.DOING);
			boolean todo = true;
			goHome.getGoHomeIO().writeln("进入登录页面========================");
			while (todo) {
				try {
					if (task.getTaskStatus() == TaskStatus.DOING) {
						otnLoginInit();
					} else {
						todo = false;
					}

					if (task.getTaskStatus() == TaskStatus.DOING) {
						validatePassCode();
					} else {
						todo = false;
					}
					if (task.getTaskStatus() == TaskStatus.DOING) {
						loginAysnSuggest();
					} else {
						todo = false;
					}
					if (task.getTaskStatus() == TaskStatus.DOING) {
						if (otnLoginUserLogin()) {
							goHome.getGoHomeIO().writeMessage(
									new Message(Message.TYPE_LOGIN_SUCESS, ""));
							// 添加下一个任务
							final Task t = new BaseTask(new QueryHuoCheExecute(goHome));
							goHome.getService().addTask(t);
							break;
						}
					} else {
						todo = false;
					}
				} catch (Exception e) {
					logger.error("", e);
					goHome.getGoHomeIO().writeErrorLn("出现错误暂停2s");
					Utils.sleep(1);
				}

			}
			goHome.getGoHomeIO().writeln("登录任务成功完成");
		}
	}

	/**
	 * 下载登录页面
	 * 
	 * @throws BusinessException
	 */
	protected void otnLoginInit() throws BusinessException {
		goHome.getGoHomeIO().writeln("清除Cookie");
		goHome.getCookie().getCookie().clear();
		goHome.getGoHomeIO().writeln("加载登录页面...");
		int count = 3;
		boolean sucess = false;
		while (count > 0) {
			html = business.otnLoginInit();
			if (null != html) {
				sucess = true;
				goHome.getGoHomeIO().writeln("加载登录页面成功");
				break;
			}
			count--;
		}
		if (!sucess) {
			throw new BusinessException("加载登录页面错误");
		}

	}

	/**
	 * 下载验证码
	 */
	protected void downLoginPassCode() throws BusinessException {
		if (null != html && business.getPassCode(html, "img_rand_code") != null) {
			String passcodeUrl = business.getPassCode(html, "img_rand_code");
			int count = 3;
			boolean sucess = false;
			while (count > 0) {
				goHome.getGoHomeIO().writeln("开始下载登录验证码...");
				passcodeUrl = passcodeUrl.replace("amp;", ""); // 去除去路径中的错误/otn/passcodeNew/getPassCodeNew?module=login&amp;rand=sjrand"
				if (business.downloadPassCode(passcodeUrl, goHome.getPasscodeimage())) {
					goHome.getGoHomeIO().writeMessage(
							new Message(Message.TYPE_PASS_CODE, goHome.getPasscodeimage()));
					sucess = true;
					break;
				}
				count--;
			}
			if (sucess) {
				goHome.getGoHomeIO().writeln("下载登录验证码成功");
			} else {
				throw new BusinessException("下载登录验证码错误");
			}
		} else {
			throw new BusinessException("加载登录页面错误,无法取得登录验证码下载路径");
		}
	}

	/**
	 * 输入 验证码
	 * 
	 * @throws BusinessException
	 */
	protected void validatePassCode() throws BusinessException {
		int count = 4;
		boolean sucess = false;
		while (count > 0) {
			downLoginPassCode();
			goHome.getGoHomeIO().writeln("请输入登录验证码:");
			goHome.getGoHomeIO().writeMessage(new Message(Message.TYPE_INPUT_PASS_CODE, ""));
			final String inputPassCode = goHome.getGoHomeIO().readLine();
			if (null != inputPassCode && !inputPassCode.trim().equals("")) {
				if (business.checkLoginPassCode(inputPassCode)) {
					this.passCode = inputPassCode;
					sucess = true;
					break;
				}
			}
			count--;
		}
		if (sucess) {
			goHome.getGoHomeIO().writeln("登录验证码验证成功");
		} else {
			throw new BusinessException("登录验证码验证错误");
		}
	}

	/**
	 * ajax登录
	 * 
	 * @throws BusinessException
	 */
	protected void loginAysnSuggest() throws BusinessException {
		int count = 4;
		boolean sucess = false;
		while (count > 0) {
			goHome.getGoHomeIO().writeln("开始登录 ...");
			String loginStr = business.loginAysnSuggest(goHome.getUsername(), goHome.getPassword(),
					this.passCode);
			if (null == loginStr) {
				sucess = true;
				break;
			} else {
				goHome.getGoHomeIO().writeErrorLn(loginStr);
			}
			count--;
		}
		if (sucess) {
			goHome.getGoHomeIO().writeln("登录成功");
		} else {
			throw new BusinessException("登录错误");
		}
	}

	/**
	 * POSR登录表单,返回跳转地址
	 * 
	 * @return
	 */
	protected boolean otnLoginUserLogin() throws BusinessException {
		String redirect = "";
		int count = 4;
		boolean sucess = false;
		while (count > 0) {
			goHome.getGoHomeIO().writeln("登录表单,返回跳转地址 ...");
			redirect = business.otnLoginUserLogin();
			if (null != redirect) {
				goHome.setRedirect(redirect);
				sucess = true;
				break;
			}
			count--;
		}
		if (sucess) {
			goHome.getGoHomeIO().writeln("登录表单,返回跳转地址:" + redirect);
			return true;
		} else {
			throw new BusinessException("登录表单,返回跳转地址错误");
		}
	}
}
