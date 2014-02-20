/**
 * @(#) UpdateResultPassword.java Created on 2012-6-21
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.password;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.aspire.common.config.Config;
import com.aspire.common.exception.ExceptionHandler;
import com.aspire.common.util.HttpClient;
import com.aspire.common.util.MobileManagerConstants;
import com.aspire.mgt.ats.tm.sync.XmlResponseProcessor;

/**
 * The class <code>UpdateResultPassword</code>更新测试脚本所用的密钥
 * 
 * @author likai
 * @version 1.0
 */
public class UpdateResultPassword {

	private String url;
	private Config config;
	public UpdateResultPassword(Config config) {
		this.url = "http://10.1.5.154:8080/sqmp/generateKey/username/password";
		this.config = config;
	}

	/**
	 * 密钥检查更新入口
	 */
	public void execute() {
		if (url != null && url.length() != 0) {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
	        String runTime = sdf.format(new Date());
	        boolean needUpdate = false;
            String updateTime = config.get(MobileManagerConstants.UPDATE_RESULT_PWD_TIME);
            if (updateTime == null || updateTime.length() == 0) {
                needUpdate = true;
            } else {
                needUpdate = checkUpdateTime(updateTime, runTime,
                        sdf);
            }

	        if (needUpdate) {
	            String cur = getResultPwdByUrl(runTime);
	            if (cur != null) {
	                System.out.println("cur = " + cur);
	            }
	        }
		}
	}

	/**
	 * 根据时间检查是否需要更新
	 * 
	 * @param currentPwd
	 *            当前的密钥
	 * @param updateTime
	 *            上次更新的年月
	 * @param runTime
	 *            现在的年月
	 * @param sdf
	 *            日期格式化
	 * @return 返回true表示需要更新，false标示不需要更新
	 */
	private boolean checkUpdateTime(String updateTime,
			String runTime, SimpleDateFormat sdf) {
		boolean needUpdate = false;
		if (!runTime.equals(updateTime)) {
			try {
				Date updateDate = sdf.parse(updateTime);
				Calendar c = Calendar.getInstance();
				int year = c.get(Calendar.YEAR);
				int month = c.get(Calendar.MONTH);
				c.setTime(updateDate);
				c.add(Calendar.MONTH, 1);
				c.getTime();
				if (year == c.get(Calendar.YEAR)
						&& month == c.get(Calendar.MONTH)) {
					needUpdate = true;
				} else {
					needUpdate = true;
				}
			} catch (Exception e) {
				ExceptionHandler.handle(e, "check the 'UPDATE_RESULT_PWD_TIME'");
				needUpdate = true;
			}
		}
		return needUpdate;
	}

	/**
	 * 通过http获取密钥
	 * 
	 * @param url
	 *            获取密钥的url
	 * @param runTime
	 *            需要获取那一月的密钥
	 * @return 返回密钥值
	 */
	private String getResultPwdByUrl(String runTime) {
		if (!url.endsWith("/")) {
			url += "/";
		}
		UpdatePasswordHandler passwordHandler = new UpdatePasswordHandler();
		new HttpClient().request(url + runTime + "01", null,
				new XmlResponseProcessor(passwordHandler));
		return passwordHandler.getPwd();
	}
}
