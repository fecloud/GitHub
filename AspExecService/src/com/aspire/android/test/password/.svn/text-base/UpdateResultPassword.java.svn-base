/**
 * @(#) UpdateResultPassword.java Created on 2012-6-21
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.password;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.aspire.android.test.PreferencesManager;
import com.aspire.android.test.execute.NameConstants.SharedPrefConstants;
import com.aspire.android.test.execute.ui.PrefsActivity;
import com.aspire.android.test.log.RunLogger;
import com.aspire.android.test.sync.pub.RemoteHttpClient;
import com.aspire.mgt.ats.tm.sync.XmlResponseProcessor;
import com.aspire.mgt.ats.tm.sync.password.PasswordHandler;

/**
 * The class <code>UpdateResultPassword</code>更新测试脚本所用的密钥
 * 
 * @author likai
 * @version 1.0
 */
public class UpdateResultPassword {

    private RunLogger runLogger = RunLogger.getInstance();

    private SharedPreferences preferences;
    private PreferencesManager preferencesManager = PreferencesManager.getInstance();

    private Editor editor;

    private String url;
    private String userName;
    private String pwd;

    public UpdateResultPassword() {
        this.preferences = preferencesManager.getPreferences();
        url = preferences.getString(PrefsActivity.PWD_UPDATE_URL, null);
        userName = preferences.getString(PrefsActivity.PWD_UPDATE_USERNAME, null);
        pwd = preferences.getString(PrefsActivity.PWD_UPDATE_PWD, null);
        editor = preferences.edit();
    }

    /**
     * 密钥检查更新入口
     */
    public void execute() {
        runLogger.debug(getClass(), "update result password start");
        if (url != null && url.length() != 0) {
            updateCurrentPwd(url);
            Calendar c = Calendar.getInstance();
            int day = c.get(Calendar.DAY_OF_MONTH);
            int nextDay = 26;
            if (day >= nextDay) {
                runLogger.debug(getClass(), "update next month's result password");
                updateNextPwd();
            }
        } else {
            runLogger.debug(getClass(), "update result password's url is null");
        }
        runLogger.debug(getClass(), "update result password finish");
    }

    /**
     * 更新当月密钥
     * 
     * @param url
     *            更新密钥的url
     */
    public void updateCurrentPwd(String url) {
        runLogger.debug(getClass(), "update current result password");
        String currentPwd = preferences.getString(SharedPrefConstants.RESULT_PWD_CURRENT, null);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String runTime = sdf.format(new Date());
        boolean needUpdate = false;
        if (currentPwd == null || currentPwd.length() == 0) {
            needUpdate = true;
        } else {
            String updateTime = preferences.getString(SharedPrefConstants.UPDATE_RESULT_PWD_TIME, null);
            if (updateTime == null || updateTime.length() == 0) {
                needUpdate = true;
            } else {
                needUpdate = checkUpdateTime(currentPwd, updateTime, runTime, sdf);
            }
        }
        if (needUpdate) {
            String cur = getResultPwdByUrl(runTime);
            runLogger.debug(getClass(), "update current result password is " + cur);
            if (cur != null) {
                editor.putString(SharedPrefConstants.RESULT_PWD_CURRENT, cur);
                editor.putString(SharedPrefConstants.UPDATE_RESULT_PWD_TIME, runTime);
                editor.commit();

            }
        } else {
            runLogger.debug(getClass(), "current result password need not to update");
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
    private boolean checkUpdateTime(String currentPwd, String updateTime, String runTime, SimpleDateFormat sdf) {
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
                if (year == c.get(Calendar.YEAR) && month == c.get(Calendar.MONTH)) {
                    String nextPwd = preferences.getString(SharedPrefConstants.RESULT_PWD_NEXT, null);
                    if (nextPwd == null || nextPwd.length() == 0) {
                        needUpdate = true;
                    } else {
                        replaceResultPwdFromNextToCur(nextPwd, runTime);
                    }
                } else {
                    needUpdate = true;
                }
            } catch (Exception e) {
                runLogger.error(getClass(), "check the 'UPDATE_RESULT_PWD_TIME'" + e.getMessage());
                needUpdate = true;
            }
        }
        return needUpdate;
    }

    /**
     * 用下一月的密钥更新当年过期的密钥
     * 
     * @param nextPwd
     *            下一月的密钥
     * @param runTime
     *            现在的时间，需要记录
     */
    private void replaceResultPwdFromNextToCur(String nextPwd, String runTime) {
        editor.putString(SharedPrefConstants.RESULT_PWD_CURRENT, nextPwd);
        editor.putString(SharedPrefConstants.UPDATE_RESULT_PWD_TIME, runTime);
        editor.putString(SharedPrefConstants.RESULT_PWD_NEXT, "");
        editor.commit();

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
        String httpString = url + userName + "/" + pwd + "/";
        PasswordHandler passwordHandler = new PasswordHandler();
        new RemoteHttpClient().request(httpString + runTime + "01", null, new XmlResponseProcessor(passwordHandler));
        return passwordHandler.getPwd();
    }

    /**
     * 获取下一月的密钥
     * 
     * @param url
     *            获取密钥的url
     */
    private void updateNextPwd() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String nextPassword = preferences.getString(SharedPrefConstants.RESULT_PWD_NEXT, null);
        if (nextPassword == null || nextPassword.equals("")) {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, 1);
            String runTime = sdf.format(c.getTime());
            String next = getResultPwdByUrl(runTime);
            runLogger.debug(getClass(), "next month's result password is " + next);
            if (next != null) {
                editor.putString(SharedPrefConstants.RESULT_PWD_NEXT, next);
                editor.commit();

            }
        }
    }
}
