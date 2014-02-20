/**
 * @(#) ASPTestCase.java Created on 2012-7-25
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.I9108;

import java.util.ArrayList;
import java.util.List;

import com.aspire.android.test.exception.MException;
import com.aspire.android.test.execute.CommandConstants;
import com.aspire.android.test.execute.ContentValues;
import com.aspire.android.test.testcase.AbstractTestCase;

/**
 * The class <code>ASPTestCase</code>
 * 
 * @author zhenghui
 * @version 1.0
 */
public abstract class ASPTestCase extends AbstractTestCase {
    public final static String TAG = "AspireCase";

    protected void logScreen(String str) throws MException {
        deviceEntity.logScreen(CommandConstants.LOG_LEVEL_DEBUG, str);
    }

    /**
     * 在超时时间内查找未读匹配的短信
     * 
     * @param from
     *            发送方地址， 包含关系: 如from为10086，可匹配100860001
     * @param keys
     *            关键字
     * @param flag
     *            true为 关键字为and关系， false 关键字为或关系
     * @param timeOut
     *            超时时间
     * @return
     * @throws MException 
     */
    protected boolean findSms(String from, String[] keys, boolean flag,
            int timeOut) throws MException {
        long start = System.currentTimeMillis();
        boolean result = false;
        while (System.currentTimeMillis() - start < timeOut) {
            try {
                List<ContentValues> smss = deviceEntity.getMessage(from, -1, 2);
                for (ContentValues sms : smss) {
                    String body = sms
                            .getAsString(CommandConstants.KEY_GETMESSAGE_BODY);
                    boolean comFlag;
                    if (flag) {
                        comFlag = true;
                        for (String key : keys) {
                            if (!body.contains(key)) {
                                comFlag = false;
                                break;
                            }
                        }
                    } else {
                        comFlag = false;
                        for (String key : keys) {
                            if (body.contains(key)) {
                                comFlag = true;
                                break;
                            }
                        }
                    }
                    if (comFlag) {
                        result = true;
                        break;
                    }
                }
            } catch (Exception e1) {
                logError("Error: " + e1.getMessage());
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {

            }
        }

        return result;
    }

    /**
     * 在超时时间内查找未读匹配的短信，查找匹配成功回复replyBody
     * 
     * @param from
     *            发送方地址， 包含关系: 如from为10086，可匹配100860001
     * @param keys
     *            关键字
     * @param flag
     *            true为 关键字为and关系， false 关键字为或关系
     * @param timeOut
     *            超时时间
     * @return
     * @throws MException
     */
    public boolean findAndReplySms(String from, String[] keys, boolean flag,
            String replyBody, int timeOut) throws MException {
        long start = System.currentTimeMillis();
        boolean result = false;
        String sourAddress = null;
        while (System.currentTimeMillis() - start < timeOut) {
            try {
                List<ContentValues> smss = deviceEntity.getMessage(from, -1, 2);
                for (ContentValues sms : smss) {
                    String body = sms
                            .getAsString(CommandConstants.KEY_GETMESSAGE_BODY);
                    boolean comFlag;
                    if (flag) {
                        comFlag = true;
                        for (String key : keys) {
                            if (!body.contains(key)) {
                                comFlag = false;
                                break;
                            }
                        }
                    } else {
                        comFlag = false;
                        for (String key : keys) {
                            if (body.contains(key)) {
                                comFlag = true;
                                break;
                            }
                        }
                    }
                    if (comFlag) {
                        result = true;
                        sourAddress = sms
                                .getAsString(CommandConstants.KEY_GETMESSAGE_SOURCEADDRESS);
                        break;
                    }
                }
            } catch (Exception e1) {
                logError("Error: " + e1.getMessage());
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {

            }
        }

        if (sourAddress != null) {
            try {
                result = deviceEntity.sendMessage(sourAddress, replyBody);
            } catch (MException e) {
                logError("SendMsg to " + sourAddress + " Error: "
                        + e.getMessage());
            }
        }

        return result;
    }

    /**
     * 在超时时间内查找from发送的未读短信
     * 
     * @param from
     *            发送方地址， 包含关系: 如from为10086，可匹配100860001
     * @param timeOut
     *            超时时间
     * @return
     * @throws MException 
     */
    protected List<String> findSms(String from, int timeOut) throws MException {
        long start = System.currentTimeMillis();
        List<String> bodys = new ArrayList<String>();
        while (System.currentTimeMillis() - start < timeOut) {
            try {
                List<ContentValues> smss = deviceEntity.getMessage(from, -1, 2);
                for (ContentValues sms : smss) {
                    String body = sms
                            .getAsString(CommandConstants.KEY_GETMESSAGE_BODY);
                    bodys.add(body);
                }
            } catch (Exception e1) {
                logError("Error: " + e1.getMessage());
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {

            }
        }

        return bodys;
    }
    
	public void exitApp() throws MException{
		

	    //按键按下   
	    deviceEntity.keyDown(4);
	    //按键弹起  
	    deviceEntity.keyUp(4);
	    if (findAndClick(20, 400, "SamSungI9108_999006_128_45_queding.bmp", 128, 45, 20, 60000)){
			logDebug("退出客户端成功");
	    }
	    else
	    {
	    	deviceEntity.stopApp("评估程序1");
	    	logDebug("强制退出客户端");
	    }
		}

}
